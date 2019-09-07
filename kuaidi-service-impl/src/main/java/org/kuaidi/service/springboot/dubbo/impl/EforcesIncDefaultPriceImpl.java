package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesIncDefaultPrice;
import org.kuaidi.dao.EforcesIncDefaultPriceMapper;
import org.kuaidi.iservice.IEforcesIncDefaultPriceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesIncDefaultPriceImpl implements IEforcesIncDefaultPriceService {
    @Autowired
    EforcesIncDefaultPriceMapper priceMapper;

    /**
     * 查询
     * @return
     */
    @Override
    public List<EforcesIncDefaultPrice> getList(Integer id) {
        return priceMapper.getList(id);
    }

    /**
     * 更新
     * @param joinprice
     * @param remark
     * @param checkRemark
     * @param id
     * @return
     */
    @Override
    public int updateIncDefalutPrice(String joinprice, String remark, String checkRemark, int id) {
            return priceMapper.updateIncDefalutPrice(joinprice,remark,checkRemark,id);
    }
}
