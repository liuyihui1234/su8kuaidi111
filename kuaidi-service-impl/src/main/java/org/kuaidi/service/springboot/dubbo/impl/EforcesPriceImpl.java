package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesPrice;
import org.kuaidi.dao.EforcesPriceMapper;
import org.kuaidi.iservice.IEforcesPrice;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0", interfaceClass=IEforcesPrice.class,timeout=12000)
public class EforcesPriceImpl implements IEforcesPrice {
	
	@Autowired
	private EforcesPriceMapper  eforcesPriceDao;  

	@Override
	public List<EforcesPrice> getPriceByProvinceIds(String fromProvinceCode , String toProvinceCode, Integer status) {
		// TODO Auto-generated method stub
		return eforcesPriceDao.selectByProvinceCodes(fromProvinceCode, toProvinceCode, status + "");
	}

	/**
	 * 分页查询地区价格表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Map<String,Object>> getByPrice(Integer pageNum, Integer pageSize, String fromProvince , String toProvince, String status) {
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String,Object>> list = eforcesPriceDao.selectByParam(fromProvince, toProvince, status);
		final PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 修改删除状态为已删除
	 * @param id
	 * @return
	 */
	@Override
	public Integer removeByPrice(Integer[] id) {
		return eforcesPriceDao.removeByPrice(id);
	}

	/**
	 * 修改地区价格
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(EforcesPrice record) {
		return eforcesPriceDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 添加地区价格
	 * @param record
	 * @return
	 */
	@Override
	public int insertSelective(EforcesPrice record) {
		return eforcesPriceDao.insertSelective(record);
	}

	@Override
	public List<Map<String, Object>> getPriceByParam(String fromProvince, String toProvince, String status) {
		// TODO Auto-generated method stub
		return eforcesPriceDao.selectByParam(fromProvince, toProvince, status);
	}

}
