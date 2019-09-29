package org.kuaidi.service.springboot.dubbo.impl.maintainance;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.iservice.IEforcesGoodsTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/10 12:03
 */

@Service(version = "1.0.0")
public class EforcesGoodsServiceImpl implements IEforcesGoodsTypeService {

    @Override
    public List   selectAll() {
        List list = new ArrayList();
       // JSONArray  data = new JSONArray();


        return list;
    }
}
