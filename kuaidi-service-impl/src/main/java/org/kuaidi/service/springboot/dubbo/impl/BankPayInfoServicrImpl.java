package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

import org.kuaidi.bean.domain.EforcesBankPayInfo;
import org.kuaidi.bean.domain.EforcesNetsign;
import org.kuaidi.dao.EforcesBankPayInfoMapper;
import org.kuaidi.dao.EforcesNetsignMapper;
import org.kuaidi.iservice.BankPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class BankPayInfoServicrImpl implements BankPayInfoService {

    @Autowired
    EforcesBankPayInfoMapper eforcesBankPayInfoMapper;
    
    @Autowired
    EforcesNetsignMapper  netSignDao;

    
    public boolean insertBankPayInfo(String customername,String banknum,String banktype,String billpic, Integer signid) {
       /**
                      * 更新
          payType 3： 线下支付
         */
        EforcesNetsign netSign = netSignDao.selectByPrimaryKey(signid);
        if(netSign != null ) {
        	netSign.setPaytype(3);
            netSignDao.updateByPrimaryKeySelective(netSign);
            eforcesBankPayInfoMapper.insert(customername,banknum,banktype,billpic,signid);
            return true;
        }
        return false; 
    }

    public boolean updatePay(String customername, String banknum, String banktype, String billpic, Integer signid) {
        eforcesBankPayInfoMapper.updatePay(customername, banknum, banktype, billpic, signid);
        return true;
    }

	@Override
	public List<EforcesBankPayInfo> selectBankPayInfoByNetSignId(int netSignId) {
		// TODO Auto-generated method stub
		return eforcesBankPayInfoMapper.selectByNetSignId(netSignId);
	}
}
