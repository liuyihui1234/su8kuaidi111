package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesProduct;
import org.kuaidi.bean.domain.EforcesRegion;

import java.util.List;

public interface EforcesProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesProduct record);

    int updateByPrimaryKey(EforcesProduct record);

    /**
     * 修改物料品名维护
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesProduct record);

    /**
     * 添加物料品名维护
     * @param record
     * @return
     */
    int insertSelective(EforcesProduct record);

    /**
     * 删除物料品名维护
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询物料品名维护
     * @return
     */
    List<EforcesProduct> getlist(EforcesProduct product);

    /**
     * 查询要修改的数据
     * @param id
     * @return
     */
    EforcesProduct selectByPrimaryKey(Integer id);
}