package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;
import org.kuaidi.dao.EforcesAreaDAO;
import org.kuaidi.dao.EforcesCityMapper;
import org.kuaidi.dao.EforcesProvinceMapper;
import org.kuaidi.iservice.CityDubboService;
import org.kuaidi.bean.domain.EforcesArea;
import org.kuaidi.bean.domain.EforcesCity;
import org.kuaidi.bean.domain.EforcesProvince;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0")
public class CityDubboServiceImpl implements CityDubboService {

	@Autowired
	EforcesAreaDAO eforcesAreaDao;
	
	@Autowired
	EforcesCityMapper cityDao;
	
	@Autowired
	EforcesProvinceMapper  provinceDao;
	
	@Autowired
	EforcesAreaDAO areaDao;
	
    public EforcesArea findByid(int id) {
    	EforcesArea area =  eforcesAreaDao.selectByPrimaryKey(id);
    	return area; 
    }
    
    public List<EforcesCity> getCityByCode(String code) {
		return cityDao.getCityByCode(code);
	}
    
    public List<EforcesProvince>  getProvinceByParentCode(){
    	return provinceDao.selectAll();
    }

	public List<EforcesArea> getAreaByCityCode(String cityCode) {
		// TODO Auto-generated method stub
		return areaDao.getAreaByCityCode(cityCode);
	}
}
