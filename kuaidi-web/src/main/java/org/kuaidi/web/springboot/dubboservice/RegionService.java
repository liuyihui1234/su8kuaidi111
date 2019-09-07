package org.kuaidi.web.springboot.dubboservice;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesArea;
import org.kuaidi.bean.domain.EforcesCity;
import org.kuaidi.bean.domain.EforcesProvince;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.CityDubboService;
import org.kuaidi.iservice.IRegionService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class RegionService {
	
	@Reference(version = "1.0.0")
    CityDubboService cityDubboService;
	
	@Reference(version = "1.0.0")
	IRegionService regionService;
	
	public ResultVo getCityByCode(String code, Map<String,Object> map) {
		try {
			List<EforcesCity> city = cityDubboService.getCityByCode(code);
			JSONArray dataList = JSONArray.fromObject(city);
			if(city != null && city.size() > 0 ) {
				return ResultUtil.exec(true, "获得城市信息成功", dataList);
			}else {
				return ResultUtil.exec(false, "获得城市信息失败", null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "获得城市信息失败", null);
		}
	}

	public ResultVo getProvince() {
		try {
			List<EforcesProvince> provinceList = cityDubboService.getProvinceByParentCode();
			
			JSONArray dataList = JSONArray.fromObject(provinceList);
			if(provinceList != null && provinceList.size() > 0 ) {
				return ResultUtil.exec(true, "获得省份信息成功", dataList);
			}else {
				return ResultUtil.exec(false, "获得省份信息失败", null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "获得省份信息失败", null);
		}
	}
	
	public ResultVo getAreaByCityCode(String cityCode) {
		try {
			List<EforcesArea> areaList = cityDubboService.getAreaByCityCode(cityCode);
			JSONArray dataList = JSONArray.fromObject(areaList);
			if(areaList != null && areaList.size() > 0 ) {
				return ResultUtil.exec(true, "获得区县信息成功", dataList);
			}else {
				return ResultUtil.exec(false, "获得区县信息失败", null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "获得区县信息失败", null);
		}
	}
	
	public ResultVo selectCityByCode(String parentCode) {
		try {
			if (StringUtils.isEmpty(parentCode)) {
				return ResultUtil.exec(false, "参数为空", null);
			} 
			
			List<EforcesRegion> citys = regionService.selectRegionByCode(parentCode);
			
			if (CollectionUtils.isEmpty(citys)) {
				return ResultUtil.exec(false, "查询信息不存在", null);
			}
			
			return ResultUtil.exec(true, "查询成功", citys);
			
		} catch (Exception e) {
			return ResultUtil.exec(false, "查询失败", null);
		}
	}
	
	/*
	 * 根据code查找对应的记录
	 */
	public ResultVo selectReginByCodes(String codes) {
		try {
			if(StringUtils.isNotEmpty(codes)) {
				List <String> codeList = new ArrayList<String>();
				String [] sections = codes.split(",");
				if(sections.length > 0 ) {
					for(int i = 0 ; i < sections.length ; i++) {
						codeList.add(sections[i]);
					}
				}
				if(codeList.size() > 0 ) {
					List<EforcesRegion>  regionList =  regionService.selectByRegionIds(codeList);
					if(regionList != null && regionList.size() > 0 ) {
						return ResultUtil.exec(true, "获得地区信息成功", regionList);
					}
				}
			}
			return ResultUtil.exec(false, "获得地区信息失败", null);
		}catch(Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "获得地区信息失败", null);
		}
	}
	
	/***
	 * * 查找省市县网点，
	 * @param parentCode  父节点的id
	 * @return
	 */
	public ResultVo selectReginByParentCode(String parentCode) {
		try {
			if(StringUtils.isNotEmpty(parentCode)) {
				List <String>parentCodeList = new ArrayList<String>();
				String [] sections = parentCode.split(",");
				if(sections.length > 0 ) {
					for(int i = 0 ; i < sections.length ; i++) {
						parentCodeList.add(sections[i]);
					}
				}
				if(parentCodeList.size() > 0 ) {
					List<Map<String,Object>> isSucc = regionService.selectRegionByParentCode(parentCodeList);
					//将出现多个的合并
					isSucc = convertRegionList(isSucc);
					if (!CollectionUtils.isEmpty(isSucc)) {
						return ResultUtil.exec(true, "查询成功", isSucc);
					}
				}
			}
			
			return ResultUtil.exec(false, "查询结果为空", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);
		}
	}
	
	public List<Map<String,Object>> convertRegionList(List<Map<String,Object>>  isSucc){
		Map<String ,Map<String,Object>> map = new HashMap<String ,Map<String,Object>>();
		for(int i = 0 ; i < isSucc.size() ; i++) {
			Map<String,Object>  regionItem = isSucc.get(i);
			if(regionItem.get("name").toString().indexOf("（") > -1) {
				String regionName = regionItem.get("name").toString();
				String name = regionName.substring(0, regionName.indexOf("（"));
				if(map.containsKey(name)) {
					Map<String,Object>  regionTmp = map.get(name);
					if(regionTmp != null ) {
						regionTmp.put("code", regionTmp.get("code") + "," + regionItem.get("code"));
					}
				}else {
					regionItem.put("name", name);
					map.put(name, regionItem);
				}
			}else {
				map.put(regionItem.get("name").toString(), regionItem);
			}
		}
		List<Map<String,Object>>  rstRegionList = new ArrayList<Map<String,Object>>();
		if(map.size() > 0 ) {
			for(Entry<String, Map<String, Object>> mapItem : map.entrySet()) {
				rstRegionList.add(mapItem.getValue());
			}
		}
		return rstRegionList;
	}

	/**
	 * 省市区管理
	 * @param
	 * @return
	 */
	public ResultVo addRegionInfo(String parentCode,String name , int bigZoneId){
			String currentMaxCode = regionService.selectMaxCodeByParent(parentCode);
			if(StringUtils.isNotEmpty(currentMaxCode)){
				String maxCode = "";
				if (StringUtils.equals(parentCode , "0")) {
					String str = currentMaxCode.substring(0, 2);
					int prexNum = Integer.parseInt(str);
					prexNum++;
					maxCode = prexNum + "0000";
				}else{
					//市和县 他们用的编号是一样的
					long prexNum = Long.parseLong(currentMaxCode);
					prexNum++;
					maxCode = prexNum + "";
				}
				EforcesRegion  regionInfo = new EforcesRegion();
				regionInfo.setBigZoneId(bigZoneId);
				regionInfo.setCode(maxCode);
				regionInfo.setName(name);
				regionInfo.setParentCode(parentCode);
				Integer rst = regionService.saveRegion(regionInfo);
				if(rst != null && rst > 0 ){
					ResultUtil.exec(true,"添加地区成功！",null);
				}else{
					ResultUtil.exec(false,"添加地区失败！",null);
				}
			}
		return ResultUtil.exec(false,"添加地区失败！",null);
	}


	/**
	 * 省市区管理
	 * @param page
	 * @return
	 */
	public PageVo getlist(QueryPageVo page){
		try {
			String parentCode = page.getInfo1();
			
			
			PageInfo<EforcesRegion> pageInfo = regionService.getListMsg(page.getPage(),page.getLimit(), parentCode);
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
		}
	}

	/**
	 * 添加省市区管理
	 * @param region
	 * @return
	 */
	public ResultVo addRegion(EforcesRegion region){
		try {
			int result = regionService.insertSelective(region);
			return ResultUtil.exec(true,"添加成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"添加失败",0);
		}
	}

	/**
	 * 修改省市区管理
	 * @param region
	 * @return
	 */
	public ResultVo updateRegion(EforcesRegion region){
		try {
			int result = regionService.updateByPrimaryKeySelective(region);
			return ResultUtil.exec(true,"修改成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"修改失败",0);
		}
	}

	/**
	 * 批量删除省市区管理
	 * @param code
	 * @return
	 */
	public ResultVo removeUpdate(@RequestBody Integer[] code){
		try {

			int result = regionService.removeUpdate(code);
			return ResultUtil.exec(true,"删除成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",0);
		}
	}


/*	*//**
	 * 单条删除省市区管理
	 * @param code
	 * @return
	 *//*
	public ResultVo removeUpdates(Integer code){
		try {
			Integer[] array = {code};
			int result = regionService.removeUpdate(array);
			return ResultUtil.exec(true,"删除成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",0);
		}
	}*/

	/**
	 * 查询修改的数据
	 * @param code
	 * @return
	 */
	public ResultVo showRegionMsg(String code){
		try {
			EforcesRegion result = regionService.selectByPrimaryKey(code);
			return ResultUtil.exec(true,"查询成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"查询失败",null);
		}
	}

}
