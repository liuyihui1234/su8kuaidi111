package org.kuaidi.iservice;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesLogisticStracking;

public interface IEforceslogisticstrackingService {
	
	List<EforcesLogisticStracking> getByBillsNumber(String billsNumber);

	/**
	 * 动态添加一天物流记录 operationTime  sql中已经修改为now()
	 * g
	 * @param record
	 * @return
	 */
	int insertLogisticSelective(EforcesLogisticStracking record);

	/**
	 * 一次性添加多条记录
	 * g
	 * @param list
	 * @return
	 */
	int insertLogList(List<EforcesLogisticStracking> list);

	/**
	 * 物流跟踪记录
	 * @param page
	 * @param size
	 * @return
	 */
	PageInfo<EforcesLogisticStracking> getListStracking(Integer page,Integer size,String billsnumber);

	/**
	 * 删除物流跟踪记录
	 * @param id
	 * @return
	 */
	Integer deleteLogisticStraking(Integer[] id);

	/**
	 * 根据多个运单编号查询
	 * @param billsNumber
	 * @return
	 */
	List<EforcesLogisticStracking> getListBillsNumber(String[] billsNumber);

	/**
	 * 根据订单编号查询对应的最后一条物流信息的创建时间
	 * g
	 * @param billsNumber
	 * @return
	 */
	Date selectMaxTime(String billsNumber);

	String  selectMaxMark(String billsNumber);
}
