package org.kuaidi.iservice;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDistributedScan;

public interface IEforcesDistributedScanService {
	/**
	 * 根据网点号进行查询未派件的数据
	 * @param number
	 * @return
	 */
	List<EforcesDistributedScan> selectByCreateincnumber(String number);

	/**
	 * 根据网点号进行查询已经派件的数据
	 */
	List<EforcesDistributedScan> selectNoByCreateincnumber(String number);

	/**
	 * 根据快递员员工编号查询待派送订单
	 * g
	 * @param postmanid
	 * @return
	 */
	PageInfo<EforcesDistributedScan> selectDisByPostmanId(Integer pageNum, Integer pageSize, String postmanid, List<String> number);

	/**
	 * 获取所有派件信息
	 * @return
	 */
	PageInfo<EforcesDistributedScan> getAlldistribute(Integer pageNum, Integer pageSize, String incid);

	/**
	 * 根据id获取派件信息
	 * @param id
	 * @return
	 */
	EforcesDistributedScan selectById(Integer id);

	int insertSelective(EforcesDistributedScan record);

	int updateByPrimaryKeySelective(EforcesDistributedScan record);

	int deleteByid(List<Integer> list);
	
	List<EforcesDistributedScan>  selectByBillNumber(String billsNum);

}
