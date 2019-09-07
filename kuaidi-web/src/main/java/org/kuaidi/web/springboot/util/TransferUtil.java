package org.kuaidi.web.springboot.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.bean.trackingmore.BatchVo;
import org.kuaidi.bean.trackingmore.GetVo;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/8/14 15:26
 */
@Component
public class TransferUtil {

    @Autowired
    private CorpListUtil corpListUtil;

    @Reference(version = "1.0.0")
    IEforceslogisticstrackingService logisticstracking ;

    @Reference(version = "1.0.0")
    IEforcesTransportedscanService transportedscanService;

    @Scheduled(cron = "0,30 * * * * ? ")
    public void sayHello() {
        Map<Integer, String> corpMap = corpListUtil.getCorpMap();//获取快递公司对应的第三方编号map

        int batchLimit = 4;//每页显示的数据量(第三方创建多个运单号，一次最多40条订单)
        PageInfo<EforcesTransportedscan> pageInfo = transportedscanService.selectAllByState0(1, batchLimit);//分页获取运输中的订单
        if(pageInfo.getSize() > 0){
//          分页循环在第三方平台查询数据
            for (int k = 1; k < pageInfo.getPages()+1; k++) {
                PageInfo<EforcesTransportedscan> pageInfo1 = transportedscanService.selectAllByState0(k, batchLimit);//获取第k页的数据
                List<EforcesTransportedscan> pageList = pageInfo1.getList();//获取第k页中list里面的主要数据

                List<String> list = new ArrayList();
                for (int i = 0; i < pageList.size(); i++){
                    String number = corpMap.get(pageList.get(i).getNextcorpid());//获取快递公司对应的第三方编号
                    String nextNumber = pageList.get(i).getNextnumber();//获取转运后的单号
                    String billsnumber = pageList.get(i).getBillsnumber();//获取原定单号

                    String requestData = "{\"tracking_number\":\""+nextNumber+"\",\"carrier_code\":\""+number+"\",\"order_id\":\""+billsnumber+"\"}";
                    list.add(requestData);
                }

                String batchUrlStr = null;
                String batchRequestData = list.toString();
                try {
                    String batchResult = new Tracker().orderOnlineByJson(batchRequestData,batchUrlStr,"batch");
                    System.out.println("创建多个运单号返回结果："+batchResult);

                    BatchVo batchVo = new Gson().fromJson(batchResult, BatchVo.class);//Gson解析数据
                    BatchVo.MetaBean meta = batchVo.getMeta();//获取返回数据中的Meta

//                    if(meta.getCode() == 200){
//                        System.out.println(batchResult);
//                    }else if(meta.getCode() == 201){
//                        System.out.println(batchResult);
//                        BatchTrue.DataBean data = batchTrue.getData();//获取返回数据中的Data
//                        List<BatchTrue.DataBean.TrackingsBean> trackings = data.getTrackings();//获取Data的trackings
//                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("转运中的订单全部添加到第三方平台");

            //分页获取第三方返回的物流详情
            //mysql中要修改运输状态的订单号
            List<String> updateList = new ArrayList<>();

            int getLimit = 2;//每页显示的数据量(第三方获取多个运单号的物流信息，一次最多2000条订单)
            String getUrlStr ="?page=1&limit="+getLimit+"";
            String getRequestData=null;
            try {
                //先查一遍，方便获取中页数，获取下面循环需要的数据
                String getResult = new Tracker().orderOnlineByJson(getRequestData,getUrlStr,"get");
                GetVo getVo = new Gson().fromJson(getResult, GetVo.class);
                GetVo.DataBean data = getVo.getData();
                int total = Integer.parseInt(data.getTotal()) ;
                int pages = total%getLimit == 0 ? (total/getLimit):(total/getLimit)+1;//获取总页数
                System.out.println("总共"+pages+"页，每页"+getLimit+"条数据！！！！");
                //暂停1秒，获取多个运单号的物流信息的第三方接口一秒执行一次
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.exit(0);//退出程序
                }

                for (int j = 1; j < pages + 1; j++) {
                    List<String> deleteList = new ArrayList<>();//保存当前页的所有订单，删除当页订单调用

                    List<EforcesLogisticStracking> logList = new ArrayList<>();//保存当前页所有订单的物流详细信息，转存数据的时候调用
                    String getUrlStr1 = "?page="+j+"&limit="+getLimit+"";
                    try {
                        //开始拿数据
                        String getResult1 = new Tracker().orderOnlineByJson(getRequestData,getUrlStr1,"get");
                        System.out.println("获取多个运单号的物流信息返回结果："+getResult1);
                        GetVo getVo1 = new Gson().fromJson(getResult1, GetVo.class);
                        List<GetVo.DataBean.ItemsBean> items = getVo1.getData().getItems();
                        for (int m = 0; m < items.size(); m++) {
                            //获取items中的数据
                            GetVo.DataBean.ItemsBean itemsBean = items.get(m);
                            //获取当前的订单号
                            String tracking_number = itemsBean.getTracking_number();
                            //获取当前订单运输公司的编号
                            String carrier_code = itemsBean.getCarrier_code();
                            //获取当前订单的原订单号
                            String order_id = itemsBean.getOrder_id().toString();

                            //把tracking_number和carrier_code拼接成删除订单要用的格式,并放到deleteList中
                            String deleteRequestData="{\"tracking_number\": \""+tracking_number+"\",\"carrier_code\":\""+carrier_code+"\"}";
                            deleteList.add(deleteRequestData);
                            System.out.println("deleteList"+deleteList);

                            //获取当前订单的状态
                            String status = itemsBean.getStatus();
                            //把运输完成的订单放到updateList中
                            if(status.equals("pickup")||status.equals("delivered")){
                                updateList.add(order_id);
                            }


                            //获取Origin_info中的数据
                            GetVo.DataBean.ItemsBean.OriginInfoBean origin_info = itemsBean.getOrigin_info();
                            //获取Trackinfo中的数据
                            List<GetVo.DataBean.ItemsBean.OriginInfoBean.TrackinfoBean> trackinfo = origin_info.getTrackinfo();
                            if(trackinfo != null){
                                for (int n = 0; n < trackinfo.size(); n++) {
                                    GetVo.DataBean.ItemsBean.OriginInfoBean.TrackinfoBean trackinfoBean = trackinfo.get(n);

                                    //获取当前记录的创建时间
                                    String date = trackinfoBean.getDate();
                                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date time = format.parse(date);//转换格式

                                    //获取当前记录的详细描述
                                    String statusDescription = trackinfoBean.getStatusDescription();

                                    //获取log表中对应单号的最后一条物流信息的添加时间
                                    Date operationtime = logisticstracking.selectMaxTime(order_id);

                                    if(operationtime == null || operationtime.compareTo(time) < 0){
                                        EforcesLogisticStracking  logisticStracking = new EforcesLogisticStracking();
                                        logisticStracking.setOperationtime(time);
                                        logisticStracking.setBillsnumber(order_id);
                                        logisticStracking.setDescription(statusDescription);
                                        logList.add(logisticStracking);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(logList.size()>0){
                        int a = logisticstracking.insertLogList(logList);//同步物流信息
                        System.out.println("_______________________第"+j+"页订单,物流信息同步完成,共"+a+"条数据__________________________");
                    }else {
                        System.out.println("_______________________第"+j+"页订单,暂无物流信息更新________________________________________");
                    }

                    //删除第三方平台上的多个运单号
                    String deleteUrlStr =null;
                    String deleteRequestData= deleteList.toString();

                    try {
                        String deleteResult = new Tracker().orderOnlineByJson(deleteRequestData,deleteUrlStr,"delete");
                        System.out.println("删除："+deleteResult);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    //暂停1秒，获取多个运单号的物流信息的第三方接口一秒执行一次
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        System.exit(0);//退出程序
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("物流信息转运记录全部同步完成");

            if(updateList.size()>0){
                int updateCode = transportedscanService.updateState(updateList);
                if(updateCode > 0){
                    System.out.println("转运运输完成订单，状态修改完成");
                }else {
                    System.out.println("暂无转运运输完成订单");
                }
            }

        }else {
            System.out.println("暂无转运订单");
        }
    }
}
