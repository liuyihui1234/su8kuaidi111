package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesShift;
import org.kuaidi.dao.EforcesShiftMapper;
import org.kuaidi.iservice.IEforcesShiftService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(version = "1.0.0")
public class EforcesShiftServiceImpl implements IEforcesShiftService {
    @Autowired
    EforcesShiftMapper shiftDao;

    /**
     * 查询班次管理
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesShift> selectByShiftlist(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<EforcesShift> list = shiftDao.selectByShiftlist();
        final PageInfo<EforcesShift> pageInfo =new PageInfo<>(list);
        return pageInfo ;
    }

    /**
     * 删除班次管理
     * @param id
     * @return
     */
    @Override
    public Integer removeByShift(Integer[] id) {
        return shiftDao.removeByShift(id);
    }

    /**
     * 添加班次管理
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesShift record) {
        return shiftDao.insertSelective(record);
    }

    /**
     * 修改
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesShift record) {
        return shiftDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据id查询数据修改班次管理
     * @param id
     * @return
     */
    @Override
    public EforcesShift selectByPrimaryKey(Integer id) {
        return shiftDao.selectByPrimaryKey(id);
    }
}
