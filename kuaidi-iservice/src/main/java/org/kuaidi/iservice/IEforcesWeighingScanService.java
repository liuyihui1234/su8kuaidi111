package org.kuaidi.iservice;


import org.kuaidi.bean.domain.EforcesWeighingScan;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEforcesWeighingScanService {
	PageInfo<EforcesWeighingScan> getAll(Integer page,Integer size,Integer incid);
   
   EforcesWeighingScan   getById(Integer id);
   
   void addWeighingScan(EforcesWeighingScan record);
   
	Integer deleteById(List<Integer> list);
	
	void setById(EforcesWeighingScan record);

}
 