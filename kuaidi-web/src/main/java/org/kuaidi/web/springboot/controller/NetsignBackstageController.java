package org.kuaidi.web.springboot.controller;

import org.kuaidi.bean.domain.EforcesNetsign;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.NetSignInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/netsign/")
public class NetsignBackstageController {
	
	@Autowired
	private NetSignInfoService netSignService;


    @RequestMapping("selectnetsign")
    @CrossOrigin
    @ResponseBody
    public PageVo doSelectNetsignSort(Integer pageNum , EforcesNetsign record){
        PageVo rst = null;
        try {
            rst = netSignService.doSelectNetsignSort(pageNum,record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    @RequestMapping("snetsignbyid")
    @CrossOrigin
    @ResponseBody
    String doSelectNetsignById(Integer id){
        return netSignService.doSelectNetsignById(id);
    }

    @RequestMapping("updatenetsignbyid")
    @CrossOrigin
    @ResponseBody
    public ResultVo douUpdateNetsignSortById(EforcesNetsign record){
        ResultVo res = null;
        try {
            res = netSignService.douUpdateNetsignSortById(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("selectsort")
    @CrossOrigin
    @ResponseBody
    public ResultVo doSelectSort(EforcesNetsign record){
        ResultVo rst = null;
        try {
            rst = netSignService.doSelectBySort(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }
}
