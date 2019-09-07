package org.kuaidi.dao;


import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesSignName;

import java.util.List;

public interface EforcesSignNameMapper {
	
	/**
	 * 获取签到信息
	 * @param crtTime 在那边转换成String了没转换的话选用日期类型
	 * @param userid
	 * @return
	 */
	List<EforcesSignName> getSignMsg(@Param("crtTime")String crtTime, @Param("userid")Integer userid);

	/**
	 * 签到添加
	 * @param signName
	 * @return
	 */
	int addSignName(EforcesSignName signName);

	/**
	 * 获取累计签到天数
	 * @param userid
	 * @return
	 */
	int getCount(Integer userid);

	int deleteByPrimaryKey(Integer id);

	int insert(EforcesSignName record);

	int insertSelective(EforcesSignName record);

	EforcesSignName selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EforcesSignName record);

	int updateByPrimaryKey(EforcesSignName record);

}