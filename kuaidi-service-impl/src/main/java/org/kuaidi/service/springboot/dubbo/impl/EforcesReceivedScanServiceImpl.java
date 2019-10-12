package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.*;
import org.kuaidi.bean.vo.DubboMsgVO;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.dao.EforcesIncmentMapper;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.dao.EforcesOrderMapper;
import org.kuaidi.dao.EforcesReceivedScanMapper;
import org.kuaidi.iservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service(version = "1.0.0",interfaceClass=IEforcesReceivedscanService.class)
public class EforcesReceivedScanServiceImpl implements IEforcesReceivedscanService {
    
    @Autowired
	private EforcesOrderMapper  orderService; 
    
    @Autowired
    private EforcesIncmentMapper incmentService; 

    @Autowired
    EforcesReceivedScanMapper receivedscanMapper;

    @Autowired
    private EforcesLogisticStrackingMapper  logisticStrackingMapper;

    /**
     * 查询派收件总数量
     * @param incid
     * @return
     */
    @Override
    public int getReceicedscanCount(String incid) {
        return receivedscanMapper.selectCount(incid);
    }

    /**
     * 查询网点下所有订单信息
     * @param incid
     * @return
     */
    @Override
    public List<EforcesReceivedScan> getAllOrder(String incid) {
        return receivedscanMapper.getAllOrder(incid);
    }

    /**
     * 收件
     * @param record
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DubboMsgVO  insertSelective(EforcesReceivedScan record,
    		EforcesLogisticStracking stracking) {
    	DubboMsgVO  msgVo = new DubboMsgVO();
    	try {
        	int rst = receivedscanMapper.insertSelective(record);
            if(rst == 1 ) {
            	rst = logisticStrackingMapper.insertSelective(stracking);
        	}
            if(rst == 1 ) {
            	msgVo.setMsg("添加收件扫描成功");
            	msgVo.setRstFlage(true);
            }else {
            	msgVo.setMsg("添加收件扫描失败");
            	msgVo.setRstFlage(false);
            }
        	return msgVo;
        }catch(Exception e) {
        	e.printStackTrace();
        }
    	return msgVo;
    }

    @Override
    public PageInfo<EforcesReceivedScan> getAllOrderSelective(Integer pageNum, Integer pageSize, Integer incid) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesReceivedScan> list = receivedscanMapper.getAllOrderSelective(incid);
        final PageInfo<EforcesReceivedScan> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(EforcesReceivedScan receivedScan) {
        return receivedscanMapper.updateByPrimaryKeySelective(receivedScan);
    }

    @Override
    public EforcesReceivedScan selectByPrimaryKey(Integer id) {
        return receivedscanMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 寄/派件运单管理
     * @param number
     * @return
     */
    @Override
    public List<HashMap> getreceiveOrder(String number) {
        List<HashMap> listMap = receivedscanMapper.getreceiveOrder(number);
        return listMap;
    }

    /**
     * 将收件信息插入数据库
     * @param list
     * @return
     */
    @Override
    public int insertList(List<EforcesReceivedScan> list) {
        return receivedscanMapper.insertList(list);
    }

