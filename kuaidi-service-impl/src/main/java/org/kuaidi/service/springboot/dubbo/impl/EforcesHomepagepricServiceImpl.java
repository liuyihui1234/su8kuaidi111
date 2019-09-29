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

    /**
     * 后台管理查询显示轮播图
     * @return
     */
    @Override
    public List<EforcesHomepagepic> getListMsg() {
        return homepagepicMapper.getListMsg();
    }

    /**
     * 后台管理添加轮播图
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesHomepagepic record) {
        return homepagepicMapper.insertSelective(record);
    }

    /**
     * 后台管理修改轮播图
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesHomepagepic record) {
        return homepagepicMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 后台管理删除轮播图
     * @param id
     * @return
     */
    @Override
    public int delete(Integer[] id) {
        return homepagepicMapper.delete(id);
    }
}
