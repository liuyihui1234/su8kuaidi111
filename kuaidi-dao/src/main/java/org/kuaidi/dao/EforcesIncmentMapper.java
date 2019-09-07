package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesIncment;

import java.util.List;
import java.util.Map;

public interface EforcesIncmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesIncment record);

    int insertSelective(EforcesIncment record);

    List<EforcesIncment> selectAllIcrment(String parameter);

    EforcesIncment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesIncment record);

    int updateByPrimaryKey(EforcesIncment record);

    String getprovinceid(String name);

    String getcityid(String name);

    String getareaid(String name,String id);


    /**
     * 根据number作为parentid查找所有的Incment
     */
    List<EforcesIncment> selectIncmentByParentid(List<String> parentidList);

    /**
     * 根据number查找对应的Incment
     */
    List<EforcesIncment> selectIncmentByNumber(List<String> number);
    
    /*
     * 根据编号查询网点信息
     */
    EforcesIncment  selectByNumber(String number);
    
    /*
            * 根据parentnumber统计对应的Incment
     */
    List<Map<String, Object>> statisticsIncmentByParentid(List<String> parentidList);
    
    /**
           * 根据number统计对应的Incment
     */
    List<Map<String, Object>> statisticsIncmentByNumber(List<String> number);

    int deleteByid(List<Integer> array);
}