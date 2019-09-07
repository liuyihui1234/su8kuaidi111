package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.dao.EforcesDistributedScanMapper;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EforcesDistributedScanImpl implements IEforcesDistributedScanService {

	@Autowired	
	private EforcesDistributedScanMapper  ScanMapper;
	
	
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
	public PageInfo<EforcesDistributedScan> getAlldistribute(Integer pageNum, Integer pageSize,Integer incid) {
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
	public int insertSelective(EforcesDistributedScan record) {
		return ScanMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(EforcesDistributedScan record) {
		return ScanMapper.updateByPrimaryKeySelective(record);
	}


}
