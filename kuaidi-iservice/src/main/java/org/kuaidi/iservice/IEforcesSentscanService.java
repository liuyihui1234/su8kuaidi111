package org.kuaidi.iservice;

import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesSentScan;
import org.kuaidi.bean.vo.DubboMsgVO;

import com.github.pagehelper.PageInfo;

public interface IEforcesSentscanService {
    /**
     * 获取数量
     * @param incid
     * @return
     */
    int getSentscanCount(String  incid);

    /*
             * 将订单发送给下一个 网点
     * */
    DubboMsgVO  addSentScan(EforcesSentScan sentScan, EforcesLogisticStracking stracking);
    
    PageInfo<EforcesSentScan> getAll(Integer page,Integer size,String incid);
    
    Integer  deleteByIds(List<Integer>list);

    EforcesSentScan getById(Integer id);
    
    void setById(EforcesSentScan sentScan);
    
    List <EforcesSentScan> getSentScanByNumber(String billsNumber, String incNumber);

    Integer updateSentScan(EforcesSentScan record);

    /**
     * 批量将发件信息插入数据库
     * @param list
     * @return
             */
    int listinsert (List<EforcesSentScan> list,List<EforcesLogisticStracking> strackingList);
    
    
    List<Map<String, Object>> getSentOrderStatisticListByParam(String province , String city, String area,String incNum , String startTime , String endTime);
	
	PageInfo<Map<String, Object>> getSentOrderStatisticListByPage(Integer pageNum , Integer pageSize , 
			String province , String city, String area , String incNum , String startTime , String endTime);


}
