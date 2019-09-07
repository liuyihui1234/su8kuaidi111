package org.kuaidi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesSettlementquotation;

public interface EforcesSettlementquotationMapper {
    int deleteByPrimaryKey(Integer id);
    
    int updateIsDeleteById(Integer[] id);

    int insert(EforcesSettlementquotation record);

    int insertSelective(EforcesSettlementquotation record);

    EforcesSettlementquotation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesSettlementquotation record);

    int updateByPrimaryKeyWithBLOBs(EforcesSettlementquotation record);

    int updateByPrimaryKey(EforcesSettlementquotation record);
    
    List<EforcesSettlementquotation> selectAll(@Param("recipientname")String recipientname,@Param("destinationname")String destinationname);
    
    
   // EforcesSettlementquotation selectByRecipientName(@Param("recipientname")String recipientname,@Param("destinationname")String destinationname);
    
}