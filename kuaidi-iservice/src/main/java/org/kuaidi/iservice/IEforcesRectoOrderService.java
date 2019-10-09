package org.kuaidi.iservice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesRectoOrder;

import com.github.pagehelper.PageInfo;

public interface IEforcesRectoOrderService {
	
	int getUserRectoOrderCount(String  userNum);
	
	EforcesRectoOrder getById(Integer id);
	
	PageInfo<EforcesRectoOrder> getAll(Integer page,Integer size,Integer incid);
	
	Integer deleteById(List<Integer> id);
	
	void addRectoOrder(EforcesRectoOrder record);
	
	void setById(EforcesRectoOrder record);
	
	List <EforcesRectoOrder> getRectoOrderByNumber( String incNum ,String Number);
	
}
