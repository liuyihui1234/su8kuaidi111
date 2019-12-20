package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesSentScan;

import java.util.List;
import java.util.Map;

public interface IEforcesIncmentService {

    /**
     * 增加网点信息
     *
     * @param eforcesIncment
     * @return
     */
    int insetIncment(EforcesIncment eforcesIncment);

     int deleteByid(List<Integer> array);

    PageInfo<EforcesIncment> selectAllIcrment(Integer curr, Integer nums,String parameter, String parentId);
    
    EforcesIncment selectByPrimaryKey(Integer id);

    int insert(EforcesIncment record);

    int updateByPrimaryKey(EforcesIncment record);

    String getprovinceid(String name);

    String getcityid(String name);

    String getareaid(String name,String id);

    /**
     * 根据number作为parentid查找所有的Incment
     * @param parentidList
     * @return
     */
    List<EforcesIncment> selectByParentid(List<String> parentidList);

    /**
     * 根据根据number查找对应的Incment
     * @param numberList
     * @return
     */
    List<EforcesIncment> selectByNumber(List<String> numberList);
    
    EforcesIncment selectByNumber(String number);
    
    /*
            * 根据父节点的number 统计所有子节点的一个月的订单情况。（发送的订单数量）
     */
    List<Map<String,Object>> statisticsByParentid(List<String> parentidList);
    /*
             * 根据 节点的编号，统计自己一个月发送订单的数量
     */
    List<Map<String,Object>> statisticsByNumber(List<String> numbers);

    EforcesIncment getByNextStopName(String name);
}
