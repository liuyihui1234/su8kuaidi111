package org.kuaidi.web.springboot.util;

import java.util.Date;

import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesUser;

public class LogisticStracking {
	/*
	 * 生成物流信息
	 * @param userInfo 用户信息
	 * @param incment 网点信息
	 * @param description  物流信息描述
	 * @param billsNum  订单号
	 * @param mark  类型   1 ： 收件交单   ， 3 ： 转运   4 ： 收件  ， 5 ： 派件
	 */
	public  static EforcesLogisticStracking createStrackingInfo(EforcesUser userInfo, 
			EforcesIncment incment, String description, String billsNum ,int mark) {
//		String description = "快件在【%s】由【%s】扫描委托【%s】派件,，派件单号【%s】";
//    	description = String.format(description, incment.getName(), userInfo.getName(),
//    			record.getNextcorpname(), record.getNextnumber());
    	// 添加物流信息。
    	EforcesLogisticStracking strackingInfo = new EforcesLogisticStracking();
		strackingInfo.setBillsnumber(billsNum);
		strackingInfo.setDescription(description);
		strackingInfo.setOperators(userInfo.getName());
		strackingInfo.setIncname(incment.getName());
		strackingInfo.setIncid(userInfo.getIncid());
		strackingInfo.setMark(3); // 转运标识
		strackingInfo.setOperationtime(new Date());
		return strackingInfo;
	} 
}
