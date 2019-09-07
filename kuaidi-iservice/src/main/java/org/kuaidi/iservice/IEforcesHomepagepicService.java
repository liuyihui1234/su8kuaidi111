package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesHomepagepic;

import java.util.List;

public interface IEforcesHomepagepicService {

    /**
     * 升序查询首页轮播
     * @param id
     * @return
     */
    List<EforcesHomepagepic> selectByDivide(Integer id);
}
