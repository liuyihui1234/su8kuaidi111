package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.domain.EforcesVersioninfo;
import org.kuaidi.dao.EforcesUsersgroupMapper;
import org.kuaidi.dao.EforcesVersioninfoMapper;
import org.kuaidi.iservice.IEforcesUserGroupService;
import org.kuaidi.iservice.IEforcesVersioninfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesUserGroupServiceImpl implements IEforcesUserGroupService {
    @Autowired
    EforcesUsersgroupMapper eforcesUsersgroupMapper;



    @Override
    public PageInfo<EforcesUsersgroup> selectAllIGroup(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<EforcesUsersgroup> eforcesUsersgroups = eforcesUsersgroupMapper.selectAllIGroup();
        final PageInfo<EforcesUsersgroup> pageInfo =new PageInfo<>(eforcesUsersgroups);
        return pageInfo;
    }

    @Override
    public EforcesUsersgroup selectByPrimaryKey(Integer id) {
        return eforcesUsersgroupMapper.selectByPrimaryKey(id);
    }

}
