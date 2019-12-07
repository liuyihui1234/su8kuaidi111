package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.dao.EforcesCustomerSignMapper;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(version = "1.0.0",interfaceClass=IEforcesCustomerSignService.class,timeout=12000)
public class EforcesCustomerSignServiceImpl implements IEforcesCustomerSignService {

    @Autowired
    EforcesCustomerSignMapper customerSignMapper;
    
    @Autowired
    EforcesLogisticStrackingMapper  strackingMapper; 

    @Override
    public PageInfo<EforcesCustomerSign> getByDeliveryUserId(Integer pageNum, Integer pageSize,String deliveryUserId) {
        PageHelper.startPage(pageNum, pageSize);
        List<EforcesCustomerSign> list = customerSignMapper.selectByDeliveryUserId(deliveryUserId);
        final PageInfo<EforcesCustomerSign> pageInfo =new PageInfo<>(list);
        return pageInfo ;
    }

    @Override
    public PageInfo<EforcesCustomerSign> getAllSign(Integer page,Integer limit,String incid) {
        PageHelper.startPage(page, limit);
        List<EforcesCustomerSign> allSign = customerSignMapper.getAllSign(incid);
        final PageInfo<EforcesCustomerSign> pageInfo = new PageInfo<>(allSign);
        return pageInfo;
    }

    @Override
    public EforcesCustomerSign selectByPrimaryKey(Integer id) {
        return customerSignMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertCustomer(EforcesCustomerSign record) {
        return customerSignMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(EforcesCustomerSign record) {
        return customerSignMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int DeleteById(List<Integer> list) {
        return customerSignMapper.DeleteById(list);
    }

    @Override
    public List<EforcesCustomerSign> selectByOperatorId(String operatorid) {
        return customerSignMapper.selectByOperatorId(operatorid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCustomerSign(EforcesCustomerSign customerSign,EforcesLogisticStracking logistic ) {
        
    	int rst =  customerSignMapper.insertSelective(customerSign);
    	if(rst > 0 ) {
    		rst = strackingMapper.insertSelective(logistic);
    	}
        return rst ; 
    }

    @Override
    public EforcesCustomerSign selectByNumber(String number) {
        return customerSignMapper.selectByNumber(number);
    }

	@Override
	public PageInfo<Map<String, Object>> webSitCustomSignByPage(Integer pageNum, Integer pageSize, String incNum,
			String province, String city, String area, String time) {
		// TODO Auto-generated method stub
		 PageHelper.startPage(pageNum, pageSize);
         List<Map<String, Object>> allSign = customerSignMapper.webSitCustomSignByParam(incNum, province, city, area, time);
         final PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(allSign);
		 return pageInfo;
	}

	@Override
	public List<Map<String, Object>> webSitCustomSignByParam(String incNum, String province, String city,
			String area, String time) {
		// TODO Auto-generated method stub
		return customerSignMapper.webSitCustomSignByParam(incNum, province, city, area, time);
	}

	@Override
	public List<Map<String, Object>> customSignByUser(String incNum, String province, String city, String area,
			Integer userId, String time) {
		// TODO Auto-generated method stub
		return customerSignMapper.customSignByUser(incNum, province, city, area , userId, time);
	}
}
