package org.kuaidi.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesRegion;

public interface EforcesRegionMapper {
	
    int deleteByPrimaryKey(String code);

    int insert(EforcesRegion record);

    int updateByPrimaryKey(EforcesRegion record);
    
    List<EforcesRegion> selectRegionByCode(String parentCode);
    
    List<EforcesRegion> selectAllRegion();
    
    List<Map<String, Object>> selectByParentCode(List<String>  parentCode);
    
    List<EforcesRegion> selectRegionByCodes(List<String> codeList);

    /**
     * 查询 省 做处理
     * @return
     */
    String selectMaxCodeByParent(@Param("code") String code);

    /**
     * 查询 市 做处理
     * @return
     */
    List<EforcesRegion> selectRegionByParent(String code);

    /**
     * 省市区管理
     * @return
     */
    List<EforcesRegion> getListMsg(@Param("name") String name, @Param("parentCode")String parentCode);

    /**
     * 添加省市区管理
     * @param record
     * @return
     */
    int insertSelective(EforcesRegion record);

    /**
     * 修改省市区管理
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesRegion record);

    /**
     * 删除省市区管理
     * @param code
     * @return
     */
    Integer removeUpdate(Integer[] code);

    /**
     * 查询修改数据信息
     * @param code
     * @return
     */
    EforcesRegion selectByPrimaryKey(String code);


    /**
     * 添加时查询
     * @param code
     * @return
     */
    EforcesRegion getBycode(String code);
    
    /*
     * 根据名字，模糊查询 
     * @param code
     */
    List<EforcesRegion> getRegionListByName(String name);
    
}