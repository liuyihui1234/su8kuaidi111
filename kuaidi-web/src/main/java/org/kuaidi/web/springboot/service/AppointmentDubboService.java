package org.kuaidi.web.springboot.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.Config;
import org.kuaidi.bean.domain.EforcesAppointment;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.AppointmentService;
import org.kuaidi.iservice.IEforcesOrderService;
import org.kuaidi.iservice.UserService;
import org.kuaidi.web.springboot.util.redis.OrderUtil;
import org.kuaidi.web.springboot.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentDubboService {
	
    @Reference(version = "1.0.0")
    AppointmentService appointmentService;

    @Reference(version = "1.0.0")
    UserService userService;

    @Reference(version = "1.0.0")
    IEforcesOrderService iEforcesOrderService;

    @Autowired
    OrderUtil orderUtil;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 用户预约下单
     * @param eforcesAppointment
     * @return
     */
    public ResultVo addAppointment(EforcesAppointment eforcesAppointment){
        try {
            eforcesAppointment.setNumber(orderUtil.getOrderNumber(eforcesAppointment.getFromareastreet()));
            int a = appointmentService.insertAppointment(eforcesAppointment);
            if(a>0){
                return ResultUtil.exec(true,"下单成功",null);
            }
            return ResultUtil.exec(false,"下单失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }

    /**
     * 根据该快递员所负责的范围的recipientid来查找范围内的预约单
     * @param id
     * @return
     */
    public PageVo findAppointment(Integer pageNum, Integer pageSize, Integer id) throws IOException {
        try {
            EforcesUser eforcesUser = userService.selectUserById(id);
            String fromarea2 = eforcesUser.getRecipientcode();//获取该用户的负责范围id
            String[] fromarea1 = fromarea2.split(",");
            List<String> fromarea = new ArrayList<String>();

            if(fromarea1.length > 0){
                for(int i = 0 ; i < fromarea1.length ; i++) {
                    fromarea.add(fromarea1[i]);
                }
            }
            if(fromarea.size()>0){
                if(pageSize == null ||  pageSize <= 0 ){
                    pageSize = Config.pageSize;
                }
                if(pageNum == null || pageNum <= 0 ){
                    pageNum = 1;
                }
                PageInfo<EforcesAppointment> pageInfo = appointmentService.selectAppointmentByRecipient(pageNum,pageSize,fromarea);
                return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
            }
            return ResultUtil.exec(pageNum,pageSize,0,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(pageNum,pageSize,0,null);
        }
    }


    /**
     * 根据快递员所属的街道 查找对应的预约单
     * g
     * @param pageNum
     * @param pageSize
     * @param fromareastreet
     * @return
     */
    public PageVo findAppointmentByStreet(Integer pageNum, Integer pageSize, String fromareastreet){
        PageInfo<EforcesAppointment> pageInfo = null;
        try {
            if(pageSize == null ||  pageSize <= 0 ){
                pageSize = Config.pageSize;
            }
            if(pageNum == null || pageNum <= 0 ){
                pageNum = 1;
            }
            pageInfo = appointmentService.selectAppointmentByStreet(pageNum,pageSize,fromareastreet);
            return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(pageNum,pageSize,0, null);
        }
    }

    /**
           * 快递员接单，根据订单id修改订单状态为已接单（status为1）
     * @param id
     * @param acceptid
     * @param acceptname
     * @return
     */
    public ResultVo alterStatus1ById(Integer id,String acceptid,String acceptname){
        try {
            EforcesAppointment appointment = appointmentService.selectAppointmentByPrimaryKey(id);
            if (appointment != null) {
                int status = appointment.getStatus();
                if (status == 0) {
                    int data = appointmentService.updateStrtus1ById(id,acceptid,acceptname);
                    if (data > 0) {
                        return ResultUtil.exec(true, "快递员已接单", null);
                    }
                    return ResultUtil.exec(false, "接单失败", null);
                }
                return ResultUtil.exec(false, "该单已被他人接单", null);
            }
            return ResultUtil.exec(false, "该单不存在", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }


    /**
     * 根据快递员的编号查询该快递员的待收件订单
     * @param acceptid
     * @return
     */
    public PageVo findAppointment1ByAcceptid(Integer pageNum, Integer pageSize, String acceptid)throws IOException{
        try {
            if(pageSize == null ||  pageSize <= 0 ){
                pageSize = Config.pageSize;
            }
            if(pageNum == null || pageNum <= 0 ){
                pageNum = 1;
            }
            PageInfo<EforcesAppointment> pageInfo = appointmentService.selsctStatus1ByAcceptid(pageNum,pageSize,acceptid);
            if(pageInfo != null && pageInfo.getSize() > 0){
                return ResultUtil.exec(pageNum,pageSize,pageInfo.getTotal(), pageInfo.getList());
            }
            return ResultUtil.exec(pageNum, pageSize, 0,null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(pageNum, pageSize, 0,null);
        }
    }

    /**
     * 修改appointment中的状态 并在order中添加该订单
     * g
     * @param appointmentId
     * @return
     */
    public ResultVo alterStatus2AddOrder(Integer appointmentId){
        try {
            EforcesAppointment appointment = appointmentService.selectAppointmentByPrimaryKey(appointmentId);
            if(appointment != null){
                if(appointment.getStatus() == 1){
                    int data = appointmentService.updateStrtus2ById(appointmentId);
                    if(data>0){
                        //数据传递
                        EforcesOrder record = new EforcesOrder();
                        record.setNumber(appointment.getNumber());
                        record.setFromname(appointment.getFromname());
                        record.setFromcity(appointment.getFromcity());
                        record.setFromcityname(appointment.getFromcityname());
                        record.setFromprovince(appointment.getFromprovince());
                        record.setFromprovincename(appointment.getFromprovincename());
                        record.setFromarea(appointment.getFromarea());
                        record.setFromareaname(appointment.getFromareaname());
                        record.setFromareastreet(appointment.getFromareastreet());
                        record.setFromareastreetname(appointment.getFromareastreetname());
                        record.setFromincname(appointment.getFromincname());
                        record.setFromaddress(appointment.getFromaddress());
                        record.setFromtel(appointment.getFromtel());
                        record.setFromcode(appointment.getFromcode());
                        record.setToguestname(appointment.getToguestname());
                        record.setToguestcode(appointment.getToguestcode());
                        record.setToprovince(appointment.getToprovince());
                        record.setToprovincename(appointment.getToprovincename());
                        record.setTocity(appointment.getTocity());
                        record.setTocityname(appointment.getTocityname());
                        record.setToarea(appointment.getToarea());
                        record.setToareaname(appointment.getToareaname());
                        record.setFromareastreet(appointment.getFromareastreet());
                        record.setFromareastreetname(appointment.getFromareastreetname());
                        record.setToincname(appointment.getToincname());
                        record.setToaddress(appointment.getToaddress());
                        record.setToname(appointment.getToname());
                        record.setTocode(appointment.getTocode());
                        record.setTotel(appointment.getTotel());
                        record.setPaymentflag(appointment.getPaymentflag());
                        record.setPaymentforgoods(appointment.getPaymentforgoods());
                        record.setIsfile(appointment.getIsfile());
                        record.setIsgoods(appointment.getIsgoods());
                        record.setFilename(appointment.getFilename());
                        record.setNum(appointment.getNum());
                        record.setWeight(appointment.getWeight());
                        record.setLongs(appointment.getLongs());
                        record.setWidths(appointment.getWidths());
                        record.setHeights(appointment.getHeights());
                        record.setVolume(appointment.getVolume());
                        record.setIscollection(appointment.getIscollection());
                        record.setPaymode(appointment.getPaymode());
                        record.setIsinsured(appointment.getIsinsured());
                        record.setInsuredsumprice(appointment.getInsuredsumprice());
                        record.setInsuredprice(appointment.getInsuredprice());
                        record.setIsmobilesend(appointment.getIsmobilesend());
                        record.setMobileprice(appointment.getMobileprice());
                        record.setPrice(appointment.getPrice());
                        record.setSendmode(appointment.getSendmode());
                        record.setModeprice(appointment.getModeprice());
                        record.setSumprice(appointment.getSumprice());
                        record.setCreateuserid(appointment.getAcceptid());
                        record.setCreateusername(appointment.getAcceptname());

                        int data2=iEforcesOrderService.insertSelective(record);
                        if(data2>0){
                            return ResultUtil.exec(true,"收件成功",null);
                        }
                        return ResultUtil.exec(false,"收件异常",null);
                    }
                    return ResultUtil.exec(false,"修改状态订单状态异常",null);
                }
                return ResultUtil.exec(false,"该订单已收件",null);
            }
            return ResultUtil.exec(false,"该订单不存在",null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
    /**
     * 预约单管理
     * @param
     * @return
     */
    public PageVo getMsg(QueryPageVo page, EforcesAppointment Eforces)throws IOException{
        try {
            PageInfo<EforcesAppointment> pageInfo = appointmentService.getlistAllMsg(page,Eforces);

            return ResultUtil.exec(pageInfo.getPageNum(), page.getLimit(),pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(page.getPage(), page.getLimit(), 0,null);
        }
    }

    /**
     * 删除预约单管理
     * @param id
     * @return
     */
    public ResultVo deleteappointment(Integer id){
        try {
            Integer[] array = {id};
            int result = appointmentService.deleteAppointment(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",null);
        }
    }

    /**
     * 删除预约单管理
     * @param array
     * @return
     */
    public ResultVo deleteappointments(@RequestBody Integer[] array){
        try {
            int result = appointmentService.deleteAppointment(array);
            return ResultUtil.exec(true,"删除成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"删除失败",null);
        }
    }

    public ResultVo updateByPrimaryKeySelective(EforcesAppointment record){
        try {
            int a = appointmentService.updateByPrimaryKeySelective(record);
            if(a > 0){
                return ResultUtil.exec(true,"修改成功",null);
            }
            return ResultUtil.exec(false,"修改失败",null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"操作失败",null);
        }
    }
}
