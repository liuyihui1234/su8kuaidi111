package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesSentScan;
import org.kuaidi.bean.vo.DubboMsgVO;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.dao.EforcesSentScanMapper;
import org.kuaidi.iservice.IEforcesSentscanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(version = "1.0.0" ,interfaceClass=IEforcesSentscanService.class)
public class EforcesSentScanServiceImpl implements IEforcesSentscanService {

    @Autowired
    private EforcesSentScanMapper sentscanMapper;
    
    @Autowired
    private EforcesLogisticStrackingMapper  logisticStrackingMapper; 
    
    /**
             * 查询派收件总数量
     * @param incid
     * @return
     */
    @Override
    public int getSentscanCount(String incid) {
        return sentscanMapper.selectCount(incid);
    }
    
    /*
     *添加转运消息， 并添加物流消息 
     */ 
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DubboMsgVO addSentScan(EforcesSentScan sentScan,  EforcesLogisticStracking stracking) {
		// TODO Auto-generated method stub
		DubboMsgVO  msgVo = new DubboMsgVO();
		try {
			Integer rst = sentscanMapper.insertSelective(sentScan);
			if(stracking!=null) {
				if (rst == 1) {
					rst = logisticStrackingMapper.insertSelective(stracking);
					msgVo.setMsg("添加成功！");
				}
			}else{
				msgVo.setMsg("添加成功");
			}
			msgVo.setRstFlage(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  msgVo;
	}

	@Override
	public PageInfo<EforcesSentScan> getAll(Integer page, Integer size,String incid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,size);
		List<EforcesSentScan> sentScan = sentscanMapper.selectAll(incid);
		final  PageInfo<EforcesSentScan> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

	@Override
	public Integer deleteByIds(List<Integer>ids) {
		return sentscanMapper.deleteByIds(ids);
	}

	@Override
	public EforcesSentScan getById(Integer id) {
		
		return sentscanMapper.selectByPrimaryKey(id);
	}

	@Override
	public void setById(EforcesSentScan sentScan) {
		sentscanMapper.updateByPrimaryKeySelective(sentScan);
	}

	@Override
	public List<EforcesSentScan> getSentScanByNumber(String billsNumber,String incNumber) {
		// TODO Auto-generated method stub
		return sentscanMapper.selectByNumber(billsNumber,incNumber);
	}

	@Override
	public Integer updateSentScan(EforcesSentScan record) {
		// TODO Auto-generated method stub
		return sentscanMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 批量将发件信息插入数据库
	 * @param list
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int listinsert(List<EforcesSentScan> list,List<EforcesLogisticStracking> strackingList) {
		/*
		 * 根据发送的订单信息，获得发送订单的物流信息。
		 * */
		Integer rst = 1 ;  
		if(list != null && list.size() > 0 ) {
			rst = sentscanMapper.insertList(list);
		}
		if(rst > 1 && strackingList != null && strackingList.size() > 0 ) {
			rst = logisticStrackingMapper.insertSelectiveList(strackingList);
		}
		return rst; 
	}

	@Override
	public List<Map<String, Object>> getSentOrderStatisticListByParam(String province, String city, String area, String incNum,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		return sentscanMapper.getSentOrderListByParam(province, city, area, incNum, startTime, endTime);
	}

	@Override
	public PageInfo<Map<String, Object>> getSentOrderStatisticListByPage(Integer pageNum, Integer pageSize, String province,
			String city, String area, String incNum, String startTime, String endTime) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		List<Map<String, Object>> sentScan = sentscanMapper.getSentOrderListByParam(province, city, area, incNum, startTime, endTime);
		final  PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(sentScan);
		return pageInfo;
	}

}
