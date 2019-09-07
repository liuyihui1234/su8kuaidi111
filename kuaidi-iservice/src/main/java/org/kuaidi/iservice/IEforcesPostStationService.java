package org.kuaidi.iservice;

import java.util.List;

import org.kuaidi.bean.domain.EforcesPostStation;

public interface IEforcesPostStationService {
	
	void  newPostStation(EforcesPostStation  eforcesPostStation)throws RuntimeException;
	
	
	List<EforcesPostStation> getByincNumbers(String incNumber)throws RuntimeException;
	
	
	List<EforcesPostStation> getPostStationByincNumbers(List<String> numbers, float longtitude, float latitude)throws RuntimeException;
    
}
