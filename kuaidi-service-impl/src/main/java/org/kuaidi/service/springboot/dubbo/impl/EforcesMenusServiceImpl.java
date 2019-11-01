package org.kuaidi.service.springboot.dubbo.impl;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.EforcesTreeMenus;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.domain.MenusUsersActionVo;
import org.kuaidi.dao.EforcesMenusMapper;
import org.kuaidi.iservice.IEforcesMenusService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
public class EforcesMenusServiceImpl implements IEforcesMenusService {

    @Autowired
    EforcesMenusMapper daoMapper;

	@Override
	public PageInfo<EforcesMenus> getAll(Integer page,Integer size) {
		PageHelper.startPage(page,size);
		List<EforcesMenus> EforcesMenus = daoMapper.selectAll();
		final  PageInfo<EforcesMenus> pageInfo = new PageInfo<>(EforcesMenus);
		return pageInfo;
	}

	@Override
	public List<EforcesMenus> getTextByAll() {
		return daoMapper.selectAll();
	}

	@Override
	public EforcesMenus getById(Integer id) {
		return daoMapper.selectById(id);
	}

	@Override
	public void setById(EforcesMenus record) {
		daoMapper.updateById(record);
	}

	@Override
	public int setIsDeleteById(List<Integer> id) {
		 return daoMapper.updateIsDeleteById(id);
	}

	@Override
	public PageInfo<MenusUsersActionVo> getAllVo(Integer page,Integer size) {
		PageHelper.startPage(page,size);
		List<MenusUsersActionVo> list = daoMapper.selectAllVo();
		final  PageInfo<MenusUsersActionVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public MenusUsersActionVo getVoByPermisId(Integer id) {
		return daoMapper.selectVoByPermisId(id);
	}

	@Override
	public int setPermiIsDeleteById(List<Integer> ids) {
		return daoMapper.updatePermiIsDeleteById(ids);
		
	}

	@Override
	public void newMenu(EforcesMenus record) {
		 daoMapper.insert(record);
	}

	@Override
	public void newPermis(List<MenusUsersActionVo> list) {
		daoMapper.deleteVoByMenuid(list.get(0).getMenuid());
		daoMapper.addPermis(list);
	}

	@Override
	public List<Integer> getActionIdByMenuId(Integer id) {
		return daoMapper.selectActionIdByMenuId(id);
	}

	@Override
	public List<EforcesTreeMenus> getMenuTree() {
		return daoMapper.getMenuTree();
	}

	@Override
	public List<HashMap> getAllMenuTree(String userid) {
		return daoMapper.getAllMenuTree(userid);
	}

	@Override
	public int getMaxNumberByParentId(String parentId) {
		return daoMapper.selectMaxNumberByParentId(parentId);
	}

	@Override
	public List<EforcesMenus> getMenuByParentId(String parentId) {
		// TODO Auto-generated method stub
		return daoMapper.getMenuByParentId(parentId);
	}

	@Override
	public List<EforcesMenus> getMenuByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return daoMapper.getMenuByUserId(userId);
	}

   
}
