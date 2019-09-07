package org.kuaidi.web.springboot.webcontroller;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/user/")
public class UserController {

	@Reference(version = "1.0.0")
	UserService userService;
	
	
	@RequestMapping("getByDepartName")
	@CrossOrigin
	public ResultVo getByDepartName(@RequestBody String departName) {
		try {
			List<HashMap> list = userService.getByDepartName(departName.replaceAll("\"", ""));
			return ResultUtil.exec(true, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);

		}
		
	}
}
