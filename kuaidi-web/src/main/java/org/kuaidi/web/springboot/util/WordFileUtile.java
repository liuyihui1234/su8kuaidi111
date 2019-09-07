package org.kuaidi.web.springboot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.org.apache.poi.util.IOUtils;

import com.deepoove.poi.XWPFTemplate;




public class WordFileUtile {
	
	
	
	public static void convertDocxToPDF(File docx, String pdfPath) throws Exception {  
        OutputStream os = null;  
        try {  
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(docx);  
            Mapper fontMapper = new IdentityPlusMapper();  
            //中文字体转换
            fontMapper.getFontMappings().put("华文行楷", PhysicalFonts.getPhysicalFonts().get("STXingkai"));  
            fontMapper.getFontMappings().put("隶书", PhysicalFonts.getPhysicalFonts().get("LiSu"));
            fontMapper.getFontMappings().put("宋体",PhysicalFonts.getPhysicalFonts().get("SimSun"));
            fontMapper.getFontMappings().put("微软雅黑",PhysicalFonts.getPhysicalFonts().get("Microsoft Yahei"));
            fontMapper.getFontMappings().put("黑体",PhysicalFonts.getPhysicalFonts().get("SimHei"));
            fontMapper.getFontMappings().put("楷体",PhysicalFonts.getPhysicalFonts().get("KaiTi"));
            fontMapper.getFontMappings().put("新宋体",PhysicalFonts.getPhysicalFonts().get("NSimSun"));
            fontMapper.getFontMappings().put("华文行楷", PhysicalFonts.getPhysicalFonts().get("STXingkai"));
            fontMapper.getFontMappings().put("华文仿宋", PhysicalFonts.getPhysicalFonts().get("STFangsong"));
            fontMapper.getFontMappings().put("宋体扩展",PhysicalFonts.getPhysicalFonts().get("simsun-extB"));
            fontMapper.getFontMappings().put("仿宋",PhysicalFonts.getPhysicalFonts().get("FangSong"));
            fontMapper.getFontMappings().put("仿宋_GB2312",PhysicalFonts.getPhysicalFonts().get("FangSong_GB2312"));
            fontMapper.getFontMappings().put("幼圆",PhysicalFonts.getPhysicalFonts().get("YouYuan"));
            fontMapper.getFontMappings().put("华文宋体",PhysicalFonts.getPhysicalFonts().get("STSong"));
            fontMapper.getFontMappings().put("华文中宋",PhysicalFonts.getPhysicalFonts().get("STZhongsong"));
            mlPackage.setFontMapper(fontMapper);  
      
            PdfConversion conversion = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(mlPackage);  
            os = new FileOutputStream(pdfPath);  
             
            conversion.output(os, new PdfSettings());  
        } finally {  
            IOUtils.closeQuietly(os);  
        }  
    }  
    

    public static void main(String[] args) {
        try {
			WordFileUtile.readFileByName("D:\\uploadFiles\\province.docx","D:\\uploadFiles\\","2019077710",
					"洛阳晨道艺术设计有限责任公司","河南省郑州市中原区","410381198402260030", "刘艺辉");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }
    
    
    /***
	 * 
	 * @param path
	 * @param outPath 输出路径
	 * @param contractNum   合同号
	 * @param companyName  公司名字
	 * @param identityNum   身份证
	 * @param legalPerson  法人
	 * @param regionLevl   等级标识
	 * @return
	 * @throws IOException
	 */
	public static void readFileByName(String path,String outPath, String contractNum,final String companyName, final String zoneName,
			final String identityNum, final String legalPerson) throws IOException {
		final LocalDate date = LocalDate.now();
		final LocalDate newDate = date.plus(2, ChronoUnit.YEARS);
		Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("companyName",companyName);
                put("zoneName", zoneName);
                put("idCart",identityNum);
                put("legalPerson",legalPerson);
                put("startYear",date.getYear()+ "");
                put("startMonth",date.getMonthValue()+ "");
                put("startData",date.getDayOfMonth()+ "");
                put("endYear",newDate.getYear() + "");
                put("endMonth",newDate.getMonthValue() + "");
                put("endData",newDate.getDayOfMonth() + "");
                put("year",date.getYear() + "");
                put("month",date.getMonthValue() + "");
                put("data",date.getDayOfMonth() + "");
            }
        };
		
        XWPFTemplate template = XWPFTemplate.compile(path)
                .render(datas);
        FileOutputStream out = null;
		try {
			out = new FileOutputStream( outPath + contractNum  +".docx");
			template.write(out);
            out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(out != null ) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(template != null) {
				try {
					template.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
