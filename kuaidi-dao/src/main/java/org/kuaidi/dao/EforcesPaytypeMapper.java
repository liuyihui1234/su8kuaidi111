package org.kuaidi.dao;

import org.kuaidi.bean.maintainance.EforcesPaytype;

import java.util.List;

public interface EforcesPaytypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesPaytype record);

    /**
     * 动态添加
     * g
     * @param record
     * @return
     */
    int insertSelective(EforcesPaytype record);

    /**
     * 根据id查找对应的详情
     * g
     * @param id
     * @return
     */
    EforcesPaytype selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<EforcesPaytype> selectAll();

    /**
     * 动态修改
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesPaytype record);

    /**
     * 修改删除状态为1
     * g
     * @param ids
     * @return
     */
    int updateDelete(Integer[] ids);

    int updateByPrimaryKey(EforcesPaytype record);
}