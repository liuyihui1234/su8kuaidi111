package org.kuaidi.iservice;

import java.util.List;

import org.kuaidi.bean.domain.EforcesPaydetai;

public interface IEforcesPaydedaiService {


    /**
     * 修改订单状态 增加流水交易号
     * @param outTradeNo
     * @param tradeno
     * @return
     */
    int upderinfo(String outTradeNo,String tradeno);

    /**
     * 增加订单信息
     * @param paydetai
     * @return
     */
    int insertPayinfo(EforcesPaydetai paydetai);
    
    List <EforcesPaydetai> selectBySort(EforcesPaydetai  record);
    
    List<EforcesPaydetai> selectByTradeno(String  outTradeNo); 
}
