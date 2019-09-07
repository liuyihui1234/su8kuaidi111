package org.kuaidi.service.springboot.dubbo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesPostStation;
import org.kuaidi.dao.EforcesPostStationMapper;
import org.kuaidi.iservice.IEforcesPostStationService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EforcesPostStationServiceImpl implements  IEforcesPostStationService{

	@Autowired
	EforcesPostStationMapper daoMapper;

	@Override
	public void newPostStation(EforcesPostStation eforcesPostStation) throws RuntimeException {
		eforcesPostStation.setCrtTime(new Date());
		Integer rows=daoMapper.addPostStation(eforcesPostStation);
		if(rows!=1) {
			throw new RuntimeException("插入失败！出现未知错误，请联系管理员");
		}
	}

	@Override
	public List<EforcesPostStation> getByincNumbers(String incNumber)throws RuntimeException {
		if(incNumber==null) {
			throw new RuntimeException("查询失败!传入非法参数");
		}
		String[] str=incNumber.split(",");
		List<String> Numbers=new ArrayList<String>();
		for(String st:str) {
			Numbers.add(st);
		}
		List<EforcesPostStation> result=daoMapper.selectByincNumbers(Numbers);
		return result;
	}

	@Override
	public List<EforcesPostStation> getPostStationByincNumbers(List<String> numbers, float longtitude, float latitude) throws RuntimeException {
		// TODO Auto-generated method stub
		Map<String , Object> map = new HashMap<String,Object>();
		
		map.put("list", numbers);
		map.put("longtitude", longtitude);
		map.put("latitude", latitude);
		
		List<EforcesPostStation> result = daoMapper.selectPostStationByincNumbers(map);
		return result;
	}
}
