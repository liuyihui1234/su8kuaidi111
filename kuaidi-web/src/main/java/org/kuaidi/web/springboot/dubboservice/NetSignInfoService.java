package org.kuaidi.web.springboot.dubboservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesDictionary;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesNetsign;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IDictionaryService;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IRegionService;
import org.kuaidi.iservice.NetSignInfo;
import org.kuaidi.iservice.UserService;
import org.kuaidi.utils.DateUtil;
import org.kuaidi.utils.OrderNumUtil;
import org.kuaidi.web.springboot.util.WordFileUtile;
//import org.kuaidi.utils.WordFileUtile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import ch.qos.logback.classic.Level;
import net.sf.json.JSONObject;

@Component
public class NetSignInfoService {
	
	@Value("${file.baseDir}")
    private String baseDir;
	
	@Value("${file.provincePath}")
    private String provincePath;
	
	@Value("${file.cityPath}")
    private String cityPath;
	
	@Value("${file.countyPath}")
    private String countyPath;
	
	@Value("${file.areaStreet}")
	private String areaStreetPath;

	@Reference(version = "1.0.0")
	private NetSignInfo  netSignService;
	
	@Reference(version = "1.0.0")
	private IRegionService  regionService;

	@Reference(version = "1.0.0")
	private IEforcesIncmentService incmentService;

	@Reference(version = "1.0.0")
	private UserService  userService;
	
	@Reference(version = "1.0.0")
	private IDictionaryService  dictionaryService; 

	/*
	 * 先保存用户信息，再保存网签信息。
	 */
	public ResultVo saveNetSignInfo(int userId , String province , String city, String area ,String areaStreet,
			String signPic, String companyName,String identityNum, String legalPerson) {
		ResultVo rst = null ; 
		EforcesNetsign  netSignInfo = new EforcesNetsign(); 
		String netWorkAreaCode = "";
		int regionLevel = 0 ; 
		// 如果公司名字为空的时候，公司名字处默认为是企业法人。
		if(StringUtils.isEmpty(companyName)) {
			companyName = legalPerson;
		}
		List <String> areaNameIds = new  ArrayList<String>();
		if(StringUtils.isNotEmpty(areaStreet)) {
		   netSignInfo.setNetwork(areaStreet);
		   netWorkAreaCode = areaStreet;
		   regionLevel = 4;
		   areaNameIds.add(areaStreet);
		}
		if(StringUtils.isNotEmpty(area)) {
		   netSignInfo.setCounty(area);
		   if(netWorkAreaCode.length() == 0 ) {
		    netWorkAreaCode = area;
		    regionLevel = 3;
		   }
		   areaNameIds.add(area);
		}
		if(StringUtils.isNotEmpty(city)) {
		   netSignInfo.setCity(city);
		   if(netWorkAreaCode.length() == 0 ) {
			    netWorkAreaCode = city;
			    regionLevel = 2;
		   }
		   areaNameIds.add(city);
		}
		
		if(StringUtils.isNotEmpty(province)) {
		   netSignInfo.setProvince(province);
		   if(netWorkAreaCode.length() == 0 ) {
			    netWorkAreaCode = province;
			    regionLevel = 1;
		   }
		   areaNameIds.add(province);
		}
		
		if(netWorkAreaCode.length() > 0 ) {
			netSignInfo.setNetworkareacode(netWorkAreaCode);
		}
		if(regionLevel > 0 ) {
			netSignInfo.setSigntype(regionLevel);
		}
		
		if(StringUtils.isNotEmpty(signPic)) {
			netSignInfo.setSignpic(signPic);
		}
		if(StringUtils.isNotEmpty(identityNum)) {
			netSignInfo.setIdentitynum(identityNum);
		}
		if(StringUtils.isNotEmpty(companyName)) {
			netSignInfo.setCompanyname(companyName);
		}
		if(StringUtils.isNotEmpty(legalPerson)) {
			netSignInfo.setLegalperson(legalPerson);
		}
		netSignInfo.setContractstarttime(new Date());
		netSignInfo.setContractendtime(DateUtil.addYears(2));
		netSignInfo.setUserid(userId);
		String  srcPath = baseDir ;
		if(regionLevel == 4) {
			srcPath += areaStreetPath;
		}else if(regionLevel == 3 ) {
			srcPath += countyPath;
		}else if(regionLevel == 2 ) {
			srcPath += cityPath;
		}else if(regionLevel == 1 ) {
			srcPath += provincePath;
		}
		String contractPath = OrderNumUtil.getOrderNum(); 
		String zoneName = "";
		//保存合同信息
		saveHeTongToFiles(companyName, identityNum, legalPerson, areaNameIds, srcPath, contractPath, zoneName);
		EforcesUser userInfo = userService.selectUserById(userId);
		if(userInfo != null ) {
			String icmNum = saveIncmentInfo(province, city, area, areaStreet, netWorkAreaCode, regionLevel, userInfo.getAddress());
			userInfo.setIncid(icmNum);
			userInfo.setIncnumber(icmNum);
			userService.updateUserBySign(userInfo,regionLevel);
			netSignInfo.setIncnumber(icmNum);
			netSignInfo.setContractpath(contractPath + ".docx");
			try {
				JSONObject data = new JSONObject();
				Integer signNetId = netSignService.saveNetSignInfo(netSignInfo);
				data.put("signNetId", signNetId);
				if(signNetId != null &&  signNetId > 0 ) {
					rst =   ResultUtil.exec(true, "保存网签数据成功！", data);
				}else {
					rst =   ResultUtil.exec(false, "保存网签数据失败！", null);
				}
			}catch(Exception e) {
				rst =   ResultUtil.exec(false, "保存网签数据失败！", null);
				e.printStackTrace();
			}
		}
		return rst;
	}

