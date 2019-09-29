package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesHelpcente;

public interface EforcesHelpcenteService {

    /**
     * 获取数据
     * @return
     */
    PageInfo<EforcesHelpcente> getCountdata(Integer pageNum, Integer pageSize);

    /**
     * 并不是真正的删除，修改状态
     * @param id
     * @return
     */
    Integer updateById(Integer[] id);

    /**
     * 动态添加
     * @param record
     * @return
     */
    int insertSelective(EforcesHelpcente record);

    /**
     * 动态修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesHelpcente record);
}
