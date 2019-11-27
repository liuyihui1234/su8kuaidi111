package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesShareProfit;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

public interface IEforcesShareProfitService {
	
	
	List<EforcesShareProfit>  getAllShareProfit();
	
	PageInfo<Map<String, Object>> getShareProfitByPage(Integer page, Integer pageSize, String fromProvince, String toProvince, Integer status);

	List<Map<String, Object>> getShareProfitByParam(String fromProvince, String toProvince, Integer status);
	
	Integer saveShareProfit(EforcesShareProfit  shareProfit);
	
	Integer updateShareProfit(EforcesShareProfit  shareProfit);

	int removeByIds(Integer[] id);

}
