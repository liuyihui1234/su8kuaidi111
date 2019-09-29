package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.dao.EforcesRegionMapper;
import org.kuaidi.iservice.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0",timeout=120000)
public class RegionServiceImpl implements IRegionService {

	@Autowired
	EforcesRegionMapper eforcesRegionDao;
	
	public List<EforcesRegion> selectRegionByCode(String parentCode) {
		return eforcesRegionDao.selectRegionByCode(parentCode);
	}

	public List<Map<String, Object>> selectRegionByParentCode( List<String> parentCode) {
		return eforcesRegionDao.selectByParentCode(parentCode);
	}

	@Override
	public List<EforcesRegion> selectByRegionIds(List<String> regionIds) {
		// TODO Auto-generated method stub
		return eforcesRegionDao.selectRegionByCodes(regionIds);
	}

	@Override
	public int saveRegion(EforcesRegion region) {
		// TODO Auto-generated method stub
		return eforcesRegionDao.insertSelective(region);
	}

	@Override
	public void updateRegionAll() {
		// TODO Auto-generated method stub
		List <EforcesRegion>  list = eforcesRegionDao.selectAllRegion();
	}

	@Override
	public String selectMaxCodeByParent(String code) {
		return eforcesRegionDao.selectMaxCodeByParent(code);
	}

	/**
	 * 省市区管理
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<EforcesRegion> getListMsg(Integer pageNum, Integer pageSize, String name,String parentCode) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesRegion> listResult = eforcesRegionDao.getListMsg(name, parentCode) ;
		final PageInfo<EforcesRegion> pageInfo = new PageInfo<>(listResult);
		return pageInfo;
	}

	/**
	 * 添加省市区管理
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(EforcesRegion record) {
		return eforcesRegionDao.insertSelective(record);
	}

	/**
	 * 修改省市区管理
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(EforcesRegion record) {
		return eforcesRegionDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 删除省市区管理
	 * @param code
	 * @return
	 */
	@Override
	public Integer removeUpdate(Integer[] code) {
		return eforcesRegionDao.removeUpdate(code);
	}

	/**
	 * 查询修改数据
	 * @param code
	 * @return
	 */
	@Override
	public EforcesRegion selectByPrimaryKey(String code) {
		return eforcesRegionDao.selectByPrimaryKey(code);
	}

	/**
	 * 添加时查询
	 * @param code
	 * @return
	 */
	@Override
	public EforcesRegion getBycode(String code) {
		return eforcesRegionDao.getBycode(code);
	}

	@Override
	public List<EforcesRegion> getRegionListByParentCode(String parentCode) {
		// TODO Auto-generated method stub
		return eforcesRegionDao.selectRegionByParent(parentCode);
	}

}
