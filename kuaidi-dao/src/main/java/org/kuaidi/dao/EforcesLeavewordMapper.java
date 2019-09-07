package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesLeaveword;

import java.util.List;

public interface EforcesLeavewordMapper {
    int insert(EforcesLeaveword record);

    int insertSelective(EforcesLeaveword record);

    /**
     * 我的留言
     * @param incNumber
     * @return
     */
    List<EforcesLeaveword> listMsg(String incNumber);
}