package org.kuaidi.web.springboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.kuaidi.iservice.IEforceslogisticstrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KdnGetTransferUtil {
	
	@Autowired
    private CorpListUtil corpListUtil;

    @Reference(version = "1.0.0")
    IEforceslogisticstrackingService logisticstracking ;

    @Reference(version = "1.0.0")
    IEforcesTransportedscanService transportedscanService;
    
    public void logistics() {
    	List<String>  finishTransfer = new ArrayList<String>();
	    List<String>  unfinishTransfer = new ArrayList<String>();
        try {
        	Map<Integer, String> corpMap = corpListUtil.getCorpMap();//获取快递公司对应的第三方编号map
        	SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
		    int batchLimit = 2;//每页显示的数据量(第三方创建多个运单号，一次最多40条订单)
		    PageInfo<EforcesTransportedscan> pageInfo = transportedscanService.selectAllByState0(1, batchLimit);//分页获取运输中的订单
		    while(pageInfo != null && pageInfo.getList() != null &&  pageInfo.getList().size() > 0 ) {
		    	List<String> orderList = new ArrayList<String>();
	    		for(int i = 0;  i < pageInfo.getList().size(); i++ ) {
	    			EforcesTransportedscan scan = pageInfo.getList().get(i);
	    			if(scan != null) {
	    				orderList.add(scan.getBillsnumber());
	    			}
	    		}
		    	Map<String, String> logisticMap = getMaxNumerTime(sdf, orderList);
		    	KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		    	// 循环查询物流信息， 
	    		for(int i = 0;  i < pageInfo.getList().size(); i++ ) {
	    			EforcesTransportedscan scan = pageInfo.getList().get(i);
	    			if(scan == null) {
	    				continue;
	    			}
    				String expNo = scan.getNextnumber();
    				Integer corpId = scan.getNextcorpid();
    				String expCode = corpMap.get(corpId);
    				String rst = api.getOrderTracesByJson(expCode, expNo);
    				JSONObject data = JSONObject.fromObject(rst);
    				if(data.has("Success") && data.getBoolean("Success")) {
    					if(data.has("State") && data.getInt("State") == 3){
    						finishTransfer.add(scan.getBillsnumber());
    					}else if(data.has("State") && data.getInt("State") == 0) {
    						unfinishTransfer.add(scan.getBillsnumber());
    						break;
    					}else {
    						unfinishTransfer.add(scan.getBillsnumber());
    					}
    					JSONArray dataArr = data.getJSONArray("Traces");
    					//将获得到的物流信息封装成本的物流信息
    					List<EforcesLogisticStracking> list = createLogisticStrack(sdf, logisticMap, scan, dataArr);
    					if(list != null && list.size() > 0 ) {
    						//保存list
    						logisticstracking.insertLogList(list);
    					}
    				}
	    		}
		    	pageInfo = transportedscanService.selectAllByState0(1, batchLimit);//分页获取运输中的订单
		    }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	if(finishTransfer.size() > 0 ) {
				transportedscanService.updateState2(finishTransfer);
			}
			if(unfinishTransfer.size() > 0 ) {
				transportedscanService.updateState0(unfinishTransfer);
			}
	    }
    }
    
    /*
     * 根据快递年查询的物流信息，将数据封装到本地物流信息中
     */
	private List<EforcesLogisticStracking> createLogisticStrack(SimpleDateFormat sdf, Map<String, String> logisticMap,
			EforcesTransportedscan scan, JSONArray dataArr)
			throws ParseException {
		List<EforcesLogisticStracking> addLogisticStracking = new ArrayList<EforcesLogisticStracking>();
		if(dataArr != null && dataArr.size() > 0 ) {
			for(int k = 0 ; k <  dataArr.size(); k++) {
				JSONObject trackInfo = dataArr.getJSONObject(k);
				if(trackInfo != null && trackInfo.has("AcceptTime")) {
					String acceptTime = trackInfo.getString("AcceptTime");
					String billsNumber = scan.getBillsnumber();
					if(logisticMap.containsKey(billsNumber) && 
								logisticMap.get(billsNumber).compareTo(acceptTime) >= 0 ) {
						//创建对象，
						continue; 
					}else {
						EforcesLogisticStracking  logistic = new EforcesLogisticStracking();
						logistic.setIncid("");
						logistic.setBillsnumber(billsNumber);
						logistic.setOperationtime(sdf.parse(acceptTime));
						logistic.setDescription(trackInfo.getString("AcceptTime"));
						logistic.setMark(2);
						logistic.setOperators("代理方");
						addLogisticStracking.add(logistic);
					}
				}
			}
		}
		return addLogisticStracking; 
	}

    /*
     * 获得最新的订单的物流信息，
     * 封装成map结构
     */
	private Map<String, String> getMaxNumerTime(SimpleDateFormat sdf, List<String> orderList) {
		Map<String, String> logisticMap = new HashMap<String, String>();
		// 查询对应的物流信息
		if(orderList.size() > 0 ) {
			List<EforcesLogisticStracking> logisticList = 
					logisticstracking.getStrackingMaxTimeByNumber(orderList);
			if(logisticList != null && logisticList.size() > 0 ) {
				for(int i = 0 ; i < logisticList.size() ; i++) {
					EforcesLogisticStracking  logistic = logisticList.get(i);
					if(logistic != null && logistic.getOperationtime() != null) {
						String lastTime = sdf.format(logistic.getOperationtime());
						logisticMap.put(logistic.getBillsnumber(), lastTime);
					}
				}
			}
		}
		return logisticMap;
	}

}
