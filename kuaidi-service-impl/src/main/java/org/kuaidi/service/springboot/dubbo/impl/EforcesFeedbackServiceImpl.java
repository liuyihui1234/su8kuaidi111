package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesFeedback;
import org.kuaidi.dao.EforcesFeedbackMapper;
import org.kuaidi.iservice.IEforcesFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class EforcesFeedbackServiceImpl implements IEforcesFeedbackService {

    @Autowired
    EforcesFeedbackMapper feedbackMapper;

    /**
     * 添加我的反馈
     * @param record
     * @return
     */
    @Override
    public int insertFeedback(EforcesFeedback record) {
        return feedbackMapper.insertSelective(record);
    }
}
