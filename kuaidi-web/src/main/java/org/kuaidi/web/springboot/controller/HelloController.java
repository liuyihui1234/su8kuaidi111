package org.kuaidi.web.springboot.controller;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.kuaidi.web.springboot.dubboservice.CityDubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kuaidi.bean.domain.EforcesRegion;

	@RestController
	@RequestMapping("/api/")
	public class HelloController {

		@Autowired
		private CityDubboConsumerService consumerService;
		

		@RequestMapping("test")
		public void test(@RequestParam("file") MultipartFile file) throws IOException {
		         try{
		        	 InputStream is =file.getInputStream();
		             //获取文件名
		             String fileName = file.getOriginalFilename();
		 
		             Workbook workbook = null;
		             if (fileName.endsWith("xls")){
		                 workbook = new HSSFWorkbook(is);
		             } else if(fileName.endsWith("xlsx")){
		                 workbook = new XSSFWorkbook(is);
		             } else {
		                 System.out.println("workBoot 创建失败");
		             }
		             //获得当前页
		             Sheet sheet = null;  
		             Row row = null;  
		             Cell cell = null;  
		             List<EforcesRegion> list = new ArrayList<EforcesRegion>();  
		             //遍历Excel中所有的sheet  
		                 sheet = workbook.getSheetAt(0);  
		                 //遍历当前sheet中的所有行  
		                 System.out.println(sheet.getLastRowNum());
		                 for (int j = sheet.getFirstRowNum(); j <=sheet.getLastRowNum(); j++) {  
		                     row = sheet.getRow(j); 
		                     EforcesRegion region = new EforcesRegion();
		                     if (j == 0) {
		                    	 Cell cell2 = row.getCell(1);
		                    	 String city = cell2.getStringCellValue();
		                    	 region.setBigZoneId(55);
		                    	 region.setCode("760000");
		                    	 region.setName(city);
		                    	 region.setParentCode("0");
		                    	 list.add(region);
		                    	 continue;
		                     }
		                     
		                     //遍历所有的列  
	                     	//市
		                     
		                     EforcesRegion region1 = new EforcesRegion();
                        	 Cell cell2 = row.getCell(1);
	                    	 String city = cell2.getStringCellValue();
                        	 String index = Integer.valueOf(list.get(list.size() - 1).getCode().substring(2, 6)) + "";//105  10
                        	 String code = getCode(index, list);
                        	 region1.setBigZoneId(5);
                        	 region1.setCode(code);
                        	 region1.setName(city);
                        	 region1.setParentCode(list.get(0).getCode());
                        	 list.add(region1);

	                        	 
                        	 //县
                        	 Cell cell3 = row.getCell(2);
	                    	 String city1 = cell3.getStringCellValue();
                        	 String[] fieldS = city1.split(" ");
                        	 String parentCode = list.get(list.size() - 1).getCode();
                        	 for (int m = 1; m <= fieldS.length; m++) {
                        		 String index1 = Integer.valueOf(list.get(list.size() - 1).getCode().substring(2, 6)) + "";//105  10
                        		 String code2 = getCode(index1, list);
                        		 EforcesRegion region2 = new EforcesRegion();
                        		 region2.setName(fieldS[m - 1]);
                        		 region2.setBigZoneId(5);
                        		 region2.setCode(code2);
                        		 region2.setParentCode(parentCode);
                        		 list.add(region2);
	                         }
                        	JSONObject dataInfo = new JSONObject();
         		 			dataInfo.put("list", list);
         		            System.out.println(dataInfo.toString());
         		            
         		           
		                 }  
		            JSONObject dataInfo = new JSONObject();
		 			dataInfo.put("list", list);
		            System.out.println(dataInfo.toString());
		            int i = 0;
		            for (EforcesRegion r : list) {
		            	i++;
		            	System.out.println(i);
		            	consumerService.saveRegion(r);
		            }
		         } catch (Exception  e){
		             System.out.println("文件转换出错");
		             e.printStackTrace();
		         } 
		     
		 }
		
		public String getCode(String index, List<EforcesRegion> list) {
			String code = "";
			if (index.length() == 1) {
	   			 if (index.endsWith("9")) {
	   				 code = list.get(0).getCode().substring(0, 2) + "00" + (Integer.valueOf(index)  + 1);//140010
	   			 } else {
	   				 code = list.get(0).getCode().substring(0, 2) + "000" + (Integer.valueOf(index)  + 1);//140002
	   			 }
	       	 } else if (index.length() == 2){
	       		 if (index.endsWith("99")) {
	       			 code = list.get(0).getCode().substring(0, 2) + "0" + (Integer.valueOf(index)  + 1);//140100
	       		 } else {
	       			 code = list.get(0).getCode().substring(0, 2) + "00" + (Integer.valueOf(index) + 1);//140020
	       		 }
	       	 } else if (index.length() == 3){
	       		 if (index.endsWith("999")) {
	       			 code = list.get(0).getCode().substring(0, 2) + "" + (Integer.valueOf(index)  + 1);//142222   
	       		 } else {
	       			 code = list.get(0).getCode().substring(0, 2) + "0" + (Integer.valueOf(index) + 1);//140222   
	       		 }
	       	 } else {
	       		 code = list.get(0).getCode().substring(0, 2) + (Integer.valueOf(index)  + 1);//1411111
	       	 }
			
			return code;
		}
	    
		
}
