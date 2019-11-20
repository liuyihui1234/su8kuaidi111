package org.kuaidi.iservice;

import java.util.List;
import java.util.Map;
import org.kuaidi.bean.domain.EforcesIncmentProfit;

import com.github.pagehelper.PageInfo;

public interface IEforcesIncmentProfitService {
	
	Integer saveAllList(List<EforcesIncmentProfit>  incprofit);
	
	PageInfo<Map<String, Object>>  getIncmentProfitByPage(int page , int pageSize, String parentId , String incName );;

}
