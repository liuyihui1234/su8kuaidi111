package org.kuaidi.iservice;

import java.util.List;
import org.kuaidi.bean.domain.EforcesBankPayInfo;

public interface BankPayInfoService {
    public boolean insertBankPayInfo(String customername,String banknum,String banktype,String billpic, Integer signid);

    /**
     * 修改支付方式
     * @param customername
     * @param banknum
     * @param banktype
     * @param billpic
     * @param signid
     * @return
     */
    public boolean updatePay(String customername,String banknum,String banktype,String billpic, Integer signid);

    
    public List<EforcesBankPayInfo> selectBankPayInfoByNetSignId(int netSignId); 
}
