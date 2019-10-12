package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesWechatUser;

public interface EforcesWechatUserService {
	/**
	 * 删除
	 * @param openid
	 * @return
	 */
	int deleteByPrimaryKey(String openid);
	
	/**
	 * 	添加
	 * @param record
	 * @return
	 */
    int insert(EforcesWechatUser record);
    
    /**
     * 	添加
     * @param record
     * @return
     */
    int insertSelective(EforcesWechatUser record);

    /**
     * 	通过主键获取实体
     * @param openid
     * @return
     */
    EforcesWechatUser selectByPrimaryKey(String openid);
    
    /**
     * 	修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesWechatUser record);
    
    /**
     * 	修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(EforcesWechatUser record);
}
