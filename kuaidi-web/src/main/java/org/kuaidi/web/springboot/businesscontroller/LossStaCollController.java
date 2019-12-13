package org.kuaidi.web.springboot.businesscontroller;

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
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

/*
 * 折损统计统计。
 */
@RestController
@RequestMapping("/web/lossStaColl/")
public class LossStaCollController {
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService; 
	 
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	 
    @RequestMapping("getOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public ResultVo getOrderShow(HttpServletRequest request ,String time){
   	 try {
   		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
   		 if(time == null ) {
   			 time = TimeDayUtil.getCurrentDate2();
   		 }
   		 List<Map<String, Object>> list = orderService.getMonStaColl(incment.getNumber(), time);
   		 if(list != null && list.size() > 0 ) {
       		 return ResultUtil.exec(true,"查询成功",list);
       	 }
   	 }catch(Exception e ) {
   		 e.printStackTrace();
   	 }
		 return  ResultUtil.exec(false,"查询失败！", null); 
	 }
    
    @RequestMapping("outExcelOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public void outExcelOrderShow(HttpServletResponse response, String incId,
    			 String time ){
   	 try {
   		 EforcesIncment  incment =  incmentService.selectByNumber(incId);
   		 /*
   		  *将incNum 转化成前缀的形式。
   		  **/
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
   		if(time == null ) {
  			 time = TimeDayUtil.getCurrentDate2();
  		 }
   		 List<Map<String, Object>> list = orderService.getMonStaColl(incNum, time);
   		 String[] header = {"网点编号", "网点名字", "账单月份", "账单份数","应收金额","已收金额",  "折损金额", "未收金额"};
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("网点月结收款统计");
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
           			 String  createincnumber = (String)rectoOrderItem.get("number");
            			 if(createincnumber == null ) {
            				 createincnumber = "";
            			 }
            			 HSSFRichTextString text = new HSSFRichTextString(createincnumber);
                        cell.setCellValue(text);
           			 
                        cell = row.createCell(1);
            			 String  createincname = (String)rectoOrderItem.get("name");
            			 if(createincname == null ) {
            				 createincname = "";
            			 }
            			text = new HSSFRichTextString(createincname);
                        cell.setCellValue(text);
                        
                        
                         cell = row.createCell(2);
	           			 String  zddate = (String)rectoOrderItem.get("zddate");
	           			 if(zddate == null ) {
	           				zddate = "";
	           			 }
	           			 text = new HSSFRichTextString(zddate);
	                     cell.setCellValue(text);
	                     
	                     
	                    cell = row.createCell(3);
    	                BigDecimal  zdfs = (BigDecimal)rectoOrderItem.get("zdfs");
    	                String zdfsStr = "";
	           			if(zdfs == null ) {
	           				zdfsStr = zdfs.floatValue()  + "";
	           			}
	           			text = new HSSFRichTextString(zdfsStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(4);
                        BigDecimal zdje   = (BigDecimal)rectoOrderItem.get("zdje");
                        String zdjeStr = "0";
	           			if(zdje != null ) {
	           				zdjeStr = zdje.floatValue() + "";
	           			}
	           			text = new HSSFRichTextString(zdjeStr);
                        cell.setCellValue(text);
    	                 
    	                cell = row.createCell(5);
    	                BigDecimal  ysje = (BigDecimal)rectoOrderItem.get("ysje");
    	                String ysjeStr = "";
	           			if(ysje == null ) {
	           				ysjeStr = ysje.floatValue()  + "";
	           			}
	           			text = new HSSFRichTextString(ysjeStr);
                        cell.setCellValue(text);
                        
                       cell = row.createCell(6);
                       BigDecimal  zsje = (BigDecimal)rectoOrderItem.get("zsje");
                       String zsjeStr = "0";
	           		   if(zsje != null ) {
	           			zsjeStr = zsje.floatValue() + "";
	           		    }
	           		   text = new HSSFRichTextString(zsjeStr);
                       cell.setCellValue(text);
                       
                       cell = row.createCell(7);
                       BigDecimal  wsje = (BigDecimal)rectoOrderItem.get("wsje");
                       String wsjeStr = "0";
	           		   if(wsje != null ) {
	           			   wsjeStr = wsje.floatValue() + "";
	           		   }
	           		   text = new HSSFRichTextString(wsjeStr);
                       cell.setCellValue(text);
           		 }
           	 }
            }
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，
            String fileName = "monStaColl" +  TimeDayUtil.getCurrentDate() + ".xls";
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
