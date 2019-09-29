package org.kuaidi.web.springboot.controller;

import org.kuaidi.bean.domain.EforcesUsers;
import org.kuaidi.web.springboot.dubboservice.CityDubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/appLogin/")
public class AppLoginController {

	@Autowired
	private CityDubboConsumerService consumerService;
	
	@RequestMapping("login")
	@ResponseBody
	public String doLogin(EforcesUsers users, Map<String,Object> map) {
		return consumerService.doLogin(users, map);
	}

}
