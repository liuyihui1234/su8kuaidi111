package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

import org.kuaidi.bean.domain.EforcesPaydetai;
import org.kuaidi.dao.EforcesPaydetaiMapper;
import org.kuaidi.iservice.IEforcesPaydedaiService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class EforcesPaydedaiServiceImpl implements IEforcesPaydedaiService {

    @Autowired
    private EforcesPaydetaiMapper paydetaiMapper;

    /**
     * 修改订单状态
     * @param
     * @return
     */
    public int upderinfo(String outTradeNo,String tradeno) {
        return  paydetaiMapper.upderinfo(outTradeNo,tradeno);
    }

    /**
     *增加订单信息
     * @param paydetai
     * @return
     */
    @Override
    public int insertPayinfo(EforcesPaydetai paydetai) {
        return paydetaiMapper.insertSelective(paydetai);
    }

	@Override
	public List<EforcesPaydetai> selectBySort(EforcesPaydetai record) {
		// TODO Auto-generated method stub
//		return paydetaiMapper.selectByPrimaryKey(record);
		return paydetaiMapper.selectBySort(record) ;
	}

	@Override
	public List<EforcesPaydetai> selectByTradeno(String outTradeNo) {
		// TODO Auto-generated method stub
		return paydetaiMapper.selectByTradeno(outTradeNo);
	}

	
	
	
}
