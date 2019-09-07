package org.kuaidi.dao;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesOrder;

import java.util.List;

public interface EforcesOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesOrder record);

    int insertSelective(EforcesOrder record);

    EforcesOrder selectByPrimaryKey(Integer id);

    List<EforcesOrder> selectLikeByNumber(String number);

    int updateByPrimaryKeySelective(EforcesOrder record);

    int updateByPrimaryKey(EforcesOrder record);
    
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
    List<EforcesOrder> notHadPostPackage(String incid);
    
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
    List<EforcesOrder> getAllMsg();

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
	void getOrderByNumbers(List<String> billsNumber);

    /**
     *
     * 根据多个订单号查询订单信息
     * @param list
     * @return
     */
	List<EforcesOrder> getAllNumberMsg (List<String> list);
	
}