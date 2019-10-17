package org.kuaidi.iservice;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.domain.EforcesUser;

import com.github.pagehelper.PageInfo;

public interface UserService {



    PageInfo<EforcesUser> selectAllUser(Integer page,Integer size,EforcesUser record);

    /**
     *User登录
     * @param Number
     * @param
     * @return
     */
    EforcesUser selectUser(String Number);

    /**
     * 开通小号
     * @param record
     * @return
     */
    int getOpenTrumpet(EforcesUser record);

    /**
     * 查寻
     * @param userId
     * @return
     */
    EforcesUser selectUserById(Integer userId);


    /**
     * 修改用户名、手机号
     * @param id 条件
     * @param Name 用户名
     * @param Mobile 手机号
     * @return
     */
    int updateMsg(String Name,String Mobile,int id);

    /**
     * 开通小号
     * @param
     * @return
     */
    Integer insertUserInfo(EforcesUser userInfo);

    /**
     * 修改用户
     * @param userInfo
     * @return
     */
    Integer updateUserInfo(EforcesUser userInfo);
    
    List<EforcesUser> selectUserByIncId(String incId);
    
    String getNextNumber(String  incNum);
    
    List<EforcesUser>  selectUserByPhone(String phone);

    List<EforcesUser> getByThree(EforcesUser record);
    
    
    List<HashMap> getByDepartName(String departName, String incNumber);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int addUser(EforcesUser record);

    /**
     * 根据id虚拟删除用户
     * @return
     */

    int deleteByid(List<Integer> array);


}
