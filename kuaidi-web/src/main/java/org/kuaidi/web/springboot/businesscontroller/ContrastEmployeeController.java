package org.kuaidi.web.springboot.businesscontroller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
/*
 * 业务统计
 */
@RestController
@RequestMapping("/web/contrastEmployee/")
public class ContrastEmployeeController {
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService; 
	 
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	
    @RequestMapping("getOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public PageVo  getOrderShow(HttpServletRequest request ,  QueryPageVo page,String province ,String city ,String area , String incNum ,  String startTime ,String endTime){
	   	try {
	   		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
	   		 PageInfo<Map<String, Object>> pageInfo = orderService.contrastEmployee(page.getPage(), page.getLimit(), province, city,area, incNum, startTime, endTime);
	   		 if(pageInfo != null && pageInfo.getTotal() > 0 ) {
	   			 List<Map<String,Object>> list = pageInfo.getList();
	   			 for(int i = 0 ; i < list.size() ; i++) {
	   				 Map<String , Object> item = list.get(i);
	       			 Date  scantime = (Date)item.get("scantime");
	       			 String scantimeStr = "";
	    			 if(scantime != null ) {
	    				 scantimeStr = TimeDayUtil.convertDateToStr(scantime);
	    			 }
	    			 item.put("scantime", scantimeStr);
	    			 
	    			 Date  issuedtime = (Date)item.get("issuedtime");
	       			 String issuedtimeStr = "";
	    			 if(scantime != null ) {
	    				 issuedtimeStr = TimeDayUtil.convertDateToStr(issuedtime);
	    			 }
	    			 item.put("issuedtime", issuedtimeStr);
	   			 }
	       		 return ResultUtil.exec(page.getPage(),page.getLimit(),pageInfo.getTotal(),pageInfo.getList());
	       	 }else {
	       		 return ResultUtil.exec(page.getPage(),page.getLimit(),0, null); 
	       	 }
	   	 }catch(Exception e ) {
	   		 e.printStackTrace();
	   	 }
		 return  ResultUtil.exec(page.getPage(),page.getLimit(),0, null); 
	 }
    
    @RequestMapping("outExcelOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public void outExcelOrderShow(HttpServletResponse response,
    		String province ,String city ,String area , String incNum ,  String startTime ,String endTime ){
   	 try {
   		 List<Map<String, Object>> list = orderService.contrastEmployeeList(province, city,area, incNum, startTime, endTime);
   		 String[] header = {"运单编号", "收件网点", "收件日期", "收件员","发放业务员","发放网点","发放日期","发放备注"};
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("业务员面单发放对比");
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
        			 String  number = (String)rectoOrderItem.get("number");
        			 if(number == null ) {
        				 number = "";
        			 }
        			 HSSFRichTextString text = new HSSFRichTextString(number);
                     cell.setCellValue(text);
                     
                     cell = row.createCell(1);
           			 String  name = (String)rectoOrderItem.get("name");
           			 if(name == null ) {
           				name = "";
           			 }
           			 text = new HSSFRichTextString(name);
                     cell.setCellValue(text);
                     
                     
                    cell = row.createCell(2);
                    Date  scantime = (Date)rectoOrderItem.get("scantime");
                    String scantimeStr = "";
                    if(scantime != null ) {
                    	scantimeStr =    TimeDayUtil.convertDateToStr(scantime);
           			}
           			text = new HSSFRichTextString(scantimeStr);
                    cell.setCellValue(text);
                    
                    cell = row.createCell(3);
                    String scanman   = (String)rectoOrderItem.get("scanman");
           			if(scanman == null ) {
           				scanman =  "";
           			}
           			text = new HSSFRichTextString(scanman);
                    cell.setCellValue(text);
	                 
	                cell = row.createCell(4);
	                String  username = (String)rectoOrderItem.get("username");

	                if(username == null ) {
	                	username =  "";
           		   }
           		   text = new HSSFRichTextString(username);
                   cell.setCellValue(text);
                        
                   cell = row.createCell(5);
                   String  incname = (String)rectoOrderItem.get("incname");
           		   if(incname == null ) {
           			   incname =  "";
           		   }
           		   text = new HSSFRichTextString(incname);
                   cell.setCellValue(text);
                   
                   cell = row.createCell(6);
                   Date  issuedtime = (Date)rectoOrderItem.get("issuedtime");
                   String issuedtimeStr = "";
           		   if(issuedtime != null ) {
           			   issuedtimeStr =  TimeDayUtil.convertDateToStr(issuedtime);
           		   }
           		   text = new HSSFRichTextString(issuedtimeStr);
                   cell.setCellValue(text);
                   
                   cell = row.createCell(7);
                   String  bz = (String)rectoOrderItem.get("bz");
           		   if(bz == null ) {
           			   bz = "";
           		   }
           		   text = new HSSFRichTextString(bz);
                   cell.setCellValue(text);
           		 }
           	 }
            }
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，
            String fileName = "contrastEmployee" +  TimeDayUtil.getCurrentDate() + ".xls";
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
