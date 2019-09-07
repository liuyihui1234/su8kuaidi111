package org.kuaidi.dao;

import org.kuaidi.bean.maintainance.EforcesProductcategory;

import java.util.List;

public interface EforcesProductcategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesProductcategory record);

    /**
     * 动态添加
     * g
     * @param record
     * @return
     */
    int insertSelective(EforcesProductcategory record);

    /**
     * 根据id查找对应的详情
     * g
     * @param id
     * @return
     */
    EforcesProductcategory selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<EforcesProductcategory> selectAll();

    /**
     * 动态修改
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesProductcategory record);
    /**
     * 修改删除状态为1
     * g
     * @param ids
     * @return
     */
    int updateDelete(Integer[] ids);

    int updateByPrimaryKey(EforcesProductcategory record);
}