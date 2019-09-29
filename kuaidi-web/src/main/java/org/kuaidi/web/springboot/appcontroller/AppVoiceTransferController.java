package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.Config;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.util.FileTransJavaDemo;
import org.kuaidi.web.springboot.util.AliyunOSS.AppReplaceOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/app/voicetransfer/")
public class AppVoiceTransferController {
	
	private static final Logger logger = LoggerFactory.getLogger(AppVoiceTransferController.class);
	
	
	@RequestMapping("transferToWord")
    @ResponseBody
    public ResultVo insertFeedback(String voiceImg, String sufName) {
		String voicePath = AppReplaceOSSUtil.string2Voice(voiceImg, sufName);
		voicePath = Config.oosUrlPath + voicePath; 
		FileTransJavaDemo demo = new FileTransJavaDemo();
        // 第一步：提交录音文件识别请求，获取任务ID用于后续的识别结果轮询
        String taskId = demo.submitFileTransRequest(voicePath);
        if (taskId != null) {
        	logger.warn("录音文件识别请求成功，task_id: " + taskId);
        }else {
        	logger.warn("录音文件识别请求失败！");
            return ResultUtil.exec(false, "录音文件识别失败,请重新录音！", null);
        }
        // 第二步：根据任务ID轮询识别结果
        String result = demo.getFileTransResult(taskId);
        if (result != null) {
        	logger.info("录音文件识别结果查询成功：" + result);
        }else {
        	logger.info("录音文件识别结果查询失败！");
            return ResultUtil.exec(false, "录音文件识别失败,请重新录音！", null);
        }
        JSONObject  data = JSONObject.fromObject(result);
        JSONObject  rst = new JSONObject(); 
        if(data != null  && data.has("Sentences")) {
        	JSONArray dataArr = data.getJSONArray("Sentences");
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < dataArr.size(); i++) {
            	JSONObject dataTmp = dataArr.getJSONObject(i);
            	if(dataTmp != null && dataTmp.getString("Text") != null ) {
            		sb.append(dataTmp.getString("Text"));
            	}
            }
            rst.put("Text", sb.toString());
            return ResultUtil.exec(true, "录音文件识别成功！", rst);
        }else {
        	rst.put("Text", "");
        	return ResultUtil.exec(false, "录音文件识别失败！", rst);
        }
        
	}

}
