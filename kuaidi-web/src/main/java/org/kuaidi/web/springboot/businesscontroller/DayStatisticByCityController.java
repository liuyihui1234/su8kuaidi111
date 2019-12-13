package org.kuaidi.web.springboot.businesscontroller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

@RestController
@RequestMapping("/web/dayStatisticByCity/")
public class DayStatisticByCityController {
	
	 @Reference(version = "1.0.0")
	 IEforcesOrderService  orderService; 
	 
	 @Reference(version = "1.0.0")
	 IEforcesIncmentService incmentService; 
	 
     @RequestMapping("getOrderShow")
     @CrossOrigin
	 @NeedUserInfo
     public ResultVo getOrderShow(HttpServletRequest request , String province , String city,
    		 			String area , String incNum , Integer userId ,String startTime , String endTime){
    	 try {
    		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
    		 if(incNum == null ) {
    			 incNum = incment.getNumber();
    		 }    		 
    		 List<Map<String, Object>> list = orderService.getDayStatisticByUser(province, city, area, incNum, userId, startTime, endTime);
    		 if(list != null && list.size() > 0 ) {
    			 for(int i = 0 ; i < list.size(); i++) {
    				 Map<String, Object> item = list.get(i);
    				 BigDecimal  hj = (BigDecimal) item.get("hj");
    				 BigDecimal zjs = (BigDecimal)item.get("zjs");
    				 if(zjs != null && zjs.floatValue() > 0 ) {
    					 Float Djprice =  hj.floatValue() / zjs.floatValue();
    					 String price = "0";
    					 if(Djprice != null ) {
    						 price = new DecimalFormat("###,###,###.##").format(Djprice); 
    					 }
    					 item.put("Djprice", price);
    				 }
    			 }
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
    		 String province , String city, String area,Integer userId, String startTime, String endTime ){
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
    		 List<Map<String, Object>> list = orderService.getDayStatisticByUser(province, city, area, incNum, userId, startTime, endTime);
    		 String[] header = {"所属网点编号", "所属网点名字","业务员编号","业务员名字",  "收件金额", "派件金额","月结","合计","代收货款","总票数","总件数"};
             //声明一个工作簿
             HSSFWorkbook workbook = new HSSFWorkbook();
             //生成一个表格，设置表格名称为"学生表"
             HSSFSheet sheet = workbook.createSheet("营业收入统计（按照业务员统计）");
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
            			 String  createincnumber = (String)rectoOrderItem.get("createincnumber");
             			 if(createincnumber == null ) {
             				 createincnumber = "";
             			 }
             			 HSSFRichTextString text = new HSSFRichTextString(createincnumber);
                         cell.setCellValue(text);
            			 
                         cell = row.createCell(1);
             			 String  createincname = (String)rectoOrderItem.get("createincname");
             			 if(createincname == null ) {
             				 createincname = "";
             			 }
             			text = new HSSFRichTextString(createincname);
                        cell.setCellValue(text);
            			 
                         cell = row.createCell(2);
            			 String  senduserid = (String)rectoOrderItem.get("senduserid");
     	      			 if(senduserid == null ) {
     	      				senduserid =  "";
     	      			 }
     	      			 text = new HSSFRichTextString(senduserid);
     	                 cell.setCellValue(text);
     	                 
     	                 
     	                cell = row.createCell(3);
            			String  sendusername = (String)rectoOrderItem.get("sendusername");
            			if(sendusername == null ) {
            				sendusername = "";
            			}
            			text = new HSSFRichTextString(sendusername);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(4);
                        BigDecimal sjje   = (BigDecimal)rectoOrderItem.get("sjje");
                        String sjjeStr = "0";
            			if(sjje != null ) {
            				sjjeStr = sjje.floatValue() + "";
            			}
            			text = new HSSFRichTextString(sjjeStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(5);
                        BigDecimal  pjje = (BigDecimal)rectoOrderItem.get("pjje");
                        String pjjeStr = "0";
            			if(pjje != null ) {
            				pjjeStr = pjje.floatValue() + "";
            			}
            			text = new HSSFRichTextString(pjjeStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(6);
                        BigDecimal  yj = (BigDecimal)rectoOrderItem.get("yj");
                        String yjStr = "0";
            			if(yj != null ) {
            				yjStr = yj.floatValue() + "";
            			}
            			text = new HSSFRichTextString(yjStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(7);
                        BigDecimal  hj = (BigDecimal)rectoOrderItem.get("hj");
                        String hjStr = "0";
            			if(hj != null ) {
            				hjStr = hj.floatValue() + "";
            			}
            			text = new HSSFRichTextString(hjStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(8);
                        BigDecimal  dshk = (BigDecimal)rectoOrderItem.get("dshk");
                        String dshkStr = "0";
            			if(dshk != null ) {
            				dshkStr = dshk.floatValue() + "";
            			}
            			text = new HSSFRichTextString(dshkStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(9);
                        BigDecimal  zps = (BigDecimal)rectoOrderItem.get("zps");
                        String zpsStr = "0";
            			if(zps != null ) {
            				zpsStr = zps.floatValue() + "";
            			}
            			text = new HSSFRichTextString(zpsStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(10);
                        BigDecimal  zjs = (BigDecimal)rectoOrderItem.get("zjs");
                        String zjsStr = "0";
            			if(zjs != null ) {
            				zjsStr = zjs.floatValue() + "";
            			}
            			text = new HSSFRichTextString(zjsStr);
                        cell.setCellValue(text);
            		 }
            	 }
             }
             //准备将Excel的输出流通过response输出到页面下载
             //八进制输出流
             response.setContentType("application/octet-stream");
             //这后面可以设置导出Excel的名称，
             String fileName = "dayStatisticByUser" +  TimeDayUtil.getCurrentDate() + ".xls";
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
