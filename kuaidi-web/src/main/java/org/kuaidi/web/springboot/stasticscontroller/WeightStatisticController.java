package org.kuaidi.web.springboot.stasticscontroller;

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
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IEforcesWeighingScanService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/web/WeightStatistic/")
public class WeightStatisticController {
	
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	
	@Reference(version = "1.0.0")
	IEforcesWeighingScanService  weightService;
	
	@RequestMapping("getOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public ResultVo getOrderShow(HttpServletRequest request , String billsNumber , String sendCity,
   		  String startTime , String endTime){
	   	 try {
	   		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
	   		 List<Map<String, Object>> list = weightService.getWeightStatisticByParam(incment.getNumber(), billsNumber, sendCity,startTime, endTime);
	       	 if(list != null && list.size() > 0 ) {
	       		 return ResultUtil.exec(true, "查询称重统计信息成功！", list);
	       	 }else {
	       		 return ResultUtil.exec(false, "查询称重统计信息失败！", list);
	       	 }
	   	 }catch(Exception e ) {
	   		 e.printStackTrace();
	   	 }
		 return  ResultUtil.exec(false,"查询用户信息失败", null); 
	 }
    
    @RequestMapping("outExcelOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public void outExcelOrderShow(HttpServletResponse response, String incId,
    		String billsNumber , String sendCity,
     		  String startTime , String endTime){
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
   		 List<Map<String, Object>> list = weightService.getWeightStatisticByParam(incNum ,billsNumber,sendCity, startTime, endTime);
   		 String[] header = { "收件城市","单号","重量", "收件员", "扫描员"};
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("称重扫描监控");
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
           		 Map<String, Object>  rectoOrderItem = list.get(i);
           		 if(rectoOrderItem != null ) {
           			 HSSFRow row = sheet.createRow(i + 1);
           			 HSSFCell cell = row.createCell(0);
           			 String  name = (String)rectoOrderItem.get("name");
	      			 if(name == null ) {
	      				name =  "";
	      			 }
	      			 HSSFRichTextString text = new HSSFRichTextString(name);
	                 cell.setCellValue(text);
	                 
	                 cell = row.createCell(1);            			 
	                 String  billsnumber = (String)rectoOrderItem.get("billsnumber");
   	      			 if(billsnumber == null ) {
   	      				 billsnumber =  "";
   	      			 }
   	      			 text = new HSSFRichTextString(billsnumber);
   	                 cell.setCellValue(text);
    	                 
   	                 cell = row.createCell(2);            			 
   	                 BigDecimal  weight = (BigDecimal)rectoOrderItem.get("weight");
    	      		 String weightStr = "0";
 	                 if(weight != null ) {
 	                	weightStr =  weight.floatValue() + "";
	      			 }
	      			 text = new HSSFRichTextString(weightStr);
	                 cell.setCellValue(text);
       			 
	                 cell = row.createCell(3);            			 
           			 String  scanman = (String)rectoOrderItem.get("scanman");
	      			 if(scanman == null ) {
	      				scanman =  "";
	      			 }
	      			 text = new HSSFRichTextString(scanman);
	                 cell.setCellValue(text);
	                 
	                 cell = row.createCell(4);
      			     String  scanners = (String)rectoOrderItem.get("scanners");
   	      			 if(scanners == null ) {
   	      				 scanners =  "";
   	      			 }
   	      			 text = new HSSFRichTextString(scanners);
   	                 cell.setCellValue(text);
           		 }
           	 }
            }
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，
            String fileName = "WeightStatistic" +  TimeDayUtil.getCurrentDate() + ".xls";
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载
            workbook.write(response.getOutputStream());
   	 }catch(Exception e) {
   		 e.printStackTrace();
   	 }
   	 
    }
}
