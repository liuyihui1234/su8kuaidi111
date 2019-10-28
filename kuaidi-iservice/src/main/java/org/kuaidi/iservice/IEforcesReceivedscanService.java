package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesReceivedScan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.DubboMsgVO;
import org.kuaidi.bean.vo.ResultVo;

import java.util.HashMap;
import java.util.List;

public interface IEforcesReceivedscanService {

    /**
     * 获取数量
     * @param incid
     * @return
     */
    int getReceicedscanCount(String incid);

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
     * 收件
     * @param record
     * @return
     */
    DubboMsgVO insertSelective(EforcesReceivedScan record, EforcesLogisticStracking stracking);

    /**
     * 根据网点展示所有到件扫描
     * g
     * @param incid
     * @return
     */
    PageInfo<EforcesReceivedScan> getAllOrderSelective(Integer pageNum, Integer pageSize, String incid);

    /**
     * 收件扫描信息动态修改
     * g
     * @param receivedScan
     * @return
     */
    int updateByPrimaryKeySelective(EforcesReceivedScan receivedScan);

    /**
     * 根据主键查询对应的详细信息
     * g
     * @param id
     * @return
     */
    EforcesReceivedScan selectByPrimaryKey(Integer id);

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


    ResultVo receiveOrder(EforcesReceivedScan scan, EforcesUser userInfo, EforcesIncment currentStop);

    
    int deleteById(List<Integer> ids);
    
}
