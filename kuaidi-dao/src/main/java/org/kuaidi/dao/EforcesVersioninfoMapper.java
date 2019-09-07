package org.kuaidi.dao;
import org.kuaidi.bean.domain.EforcesVersioninfo;

import java.util.List;

public interface EforcesVersioninfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesVersioninfo record);

    int insertSelective(EforcesVersioninfo record);

    EforcesVersioninfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesVersioninfo record);

    int updateByPrimaryKey(EforcesVersioninfo record);

    /**
     * 查詢版本升級相關信息
     * @return
     */
    List<EforcesVersioninfo> getlist(int type);
}