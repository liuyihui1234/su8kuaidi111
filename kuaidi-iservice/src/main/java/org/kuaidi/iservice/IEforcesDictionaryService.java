package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDictionary;
public interface IEforcesDictionaryService {

    /**
     * 显示数据字典信息
     * @return
     */
    PageInfo<EforcesDictionary> getlist(Integer pageNum,Integer pageSize,EforcesDictionary dictionary);


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
}
