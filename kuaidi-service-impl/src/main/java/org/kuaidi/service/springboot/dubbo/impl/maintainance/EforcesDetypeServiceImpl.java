package org.kuaidi.service.springboot.dubbo.impl.maintainance;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesDetype;
import org.kuaidi.dao.EforcesDetypeMapper;
import org.kuaidi.iservice.maintainance.IEforcesDetypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2019/8/10 12:03
 */

@Service(version = "1.0.0")
public class EforcesDetypeServiceImpl implements IEforcesDetypeService {
    @Autowired
    EforcesDetypeMapper detypeMapper;

    @Override
    public EforcesDetype selectOne(Integer id) {
        return detypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<EforcesDetype> selectAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<EforcesDetype> list = detypeMapper.selectAll();
        final PageInfo<EforcesDetype> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertOne(EforcesDetype record) {
        return detypeMapper.insertSelective(record);
    }

    @Override
    public int updateOne(EforcesDetype record) {
        return detypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateDelete(Integer[] ids) {
        return detypeMapper.updateDelete(ids);
    }
}
