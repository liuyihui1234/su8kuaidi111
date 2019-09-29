package org.kuaidi.service.springboot.dubbo.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesPrice;
import org.kuaidi.dao.EforcesPriceMapper;
import org.kuaidi.iservice.IEforcesPrice;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
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
	public PageInfo<EforcesPrice> getByPrice(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<EforcesPrice> list = eforcesPriceDao.getByPrice();
		final PageInfo<EforcesPrice> pageInfo = new PageInfo<>(list);
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

}
