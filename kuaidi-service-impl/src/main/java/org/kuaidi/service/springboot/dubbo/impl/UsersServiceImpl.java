package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesUsers;
import org.kuaidi.dao.EforcesUsersMapper;
import org.kuaidi.iservice.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class UsersServiceImpl implements UsersService {
   @Autowired
    private EforcesUsersMapper UsersDao;

   public boolean inseUsers(String username,String password){
       UsersDao.inserUsers(username,password);
       return true;
   }

    public boolean inserUsers(String username, String password) {
        return false;
    }

    public EforcesUsers selectUsers(String username, String password){
       System.out.println(username+ " "+password);
       return UsersDao.selectUsers(username,password);
   }
}
