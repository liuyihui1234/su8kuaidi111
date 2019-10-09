package org.kuaidi.web.springboot.webcontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.kuaidi.bean.domain.EforcesHomepagepic;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesHomepagepicService;
import org.kuaidi.web.springboot.util.AliyunOSS.AliyunOSSUtil;
import org.kuaidi.web.springboot.util.AliyunOSS.DeleteFileUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/web/Homepagepic/")
public class EforcesHomepagepicController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version="1.0.0")
    IEforcesHomepagepicService homepagepicService;

    @RequestMapping("HomePage1")
    @ResponseBody
    @CrossOrigin
    public ResultVo getListMsg (){
        try {
            List<EforcesHomepagepic> result = homepagepicService.getListMsg();
            return ResultUtil.exec(true,"查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }

    /**
     * 修改轮播图
     * @param homepagepic
     * @return
     */
    @RequestMapping("HomePage2")
    @ResponseBody
    @CrossOrigin
    public ResultVo updateHomePage (EforcesHomepagepic homepagepic){
        try {
            int result = homepagepicService.updateByPrimaryKeySelective(homepagepic);
            return ResultUtil.exec(true,"修改成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"修改失败",0);
        }
    }

    @RequestMapping("HomePage3")
    @ResponseBody
    @CrossOrigin
    public ResultVo addHomePage(MultipartFile file,HttpServletRequest request){
        logger.info("============>文件上传");
        try {
            if(file==null){
                System.out.println("2*******************************");
            }
            if(file != null) {
                String filename =file.getOriginalFilename(); //得到上传时的文件名
                if(!"".equals(filename.trim())){ //判断文件名不是空的话去掉文件名两边的空格
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    os.flush();
                    //上传到OSS
                    String uploadUrl = AliyunOSSUtil.upload(newFile);//上传成功返回路径
/*                    homepagepic.setPicpath(Config.oosUrlPath + uploadUrl);
                    homepagepic.setCrttime(new Date());*/
                    //上传后的图片会滞留在项目根目录
                    File file1 = new File(""); //创建file对象
                    String filePath = file1.getAbsolutePath(); //得到上传图片路径
                    DeleteFileUtil.delete(filePath + filename); //调用工具类把项目本地图片删除
                    logger.info("上传图片路径" + uploadUrl);
                }
                //int result = homepagepicService.insertSelective(homepagepic);
                return ResultUtil.exec(true, "添加成功",0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.exec(false,"添加失败",0);
    }

    /**
     * 删除轮播图
     * @param id
     * @return
     */
    @RequestMapping("HomePage4")
    @ResponseBody
    @CrossOrigin
    public ResultVo removeHomePage(@RequestBody Integer[] id){
        try {
            int result = homepagepicService.delete(id);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",0);
        }
    }

}
