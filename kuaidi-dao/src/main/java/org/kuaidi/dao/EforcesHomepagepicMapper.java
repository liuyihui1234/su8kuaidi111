package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesHomepagepic;

import java.util.List;

public interface EforcesHomepagepicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesHomepagepic record);

    EforcesHomepagepic selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(EforcesHomepagepic record);
    /**

     * 分组查询 eforces_homePagePic
     * @return
     */
    List<EforcesHomepagepic> selectByDivide(Integer id);

    /**
     * 后台管理查询显示轮播图
     * @return
     */
    List<EforcesHomepagepic> getListMsg();

    /**
     * 后台管理增加轮播图
     * @param record
     * @return
     */
    int insertSelective(EforcesHomepagepic record);

    /**
     * 后台管理修改轮播图
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesHomepagepic record);

    /**
     * 后台管理删除轮播图
     * @param id
     * @return
     */
    int delete (Integer[] id);
}