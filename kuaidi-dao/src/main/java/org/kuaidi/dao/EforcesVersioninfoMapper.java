package org.kuaidi.dao;
import org.kuaidi.bean.domain.EforcesVersioninfo;

import java.util.List;

public interface EforcesVersioninfoMapper {

    int insert(EforcesVersioninfo record);

    EforcesVersioninfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(EforcesVersioninfo record);

    /**
     * 查詢版本升級相關信息
     * @return
     */
    List<EforcesVersioninfo> getlist(int type);

    /**
     * 后台管理查询版本信息
     * @return
     */
    List<EforcesVersioninfo> getListMsg();

    /**
     * 后台管理修改版本信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesVersioninfo record);

    /**
     * 后台管理增加版本信息
     * @param record
     * @return
     */
    int insertSelective(EforcesVersioninfo record);

    int deleteByPrimaryKey(Integer id);

    /**
     * 后台管理删除版本信息
     * @param id
     * @return
     */
    int delete(Integer[] id);
}