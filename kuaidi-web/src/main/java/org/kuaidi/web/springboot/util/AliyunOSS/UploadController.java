package org.kuaidi.web.springboot.util.AliyunOSS;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by lightClouds917
 * Date 2019/7/27
 * Description:文件上传
 */
@Controller
@RequestMapping("/upload/")
public class UploadController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 文件上传
     * @param file
     */
    @RequestMapping(value = "uploadBlog")
    @ResponseBody
    public String uploadBlog(MultipartFile file){
        logger.info("============>文件上传");
        try {
            if(null != file){
                String filename = file.getOriginalFilename();
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    os.flush();
                    file.transferTo(newFile);
                    //上传到OSS
                    String uploadUrl = AliyunOSSUtil.upload(newFile);

                    //上传图片的时候图片会保留在本地项目
                    File file1 = new File("");
                    String s = file1.getAbsolutePath();
                    DeleteFileUtil.delete(s + "\\" + filename);
                    logger.info("上传图片路径"+uploadUrl);
                    //return "上传成功";
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "上传成功";
    }

    @RequestMapping(value = "toUploadBlog",method = RequestMethod.GET)
    public String toUploadBlog(){
        return "upload";
    }
}