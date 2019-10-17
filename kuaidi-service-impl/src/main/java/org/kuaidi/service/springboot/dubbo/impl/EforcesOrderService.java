package org.kuaidi.service.springboot.dubbo.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.vo.OrderInfoVO;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.dao.EforcesOrderMapper;
import org.kuaidi.iservice.IEforcesOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;



@Service(version = "1.0.0",timeout=120000)
public class EforcesOrderService implements IEforcesOrderService {

	@Autowired
	private EforcesOrderMapper  orderDao; 

	@Override
	public int getUserOrderCount(String  userNum) {
		// TODO Auto-generated method stub
		return orderDao.selectByUserId(userNum);
	}

	/**
	 * 已派件
	 * @param pageNum
	 * @param pageSize
	 * @param incNum
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> getHadPostPackage(Integer pageNum, Integer pageSize, String incNum) { 
		PageHelper.startPage(pageNum, pageSize);
		List<EforcesOrder> list = orderDao.getHadPostPackage(incNum);
		final PageInfo<EforcesOrder> pageInfo =new PageInfo<>(list);
		return pageInfo ;
	}

	/**
	 * 未派件
	 * @param pageNum
	 * @param pageSize
	 * @param incNum
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> getNotPostPackage(Integer pageNum, Integer pageSize,
			String userNum ,String incNum, Integer incLevel) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list =  null;
		if(incLevel == 4) {
			// 如果是街道级别的话，
			list = orderDao.notHadPostPackage1(userNum,incNum); ; 
		}else {
			list = orderDao.notHadPostPackage(userNum,incNum);
		}
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * 查看详情
	 */
	@Override
	public List<EforcesOrder> getByNumber(String number) {
		return orderDao.selectLikeByNumber(number);
	}




	/**
	 * 动态添加一条数据
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(EforcesOrder record) {
		return orderDao.insertSelective(record);
	}

	@Override
	public PageInfo<EforcesOrder> getHadFinishPackage(Integer pageNum, Integer pageSize, String incNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list = orderDao.getHadFinishPackage(incNum);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<EforcesOrder> getNotFinishPackage(Integer pageNum, Integer pageSize, String incNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list = orderDao.getNotFinishPackage(incNum);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 根据创建用户id查询
	 * @param createUserId
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> getByCreateUserId(Integer pageNum, Integer pageSize,String createUserId) {
		PageHelper.startPage(pageNum, pageSize);
		List<EforcesOrder> list = orderDao.selectByCreateUserId(createUserId);
		final PageInfo<EforcesOrder> pageInfo =new PageInfo<>(list);
		return pageInfo ;
	}

	@Override
	public PageInfo<EforcesOrder> getDeliveryByCreateUserId(Integer pageNum, Integer pageSize, String createUserId) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<EforcesOrder> list = orderDao.selectDeleveryByCreateUserId(createUserId);
		final PageInfo<EforcesOrder> pageInfo =new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 收件、根据Number订单编号查询数据添加到扫描表
	 * @param Number
	 * @return
	 */
	@Override
	public EforcesOrder getOrderMsg(String Number) {
		return orderDao.getOrderMsg(Number);
	}

	/**
	 * 获取已支付订单信息
	 * @param postmanid 发件快递员编号
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> yetPayment(String postmanid,Integer pageSize,Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list = orderDao.yetPayment(postmanid);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 获取未支付订单信息
	 * @param postmanid 发件快递员编号
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> notPayment(String postmanid, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list = orderDao.notPayment(postmanid);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 收件详情信息
	 * @param incid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> getListAddressee(String incid, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list = orderDao.getListAddressee(incid);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 寄/派件运单管理
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public PageInfo<EforcesOrder> getAllMsg(Integer page,Integer size, OrderInfoVO order) {
		PageHelper.startPage(page,size);
		List<EforcesOrder> list = orderDao.getAllMsg(order);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 删除寄/派件运单管理
	 * @param id
	 * @return
	 */
	@Override
	public Integer removeUpdate(Integer[] id) {
		return orderDao.removeUpdate(id);
	}

	@Override
	public PageInfo<EforcesOrder> getDestoryedBill(Integer pageNum, Integer pageSize, String incNum,
			List<Integer> causeIds) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesOrder> list = orderDao.getDestoryBill(incNum, causeIds);
		final PageInfo<EforcesOrder> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<EforcesOrder> getOrderByNumbers(String numbers) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(numbers)) {
			return null ; 
		}
		String [] sections = numbers.split(",");
		if(sections != null && sections.length > 0 ) {
			List <String> billsNumber = new ArrayList<String>();
			for(int i = 0 ; i < sections.length ; i++) {
				String number = sections[i];
				if(StringUtils.isNotEmpty(number)) {
					billsNumber.add(number.trim());
				}
			}
			if(billsNumber.size() > 0 ) {
				orderDao.getOrderByNumbers(billsNumber);
			}
		}
		return null;
	}

	/**
	 * 根据多个订单号查询订单信息
	 * @param list
	 * @return
	 */
	@Override
	public List<EforcesOrder> getAllNumberMsg(List<String> list) {
		return orderDao.getOrderByNumbers(list);
	}

	/**
	 * 物流跟踪详细查询
	 * @param name
	 * @return
	 */
	@Override
	public EforcesOrder getOrder(String Number) {
		return orderDao.getOrderMsg(Number);
	}

	/**
	 * 查询详细信息
	 * @param id
	 * @return
	 */
	@Override
	public EforcesOrder getByid(Integer id) {
		return orderDao.getByid(id);
	}
	
	@Override
	public List<EforcesOrder> getNumberByOpenId(String openid) {
		// TODO Auto-generated method stub
		return orderDao.getNumbersByOpenId(openid);
	}
	
	public int updateByPrimaryKeySelective(EforcesOrder record) {
		return orderDao.updateByPrimaryKeySelective(record);
	}
}
