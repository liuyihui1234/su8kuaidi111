package org.kuaidi.web.springboot.billmonitorcontroller;

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
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesCustomerSignService;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/signMonitorByUser/")
public class SignMonitorByUserController {
	
	 @Reference(version = "1.0.0")
	 IEforcesCustomerSignService  customerSignService; 
	 
	 @Reference(version = "1.0.0")
	 IEforcesIncmentService incmentService; 
	 
     @RequestMapping("getOrderShow")
     @CrossOrigin
	 @NeedUserInfo
     public ResultVo getOrderShow(HttpServletRequest request , String province , String city,
    		 String area , String incNum , Integer userId ,String time){
    	 try {
    		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
    		 if(time == null) {
        		 time = TimeDayUtil.getCurrentDate1();
        	 }
    		 List<Map<String, Object>> list = customerSignService.customSignByUser(incment.getNumber(), province, city, area, userId, time);
    		 if(list != null && list.size() > 0 ) {

        		 for(int i = list.size() -1 ; i >= 0   ; i--) {
        			 Map<String,Object>  customerSignItem = list.get(i);
        			 if(customerSignItem == null || customerSignItem.get("num") == null) {
        				 list.remove(i);
        				 continue;
        			 }
        			 BigDecimal number  = (BigDecimal) customerSignItem.get("num");
        			 Integer total = 0 ; 
        			 if(number != null && number.intValue() > 0 ) {
        				 total = number.intValue();
        			 }
        			 if(total > 0 ) {
        				BigDecimal num1 = (BigDecimal)customerSignItem.get("num1");
        				float numIten = 0 ;
        				if(num1 != null ) {
        					numIten = num1.floatValue();
        				}else {
        					customerSignItem.put("num1",0);
        				}
							Float percent =   numIten * 100/(float)total ;
							String a = new DecimalFormat("###,###,###.##").format(percent); 
							customerSignItem.put("preHoursTen", a + "%");
							
							BigDecimal num2 = (BigDecimal)customerSignItem.get("num2");
							numIten = 0 ;
        				if(num2 != null ) {
        					numIten = num2.floatValue();
        				}else {
        					customerSignItem.put("num2",0);
        				}
							percent =   numIten * 100/(float)total ;
							a = new DecimalFormat("###,###,###.##").format(percent); 
							customerSignItem.put("preHoursTwelve", a + "%");
							
							BigDecimal num3 = (BigDecimal)customerSignItem.get("num3");
							numIten = 0 ;
        				if(num3 != null ) {
        					numIten = num3.floatValue();
        				}else {
        					customerSignItem.put("num3",0);
        				}
							percent =   numIten * 100/(float)total ;
							a = new DecimalFormat("###,###,###.##").format(percent); 
							customerSignItem.put("preHoursFifteen", a + "%");
							
							
							BigDecimal num4 = (BigDecimal)customerSignItem.get("num4");
							numIten = 0 ;
        				if(num4 != null ) {
        					numIten = num4.floatValue();
        				}else {
        					customerSignItem.put("num4", 0);
        				}
							percent =   numIten * 100/(float)total ;
							a = new DecimalFormat("###,###,###.##").format(percent); 
							customerSignItem.put("afterHoursFifteen", a + "%");
							
							
							BigDecimal num5 = (BigDecimal)customerSignItem.get("num5");
							numIten = 0 ;
        				if(num5 != null ) {
        					numIten = num5.floatValue();
        				}else {
        					customerSignItem.put("num5", 0);
        				}
							percent =   numIten * 100/(float)total ;
							a = new DecimalFormat("###,###,###.##").format(percent); 
							customerSignItem.put("unSignHoursFifteen", a + "%");
        			 }else {
        				 customerSignItem.put("preHoursTen", "0%");
        				 customerSignItem.put("preHoursTwelve", "0%");
        				 customerSignItem.put("preHoursFifteen", "0%");
        				 customerSignItem.put("AfterHoursFifteen", "0%");
        				 customerSignItem.put("unSignHoursFifteen", "0%");
        				 customerSignItem.put("num",0);
        				 customerSignItem.put("num1",0);
        				 customerSignItem.put("num2",0);
        				 customerSignItem.put("num3",0);
        				 customerSignItem.put("num4",0);
        				 customerSignItem.put("num5",0);
        			 }
        			 if(customerSignItem.get("wtjnum") == null) {
        				 customerSignItem.put("wtjnum",0);
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
    		 String province , String city, String area,Integer userId, String time ){
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
    		 if(time == null) {
        		 time = TimeDayUtil.getCurrentDate1();
        	 }
    		 List<Map<String, Object>> list = customerSignService.customSignByUser(incNum, province, city, area,userId, time);
    		 String[] header = {"业务员编号","业务员名字", "签收网点编号", "签收网点名字", "签收日期", "派件票数","10:30前","百分比","12:00前","百分比","15:00前","百分比",
    				 		"15:00后","百分比","未签收15:00后","未签收百分比","问题件票数"};
             //声明一个工作簿
             HSSFWorkbook workbook = new HSSFWorkbook();
             //生成一个表格，设置表格名称为"学生表"
             HSSFSheet sheet = workbook.createSheet("业务员签收统计");
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
             /*
              * 计算所占的百分比。
              */
    		 for(int i = list.size() -1 ; i >= 0  ; i--) {
    			 Map<String,Object>  customerSignItem = list.get(i);
    			 if(customerSignItem == null || customerSignItem.get("num") == null) {
    				 list.remove(i);
    				 continue;
    			 }
    			 BigDecimal number  = (BigDecimal) customerSignItem.get("num");
    			 Integer total = 0 ; 
    			 if(number != null && number.intValue() > 0 ) {
    				 total = number.intValue();
    			 }
    			 if(total > 0 ) {
    				BigDecimal num1 = (BigDecimal)customerSignItem.get("num1");
    				float numIten = 0 ;
    				if(num1 != null ) {
    					numIten = num1.floatValue();
    				}else {
    					customerSignItem.put("num1",0);
    				}
						Float percent =   numIten * 100/(float)total ;
						String a = new DecimalFormat("###,###,###.##").format(percent); 
						customerSignItem.put("preHoursTen", a + "%");
						
						BigDecimal num2 = (BigDecimal)customerSignItem.get("num2");
						numIten = 0 ;
    				if(num2 != null ) {
    					numIten = num2.floatValue();
    				}else {
    					customerSignItem.put("num2",0);
    				}
						percent =   numIten * 100/(float)total ;
						a = new DecimalFormat("###,###,###.##").format(percent); 
						customerSignItem.put("preHoursTwelve", a + "%");
						
						BigDecimal num3 = (BigDecimal)customerSignItem.get("num3");
						numIten = 0 ;
    				if(num3 != null ) {
    					numIten = num3.floatValue();
    				}else {
    					customerSignItem.put("num3",0);
    				}
						percent =   numIten * 100/(float)total ;
						a = new DecimalFormat("###,###,###.##").format(percent); 
						customerSignItem.put("preHoursFifteen", a + "%");
						
						
						BigDecimal num4 = (BigDecimal)customerSignItem.get("num4");
						numIten = 0 ;
    				if(num4 != null ) {
    					numIten = num4.floatValue();
    				}else {
    					customerSignItem.put("num4", 0);
    				}
						percent =   numIten * 100/(float)total ;
						a = new DecimalFormat("###,###,###.##").format(percent); 
						customerSignItem.put("afterHoursFifteen", a + "%");
						BigDecimal num5 = (BigDecimal)customerSignItem.get("num5");
						numIten = 0 ;
    				if(num5 != null ) {
    					numIten = num5.floatValue();
    				}else {
    					customerSignItem.put("num5", 0);
    				}
						percent =   numIten * 100/(float)total ;
						a = new DecimalFormat("###,###,###.##").format(percent); 
						customerSignItem.put("unSignHoursFifteen", a + "%");
    			 }else {
    				 customerSignItem.put("preHoursTen", "0%");
    				 customerSignItem.put("preHoursTwelve", "0%");
    				 customerSignItem.put("preHoursFifteen", "0%");
    				 customerSignItem.put("AfterHoursFifteen", "0%");
    				 customerSignItem.put("unSignHoursFifteen", "0%");
    				 customerSignItem.put("num",0);
    				 customerSignItem.put("num1",0);
    				 customerSignItem.put("num2",0);
    				 customerSignItem.put("num3",0);
    				 customerSignItem.put("num4",0);
    				 customerSignItem.put("num5",0);
    			 }
    			 if(customerSignItem.get("wtjnum") == null) {
    				 customerSignItem.put("wtjnum",0);
    			 }
    		 }
             if(list != null && list.size() > 0 ) {
            	 for(int i = 0 ; i < list.size() ; i++) {
            		 Map<String, Object>  rectoOrderItem = list.get(i);
            		 if(rectoOrderItem != null ) {
            			 HSSFRow row = sheet.createRow(i + 1);
            			 
            			 HSSFCell cell = row.createCell(0);
             			String  postmanid = (String)rectoOrderItem.get("postmanid");
             			if(postmanid == null ) {
             				postmanid = "";
             			}
             			HSSFRichTextString text = new HSSFRichTextString(postmanid);
                        cell.setCellValue(text);

     	                cell = row.createCell(1);
            			String  postman = (String)rectoOrderItem.get("postman");
            			if(postman == null ) {
            				postman = "";
            			}
            			text = new HSSFRichTextString(postman);
                        cell.setCellValue(text);
            			 
            			 System.out.println(postman +  ">>>>"  );
            			 System.out.println(postmanid + ">>>>" );
            			 
                         cell = row.createCell(2);
            			 String  departid = (String)rectoOrderItem.get("number");
     	      			 if(departid == null ) {
     	      				departid =  "";
     	      			 }
     	      			 text = new HSSFRichTextString(departid);
     	                 cell.setCellValue(text);
     	                 
     	                 
     	                cell = row.createCell(3);
            			String  incname = (String)rectoOrderItem.get("name");
            			if(incname == null ) {
            				incname = "";
            			}
            			text = new HSSFRichTextString(incname);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(4);
            			String  time1 = (String)rectoOrderItem.get("time");
            			if(time1 == null ) {
            				time1 = "";
            			}
            			text = new HSSFRichTextString(time1);
                        cell.setCellValue(text);
                        
                        
                        
                        cell = row.createCell(5);
                        BigDecimal  num = (BigDecimal)rectoOrderItem.get("num");
                        String numStr = "0";
            			if(num != null ) {
            				numStr = num.intValue() + "";
            			}
            			text = new HSSFRichTextString(numStr + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(6);
                        Integer  num1 = (Integer)rectoOrderItem.get("num1");
                        String num1Str = "0";
            			if(num1 != null ) {
            				num1Str = num1.intValue() + "";
            			}
            			text = new HSSFRichTextString(num1Str + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(7);
                        text = new HSSFRichTextString(rectoOrderItem.get("preHoursTen") + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(8);
                        Integer  num2 = (Integer)rectoOrderItem.get("num2");
                        String num2Str = "0";
            			if(num2 != null ) {
            				num2Str = num2.intValue() + "";
            			}
            			text = new HSSFRichTextString(num2Str + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(9);
                        text = new HSSFRichTextString(rectoOrderItem.get("preHoursTwelve") + "");
                        cell.setCellValue(text);
                        cell = row.createCell(10);
                        
                        Object  num3 = (Object)rectoOrderItem.get("num3");
                        String num3Str = "0";
            			if(num3 != null ) {
            				if(num3 instanceof BigDecimal) {
            					num3Str = ((BigDecimal)num3).intValue() + "";
            				}else if(num3 instanceof Integer) {
            					num3Str = ((Integer)num3).intValue() + "";
            				}
            			}
            			text = new HSSFRichTextString(num3Str + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(11);
                        text = new HSSFRichTextString(rectoOrderItem.get("preHoursFifteen") + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(12);
                        Integer  num4 = (Integer)rectoOrderItem.get("num4");
                        String num4Str = "0";
            			if(num4 != null ) {
            				num4Str = num4.intValue() + "";
            			}
            			text = new HSSFRichTextString(num4Str + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(13);
                        text = new HSSFRichTextString(rectoOrderItem.get("AfterHoursFifteen") + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(14);
                        Object  num5 = (Object)rectoOrderItem.get("num5");
                        String num5Str = "0";
            			if(num5 != null ) {
            				if(num5 instanceof BigDecimal) {
            					num5Str = ((BigDecimal)num5).intValue() + "";
            				}else if(num5 instanceof Integer) {
            					num5Str = ((Integer)num5).intValue() + "";
            				}
            			}
            			text = new HSSFRichTextString(num5Str);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(15);
                        text = new HSSFRichTextString(rectoOrderItem.get("unSignHoursFifteen") + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(16);
                        Object  wtjnum = (Object)rectoOrderItem.get("wtjnum");
                        String wtjnumStr = "0";
            			if(wtjnum != null ) {
            				if(wtjnum instanceof BigDecimal) {
            					wtjnumStr = ((BigDecimal)wtjnum).intValue() + "";
            				}else if(wtjnum instanceof Integer) {
            					wtjnumStr = ((Integer)wtjnum).intValue() + "";
            				}
            			}
            			text = new HSSFRichTextString(wtjnumStr );
                        cell.setCellValue(text);
            		 }
            	 }
             }
             //准备将Excel的输出流通过response输出到页面下载
             //八进制输出流
             response.setContentType("application/octet-stream");
             //这后面可以设置导出Excel的名称，
             String fileName = "signMonitorByUser" +  TimeDayUtil.getCurrentDate() + ".xls";
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
