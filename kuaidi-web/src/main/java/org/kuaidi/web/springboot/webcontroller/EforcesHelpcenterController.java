package org.kuaidi.web.springboot.webcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesHelpcente;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.EforcesHelpcenteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/Helpcenter/")
public class EforcesHelpcenterController {

    @Reference(version = "1.0.0")
    EforcesHelpcenteService helpcenteService;

    /**
     * 获取帮助中心信息
     * @param
     * @return
     */
    @GetMapping("Helpcente")
    @CrossOrigin
    public PageVo<EforcesHelpcente> getListByPage(QueryPageVo page){
        try {
           PageInfo<EforcesHelpcente> result = helpcenteService.getCountdata(page.getPage(),page.getLimit());
           return ResultUtil.exec(result.getPageNum(),result.getSize(),result.getTotal(),"查询数据成功！",result.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0 ,"查询帮助中心操作失败！",null);
        }
    }

    /**
     * 添加帮助中心
     * @param helpcente
     * @return
     */
    @PostMapping("Helpcente")
    @CrossOrigin
    public ResultVo  add(EforcesHelpcente helpcente){
        try {
            int result = helpcenteService.updateByPrimaryKeySelective(helpcente);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            return ResultUtil.exec(false,"添加失败",0);
        }
    }

    /**
     * 删除帮助中心
     * @param id
     * @return
     */
    @DeleteMapping("Helpcente")
    @CrossOrigin
    public ResultVo removeDelete(@RequestBody Integer[] id){
        try {
            int result = helpcenteService.updateById(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

    /**
     * 修改帮助中心
     * @param helpcente
     * @return
     */
    @PutMapping("Helpcente")
    @CrossOrigin
    public ResultVo update(EforcesHelpcente helpcente){
        try {
            int result = helpcenteService.updateByPrimaryKeySelective(helpcente);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            return ResultUtil.exec(false,"修改失败",0);
        }
    }
}
