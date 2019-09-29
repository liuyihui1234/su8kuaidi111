package org.kuaidi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesMenus;
import org.kuaidi.bean.domain.EforcesTreeMenus;
import org.kuaidi.bean.domain.EforcesTreeMenusGroup;
import org.kuaidi.bean.domain.MenusUsersActionVo;

public interface EforcesMenusMapper {
    int deleteByPrimaryKey(Integer id);

    void deleteVoByMenuid(String menuid);

    int insert(EforcesMenus record);

    int insertSelective(EforcesMenus record);

    EforcesMenus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesMenus record);

    int updateByPrimaryKey(EforcesMenus record);
    
    List<EforcesMenus> selectAll();

    List<Integer> selectActionIdByMenuId(Integer id);

    
    EforcesMenus selectById(Integer id);
    
    Integer updateById(EforcesMenus record);
    
    Integer updateIsDeleteById(List<Integer> id);
    
    List<MenusUsersActionVo> selectAllVo();
    
    MenusUsersActionVo  selectVoByPermisId(Integer id);
    
    int updatePermiIsDeleteById(List<Integer> ids);
    
    
    int  addPermis(List<MenusUsersActionVo> list);

    List<EforcesTreeMenus> getMenuTree();
    @MapKey("id")
    List<HashMap> getAllMenuTree(String userid);
    
    Integer  selectMaxNumberByParentId(String parentId);

    List<EforcesTreeMenusGroup> getMenuTree_group(String userid);
}