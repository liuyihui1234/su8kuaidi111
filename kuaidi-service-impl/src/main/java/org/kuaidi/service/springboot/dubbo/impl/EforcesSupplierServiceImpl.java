package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesSupplier;
import org.kuaidi.dao.EforcesSupplierMapper;
import org.kuaidi.iservice.IEforcesSupplierService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesSupplierServiceImpl implements IEforcesSupplierService {

    @Autowired
    EforcesSupplierMapper supplierDao;

    /**
     * 获取供应商列表信息
     * @return
     */
    @Override
    public PageInfo<EforcesSupplier> getlistSupplier(Integer pageNum,Integer pageSize,EforcesSupplier supplier) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesSupplier> listResult = supplierDao.getlistSupplier(supplier);
        final PageInfo<EforcesSupplier> pageInfo = new PageInfo<>(listResult);
        return pageInfo;
    }

    /**
     * 删除供应商列表信息
     * @param id
     * @return
     */
    @Override
    public Integer removeUpdate(Integer[] id) {
        return supplierDao.removeUpdate(id);
    }

    /**
     * 增加供应商列表信息
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesSupplier record) {
        return supplierDao.insertSelective(record);
    }

    /**
     * 修改供应商列表信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesSupplier record) {
        return supplierDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据id查询修改数据
     * @param id
     * @return
     */
    @Override
    public EforcesSupplier selectByPrimaryKey(Integer id) {
        return supplierDao.selectByPrimaryKey(id);
    }
}
