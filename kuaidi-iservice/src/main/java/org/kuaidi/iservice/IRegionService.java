package org.kuaidi.iservice;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesRegion;

public interface IRegionService {

	/**
	 * 根据   parentCode 查询省 市 县
	 * @param parentCode
	 * @return
	 */
	List<EforcesRegion> selectRegionByCode(String parentCode);
	
	/**
	 *   查询网点信息
	 * @return
	 */
	List<Map<String, Object>> selectRegionByParentCode(List<String> parentCode) ;
	
	/*
	 * 
	 */
	List <EforcesRegion>   selectByRegionIds(List<String> regionIds);

    int saveRegion(EforcesRegion region);

    void updateRegionAll();
	/**
	 * 查询 省 做处理
	 * @return
	 */
	String selectMaxCodeByParent(String code);

	/**
	 * 省市区管理
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<EforcesRegion> getListMsg(Integer pageNum,Integer pageSize,String name, String parentCode);

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
	 * @param
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
	 * 根据父节点查询所在的地区
	 * @param  parentCode
	 * @return 
	 */
	List<EforcesRegion>  getRegionListByParentCode(String parentCode);
	
	/*
	 * 根据名字like查询
	 */
	List<EforcesRegion>  getRegionListByName(String name);
	
}
