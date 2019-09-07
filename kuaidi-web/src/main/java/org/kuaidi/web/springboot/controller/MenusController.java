package org.kuaidi.web.springboot.controller;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.EforcesTreeMenus;
import org.kuaidi.bean.domain.MenusUsersActionVo;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesMenusService;
import org.kuaidi.web.springboot.dubboservice.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/Menus/")
public class MenusController {
	
    @Reference(version = "1.0.0")
	IEforcesMenusService  menusService;

    @Autowired
    MenuService dubboService;
	

	
	@RequestMapping("getById")
	@CrossOrigin
	public ResultVo getById(Integer id) {
		try {
			EforcesMenus result=menusService.getById(id);
			return ResultUtil.exec(true, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);		
			}
	}
	/**
	 * 新增用户
	 * @param record
	 * @return
	 */
	@PostMapping("module")
	@CrossOrigin
	public ResultVo addMenu(EforcesMenus record) {
		try {
			dubboService.addMenu(record);
			return ResultUtil.exec(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "添加失败", null);		
			}
	}
	
	/**
	 * 修改用户
	 * @param record
	 * @return
	 */
	@PutMapping("module")
	@CrossOrigin
	public ResultVo setById(EforcesMenus record) {
		try {
			menusService.setById(record);
			return ResultUtil.exec(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "修改失败", null);		
		}
	}
	
	   /**
     * 删除用户
     * @return
     */
    @DeleteMapping("module")
    @CrossOrigin
    public ResultVo deleteUser(@RequestBody List<Integer> array) {
        ResultVo rst = null;
       rst = dubboService.deleteMenusByID(array);
        return rst;
    }
	
	/**
	 *获取全部数据
	 */
	@GetMapping("module")
    @CrossOrigin
    public PageVo getAll(QueryPageVo page) {
			PageVo rst = null;
	        rst = dubboService.getAllMenus(page);
	        return rst;
	}
	/**
	 * 获取全部权限
	 * @param page
	 * @return
	 */
	@GetMapping("authority")
	@CrossOrigin
	public PageVo getAllVo(QueryPageVo page) {
			PageVo rst = null;
	        rst=dubboService.getAllVo(page);
	        return rst;

	}
	/**
	 * 删除权限
	 * @param array
	 * @return
	 */
	@DeleteMapping("authority")
    @CrossOrigin
    public ResultVo deleteAuthority(@RequestBody List<Integer> array) {
        ResultVo rst = null;
        rst = dubboService.deleteAuthorityByID(array);
        return rst;
    }

	
	@RequestMapping("getVoByPermisId")
	@CrossOrigin
	public ResultVo getVoByPermisId(Integer id) {
		try {
			MenusUsersActionVo result=menusService.getVoByPermisId(id);
			return ResultUtil.exec(true, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);		
		}
	}


	@PostMapping("authority")
	@CrossOrigin
	public ResultVo newPermis(@RequestParam("menuid")Integer menuid,@RequestParam("actionid")Integer actionid) {
		try {
			menusService.newPermis(menuid, actionid);
			return ResultUtil.exec(true, "查询成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);		
		}
	}

	@RequestMapping("getTextByAll")
	@CrossOrigin
	public ResultVo getTextByAll() {
		try {
		List<EforcesMenus>list=menusService.getTextByAll();
			return ResultUtil.exec(true, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);
		}
	}

	


	@RequestMapping("getTreeMenu")
	@CrossOrigin
	public ResultVo getTreeMenu() {
		try {
			List<EforcesTreeMenus> menuTree = menusService.getMenuTree();
			return ResultUtil.exec(true, "查询成功", menuTree);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);
		}
	}
	@RequestMapping("getAllTreeMenu")
	@CrossOrigin
	public ResultVo getAllTreeMenu() {
		try {
			List<HashMap> allMenuTree = menusService.getAllMenuTree();
			return ResultUtil.exec(false, "查询成功", allMenuTree);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询失败", null);
		}
	}


	
}
