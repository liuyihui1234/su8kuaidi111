package org.kuaidi.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesSentScan;

public interface EforcesSentScanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesSentScan record);

    int insertSelective(EforcesSentScan record);

    EforcesSentScan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesSentScan record);

    int updateById(EforcesSentScan record);

    int updateByPrimaryKey(EforcesSentScan record);

    /**
             * 派件 收件总数量
     * @param incid
     * @return
     */
    int selectCount(String incid);

    List<EforcesSentScan> selectAll(String paramter);

    int deleteByIds(List<Integer>ids);

    List<EforcesSentScan>  selectByNumber(@Param("billsNumber") String billsNumber, @Param("incNumber") String incNumber);

    /**
     * 批量将发件信息插入数据库
     * @param list
     * @return
     */
    int insertList (List<EforcesSentScan> list);

    List<Map<String, Object>> getAllBaggingScan(String incid); 
}