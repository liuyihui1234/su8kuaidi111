package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import org.kuaidi.bean.domain.EforcesSettlementquotation;
import org.kuaidi.dao.EforcesSettlementquotationMapper;
import org.kuaidi.iservice.IEforcesSettlementquotationService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesSettlementquotationService implements IEforcesSettlementquotationService {

	@Autowired
	private EforcesSettlementquotationMapper daoMapper;
	
	@Override
	public PageInfo<EforcesSettlementquotation> getAll(Integer page,Integer size,String recipientname,String destinationname) {
		//PageHelper.startPage(page,size);
		List<EforcesSettlementquotation>list=daoMapper.selectAll(recipientname,destinationname);
		System.err.println(list);
		final  PageInfo<EforcesSettlementquotation> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}

	@Override
	public EforcesSettlementquotation getById(Integer id) {
		return daoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteById(Integer[] id) {
		daoMapper.updateIsDeleteById(id);
	}

	@Override
	public void addRecord(EforcesSettlementquotation record) {
		daoMapper.insertSelective(record);
	}

	@Override
	public void setById(EforcesSettlementquotation record) {
		daoMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void updateById(EforcesSettlementquotation record) {
		// TODO Auto-generated method stub
		
	}

	

}
