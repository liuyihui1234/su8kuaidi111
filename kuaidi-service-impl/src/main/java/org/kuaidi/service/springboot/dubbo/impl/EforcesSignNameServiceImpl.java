package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

import org.kuaidi.bean.domain.EforcesSignName;
import org.kuaidi.dao.EforcesSignNameMapper;
import org.kuaidi.iservice.IEforcesSignNameService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class EforcesSignNameServiceImpl implements IEforcesSignNameService {

    @Autowired
    EforcesSignNameMapper signNameMapper;

/*	*//**
	 * 签到
	 * @param signName
	 * @throws RuntimeException
	 *//*
	@Override
	public void newSignName(EforcesSignName signName)throws  RuntimeException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		EforcesSignName result=signNameMapper.selectByCrtTimeAndUserId(sdf.format(date), signName.getUserid());
		if(result==null) {
			// 查询累计打卡的天数。			
			// 如果天数，大于10 的时候。判断最近十天累计
			signName.setCrttime(date);
			signNameMapper.addSignName(signName);	
			EforcesUserPoint userPoint=new EforcesUserPoint();
			userPoint.setType(1);
			userPoint.setCrtTime(new Date());
			userPoint.setIncNumber(signName.getIncnumber());
			userPoint.setPointNum(1);
			userPoint.setUserId(signName.getUserid());
			userPointMapper.addUserPoint(userPoint);			
			return;
		}
		throw new RuntimeException();
	}*/

	@Override
	public void newSignName(EforcesSignName signName) throws RuntimeException {

	}

	/**
	 * 签到后添加
	 * @param signName
	 * @return
	 */
	@Override
	public Integer addSignName(EforcesSignName signName) {
		signNameMapper.insertSelective(signName);
		return signName.getId();
	}

	/**
	 * 获取签到信息
	 * @param crtTime
	 * @param userid
	 * @return
	 */
	public List<EforcesSignName> getSignMsg(String crtTime,Integer userid){
		return signNameMapper.getSignMsg(crtTime,userid);
	}

	/**
	 * 获取累计签到天数
	 * @param userid
	 * @return
	 */
	@Override
	public int getCount(Integer userid) {
		return signNameMapper.getCount(userid);
	}

	/**
	 * 修改累计签到 0 或 1
	 * @param signName
	 * @return
	 */
	@Override
	public int update(EforcesSignName signName) {
		return signNameMapper.updateByPrimaryKey(signName);
	}
}
