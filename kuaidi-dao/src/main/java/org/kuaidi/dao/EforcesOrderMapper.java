package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.OrderInfoVO;
import org.kuaidi.bean.vo.ScanSearchVO;

import java.util.List;
import java.util.Map;

public interface EforcesOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesOrder record);

    int insertSelective(EforcesOrder record);

    EforcesOrder selectByPrimaryKey(Integer id);

    List<EforcesOrder> selectLikeByNumber(String number);

    int updateByPrimaryKeySelective(EforcesOrder record);

    int updateByPrimaryKey(EforcesOrder record);
    /*
     * 批量的更新订单的状态。
     */
    int updateStatusByNumberList(@Param("status")Integer status , @Param("numberList")List<String> numberList);
    
    int selectByUserId(String userNumber);
    
    /**
     * 根据创建用户id查询
     * @param createUserId
     * @return
     */
    List<EforcesOrder> selectByCreateUserId(String createUserId);
    
    /*
     * 已经投递订单
     */
    List<EforcesOrder> selectDeleveryByCreateUserId(String createUserId);

    /**
     * 已派件
     */
    List<EforcesOrder> getHadPostPackage(String incid);

    /**
     * 未派件
     * @param incid
     * @return
     */
    List<EforcesOrder> notHadPostPackage(@Param("userNum")String userNum , @Param("incid") String incid);
    
    /**
	     * 未派件(街道的未派单)
	* @param incid
	* @return
	*/
	List<EforcesOrder> notHadPostPackage1(@Param("userNum")String userNum , @Param("incid") String incid);

    
    /**
     * 已完成订单
     */
    List<EforcesOrder> getHadFinishPackage(String incid);

    /**
     * 未完成订单
     * @param incid 用户所在的部门
     * @return
     */
    List<EforcesOrder> getNotFinishPackage(String incid);

    /**
          * 收件、根据Number订单编号查询数据添加到扫描表
     * @param Number
     * @return
     */
    EforcesOrder getOrderMsg(String Number);

    /**
     * 获取已支付订单信息
     * @param postmanid 发件快递员编号
     * @return
     */
    List<EforcesOrder> yetPayment(String postmanid);

    /**
     * 获取未支付订单信息
     * @param postmanid 发件快递员编号
     * @return
     */
    List<EforcesOrder> notPayment(String postmanid);

    /**
     * 收件详情信息
     * @param incid
     * @return
     */
    List<EforcesOrder> getListAddressee(String incid);

    /**
               * 寄/派件运单管理
     * @return
     */
    List<EforcesOrder> getAllMsg(OrderInfoVO order);

    /**
     * 删除寄/派件运单管理
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询已经损坏的件
     */
	List<EforcesOrder> getDestoryBill(@Param("incid") String incid, @Param("causeIds") List<Integer> causeIds);
	
	/*
	 * 根据多个订单id，查询订单信息 
	 */
	List<EforcesOrder> getOrderByNumbers(@Param("list") List<String> list);

    /**
     * 查询详情信息
     * @param id
     * @return
     */
    EforcesOrder getByid (Integer id);
    
    /*
	 * 根据发单人的openId 查询他发送的订单信息。
	 */
	List<EforcesOrder> getNumbersByOpenId(String openid);
	/*
	 * 派件扫描
	 */
	List<Map<String,Object>> getSendBillsByParam(ScanSearchVO scanSearch);
	/*
	 * 收件扫描
	 */
	List<Map<String,Object>> getReceiveBillsByParam(ScanSearchVO scanSearch);
	/*
	 * 派件查询
	 */
	List<Map<String,Object>> getDistributedBillsByParam(ScanSearchVO scanSearch);
	/*
	 * 签收
	 */
	List<Map<String,Object>> getCustomerSignBillsByParam(ScanSearchVO scanSearch);
	/*
	 * 称重
	 */
	List<Map<String,Object>> getWeightBillsByParam(ScanSearchVO scanSearch);
	
	/*
	 *营业收入统计， 按照营业员 
	 */
	List<Map<String, Object>> getDayStatisticByUser(@Param("province")String province,@Param("city") String city,@Param("area")  String area, 
			@Param("incNum") String incNum, @Param("userId")  Integer userId, 
			@Param("startTime") String startTime,@Param("endTime") String endTime);

	/*
	 * 营业收入统计， 按照城市
	 */
	List<Map<String, Object>> getDayStatisticByCity(@Param("incNum") String incNum,
							@Param("startTime") String startTime, @Param("endTime") String endTime);
	
	/*
	 *  第三个 每日营业统计(按业务员统计) 
	 */
	List<Map<String, Object>> getDayToPriceByUser(@Param("incNum") String incName,
				 @Param("startTime") String startTime,@Param("endTime") String endTime);	
	
	/*
	 *  第四个 每日营业统计(按网点) 
	 */
	List<Map<String, Object>> getDayToPriceByCity(@Param("incNum") String incName,
				 @Param("startTime") String startTime,@Param("endTime") String endTime);
	
	/*
	 *  第四个 每日营业统计(按网点) 
	 */
	List<Map<String, Object>> getDayToPriceByInc(@Param("incNum") String incName,
				 @Param("startTime") String startTime,@Param("endTime") String endTime);
	
	/*
	 * 网点月结统计
	 */
	List<Map<String, Object>> getMonStaColl(@Param("incNum")String incName, 
				@Param("time")String time);	
	
	/*
	 * 客户月结收款统计
	 */
	List<Map<String, Object>>getCusMonthBill(@Param("guestId") Integer guestId, @Param("startTime") String startTime,
			@Param("endTime")String endTime);
	
	/*
	 * 业务员月账单
	 */
	List<Map<String , Object>> getYwyMonthBill(@Param("ywy") String ywy, @Param("szy") String szy,
			@Param("startTime") String startTime, @Param("endTime") String endTime);
	
	/*
	 * 折损统计
	 */
	List<Map<String, Object>> getLossStaColl(@Param("incNum") String incNum, 
			@Param("time")String time);

	/*
	  * 业务员面单发放对比。
	 */
	List<Map<String, Object>> contrastEmployee(@Param("province")String province, @Param("city")String city,@Param("area") String area,
			@Param("incNum") String incNum,	@Param("startTime") String startTime, @Param("endTime")  String endTime);
	
}