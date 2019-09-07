package org.kuaidi.web.springboot.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;
import java.util.Map;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.bean.domain.EforcesUsers;
import org.kuaidi.iservice.IRegionService;
import org.kuaidi.iservice.UsersService;
import org.springframework.stereotype.Component;

/**    eforces_incment
 * 城市 Dubbo 服务消费者   eforces_user
 * Created by bysocket on 28/02/2017.
 */
@Component
public class CityDubboConsumerService {
    
    @Reference(version = "1.0.0")
	private UsersService usersService;
    
    @Reference(version = "1.0.0")
    private IRegionService regionService; 
    
    public String doLogin(EforcesUsers users, Map<String,Object> map) {
		System.out.println(users.getUsername());
		EforcesUsers users1 = usersService.selectUsers(users.getUsername(), users.getPassword());
		System.out.println(users1);
		if (users1 == null) {
			map.put("msg", "账号或密码错误，请重新输入！");
			return "fail";
		} else {
			map.put("msg", "登录成功！");
			JSONObject rst =  new  JSONObject();
			rst.put("success", 200);
			rst.put("msg", "登录成功！");
			JSONObject dataInfo = new JSONObject();
			dataInfo.put("name", "success");
			rst.put("dataInfo", dataInfo);
			return rst.toString();
		}
	}
    
    public void saveRegion(EforcesRegion region) {
    	regionService.saveRegion(region);
    }

}
