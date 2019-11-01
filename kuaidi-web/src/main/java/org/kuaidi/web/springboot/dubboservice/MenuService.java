package org.kuaidi.web.springboot.dubboservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesGroupModule;
import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.MenusUsersActionVo;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesGroupModuleService;
import org.kuaidi.iservice.IEforcesMenusService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@Component
public class MenuService {
	  	
	@Reference(version = "1.0.0")
    private IEforcesMenusService menusService;
	
	@Reference(version = "1.0.0")
    IEforcesGroupModuleService  groupMenuService; 
	
  	public PageVo<EforcesMenus> getAllMenus(QueryPageVo page) {
        try {
            PageInfo<EforcesMenus> eforcesUsers = menusService.getAll(page.getPage(),page.getLimit());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
  	
  	/*
  	 * 查询组中已经有的菜单。
  	 */
  	public PageVo<EforcesMenus> getAllGroupMenus(QueryPageVo page, Integer groupId) {
        try {
        	List<EforcesGroupModule> list = groupMenuService.selectByGroupId(groupId);
    		Map<Integer,EforcesGroupModule>  menuMap = new HashMap<Integer,EforcesGroupModule>();
    		if(list != null && list.size() > 0 ) {
    			for(int i = 0 ; i < list.size() ; i++) {
    				EforcesGroupModule  menuGroup = list.get(i);
    				if(menuGroup != null ) {
    					menuMap.put(menuGroup.getModuleid(), menuGroup);
    				}
    			}
    		}
    		PageInfo<EforcesMenus> eforcesUsers = menusService.getAll(page.getPage(),page.getLimit());
            if(eforcesUsers != null ) {
            	List<EforcesMenus>  menuList = eforcesUsers.getList();
            	for(int i = 0 ; i < menuList.size() ; i++) {
            		EforcesMenus  menu = menuList.get(i);
            		if(menuMap.containsKey(menu.getId())) {
            			menu.setChecked(true);
            		}else {
            			menu.setChecked(false);
            		}
            	}
            }
            // 根据groupId 查询对应的菜单页面
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
  	
  	
  	public PageVo<MenusUsersActionVo> getAllVo(QueryPageVo page) {
        try {
            PageInfo<MenusUsersActionVo> eforcesUsers = menusService.getAllVo(page.getPage(),page.getLimit());
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }
  	
  	public ResultVo deleteMenusByID(List<Integer> list) {
        try {
            int i = menusService.setIsDeleteById(list);
            if(i>0){
                return ResultUtil.exec(true, "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败！", null);
        }
        return ResultUtil.exec(false, "删除失败！", null);
    }
  	
  	
  	public ResultVo deleteAuthorityByID(List<Integer> list) {
        try {
            int i = menusService.setPermiIsDeleteById(list);
            if(i>0){
                return ResultUtil.exec(true, "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败！", null);
        }
        return ResultUtil.exec(false, "删除失败！", null);
    }
  	
  	/*
  	 * 事务不是这样加的。
  	 * 稍后测试一下。
  	 * */
  	@Transactional
  	public ResultVo addMenu(EforcesMenus record) {
        try {
        	Integer number=menusService.getMaxNumberByParentId(record.getParentid());
        	System.err.println(number);
        	record.setNumber(number+1);
        	menusService.newMenu(record);
        	return ResultUtil.exec(false, "添加成功！", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "添加失败！", null);
        }
    }
}