	/*
	 * 保存网点信息。
	 */
	private String saveIncmentInfo(String province, String city, String area, String areaStreet, String netWorkAreaCode,
			int regionLevel, String address) {
		String name = "" ;
		String mnemonic =  "";
		int  bigZoneId =  0 ;
		String parentId = "";
		String parentName = "";
		EforcesRegion  regionInfo = regionService.selectByPrimaryKey(netWorkAreaCode);
		if(regionInfo != null && StringUtils.equals(regionInfo.getParentCode(),"0")) {
			name = regionInfo.getName();
			mnemonic = regionInfo.getName();
			bigZoneId = regionInfo.getBigZoneId();
			EforcesDictionary dictionaryInfo = dictionaryService.getDictionaryById(bigZoneId);
			if(dictionaryInfo != null) {
				parentId = dictionaryInfo.getUsergroup();
				parentName = dictionaryInfo.getName();
			}
		}else if(regionInfo != null && StringUtils.isNotEmpty(regionInfo.getParentCode())){
			name = regionInfo.getName();
			mnemonic = regionInfo.getName();
			regionInfo = regionService.selectByPrimaryKey(regionInfo.getParentCode());
			if(regionInfo != null) {
				bigZoneId = regionInfo.getBigZoneId();
				parentId = regionInfo.getCode() + "00";
				parentName = regionInfo.getName();
			}
		}
		String icmNum = saveIncInfo(province,city,area,areaStreet,bigZoneId, name ,mnemonic, regionLevel,
				address,parentId , parentName);
		return icmNum;
	}