    /**
     * 收件 根据订单号查询一条订单信息，并存入扫描表内
     * @return
     */
    public ResultVo receiveOrder( EforcesReceivedScan scan, EforcesUser userInfo, EforcesIncment currentStop){
        int i=0;
        try {
            String[] split = {};
            if(StringUtils.isNotEmpty(scan.getBillsnumber())){
                split = scan.getBillsnumber().split("\\s+");
            }else {
                return ResultUtil.exec(false, "运单号不能为空", null);
            }
            Set<String> set = new HashSet<String>();
            for(String str:split){
                if(StringUtils.isNotEmpty(str)){
                    set.add(str);
                }
            }
            EforcesIncment preStop = incmentService.selectNextSyopByName(scan.getLaststopname());
            System.err.println(preStop);
            if(preStop==null){
                return  ResultUtil.exec(false,"到件扫描失败！请输入正确的目的地",null);
            }
            
            /*
                                     * 判断订单是否存在无效的记录
             **/
            List<String> billNumList = new ArrayList<String>();
            for(String billNumber:set) {
            	billNumList.add(billNumber);
            }
            List<EforcesOrder> billList = orderService.getOrderByNumbers(billNumList);
            Map<String , EforcesOrder> billMap = new HashMap<String , EforcesOrder>();
            for(EforcesOrder  billInfo : billList) {
            	billMap.put(billInfo.getNumber(), billInfo);
            }
            boolean flage = false ; 
            for(String billNumber:set) {
            	if(!billMap.containsKey(billNumber)) {
            		flage = true ;
            		break;
            	}
            }
            if(flage) {
            	return ResultUtil.exec(false, "订单中有不正确的订单号，请确定！", null);
            }
            
            /*
             * 判断订单号中是否有已经接收过的订单号
             */

            List<EforcesReceivedScan> receiveList = receivedscanMapper.getHadReceiveOrder(userInfo.getIncid() , billNumList);
            if(receiveList != null && receiveList.size() > 0 ) {
            	return ResultUtil.exec(false, "订单列表中有已经接收过的订单信息！", null);
            }
            
            for(String billNumber:set) {
                List<EforcesOrder> orderList = orderService.selectLikeByNumber(billNumber);
                if (orderList == null || orderList.size() == 0) {
                    return ResultUtil.exec(false, "订单号错误，请确定！", null);
                }
                if (userInfo == null) {
                    return ResultUtil.exec(false, "用户信息错误，请确定！", null);
                }
                // 判断邮件发送地方是否错误。
                EforcesOrder result = orderList.get(0);                
                EforcesReceivedScan parameter = createReceiveScan(scan,userInfo, preStop, currentStop, result, 0);
                System.err.println("currentStop:"+currentStop);
                String description = "快件到达【%s】，上一站是【%s】,扫描员是【%s】";
                description = String.format(description, currentStop.getName(), "", userInfo.getName());
                EforcesLogisticStracking stracking = getLogisticstracking(billNumber, description, userInfo.getName(), currentStop.getName(), currentStop.getNumber(), 4);
                DubboMsgVO msgVo = insertSelective(parameter, stracking);
                if(msgVo != null && msgVo.isRstFlage() ) {
                    i++;
                }
            }
            if(i==split.length){
                return ResultUtil.exec(true,"到件扫描成功！",null);
            }else {
                return ResultUtil.exec(false, "到件扫描失败！", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"到件扫描失败",null);
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

    private EforcesReceivedScan createReceiveScan( EforcesReceivedScan parameter,EforcesUser userInfo,EforcesIncment preIncment, EforcesIncment currentStop,
                                                  EforcesOrder result , Integer isBagBill) {
        EforcesReceivedScan scan = new EforcesReceivedScan();
        scan.setBillsnumber(result.getNumber());
        scan.setGoodstype(parameter.getGoodstype());
        scan.setExpresstype(parameter.getExpresstype());
        scan.setExpressid(1);
        // lastStop 需要查找一下。
        scan.setFlightsnumber(parameter.getFlightsnumber());
        scan.setScanners(userInfo.getName());
        scan.setScannerid(userInfo.getNumber());
        scan.setLaststop(preIncment.getNumber());
        scan.setLaststopname(parameter.getLaststopname());
        scan.setIncid(userInfo.getIncnumber());
        scan.setIncname(currentStop.getName());
        scan.setAmount(0);
        scan.setBz(parameter.getBz());
        scan.setCreatetime(new Date());
        scan.setScantime(new Date());
        scan.setTranname(parameter.getTranname());
        scan.setIsBagBill(isBagBill);
        return scan;
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

	@Override
	public int deleteById(List<Integer> ids) {
		// TODO Auto-generated method stub
		return receivedscanMapper.updateById(ids);
	}
}
