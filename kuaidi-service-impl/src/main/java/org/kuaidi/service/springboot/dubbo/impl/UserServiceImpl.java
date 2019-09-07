package org.kuaidi.service.springboot.dubbo.impl;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.dao.EforcesUserMapper;
import org.kuaidi.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Autowired
    private EforcesUserMapper UserDao;


	@Override
	public PageInfo<EforcesUser> selectAllUser(Integer page,Integer size) {
		PageHelper.startPage(page,size);
		List<EforcesUser> eforcesUser = UserDao.selectAllUser();
		final  PageInfo<EforcesUser> pageInfo = new PageInfo<>(eforcesUser);
		return pageInfo;
	}

	/**
     *User登录
     * @param Number
     * @param
     * @return
     */
    public EforcesUser selectUser(String Number) {
        return UserDao.selectUser(Number);
    }

	/**
	 * 开通小号
	 * @param record
	 * @return
	 */
	@Override
	public int getOpenTrumpet(EforcesUser record) {
		return UserDao.getOpenTrumpet(record);
	}

	/**
	 * 保存用户信息
	 * @param userInfo
	 * @return
	 */
	public Integer insertUserInfo(EforcesUser userInfo) {
		// TODO Auto-generated method stub
		int rst =  UserDao.insertSelective(userInfo);
		if(rst > 0 ) {
			return userInfo.getId();
		}
		return rst; 
	}

	/**
	 * 查寻
	 * @param userId
	 * @return
	 */
	public EforcesUser selectUserById(Integer userId) {
		// TODO Auto-generated method stub
		return UserDao.selectByPrimaryKey(userId);
	}

	/**
	 * 修改用户名、手机号
	 * @param id 条件
	 * @param Name 用户名
	 * @param Mobile 手机号
	 * @return
	 */
	@Override
	public int updateMsg(String Name, String Mobile,int id) {
		return UserDao.updateMsg(id,Name,Mobile);
	}

	public Integer updateUserInfo(EforcesUser userInfo) {
		// TODO Auto-generated method stub
		return UserDao.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public List<EforcesUser> selectUserByIncId(String incId) {
		// TODO Auto-generated method stub
		return UserDao.selectUserByIncId(incId);
	}

	@Override
	public String getNextNumber(String  incNum) {
		// TODO Auto-generated method stub
		String currentNum = UserDao.selectCurrentMaxNumber(incNum);;
		return currentNum;
	}

	@Override
	public List<EforcesUser> selectUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return UserDao.selectUserByPhone(phone);
	}

	@Override
	public List<HashMap> getByDepartName(String departName) {
		return UserDao.selectByDepartname(departName);
	}

	@Override
	public int addUser(EforcesUser record) {
		return UserDao.insertSelective(record);
	}

	@Override
	public int deleteByid(List<Integer> array) {
		return UserDao.deleteByid(array);
	}
}
