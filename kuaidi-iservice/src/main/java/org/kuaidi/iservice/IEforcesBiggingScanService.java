package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesBaggingScan;
import java.util.List;
import com.github.pagehelper.PageInfo;

public interface IEforcesBiggingScanService {
	EforcesBaggingScan getById(Integer id);
	
	PageInfo<EforcesBaggingScan > getAll(Integer page, Integer size,String incid);
	
	int deleteById(List<Integer> ids);
	
	void updateById(EforcesBaggingScan record);
	
	void addRecord(EforcesBaggingScan record);
	
	Integer addRecordList(List<EforcesBaggingScan> baggingScan);
	
	/*
	 * 根据包的编号查询对应的打包信息
	 */
	List<EforcesBaggingScan> getBaggingScanByBagNum(String bagNumber);

}
