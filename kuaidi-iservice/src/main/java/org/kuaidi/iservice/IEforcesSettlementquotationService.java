package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesSettlementquotation;

import com.github.pagehelper.PageInfo;

public interface IEforcesSettlementquotationService {
	
	
	void updateById(EforcesSettlementquotation record);
	
	EforcesSettlementquotation getById(Integer id);
	
	PageInfo<EforcesSettlementquotation> getAll(Integer page,Integer size,String recipientname,String destinationname );
	
	void deleteById(Integer[] id);
	
	void addRecord(EforcesSettlementquotation record);
	
	void setById(EforcesSettlementquotation record);
	
	
}
