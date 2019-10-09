package org.kuaidi.web.springboot.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesBaggingScan;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesRemovingBagScan;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.kuaidi.utils.JBarCodeUtil;
import org.kuaidi.web.springboot.util.redis.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class BaggingScanService {
	@Reference(version = "1.0.0")
	IEforcesBiggingScanService scanService;

    @Autowired
    private OrderUtil orderUtil ;

    @Value("${file.baseDir}")
    private String baseDir;

    @Reference(version = "1.0.0")
    private IEforcesBiggingScanService  biggingScanService;


	public PageVo<EforcesBaggingScan> getAll(QueryPageVo page,String number) {
        try {
            PageInfo<EforcesBaggingScan> eforcesUsers = scanService.getAll(page.getPage(),page.getLimit(),number);
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }

    public ResultVo webMakeBagScan(EforcesBaggingScan record , EforcesIncment eforcesIncment, EforcesUser eforcesUser ) {
//        // 生成包号
        String bagNumber;
        if(StringUtils.isEmpty(record.getCode())){
            bagNumber = orderUtil.getOrderNumber(eforcesUser.getIncid());
        }else{
            bagNumber=record.getCode();
        }
        String billsnumber = record.getNumberlist();
        String[] split = {};
        if(StringUtils.isNotEmpty(billsnumber)){
            split = billsnumber.split("\\s+");
        }else {
            return ResultUtil.exec(false, "运单号不能为空", null);
        }
        Set<String> set = new HashSet<String>();
        for (String str : split) {
            if(StringUtils.isNotEmpty(str)){
                System.err.println(str);
                set.add(str);
            }
        }
        List<EforcesBaggingScan> list = new ArrayList<>();
        for (String str1 :
                set) {
            EforcesBaggingScan  baggingScan = new EforcesBaggingScan();
            baggingScan.setCode(bagNumber);
            baggingScan.setNumberlist(str1);
            baggingScan.setNum(1);
            //装袋用户的编号
            //baggingScan.setBaggingid(bagNumber);

            baggingScan.setBaggingname(record.getBaggingname());
            baggingScan.setCreateid(eforcesUser.getNumber());
            baggingScan.setCreatename(eforcesUser.getName());
            baggingScan.setIncid(eforcesUser.getIncid());
            baggingScan.setIncname(eforcesIncment.getName());
            list.add(baggingScan);
        }
        int rst = 0 ;
        if(list.size() > 0 ) {
            rst = biggingScanService.addRecordList(list);
            if(rst > 0 ) {
            	return  ResultUtil.exec(true, "添加成功",bagNumber);
            }else {
            	return  ResultUtil.exec(false, "添加记录失败",null);
            }
        }
       /* if(rst > 0 ) {
            JSONObject rstData = new JSONObject();
            rstData.put("bagName", record.getBaggingname());
            rstData.put("bagNumber", bagNumber);
            String barPath = JBarCodeUtil.createBarcode(bagNumber, baseDir, "");
            rstData.put("barPath",barPath);

        }*/
        return  ResultUtil.exec(false, "添加失败！", null);
    }
}
