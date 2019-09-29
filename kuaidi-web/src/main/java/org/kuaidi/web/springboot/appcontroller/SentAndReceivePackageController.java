package org.kuaidi.web.springboot.appcontroller;


import javax.servlet.http.HttpServletRequest;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.kuaidi.web.springboot.service.HandlingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//收发件。
@RestController
@RequestMapping("/app/handlingOrders/")
public class SentAndReceivePackageController {
	
	@Autowired
	private HandlingOrdersService  handlingService ;

	/*
	 * 节点发送
	 * @param   billNumber  订单号
	 * @param   userId      用户id。
	 * */
	@RequestMapping("incSentPackageToNext")
    @ResponseBody
    @Authorization
    public ResultVo incSentPackageToNext(HttpServletRequest request, String billNumber){
		EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
		EforcesIncment  incMent = (EforcesIncment)request.getAttribute("inc");
        return handlingService.sentOrder(billNumber,userInfo, incMent);
    }
	
	/*
	 * 节点接收
	 * 把订单中的数据，copy一份保存到  eforces_receiveScan  表中。
	 * 往物流信息中加一条数据。
	 * */
	@RequestMapping("incReceivePackage")
    @ResponseBody
    @Authorization
    public ResultVo incReceivePackage(HttpServletRequest request, String billNumber){
        try {
        	EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
    		EforcesIncment  incMent = (EforcesIncment)request.getAttribute("inc");
            return handlingService.receiveOrder(billNumber,userInfo, incMent);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"收单异常！",null);
        }
    }
}
