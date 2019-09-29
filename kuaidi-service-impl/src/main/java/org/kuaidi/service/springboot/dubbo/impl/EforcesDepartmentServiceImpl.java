package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDepartment;
import org.kuaidi.dao.EforcesDepartmentMapper;
import org.kuaidi.iservice.IEforcesDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesDepartmentServiceImpl implements IEforcesDepartmentService {
    @Autowired
    EforcesDepartmentMapper departmentMapper;

    @Override
    public List<EforcesDepartment> getListTwo() {
        List<EforcesDepartment> listResult = departmentMapper.getList();
        return listResult;
    }

    /**
     * 显示部门管理
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesDepartment> getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesDepartment> listResult = departmentMapper.getList();
        final PageInfo<EforcesDepartment> pageInfo = new PageInfo<>(listResult);
        return pageInfo;
    }

    /**
     * 添加企业部门管理
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesDepartment record) {
        return departmentMapper.insertSelective(record);
    }

    /**
     * 修改企业部门管理
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesDepartment record) {
        return departmentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 删除企业部门管理
     * @param id
     * @return
     */
    @Override
    public Integer removeUpdate(Integer[] id) {
        return departmentMapper.removeUpdate(id);
    }

    /**
     * 查询要修改的数据
     * @param id
     * @return
     */
    @Override
    public EforcesDepartment selectByPrimaryKey(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
}
