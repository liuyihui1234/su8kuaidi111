package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesArea;
import org.kuaidi.bean.domain.EforcesCity;
import org.kuaidi.bean.domain.EforcesProvince;

import java.util.List;

/**
 * 城市业务 Dubbo 服务层
 * 
 * Created by bysocket on 28/02/2017.
 */
public interface CityDubboService {

    
    EforcesArea findByid(int id);
    
    List<EforcesCity> getCityByCode(String code);
    
    List<EforcesProvince>  getProvinceByParentCode();
    
    List<EforcesArea>  getAreaByCityCode(String cityCode);
}
