package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesShift;

import java.util.List;

public interface IEforcesShiftService {
    /**
     * 查询班次管理
     * @param
     * @return
     */
    PageInfo<EforcesShift> selectByShiftlist(Integer pageNum, Integer pageSize);

    /**
     * 删除班次管理
     * @param id
     * @return
     */
    Integer removeByShift(Integer[] id);

    /**
     * 添加班次管理
     * @param record
     * @return
     */
    int insertSelective(EforcesShift record);

    /**
     * 修改班次管理
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesShift record);

    /**
     * 根据id查询数据修改班次管理
     * @param id
     * @return
     */
    EforcesShift selectByPrimaryKey(Integer id);
    
    
    /**
     * 查询班次管理
     * @param
     * @return
     */
    List<EforcesShift> selectByShiftlist();
}
