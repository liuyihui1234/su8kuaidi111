package org.kuaidi.iservice;

import java.util.HashMap;
import java.util.List;

import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.EforcesUser;
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

    void newPermis(Integer menuid,Integer actionid);

    List<EforcesTreeMenus> getMenuTree();

    List<HashMap> getAllMenuTree();
    
    int getMaxNumberByParentId(String parentId);

}
