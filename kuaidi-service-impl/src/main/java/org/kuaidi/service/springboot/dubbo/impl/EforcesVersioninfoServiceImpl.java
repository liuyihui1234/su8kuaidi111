package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesVersioninfo;
import org.kuaidi.dao.EforcesVersioninfoMapper;
import org.kuaidi.iservice.IEforcesVersioninfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesVersioninfoServiceImpl implements IEforcesVersioninfoService {

    @Autowired
    EforcesVersioninfoMapper versioninfoMapper;

    @Override
    public List<EforcesVersioninfo> getlist(int type){
       return versioninfoMapper.getlist(type);
    }
}
