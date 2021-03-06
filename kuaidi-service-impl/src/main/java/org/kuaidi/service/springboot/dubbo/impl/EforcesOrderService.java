package org.kuaidi.service.springboot.dubbo.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesOrderIdentity;
import org.kuaidi.bean.vo.OrderInfoVO;
import org.kuaidi.bean.vo.ScanSearchVO;
import org.kuaidi.dao.EforcesOrderIdentityMapper;
import org.kuaidi.dao.EforcesOrderMapper;
import org.kuaidi.iservice.IEforcesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(version = "1.0.0",interfaceClass=IEforcesOrderService.class,timeout=120000)
public class EforcesOrderService implements IEforcesOrderService {

	@Autowired
	private EforcesOrderMapper  orderDao; 
	
	@Autowired
	private EforcesOrderIdentityMapper identityDao; 

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
	@Transactional(rollbackFor = Exception.class)
	public int insertSelective(EforcesOrder record,  EforcesOrderIdentity orderIdentity) {
		int rst = orderDao.insertSelective(record);
		if(rst > 0 && orderIdentity != null && 
				(StringUtils.isNotEmpty(orderIdentity.getIdentitypic1()) ||
				 StringUtils.isNotEmpty(orderIdentity.getIdentitypic2()))) {
			rst = identityDao.insertSelective(orderIdentity);
		}
		return rst ; 
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

	@Override
	public PageInfo<Map<String, Object>> getSendBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,limit);
		List<Map<String, Object>> list = orderDao.getSendBillsByParam(scanSearch);
		final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Override
	public PageInfo<Map<String, Object>> getReceiveBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,limit);
		List<Map<String, Object>> list = orderDao.getReceiveBillsByParam(scanSearch);
		final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Map<String, Object>> getDistributedBillsByParam(Integer page, Integer limit,
			ScanSearchVO scanSearch) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,limit);
		List<Map<String, Object>> list = orderDao.getDistributedBillsByParam(scanSearch);
		final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<Map<String, Object>> getCustomerSignBillsByParam(Integer page, Integer limit,
			ScanSearchVO scanSearch) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,limit);
		List<Map<String, Object>> list = orderDao.getCustomerSignBillsByParam(scanSearch);
		final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return null;
	}

	@Override
	public PageInfo<Map<String, Object>> getWeightBillsByParam(Integer page, Integer limit, ScanSearchVO scanSearch) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,limit);
		List<Map<String, Object>> list = orderDao.getWeightBillsByParam(scanSearch);
		final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> getDayStatisticByUser(String province, String city, String area, String incNum,
			Integer userId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.getDayStatisticByUser(province, city,area, incNum, userId,startTime , endTime);
	}

	@Override
	public List<Map<String, Object>> getDayStatisticByCity(String incNum, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return  orderDao.getDayStatisticByCity(incNum,startTime ,endTime);
	}

	@Override
	public List<Map<String, Object>> GetPriceByDayToUser(String incNum, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> GetPriceByDayToCity(String incNum, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getDayToPriceByInc(String incName, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.getDayToPriceByInc(incName , startTime , endTime);
	}
	
	@Override
	public List<Map<String, Object>> getDayToPriceByCity(String incName, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.getDayToPriceByCity(incName , startTime , endTime);
	}

	@Override
	public List<Map<String, Object>> getDayToPriceByUser(String incName, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.getDayToPriceByUser(incName, startTime, endTime);
	}

	@Override
	public List<Map<String, Object>> getMonStaColl(String incName, String time) {
		// TODO Auto-generated method stub
		return orderDao.getMonStaColl(incName, time);
	}

	@Override
	public PageInfo<Map<String, Object>> getCusMonthBillByPage(Integer pageNum, Integer pageSize, Integer guestId,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		List<Map<String , Object>> list =  orderDao.getCusMonthBill(guestId, startTime, endTime);
		final PageInfo<Map<String , Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
		
	}

	@Override
	public List<Map<String, Object>> getCusMonthBillByList(Integer guestId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.getCusMonthBill(guestId, startTime, endTime);
	}

	@Override
	public List<Map<String, Object>> getYwyMonthBill(String ywy, String szy, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.getYwyMonthBill(ywy, szy, startTime, endTime);
	}

	@Override
	public List<Map<String, Object>> getLossStaColl(String incNum, String time) {
		// TODO Auto-generated method stub
		return orderDao.getLossStaColl(incNum , time);
	}

	@Override
	public PageInfo<Map<String, Object>> contrastEmployee(Integer pageNum , Integer pageSize, String province, String city, String area, String incNum,
			String startTime, String endTime) {
		// TODO Auto-generated method stub、
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list  = orderDao.contrastEmployee(province, city, area, incNum, startTime , endTime);
		final PageInfo<Map<String , Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> contrastEmployeeList(String province,
			String city, String area, String incNum, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return orderDao.contrastEmployee(province, city, area, incNum, startTime , endTime);
	}
	
}
