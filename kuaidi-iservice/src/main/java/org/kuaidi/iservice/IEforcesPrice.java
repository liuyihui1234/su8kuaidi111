package org.kuaidi.iservice;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesPrice;

public interface IEforcesPrice {
	
	List<EforcesPrice>  getPriceByProvinceIds(String fromProvinceCode , String toProvinceCode, Integer status);

	/**
	 * 分页查询地区价格表
	 * @return
	 */
	PageInfo<EforcesPrice> getByPrice(Integer pageNum,Integer pageSize);

	/**
	 * 删除地区价格信息
	 * @param id
	 * @return
	 */
	Integer removeByPrice(Integer[] id);

	/**
	 * 修改地区价格信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(EforcesPrice record);

	/**
	 * 添加地区价格信息
	 * @param record
	 * @return
	 */
	int insertSelective(EforcesPrice record);
	
}
