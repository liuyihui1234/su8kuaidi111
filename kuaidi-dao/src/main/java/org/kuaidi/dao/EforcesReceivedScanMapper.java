package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesReceivedScan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<EforcesReceivedScan> getAllOrderSelective(String incid);

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

    /*
              * 假删除
     */
	int updateById(List<Integer> ids);
	
	/*
	 * 查找已收的订单
	 */
	List<EforcesReceivedScan> getHadReceiveOrder(@Param("incNum")String incNum , @Param("billNumList")List<String>billNumList);
	
	/*
	 * 查询收包记录
	 */
	List<Map<String, Object>> getAllBaggingScan(String incid);
	
	
	/*
	 * 收件统计
	 */
	List<Map<String, Object>> getReceiveStatistics(@Param("SstartTime") String SstartTime,@Param("SendTime") String SendTime, 
			@Param("RstartTime")  String RstartTime,@Param("RendTime")  String RendTime, 
			@Param("incNum") String incNum, @Param("province") String province, 
			@Param("city") String city,@Param("area") String area);

	/*
	  * 中转站到发统计
	 */
	List<Map<String, Object>> getToSendStatisticsByList(@Param("incNum")String incNum , @Param("fstime")String fstime,@Param("fetime") String fetime,
				@Param("dstime")String dstime,@Param("detime") String detime);
    
}