package org.kuaidi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesBankPayInfo;
public interface EforcesBankPayInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加支付方式
     * @param
     * @param banknum
     * @param
     * @param billpic
     * @param
     * @return
     */
    int insert(@Param("customername") String customername, @Param("banknum")String banknum, @Param("banktype")String banktype, @Param("billpic")String billpic, @Param("signid")Integer signid);

    /**
     * 添加支付方式
     * @param billpic
     * @return
     */
    int updatePay(@Param("customername") String customername,@Param("banknum") String banknum,@Param("banktype") String banktype,@Param("billpic") String billpic,@Param("signid")Integer signid);





    int insertSelective(EforcesBankPayInfo record);

    EforcesBankPayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesBankPayInfo record);

    int updateByPrimaryKey(EforcesBankPayInfo record);
    
    List<EforcesBankPayInfo> selectByNetSignId(Integer  signId);
//    List<EforcesBankPayInfo> selectBySort(EforcesBankPayInfo eforcesBankPayInfo);
}