package org.kuaidi.web.springboot.webcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesMenusService;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//用户权限管理
@RestController
@RequestMapping("/web/userpower/")
public class UserPowerController {
	
	//根据用户的id，获得用户所有的菜单信息
	@Reference(version = "1.0.0")
	private IEforcesMenusService menuService;
	
	@RequestMapping("getuserMenu")
    @CrossOrigin
    @NeedUserInfo
    public ResultVo getuserMenu(HttpServletRequest request) {
		try {
			EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
			Integer userId = userInfo.getId();
			List<EforcesMenus>  menuList =  menuService.getMenuByUserId(userId);
			if(menuList != null ) {
				// 对数据进行封装
				JSONArray  data = new JSONArray();
				//一级封装
				for(EforcesMenus  menuItem : menuList) {
					if(StringUtils.equals(menuItem.getParentid() , "-1")){
						JSONObject item = new JSONObject();
						item.put("id", menuItem.getId());
						item.put("name", menuItem.getText());
						item.put("icon", menuItem.getIconcls());
						item.put("url", menuItem.getUrl());
						data.add(item);
					}
				}
				Map<String , List<EforcesMenus>>  menuMap = 
						new HashMap<String , List<EforcesMenus>>();
				for(EforcesMenus  menuItem : menuList) {
					if(StringUtils.equals(menuItem.getLeaf(),"true")) {
						String parentId = menuItem.getParentid();
						List <EforcesMenus> subMenu = null;
						if(menuMap.containsKey(parentId)) {
							subMenu =  menuMap.get(parentId);
						}else {
							subMenu =  new ArrayList<EforcesMenus>();
						}
						subMenu.add(menuItem);
						menuMap.put(parentId, subMenu);
					}
				}
				if(data.size() > 0 ) {
					for(int i = 0 ; i < data.size() ; i++) {
						JSONObject item = data.getJSONObject(i);
						String id = item.getInt("id") + "";
						if(menuMap.containsKey(id)){
							List<EforcesMenus> list = menuMap.get(id);
							if(list != null && list.size() > 0 ) {
								JSONArray subMenus = new JSONArray();
								for(EforcesMenus menuItem : list) {
									JSONObject item1 = new JSONObject();
									item1.put("id", menuItem.getId());
									item1.put("name", menuItem.getText());
									item1.put("path", menuItem.getPath());
									item1.put("url", menuItem.getUrl());
									item1.put("auth", menuItem.getAuth());
									subMenus.add(item1);
								}
								item.put("subMenus", subMenus);
							}else {
								item.put("subMenus", new JSONObject());
							}
						}else {
							item.put("subMenus", new JSONObject());
						}
					}
				}
				return ResultUtil.exec(true, "获得用户权限成功！", data);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return ResultUtil.exec(false, "获得用户权限失败！", null); 
	}

}
