package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.dao.EforcesDistributedScanMapper;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0",interfaceClass=IEforcesDistributedScanService.class)
public class EforcesDistributedScanImpl implements IEforcesDistributedScanService {

	@Autowired	
	private EforcesDistributedScanMapper  ScanMapper;
	
	@Autowired
	private EforcesLogisticStrackingMapper  logisticMapper; 
	
	
	@Override
	public List<EforcesDistributedScan> selectByCreateincnumber(String number) {
		List<EforcesDistributedScan> list=ScanMapper.selectByCreateincnumber(number);
		return 	 list;

	}


	@Override
	public List<EforcesDistributedScan> selectNoByCreateincnumber(String number) {
		return  ScanMapper.selectNoByCreateincnumber(number);
	}

	@Override
	public PageInfo<EforcesDistributedScan> selectDisByPostmanId(Integer pageNum, Integer pageSize, String postmanid, List<String> number) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesDistributedScan> list = ScanMapper.selectDisByPostmanId(postmanid,number);
		final PageInfo<EforcesDistributedScan> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<EforcesDistributedScan> getAlldistribute(Integer pageNum, Integer pageSize,String incid) {
		 PageHelper.startPage(pageNum,pageSize);
		List<EforcesDistributedScan> alldistribute = ScanMapper.getAlldistribute(incid);
		final  PageInfo<EforcesDistributedScan> pageInfo = new PageInfo<>(alldistribute);
		return pageInfo;
	}

	@Override
	public EforcesDistributedScan selectById(Integer id) {
		return ScanMapper.selectByPrimaryKey(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertSelective(List<EforcesDistributedScan> record,List<EforcesLogisticStracking>  logisticStracking){
		int rst = ScanMapper.insertSelectiveList(record);
		if(rst > 0 ) {
			rst = logisticMapper.insertSelectiveList(logisticStracking);
		}
		return rst; 
	}

	@Override
	public int updateByPrimaryKeySelective(EforcesDistributedScan record) {
		return ScanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByid(List<Integer> list) {
		return ScanMapper.deleteByid(list);
	}


	@Override
	public List<EforcesDistributedScan> selectByBillNumber(String billsNum) {
		// TODO Auto-generated method stub
		return ScanMapper.selectByBillNumber(billsNum);
	}


	@Override
	public PageInfo<Map<String, Object>> getDistributedStatisticsByPage(Integer pageNum, Integer pageSize,
			String province, String city, String area,String incNum,  String startTime, String endTime) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> list = ScanMapper.getDistributedStatistics(province, city, area,incNum,  startTime, endTime) ; 			
		final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}


	@Override
	public List<Map<String, Object>> getDistributedStatisticsByList(String province, String city, String area,
			String incNum,  String startTime, String endTime) {
		// TODO Auto-generated method stub
		return ScanMapper.getDistributedStatistics(province, city, area,incNum, startTime, endTime);
	}


}
