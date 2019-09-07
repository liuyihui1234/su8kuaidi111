package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesTransportmode;
import org.kuaidi.dao.EforcesTransportmodeMapper;
import org.kuaidi.iservice.IEforcesTransportmodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(version = "1.0.0")
public class EforcesTranportmodeServiceImpl implements IEforcesTransportmodeService {
    @Autowired
    EforcesTransportmodeMapper tranpsoDao;

    /**
     * 显示运输方式
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesTransportmode> getListTran(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesTransportmode> listResult = tranpsoDao.getListTran();
        final PageInfo<EforcesTransportmode> pageInfo = new PageInfo<>(listResult);
        return pageInfo;
    }

    /**
     *删除运输方式
     * @param id
     * @return
     */
    @Override
    public Integer removeUpdate(Integer[] id) {
        return tranpsoDao.removeUpdate(id);
    }

    /**
     * 添加运输方式
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesTransportmode record) {
        return tranpsoDao.insertSelective(record);
    }

    /**
     * 修改运输方式
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesTransportmode record) {
        return tranpsoDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @Override
    public EforcesTransportmode selectByPrimaryKey(Integer id) {
        return tranpsoDao.selectByPrimaryKey(id);
    }
}
