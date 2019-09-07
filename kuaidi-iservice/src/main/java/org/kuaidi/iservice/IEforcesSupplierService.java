package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesSupplier;

public interface IEforcesSupplierService {
    /**
     * 获取供应商列表信息
     * @return
     */
    PageInfo<EforcesSupplier> getlistSupplier(Integer pageNum,Integer pageSize,EforcesSupplier supplier);

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
