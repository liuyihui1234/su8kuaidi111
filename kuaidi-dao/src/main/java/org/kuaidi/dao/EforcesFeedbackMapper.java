package org.kuaidi.dao;

import org.kuaidi.bean.domain.EforcesFeedback;

public interface EforcesFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EforcesFeedback record);

    int insertSelective(EforcesFeedback record);

    EforcesFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesFeedback record);

    int updateByPrimaryKey(EforcesFeedback record);

    /**
     * 添加
     * @param record
     * @return
     */
    int insertFeedback (EforcesFeedback record);
}