package org.kuaidi.web.springboot.controller;

import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.util.AliyunOSS.AppReplaceOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kuaidi.web.springboot.dubboservice.BankPayInfoDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bankpay/")
public class BankPayInfoController {
	
    @Autowired
    private BankPayInfoDubboService bankPayInfoDubboService;
    
    Logger logger = LoggerFactory.getLogger(BankPayInfoController.class);

    @RequestMapping("bankpayinfo")
    @ResponseBody
    ResultVo doInsertBankPayInfo(String customername,String banknum,String banktype, Integer signid, String imgValue){
        String billpic = "";
            try {
                String rst = AppReplaceOSSUtil.string2Image(imgValue);
                billpic = rst;
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	logger.debug(e.getMessage());
            e.printStackTrace();
        }
        logger.debug("添加银行上传凭据成功！");
        return bankPayInfoDubboService.doInsertBankPayInfo(customername,banknum,banktype, Config.oosUrlPath+billpic,signid);
    }

    @RequestMapping("updatePay")
    @ResponseBody
    String updatePay (String customername,String banknum,String banktype,String billpic, Integer signid, String imgValue, String imgName){
        String billpic1 = "";
        try{
        	if(StringUtils.isNotEmpty(imgValue)) {
        		String rst = AppReplaceOSSUtil.string2Image(imgValue);
                billpic1 = rst;
        	}
        } catch (Exception e){
            e.printStackTrace();
        }
        return bankPayInfoDubboService.doUpdatePay(customername, banknum, banktype, Config.oosUrlPath+billpic1, signid);
    }
}
