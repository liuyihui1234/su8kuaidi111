package org.kuaidi.dao;

import org.kuaidi.bean.maintainance.EforcesDetype;

import java.util.List;

public interface EforcesDetypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesDetype record);

    /**
     * 动态添加一条数据
     * g
     * @param record
     * @return
     */
    int insertSelective(EforcesDetype record);

    /**
     * 根据主键查找对应的详情
     * g
     * @param id
     * @return
     */
    EforcesDetype selectByPrimaryKey(Integer id);

    /**
     * 展示所有信息列表
     * g
     * @return
     */
    List<EforcesDetype> selectAll();

    /**
     * 动态修改数据
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesDetype record);

    /**
     * 修改删除状态为1
     * g
     * @param ids
     * @return
     */
    int updateDelete(Integer[] ids);

    int updateByPrimaryKey(EforcesDetype record);
}