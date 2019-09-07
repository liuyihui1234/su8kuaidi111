package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesProduct;
import org.kuaidi.dao.EforcesProductMapper;
import org.kuaidi.iservice.IEforcesProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesProductServiceImpl implements IEforcesProductService {

    @Autowired
    EforcesProductMapper productMapper;

    /**
     * 修改物料品名维护
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesProduct record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加物料品名维护
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesProduct record) {
        return productMapper.insertSelective(record);
    }

    /**
     * 删除物料品名维护
     * @param id
     * @return
     */
    @Override
    public Integer removeUpdate(Integer[] id) {
        return productMapper.removeUpdate(id);
    }

    /**
     * 查询物料品名维护
     * @return
     */
    @Override
    public PageInfo<EforcesProduct> getlist(Integer pageNum, Integer pageSize,EforcesProduct product) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesProduct> listResult = productMapper.getlist(product);
        final PageInfo<EforcesProduct> pageInfo = new PageInfo<>(listResult);
        return pageInfo;
    }

    /**
     * 查询要修改的数据
     * @param id
     * @return
     */
    @Override
    public EforcesProduct selectByPrimaryKey(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
