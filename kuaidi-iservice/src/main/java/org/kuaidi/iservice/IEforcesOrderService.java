package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesOrderIdentity;
import org.kuaidi.bean.vo.OrderInfoVO;
import org.kuaidi.bean.vo.ScanSearchVO;


public interface IEforcesOrderService {
		

	int getUserOrderCount(String  userNum);
	
	/**
	 * 根据创建用户id查询
	 * @param pageNum
	 * @param pageSize
	 * @param createUserId
	 * @return
	 */
	 PageInfo<EforcesOrder> getByCreateUserId(Integer pageNum, Integer pageSize,String createUserId);
	 
	 /*
	  * 根据创建用户的id查询派件信息
	  * @param pageNum
	  * @param pageSize
	  * @param createUserId
	  */
	 PageInfo<EforcesOrder> getDeliveryByCreateUserId(Integer pageNum, Integer pageSize,String createUserId);

	/**
	 * 根据订单号查询订单详情
	 * @param number
	 * @return
	 */
	List<EforcesOrder> getByNumber(String number);

    /**
     * 已派件
     * @param pageNum
     * @param pageSize
     * @param incNum
     * @return
     */
    PageInfo<EforcesOrder> getHadPostPackage(Integer pageNum, Integer pageSize, String incNum);

    /**
     * 未派件
     * @param pageNum
     * @param pageSize
     * @param incNum
     * @return
     */
    PageInfo<EforcesOrder> getNotPostPackage(Integer pageNum, Integer pageSize,  String userNum 
    		,String incNum, Integer incLevel);

    /**
     * 动态插入一条数据
     * @param record
     * @return
     */
    int insertSelective(EforcesOrder record, EforcesOrderIdentity orderIdentity);

	PageInfo<EforcesOrder> getHadFinishPackage(Integer pageNum, Integer pageSize, String incNum);
    
    PageInfo<EforcesOrder> getNotFinishPackage(Integer pageNum , Integer pageSize , String incNum);

	/**
	 * 收件、根据Number订单编号查询数据添加到扫描表
	 * @param Number
	 * @return
	 */
	EforcesOrder getOrderMsg(String Number);

	/**
	 * 获取已支付订单信息
	 * @param postmanid 发件快递员编号
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	PageInfo<EforcesOrder> yetPayment(String postmanid,Integer pageSize,Integer pageNum);

	/**
	 * 获取未支付订单信息
	 * @param postmanid 发件快递员编号
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	PageInfo<EforcesOrder> notPayment(String postmanid,Integer pageSize,Integer pageNum);

	/**
	 * 收件详情信息
	 * @param
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<EforcesOrder> getListAddressee(String incid,Integer pageNum,Integer pageSize);

	/**
	 * 寄/派件运单管理
	 * @param page
	 * @param size
	 * @return
	 */
	PageInfo<EforcesOrder> getAllMsg(Integer page,Integer size, OrderInfoVO order);

	/**
	 * 删除寄/派件运单管理
	 * @param id
	 * @return
	 */
	Integer removeUpdate(Integer[] id);
	
	/*
	 * 查询留仓件和已损件， 
	 */
	PageInfo<EforcesOrder> getDestoryedBill(Integer pageNum,Integer pageSize, String incNum, List<Integer>causeIds);
	
	
	/**
	 * 根据订单号查询订单详情
	 * @param numbers ,用逗号分隔的订单号
	 * @return
	 */
	List<EforcesOrder> getOrderByNumbers(String numbers);

	/**
	 *
	 * 根据多个订单号查询订单信息
	 * @param list
	 * @return
	 */
	List<EforcesOrder> getAllNumberMsg (List<String> list);

	/**
	 * 物流跟踪详细查询
	 * @param Number
	 * @return
	 */
	EforcesOrder getOrder (String Number);

	/**
	 * 查询详情信息
	 * @param id
	 * @return
	 */
	EforcesOrder getByid (Integer id);
	
	/*
	  * 根据派单用户的openId 查询订单信息
	 */
	List<EforcesOrder> getNumberByOpenId(String openid);
	
	/*
	 * 更新邮件信息。
	 */
	int updateByPrimaryKeySelective(EforcesOrder record);
	
	/*
	 * 获得发件信息。
	 */
	PageInfo<Map<String,Object>> getSendBillsByParam(Integer page , Integer limit , ScanSearchVO  scanSearch);

	/*
	 * 获得发件信息。
	 */
	public PageInfo<Map<String, Object>> getReceiveBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch);
	
	/*
	 * 派件扫描
	 */
	public PageInfo<Map<String, Object>> getDistributedBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch);
	
	/*
	 * 签收扫描
	 * getCustomerSignBillsByParam
	 */
	public PageInfo<Map<String, Object>> getCustomerSignBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch);
	
	/*
	 * 称重
	 * getWeightBillsByParam
	 */
	public PageInfo<Map<String, Object>> getWeightBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch);
	
	/*
	 * 查询业务员收入统计
	 */
	public List<Map<String, Object>>  getDayStatisticByUser( String province , String city,
   		 					String area , String incNum , Integer userId ,String startTime , String endTime);
	
	/*
	 * 查询城市收入信息
	 */
	public List<Map<String, Object>>  getDayStatisticByCity( String incNum ,String startTime , String endTime);
	
	/*
	 *每日营业统计（按业务员） 
	 */
	public List<Map<String,Object>> GetPriceByDayToUser(String incNum ,String startTime , String endTime);
	
	/*
	 * 每日营业统计（按用户）
	 */
	public List<Map<String,Object>> GetPriceByDayToCity(String incNum ,String startTime , String endTime);
 	
	/*
 	 * 每日营业统计按照城市
 	 */
	public List<Map<String, Object>> getDayToPriceByCity(String incName, String startTime , String endTime);
	/*
	 *第十个  每日营业统计(按网点)  
	 */
	public List<Map<String, Object>> getDayToPriceByInc(String incName, String startTime , String endTime);
	
	/*
	 *每日营业统计(按业务员)
	 */
	public List<Map<String, Object>> getDayToPriceByUser(String incName, String startTime , String endTime);
	
	/*
	 * 网点月结统计
	 */
	public List<Map<String,Object>> getMonStaColl(String incName , String time);
	
	/*
	 * 客户月结收款统计， 按照页进行查询
	 */
	public PageInfo<Map<String , Object>> getCusMonthBillByPage(Integer pageNum , Integer pageSize , Integer guestId ,String startTime , String endTime);
	
	/*
	 *  客户月结收款统计， 查询全部数据，为了Excel 导出用
	 */
	public List<Map<String , Object>> getCusMonthBillByList(Integer guestId ,String startTime , String endTime);
	
	/*
	 * 业务员月帐统计
	 */
	List<Map<String , Object>> getYwyMonthBill(String ywy, String szy, String startTime,String endTime);
	
	/*
	  * 折损统计
	 */
	List<Map<String , Object>> getLossStaColl(String incNum , String time );
	
	/*
	  * 业务员面单发放统计。
	  *分页查询
	 */
	PageInfo<Map<String, Object>> contrastEmployee(Integer pageNum , Integer pageSize, String province , String city, String area ,String incNum
			, String startTime , String endTime );
	
	/*
	 *  业务员面单发放统计。
	 *  查询全部数据
	 */
	List<Map<String, Object>> contrastEmployeeList(String province , String city, String area ,String incNum
			, String startTime , String endTime );
		
}
