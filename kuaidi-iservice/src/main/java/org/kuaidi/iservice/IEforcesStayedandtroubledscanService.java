package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IEforcesStayedandtroubledscanService {

    /**
     * 获取问题件详细信息
     *
     * @param incid 网店编号 incment中的number
     * @return
     */
    PageInfo<EforcesStayedandtroubledscan> getIssue(Integer pageNum, Integer pageSize, String incid, List<Integer> causeIds);

    PageInfo<EforcesStayedandtroubledscan> getAllIssue(Integer pageNum, Integer pageSize, String incid);

    int insertList(List<EforcesStayedandtroubledscan> list);

    int updateByPrimaryKeySelective(EforcesStayedandtroubledscan record);

    EforcesStayedandtroubledscan selectById(Integer id);
    
    int deleteDestoryBill(String billNumber);

    int deleteByid(List<Integer> list);

}
