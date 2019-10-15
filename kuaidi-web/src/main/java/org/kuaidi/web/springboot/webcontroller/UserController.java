package org.kuaidi.web.springboot.webcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.UserService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
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
	@NeedUserInfo
	public ResultVo getByDepartName(HttpServletRequest request, @RequestBody String departName) {
		try {
			EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
			
			System.err.println(departName);
			List<HashMap> list = userService.getByDepartName(departName.replaceAll("\"", ""), userInfo.getIncid());
			return ResultUtil.exec(true, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);

		}
		
	}
	
	@RequestMapping("getAllUserByDepartName")
	@CrossOrigin
	public ResultVo getAllUserByDepartName(@RequestBody String departName) {
		try {
			System.err.println(departName);
			List<HashMap> list = userService.getByDepartName(departName.replaceAll("\"", ""),null);
			return ResultUtil.exec(true, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);

		}
		
	}
	


	@RequestMapping("getByThree")
	@CrossOrigin
	public ResultVo  getByThree(EforcesUser record){
		try {
			List<EforcesUser> list=userService.getByThree(record);
			return ResultUtil.exec(true, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);
		}
	}
}
