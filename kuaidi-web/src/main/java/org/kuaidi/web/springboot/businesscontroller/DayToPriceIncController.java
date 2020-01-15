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
 * 每日营业统计，按照网点。
 */
@RestController
@RequestMapping("/web/dayToPriceInc/")
public class DayToPriceIncController {
	
	@Reference(version = "1.0.0")
	IEforcesOrderService  orderService; 
	 
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	 
    @RequestMapping("getOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public ResultVo getOrderShow(HttpServletRequest request ,String startTime , String endTime){
   	 try {
   		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
   		 List<Map<String, Object>> list = orderService.getDayToPriceByInc(incment.getNumber(), startTime, endTime);
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
    					String startTime, String endTime ){
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
   		 List<Map<String, Object>> list = orderService.getDayToPriceByInc(incNum, startTime, endTime);
   		 String[] header = {"网点编号", "网点名字","寄方付","收方付","月结",  "代收货款"};
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("每日营业统计(按网点)");
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
           			 String  createincnumber = (String)rectoOrderItem.get("incnumber");
            			 if(createincnumber == null ) {
            				 createincnumber = "";
            			 }
            			 HSSFRichTextString text = new HSSFRichTextString(createincnumber);
                         cell.setCellValue(text);
           			 
                        cell = row.createCell(1);
            			 String  createincname = (String)rectoOrderItem.get("incname");
            			 if(createincname == null ) {
            				 createincname = "";
            			 }
            			text = new HSSFRichTextString(createincname);
                        cell.setCellValue(text);
           			 
                        cell = row.createCell(2);
                        BigDecimal  jif = (BigDecimal)rectoOrderItem.get("jif");
                        String jifStr = "0";
    	      			 if(jif != null ) {
    	      				jifStr =  jif.floatValue() + "";
    	      			 }
    	      			 text = new HSSFRichTextString(jifStr);
    	                 cell.setCellValue(text);
    	                 
    	                 
    	                cell = row.createCell(3);
    	                BigDecimal  daof = (BigDecimal)rectoOrderItem.get("daof");
    	                String daofStr = "";
	           			if(daof != null ) {
	           				daofStr = daof.floatValue()  + "";
	           			}
	           			text = new HSSFRichTextString(daofStr);
                        cell.setCellValue(text);
                       
                        cell = row.createCell(4);
                        BigDecimal yj   = (BigDecimal)rectoOrderItem.get("yj");
                        String yjStr = "0";
	           			if(yj != null ) {
	           				yjStr = yj.floatValue() + "";
	           			}
	           			text = new HSSFRichTextString(yjStr);
                       cell.setCellValue(text);
                       
                       cell = row.createCell(5);
                       BigDecimal  Dshk = (BigDecimal)rectoOrderItem.get("Dshk");
                       String DshkStr = "0";
	           		   if(Dshk != null ) {
	           			   DshkStr = Dshk.floatValue() + "";
	           		    }
	           		   text = new HSSFRichTextString(DshkStr);
                       cell.setCellValue(text);
           		 }
           	 }
            }
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，
            String fileName = "dayToPriceInc" +  TimeDayUtil.getCurrentDate() + ".xls";
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
