package org.kuaidi.web.springboot.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.*;
import org.kuaidi.bean.vo.DubboMsgVO;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.*;
import org.kuaidi.utils.JBarCodeUtil;
import org.kuaidi.web.springboot.util.redis.OrderUtil;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;


import net.sf.json.JSONObject;

@Component
public class HandlingOrdersService {
	
	@Reference(version = "1.0.0")
	private UserService  userService; 
	
	@Reference(version = "1.0.0")
	private IEforcesOrderService orderService; 
	
	@Reference(version = "1.0.0")
	private IEforcesSentscanService  sentScanService; 
	
	@Reference(version = "1.0.0")
	private IEforceslogisticstrackingService  logisticstrackingService; 
	
	@Reference(version = "1.0.0")
	private IEforcesIncmentService  incmentService ;

	@Reference(version = "1.0.0")
	private IEforcesReceivedscanService receivedscanService;
	
	@Autowired
	private OrderUtil orderUtil ; 
	
	@Autowired
	private RedisUtil  redisUtil;
	
	@Value("${file.baseDir}")
    private String baseDir;
	
	@Reference(version = "1.0.0")
	private IEforcesBiggingScanService  biggingScanService; 
	
	@Reference(version= "1.0.0")
	private IEforcesRemovingBagScanService  removeBageService; 
	
	public ResultVo sentOrder(String billNumber , EforcesUser userInfo ,EforcesIncment currentStop){
		try {
    		List<EforcesOrder> orderList =  orderService.getByNumber(billNumber);
    		if(orderList == null || orderList.size() == 0 ) {
    			return ResultUtil.exec(false,"订单号错误，请确定！",null);
    		}
    		if(userInfo == null ) {
    			return ResultUtil.exec(false,"用户信息错误，请确定！",null);
    		}
    		EforcesOrder  orderInfo = orderList.get(0);
    		
    		List <EforcesSentScan> sentScanList = sentScanService.getSentScanByNumber(billNumber, userInfo.getIncnumber());
    		if(sentScanList != null && sentScanList.size() > 0 ) {
    			EforcesSentScan  sentScan = sentScanList.get(0);
    			if(orderInfo.getNum() <= 1  ||  orderInfo.getNum() ==  sentScan.getGoodsCount() ) {
    				return ResultUtil.exec(false,"订单已经发送出去，请确定！",null);
    			}else if(sentScan  != null && orderInfo.getNum() >  sentScan.getGoodsCount()) {
    				// 更新数量， 然后返回保存成功！
    				sentScan.setGoodsCount(sentScan.getGoodsCount() + 1);
    				Integer rst = sentScanService.updateSentScan(sentScan);
    				if(rst > 0 ) {
    					return ResultUtil.exec(true,"收件成功！",null);
    				}
    			}
    		}
    		String nextIncNum = getNextIncNumber(userInfo, orderInfo);
    		if(nextIncNum.length() == 0 ){
    			return ResultUtil.exec(false,"没有找到下一站的编号，确定一下是否发错了！",null);
    		}
    		EforcesIncment nextStop =  incmentService.selectByNumber(nextIncNum);
    		EforcesSentScan sentScan = createSentScanInfo(userInfo, orderInfo, nextStop, currentStop,0);
    		/*
    		 * 封装物流信息
    		 */
    		String description = "快件在【%s】由【%s】扫描发往【%s】";
    		String nextStopName = "";
    		if(nextStop != null ) {
    			nextStopName  = nextStop.getName();
    		}
    		description = String.format(description, currentStop.getName(), userInfo.getName(), nextStopName);
    		EforcesLogisticStracking stracking =  getLogisticstracking(billNumber,description,userInfo.getName(), currentStop.getName(), currentStop.getNumber() ,3);
    		DubboMsgVO msgVo = sentScanService.addSentScan(sentScan, stracking);
    		if(msgVo  != null && msgVo.isRstFlage()) {
        		return ResultUtil.exec(true,"发单扫描成功！",null);
        	}else {
				return ResultUtil.exec(false,"发单扫描失败！",null);
			}
        }catch (Exception e){
				e.printStackTrace();
				return ResultUtil.exec(false,"发单扫描异常！",null);
        }
	}

