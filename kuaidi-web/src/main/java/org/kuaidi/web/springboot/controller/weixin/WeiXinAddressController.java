package org.kuaidi.web.springboot.controller.weixin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kuaidi.bean.domain.EforcesCustomerAddress;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesAddressService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

/*
 * 添加编辑地址信息
 */
@RestController
@RequestMapping("/weixin/address/")
public class WeiXinAddressController {
	
	@Reference(version= "1.0.0")
	private IEforcesAddressService addressService; 
	
	@RequestMapping("addAddress")
    @CrossOrigin
    public ResultVo addAddress(EforcesCustomerAddress  address) {
		if(address == null ) {
			return  ResultUtil.exec(false, "参数错误！", null);
		}
		address.setChannelsource("WEIXIN");
		try {
			int rst = addressService.saveAddress(address);
			if(rst > 0 ) {
				return  ResultUtil.exec(true, "添加地址成功！", null);
			}
			return  ResultUtil.exec(false, "添加地址错误！", null);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResultUtil.exec(false, "添加地址错误！", null);
			
		}
	}
	
	@RequestMapping("modifyAddress")
    @CrossOrigin
    public ResultVo modifyAddress(EforcesCustomerAddress  address) {
		if(address == null ) {
			return  ResultUtil.exec(false, "参数错误！", null);
		}
		try {
			int rst = addressService.saveAddress(address);
			if(rst > 0 ) {
				return  ResultUtil.exec(true, "修改地址成功！", null);
			}
			return  ResultUtil.exec(false, "修改地址错误！", null);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResultUtil.exec(false, "修改地址错误！", null);
			
		}
	}
	
	@RequestMapping("delAddress")
    @CrossOrigin
    public ResultVo delAddress(Integer  id) {
		if(id == null || id < 0 ) {
			return  ResultUtil.exec(false, "参数错误！", null);
		}
		try {
			int rst = addressService.deleteAddress(id);
			if(rst > 0 ) {
				return  ResultUtil.exec(true, "删除地址成功！", null);
			}
			return  ResultUtil.exec(false, "删除地址错误！", null);
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResultUtil.exec(false, "删除地址错误！", null);
			
		}
	}
	
	@RequestMapping("getAddress")
    @CrossOrigin
    public ResultVo getAddress(Integer  id) {
		if(id == null || id < 0 ) {
			return  ResultUtil.exec(false, "参数错误！", null);
		}
		try {
			EforcesCustomerAddress address = addressService.selectByPrimaryKey(id);
			if(address==null) {
				return  ResultUtil.exec(false, "选择地址信息失败！", null);
			}else {
				return  ResultUtil.exec(true, "选择地址信息失败！", address);
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
			return  ResultUtil.exec(false, "选择地址信息失败！", null);
			
		}
	}
	
	
	public static List<Map<String,String>> addressResolution(String address){
		/* 
		 * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包。它包括两个类：Pattern和Matcher Pattern
	     *    一个Pattern是一个正则表达式经编译后的表现模式。 Matcher
	     *    一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
         *    首先一个Pattern实例订制了一个所用语法与PERL的类似的正则表达式经编译后的模式，然后一个Matcher实例在这个给定的Pattern实例的模式控制下进行字符串的匹配工作。
    	*/
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        return table;
    }
	
	@ResponseBody
	@RequestMapping("myAddressList")
	public ResultVo getCity(String type,String openId) {
		try {
			List<EforcesCustomerAddress> newList=new ArrayList<>();
			List<EforcesCustomerAddress> list = addressService.getAllAddressByOpenId(openId);
			if(list!=null&&list.size()>0) {
				for (EforcesCustomerAddress eforcesCustomerAddress : list) {
					if(eforcesCustomerAddress.getMemtype().equals(type)) {
						newList.add(eforcesCustomerAddress);
					}
				}
				return  ResultUtil.exec(true, "地址查询成功！", newList);
			}else {
				return  ResultUtil.exec(false, "地址库暂无信息！", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return  ResultUtil.exec(false, "地址查询错误！", null);
		}
	}

}
