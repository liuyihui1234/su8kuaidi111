package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import java.util.List;
import org.kuaidi.bean.domain.EforcesOrder;


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
    PageInfo<EforcesOrder> getNotPostPackage(Integer pageNum, Integer pageSize, String incNum);

    /**
     * 动态插入一条数据
     * @param record
     * @return
     */
    int insertSelective(EforcesOrder record);

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
	PageInfo<EforcesOrder> getAllMsg(Integer page,Integer size);

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
	
}
