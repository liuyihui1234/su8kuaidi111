package org.kuaidi.service.springboot.dubbo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.domain.EforcesUsersgrouprele;
import org.kuaidi.dao.EforcesUserMapper;
import org.kuaidi.dao.EforcesUsersgroupMapper;
import org.kuaidi.dao.EforcesUsersgroupreleMapper;
import org.kuaidi.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0",interfaceClass= UserService.class,timeout=12000)
public class UserServiceImpl implements UserService {
	
    @Autowired
    private EforcesUserMapper UserDao;
    
    @Autowired
    EforcesUsersgroupreleMapper eforcesUsersgroupDao;

    @Autowired
    EforcesUsersgroupMapper userGroupDao;

	@Override
	public PageInfo<EforcesUser> selectAllUser(Integer page,Integer size,EforcesUser record) {
		PageHelper.startPage(page,size);
		List<EforcesUser> eforcesUser = UserDao.selectAllUser( record);
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
	public List<EforcesUser> getByThree(EforcesUser record) {
		return UserDao.selectByThree(record);
	}

	@Override
	public List<HashMap> getByDepartName(String departName,String incNumber) {
		return UserDao.selectByDepartname(departName, incNumber);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addUser(EforcesUser user) {
		Integer rst = UserDao.insertSelective(user);
		List<EforcesUsersgrouprele> list = new ArrayList<EforcesUsersgrouprele>();
		if (StringUtils.isNotEmpty(user.getGroupid())) {
            String[] split = user.getGroupid().split(",");
            for (int i = 0; i < split.length; i++) {
                EforcesUsersgrouprele role = new EforcesUsersgrouprele();
                role.setUsername(user.getNumber());
                role.setGroupid(Integer.parseInt(split[i]));
                role.setUserid(user.getId());
                list.add(role);
            }
        }
        int k = eforcesUsersgroupDao.insertForeach(list);
		return rst;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteByid(List<Integer> array) {
		//批量删除用户分组信息。
		eforcesUsersgroupDao.deleteByUserIdList(array);
		return UserDao.deleteByid(array);
	}
	
	/*
	 *根据等级对用户进行修改权限
	 *和分配部门
	 *管理人员还没有设置。
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateUserBySign(EforcesUser userInfo, Integer level) {
		// TODO Auto-generated method stub
		/*
		 * 给用户分配部门
		 * 16	总经理室
		 * 生成小号的时候，
		 * */ 
		userInfo.setDepartid(16);
		userInfo.setDepartname("总经理室");
		Integer rst = UserDao.updateByPrimaryKeySelective(userInfo);
		/*
		 * 设置用户权限
		 */
		List<EforcesUsersgroup> groupList = userGroupDao.selectAllIGroup();
		Map<Integer, EforcesUsersgroup>  groupMap = new HashMap<Integer, EforcesUsersgroup>();
		if(groupList != null && groupList.size() > 0){
			for(int i = 0 ; i < groupList.size(); i++) {
				EforcesUsersgroup  groupItem = groupList.get(i);
				if(groupItem != null && groupItem.getGroupname() != null ) {
					if(groupItem.getGroupname().indexOf("A") > -1) {
						groupMap.put(1, groupItem);
					}else if(groupItem.getGroupname().indexOf("B") > -1) {
						groupMap.put(2, groupItem);
					}else if(groupItem.getGroupname().indexOf("C") > -1) {
						groupMap.put(3, groupItem);
					}else if(groupItem.getGroupname().indexOf("D") > -1) {
						groupMap.put(4, groupItem);
					}
				}
			}
		}
		if(groupMap.containsKey(level)) {
			EforcesUsersgroup groupItem = groupMap.get(level);
			if(groupItem != null ) {
				EforcesUsersgrouprele  userGroupRele = new EforcesUsersgrouprele();
				userGroupRele.setUserid(userInfo.getId());
				userGroupRele.setUsername(userInfo.getNumber());
				userGroupRele.setGroupid(groupItem.getId());
				eforcesUsersgroupDao.insert(userGroupRele);
			}
		}
		
		
		return null;
	}
}
