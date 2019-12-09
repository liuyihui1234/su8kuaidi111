package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesWeighingScan;
import org.kuaidi.dao.EforcesWeighingScanMapper;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class EforcesWeighingScanServiceImpl implements IEforcesWeighingScanService {

	@Autowired
	EforcesWeighingScanMapper scanDao;

	public PageInfo<EforcesWeighingScan> getAll(Integer page, Integer size,String incid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,size);
		List<EforcesWeighingScan> sentScan = scanDao.selectAll(incid);
		final  PageInfo<EforcesWeighingScan> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

	@Override
	public EforcesWeighingScan getById(Integer id) {
		return scanDao.selectByPrimaryKey(id);
	}

	@Override
	public void addWeighingScan(EforcesWeighingScan record) {
		scanDao.insertSelective(record);
	}

	@Override
	public Integer deleteById(List<Integer> list) {
		return scanDao.updateIsDeleteById(list);
	}

	@Override
	public void setById(EforcesWeighingScan record) {
		scanDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<EforcesWeighingScan> getWeightScanByParam(String incNum, String number) {
		// TODO Auto-generated method stub
		return scanDao.getWeightScanByParam(incNum, number);
	}

	@Override
	public List<Map<String, Object>> getWeightStatisticByParam(String incNum, String billsNum, String sendCity,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		List <String> billsNumList = new ArrayList<String>();
		List <String> sendCityList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(billsNum)) {
			String [] sections = billsNum.split("\\s+");
			if(sections != null && sections.length > 0 ) {
				for(int i = 0 ; i < sections.length ; i++) {
					String item = sections[i];
					if(StringUtils.isNotEmpty(item)) {
						billsNumList.add(item.trim());
					}
				}
			}
		}
		if(StringUtils.isNotEmpty(sendCity)) {
			String [] sections = sendCity.split(",");
			if(sections != null && sections.length > 0 ) {
				for(int i = 0 ; i < sections.length ; i++) {
					String item = sections[i];
					if(StringUtils.isNotEmpty(item)) {
						sendCityList.add(item.trim());
					}
				}
			}
		}
		return scanDao.getWeightStatisticByParam(incNum, billsNumList, sendCityList, startTime ,endTime);
	}


}
