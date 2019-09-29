package org.kuaidi.iservice.maintainance;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesProblemcause;

import java.util.List;

/**
 * Created by Administrator on 2019/8/10 17:23
 */
public interface IEforcesProblemcauseService {
    /**
     * 根据主键查找对应的详情
     * g
     * @param id
     * @return
     */
    EforcesProblemcause selectOne(Integer id);

    /**
     * 分页展示所有信息列表
     * g
     * @param page
     * @param size
     * @return
     */
    PageInfo<EforcesProblemcause> selectAll(Integer page, Integer size);

    /**
     * 动态添加一条数据
     * g
     * @param record
     * @return
     */
    int insertOne(EforcesProblemcause record);

    /**
     * 动态修改数据
     * g
     * @param record
     * @return
     */
    int updateOne(EforcesProblemcause record);

    /**
     * 修改删除状态为1
     * g
     * @param ids
     * @return
     */
    int updateDelete(Integer[] ids);
    
    /**
              * 展示所有信息列表
     * @return
     */
    List<EforcesProblemcause> selectAll();
}
