package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesFeedback;

public interface IEforcesFeedbackService {
    /**
     * 添加我的反馈
     * @param record
     * @return
     */
    int insertFeedback (EforcesFeedback record);
}
