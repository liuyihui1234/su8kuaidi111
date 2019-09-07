package org.kuaidi.dao;

import org.kuaidi.bean.maintainance.EforcesProblemcause;

import java.util.List;

public interface EforcesProblemcauseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesProblemcause record);

    /**
     * 动态添加一条数据
     * @param record
     * @return
     */
    int insertSelective(EforcesProblemcause record);

    /**
     * 根据id查找对应的详情
     * g
     * @param id
     * @return
     */
    EforcesProblemcause selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<EforcesProblemcause> selectAll();

    /**
     * 动态修改
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesProblemcause record);
    /**
     * 修改删除状态为1
     * g
     * @param ids
     * @return
     */
    int updateDelete(Integer[] ids);

    int updateByPrimaryKey(EforcesProblemcause record);
}