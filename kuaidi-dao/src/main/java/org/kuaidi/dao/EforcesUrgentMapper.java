package org.kuaidi.dao;

import org.kuaidi.bean.maintainance.EforcesUrgent;

import java.util.List;

public interface EforcesUrgentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesUrgent record);

    int updateByPrimaryKey(EforcesUrgent record);

    /**
     * 获取业务类型信息
     * @return
     */
    List<EforcesUrgent> listResult();

    /**
     * 修改业务类型信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesUrgent record);

    /**
     * 添加业务类型信息
     * @param record
     * @return
     */
    int insertSelective(EforcesUrgent record);

    /**
     * 删除业务类型信息
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询修改数据
     * @param id
     * @return
     */
    EforcesUrgent selectByPrimaryKey(Integer id);
}