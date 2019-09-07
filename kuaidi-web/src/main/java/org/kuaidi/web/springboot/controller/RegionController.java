package org.kuaidi.web.springboot.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regionInfo/")
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	@ResponseBody
	@RequestMapping("city/get")
	public ResultVo getCity(String parentCode) {
		ResultVo vo = null;
		try {
			vo = regionService.selectCityByCode(parentCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("selectRegionList")
	public ResultVo selectReginList(String parentCode) {
		ResultVo vo = null;
		try {
			if(StringUtils.isEmpty(parentCode)) {
				parentCode = "0";
			}
			vo = regionService.selectReginByParentCode(parentCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("selectRegionByCodes")
	public ResultVo selectRegionByCodes(String codes) {
		ResultVo vo = null;
		try {
			vo = regionService.selectReginByCodes(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	@RequestMapping("getRegionByCode")
	public ResultVo getCityByCode(String code) {
		ResultVo rst = null ;
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			if(StringUtils.isEmpty(code)) {
				rst = regionService.getProvince();
			}else if(code != null && code.endsWith("0000")) {
				rst = regionService.getCityByCode(code, map);
			}else {
				rst = regionService.getAreaByCityCode(code);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	@RequestMapping("addRegionInfo")
	@ResponseBody
	public ResultVo addRegion(String parentCode,String name ,int bigZoneId){
		ResultVo rst=null;
		try {
			regionService.addRegionInfo(parentCode,name,bigZoneId);
		}catch (Exception e){
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * 省市区管理
	 * @param page
	 * @return
	 */
	@GetMapping("Regioninfo")
	@ResponseBody
	@CrossOrigin
	public PageVo getlist(QueryPageVo page){
		return regionService.getlist(page);
	}


	/**
	 * 添加省市区管理
	 * @param region
	 * @return
	 */
	@PostMapping("Regioninfo")
	@ResponseBody
	@CrossOrigin
	public ResultVo addRegion(EforcesRegion region){
	return regionService.addRegion(region);
	}

	/**
	 * 批量删除省市区管理
	 * @param code
	 * @return
	 */
	@DeleteMapping("Regioninfo")
	@ResponseBody
	@CrossOrigin
	public ResultVo removeUpdateRegion(@RequestBody Integer[] code){
		return regionService.removeUpdate(code);
	}

/*	*//**
	 * 单条删除省市区管理
	 * @param code
	 * @return
	 *//*
	@DeleteMapping("Regioninfo")
	@ResponseBody
	@CrossOrigin
	public ResultVo removeUpdateRegions(Integer code){
		Integer[] array = {code};
		return regionService.removeUpdate(array);
	}*/

	/**
	 * 修改省市区管理
	 * @param region
	 * @return
	 */
	@PutMapping("Regioninfo")
	@ResponseBody
	@CrossOrigin
	public ResultVo updateRegion(EforcesRegion region){
		return regionService.updateRegion(region);
	}

/*	*//**
	 * 查询修改的数据
	 * @param code
	 * @return
	 *//*
	@RequestMapping("showRegion")
	@ResponseBody
	@CrossOrigin
	public ResultVo showRegionMsg(String code){
		return regionService.showRegionMsg(code);
	}*/

}
