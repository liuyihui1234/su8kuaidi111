package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesUrgent;

public interface IEforcesUrgentService {
    /**
     * 获取业务类型信息
     * @return
     */
    PageInfo<EforcesUrgent> listResult(Integer pageNum,Integer pageSize);
    /**
     * 修改业务类型信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesUrgent record);

    /**
     * 添加业务类型信息
     * @param record
     * @return
     */
    int insertSelective(EforcesUrgent record);

    /**
     * 删除业务类型信息
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询修改数据
     * @param id
     * @return
     */
    EforcesUrgent selectByPrimaryKey(Integer id);
}
