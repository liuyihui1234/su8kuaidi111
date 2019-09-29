package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesTransportedscan;

import java.util.HashMap;
import java.util.List;

public interface EforcesTransportedscanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesTransportedscan record);

    /**
            * 动态添加转运记录
     * g
     * @param record
     * @return
     */
    int insertSelective(EforcesTransportedscan record);

    /**
     * 根据网点id 查询对应的转运记录列表
     * g
     * @param incid
     * @return
             */
    List<EforcesTransportedscan> selectByIncid(Integer incid);

    /**
     * 查找所有start为0 的转运订单（转运中的订单）
     * g
     * @return
     */
    List<EforcesTransportedscan> selectState0();

    /**
     * 根据主键 查找对应的转运记录详细
     * g
     * @param id
     * @return
     */
    EforcesTransportedscan selectByPrimaryKey(Integer id);

    /**
     * 根据订单号 查找对应的转运记录详细
     * g
     * @param billsnumber
     * @return
     */
    EforcesTransportedscan selectByBillsnumber(String billsnumber);

    /**
     * 根据主键 动态修改对应的转运记录详情
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesTransportedscan record);

    int updateByPrimaryKey(EforcesTransportedscan record);

    /**
     * 把订单转态更改为（State=1）已添加到第三方平台
     * @param billsNumber
     * @return
     */
    int updateState1(List<String> billsNumber);

    /**
     * 把订单转态更改为（State=1）转运完成
     * @param billsNumber
     * @return
     */
    int updateState2(List<String> billsNumber);
}