package org.kuaidi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesUser;

public interface EforcesUserMapper {
/*    int deleteByPrimaryKey(Integer id);

    int insert(EforcesUser record);

    int updateByPrimaryKey(EforcesUser record);*/


    /**
     *User 登录功能
     * @param Number
     * @param
     * @return
     */
    EforcesUser selectUser(@Param("Number")String Number);

    /**
     * 开通小号
     * @param record
     * @return
     */
    int getOpenTrumpet(EforcesUser record);

    /**
     * 查寻
     * @param id
     * @return
     */
    EforcesUser selectByPrimaryKey(Integer id);


    /**
     * 修改用户名、手机号
     * @param id
     * @return
     */
    int updateMsg(int id,String Name,String Mobile);

    int insertSelective(EforcesUser record);
    
    int updateByPrimaryKeySelective(EforcesUser record);

	List<EforcesUser> selectUserByIncId(String incId);

	String selectCurrentMaxNumber(String incNum);

	List<EforcesUser> selectUserByPhone(String phone);

    List<EforcesUser> selectAllUser(EforcesUser record );

    List<EforcesUser> selectByThree(EforcesUser record);
    
    List<HashMap> selectByDepartname(@Param("departname")String departname,
    				@Param("incNumber")String incNumber);

    int deleteByid(List<Integer> array);


}