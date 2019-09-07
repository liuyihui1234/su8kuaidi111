package org.kuaidi.web.springboot.appcontroller;

import javax.servlet.http.HttpServletRequest;

import org.kuaidi.bean.domain.EforcesContraband;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IDictionaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import java.util.List;

/*
 * 违禁品查询
 */
@RestController
@RequestMapping("/app/contraband/")
public class EforcesContrabandController {
	
	@Reference(version = "1.0.0")
	private IDictionaryService  dictionaryService;
	
	@RequestMapping("getContrabandByName")
    @ResponseBody
    public ResultVo getContrabandByName(String name){
		try {
			List<EforcesContraband> contrabandList = dictionaryService.getContrabandByName(name);
			if(contrabandList != null && contrabandList.size() > 0 ) {
				return ResultUtil.exec(true, "查询违禁物品成功", contrabandList);
			}else {
				return ResultUtil.exec(false, "没有查询到违禁物品", null);
			}
		}catch(Exception e ) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询异常！", null);
		}
	}
	
}