	private void saveHeTongToFiles(String companyName, String identityNum, String legalPerson, List<String> areaNameIds,
			String srcPath, String contractPath, String zoneName) {
		List<EforcesRegion> regionList = null ;
		/*
		 * 省市县区关联起来
		 * */
		if(areaNameIds.size() > 0 ) {
			regionList = regionService.selectByRegionIds(areaNameIds);
			if(regionList != null && regionList.size() > 0 ){
				String parentCode = "0" ; 
				while(true) {
					String newParentCode = parentCode;
					for (int i = 0; i < regionList.size(); i++) {
						EforcesRegion region = regionList.get(i);
						if (region != null && StringUtils.equals(region.getParentCode(), parentCode)) {
							zoneName = zoneName + region.getName() + "\t";
							parentCode = region.getCode();
							break;
						}
					}
					if (StringUtils.equals(parentCode, newParentCode)) {
						break;
					}
				}
			}
		}
		try {
			WordFileUtile.readFileByName(srcPath,baseDir ,contractPath,
					companyName,zoneName,identityNum, legalPerson);
//			WordFileUtile.convertDocxToPDF(new File(baseDir + contractPath + ".docx"), baseDir + contractPath + ".pdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 增加网点信息
	 * @param province 省
	 * @param city 市
	 * @param area 县
	 * @param bigZoneId 华北:55 华西:58 华南:57 华东:56
	 * @param Name 网点公司名称 前缀快8速递 后缀公司
	 * @param mnemonic 公司名称
	 * @param
	 * @return  返回对应的number
	 */
	public String  saveIncInfo(String province , String city , String area, String areaStreet ,int bigZoneId , 
			String Name, String mnemonic, int regionLevel,String address,String parentId, String parentName){
		EforcesIncment incment = new EforcesIncment();
		String lagearea="";
		if(bigZoneId==55){
			lagearea="华北地区";
		}else if(bigZoneId==58){
			lagearea="华西地区";
		}else if(bigZoneId==57){
			lagearea="华南地区";
		}else if(bigZoneId==56){
			lagearea="华东地区";
		}
		String Provinces="";
		if(areaStreet != null && !StringUtils.equals(areaStreet, "")) {
			Provinces = areaStreet.trim();
		}else if(area != null && !area.equals("")){
			Provinces = area + "00";
		}else if(city != null &&!city.equals("")){
			Provinces = city + "00";
		}else if(province != null && !province.equals("")){
			Provinces = province + "00";
		}
		incment = incmentService.selectByNumber(Provinces);
		if(incment  == null ) {
			incment = new EforcesIncment();
		}else {
			return incment.getNumber();
		}
		String type="";
		if(regionLevel==1){
			type="中心";
		} else if(regionLevel==2){
			type="市中心";
		} else if (regionLevel==3){
			type="分站";
		}else if(regionLevel==4){
			type="网点";
		}
		Name = Name.replace("\t","");
		Name = "快8速递" + Name +"分公司";
		incment.setProvince(province);
		incment.setCity(city);
		incment.setArea(area);
		incment.setAreastreet(areaStreet);
		incment.setLagearea(lagearea);
		incment.setName(Name);
		incment.setMnemonic(mnemonic);
		incment.setNumber(Provinces);
		incment.setMoneytype("人民币");
		incment.setIsfinancecenter(0);
		incment.setIstopay(0);
		incment.setIstransfer(0);
		incment.setType(type);
		incment.setLevel(regionLevel);
		incment.setAddress(address);
		incment.setParentid(parentId);
		incment.setParentname(parentName);
		int  icmId = incmentService.insetIncment(incment);
		if(icmId <= 0 ) {
			return "";
		}
		return Provinces;
	}

	/***
	 * @param netSignId  	网签记录的id
	 * @param identityFont   身份证正面的图片
	 * @param identityBack	 身份证反面的图片
	 * @return
	 */
	public ResultVo  updateNetSignIdentityPic(Integer netSignId , String identityFont , String identityBack) {
		ResultVo  rst; 
		try {
			int updateCount =  netSignService.updateNetSignIdentityPics(netSignId, identityFont, identityBack);
			if(updateCount > 0 ) {
				rst = ResultUtil.exec(true, "更新身份证信息成功！", null);
			}else {
				rst = ResultUtil.exec(false, "更新身份证信息失败！", null);
			}
		}catch(Exception e) {
			rst = ResultUtil.exec(false, "保存身份证信息失败", null);
		}
		return rst;
	}  
	
	public ResultVo selectProvinces(EforcesNetsign eforcesNetsign, Map<String,Object>map){
        ResultVo rst ; 
        try{
            EforcesNetsign eforcesNetsign1 =  netSignService.selectProvinces(eforcesNetsign);
            JSONObject data = new JSONObject();
            if(eforcesNetsign1==null){
                data.put("hasSigned",false);
            }else{
                data.put("hasSigned",true);
            }
            rst = ResultUtil.exec(true, "检查是否已经网签过成功", data);
       
        }catch(Exception e){
            rst = ResultUtil.exec(false, "检查是否已经网签过失败", null);
	}
        return rst;
    }

	public PageVo doSelectNetsignSort(Integer pageNum, Integer rows, EforcesNetsign record){
        try {
        	PageInfo<EforcesNetsign> pageInfo = netSignService.selectNetsignSort(pageNum, rows,record);
            return ResultUtil.exec(pageInfo.getPageNum(), pageInfo.getPageSize(),
            		pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            return ResultUtil.exec(1, 10, 0,null);
        }
    }

    public String doSelectNetsignById(Integer id){
        JSONObject rst = new JSONObject();
        rst.put("code",200);
        rst.put("msg","操作成功");
        try{
        EforcesNetsign eforcesNetsign = netSignService.selectNetsignById(id);
        JSONObject data = new JSONObject();
        if(eforcesNetsign==null){
            data.put("hasSigned",false);
        }else{
            data.put("hasSigned",true);
        }
        rst.put("data",data);
        }catch(Exception e){
            rst.put("code",500);
            rst.put("msg","操作失败！");
        }
        return rst.toString();
    }


    public ResultVo douUpdateNetsignSortById(EforcesNetsign record){
//        EforcesNetsign ef = netSignService.selectNetsignById(record.getId());
//        try {
//            if(ef == null){
//                return ResultUtil.exec(false,"传入参数有误",null);
//            }else {
//                Integer a = netSignService.updateNetsignSort(record);
//                if(a > 0){
//                    return ResultUtil.exec(true,"数据修改成功",null);
//                }
//                return ResultUtil.exec(false,"修改失败",null);
//            }
//
//        } catch (Exception e) {
//            return ResultUtil.exec(false,"操作失败",null);
//        }
		Integer a = netSignService.updateNetsignSort(record);
		if(a > 0){
			return ResultUtil.exec(true,"ok",null);
		}
		return ResultUtil.exec(false,"cuowu",null);
    }

    public ResultVo doSelectBySort(EforcesNetsign record){
		try {
//			List list =netSignService.selectSort(record);
			EforcesNetsign eforcesNetsign = netSignService.selectSort(record);
			return ResultUtil.exec(true,"ok",eforcesNetsign);
		} catch (Exception e) {
			return ResultUtil.exec(false,"操作失败",null);
		}
	}

	/**
	 * 查询我的合同pdf
	 * @param incNumber
	 * @return
	 */
	public ResultVo getPath(String incNumber){
		if(StringUtils.isEmpty(incNumber)){
			return ResultUtil.exec(false,"参数错误！",null);
		}
		List<EforcesNetsign> NetsignList = netSignService.selectPath(incNumber);
		if(NetsignList != null && NetsignList.size()>0){
			return ResultUtil.exec(true,"成功",NetsignList.get(0));
		}else{
			return ResultUtil.exec(false,"没有找到对应的数据",null);
		}
	}
	
	public ResultVo deleteByIds(String ids) {
		if(ids == null || ids.length() == 0 ) {
			return ResultUtil.exec(false, "参数错误", null);
		}
		try {
			Integer num = netSignService.delNetSignByIds(ids);
			if(num > 0 ) {
				return ResultUtil.exec(true, "删除数据成功", num);
			}
		}catch(Exception e ) {
			e.printStackTrace();
			return ResultUtil.exec(false, "操作异常！", null);
		}
		return ResultUtil.exec(false, "没有删除的数据", null);
	}

}
