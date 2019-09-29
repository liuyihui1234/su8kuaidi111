package org.kuaidi.web.springboot.controller;

import org.kuaidi.bean.Config;
import org.kuaidi.utils.UUIDUtil;
import org.kuaidi.utils.VerifyUtil;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class WebVerifyController {
    /**
     * web验证码生成
     */


    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("vCode")
    @CrossOrigin
    public HashMap<String, String> valicode(@RequestBody(required = false) String name,Model model) throws Exception {
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.createImage();
        //将验证码存入Session
        // session.setAttribute("imageCode",objs[0]);
        String uuid = UUIDUtil.getUUID();
        //将验证码信息存入redis
        redisUtil.set(Config.WEBCODE + uuid, objs[0], 300);
        //将图片转正base64
        BufferedImage image = (BufferedImage) objs[1];
        //转base64
        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        ImageIO.write(image, "png", baos);//写入流中
        byte[] bytes = baos.toByteArray();//转换成字节
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        //删除 \r\n
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
        HashMap map = new HashMap<String, String>();
        map.put(uuid, png_base64);
        return map;
    }

    @ModelAttribute
    public String before(@RequestBody(required = false) String name, Model model){
       return  name;
    }


    @RequestMapping("file")
    @CrossOrigin
    public void valicode1(HttpServletRequest request) throws Exception {
        System.out.println(request);
    }


}
