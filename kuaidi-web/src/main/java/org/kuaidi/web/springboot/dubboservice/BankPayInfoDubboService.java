package org.kuaidi.web.springboot.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;

import net.sf.json.JSONObject;

import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.BankPayInfoService;
import org.springframework.stereotype.Component;

@Component
public class BankPayInfoDubboService {
    @Reference(version = "1.0.0")
    BankPayInfoService bankPayInfoService;

    public ResultVo doInsertBankPayInfo(String customername,String banknum,String banktype,String billpic, Integer signid){
    	ResultVo rst ; 
		JSONObject data = new JSONObject();
		try {
			boolean a =  bankPayInfoService.insertBankPayInfo(customername,banknum,banktype,billpic,signid);
			data.put("ret", a ? 1 : 0 );
			if(a) {
				rst = ResultUtil.exec(a, "保存线下支付信息成功", null);
			}else {
				rst = ResultUtil.exec(a, "保存线下支付信息失败", null);
			}
			
		}catch(Exception e ) {
			rst = ResultUtil.exec(false, "保存线下支付信息失败", null);
		}
    	return rst;
    }

    public String doUpdatePay(String customername,String banknum,String banktype,String billpic, Integer signid){
        boolean result = bankPayInfoService.updatePay(customername, banknum, banktype, billpic, signid);
        if (result == true){
            return "ok";
        }
        return "false";
    }
}
