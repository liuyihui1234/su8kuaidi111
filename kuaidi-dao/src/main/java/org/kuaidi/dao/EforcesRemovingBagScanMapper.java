package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesBaggingScan;
import org.kuaidi.bean.domain.EforcesRemovingBagScan;

public interface EforcesRemovingBagScanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesRemovingBagScan record);

    int insertSelective(EforcesRemovingBagScan record);

    EforcesRemovingBagScan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesRemovingBagScan record);

    int updateByPrimaryKeyWithBLOBs(EforcesRemovingBagScan record);

    int updateByPrimaryKey(EforcesRemovingBagScan record);
    
    List<EforcesRemovingBagScan> selectAll(Integer incid);
    
    int updateIsDeleteById(Integer[] id);
    /*
     * 批量删除拆包记录。
     */
    int insertBatch(List<EforcesRemovingBagScan> list);
}