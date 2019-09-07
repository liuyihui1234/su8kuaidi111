package org.kuaidi.web.springboot.appcontroller;

import org.kuaidi.bean.domain.EforcesCorp;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCorp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import java.util.*;

@RestController
@RequestMapping("/app/corpmanage/")
public class EforcesCorpController {

	Logger logger = LoggerFactory.getLogger(EforcesCorpController.class);

	@Reference(version = "1.0.0")
	private IEforcesCorp  corpService;

	@RequestMapping("getAllCrop")
    @ResponseBody
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

}
