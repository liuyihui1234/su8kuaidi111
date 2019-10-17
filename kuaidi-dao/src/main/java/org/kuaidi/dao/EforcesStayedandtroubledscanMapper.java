package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;

import java.util.List;

public interface EforcesStayedandtroubledscanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesStayedandtroubledscan record);

    int insertList(List<EforcesStayedandtroubledscan> list);


    int insertSelective(EforcesStayedandtroubledscan record);

    EforcesStayedandtroubledscan selectByPrimaryKey(Integer id);

    EforcesStayedandtroubledscan selectById(Integer id);

    int updateByPrimaryKeySelective(EforcesStayedandtroubledscan record);

    int updateByPrimaryKeyWithBLOBs(EforcesStayedandtroubledscan record);

    int updateByPrimaryKey(EforcesStayedandtroubledscan record);

    /**
     * 获取问题件详细信息
     *
     * @param incid 网店编号 incment中的number
     * @return
     */
    List<EforcesStayedandtroubledscan> getIssue(@Param("incid") String incid, @Param("causeIds") List<Integer> causeIds);

    List<EforcesStayedandtroubledscan> getAllIssue(String incid);

    /*
     * 根据订单号删除问题件
     */
    int deleteStayedandtroubledByNumber(String billsNumber);

    /**
     * 根据id删除
     *
     * @param list
     * @return
     */
    int deleteByid(List<Integer> list);
    
    /*
              * 根据订单号查找问题订单。
     */
    List<EforcesStayedandtroubledscan> getScanInfoByBillsNumbers(List<String> list);

}