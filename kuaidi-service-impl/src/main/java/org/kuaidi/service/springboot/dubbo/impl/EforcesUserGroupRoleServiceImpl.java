package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.domain.EforcesUsersgrouprele;
import org.kuaidi.dao.EforcesUsersgroupMapper;
import org.kuaidi.dao.EforcesUsersgroupreleMapper;
import org.kuaidi.iservice.IEforcesUserGroupService;
import org.kuaidi.iservice.IEforcesUserGrouproleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesUserGroupRoleServiceImpl implements IEforcesUserGrouproleService {


    @Autowired
    EforcesUsersgroupreleMapper eforcesUsersgroupreleMapper;

    @Override
    public int deleteByUserId(Integer userid) {
        return eforcesUsersgroupreleMapper.deleteByUserId(userid);
    }

    @Override
    public int insertForeach(List<EforcesUsersgrouprele> list) {
        return eforcesUsersgroupreleMapper.insertForeach(list);
    }
}
