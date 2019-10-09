package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesWechatUser;

public interface EforcesWechatUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(EforcesWechatUser record);

    int insertSelective(EforcesWechatUser record);

    EforcesWechatUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(EforcesWechatUser record);

    int updateByPrimaryKey(EforcesWechatUser record);
}