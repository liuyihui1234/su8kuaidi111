package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDepartment;

import java.util.List;

public interface IEforcesDepartmentService {

    /**
     * 显示企业部门管理 不分页
     * @return
     */
    List<EforcesDepartment> getListTwo();

    /**
     * 显示企业部门管理
     * @return
     */
    PageInfo<EforcesDepartment> getList(Integer pageNum,Integer pageSize);

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
