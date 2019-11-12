package org.kuaidi.web.springboot.dubboservice;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.*;
import org.kuaidi.bean.vo.*;
import org.kuaidi.iservice.*;
import org.kuaidi.web.springboot.util.redis.OrderUtil;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SentScanService {
    @Reference(version = "1.0.0")
    private UserService userService;


    @Reference(version = "1.0.0")
    private IEforcesOrderService orderService;

    @Reference(version = "1.0.0")
    private IEforcesSentscanService  sentScanService;

    @Reference(version = "1.0.0")
    private IEforceslogisticstrackingService logisticstrackingService;

    @Reference(version = "1.0.0")
    private IEforcesIncmentService incmentService ;

    @Reference(version = "1.0.0")
    private IEforcesReceivedscanService receivedscanService;

    @Autowired
    private OrderUtil orderUtil ;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${file.baseDir}")
    private String baseDir;

    @Reference(version = "1.0.0")
    private IEforcesBiggingScanService  biggingScanService;

    @Reference(version= "1.0.0")
    private IEforcesRemovingBagScanService  removeBageService;

    public PageVo<EforcesSentScan> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesSentScan> eforcesUsers = sentScanService.getAll(page.getPage(),page.getLimit(),null);
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
    public ResultVo deleteMenusByID(List<Integer> list) {
        try {
            int i = sentScanService.deleteByIds(list);
            if(i>0){
                return ResultUtil.exec(true, "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败！", null);
        }
        return ResultUtil.exec(false, "删除失败！", null);
    }

    public ResultVo sentOrder( EforcesSentScan record,EforcesUser user, EforcesIncment incment){
        int i = 0;
        try {
            String[] split = {};
            if(StringUtils.isNotEmpty(record.getBillsnumber())){
                split = record.getBillsnumber().split("\\s+");
            }else {
                return ResultUtil.exec(false, "运单号不能为空", null);
            }
            Set<String> set = new HashSet<String>();
            for(String str:split){
                if(StringUtils.isNotEmpty(str)){
                    set.add(str);
                }
            }
            for( String str:set) {
                String billNumber=str;
                List<EforcesOrder> orderList = orderService.getByNumber(billNumber);
                if(orderList.isEmpty()){
                    return ResultUtil.exec(false, "请输入正确的订单号", null);
                }
                EforcesOrder orderInfo = orderList.get(0);
                List<EforcesSentScan> sentScanList = sentScanService.getSentScanByNumber(billNumber, user.getIncnumber());
                if (sentScanList != null && sentScanList.size() > 0) {
                    EforcesSentScan sentScan = sentScanList.get(0);
                    if (orderInfo.getNum() <= 1 || orderInfo.getNum() == sentScan.getGoodsCount()) {
                        return ResultUtil.exec(false, "订单已经发送出去，请确定！", null);
                    } else if (sentScan != null && orderInfo.getNum() > sentScan.getGoodsCount()) {
                             // 更新数量， 然后返回保存成功！
                            sentScan.setGoodsCount(sentScan.getGoodsCount() + 1);
                            Integer rst = sentScanService.updateSentScan(sentScan);
                            if (rst > 0) {
                            return ResultUtil.exec(true, "发件成功！", null);
                        }
                    }
                }
//                EforcesIncment nextStop =  null;
//                if(record.getNextstop() != null && record.getNextstop().matches("\\d+")) {
//                	nextStop = incmentService.selectByNumber(record.getNextstop());//incmentService.getByNextStopName(record.getNextstopname());
//                }
//                
//                EforcesIncment nextStop1 = null;
//                if(record.getNextstop1() != null && record.getNextstop1().matches("\\d+")) {
//                	nextStop = incmentService.selectByNumber(record.getNextstop1());//incmentService.getByNextStopName(record.getNextstopname1());
//                }
                if(StringUtils.equals(record.getNextstop(), "") ||StringUtils.equals(record.getNextstop1(), "")){
                    return  ResultUtil.exec(false,"发件扫描失败！请输入正确的目的地",null);
                }
                EforcesSentScan sentScan = createSentScanInfo(record,user, orderInfo, incment, 0);
            /*
             * 封装物流信息
             */
            String description = "快件在【%s】由【%s】扫描发往【%s】";
            String nextStopName = "";
            if(StringUtils.equals(record.getNextstop(), "")) {
                nextStopName  = record.getNextstopname();
            }
            description = String.format(description, incment.getName(), user.getName(), nextStopName);
            EforcesLogisticStracking stracking =  getLogisticstracking(billNumber,description,user.getName(), incment.getName(), user.getIncnumber() ,3);
            DubboMsgVO msgVo = sentScanService.addSentScan(sentScan, stracking);
            if(msgVo  != null && msgVo.isRstFlage()) {
                i++;
            }
            }
            if(i==split.length){
                return ResultUtil.exec(true,"发件扫描成功！",null);
            }else {
                return ResultUtil.exec(false, "发件扫描失败！", null);
            }
            }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"发单扫描异常！",null);
        }
    }
    
    private EforcesSentScan createSentScanInfo(EforcesSentScan record, EforcesUser userInfo, EforcesOrder orderInfo, 
                                               EforcesIncment currentStop, Integer isBagBill) {
        EforcesSentScan sentScan = record;
        sentScan.setScantype("发件扫描");
        sentScan.setScanners(userInfo.getName());
        sentScan.setScannerid(userInfo.getNumber());
        sentScan.setIncname(currentStop.getName());
        sentScan.setIncid(userInfo.getIncnumber());
        sentScan.setAmount(0);
        sentScan.setIsBagBill(isBagBill);
        return sentScan;
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
}
