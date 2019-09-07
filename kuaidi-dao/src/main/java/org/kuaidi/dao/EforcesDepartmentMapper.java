package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesDepartment;

import java.util.List;

public interface EforcesDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesDepartment record);

    int updateByPrimaryKeyWithBLOBs(EforcesDepartment record);

    int updateByPrimaryKey(EforcesDepartment record);

    /**
     * 显示企业部门管理
     * @return
     */
    List<EforcesDepartment> getList();

    /**
     * 添加企业部门管理
     * @param record
     * @return
     */
    int insertSelective(EforcesDepartment record);

    /**
     * 修改企业部门管理
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesDepartment record);

    /**
     * 删除企业部门管理
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询修改的数据
     * @param id
     * @return
     */
    EforcesDepartment selectByPrimaryKey(Integer id);
}