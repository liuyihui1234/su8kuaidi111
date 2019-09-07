package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesReceivedScan;

import java.util.HashMap;
import java.util.List;

public interface EforcesReceivedScanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesReceivedScan record);

    /**
     * 收件
     * @param record
     * @return
     */
    int insertSelective(EforcesReceivedScan record);

    /**
     * 根据主键参照对应的详细信息
     * g
     * @param id
     * @return
     */
    EforcesReceivedScan selectByPrimaryKey(Integer id);


    /**
     * 收件扫描信息动态修改
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesReceivedScan record);

    int updateByPrimaryKeyWithBLOBs(EforcesReceivedScan record);

    int updateByPrimaryKey(EforcesReceivedScan record);

    /**
     * 派件 收件总数量
     * @param incid
     * @return
     */
    int selectCount(String incid);

    /**
     * 获得网点下的所有订单信息
     * @return
     */
    List<EforcesReceivedScan> getAllOrder(String incid);

/*    *//**
     * 收件，根据订单号在订单表查到数据增加到扫描表内
     * @param
     * @param
     * @return
     *//*
    int addOrderMsg(EforcesReceivedScan receivedScan);*/

    /**
     * 根据网点展示所有到件扫描
     * g
     * @param incid
     * @return
     */
    List<EforcesReceivedScan> getAllOrderSelective(Integer incid);

    /**
     * 寄/派件运单管理
     * @param number
     * @return
     */
    List<HashMap> getreceiveOrder (String number);


    /**
     *  收件信息批量插入
     * @param list
     * @return
     */
    int insertList (List<EforcesReceivedScan> list);
}