	private EforcesSentScan createSentScanInfo(EforcesUser userInfo, EforcesOrder orderInfo, EforcesIncment nextStop,
			EforcesIncment currentStop, Integer isBagBill) {
		EforcesSentScan sentScan = new EforcesSentScan();
		sentScan.setBillsnumber(orderInfo.getNumber());
		sentScan.setCreatetime(new Date());
		sentScan.setFlightsnumber("");  // 可能需要
		sentScan.setGoodstype(orderInfo.getIsgoods()); // 需要确定一下
		sentScan.setExpresstype("物流");  // 需要判断
		sentScan.setExpressid(1); //	
		if(nextStop != null ) {
			sentScan.setNextstop(nextStop.getNumber());
			sentScan.setNextstopname(nextStop.getName()); 
		}else {
			sentScan.setNextstop("");
			sentScan.setNextstopname(""); 
		}
		sentScan.setScantype("发件扫描");

		sentScan.setScanners(userInfo.getName());
		sentScan.setScannerid(userInfo.getNumber());
		sentScan.setIncname(currentStop.getName());
		sentScan.setIncid(currentStop.getNumber());
		sentScan.setScantime(new Date());
		sentScan.setAmount(0);
		sentScan.setBz("");
		sentScan.setNextstop1("0");
		sentScan.setNextstopname1("");
		sentScan.setTranname("网络转运");
		sentScan.setIsBagBill(isBagBill);
		return sentScan;
	}

	private EforcesReceivedScan createReceiveScan(EforcesUser userInfo,EforcesIncment preIncment, EforcesIncment currentStop,
			EforcesOrder result , Integer isBagBill) {
		EforcesReceivedScan scan = new EforcesReceivedScan();
		scan.setBillsnumber(result.getNumber());
		scan.setCreatetime(new Date());
		if(StringUtils.isNotEmpty(result.getIsgoods())) {
			scan.setGoodstype(result.getIsgoods());
		}else {
			scan.setGoodstype("文件");
		}
		scan.setExpresstype("快递");
		scan.setExpressid(1);
		// lastStop 需要查找一下。
		scan.setFlightsnumber("第一班");
		scan.setScanners(userInfo.getName());
		scan.setScannerid(userInfo.getNumber());
		scan.setLaststop(preIncment.getNumber());
		scan.setLaststopname(preIncment.getName());
		scan.setIncid(currentStop.getNumber());
		scan.setIncname(currentStop.getName());
		scan.setScantime(new Date());
		scan.setAmount(0);
		scan.setBz("网络转运");
		scan.setTranname("第三方物流");
		scan.setIsBagBill(isBagBill);
		return scan;
	}

