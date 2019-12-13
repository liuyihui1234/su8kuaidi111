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
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.kuaidi.utils.TimeDayUtil;
import org.kuaidi.web.springboot.core.authorization.NeedUserInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

/*
 * 始发地取件统计
 */

@RestController
@RequestMapping("/web/summaryDeliveryOrderController/")
public class SummaryDeliveryOrderController {
	
	@Reference(version = "1.0.0")
	IEforcesRectoOrderService  rectoOrderService; 
	 
	@Reference(version = "1.0.0")
	IEforcesIncmentService incmentService; 
	 
    @RequestMapping("getOrderShow")
    @CrossOrigin
	@NeedUserInfo
    public ResultVo getOrderShow(HttpServletRequest request ,String startTime , String endTime){
    	try {
	   		 EforcesIncment  incment = (EforcesIncment)request.getAttribute("inc");
	   		 List<Map<String, Object>> list = rectoOrderService.getDiliveryByRegion(incment.getNumber() , startTime, endTime);
	   		 if(list != null) {
	       		 return ResultUtil.exec(true,"始发地取件统计查询成功！", list);
		     }
	   		return  ResultUtil.exec(false, "查询结果为空！", null); 
	   	 }catch(Exception e ) {
	   		 e.printStackTrace();
	   	 }
		 return  ResultUtil.exec(false, "查询结果错误！", null); 
    }
    
    @RequestMapping("outExcelOrderShow")
    @CrossOrigin
	 @NeedUserInfo
    public void outExcelOrderShow(HttpServletResponse response, String incId,
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
	   		String[] header = {"序号", "始发地", "取件票数", "总运费", "营业额", "代收货款"};
            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("始发地取件统计");
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
	   		
            List<Map<String, Object>> list = rectoOrderService.getDiliveryByRegion(incNum , startTime, endTime);
	   		if(list != null && list.size() > 0 ) {
	   			for(int i = 0 ; i < list.size() ; i++) {
	   				Map<String, Object>  rectoOrderItem = list.get(i);
	   				if(rectoOrderItem != null ) {
	   					HSSFRow row = sheet.createRow(i + 1);
	   					HSSFCell cell = row.createCell(0);
	   					String  code = (String)rectoOrderItem.get("code");
	      			 	if(code == null ) {
	      			 		code =  "";
	      			 	}
	      			 	HSSFRichTextString text = new HSSFRichTextString(code);
    	                cell.setCellValue(text);
    	                
    	                cell = row.createCell(1);
            			String  name = (String)rectoOrderItem.get("name");
            			if(name == null ) {
            				name = "";
            			}
            			text = new HSSFRichTextString(name);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(2);
                        BigDecimal  pjnum = (BigDecimal)rectoOrderItem.get("pjnum");
                        String pjnumStr = "0"; 
            			if(pjnum != null ) {
            				pjnumStr = pjnum.intValue() + "";
            			}
            			text = new HSSFRichTextString(pjnumStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(3);
                        BigDecimal  price = (BigDecimal)rectoOrderItem.get("price");
                        String priceStr = "0";
            			if(price != null ) {
            				priceStr = price.floatValue() + "";
            			}
            			text = new HSSFRichTextString(priceStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(4);
                        BigDecimal  sumprice = (BigDecimal)rectoOrderItem.get("sumprice");
                        String sumpriceStr = "0";
            			if(sumprice != null ) {
            				sumpriceStr = sumprice.floatValue() + "";
            			}
            			text = new HSSFRichTextString(sumpriceStr);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(5);
                        BigDecimal  dshk = (BigDecimal)rectoOrderItem.get("dshk");
                        String dshkStr = "0";
            			if(dshk != null ) {
            				dshkStr = dshk.floatValue() + "";
            			}
            			text = new HSSFRichTextString(dshkStr + "");
                        cell.setCellValue(text);
                        
	   				}
	   			}
	   		}
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，
            String fileName = "SummaryRecToOrder" +  TimeDayUtil.getCurrentDate() + ".xls";
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
