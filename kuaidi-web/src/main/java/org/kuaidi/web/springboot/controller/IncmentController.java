package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.*;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IRegionService;
import org.kuaidi.web.springboot.dubboservice.IncmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/web/incment/")
public class IncmentController {
    @Reference(version = "1.0.0")
    IEforcesIncmentService iEforcesIncmentService;

    @Autowired
    IncmentService incmentDubboService;
    
    @Reference(version = "1.0.0")
    IRegionService regionService;
    

	@GetMapping("incment")
	@CrossOrigin
	@ResponseBody
	public PageVo getAllIncment(QueryPageVo page){
		return incmentDubboService.getAllIncment(page);
				
	}


    @RequestMapping("getincmentById")
    @CrossOrigin
    @ResponseBody
    public ResultVo getIncmentById(Integer id) {
        ResultVo rst = null;
        try {
            rst = incmentDubboService.getIncmentById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    /**
     * 添加数据
     *
     * @param incment
     * @return
     */

    @PostMapping("incment")
    @CrossOrigin
    public ResultVo saveincment(EforcesIncment incment) {
        ResultVo rst = null;
        try {
        	//默认不能添加总分区和四大分区
        	String regionCode = null;
        	if(StringUtils.isNotEmpty(incment.getAreastreet())) {
        		regionCode = incment.getAreastreet();
        	}else if(StringUtils.isNotEmpty(incment.getArea())) {
        		regionCode = incment.getArea();
        	}else if(StringUtils.isNotEmpty(incment.getCity())) {
        		regionCode = incment.getCity();
        	}else if(StringUtils.isNotEmpty(incment.getProvince())) {
        		regionCode = incment.getProvince();
        	}
        	if(StringUtils.isNotEmpty(regionCode)) {
        		//判断网点是否存在。
        		EforcesIncment incmentInfo =  iEforcesIncmentService.selectByNumber(regionCode);
            	if(incmentInfo != null) {
            		return ResultUtil.exec(false, "对应分区的网点已经存在，请确定", "");
            	}
        		if(StringUtils.equals(regionCode, incment.getProvince())) {
        			String parentCode = getAreaInfo(incment.getLagearea());
        			if(StringUtils.isEmpty(parentCode)) {
        				return ResultUtil.exec(false, "请选择对应的大区", "");
        			}
        			incment.setParentid(parentCode);
        		}else if(StringUtils.equals(regionCode, incment.getCity())) {
        			incment.setParentid(incment.getProvince());
        		}else if(StringUtils.equals(regionCode, incment.getArea())) {
        			incment.setParentid(incment.getCity());
        		}else if(StringUtils.equals(regionCode, incment.getAreastreet())) {
        			incment.setParentid(incment.getArea());
        		}
        		if(StringUtils.isEmpty(incment.getParentid())) {
        			return ResultUtil.exec(false, "没有找到对应的上级节点！", "");
        		}
        		incmentInfo =  iEforcesIncmentService.selectByNumber(incment.getParentid());
        		if(incmentInfo == null || StringUtils.isEmpty(incmentInfo.getName())) {
        			return ResultUtil.exec(false, "上级网点名字不正确！", "");
        		}
        		incment.setParentname(incmentInfo.getName());
        		if(regionCode.length() < 8) {
        			regionCode = regionCode + "00";
        		}
        		incment.setNumber(regionCode);
        	}else {
        		//添加大区
        		return ResultUtil.exec(false, "请选择省市区街道！", "");
        	}
        	incment.setMoneytype("人民币");
        	if(StringUtils.equals(regionCode, incment.getCity()) ||
        			StringUtils.equals(regionCode, incment.getProvince())) {
        		incment.setType("中心");
        	}else {
        		incment.setType("网点");
        	}
            rst = incmentDubboService.saveIncment(incment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }
    
    /*
	 * 华北总部	00000101
	华东总部	00000102
	华南总部	00000103
	华西总部	01000000
	 */
    public String getAreaInfo(String lageareaName) {
    	if(StringUtils.isEmpty(lageareaName)) {
    		return null ; 
    	}
    	if(lageareaName.indexOf("华北") > -1) {
    		return "00000101";
		}else if(lageareaName.indexOf("华东") > -1) {
    		return "00000102";
		}else if(lageareaName.indexOf("华南") > -1) {
    		return "00000103";
		}else if(lageareaName.indexOf("华西") > -1) {
    		return "00000104";
		}
    	return null;
    }

    /**
     * 修改数据
     *
     * @param incment
     * @return
     */
    @PutMapping("incment")
    @CrossOrigin
    public ResultVo updateIncment(EforcesIncment incment) {
        ResultVo rst = null;
        try {
            System.out.println("收到的参数是" + incment);
            if ("市辖区".equals(incment.getCity())) {
                incment.setCity("aaa");
            }
            if ("市辖区".equals(incment.getArea())) {
                incment.setArea("aaa");
            }
            ProvinceVo provinId = incmentDubboService.getProvinId(incment.getProvince(), incment.getCity(), incment.getArea());
            System.out.println("查出来的省数据是" + provinId);
            if (StringUtils.isNotEmpty(provinId.getProvincecode())) {
                incment.setProvince(provinId.getProvincecode());
            } else {
                incment.setProvince("");
            }
            if (StringUtils.isNotEmpty(provinId.getCitycode())) {
                incment.setCity(provinId.getCitycode());
            } else {
                incment.setCity("");
            }
            if (StringUtils.isNotEmpty(provinId.getAreacode())) {
                incment.setArea(provinId.getAreacode());
            } else {
                incment.setArea("");
            }
            rst = incmentDubboService.updateIncment(incment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }



    /**
     * 删除
     * @return
     */
    @DeleteMapping("incment")
    @CrossOrigin
    public ResultVo deleteUser(@RequestBody List<Integer> array) {
        int  rst = iEforcesIncmentService.deleteByid(array);
        return ResultUtil.exec(true, "删除成功！", rst);
    }


    @RequestMapping("getProvinId")
    @CrossOrigin
    @ResponseBody
    public ResultVo getProvinId(String name) {
        ResultVo rst = null;
        try {
            System.out.println("收到的参数是" + name);

            ProvinceVo provinId = incmentDubboService.getProvinId(name, null, null);
            System.out.println(provinId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }
    
    @RequestMapping("getIncByParent")
    @CrossOrigin
    @ResponseBody
    public ResultVo getIncByParent(String parentCode) {
        ResultVo rst = null;
        try {
        	if(StringUtils.isEmpty(parentCode)) {
        		parentCode = "0";
        	}
        	rst = incmentDubboService.findIncmentByParentNumber(parentCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }
    
    
}
