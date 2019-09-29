package org.kuaidi.web.springboot.controller.maintainance;

import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesGoodsTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/Goods/")
public class GoodsTypeController {

    //@Reference(version = "1.0.0")
   // IEforcesGoodsTypeService goodService;

    @RequestMapping("Goods")
    @ResponseBody
    @CrossOrigin
    public ResultVo selectAll(){
        try {
            JSONArray  data = new JSONArray();
            JSONObject  item = new JSONObject();
            item.put("name","物品");
            item.put("id","1");
            data.add(item);
            item = new JSONObject();
            item.put("name","文件");
            item.put("id","2");
            data.add(item);


//            List<String> list = goodService.selectAll();
            return ResultUtil.exec(true,"查询成功",data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"查询失败",null);
        }
    }
}
