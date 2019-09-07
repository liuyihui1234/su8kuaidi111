package org.kuaidi.dao;

import java.util.List;

import org.kuaidi.bean.domain.EforcesDefaultBankInfo;

public interface EforcesDefaultBankInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesDefaultBankInfo record);

    int insertSelective(EforcesDefaultBankInfo record);

    EforcesDefaultBankInfo selectByPrimaryKey(Integer id);
    
    List<EforcesDefaultBankInfo>  selectAllBankInfo();

    int updateByPrimaryKeySelective(EforcesDefaultBankInfo record);

    int updateByPrimaryKey(EforcesDefaultBankInfo record);
}