package org.kuaidi.web.springboot.appcontroller;

import java.io.IOException;
import java.util.List;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesReceivedScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesReceivedscanService;
import org.kuaidi.iservice.IEforcesSentscanService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.kuaidi.utils.JBarCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/app/order/")
public class AppOrderController {

	@Reference(version = "1.0.0",interfaceName="org.kuaidi.iservice.IEforcesSentscanService")
	IEforcesSentscanService sentscanService;
	
	@Reference(version = "1.0.0")
    IEforcesReceivedscanService receivedscanService;

	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService;
	
	@Reference(version = "1.0.0")
	IEforceslogisticstrackingService  strackingService;
    
//    @Autowired
//	private RedisUtil  redisUtil;

    @Value("${file.baseDir}")
    private String baseDir;

	@RequestMapping("MySendAndReceiveOrder")
	@ResponseBody
	public ResultVo MySendAndReceiveOrder(String incid) throws IOException {
		try {
			JSONObject data = new JSONObject();
			int recCount = receivedscanService.getReceicedscanCount(incid);
			int senCount = sentscanService.getSentscanCount(incid);
			data.put("receivedscan",recCount);
			data.put("sentscan",senCount);
			return ResultUtil.exec(true,"",data);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"查询错误！",null);
		}
	}

	/**
	 *  分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param incNum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("hadPostPackage")
	@ResponseBody
	public PageVo hadPostPackage(Integer pageNum, Integer  pageSize, String  incNum) throws IOException {
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.getHadPostPackage(pageNum , pageSize , incNum);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}

	/**
	 * 未派件 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param incNum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("NohadPostPackage")
	@ResponseBody
	public PageVo NohadPostPackage(Integer pageNum, Integer  pageSize, String  incNum) throws IOException {
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.getNotPostPackage(pageNum , pageSize , incNum);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	
	/**
	 *  分页查询(网点端已经完成的订单)
	 * @param pageNum
	 * @param pageSize
	 * @param incNum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("hadFinishPackage")
	@ResponseBody
	public PageVo hadFinishPackage(Integer pageNum, Integer  pageSize, String  incNum) throws IOException {
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.getHadFinishPackage(pageNum , pageSize , incNum);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	
	/**
	 * 未完成的订单分页查询(网点端未完成订单)
	 * @param pageNum
	 * @param pageSize
	 * @param incNum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("NoHadFinishPackage")
	@ResponseBody
	public PageVo NoHadFinishPackage(Integer pageNum, Integer  pageSize, String  incNum) throws IOException {
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.getNotFinishPackage(pageNum , pageSize , incNum);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(pageNum, pageSize, 0,null);
		}
	}
	
	/**
	 *   根据订单号  查询物流信息
	 * @param billsNumber
	 * @return
	 */
	@RequestMapping("getStrackingList")
	@ResponseBody
	public ResultVo getStrackingList(String billsNumber) {
		try {
			if (billsNumber == null) {
				return ResultUtil.exec(false, "提交参数不合法！", null);
			}
			List<EforcesLogisticStracking> result = strackingService.getByBillsNumber(billsNumber);
			return ResultUtil.exec(true, "", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询操作异常！", null);
		}
	}
	/**
	  * 查看订单详情
	 * @param number
	 * @return
	 */
	@RequestMapping("getByNumber")
	public ResultVo getByNumber(String number) {
		try {
//			redisUtil.set("name", "liuyihui1234", 20000);
			if (number == null) {
				return ResultUtil.exec(false, "提交参数不合法！", null);
			}
			List<EforcesOrder> result =orderService.getByNumber(number);
			return ResultUtil.exec(true, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.exec(false, "查询操作异常！", null);
		}
	}
	/**
	 * 查询网点下所有的订单信息
	 * @param incid
	 * @return
	 */
	@RequestMapping("getAllOrder")
	@ResponseBody
	public ResultVo getAllOrder(String incid){
		try {
			List<EforcesReceivedScan> allorder = receivedscanService.getAllOrder(incid);
			return ResultUtil.exec(true,"查询数据成功",allorder);
		} catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"查询数据失败",null);
		}
	}

    /**
	 * 查看订单详情
     * @param number
     * @return
     */
    @RequestMapping("getOrderDetailByNum")
    public ResultVo getOrderDetailByNum(String number) {
        try {
            if (number == null) {
                return ResultUtil.exec(false, "提交参数不合法！", null);
            }
            List<EforcesOrder> result =orderService.getByNumber(number);
            if(result != null && result.size() > 0 ){
                EforcesOrder orderDetail = result.get(0);
                String orderNumber = orderDetail.getNumber() ;
                //  转换成条形码
                String orderPath = JBarCodeUtil.createBarcode(orderNumber, baseDir, "");
                orderDetail.setBarcodePath(orderPath);
                return ResultUtil.exec(true, "获取数据成功", orderDetail);
            }else{
                return ResultUtil.exec(false, "没有找到对应的数据", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "查询操作异常！", null);
        }
    }

	/**
	 * 获取已支付订单信息
	 * @param postmanid 快递员编号
	 * @return
	 */
	@RequestMapping("getYetPayment")
	@ResponseBody
	public PageVo yetPayment(String postmanid,Integer pageSize,Integer pageNum){
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.yetPayment(postmanid,pageSize,pageNum);
				return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResultUtil.exec(pageNum,pageSize,0,null);
	}

	/**
	 * 获取已支付订单信息
	 * @param postmanid 快递员编号
	 * @return
	 */
	@RequestMapping("getNotPayment")
	@ResponseBody
	public PageVo notPayment(String postmanid,Integer pageSize,Integer pageNum){
		try {
			if(pageSize == null ||  pageSize <= 0 ){
				pageSize = Config.pageSize;
			}
			if(pageNum == null || pageNum <= 0 ){
				pageNum = 1;
			}
			PageInfo<EforcesOrder> pageInfo = orderService.notPayment(postmanid,pageSize,pageNum);
			return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResultUtil.exec(pageNum,pageSize,0,null);
	}


	/**
	 * 收件信息详情
	 * @param incid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("listAddressee")
	@ResponseBody
	public PageVo ListAddressee(String incid,Integer pageNum,Integer pageSize){
	try {
		if(pageNum == null || pageNum <= 0 ){
			pageNum = 1;
		}
		if(pageSize == null || pageSize <= 0 ){
			pageSize = Config.pageSize;
		}
		PageInfo<EforcesOrder> pageInfo = orderService.getListAddressee(incid,pageNum,pageSize);
		return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getList());
	}catch (Exception e){
		e.printStackTrace();
		return ResultUtil.exec(pageNum,pageSize,0,null);
		}
	}

	/**
	 * 寄/派件运单管理
	 * @param page
	 * @return
	 */
	@GetMapping("order")
	@ResponseBody
	@CrossOrigin
	public PageVo getListMsg(QueryPageVo page){
		try {
			PageInfo<EforcesOrder> pageInfo = orderService.getAllMsg(page.getPage(),page.getLimit());
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
		}
	}


	/**
	 * 删除寄/派件运单管理
	 * @param id
	 * @return
	 */
	@RequestMapping("removeOrder")
	@CrossOrigin
	public ResultVo removeOrderMsg(Integer id){
		try {
			Integer[] array = {id};
			int result = orderService.removeUpdate(array);
			return ResultUtil.exec(true,"删除成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}

	/**
	 * 删除寄/派件运单管理
	 * @param array
	 * @return
	 */
	@DeleteMapping("order")
	@CrossOrigin
	public ResultVo removeOrderMsgs(@RequestBody Integer[] array){
		try {
			int result = orderService.removeUpdate(array);
			return ResultUtil.exec(true,"删除成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}


}
