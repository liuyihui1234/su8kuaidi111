package org.kuaidi.web.springboot.appcontroller;


import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
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
    public ResultVo incSentPackageToNext(String billNumber,Integer userId){
        return handlingService.sentOrder(billNumber, userId);
    }
	
	/*
	 * 节点接收
	 * 把订单中的数据，copy一份保存到  eforces_receiveScan  表中。
	 * 往物流信息中加一条数据。
	 * */
	@RequestMapping("incReceivePackage")
    @ResponseBody
    public ResultVo incReceivePackage(String billNumber,Integer userId){
        try {
            return handlingService.receiveOrder(billNumber,userId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"收单异常！",null);
        }
    }
}
