package org.kuaidi.web.springboot.controller.weixin;

import org.kuaidi.bean.domain.EforcesCustomerAddress;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesAddressService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
