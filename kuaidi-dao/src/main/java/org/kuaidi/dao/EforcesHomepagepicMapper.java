package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesHomepagepic;

import java.util.List;

public interface EforcesHomepagepicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesHomepagepic record);

    int insertSelective(EforcesHomepagepic record);

    EforcesHomepagepic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesHomepagepic record);

    int updateByPrimaryKey(EforcesHomepagepic record);

    /**
     * 分组查询 eforces_homePagePic
     * @return
     */
    List<EforcesHomepagepic> selectByDivide(Integer id);
}