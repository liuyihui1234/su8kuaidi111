package org.kuaidi.iservice;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.EforcesTreeMenus;
import org.kuaidi.bean.domain.MenusUsersActionVo;

import com.github.pagehelper.PageInfo;

public interface IEforcesMenusService {
	PageInfo<EforcesMenus> getAll(Integer page,Integer size);

	List<EforcesMenus> getTextByAll();
    
    int setIsDeleteById(List<Integer> id);
    
    EforcesMenus getById(Integer id);
    
    void setById(EforcesMenus record);
    
    void newMenu(EforcesMenus record);
    
    PageInfo<MenusUsersActionVo> getAllVo(Integer page,Integer size);
    
    MenusUsersActionVo  getVoByPermisId(Integer id);

    int setPermiIsDeleteById(List<Integer> ids);

    void newPermis(List<MenusUsersActionVo>list);

    List<Integer> getActionIdByMenuId(Integer  id);

    List<EforcesTreeMenus> getMenuTree();

    List<HashMap> getAllMenuTree(String userid);
    
    Integer getMaxNumberByParentId(String parentId);
    
    List<EforcesMenus> getMenuByParentId(String parentId);
    
    List<EforcesMenus> getMenuByUserId(Integer userId);

}
