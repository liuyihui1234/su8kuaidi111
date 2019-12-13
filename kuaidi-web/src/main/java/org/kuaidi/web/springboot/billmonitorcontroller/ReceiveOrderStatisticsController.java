package org.kuaidi.web.springboot.billmonitorcontroller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IEforcesReceivedscanService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/receiveOrderStatics/")
public class ReceiveOrderStatisticsController {
	
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	
	@Reference(version = "1.0.0")
	IEforcesReceivedscanService  receiveService; 
	
	@RequestMapping("getOrderShow")
	@CrossOrigin
	@NeedUserInfo
	public PageVo getOrderShow(HttpServletRequest request , QueryPageVo page, String province , String city,
   		 String area , String SstartTime , String SendTime, String RstartTime , String RendTime){
	   	 Integer pageNum = page.getPage();
	   	 if(page.getPage() == null  ) {
	   		 pageNum = 1;
	   	 }
	   	 Integer pageSize = page.getLimit();
	   	 if(pageSize == null) {
	   		pageSize = Config.pageSize;
	   	 }
	   	 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
	   	 try {
	   		PageInfo<Map<String, Object>> pageInfo = receiveService.getReceiveStatisticsByPage(pageNum, pageSize, 
		   			SstartTime, SendTime, RstartTime, RendTime, incment.getNumber(), province, city, area);
	   		if(pageInfo != null) {
	   			return ResultUtil.exec(pageNum, pageSize, pageInfo.getTotal(), pageInfo.getList());
	   		}
	   	 }catch(Exception e) {
	   		 e.printStackTrace();
	   	 }
	   	 return ResultUtil.exec(pageNum, pageSize,0 , null) ; 
	}
	
	@RequestMapping("outExcelOrderShow")
    @CrossOrigin
	 @NeedUserInfo
    public void outExcelOrderShow(HttpServletResponse response, String incId,
   		 String province , String city, String area,
   		String SstartTime , String SendTime, String RstartTime , String RendTime){
	   	 try {
	   		 EforcesIncment  incment =  incmentService.selectByNumber(incId);
	   		 /*
	   		  * 将incNum 转化成前缀的形式。
	   		  * */
	   		 String incNum = "";
	   		 if(incment != null ) {
	   			 if (StringUtils.isNotEmpty(incment.getLevel() + "")  && incment.getLevel() > 0 ) {
	                	String incNumber = incment.getNumber().substring(0, incment.getLevel() * 2);
	                	if(StringUtils.equals(incNumber, "00")) {
	                		incNum = "";
	                	}
	                }else {
	               	 incNum = "";
	                }
	   		 }
	   		List<Map<String , Object>> list = receiveService.getReceiveStatisticsByList(SstartTime, SendTime, RstartTime, RendTime, incNum, province, city, area);
	   		 // 查询数据
	   		 //Excel file head
	   		String[] header = {"到件网点编号","到件网点名字", "应到票数", "已到票数", "未到票数"};
	        //声明一个工作簿
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        //生成一个表格，设置表格名称为"学生表"
	        HSSFSheet sheet = workbook.createSheet("网点到件监听");
	        //设置表格列宽度为10个字节
	        sheet.setDefaultColumnWidth(10);
	        //创建第一行表头
	        HSSFRow headrow = sheet.createRow(0);
	        // 创建表头
	        for (int i = 0; i < header.length; i++) {
	              //创建一个单元格
	              HSSFCell cell = headrow.createCell(i);
	              //创建一个内容对象
	              HSSFRichTextString text = new HSSFRichTextString(header[i]);
	              //将内容对象的文字内容写入到单元格中.
	              cell.setCellValue(text);
	        }
	        if(list != null && list.size() > 0 ) {
				for(int i = 0 ; i < list.size() ; i++) {
					Map<String,Object>  receiveItem = list.get(i);
					if(receiveItem != null ) {
						 HSSFRow row = sheet.createRow(i + 1);
	           			 HSSFCell cell = row.createCell(0);
	           			 String  departid = (String)receiveItem.get("incid");
		      			 if(departid == null ) {
		      				departid =  "";
		      			 }
		      			 HSSFRichTextString text = new HSSFRichTextString(departid);
		                 cell.setCellValue(text);
		                 
		                 cell = row.createCell(1);
	           			 String  incname = (String)receiveItem.get("incname");
		      			 if(incname == null ) {
		      				incname =  "";
		      			 }
		      			 text = new HSSFRichTextString(incname);
		                 cell.setCellValue(text);
		                 
		                 cell = row.createCell(2);
		                 BigDecimal  ygd = (BigDecimal)receiveItem.get("ygd");
		                 String ygdStr = "0" ; 
		      			 if(ygd != null ) {
		      				ygdStr =  ygd.intValue() + "";
		      			 }
		      			 text = new HSSFRichTextString(ygdStr);
		                 cell.setCellValue(text);
		                 
		                 cell = row.createCell(3);
		                 BigDecimal  wd = (BigDecimal)receiveItem.get("wd");
		                 String wdStr = "0" ; 
		      			 if(wd != null ) {
		      				wdStr =  wd.intValue() + "";
		      			 }
		      			 text = new HSSFRichTextString(wdStr);
		                 cell.setCellValue(text);
		                 
		                 cell = row.createCell(4);
		                 BigDecimal  yjd = (BigDecimal)receiveItem.get("yjd");
		                 String yjdStr = "0" ; 
		      			 if(yjd != null ) {
		      				yjdStr =  yjd.intValue() + "";
		      			 }
		      			 text = new HSSFRichTextString(yjdStr);
		                 cell.setCellValue(text);
					}
				}
	   		}
	        //准备将Excel的输出流通过response输出到页面下载
	        //八进制输出流
	        response.setContentType("application/octet-stream");
	        //这后面可以设置导出Excel的名称，
	        String fileName = "ReceiveOrderByMonitor" +  TimeDayUtil.getCurrentDate() + ".xls";
	        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
	        //刷新缓冲
	        response.flushBuffer();
	        //workbook将Excel写入到response的输出流中，供页面下载
	        workbook.write(response.getOutputStream());
		   	 }catch(Exception e ) {
		   		 e.printStackTrace();
		   	 }
	}
}
