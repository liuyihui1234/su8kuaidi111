package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesIncDefaultPrice;

import java.util.List;

public interface IEforcesIncDefaultPriceService {

    /**
     * 查询所有
     * @return
     */
    List<EforcesIncDefaultPrice> getList(Integer id);


    /**
     * 修改
     * @param joinprice
     * @param remark
     * @param checkRemark
     * @param id
     * @return
     */
    int updateIncDefalutPrice (String joinprice,String remark,String checkRemark,int id);
}
