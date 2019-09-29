package org.kuaidi.web.springboot.appcontroller;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCustomerAddress;
import org.kuaidi.bean.maintainance.EforcesPaytype;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesAddressService;
import org.kuaidi.iservice.maintainance.IEforcesPaytypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/app/address/")
public class EforcesAddressController {
	
	@Reference(version = "1.0.0")
	private IEforcesAddressService  addressService; 
	
	@Reference(version = "1.0.0")
	private IEforcesPaytypeService  payTypeService; 
	
	
	@RequestMapping("getAddressByOpenId")
    @CrossOrigin
    public ResultVo getAddressByOpenId(String openId){
        try {
        	List<EforcesCustomerAddress> list = 
    				addressService.getAllAddressByOpenId(openId);
        	if(list != null && list.size() > 0) {
        		return ResultUtil.exec(true, "获取数据成功！", list);
        	}
        	return ResultUtil.exec(false, "没有对应的记录！", null);
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResultUtil.exec(false, "查询地址信息错误！", null);
        }
    }
	
	@RequestMapping("getAllPayType")
    @CrossOrigin
    public ResultVo getAllPayType(){
        try {
        	List<EforcesPaytype> list = payTypeService.selectAll();
        	if(list != null && list.size() > 0) {
        		return ResultUtil.exec(true, "获取数据成功！", list);
        	}
        	return ResultUtil.exec(false, "没有对应的记录！", null);
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResultUtil.exec(false, "查询付费类型信息错误！", null);
        }
    }
	
}
