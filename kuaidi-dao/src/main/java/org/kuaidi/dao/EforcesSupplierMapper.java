package org.kuaidi.dao;

import org.kuaidi.bean.maintainance.EforcesSupplier;

import java.util.List;

public interface EforcesSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesSupplier record);

    int updateByPrimaryKey(EforcesSupplier record);

    /**
     * 获取供应商列表信息
     * @return
     */
    List<EforcesSupplier> getlistSupplier(EforcesSupplier supplier);

    /**
     * 删除供应商列表信息
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 增加供应商列表信息
     * @param record
     * @return
     */
    int insertSelective(EforcesSupplier record);

    /**
     * 修改供应商列表信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesSupplier record);

    /**
     * 根据id查询供应商列表修改
     * @param id
     * @return
     */
    EforcesSupplier selectByPrimaryKey(Integer id);
}