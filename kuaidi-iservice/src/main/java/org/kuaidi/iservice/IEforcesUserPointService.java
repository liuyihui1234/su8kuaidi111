package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesUserPoint;

import java.util.List;

public interface IEforcesUserPointService {
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
    PageInfo<EforcesUserPoint> getCountDate(Integer userid,Integer PageNum,Integer PageSize);
    /*
     * 根据用户的id查询用户的总积分，
     * @param userId  用户id
     */
    Integer getPointsByUserId (Integer userId);
}
