package org.kuaidi.iservice;

import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesRectoOrder;
import com.github.pagehelper.PageInfo;

public interface IEforcesRectoOrderService {
	
	int getUserRectoOrderCount(String  userNum);
	
	EforcesRectoOrder getById(Integer id);
	
	EforcesRectoOrder getByOrderNumber(String  orderNumber);
	
	PageInfo<EforcesRectoOrder> getAll(Integer page,Integer size,Integer incid);
	
	Integer deleteById(List<Integer> id);
	
	void addRectoOrder(EforcesRectoOrder record,EforcesLogisticStracking  logisticStracking,List<String> numberList);
	
	void setById(EforcesRectoOrder record);
	
	List <EforcesRectoOrder> getRectoOrderByNumber(String incNum ,List<String> Numbers);
	
	List<Map<String, Object>> getRecToListByInc(String province , String city, String area,String incNum , String startTime , String endTime);
	
	PageInfo<Map<String, Object>> getRecToListByPage(Integer pageNum , Integer pageSize , 
			String province , String city, String area , String incNum , String startTime , String endTime);
	
	/*
	 * 始发地取件统计
	 */
	List<Map<String, Object>> getRecToByRegion(String incNum , String startTime ,String endTime);
	
	/*
	 * 派件地派件统计
	 */
	List<Map<String, Object>> getDiliveryByRegion(String incNum , String startTime , String endTime);
	
}
