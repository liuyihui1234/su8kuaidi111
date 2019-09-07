package org.kuaidi.iservice.maintainance;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesDetype;

import java.util.List;

/**
 * Created by Administrator on 2019/8/10 12:02
 */
public interface IEforcesDetypeService {

    /**
     * 根据主键查找对应的详情
     * g
     * @param id
     * @return
     */
    EforcesDetype selectOne(Integer id);

    /**
     * 分页展示所有信息列表
     * g
     * @param page
     * @param size
     * @return
     */
    PageInfo<EforcesDetype> selectAll(Integer page, Integer size);

    /**
     * 动态添加一条数据
     * g
     * @param record
     * @return
     */
    int insertOne(EforcesDetype record);

    /**
     * 动态修改数据
     * g
     * @param record
     * @return
     */
    int updateOne(EforcesDetype record);

    /**
     * 修改删除状态为1
     * g
     * @param ids
     * @return
     */
    int updateDelete(Integer[] ids);


}
