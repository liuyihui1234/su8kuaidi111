package org.kuaidi.web.springboot.webcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.kuaidi.bean.domain.EforcesPrice;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesPrice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("web/price/")
public class EforcesPricesController {

    @Reference(version="1.0.0")
    IEforcesPrice price;
    
    /*
     * 根据条件导出excel文件
     */
    @RequestMapping("getPriceExcelByParam")
    @CrossOrigin
    public void getPriceExcelByParam(HttpServletResponse response ,String fromProvince , String toProvince, String status){
        try {
            List<Map<String,Object>> result = price.getPriceByParam( fromProvince, toProvince, status);
            // 对数据进行封装。
            String[] header = {"ID", "发件省份", "目的地省份", "首重", "首重价格", "续重","续重价格", "类型"};
            
          //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();

            //生成一个表格，设置表格名称为"学生表"
            HSSFSheet sheet = workbook.createSheet("快递运费数据");

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
                //将内容对象的文字内容写入到单元格中
                cell.setCellValue(text);
            }
            /*
             * 循环遍历，将数据放入到cell中
             */
            if(result != null && result.size() > 0 ) {
            	for(int i = 0 ; i < result.size() ; i ++ ) {
            		Map<String,Object>  priceItem = result.get(i);
            		if(priceItem != null ) {
            			HSSFRow row = sheet.createRow(i + 1);
            			
            			// 第一列，发送地址所在的省份
            			
            			HSSFCell cell = row.createCell(0);
            			Integer  id = (Integer)priceItem.get("id");
            			String idStr = "";
            			if(id != null ) {
            				idStr =  id + "";
            			}
            			HSSFRichTextString text = new HSSFRichTextString(id + "");
                        cell.setCellValue(text);
            			
            			
            			cell = row.createCell(1);
            			String  fromProvinceName = (String)priceItem.get("fromProvinceName");
            			if(fromProvinceName == null ) {
            				fromProvinceName = "";
            			}
            			text = new HSSFRichTextString(fromProvinceName);
                        cell.setCellValue(text);
            			
                        cell = row.createCell(2);
            			String  toProvinceName = (String)priceItem.get("toProvinceName");
            			if(toProvinceName == null ) {
            				toProvinceName = "";
            			}
            			text = new HSSFRichTextString(toProvinceName);
                        cell.setCellValue(text);
            			
                        cell = row.createCell(3);
                        BigDecimal  firstweight = (BigDecimal)priceItem.get("firstweight");
            			String firstweightInt = "";
                        if(firstweight == null ) {
                        	firstweightInt = "3";
            			}else {
            				firstweightInt = firstweight.intValue() + "";
            			}
            			text = new HSSFRichTextString(firstweightInt);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(4);
                        BigDecimal  firstprice = (BigDecimal)priceItem.get("firstprice");
            			String firstpriceInt = "";
                        if(firstprice == null ) {
                        	firstpriceInt = "0";
            			}else {
            				firstpriceInt = firstprice.floatValue() + "";
            			}
            			text = new HSSFRichTextString(firstpriceInt);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(5);
                        BigDecimal  continueweight = (BigDecimal)priceItem.get("continueweight");
            			String continueweightInt = "";
                        if(continueweight == null ) {
                        	continueweightInt = "1";
            			}else {
            				continueweightInt = continueweight.floatValue() + "";
            			}
            			text = new HSSFRichTextString(continueweightInt);
                        cell.setCellValue(text);
                        
                        cell = row.createCell(6);
                        BigDecimal  continueprice = (BigDecimal)priceItem.get("continueprice");
            			String continuepriceInt = "";
                        if(continueprice == null ) {
                        	continuepriceInt = "0";
            			}else {
            				continuepriceInt = continueprice.floatValue() + "";
            			}
            			text = new HSSFRichTextString(continuepriceInt);
                        cell.setCellValue(text);
                        cell = row.createCell(7);
                        String  type = (String)priceItem.get("type");
                        String typeStr = "";
                        if(StringUtils.equals("1", type)) {
                        	typeStr = "普通件";
            			}else if(StringUtils.equals("2", type)) {
            				typeStr = "多发件";
            			}
            			text = new HSSFRichTextString(typeStr);
                        cell.setCellValue(text);
            		}
                }
            }
            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");
            //这后面可以设置导出Excel的名称，此例中名为student.xls
            response.setHeader("Content-disposition", "attachment;filename=kuaidiprice.xls");
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载
            workbook.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 获取所有地区所有价格信息
     * @param page
     * @return
     */
    @GetMapping("price")
    @CrossOrigin
    public PageVo getByPrice(QueryPageVo page, String status){
        try {
        	String fromProvince = page.getInfo1();
        	String toProvince = page.getInfo2();
        	System.out.println(fromProvince);
        	System.out.println(toProvince);
            PageInfo<Map<String,Object>> result = price.getByPrice(page.getPage(),page.getLimit(), fromProvince, toProvince, status);
            return ResultUtil.exec(result.getPageNum(),result.getPageSize(),result.getTotal(),result.getList());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(),page.getLimit(),0,null);
        }
    }

    /**
     * 添加地区价格信息
     * @param priceObject
     * @return
     */
    @PostMapping("price")
    @CrossOrigin
    public ResultVo addPrice(EforcesPrice priceObject){
        try {
            int result = price.insertSelective(priceObject);
            return ResultUtil.exec(true,"添加成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"添加失败",0);
        }
    }


    /**
     * 删除地区价格信息
     * @param id
     * @return
     */
    @DeleteMapping("price")
    @CrossOrigin
    public ResultVo deletePrice (@RequestBody Integer[] id){
        try {
            int result = price.removeByPrice(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

    /**
     * 修改地区价格信息
     * @param priceObject
     * @return
     */
    @PutMapping("price")
    @CrossOrigin
    public ResultVo updatePrice(EforcesPrice priceObject){
        try {
            int result = price.updateByPrimaryKeySelective(priceObject);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

}
