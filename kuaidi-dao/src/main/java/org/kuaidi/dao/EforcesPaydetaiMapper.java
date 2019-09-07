package org.kuaidi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesPaydetai;

public interface EforcesPaydetaiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesPaydetai record);

    /**
     * 调用下单的方法的时候 保存用户订单信息
     * @param record
     * @return
     */
    int insertSelective(EforcesPaydetai record);

    EforcesPaydetai selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(EforcesPaydetai record);

    int updateByPrimaryKey(EforcesPaydetai record);

  /*  *//**
     * 保存订单信息接口
     * @param
     * @return
     *//*
    int orderinfo(EforcesPaydetai paydetai);*/
    
    /**
     * 修改订单信息
     * @param tradeNo
     * @param outTradeNo
     * @return
     */
    int upderinfo(@Param("outTradeNo") String outTradeNo ,@Param("tradeNo") String tradeNo );

    List<EforcesPaydetai>  selectBySort(EforcesPaydetai record);

	List<EforcesPaydetai> selectByTradeno(String tradeno);
    
}