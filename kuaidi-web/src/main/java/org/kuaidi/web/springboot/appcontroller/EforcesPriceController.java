package org.kuaidi.web.springboot.appcontroller;

import java.util.List;
import org.kuaidi.bean.domain.EforcesPrice;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesPrice;
import org.kuaidi.utils.CalVolume;
import org.kuaidi.utils.TimeDayUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/app/price/")
public class EforcesPriceController {
	
	@Reference(version = "1.0.0")
	private IEforcesPrice eforcesPrice ; 
	
	/*
	 *  1：快递
	 *  2： 大件
	 *  3： 冷运  
	 */
	@RequestMapping("getPrice")
    @ResponseBody
    public ResultVo getPrice(String  fromProvinceCode, String toProvinceCode,Float weight , Float length ,
    		Float width , Float height, Integer type){
		int status = 1;
		if(type != null && type == 3) {
			status = 0 ;
		}
        List<EforcesPrice> eforcesPriceList;
        if(StringUtils.isEmpty(fromProvinceCode)) {
        	return ResultUtil.exec(false,"参数错误，请确定！",null);
        }
        if(StringUtils.isEmpty(toProvinceCode)) {
        	return ResultUtil.exec(false,"参数错误，请确定！",null);
        }
        float calWeight = 0 ; 
        if(length != null  && width != null && height != null  ) {
        	calWeight = CalVolume.calVolumeInfo(length, width, height) ; 
        }
        if(weight != null && calWeight > weight ) {
        	weight = calWeight; 
        	int num = (int)(weight* 10);
        	if(num%10  > 0) {
        		weight = (num - num % 10  + 10*1.0f)/10.0f;
        	}
        }
        try{
        	eforcesPriceList = eforcesPrice.getPriceByProvinceIds(fromProvinceCode, toProvinceCode, status);
            if (eforcesPriceList != null && !eforcesPriceList.isEmpty()){
            	float price = 0 ; 
            	if(weight > 0 ) {
            		// 计算价格
            		EforcesPrice  priceInfo = eforcesPriceList.get(0);
            		if(weight >  priceInfo.getFirstweight().floatValue() ) {
            			price = priceInfo.getFirstprice().floatValue() * priceInfo.getFirstweight().floatValue() 
            					  + priceInfo.getContinueprice().floatValue() * (weight - priceInfo.getFirstweight().floatValue());
            		}else {
            			price = priceInfo.getFirstprice().floatValue() * priceInfo.getFirstweight().floatValue()  ;
            		}
            	}
            	JSONObject data = new JSONObject();
            	data.put("price", price);
            	data.put("weight", weight);
            	String receiveTime = TimeDayUtil.getNextDay(3);
            	data.put("receiveTime",receiveTime + " 18:00:00");
            	return ResultUtil.exec(true,"查询成功！",data);
            }else{
                return ResultUtil.exec(false,"查询失败！",null);
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"计算几个异常！",e.getMessage());
        }
    }
}
