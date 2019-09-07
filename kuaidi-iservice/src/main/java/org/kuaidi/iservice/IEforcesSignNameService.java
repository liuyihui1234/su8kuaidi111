package org.kuaidi.iservice;


import org.kuaidi.bean.domain.EforcesSignName;

import java.util.List;

public interface IEforcesSignNameService {
	//EforcesSignName getByCrtTimeAndUserId(Date date,Integer userId);
	
	void  newSignName(EforcesSignName signName)throws RuntimeException;

	/**
	 * 签到添加
	 * @param signName
	 * @return
	 */
	Integer addSignName(EforcesSignName signName);

	/**
	 * 获取签到信息
	 */
	List<EforcesSignName> getSignMsg(String crtTime,Integer userid);

	/**
	 * 获取累计签到天数
	 * @param userid
	 * @return
	 */
	int getCount(Integer userid);

	/**
	 * 修改签到累计 0 或 1
	 * @param signName
	 * @return
	 */
	int update(EforcesSignName signName);
}
