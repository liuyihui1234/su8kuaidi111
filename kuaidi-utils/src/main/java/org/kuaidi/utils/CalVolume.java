package org.kuaidi.utils;

public class CalVolume {
	
	public static  float calVolumeInfo(Float length , Float width , Float height) {
		float rst = 0 ; 
		rst = (length * width * height)/6000;
		return rst ; 
	}

}
