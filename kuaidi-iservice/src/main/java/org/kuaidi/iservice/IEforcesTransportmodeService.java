package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesTransportmode;
public interface IEforcesTransportmodeService {
    /**
     * 获取运输方式信息
     * @return
     */
    PageInfo<EforcesTransportmode> getListTran(Integer pageNum,Integer Page);

    /**
     * 删除运输方式信息
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);
    /**
     * 添加运输方式信息
     * @param record
     * @return
     */
    int insertSelective(EforcesTransportmode record);

    /**
     * 修改运输方式信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesTransportmode record);

    /**
     * 根据id查询要修改的数据
     * @param id
     * @return
     */
    EforcesTransportmode selectByPrimaryKey(Integer id);
}
