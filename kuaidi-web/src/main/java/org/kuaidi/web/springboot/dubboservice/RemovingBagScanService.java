package org.kuaidi.web.springboot.dubboservice;

import org.apache.commons.lang3.StringUtils;
import org.kuaidi.bean.domain.*;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesBiggingScanService;
import org.kuaidi.iservice.IEforcesRemovingBagScanService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;

@Component
public class RemovingBagScanService {
	@Reference(version = "1.0.0")
    IEforcesRemovingBagScanService scanService;

    @Reference(version = "1.0.0")
    IEforcesBiggingScanService biggingScanService;


	public PageVo<EforcesRemovingBagScan> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesRemovingBagScan> eforcesUsers = scanService.getAll(page.getPage(),page.getLimit(),page.getId());
            
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }

    public ResultVo removeBagScan(EforcesRemovingBagScan record, EforcesUser eforcesUser, EforcesIncment eforcesIncment) {
        // TODO Auto-generated method stub
        /*
         * 根据bagNumber 查询对应的发包记录
         */


        if(StringUtils.isEmpty(record.getBaggingid())) {
            return ResultUtil.exec(false, "包的编号不能为空", null);
        }
        try {
            List<EforcesBaggingScan> bagScanList =  biggingScanService.getBaggingScanByBagNum(record.getBaggingid());
            /*             * 将打包的数据，放到手包中去。
             **/

            if(bagScanList != null && bagScanList.size() > 0 ) {
                List<EforcesRemovingBagScan>  removeBagScanList = new ArrayList<EforcesRemovingBagScan>();
                for(int i = 0 ; i < bagScanList.size() ; i++) {
                    EforcesBaggingScan  bagScannInfo = bagScanList.get(i);
                    if(bagScannInfo != null) {
                        EforcesRemovingBagScan  removeScanInfo = new EforcesRemovingBagScan();
                        removeScanInfo.setCode(bagScannInfo.getCode());
                        removeScanInfo.setNumberlist(bagScannInfo.getNumberlist());
                        removeScanInfo.setNum(bagScannInfo.getNum());
                        removeScanInfo.setBaggingid(record.getBaggingid());
                        removeScanInfo.setBaggingname(bagScannInfo.getBaggingname());
                        removeScanInfo.setCreateid(eforcesUser.getNumber());
                        removeScanInfo.setCreatename(eforcesUser.getName());
                        removeScanInfo.setIncid(eforcesIncment.getNumber());
                        removeScanInfo.setIncname(eforcesIncment.getName());
                        removeBagScanList.add(removeScanInfo);
                    }
                }
                if(removeBagScanList.size() > 0 ) {
                    scanService.addRecordList(removeBagScanList);
                }
            }
            return ResultUtil.exec(true, "添加成功！", null);
        }catch(Exception e ) {
            e.printStackTrace();
            return ResultUtil.exec(false, "添加失败！", null);
        }
    }
}
