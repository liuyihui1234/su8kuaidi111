package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesHomepagepic;
import org.kuaidi.dao.EforcesHomepagepicMapper;
import org.kuaidi.iservice.IEforcesHomepagepicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesHomepagepricServiceImpl implements IEforcesHomepagepicService {

    @Autowired
    EforcesHomepagepicMapper homepagepicMapper;

    /**
     * 升序查询首页轮播
     * @param id
     * @return
     */
    @Override
    public List<EforcesHomepagepic> selectByDivide(Integer id) {
        return homepagepicMapper.selectByDivide(id);
    }
}
