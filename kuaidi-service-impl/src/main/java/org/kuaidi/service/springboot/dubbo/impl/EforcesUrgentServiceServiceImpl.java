package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesUrgent;
import org.kuaidi.dao.EforcesUrgentMapper;
import org.kuaidi.iservice.IEforcesUrgentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(version = "1.0.0")
public class EforcesUrgentServiceServiceImpl implements IEforcesUrgentService {
   @Autowired
   EforcesUrgentMapper urgentMapper;

    /**
     * 获取业务类型
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesUrgent> listResult(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesUrgent> listResult = urgentMapper.listResult();
        final PageInfo<EforcesUrgent> pageInfo = new PageInfo<>(listResult);
        return pageInfo;
    }

    /**
     * 修改业务类型
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesUrgent record) {
        return urgentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加业务类型
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesUrgent record) {
        return urgentMapper.insertSelective(record);
    }

    /**
     * 删除业务类型
     * @param id
     * @return
     */
    @Override
    public Integer removeUpdate(Integer[] id) {
        return urgentMapper.removeUpdate(id);
    }

    /**
     * 查询修改数据
     * @param id
     * @return
     */
    @Override
    public EforcesUrgent selectByPrimaryKey(Integer id) {
        return urgentMapper.selectByPrimaryKey(id);
    }
}
