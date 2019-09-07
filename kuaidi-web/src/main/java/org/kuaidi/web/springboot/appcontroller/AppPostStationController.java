package org.kuaidi.web.springboot.appcontroller;

import java.util.ArrayList;
import java.util.List;

import org.kuaidi.bean.domain.EforcesPostStation;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesPostStationService;
import org.kuaidi.web.springboot.dubboservice.IncmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;


@RestController
@RequestMapping("/app/postStation/")
public class AppPostStationController {
	
	@Reference(version = "1.0.0")
	IEforcesPostStationService   stationService;
	
	@Autowired
	private IncmentService incmentService; 
	
	
	@RequestMapping("newPostStation")
	public ResultVo newPostStation(EforcesPostStation  eforcesPostStation) {
		try {
			stationService.newPostStation(eforcesPostStation);
			return ResultUtil.exec(true, "插入成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, e.getMessage(), null);
		}
		
	}
	
	@RequestMapping("getByincNumbers")
	public ResultVo getByincNumbers(String incNumber) {
		try {
			List<EforcesPostStation> list = stationService.getByincNumbers(incNumber);
			return ResultUtil.exec(true, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询错误！", null);
		}
	}
	
	@RequestMapping("getPostStationByincNumbers")
	public ResultVo getPostStationByincNumbers(String incNumber, float longtitude , float latitude) {
		try {
			return incmentService.findIncmentPostStationByNumbers(incNumber, longtitude, latitude);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询错误！", null);
		}
	}
	
}
