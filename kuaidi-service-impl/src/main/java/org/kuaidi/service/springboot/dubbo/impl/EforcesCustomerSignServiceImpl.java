package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesCustomerSign;
import org.kuaidi.dao.EforcesCustomerSignMapper;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesCustomerSignServiceImpl implements IEforcesCustomerSignService {

    @Autowired
    EforcesCustomerSignMapper customerSignMapper;

    @Override
    public PageInfo<EforcesCustomerSign> getByDeliveryUserId(Integer pageNum, Integer pageSize,String deliveryUserId) {
        PageHelper.startPage(pageNum, pageSize);
        List<EforcesCustomerSign> list = customerSignMapper.selectByDeliveryUserId(deliveryUserId);
        final PageInfo<EforcesCustomerSign> pageInfo =new PageInfo<>(list);
        return pageInfo ;
    }

    @Override
    public PageInfo<EforcesCustomerSign> getAllSign(Integer page,Integer limit,Integer incid) {
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
    public List<EforcesCustomerSign> selectByOperatorId(String operatorid) {
        return customerSignMapper.selectByOperatorId(operatorid);
    }

    @Override
    public int insertCustomerSign(EforcesCustomerSign customerSign) {
        return customerSignMapper.insertSelective(customerSign);
    }

    @Override
    public EforcesCustomerSign selectByNumber(String number) {
        return customerSignMapper.selectByNumber(number);
    }
}
