package org.kuaidi.web.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDistributedScan;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesDistributedScanService;
import org.kuaidi.web.springboot.dubboservice.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/app/dict/")
public class DictionaryController {
	
	@Autowired
	private DictionaryService  dictService;

	@Reference(version = "1.0.0")
	IEforcesDistributedScanService distributedScanService;
	
	@RequestMapping("defaultPrice")
    @ResponseBody
    public ResultVo doInsertBankPayInfo(String province , String city, String area, String areaStreet) {
		return dictService.getdefaultPrice(province, city, area, areaStreet);
	}
	
	@RequestMapping("defaultBankInfo")
    @ResponseBody
    public ResultVo defaultBankInfo() {
		return dictService.defaultBankInfo();
	}

/*	*//**
	 * 派件扫描
	 * @param page
	 * @return
	 *//*
	@RequestMapping("Sendscan")
	@ResponseBody
	@CrossOrigin
	public PageVo getlistDisy(QueryPageVo page){
		try {
			PageInfo<EforcesDistributedScan> pageInfo = distributedScanService.getlistDisy(page.getPage(),page.getLimit());
			return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
		}
	}*/
/*
	*//**
	 * 删除派件扫描
	 * @param id
	 * @return
	 *//*
	@RequestMapping("deleteSendscan")
	@CrossOrigin
	public ResultVo deleteDistributedScan(Integer id){
		try {
			Integer[] array = {id};
			int result = distributedScanService.deleteDistributedScan(array);
			return ResultUtil.exec(true,"删除成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}

	*//**
	 * 删除派件扫描
	 * @param array
	 * @return
	 *//*
	@RequestMapping("deleteSendscans")
	@CrossOrigin
	public ResultVo deleteDistributedScans(@RequestBody Integer[] array){
		try {
			int result = distributedScanService.deleteDistributedScan(array);
			return ResultUtil.exec(true,"删除成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"删除失败",null);
		}
	}

	*//**
	 * 修改  查询要修改的记录
	 * @param id
	 *//*
	@RequestMapping("getUpdateMsg")
	@ResponseBody
	@CrossOrigin
	public ResultVo getupdateSend(int id) {
		try {
			EforcesDistributedScan result = distributedScanService.getbuteScan(id);
			return ResultUtil.exec(true,"查询的这条数据是要进行呢修改的数据",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"查询失败",null);
		}
	}*/

/*
	//当前端页面传过来的的String类型的日期与后台实体类的Date类型不匹配时，需要加上该方法
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
*/

	/**
	 * 修改派件扫描
	 * @param buScan
	 * @return
	 */
	@RequestMapping("updateSend")
	@ResponseBody
	@CrossOrigin
	public ResultVo updateSendMsg(EforcesDistributedScan buScan){
		try {
			int result = distributedScanService.updateByPrimaryKeySelective(buScan);
			return ResultUtil.exec(true,"修改成功",result);
		}catch (Exception e){
			e.printStackTrace();
			return ResultUtil.exec(false,"修改失败",null);
		}
	}

/*	*//**
	 * 增加派件扫描信息
	 * @param buScan
	 * @return
	 *//*
	@RequestMapping("addSendMsg")
	@ResponseBody
	@CrossOrigin
	public ResultVo addSendMsg(EforcesDistributedScan buScan){
	try {
		int result = distributedScanService.addSendMsg(buScan);
		return ResultUtil.exec(true,"增加信息成功",result);
	}catch (Exception e){
		e.printStackTrace();
		return ResultUtil.exec(false,"增加信息失败",null);
	}
	}*/
}
