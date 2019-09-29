package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.*;
import org.kuaidi.iservice.IEforcesIncmentService;
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
            rst = incmentDubboService.saveIncment(incment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
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
