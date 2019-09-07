package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesUserPoint;

import java.util.List;

public interface EforcesUserPointMapper {
	/**
	 * 保存签到积分
	 * @param userPoint
	 * @return
	 */
	int addUserPoint(EforcesUserPoint userPoint);

	/**
	 * 获取某用户的所有签到信息
	 * @param userid
	 * @return
	 */
	List<EforcesUserPoint> getCountMsg(Integer userid);

	/**
	 * 查询积分列表
	 * @param userid
	 * @return
	 */
	List<EforcesUserPoint> getCountDate(Integer userid);
	
	/*
	 * 查询用户可用积分
	 */
	Integer getPointsByUserId(Integer userid);
	
}
