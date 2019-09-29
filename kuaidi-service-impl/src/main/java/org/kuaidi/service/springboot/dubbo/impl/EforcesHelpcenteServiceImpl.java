package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesHelpcente;
import org.kuaidi.dao.EforcesHelpcenteMapper;
import org.kuaidi.iservice.EforcesHelpcenteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesHelpcenteServiceImpl implements EforcesHelpcenteService {

    @Autowired
    EforcesHelpcenteMapper helpcenteMapper;

    /**
     * 获取帮助中心数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesHelpcente> getCountdata(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesHelpcente> result = helpcenteMapper.getCountdata();
        final PageInfo<EforcesHelpcente> pageInfo = new PageInfo<>(result);
        return pageInfo;
    }

    /**
     * 修改删除状态为已删除
     * @param id
     * @return
     */
    @Override
    public Integer updateById(Integer[] id) {
        return helpcenteMapper.updateById(id);
    }

    /**
     * 添加中心
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesHelpcente record) {
        return helpcenteMapper.insertSelective(record);
    }

    /**
     * 修改帮助中心
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesHelpcente record) {
        return helpcenteMapper.updateByPrimaryKeySelective(record);
    }
}
