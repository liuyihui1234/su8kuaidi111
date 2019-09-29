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

@Service(version = "1.0.0",interfaceClass=IEforcesCustomerSignService.class)
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
}
