package org.kuaidi.web.springboot.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.kuaidi.bean.domain.EforcesRectoOrder;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.IEforcesRectoOrderService;
import org.springframework.stereotype.Component;

@Component
public class RectoorderDubboService {
    @Reference(version = "1.0.0")
    IEforcesRectoOrderService rectoOrderService;

    @Reference(version = "1.0.0")
    IEforcesOrderService orderService;
    
	public ResultVo deleteMenusByID(List<Integer> list) {
        try {
            int i = rectoOrderService.deleteById(list);
            if(i>0){
                return ResultUtil.exec(true, "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败！", null);
        }
        return ResultUtil.exec(false, "删除失败！", null);
    }
    
    /**
     * 分页查询全部扫描订单
     * @param page
     * @return
     */
    public PageVo<EforcesRectoOrder> getAll(QueryPageVo page) {
        try {
            PageInfo<EforcesRectoOrder> eforcesUsers = rectoOrderService.getAll(page.getPage(),page.getLimit(),page.getId());
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }

//    /**
//     * 快递员收件交单扫描  并在扫描收件表、物流表、扫描发件中添加该订单的信息
//     *      *
//     * @param number
//     * @return
//     */
//    public ResultVo addRectoorderService(String number){
//        try {
//            EforcesOrder record =  orderService.selectOrderByNumber(number);
//            if(record != null){
//                EforcesRectoOrder data = new EforcesRectoOrder();
//
//                data.setCreatename(record.getCreateusername());
//                data.setCreateid(record.getCreateuserid());//需要上传什么值  直接在下面加
//
//                int a = rectoOrderService.insertRectoOrder(data);
//                if(a>0){
//                    return ResultUtil.exec(true,"扫描收件成功",null);
//                }
//                return ResultUtil.exec(false,"扫描收件失败",null);
//            }
//            return ResultUtil.exec(false,"该订单不存在",null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.exec(false,"扫描操作失败",null);
//        }
//    }
}
