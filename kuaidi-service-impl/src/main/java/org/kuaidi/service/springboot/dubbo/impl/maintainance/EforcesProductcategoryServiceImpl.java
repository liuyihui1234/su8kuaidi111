package org.kuaidi.service.springboot.dubbo.impl.maintainance;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesProductcategory;
import org.kuaidi.dao.EforcesProductcategoryMapper;
import org.kuaidi.iservice.maintainance.IEforcesProductcategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2019/8/10 17:26
 */
@Service(version = "1.0.0")
public class EforcesProductcategoryServiceImpl implements IEforcesProductcategoryService {
    @Autowired
    EforcesProductcategoryMapper productcategoryMapper;
    @Override
    public EforcesProductcategory selectOne(Integer id) {
        return productcategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<EforcesProductcategory> selectAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<EforcesProductcategory> list = productcategoryMapper.selectAll();
        final PageInfo<EforcesProductcategory> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertOne(EforcesProductcategory record) {
        return productcategoryMapper.insertSelective(record);
    }

    @Override
    public int updateOne(EforcesProductcategory record) {
        return productcategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateDelete(Integer[] ids) {
        return productcategoryMapper.updateDelete(ids);
    }
}