	/**
	 * 收件 根据订单号查询一条订单信息，并存入扫描表内
	 * @param billNumber
	 * @return
	 */
	public ResultVo receiveOrder(String billNumber ,  EforcesUser userInfo, EforcesIncment currentStop){
		try {
    		List<EforcesOrder> orderList =  orderService.getByNumber(billNumber);
    		if(orderList == null || orderList.size() == 0 ) {
			return ResultUtil.exec(false,"订单号错误，请确定！",null);
    		}
    		String preIncNum =  getPreIncNumber(userInfo, orderList.get(0));
    		/*
    		 * 没有上个节点的接单，只有一种情况，就是业务员收单。（单独处理）
    		 **/ 
    		if(preIncNum.length() == 0 ) {
				return ResultUtil.exec(false,"用户信息错误，请确定！",null);
			}
    		EforcesIncment preStop = incmentService.selectByNumber(preIncNum);
    		// 判断邮件发送地方是否错误。
    		EforcesOrder  result = orderList.get(0);
			EforcesReceivedScan scan = createReceiveScan(userInfo, preStop, currentStop, result, 0 );

			String description = "快件到达【%s】，上一站是【%s】,扫描员是【%s】";
    		description = String.format(description, currentStop.getName(),  "",userInfo.getName());
    		EforcesLogisticStracking stracking =  getLogisticstracking(billNumber,description,userInfo.getName(), currentStop.getName(), currentStop.getNumber() ,4);

    		DubboMsgVO msgVo  = receivedscanService.insertSelective(scan, stracking);
			if(msgVo != null && msgVo.isRstFlage() ) {
				return ResultUtil.exec(true,"收件扫描成功！",null);
			}
			return ResultUtil.exec(false,"收件成功失败",null);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"收件失败",null);
		}
	}

	/*
	 *根据当前用户信息，和订单信息，查询订单来自哪里 
	 * */
	private String  getPreIncNumber(EforcesUser userInfo,EforcesOrder orderInfo) {
		String preIncNumber = "";
		if(orderInfo != null) {
			int sameZoneLeve = getSameLevelByOrder(orderInfo);
			/*
			 * provincePrexIncNum, 这个变量是用来标识现在是在 fromXXX  的路上，还是在  toXXX 的位置。
			 */
			String userIncNum = userInfo.getIncnumber().trim();
			String provincePrexIncNum = "";
			if(StringUtils.isNotEmpty(userIncNum) && userIncNum.length() > 2) {
				provincePrexIncNum = userIncNum.substring(0, 2);
			}
			// 在from的线上
			if(StringUtils.isNotEmpty(orderInfo.getFromarea())  && 
					orderInfo.getFromarea().trim().startsWith(provincePrexIncNum)) {
				if(StringUtils.isNotEmpty(orderInfo.getFromprovince()) && 
						userIncNum.startsWith(orderInfo.getFromprovince().trim())
						) {
					preIncNumber = orderInfo.getFromcity();
				}
				if(StringUtils.isNotEmpty(orderInfo.getFromcity())   &&
						userIncNum.startsWith(orderInfo.getFromcity().trim())
						) {
					preIncNumber = orderInfo.getFromarea();
				}else if(StringUtils.isNotEmpty(orderInfo.getFromarea()) &&
						userIncNum.startsWith(orderInfo.getFromarea().trim())
						){
					preIncNumber = orderInfo.getFromareastreet();
				}
			}else  if(StringUtils.isNotEmpty(orderInfo.getToarea())  && 
					orderInfo.getToarea().trim().startsWith(provincePrexIncNum)) {
				if(StringUtils.isNotEmpty(orderInfo.getToprovince()) && 
						userIncNum.startsWith(orderInfo.getToprovince().trim())
						) {
					preIncNumber = orderInfo.getFromprovince();
				}else if(StringUtils.isNotEmpty(orderInfo.getTocity()) && 
						userIncNum.startsWith(orderInfo.getTocity().trim())
						) {
					preIncNumber = orderInfo.getToprovince();
				}else if(StringUtils.isNotEmpty(orderInfo.getToarea()) && 
						userIncNum.startsWith(orderInfo.getToarea().trim())
						) {
					preIncNumber = orderInfo.getTocity();
				}else if(StringUtils.isNotEmpty(orderInfo.getToareastreet()) && 
						userIncNum.startsWith(orderInfo.getToareastreet().trim())
						) {
					preIncNumber = orderInfo.getToarea();
				}
			}
			
			if(sameZoneLeve == 3 &&  StringUtils.isNotEmpty(orderInfo.getToareastreet()) &&
					userIncNum.startsWith(orderInfo.getToareastreet().trim())
					) {
				if(StringUtils.isEmpty(orderInfo.getFromarea())) {
					preIncNumber = orderInfo.getFromarea().trim();
				}
			}else if(sameZoneLeve == 2 && StringUtils.isNotEmpty(orderInfo.getToarea()) && 
					userIncNum.startsWith(orderInfo.getToarea().trim())
					) {
				if(StringUtils.isEmpty(orderInfo.getFromcity())) {
					preIncNumber = orderInfo.getFromcity().trim();
				}
			}else if(sameZoneLeve == 1 && StringUtils.isNotEmpty(orderInfo.getTocity()) && 
					userIncNum.startsWith(orderInfo.getTocity().trim())
					) {
				preIncNumber = orderInfo.getFromprovince().trim();
			}
		}
		if(preIncNumber.length() > 0 ) {
			preIncNumber = preIncNumber + "00";
		}
		return preIncNumber; 
	}
	
	private String  getNextIncNumber(EforcesUser userInfo,  EforcesOrder orderInfo) {
		String nextIncNumber = "";
		if(orderInfo != null) {
			int sameZoneLeve = getSameLevelByOrder(orderInfo);
			/*
			 * 不可能出现两个街道都是一样的， 如果这样的话，直接就送了，不用发送给下一跳
			 * */
			// 如果有分区的时候			
			/*
			 * provincePrexIncNum, 这个变量是用来标识现在是在 fromXXX  的路上，还是在  toXXX 的位置。
			 */
			String userIncNum = userInfo.getIncnumber().trim();
			String provincePrexIncNum = "";
			if(StringUtils.isNotEmpty(userIncNum) && userIncNum.length() > 2) {
				provincePrexIncNum = userIncNum.substring(0, 2);
			}
			if(StringUtils.isNotEmpty(orderInfo.getFromarea())  && 
					orderInfo.getFromarea().trim().startsWith(provincePrexIncNum)) {
				if(StringUtils.isNotEmpty(orderInfo.getFromprovince()) && 
						userIncNum.startsWith(orderInfo.getFromprovince().trim())
						) {
					nextIncNumber = orderInfo.getToprovince();
				}
				if(StringUtils.isNotEmpty(orderInfo.getFromcity())   &&
						userIncNum.startsWith(orderInfo.getFromcity().trim())
						) {
					nextIncNumber = orderInfo.getFromprovince();
				}else if(StringUtils.isNotEmpty(orderInfo.getFromarea()) &&
						userIncNum.startsWith(orderInfo.getFromarea().trim())
						){
					nextIncNumber = orderInfo.getFromcity();
				}else if(StringUtils.isNotEmpty(orderInfo.getFromareastreet()) &&
						userIncNum.startsWith(orderInfo.getFromareastreet().trim())) {
					nextIncNumber = orderInfo.getFromarea();
				}
				// 多加一级的话，还需要再写一级。
			}else if(StringUtils.isNotEmpty(orderInfo.getToarea())  && 
					orderInfo.getToarea().trim().startsWith(provincePrexIncNum)) {
				if(StringUtils.isNotEmpty(orderInfo.getToprovince()) && 
						userIncNum.startsWith(orderInfo.getToprovince().trim())
						) {
					nextIncNumber = orderInfo.getTocity();
				}else if(StringUtils.isNotEmpty(orderInfo.getTocity()) && 
						userIncNum.startsWith(orderInfo.getTocity().trim())
						) {
					nextIncNumber = orderInfo.getToarea();
				}else if(StringUtils.isNotEmpty(orderInfo.getToarea()) && 
						userIncNum.startsWith(orderInfo.getToarea().trim())
						) {
					nextIncNumber = orderInfo.getToareastreet();
				}
			}
			//相同的单独处理
			if(sameZoneLeve == 3 && 
					userIncNum.startsWith(orderInfo.getFromarea().trim())
					) {
				if(StringUtils.isNotEmpty(orderInfo.getToareastreet())) {
					nextIncNumber = orderInfo.getToareastreet().trim();
				}
			}else if(sameZoneLeve == 2 && 
					userIncNum.startsWith(orderInfo.getFromcity().trim())
					) {
				if(StringUtils.isNotEmpty(orderInfo.getToarea())) {
					nextIncNumber = orderInfo.getToarea().trim();
				}
			}else if(sameZoneLeve == 1 && 
					userIncNum.startsWith(orderInfo.getFromprovince().trim())
					) {
				if(StringUtils.isNotEmpty(orderInfo.getTocity())) {
					nextIncNumber = orderInfo.getTocity().trim();
				}
				
			}
		}
		if(nextIncNumber.length() > 0 ) {
			nextIncNumber = nextIncNumber + "00";
		}
		return nextIncNumber; 
	}

	/*
	 * 通过订单查询这个快递，在哪个地区等级上是相同的。
	 */
	private int getSameLevelByOrder(EforcesOrder orderInfo) {
		boolean sameZoneFlage = false; 
		int sameZoneLeve = 0 ; 
		if(StringUtils.isNotEmpty(orderInfo.getFromprovince()) && 
				StringUtils.isNotEmpty(orderInfo.getToprovince())
			&& StringUtils.equals(orderInfo.getFromprovince().trim(), orderInfo.getToprovince().trim())
				) {
			sameZoneFlage = true;
			sameZoneLeve = 1;
		}else {
			sameZoneFlage = false;
		}
		if(sameZoneFlage &&  StringUtils.isNotEmpty(orderInfo.getFromcity()) && 
				StringUtils.isNotEmpty(orderInfo.getTocity())
			&& StringUtils.equals(orderInfo.getFromcity().trim(), orderInfo.getTocity().trim())
				) {
			sameZoneLeve = 2;
		}else {
			sameZoneFlage = false;
		}
		
		if(sameZoneFlage &&  StringUtils.isNotEmpty(orderInfo.getFromarea()) && 
				StringUtils.isNotEmpty(orderInfo.getToarea())
			&& StringUtils.equals(orderInfo.getFromarea().trim(), orderInfo.getFromarea().trim())
				) {
			sameZoneLeve = 3;
		}else {
			sameZoneFlage = false;
		}
		return sameZoneLeve;
	}
	
	/*
	 * 保存物流信息。
	 * mark : 3 : 表示发送
	 * 		  4 ：  表示接收
	 */
	public EforcesLogisticStracking  getLogisticstracking(String billsNumber, String description,
			String operator, String incName, String incId, Integer mark) {
		EforcesLogisticStracking strackingInfo = new EforcesLogisticStracking();
		strackingInfo.setBillsnumber(billsNumber);
		strackingInfo.setDescription(description);
		strackingInfo.setOperators(operator);
		strackingInfo.setIncname(incName);
		strackingInfo.setIncid(incId);
		strackingInfo.setMark(mark);
		return strackingInfo;
	}
	
	/*
	 * 转包操作
	 * 认为所有的包的下一跳都是相同的（不同的下一跳，不可能不拆包）
	 */
	public ResultVo sendBagScan(HttpServletRequest request , String bagNumber,EforcesSentScan  sentScan) {
        Map<String,EforcesOrder> map = new HashMap<String, EforcesOrder>();
        /**
         * * 根据token获得用户信息
         */
        EforcesUser user = (EforcesUser) request.getAttribute("user");
        EforcesIncment incment = (EforcesIncment)request.getAttribute("inc");
        try {
        	if(StringUtils.isEmpty(bagNumber)) {
        		return  ResultUtil.exec(false, "参数错误！", null);
        	}
            // 根据包的编号查询出打包的订单
            List<EforcesBaggingScan> bagScanList =  biggingScanService.getBaggingScanByBagNum(bagNumber);
            if(bagScanList != null && bagScanList.size() > 0 ) {
                List<EforcesSentScan>  sentScanList = new ArrayList<EforcesSentScan>();
				/*
                 * 判断根据编号查询出打包的订单的订单编号判断不为空，根据此单号循环查询所有信息
                 * 多个单号，查询到放在map里面key是订单号valu是操磁订单号的信息
				*/
                List<String> stringList = new ArrayList<String>();
                for (int i = 0; i < bagScanList.size(); i++){
                    EforcesBaggingScan bagList = bagScanList.get(i);
                    if(bagList !=null ){
                        stringList.add(bagList.getNumberlist());
                    }
                }
                //判断stringList集合里面是否有值，里面只有订单编号
                if(stringList.size() > 0 ||stringList != null){
                    //多个订单编号查询
                    List<EforcesOrder> eforcesOrders = orderService.getAllNumberMsg(stringList);
                    //循环添加map
                    for (int i = 0; i < eforcesOrders.size();i++){
                        EforcesOrder orderValue = eforcesOrders.get(i);
                        map.put(orderValue.getNumber(),orderValue);
                    }
                }
				/*
				  * 拿出一张订单，查询下一跳的位置。
                 **/
                EforcesBaggingScan  bagScannInfo = bagScanList.get(0);
                if(bagScannInfo != null) {
                    String billsNumber = bagScannInfo.getNumberlist();
                    if(StringUtils.isNotEmpty(billsNumber)) {
                        //此处不在去数据查 直接根据订单号从map里取
                        //List<EforcesOrder> orderList =  orderService.getByNumber(billsNumber);
                        EforcesOrder orderMap = map.get(billsNumber);
                        if(orderMap == null || orderMap.getNumber() == null  || "".equals(orderMap.getNumber())) {
                            return ResultUtil.exec(false,"订单号错误，请确定！",null);
                        }
                        if(sentScan != null ) {
                        	sentScan.setBillsnumber(billsNumber);
                        	sentScan.setIsBagBill(1);
                        	sentScan.setIncid(user.getIncid());
                        	sentScan.setIncname(incment.getName());
                        	sentScan.setScannerid(user.getNumber());
                        	sentScan.setScanners(user.getName());
                        	sentScan.setGoodsCount(1);
                            sentScanList.add(sentScan);
                        }
						/** 其余的地址要发的 用户，订单信息，nextStop下一跳站点 ， 当前站点， 都一样
						 * 需要批量的查询订单。（对订单进行封装）
                        **/
                        for(int i = 1 ; i < bagScanList.size() ; i++) {
                            EforcesBaggingScan  bagScanInfo = bagScanList.get(i);
                            String billNumber = bagScanInfo.getNumberlist();
                            //此处不在去数据查 直接根据订单号从map里取
                            EforcesOrder orderMap1 = map.get(billNumber);
                            if(orderMap1 == null || "".equals(orderMap1.getNumber())) {
                                continue;
                            }
                            //订单
                            EforcesSentScan sentScanTmp = sentScan.clone();
                            sentScanTmp.setBillsnumber(bagScanInfo.getNumberlist());
                            if(sentScanTmp != null ) {
                                sentScanList.add(sentScanTmp);
                            }
                        }
                        if(sentScanList.size() > 0 ) {
                        	List<EforcesLogisticStracking> strackingList = new ArrayList<EforcesLogisticStracking>();
                        	for(int i = 0 ; i < sentScanList.size()  ; i++) {
                        		EforcesSentScan sentScanItem = sentScanList.get(i);
                        		String description = "快件在【%s】由【%s】扫描发往【%s】";
                                String nextStopName = "";
                                if(StringUtils.equals(sentScanItem.getNextstop(), "")) {
                                    nextStopName  = sentScanItem.getNextstopname();
                                }
                                description = String.format(description, incment.getName(), user.getName(), nextStopName);
                        		EforcesLogisticStracking logisticStracking = 
                            			getLogisticstracking(sentScanItem.getBillsnumber(),description,user.getName(), incment.getName(), user.getIncnumber() ,3);
                        		if(logisticStracking != null ) {
                        			strackingList.add(logisticStracking);
                        		}
                        	}
                            // 批量的添加。
                            sentScanService.listinsert(sentScanList,strackingList);
                        }
                    }
                }
            }
            // 调用上面的方法，对订单进行发送， 并添加物流信息。
            return ResultUtil.exec(true, "发包操作成功！", null);
        }catch(Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "发包操作失败！", null);
        }
    
	}
	
	/*
	 * 收包操作（）
	 */
	public ResultVo  receiveBag(HttpServletRequest request , String bagNumber,EforcesReceivedScan  receivedScan) {

        Map<String,EforcesOrder> map = new HashMap<String, EforcesOrder>();
        /**
         * * 根据token获得用户信息
         */
        EforcesUser user = (EforcesUser) request.getAttribute("user");
        EforcesIncment incment = (EforcesIncment)request.getAttribute("inc");
        try {
        	if(StringUtils.isEmpty(bagNumber)) {
        		return  ResultUtil.exec(false, "参数错误！", null);
        	}
            // 根据包的编号查询出打包的订单
            List<EforcesBaggingScan> bagScanList =  biggingScanService.getBaggingScanByBagNum(bagNumber);
            if(bagScanList != null && bagScanList.size() > 0 ) {
                List<EforcesReceivedScan>  receiveScanList = new ArrayList<EforcesReceivedScan>();
				/*
                 * 判断根据编号查询出打包的订单的订单编号判断不为空，根据此单号循环查询所有信息
                 * 多个单号，查询到放在map里面key是订单号valu是操磁订单号的信息
				*/
                List<String> stringList = new ArrayList<String>();
                for (int i = 0; i < bagScanList.size(); i++){
                    EforcesBaggingScan bagList = bagScanList.get(i);
                    if(bagList !=null ){
                        stringList.add(bagList.getNumberlist());
                    }
                }
                //判断stringList集合里面是否有值，里面只有订单编号
                if(stringList.size() > 0 ||stringList != null){
                    //多个订单编号查询
                    List<EforcesOrder> eforcesOrders = orderService.getAllNumberMsg(stringList);
                    //循环添加map
                    for (int i = 0; i < eforcesOrders.size();i++){
                        EforcesOrder orderValue = eforcesOrders.get(i);
                        map.put(orderValue.getNumber(),orderValue);
                    }
                }
				/*
				  * 拿出一张订单，查询下一跳的位置。
                 **/
                EforcesBaggingScan  bagScannInfo = bagScanList.get(0);
                if(bagScannInfo != null) {
                    String billsNumber = bagScannInfo.getNumberlist();
                    if(StringUtils.isNotEmpty(billsNumber)) {
                        //此处不在去数据查 直接根据订单号从map里取
                        //List<EforcesOrder> orderList =  orderService.getByNumber(billsNumber);
                        EforcesOrder orderMap = map.get(billsNumber);
                        if(orderMap == null || orderMap.getNumber() == null  || "".equals(orderMap.getNumber())) {
                            return ResultUtil.exec(false,"订单号错误，请确定！",null);
                        }
                        if(receivedScan != null ) {
                        	receivedScan.setBillsnumber(billsNumber);
                        	receivedScan.setIsBagBill(1);
                        	receivedScan.setIncid(user.getIncid());
                        	receivedScan.setIncname(incment.getName());
                        	receivedScan.setScannerid(user.getNumber());
                        	receivedScan.setScanners(user.getName());
                        	receivedScan.setGoodsCount(1);
                        	receiveScanList.add(receivedScan);
                        }
						/** 其余的地址要发的 用户，订单信息，nextStop下一跳站点 ， 当前站点， 都一样
						 * 需要批量的查询订单。（对订单进行封装）
                        **/
                        for(int i = 1 ; i < bagScanList.size() ; i++) {
                            EforcesBaggingScan  bagScanInfo = bagScanList.get(i);
                            String billNumber = bagScanInfo.getNumberlist();
                            //此处不在去数据查 直接根据订单号从map里取
                            EforcesOrder orderMap1 = map.get(billNumber);
                            if(orderMap1 == null || "".equals(orderMap1.getNumber())) {
                                continue;
                            }
                            //订单
                            EforcesReceivedScan sentScanTmp = receivedScan.clone();
                            sentScanTmp.setBillsnumber(bagScanInfo.getNumberlist());
                            if(sentScanTmp != null ) {
                            	receiveScanList.add(sentScanTmp);
                            }
                        }
                        if(receiveScanList.size() > 0 ) {
                        	List<EforcesLogisticStracking> strackingList = new ArrayList<EforcesLogisticStracking>();
                        	for(int i = 0 ; i < receiveScanList.size()  ; i++) {
                        		EforcesReceivedScan sentReceivedItem = receiveScanList.get(i);
                        		String description = "快件到达【%s】，上一站是【%s】,扫描员是【%s】";
                                String lastStopName = "";
                                if(StringUtils.equals(sentReceivedItem.getLaststop(), "")) {
                                	lastStopName  = sentReceivedItem.getLaststop();
                                }
                                description = String.format(description, incment.getName(), lastStopName, user.getName());
                        		EforcesLogisticStracking logisticStracking = 
                            			getLogisticstracking(sentReceivedItem.getBillsnumber(),description,user.getName(), incment.getName(), user.getIncnumber() ,4);
                        		if(logisticStracking != null ) {
                        			strackingList.add(logisticStracking);
                        		}
                        	}
                            // 批量的添加。
                        	receivedscanService.listinsert(receiveScanList,strackingList);
                        }
                    }
                }
            }
            // 调用上面的方法，对订单进行发送， 并添加物流信息。
            return ResultUtil.exec(true, "收包操作成功！", null);
        }catch(Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "收包操作失败！", null);
        }
	}
	
	/*
	 * 打包操作
	 * @param  billsNum 订单编号， 多个订单用英文状态下半角的逗号隔开  .
	 */
	public ResultVo makeBagScan(String token , String billsNum, String  bagName) {
		if(StringUtils.isEmpty(billsNum)) {
			return ResultUtil.exec(false, "订单不能为空", null);
		}
		String userData = redisUtil.get(Config.REDISAPPLOGINPREX + token);
		JSONObject data = JSONObject.fromObject(userData);
		JSONObject userInfo = data.getJSONObject("userInfo");
		EforcesUser eforcesUser =   (EforcesUser) JSONObject.toBean(userInfo, EforcesUser.class);
		JSONObject incInfo = data.getJSONObject("incInfo");
		EforcesIncment eforcesIncment = (EforcesIncment)JSONObject.toBean(incInfo, EforcesIncment.class);
		// 生成包号
		String bagNumber = orderUtil.getOrderNumber(eforcesUser.getIncid());
		// 将订单号和包号插入到打包表中
		String[] billSection = billsNum.split(",");
		if(billSection == null || billSection.length == 0 ) {
			return ResultUtil.exec(false, "订单不能为空", null);
		}
		List<EforcesBaggingScan> bagScanList = new ArrayList<EforcesBaggingScan>(); 
		// 调用发送包表。
		for(int i = 0 ; i < billSection.length ; i++) {
			String billItem = billSection[i];
			if(StringUtils.isNotEmpty(billItem)) {
				EforcesBaggingScan  baggingScan = new EforcesBaggingScan();
				baggingScan.setScantime(new Date());
				baggingScan.setCode(bagNumber);
				baggingScan.setNumberlist(billItem);
				baggingScan.setNum(1);
				baggingScan.setBaggingid(bagNumber);
				baggingScan.setBaggingname(bagName);
				baggingScan.setCreatetime(new Date());
				baggingScan.setCreateid(eforcesUser.getNumber());
				baggingScan.setCreatename(eforcesUser.getName());
				baggingScan.setIncid(eforcesIncment.getNumber());
				baggingScan.setIncname(eforcesIncment.getName());
				baggingScan.setIsDelete(0);
				bagScanList.add(baggingScan);
			}
		}
		
		int rst = 0 ; 
		if(bagScanList.size() > 0 ) {
			rst = biggingScanService.addRecordList(bagScanList);
		}
		if(rst > 0 ) {
			JSONObject  rstData = new JSONObject();
			rstData.put("bagName", bagName);
			rstData.put("bagNumber", bagNumber);
			String barPath = JBarCodeUtil.createBarcode(bagNumber, baseDir, "");
			rstData.put("barPath",barPath);
			return  ResultUtil.exec(true, "订单打包成功", rstData); 
		}
		return  ResultUtil.exec(false, "订单打包失败！", null); 
	}

	/*
	 * 拆包操作
	 */
	public ResultVo removeBagScan(String token, String bagNum) {
		// TODO Auto-generated method stub
		/*
		 * 根据bagNumber 查询对应的发包记录
		 */
		if(StringUtils.isEmpty(bagNum)) {
			return ResultUtil.exec(false, "包的编号不能为空", null);
		}
		try {
			String userData = redisUtil.get(Config.REDISAPPLOGINPREX + token);
			JSONObject data = JSONObject.fromObject(userData);
			JSONObject userInfo = data.getJSONObject("userInfo");
			EforcesUser eforcesUser =   (EforcesUser) JSONObject.toBean(userInfo, EforcesUser.class);
			JSONObject incInfo = data.getJSONObject("incInfo");
			EforcesIncment eforcesIncment = (EforcesIncment)JSONObject.toBean(incInfo, EforcesIncment.class);
			List<EforcesBaggingScan> bagScanList =  biggingScanService.getBaggingScanByBagNum(bagNum);
			/*
			 * 将打包的数据，放到手包中去。
			 * */ 
			if(bagScanList != null && bagScanList.size() > 0 ) {
				List<EforcesRemovingBagScan>  removeBagScanList = new ArrayList<EforcesRemovingBagScan>();
				for(int i = 0 ; i < bagScanList.size() ; i++) {
					EforcesBaggingScan  bagScannInfo = bagScanList.get(i);
					if(bagScannInfo != null) {
						EforcesRemovingBagScan  removeScanInfo = new EforcesRemovingBagScan();
						removeScanInfo.setScantime(new Date());
						removeScanInfo.setCode(removeScanInfo.getCode());
						removeScanInfo.setNumberlist(removeScanInfo.getNumberlist());
						removeScanInfo.setNum(removeScanInfo.getNum());
						removeScanInfo.setBaggingid(removeScanInfo.getBaggingid());
						removeScanInfo.setBaggingname(removeScanInfo.getBaggingname());
						removeScanInfo.setCreateid(eforcesUser.getNumber());
						removeScanInfo.setCreatename(eforcesUser.getName());
						removeScanInfo.setIncid(eforcesUser.getIncid());
						removeScanInfo.setIncname(eforcesIncment.getName());
						removeBagScanList.add(removeScanInfo);
					}
				}
				if(removeBagScanList.size() > 0 ) {
					removeBageService.addRecordList(removeBagScanList);
				}
			}
			return ResultUtil.exec(true, "拆包操作成功！", null);
		}catch(Exception e ) {
			e.printStackTrace();
			return ResultUtil.exec(false, "拆包操作失败！", null);
		}
	}
}
