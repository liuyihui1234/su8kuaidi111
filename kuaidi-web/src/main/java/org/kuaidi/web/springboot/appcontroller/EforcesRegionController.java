package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/app/regionInfo/")
public class EforcesRegionController {
	
	@Autowired
	private RegionService regionService;
	
	@ResponseBody
	@RequestMapping("addressResolution")
	public ResultVo addressResolution(String address) {
		try {
			JSONObject data  = regionService.addressResolution(address);
			if(data == null) {
				return ResultUtil.exec(false, "地址解析错误！", null);
			}
			if(!data.containsKey("provinceName")) {
				data.put("provinceName", "");
			}
			if(!data.containsKey("provinceCode")) {
				data.put("provinceCode", "");
			}
			if(!data.containsKey("cityName")) {
				data.put("cityName", "");
			}
			if(!data.containsKey("cityCode")) {
				data.put("cityCode", "");
			}
			if(!data.containsKey("countyName")) {
				data.put("countyName", "");
			}
			if(!data.containsKey("countyCode")) {
				data.put("countyCode", "");
			}
			if(!data.containsKey("villageName")) {
				data.put("villageName", "");
			}
			if(!data.containsKey("villageCode")) {
				data.put("villageCode", "");
			}
			if(!data.containsKey("detailAddress")) {
				data.put("detailAddress", "");
			}
			return ResultUtil.exec(true, "地址解析成功！", data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "地址解析异常！", null);
		}
	}
}
