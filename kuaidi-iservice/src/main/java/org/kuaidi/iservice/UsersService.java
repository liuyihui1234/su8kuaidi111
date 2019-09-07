package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesUsers;

public interface UsersService {
    /**
     * Users注册
     * @param username
     * @param password
     * @return
     */
    boolean inserUsers(String username,String password);

    /**
     * Users登录
     * @param username
     * @param password
     * @return
     */
    EforcesUsers selectUsers(String username,String password);
}
