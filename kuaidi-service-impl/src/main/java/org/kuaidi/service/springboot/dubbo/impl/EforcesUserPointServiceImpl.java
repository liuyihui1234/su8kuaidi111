package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesUserPoint;
import org.kuaidi.dao.EforcesUserPointMapper;
import org.kuaidi.iservice.IEforcesUserPointService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesUserPointServiceImpl implements IEforcesUserPointService {

    @Autowired
    EforcesUserPointMapper userPointMapper;

    /**
     * 保存签到积分
     * @param userPoint
     * @return
     */
    @Override
    public int addUserPoint(EforcesUserPoint userPoint) {
        return userPointMapper.addUserPoint(userPoint);
    }

    @Override
    public List<EforcesUserPoint> getCountMsg(Integer userid) {
        return userPointMapper.getCountMsg(userid);
    }

    /**
     * 查询积分列表
     * @param userid
     * @param PageNum
     * @param PageSize
     * @return
     */
    @Override
    public PageInfo<EforcesUserPoint> getCountDate(Integer userid,Integer PageNum,Integer PageSize) {
        PageHelper.startPage(PageNum,PageSize);
        List<EforcesUserPoint> list = userPointMapper.getCountDate(userid);
        final PageInfo<EforcesUserPoint> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

	@Override
	public Integer getPointsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userPointMapper.getPointsByUserId(userId);
	}
}
