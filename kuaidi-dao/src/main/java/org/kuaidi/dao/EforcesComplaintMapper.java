package org.kuaidi.dao;
import org.kuaidi.bean.domain.EforcesComplaint;

import java.util.List;

public interface EforcesComplaintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesComplaint record);

    int insertSelective(EforcesComplaint record);

    EforcesComplaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesComplaint record);

    int updateByPrimaryKey(EforcesComplaint record);

    /**
     *
     * @param incNumber
     * @return
     */
    List<EforcesComplaint> getcomplaint(String incNumber);
}