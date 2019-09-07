package org.kuaidi.web.springboot.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesDictionary;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesDictionaryService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/web/Diction/")
@RestController
public class EforcesDictionController {

    @Reference(version = "1.0.0")
    IEforcesDictionaryService dictionaryService;

    /**
     * 获取字典数据信息
     * @param page
     * @return
     */
    @GetMapping("Diction")
    @ResponseBody
    @CrossOrigin
public PageVo getlistDiction(QueryPageVo page){
    try {
        EforcesDictionary dictionary = new EforcesDictionary();
    if(StringUtils.isNotEmpty(page.getInfo1())){
        dictionary.setName(page.getInfo1());
    }
        PageInfo<EforcesDictionary> pageInfo = dictionaryService.getlist(page.getPage(),page.getLimit(),dictionary);
        return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
    }catch (Exception e){
    e.printStackTrace();
        return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
    }
}

    /**
     * 修改数据字典信息
     * @param dictionary
     * @return
     */
    @PutMapping("Diction")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateDiction(EforcesDictionary dictionary){
        try {
            int result = dictionaryService.updateByPrimaryKeySelective(dictionary);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

    /**
     * 添加数据字典信息
     * @param dictionary
     * @return
     */
    @PostMapping("Diction")
    @ResponseBody
    @CrossOrigin
    public ResultVo addDiction(EforcesDictionary dictionary){
        try {
            int result = dictionaryService.insertSelective(dictionary);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",0);
        }
    }

    /**
     * 批量删除数据字典信息
     * @param id
     * @return
     */
    @DeleteMapping("Diction")
    @CrossOrigin
    @ResponseBody
    public ResultVo removeUpdateDiction(@RequestBody Integer[] id){
        try {
            int result = dictionaryService.removeUpdate(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

    /**
     * 单条删除数据字典信息
     * @param id
     * @return
     */
    @RequestMapping("removeUpdateDictions")
    @CrossOrigin
    @ResponseBody
    public ResultVo removeUpdateDictions(Integer id){
        try {
            Integer[] array = {id};
            int result = dictionaryService.removeUpdate(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

    /**
     * 查询修改数据
     * @param id
     * @return
     */
    @RequestMapping("showDiction")
    @ResponseBody
    @CrossOrigin
    public ResultVo showDiction(Integer id){
        try {
            EforcesDictionary result = dictionaryService.selectByPrimaryKey(id);
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }
}
