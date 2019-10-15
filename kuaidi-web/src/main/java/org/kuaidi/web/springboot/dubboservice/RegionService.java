package org.kuaidi.web.springboot.dubboservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IRegionService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class RegionService {
	
	@Reference(version = "1.0.0")
	IRegionService regionService;

	
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
			String name = page.getInfo1();
			String parentCode = page.getInfo2();
			PageInfo<EforcesRegion> pageInfo = regionService.getListMsg(page.getPage(),page.getLimit(),name,parentCode);
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
		}
	}
	
	/**
	 *      省市区管理
	 * @return
	 */
	public ResultVo getRegionByParentCode(String parentCode){
		try {
			List<EforcesRegion> regionList = regionService.selectRegionByCode(parentCode);
			return ResultUtil.exec(true,"查询地区信息成功！",regionList);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"查询地区信息失败！",null);
		}
	}
	
	// 将code 
	private String convertCodeToLen(String code , int toLen) {
		if(StringUtil.isEmpty(code)) {
			return code; 
		}
		if(code.length() >= toLen) {
			return code.substring(0, toLen);
		}
		while(code.length() < toLen) {
			code = "0" + code;
		}
		return code; 
	}

	/**
	 * 添加省市区管理
	 * @param region
	 * @return
	 */
	public ResultVo addRegion(EforcesRegion region){
		try {
			String parentCode = region.getParentCode();
			if(StringUtil.isEmpty(parentCode) || StringUtils.equals(parentCode,"0")){ //传的值如果是0就代表添加省
				String maxCode= regionService.selectMaxCodeByParent(parentCode);//得到最大值的code
				if(StringUtil.isNotEmpty(parentCode)) {
					String front = maxCode.substring(0,2); //取出来最大值code前两位
					int front1 = Integer.parseInt(front)+1;
					String code = front1+"0000"; //追加后缀
					//code 需要转化成规定的格式。
					code = convertCodeToLen(code, 6);
					region.setCode(code);
					region.setLevel(1);
					region.setParentCode("0");
				}
				int result = regionService.insertSelective(region);
				return ResultUtil.exec(true,"添加成功",result);
			}else{
				EforcesRegion result =regionService.getBycode(parentCode);
				if(result.getLevel() < 4){
					if(result.getLevel() == 1){ //说明添加的是市
						String maxCode= regionService.selectMaxCodeByParent(result.getCode());//得到最大值的code
						String code = "";
						if(StringUtil.isNotEmpty(maxCode)) {
							maxCode = maxCode.substring(0,4);
							int  maxCode1 = Integer.parseInt(maxCode) + 1;
							code = maxCode1+ "00" ;
						}else {
							code =  parentCode.substring(0,2) + "0100";
						}
						code = convertCodeToLen(code, 6);
						region.setCode(code);
						region.setLevel(2);
						int result1 = regionService.insertSelective(region);
						return ResultUtil.exec(true,"添加成功",result1);
					}else if(result.getLevel() == 2){//说明添加的是区县
						String maxCode= regionService.selectMaxCodeByParent(result.getCode());//得到最大值的code
						String code = "";
						if(StringUtils.isNotEmpty(maxCode)) {
							maxCode = maxCode.substring(0,6);
							int maxCode1=Integer.parseInt(maxCode) + 1 ;
							code =maxCode1 + "";
						}else {
							code =parentCode.substring(0,4) + "01";
						}
						code = convertCodeToLen(code, 6);
						region.setCode(code);
						region.setLevel(3);
						int result1 = regionService.insertSelective(region);
						return ResultUtil.exec(true,"添加成功",result1);
					}else if(result.getLevel() == 3) {
						String maxCode= regionService.selectMaxCodeByParent(result.getCode());
						String  code = "";
						if(StringUtils.isNotEmpty(maxCode) && maxCode.length() >= 8  ) {
							maxCode = maxCode.substring(0,8);
							int maxCode1=Integer.parseInt(maxCode) + 1 ;
							code =maxCode1 + "";
						}else {
							code = parentCode + "01";
						}
						code = convertCodeToLen(code, 8);
						region.setCode(code);
						region.setLevel(4);
						int result1 = regionService.insertSelective(region);
						return ResultUtil.exec(true,"添加成功",result1);
					}
				}else {
					return ResultUtil.exec(false,"参数有误不能再添加",0);
				}
			}
			return ResultUtil.exec(false,"添加失败",0);
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
			System.out.println(region.getCode());
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


    /**
	 * 单条删除省市区管理
	 * @param code
	 * @return
	 */
	/*
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
	
	public JSONObject addressResolution(String address){
		/* 
		 * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包。它包括两个类：Pattern和Matcher Pattern
	     *    一个Pattern是一个正则表达式经编译后的表现模式。 Matcher
	     *    一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
         *    首先一个Pattern实例订制了一个所用语法与PERL的类似的正则表达式经编译后的模式，然后一个Matcher实例在这个给定的Pattern实例的模式控制下进行字符串的匹配工作。
    	*/
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        
        JSONObject data = new JSONObject();
        /*
         * 假定省市的名字是正确的，按照标准查找
         **/
        if(table.size() > 0 ) {
        	Map<String,String>  map = table.get(0);
        	province = map.get("province");
        	city = map.get("city");
        	county = map.get("county");
        	village = map.get("village");
		}else {
			province = null;
			city = null;
			county = null;
			village = null;
		}
        if(address.length() < 2 ) {
			return data; 
		}
		String provinceName = address.substring(0,2);
		if(StringUtils.isEmpty(provinceName)) {
			return data;
		}
		if(province != null && province.length() > 2) {
			provinceName = province.substring(0,2);
		}
		List <EforcesRegion> regionList=  regionService.getRegionListByName(provinceName);
		if(regionList != null && regionList.size() > 0 ) {
			String regionName = "";
			String regionCode = "";
			for(int i = 0 ; i < regionList.size() ; i ++) {
				EforcesRegion  regionInfo = regionList.get(i);
				if(regionInfo != null && regionInfo.getLevel() == 1) {
					regionName = regionInfo.getName();
					regionCode = regionInfo.getCode();
					break;
				}
			}
			if(regionName.length() == 0 ){
				return data ; 
			}
			data.put("provinceName", regionName);
			data.put("provinceCode", regionCode);
			if(address.indexOf(regionName) == 0 ) {
				address = address.substring(regionName.length());
			}else {
				int  provinceNameLen = regionName.length(); 
				for(int i = provinceNameLen - 1 ; i >= 0 ; i-- ) {
					String prexName = regionName.substring(0,i);
					if(address.indexOf(prexName) > -1 ) {
						address = address.substring(i);
						if(address.length() > 0 ) {
							address = address.trim();
						}
						break;
					}
				}
			}
		}		
		if(address.length() == 0 ) {
			return data;
		}
		//城市是必须能匹配上的
		if(address.length() < 2 ) {
			return data; 
		}
		String cityName = address.substring(0,2);
		if(city != null && city.length() > 2) {
			cityName = city.substring(0,2);
		}
		regionList=  regionService.getRegionListByName(cityName);
		if(regionList != null && regionList.size() > 0 ) {
			String regionName = "";
			String regionCode = "";
			for(int i = 0 ; i < regionList.size() ; i ++) {
				EforcesRegion  regionInfo = regionList.get(i);
				if(regionInfo != null && regionInfo.getLevel() == 2 && 
						StringUtils.equals(regionInfo.getParentCode(), data.getString("provinceCode"))) {
					regionName = regionInfo.getName();
					regionCode = regionInfo.getCode();
					break;
				}
			}
			if(regionName.length() == 0 ){
				return data ; 
			}
			data.put("cityName", regionName);
			data.put("cityCode", regionCode);
			if(address.indexOf(regionName) == 0 ) {
				address = address.substring(regionName.length());
			}else {
				int  cityNameLen = regionName.length(); 
				for(int i = cityNameLen - 1 ; i >= 0 ; i-- ) {
					String prexName = regionName.substring(0,i);
					if(address.indexOf(prexName) > -1 ) {
						address = address.substring( i);
						if(address.length() > 0 ) {
							address = address.trim();
						}
						break;
					}
				}
			}
		}
		if(address!= null && address.length() > 0 ) {
			address.trim();
		}
		//判断县
		if(address.length() < 2 ) {
			return data; 
		}
		String countyName = address.substring(0,2);
		if(county != null && county.length() > 2) {
			countyName = county.substring(0,2);
		}
		regionList=  regionService.getRegionListByName(countyName);
		if(regionList != null && regionList.size() > 0 ) {
			String regionName = "";
			String regionCode = "";
			for(int i = 0 ; i < regionList.size() ; i ++) {
				EforcesRegion  regionInfo = regionList.get(i);
				if(regionInfo != null && regionInfo.getLevel() == 3 && 
						StringUtils.equals(regionInfo.getParentCode(), data.getString("cityCode"))) {
					regionName = regionInfo.getName();
					regionCode = regionInfo.getCode();
					break;
				}
			}
			if(regionName.length() == 0 ){
				return data ; 
			}
			data.put("countyName", regionName);
			data.put("countyCode", regionCode);
			if(address.indexOf(regionName) == 0 ) {
				address = address.substring(regionName.length());
			}else {
				int  countyNameLen = regionName.length(); 
				for(int i = countyNameLen - 1 ; i >= 0 ; i-- ) {
					String prexName = regionName.substring(0,i);
					if(address.indexOf(prexName) > -1 ) {
						address = address.substring(i);
						if(address.length() > 0 ) {
							address = address.trim();
						}
						break;
					}
				}
			}
		}
		//判断县
		if(address.length() < 2 ) {
			return data; 
		}
		String villageName = address.substring(0,2);
		if(village != null && village.length() > 2) {
			villageName = village.substring(0,2);
		}
		regionList=  regionService.getRegionListByName(villageName);
		if(regionList != null && regionList.size() > 0 ) {
			String regionName = "";
			String regionCode = "";
			for(int i = 0 ; i < regionList.size() ; i ++) {
				EforcesRegion  regionInfo = regionList.get(i);
				if(regionInfo != null && regionInfo.getLevel() == 4 && 
						StringUtils.equals(regionInfo.getParentCode(), data.getString("countyCode"))) {
					regionName = regionInfo.getName();
					regionCode = regionInfo.getCode();
					break;
				}
			}
			if(regionName.length() == 0 ){
				return data ; 
			}
			data.put("villageName", regionName);
			data.put("villageCode", regionCode);
			if(address.indexOf(regionName) == 0 ) {
				address = address.substring(regionName.length());
			}else {
				int  villageNameLen = regionName.length(); 
				for(int i = villageNameLen - 1 ; i >= 0 ; i-- ) {
					String prexName = regionName.substring(0,i);
					if(address.indexOf(prexName) > -1 ) {
						address = address.substring(i);
						if(address.length() > 0 ) {
							address = address.trim();
						}
						break;
					}
				}
			}
		}
		String  detailAddress = address;
		data.put("detailAddress", detailAddress);
        return data;
    }

}
