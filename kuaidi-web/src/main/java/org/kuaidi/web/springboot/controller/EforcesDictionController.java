package org.kuaidi.web.springboot.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesCorp;
import org.kuaidi.bean.domain.EforcesDictionary;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCorp;
import org.kuaidi.iservice.IEforcesDictionaryService;
import org.kuaidi.iservice.IEforcesUserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/web/Diction/")
@RestController
public class EforcesDictionController {

    @Reference(version = "1.0.0")
    IEforcesDictionaryService dictionaryService;
    
    @Reference(version = "1.0.0")
	private IEforcesCorp  corpService;
    
    @Reference(version = "1.0.0")
    private IEforcesUserGroupService  userGroupService; 
    
    Logger logger = LoggerFactory.getLogger(EforcesDictionController.class);

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
		    if(StringUtils.isNotEmpty(page.getInfo2()) && page.getInfo2().matches("\\d+")) {
		    	dictionary.setParent(Integer.parseInt(page.getInfo2()));
		    }
	        PageInfo<EforcesDictionary> pageInfo = dictionaryService.getlist(page.getPage(),page.getLimit(),dictionary);
	        return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
	    }catch (Exception e){
	    e.printStackTrace();
	        return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
	    }
	}
    
    /**
     * 获取字典数据信息
     * @param page
     * @return
     */
    @RequestMapping("getlistByParentId")
    @ResponseBody
    @CrossOrigin
	public ResultVo getlistByParentId(Integer parentId){
	    try {
		      List<EforcesDictionary> dictList = dictionaryService.selectByParentId(parentId);
		      if(dictList != null && dictList.size() > 0 ) {
		    	  return ResultUtil.exec(true,"获取数据成功！", dictList);
		      }
		      return ResultUtil.exec(false,"获取数据为空！", null);
		}catch (Exception e){
		    e.printStackTrace();
		    return  ResultUtil.exec(false,"获取数据异常！", null);
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
        	if(dictionary.getId() != null) {
        		dictionary.setId(null);
        	}
        	if(dictionary.getParent() == null || dictionary.getParent() == -1) {
        		dictionary.setParent(0);
        	}
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
                *  查询修改数据
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
    
    @RequestMapping("getAllCrop")
    @ResponseBody
    @CrossOrigin
    public ResultVo getAllCrop(){
		try {
			List<EforcesCorp> list = corpService.getAllEforcesCorp();
			if(list != null && list.size() > 0 ) {
				return ResultUtil.exec(true, "查询成功！", list);
			}
			return ResultUtil.exec(false, "查询结果为空！", list);
		}catch(Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return ResultUtil.exec(false, "查询失败！", null);
		}
	}
    
    @RequestMapping("getAllUserGroup")
    @ResponseBody
    @CrossOrigin
    public ResultVo getAllUserGroup(){
		try {
			List<EforcesUsersgroup> list = userGroupService.selectAllUserGroup();
			if(list != null && list.size() > 0 ) {
				return ResultUtil.exec(true, "查询成功！", list);
			}
			return ResultUtil.exec(false, "查询结果为空！", list);
		}catch(Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return ResultUtil.exec(false, "查询失败！", null);
		}
	}
}
