package org.kuaidi.web.springboot.controller;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesNetsign;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.NetSignInfoService;
import org.kuaidi.web.springboot.util.AliyunOSS.AppReplaceOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Netsign/")
public class NetsignController {
    
    @Autowired
	private NetSignInfoService netSignService;

	@RequestMapping("checkRegionNetSign")
	@ResponseBody
	public ResultVo checkRegionNetSign(EforcesNetsign eforcesNetsign) {
		ResultVo rst = null;
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			rst = netSignService.selectProvinces(eforcesNetsign,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}


	@RequestMapping("saveNetSignInfo1")
	public ResultVo saveNetSignInfo1(int userId,String province, String city, String area, String areaStreet,
			String imgValue, String companyName,String identityNum, String legalPerson) {
		ResultVo rst = null ;
		try {
			String signPic  = AppReplaceOSSUtil.string2Image(imgValue);
			rst = netSignService.saveNetSignInfo(userId, province, city, area, areaStreet,
					Config.oosUrlPath+signPic,companyName,identityNum,legalPerson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	// 更新网签身份证信息
	@RequestMapping("updateNetSignIdentityPic")
	public ResultVo updateNetSignIdentityPic(Integer netSignId, String identityFontValue, String identityBackValue) {
		ResultVo rst = null;
		try {
			String identityFont  = AppReplaceOSSUtil.string2Image(identityFontValue);
			String identityBack = AppReplaceOSSUtil.string2Image(identityBackValue);
			rst = netSignService.updateNetSignIdentityPic(netSignId, Config.oosUrlPath+identityFont, Config.oosUrlPath+identityBack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * 查询我的合同pdf
	 * @param IncNumber
	 * @return
	 */
	@RequestMapping("getPath")
	public ResultVo selectgetPath(String IncNumber){
		return netSignService.getPath(IncNumber);
	}
}
