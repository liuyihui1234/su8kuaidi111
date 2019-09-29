package org.kuaidi.web.springboot.webcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesVersioninfo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesVersioninfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/web/Version/")
public class EforcesVersionInfoController {

    @Reference(version = "1.0.0")
    IEforcesVersioninfoService versioninfoService;

    /**
     * 查询版本控制信息
     * @return
     */
    @GetMapping("Version")
    @CrossOrigin
    public ResultVo getListMsg(){
        try {
            List<EforcesVersioninfo> ListVersion = versioninfoService.getListMsg();
            return ResultUtil.exec(true,"查询成功",ListVersion);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }

    /**
     * 修改版本控制信息
     * @param versioninfo
     * @return
     */
    @PutMapping("Version")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateVersion(EforcesVersioninfo versioninfo){
        try {
            int result = versioninfoService.updateByPrimaryKeySelective(versioninfo);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

    /**
     * 添加版本控制信息
     * @param versioninfo
     * @return
     */
    @PostMapping("Version")
    @ResponseBody
    @CrossOrigin
    public ResultVo insertVersion(EforcesVersioninfo versioninfo){
        try {
            versioninfo.setCrttime(new Date());
            int result = versioninfoService.insertSelective(versioninfo);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",0);
        }
    }

    /**
     * 删除版本控制信息
     * @param id
     * @return
     */
    @DeleteMapping("Version")
    @ResponseBody
    @CrossOrigin
    public ResultVo deleteVersion(@RequestBody Integer[] id){
        try {
            int result = versioninfoService.delete(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }
}
