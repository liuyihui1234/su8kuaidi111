package org.kuaidi.web.springboot.webcontroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesShareProfit;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesIncmentProfitService;
import org.kuaidi.iservice.IEforcesShareProfitService;
import org.kuaidi.web.springboot.util.ProfitShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/web/shareprofit/")
public class EforcesShareProfitController {
	
	@Reference(version = "1.0.0")
	private IEforcesIncmentProfitService  profitService; 
	
	@Reference(version = "1.0.0")
	private IEforcesShareProfitService  shareProfitService; 
	
	@Autowired
	private ProfitShare profitShare; 
	
	/*
	 * 文件导出
	 */
	@RequestMapping("getShareProfitByParam")
    @CrossOrigin
    public void getPriceExcelByParam(HttpServletResponse response ,String fromProvince , String toProvince, Integer status){
        try {
        	List<Map<String, Object>> list = shareProfitService.getShareProfitByParam(fromProvince, toProvince, status);
        	String[] header = {"ID", "发件省份", "目的地省份", "分润总金额", "省级分润", "城市分润",
        			"区/县分润","网点分润","公司分润","重量", "类型"};
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
              
             // 封装数据
            if(list != null && list.size() > 0 ) {
            	 for(int i = 0 ; i < list.size() ; i++) {
            		 Map<String, Object>  shareProfitItem = list.get(i);
            		 if(shareProfitItem != null ) {
            			 HSSFRow row = sheet.createRow(i + 1);
            			 
            			 HSSFCell cell = row.createCell(0);
            			 Integer  id = (Integer)shareProfitItem.get("id");
     	      			 String idStr = "";
     	      			 if(id != null ) {
     	      				 idStr =  id + "";
     	      			 }
     	      			 HSSFRichTextString text = new HSSFRichTextString(id + "");
     	                 cell.setCellValue(text);
            			 
     	                cell = row.createCell(1);
            			String  fromProvinceName = (String)shareProfitItem.get("fromprovincename");
            			if(fromProvinceName == null ) {
            				fromProvinceName = "";
            			}
            			text = new HSSFRichTextString(fromProvinceName);
                        cell.setCellValue(text);
            			
                        cell = row.createCell(2);
            			String  toProvinceName = (String)shareProfitItem.get("toprovincename");
            			if(toProvinceName == null ) {
            				toProvinceName = "";
            			}
            			text = new HSSFRichTextString(toProvinceName);
                        cell.setCellValue(text);
                        
                        
                        cell = row.createCell(3);
                        Double  total = (Double)shareProfitItem.get("total");
            			if(total == null ) {
            				total = 0D;
            			}
            			text = new HSSFRichTextString(total + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(4);
                        Double  province = (Double)shareProfitItem.get("province");
            			if(province == null ) {
            				province = 0D;
            			}
            			text = new HSSFRichTextString(province + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(5);
                        Double  city = (Double)shareProfitItem.get("city");
            			if(city == null ) {
            				city = 0D;
            			}
            			text = new HSSFRichTextString(city + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(6);
                        Double  area = (Double)shareProfitItem.get("area");
            			if(area == null ) {
            				area = 0D;
            			}
            			text = new HSSFRichTextString(area + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(7);
                        Double  areastreet = (Double)shareProfitItem.get("areastreet");
            			if(areastreet == null ) {
            				areastreet = 0D;
            			}
            			text = new HSSFRichTextString(areastreet + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(8);
                        Double  company = (Double)shareProfitItem.get("company");
            			if(company == null ) {
            				company = 0D;
            			}
            			text = new HSSFRichTextString(company + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(9);
                        Integer  weight = (Integer)shareProfitItem.get("weight");
            			if(weight == null ) {
            				weight = 1;
            			}
            			text = new HSSFRichTextString(weight + "");
                        cell.setCellValue(text);
                        
                        cell = row.createCell(10);
                        Integer  type = (Integer)shareProfitItem.get("type");
                        String typeName = "";
            			if(type != null  && type == 1 ) {
            				typeName = "发件首重";
            			}else if(type != null  && type == 2 ) {
            				typeName = "发件续重";
            			}else if(type != null  && type == 3 ) {
            				typeName = "收件首重";
            			}else if(type != null  && type == 4 ) {
            				typeName = "收件续重";
            			}
            			text = new HSSFRichTextString(typeName);
                        cell.setCellValue(text);
            		 }
            	 }
              }
              // 设置输出
              //准备将Excel的输出流通过response输出到页面下载
              //八进制输出流
              response.setContentType("application/octet-stream");
              //这后面可以设置导出Excel的名称，此例中名为student.xls
              response.setHeader("Content-disposition", "attachment;filename=kuaidiShareProfit.xls");
              //刷新缓冲
              response.flushBuffer();
              //workbook将Excel写入到response的输出流中，供页面下载
              workbook.write(response.getOutputStream());
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	@ResponseBody
    @RequestMapping("getProfitByParam")
    @CrossOrigin
    public PageVo doFindIncmentByNumber(QueryPageVo page){
        try {
        	Integer pages = page.getPage();
        	if(pages == null ) {
        		pages = 1 ; 
        	}
        	Integer pageSize = page.getLimit();
        	if(pageSize == null) {
        		pageSize = Config.pageSize;
        	}
        	String parentId = page.getInfo1();
        	String incName = page.getInfo2();
        	PageInfo<Map<String, Object>> pageInfo = profitService.getIncmentProfitByPage(pages, pageSize, parentId, incName);
        	return ResultUtil.exec(pageInfo.getPageNum(),pageInfo.getSize(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1,10,0,null);
        }
    }
	
	/*
	 * 测试分润
	 */
	@ResponseBody
    @RequestMapping("shareProfitDetail")
    @CrossOrigin
    public PageVo shareProfitDetail(String  billsNums){
		profitShare.shareProfit(billsNums);
		return null; 
	}
	
	/**
             * 获取所有地区所有价格信息
     * @param page
     * @return
     */
    @GetMapping("profit")
    @CrossOrigin
    public PageVo getProfitByParam(QueryPageVo page, Integer status) {
    	String fromProvince = page.getInfo1();
    	String toProvince = page.getInfo2();
    	try {
    		PageInfo<Map<String, Object>> pageInfo = shareProfitService.getShareProfitByPage(page.getPage(), page.getLimit(), fromProvince, toProvince, status);
    		if(pageInfo != null ) {
    			return ResultUtil.exec(pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal(), "获取分润信息成功！", pageInfo.getList());
    		}
    	}catch(Exception e ) {
    		e.printStackTrace();
    	}
    	return ResultUtil.exec(page.getPage(), page.getLimit(),0 , "获取分润信息失败！", null); 
    }
    
    /**
          * 获取所有地区所有价格信息
	* @param page
	* @return
	*/
	@PostMapping("profit")
	@CrossOrigin
	public ResultVo addShareProfit(EforcesShareProfit  shareProfit) {
		try {
			Integer rst = shareProfitService.saveShareProfit(shareProfit);
			if(rst > 0 ) {
				return ResultUtil.exec(true, "添加成功", null);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.exec(false, "添加失败", null);
	}
	
	/*
	 * 删除
	 */
    @DeleteMapping("profit")
    @CrossOrigin
    public ResultVo deleteShareProfit (@RequestBody Integer[] id){
        try {
            int result = shareProfitService.removeByIds(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }
    
	/*
	 * 修改
	 */
	@PutMapping("profit")
	@CrossOrigin
	public ResultVo updateShareProfit(EforcesShareProfit  shareProfit) {
		try {
			Integer rst = shareProfitService.updateShareProfit(shareProfit);
			if(rst > 0 ) {
				return ResultUtil.exec(true, "修改成功", null);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.exec(false, "修改失败", null);
	}
    
}
