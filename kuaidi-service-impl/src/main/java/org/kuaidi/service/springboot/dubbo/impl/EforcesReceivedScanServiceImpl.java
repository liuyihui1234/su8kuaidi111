package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesReceivedScan;
import org.kuaidi.bean.vo.DubboMsgVO;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.dao.EforcesReceivedScanMapper;
import org.kuaidi.iservice.IEforcesReceivedscanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;

@Service(version = "1.0.0",interfaceClass=IEforcesReceivedscanService.class)
public class EforcesReceivedScanServiceImpl implements IEforcesReceivedscanService {

    @Autowired
    EforcesReceivedScanMapper receivedscanMapper;
    
    @Autowired
    private EforcesLogisticStrackingMapper  logisticStrackingMapper; 

    /**
     * 查询派收件总数量
     * @param incid
     * @return
     */
    @Override
    public int getReceicedscanCount(String incid) {
        return receivedscanMapper.selectCount(incid);
    }

    /**
     * 查询网点下所有订单信息
     * @param incid
     * @return
     */
    @Override
    public List<EforcesReceivedScan> getAllOrder(String incid) {
        return receivedscanMapper.getAllOrder(incid);
    }

    /**
     * 收件
     * @param record
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DubboMsgVO  insertSelective(EforcesReceivedScan record,
    		EforcesLogisticStracking stracking) {
    	DubboMsgVO  msgVo = new DubboMsgVO();
    	try {
        	int rst = receivedscanMapper.insertSelective(record);
            if(rst == 1 ) {
            	rst = logisticStrackingMapper.insertSelective(stracking);
        	}
            if(rst == 1 ) {
            	msgVo.setMsg("添加收件扫描成功");
            	msgVo.setRstFlage(true);
            }else {
            	msgVo.setMsg("添加收件扫描失败");
            	msgVo.setRstFlage(false);
            }
        	return msgVo;
        }catch(Exception e) {
        	e.printStackTrace();
        }
    	return msgVo;
    }

    @Override
    public PageInfo<EforcesReceivedScan> getAllOrderSelective(Integer pageNum, Integer pageSize, Integer incid) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesReceivedScan> list = receivedscanMapper.getAllOrderSelective(incid);
        final PageInfo<EforcesReceivedScan> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(EforcesReceivedScan receivedScan) {
        return receivedscanMapper.updateByPrimaryKeySelective(receivedScan);
    }

    @Override
    public EforcesReceivedScan selectByPrimaryKey(Integer id) {
        return receivedscanMapper.selectByPrimaryKey(id);
    }

    /**
     * 寄/派件运单管理
     * @param number
     * @return
     */
    @Override
    public List<HashMap> getreceiveOrder(String number) {
        List<HashMap> listMap = receivedscanMapper.getreceiveOrder(number);
        return listMap;
    }

    /**
     * 将收件信息插入数据库
     * @param list
     * @return
     */
    @Override
    public int insertList(List<EforcesReceivedScan> list) {
        return receivedscanMapper.insertList(list);
    }

    /*    *//**
     * 收件，根据订单号在订单表查到数据增加到扫描表内
     * @param receivedScan
     * @return
     *//*
    @Override
    public int addOrderMsg(EforcesReceivedScan receivedScan) {
        return receivedscanMapper.addOrderMsg(receivedScan);
    }*/
}
