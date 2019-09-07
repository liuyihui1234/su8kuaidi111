package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesDictionary;

import java.util.List;

public interface EforcesDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesDictionary record);

    int updateByPrimaryKey(EforcesDictionary record);

    /**
     * 显示数据字典信息
     * @return
     */
    List<EforcesDictionary> getlist(EforcesDictionary dictionary);

    /**
     * 修改数据字典信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesDictionary record);

    /**
     * 增加数据字典信息
     * @param record
     * @return
     */
    int insertSelective(EforcesDictionary record);

    /**
     * 删除数据字典信息
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询修改数据
     * @param id
     * @return
     */
    EforcesDictionary selectByPrimaryKey(Integer id);

    /**
     * 得到所有父级
     * @return
     */
    List<EforcesDictionary> getParent();

}