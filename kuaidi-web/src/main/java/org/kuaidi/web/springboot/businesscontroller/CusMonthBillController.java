package org.kuaidi.web.springboot.businesscontroller;

import java.math.BigDecimal;
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
 * 客户月结收款统计。
 */
@RestController
@RequestMapping("/web/cusMonthBill/")
public class CusMonthBillController {
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService; 
	 
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	 
    @RequestMapping("getOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public ResultVo getOrderShow(HttpServletRequest request ,  QueryPageVo page,Integer guestId, String startTime ,String endTime){
   	 try {
   		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
   		 PageInfo<Map<String, Object>> pageInfo = orderService.getCusMonthBillByPage(page.getPage(), page.getLimit(), guestId, startTime, endTime);
   		 if(pageInfo != null && pageInfo.getTotal() > 0 ) {
       		 return ResultUtil.exec(true,"查询成功",pageInfo.getList());
       	 }
   	 }catch(Exception e ) {
   		 e.printStackTrace();
   	 }
		 return  ResultUtil.exec(false,"查询失败！", null); 
	 }
    
    @RequestMapping("outExcelOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public void outExcelOrderShow(HttpServletResponse response,
    		Integer guestId, String startTime ,String endTime ){
   	 try {
   		 List<Map<String, Object>> list = orderService.getCusMonthBillByList(guestId, startTime, endTime);
   		 String[] header = {"账单月份", "客户名称", "账单编号", "所属网点","票数","应收金额","已收金额",  "未收金额", "折损金额"};
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("客户月结收款统计");
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
           			 Date  ZdDate = (Date)rectoOrderItem.get("ZdDate");
           			 String ZdDateStr = "";
        			 if(ZdDate != null ) {
        				 ZdDateStr = TimeDayUtil.convertDateToStr2(ZdDate);
        			 }
        			 HSSFRichTextString text = new HSSFRichTextString(ZdDateStr);
                     cell.setCellValue(text);
       			 
                    cell = row.createCell(1);
        			 String  GuestName = (String)rectoOrderItem.get("GuestName");
        			 if(GuestName == null ) {
        				 GuestName = "";
        			 }
        			 text = new HSSFRichTextString(GuestName);
                     cell.setCellValue(text);
                    
                     cell = row.createCell(2);
           			 String  Zdnumber = (String)rectoOrderItem.get("Zdnumber");
           			 if(Zdnumber == null ) {
           				Zdnumber = "";
           			 }
           			 text = new HSSFRichTextString(Zdnumber);
                     cell.setCellValue(text);
                     
                     
                    cell = row.createCell(3);
                    String  IncName = (String)rectoOrderItem.get("IncName");
           			if(IncName == null ) {
           				IncName =   "";
           			}
           			text = new HSSFRichTextString(IncName);
                    cell.setCellValue(text);
                    
                    cell = row.createCell(4);
                    BigDecimal Tiaoshu   = (BigDecimal)rectoOrderItem.get("Tiaoshu");
                    String TiaoshuStr = "0";
           			if(Tiaoshu != null ) {
           				TiaoshuStr = Tiaoshu.floatValue() + "";
           			}
           			text = new HSSFRichTextString(TiaoshuStr);
                    cell.setCellValue(text);
	                 
	                cell = row.createCell(5);
	                BigDecimal  ZdPrices = (BigDecimal)rectoOrderItem.get("ZdPrices");
	                String ZdPricesStr = "0";
           			if(ZdPrices == null ) {
           				ZdPricesStr = ZdPrices.floatValue()  + "";
           		   }
           		   text = new HSSFRichTextString(ZdPricesStr);
                   cell.setCellValue(text);
                        
                   cell = row.createCell(6);
                   BigDecimal  YsPrices = (BigDecimal)rectoOrderItem.get("YsPrices");
                   String YsPricesStr = "0";
           		   if(YsPrices != null ) {
           			   YsPricesStr = YsPrices.floatValue() + "";
           		    }
           		   text = new HSSFRichTextString(YsPricesStr);
                   cell.setCellValue(text);
                   
                   cell = row.createCell(7);
                   BigDecimal  WsPrices = (BigDecimal)rectoOrderItem.get("WsPrices");
                   String WsPricesStr = "0";
           		   if(WsPrices != null ) {
           			   WsPricesStr = WsPrices.floatValue() + "";
           		   }
           		   text = new HSSFRichTextString(WsPricesStr);
                   cell.setCellValue(text);
                   
                   cell = row.createCell(8);
                   BigDecimal  ZsPrices = (BigDecimal)rectoOrderItem.get("ZsPrices");
                   String ZsPricesStr = "0";
           		   if(ZsPrices != null ) {
           			   ZsPricesStr = ZsPrices.floatValue() + "";
           		   }
           		   text = new HSSFRichTextString(ZsPricesStr);
                   cell.setCellValue(text);
           		 }
           	 }
            }
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，
            String fileName = "cusMonthBill" +  TimeDayUtil.getCurrentDate() + ".xls";
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
