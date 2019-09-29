package org.kuaidi.dao;

import org.apache.ibatis.annotations.Options;
import org.kuaidi.bean.domain.EforcesNetsign;

import java.util.List;


public interface EforcesNetsignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesNetsign record);

    @Options(useGeneratedKeys = true)
    int insertSelective(EforcesNetsign record);


    /**
     * 根据id查询该用签约用户详细信息
     * @param id
     * @return
     */
    EforcesNetsign selectByPrimaryKey(Integer id);

    /**
     * 根据id动态修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesNetsign record);

    int updateStatusById(EforcesNetsign record);

    int updateByPrimaryKey(EforcesNetsign record);

    /**
     * 查询省、市、县、网点
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    EforcesNetsign selectProvinces(EforcesNetsign eforcesNetsign);

    /**
     *根据数据的各种条件动态查询信息列表
     * @param record
     * @return
     */
    List<EforcesNetsign> selectNetsignSort(EforcesNetsign record);

    /**
     * 根据输入的条件动态联表查询该行数据的详细信息
     * @param record
     * @return
     */
    EforcesNetsign selectBySort(EforcesNetsign record);

    /**
     * 查询我的合同pdf
     * @return
     */
    List<EforcesNetsign> selectPath(String incNumber);
	
	/*
     * 根据ID删除
     */
    Integer  delNetSignByIds(List<Integer>list);

}