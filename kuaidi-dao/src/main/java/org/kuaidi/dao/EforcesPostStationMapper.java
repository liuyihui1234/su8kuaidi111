package org.kuaidi.dao;

import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesPostStation;

public interface EforcesPostStationMapper {
	/**
	 * 创建驿站
	 * @param eforcesPostStation
	 * @return
	 */
  Integer addPostStation(EforcesPostStation  eforcesPostStation);
  
  
  /**
   * 根据多个多个incNumbers查询
   * @param Numbers
   * @return
   */
  List<EforcesPostStation> selectByincNumbers(List<String> incNumbers);
  
  /**
   * @param incNumbers
   * @param longtitude 经度
   * @param latitude  维度
   * @return
   */
  List<EforcesPostStation> selectPostStationByincNumbers(Map<String,Object> map);

}