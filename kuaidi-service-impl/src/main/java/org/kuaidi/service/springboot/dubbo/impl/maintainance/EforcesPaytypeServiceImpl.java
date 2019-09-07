package org.kuaidi.service.springboot.dubbo.impl.maintainance;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesPaytype;
import org.kuaidi.dao.EforcesPaytypeMapper;
import org.kuaidi.iservice.maintainance.IEforcesPaytypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2019/8/10 17:25
 */
@Service(version = "1.0.0")
public class EforcesPaytypeServiceImpl implements IEforcesPaytypeService {
    @Autowired
    EforcesPaytypeMapper paytypeMapper;

    @Override
    public EforcesPaytype selectOne(Integer id) {
        return paytypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<EforcesPaytype> selectAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<EforcesPaytype> list = paytypeMapper.selectAll();
        final PageInfo<EforcesPaytype> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertOne(EforcesPaytype record) {
        return paytypeMapper.insertSelective(record);
    }

    @Override
    public int updateOne(EforcesPaytype record) {
        return paytypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateDelete(Integer[] ids) {
        return paytypeMapper.updateDelete(ids);
    }
}
