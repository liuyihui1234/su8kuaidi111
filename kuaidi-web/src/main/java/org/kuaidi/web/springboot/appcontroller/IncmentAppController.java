package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.IncmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/incment/")
public class IncmentAppController {
    @Autowired
    private IncmentService incmentService;

    @ResponseBody
    @RequestMapping("findIncmentByNumber")
    @CrossOrigin
    public ResultVo doFindIncmentByNumber(String number){
        ResultVo vo = null;
        try {
            vo = incmentService.findIncmentByNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    
    /*
     *  网点统计
     */
    @ResponseBody
    @RequestMapping("findIncmentByParentNum")
    @CrossOrigin
    public ResultVo findIncmentByParentNum(String parentNum){
        ResultVo vo = null;
        try {
            vo = incmentService.findIncmentByParentNumber(parentNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    
    
    @ResponseBody
    @RequestMapping("statisticsIncmentByNumber")
    @CrossOrigin
    public ResultVo statisticsIncmentByNumber(String number){
        ResultVo vo = null;
        try {
            vo = incmentService.statisticsIncmentByNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    
    
    @ResponseBody
    @RequestMapping("statisticsIncByParentNumber")
    @CrossOrigin
    public ResultVo statisticsIncByParentNumber(String parentNumber){
        ResultVo vo = null;
        try {
            vo = incmentService.statisticsIncByParentNumber(parentNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    
    

}
