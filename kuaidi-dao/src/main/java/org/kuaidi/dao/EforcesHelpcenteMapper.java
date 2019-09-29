package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesHelpcente;

import java.util.List;

public interface EforcesHelpcenteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesHelpcente record);

    EforcesHelpcente selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(EforcesHelpcente record);

    /**
     * 获取帮助中心数据
     * @return
     */
    List<EforcesHelpcente> getCountdata();

    /**
     * 并不是真正的删除，修改状态
     * @param id
     * @return
     */
    Integer updateById(Integer[] id);

    /**
     * 动态添加
     * @param record
     * @return
     */
    int insertSelective(EforcesHelpcente record);

    /**
     * 动态修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesHelpcente record);
}