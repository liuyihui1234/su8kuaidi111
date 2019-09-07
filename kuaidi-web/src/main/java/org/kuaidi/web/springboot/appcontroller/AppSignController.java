package org.kuaidi.web.springboot.appcontroller;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesSignName;
import org.kuaidi.bean.domain.EforcesUserPoint;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesSignNameService;
import org.kuaidi.iservice.IEforcesUserPointService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/sign/")
public class AppSignController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AppSignController.class);
	
	@Reference(version = "1.0.0")
	IEforcesSignNameService  signService;

	@Reference(version = "1.0.0")
	IEforcesUserPointService userPointService;
	
	@RequestMapping("newSignName")
	public ResultVo newSignName(EforcesSignName signName) {
		try {
			signService.newSignName(signName);
			return ResultUtil.exec(true, "签到成功", null);
		} catch (Exception e) {
			return ResultUtil.exec(false, "你今天已经签到过一次了", null);
		}
	}

	/**
	 * 签到
	 * @param signName
	 * @return
	 */
	@RequestMapping("addSginMsg")
	@ResponseBody
	public ResultVo addSginMsg(EforcesSignName signName){
        //获取当前日期
        Date creatTime = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String verif = dft.format(creatTime);
        List<EforcesSignName> listSize= signService.getSignMsg(verif,signName.getUserid());
        if(listSize == null || listSize.size() == 0 ){
           EforcesUserPoint userPoint = new EforcesUserPoint();
           int result;
           signName.setCrttime(creatTime);
           signName.setReceivepointflage(0);
           Integer rst = signService.addSignName(signName);
           int userid = signName.getUserid();
           int signId = 0 ;
           if(rst  != null && rst > 0 ){
               signId = rst;
           }
           //调用工具类获取当前日期的前六天的日期
           String crtTime = TimeDayUtil.getDateTime(1 - Config.signDays );
           //查询当前插入的用户信息 条件userid
           List<EforcesSignName> listMsg = signService.getSignMsg(crtTime,userid);
           int getPoint = 1 ; 
           //判断当前用户是否有七条签到记录
           if(listMsg.size() < Config.signDays){
               //往积分表中插入一条该用户获得一个积分的记录
               userPoint.setType(1);
               userPoint.setPointnum(1);
               userPoint.setCrttime(creatTime);
               userPoint.setIncnumber(signName.getIncnumber());
               userPoint.setUserid(signName.getUserid());
               result = userPointService.addUserPoint(userPoint);
               logger.info("查询结果小于10添加的返回结果:"+result);
           }else{
        	   boolean  tenRecordFlage = false ;  
               for (int i = 0; i < listMsg.size(); i++){
                   //往积分表中插入一条该用户获得一个积分的记录
                   if(listMsg.get(i).getReceivepointflage() == 1){
                	   tenRecordFlage = true ; 
                       break;
                   }
               }
               if(tenRecordFlage) {
                 userPoint.setType(1);
                 userPoint.setPointnum(1);
                 userPoint.setCrttime(creatTime);
                 userPoint.setUserid(signName.getUserid());
                 userPoint.setIncnumber(signName.getIncnumber());
                 result = userPointService.addUserPoint(userPoint);
                 logger.info("添加十个积分返回结果:"+result);
               }else {
            	   getPoint = Config.signPoint ; 
            	   userPoint.setType(2);
                   userPoint.setPointnum(getPoint);
                   userPoint.setCrttime(creatTime);
                   userPoint.setUserid(signName.getUserid());
                   userPoint.setIncnumber(signName.getIncnumber());
                   result = userPointService.addUserPoint(userPoint);
                   logger.info("添加一个积分返回结果:"+result);
                   if(signId > 0){
                	   signName.setId(signId);
                	   signName.setReceivepointflage(1);
                       signService.update(signName);
                   }
               }
           }
           JSONObject data = new JSONObject(); 
           data.put("receivePoint", getPoint);
           data.put("days", Config.signDays);
           data.put("points", Config.signPoint);
           // 如果不是满分的时候
           if(getPoint < Config.signPoint) {
        	   if( listMsg != null && listMsg.size() > 0 ) {
        		   Date lastTime = null; 
        		   int signDays = 0 ; 
        		   for(int i = 0 ; i < listMsg.size() ; i++) {
        			   EforcesSignName  signNameItem = listMsg.get(i);
        			   if(signNameItem != null && signNameItem.getCrttime() != null  &&  
       					   	signNameItem.getReceivepointflage() == 1){
        				   lastTime = null;
        				   signDays = 0 ; 
        			   }else if(signNameItem != null && signNameItem.getCrttime() != null  &&  
        					   	signNameItem.getReceivepointflage() == 0   && lastTime == null ) {
        				   lastTime = signNameItem.getCrttime();
        				   signDays = 1 ; 
        			   }else if(signNameItem != null && signNameItem.getCrttime() != null 
        					   && signNameItem.getReceivepointflage() == 0   && lastTime != null){
        				   if(signNameItem.getCrttime().getTime() - lastTime.getTime() > 24*60*60*1000  ) {
        					   lastTime = signNameItem.getCrttime();
            				   signDays = 1 ; 
        				   }else {
        					   lastTime = signNameItem.getCrttime();
        					   signDays ++; 
        				   }
        			   }
        		   }
        		   data.put("signDays", signDays);
        	   }
           }else {
        	   data.put("signDays", Config.signDays);
           }
           return ResultUtil.exec(true,"签到成功",data);
       }else{
           return ResultUtil.exec(false,"今天已经签过到了",null);
       }
	}
	
	/**
	 * 获取累计签到天数！
	 * @param userid
	 * @return
	 */
	
	@RequestMapping("countSign")
	@ResponseBody
	@Authorization
	public ResultVo getCount(Integer userid){
		try {
			Integer result = signService.getCount(userid);
			return ResultUtil.exec(true,"查询数据成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"查询失败",null);
		}
	}

    /**
     * 获取某用户的所有签到信息
     * @param userid
     * @return
     */
    @RequestMapping("showCountMsg")
    @ResponseBody
	public ResultVo getCountMsg(Integer userid){
	    try {
	        List<EforcesUserPoint> result = userPointService.getCountMsg(userid);
	        return ResultUtil.exec(true,"查询信息成功",result);
        }catch (Exception e){
	        e.printStackTrace();
	        return ResultUtil.exec(false,"查询信息失败",null);
        }
    }

	/**
	 * 查询积分列表
	 * @param userid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("CountDate")
	@ResponseBody
    public PageVo getCountDate(Integer userid, Integer pageNum, Integer pageSize){
    	try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesUserPoint> resultDate =  userPointService.getCountDate(userid,pageNum,pageSize);
			List<EforcesUserPoint>  pointList = resultDate.getList();
			if(pointList != null && pointList.size() > 0 ) {
				for(int i = 0 ; i < pointList.size() ; i++) {
					EforcesUserPoint  pointInfo = pointList.get(i);
					String typeRemark = "";
					if(pointInfo.getType() == 1 ) {
						typeRemark = "签到得积分";
					}else if(pointInfo.getType() == 2) {
						typeRemark = "连续七天签到得积分";
					}
					pointInfo.setTypeRemark(typeRemark);
				}
				return ResultUtil.exec(pageNum,pageSize,resultDate.getTotal(),pointList);
			}else {
				return ResultUtil.exec(pageNum,pageSize,0,"查询到的记录条数为空！",null);
			}
		}catch (Exception e){
    		e.printStackTrace();
    		return ResultUtil.exec(pageNum,pageSize,0,null);
		}
	}
	
	@Authorization
	@RequestMapping("getTotalPoints")
	@ResponseBody
    public ResultVo getTotalPoints(Integer userid){
    	try {
    		Integer sunPoint = userPointService.getPointsByUserId(userid);
    		JSONObject data = new JSONObject(); 
    		data.put("sunPoint", sunPoint);
    		return ResultUtil.exec(true,"获取用户总积分成功！",data);
    	}catch (Exception e){
    		e.printStackTrace();
    		return ResultUtil.exec(false,"查询异常！",null);
		}
	}
	
	
}
