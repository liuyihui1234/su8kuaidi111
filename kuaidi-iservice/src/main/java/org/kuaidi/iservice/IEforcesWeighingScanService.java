package org.kuaidi.iservice;


import org.kuaidi.bean.domain.EforcesWeighingScan;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

public interface IEforcesWeighingScanService {
	PageInfo<EforcesWeighingScan> getAll(Integer page,Integer size,String incid);
   
   EforcesWeighingScan   getById(Integer id);
   
   void addWeighingScan(EforcesWeighingScan record);
   
	Integer deleteById(List<Integer> list);
	
	void setById(EforcesWeighingScan record);
	
	List<EforcesWeighingScan> getWeightScanByParam(String incNum,
			 String number);
	
	List<Map<String, Object>>  getWeightStatisticByParam(String incNum , String billsNum,
			String sendCity, String startTime , String endTime);

}
 