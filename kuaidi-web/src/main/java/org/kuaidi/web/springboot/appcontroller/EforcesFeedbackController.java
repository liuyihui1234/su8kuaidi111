package org.kuaidi.web.springboot.appcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesFeedback;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesFeedbackService;
import org.kuaidi.web.springboot.util.AliyunOSS.AppReplaceOSSUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/feedBack/")
public class EforcesFeedbackController {

/*    @Value("${file.baseDir}")
    private String baseDir;*/

    @Reference(version = "1.0.0")
    IEforcesFeedbackService feedbackService;

    /**
     * 反馈
     * @param feedback
     * @return
     */
    @RequestMapping("insertBack")
    @ResponseBody
    public ResultVo insertFeedback(EforcesFeedback feedback){
        int verif;
        if(feedback!=null){
            if(StringUtils.isNotEmpty(feedback.getImgpath1())){
                String img1 = AppReplaceOSSUtil.string2Image(feedback.getImgpath1());
                feedback.setImgpath1(Config.oosUrlPath+img1);
            }
            if(StringUtils.isNotEmpty(feedback.getImgpath2())){
                String img2 = AppReplaceOSSUtil.string2Image(feedback.getImgpath2());
                feedback.setImgpath2(Config.oosUrlPath+img2);
            }
            if(StringUtils.isNotEmpty(feedback.getImgpath3())){
                String img3 = AppReplaceOSSUtil.string2Image(feedback.getImgpath3());
                feedback.setImgpath3(Config.oosUrlPath+img3);
            }
            if(StringUtils.isNotEmpty(feedback.getImgpath4())){
                String img4 = AppReplaceOSSUtil.string2Image(feedback.getImgpath4());
                feedback.setImgpath4(Config.oosUrlPath+img4);
            }
            Date neDate = new Date();
            feedback.setCrttime(neDate);
            verif =  feedbackService.insertFeedback(feedback);
            return ResultUtil.exec(true,"添加成功",verif);
        }else{
            return ResultUtil.exec(false,"增加失败",null);
        }
    }
}
