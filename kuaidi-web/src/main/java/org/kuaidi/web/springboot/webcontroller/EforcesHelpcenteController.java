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

import java.util.Date;

@RestController
@RequestMapping("/auth/web/Helpcente/")
public class EforcesHelpcenteController {

    @Reference(version = "1.0.0")
    EforcesHelpcenteService helpcenteService;

    /**
     * 获取中心信息
     * @param pageVo
     * @return
     */
    @GetMapping("Helpcente")
    @CrossOrigin
    public PageVo<EforcesHelpcente> getList(QueryPageVo pageVo){
        try {
            PageInfo<EforcesHelpcente> result = helpcenteService.getCountdata(pageVo.getPage(),pageVo.getLimit());
           return ResultUtil.exec(result.getPageNum(),result.getSize(),result.getTotal(),result.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(pageVo.getPage(),pageVo.getLimit(),pageVo.getLimit(),null);
        }
    }

    /**
     * 修改删除的状态为已删除
     * @param id
     * @return
     */
    @DeleteMapping("Helpcente")
    @CrossOrigin
    public ResultVo removeUpdate (@RequestBody Integer[] id){
        try {
            Integer result = helpcenteService.updateById(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

    @PostMapping("Helpacente")
    @CrossOrigin
    public ResultVo addHelpcente(EforcesHelpcente helpcente){
        try {
            helpcente.setCreatetime(new Date());
            int result = helpcenteService.insertSelective(helpcente);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",0);
        }
    }

    @PutMapping("Helpacente")
    @CrossOrigin
    public ResultVo updateHelpcente(EforcesHelpcente helpcente){
        try {
            int result = helpcenteService.updateByPrimaryKeySelective(helpcente);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

}
