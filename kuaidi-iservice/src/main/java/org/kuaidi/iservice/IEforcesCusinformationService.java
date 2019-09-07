package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesCusinformation;

import com.github.pagehelper.PageInfo;

public interface IEforcesCusinformationService {
	EforcesCusinformation getById(Integer id);
	
	PageInfo<EforcesCusinformation > getAll(Integer page, Integer size);
	
	void deleteById(Integer[] id);
	
	void updateById(EforcesCusinformation record);
	
	void addRecord(EforcesCusinformation record);

}
