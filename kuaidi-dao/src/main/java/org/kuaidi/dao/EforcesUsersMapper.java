package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesUsers;

public interface EforcesUsersMapper {
/*    int deleteByPrimaryKey(Integer uid);

    int insert(EforcesUsers record);

    int insertSelective(EforcesUsers record);

    EforcesUsers selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(EforcesUsers record);

    int updateByPrimaryKeyWithBLOBs(EforcesUsers record);

    int updateByPrimaryKey(EforcesUsers record);*/

    /**
     * Users注册
     * @param Username
     * @param password
     */
    public void  inserUsers(String Username,String password);
    
    /**
     * Users登录
     * @param password
     * @Param("username") String username,@Param("password")String password
     * @return
     */
    public  EforcesUsers selectUsers(@Param("username") String username,@Param("password")String password);
}