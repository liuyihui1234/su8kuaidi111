package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesTransportedscan;
import org.kuaidi.dao.EforcesLogisticStrackingMapper;
import org.kuaidi.dao.EforcesTransportedscanMapper;
import org.kuaidi.iservice.IEforcesTransportedscanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2019/8/7 15:03
 */
@Service(version = "1.0.0",interfaceClass = IEforcesTransportedscanService.class)
public class EforcesTransportedscanServiceImpl implements IEforcesTransportedscanService {

    @Autowired
    private EforcesTransportedscanMapper transportedscan;

    @Autowired
    private EforcesLogisticStrackingMapper  logisticStrackingDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective (EforcesTransportedscan record,EforcesLogisticStracking  strackingInfo) {
    	int rst = 0 ;
    	rst = transportedscan.insertSelective(record);
    	if(rst >0 ) {
    		rst = logisticStrackingDao.insertSelective(strackingInfo);
    	}
    	return rst ;
    }

    @Override
	public int insertSelective(EforcesTransportedscan record) {
		// TODO Auto-generated method stub
		return  transportedscan.insertSelective(record);
	}

    @Override
    public PageInfo<EforcesTransportedscan> selectByIncid(Integer pageNum, Integer pageSize, Integer incid) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesTransportedscan> list = transportedscan.selectByIncid(incid);
        final PageInfo<EforcesTransportedscan> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<EforcesTransportedscan> selectState0() {
        return transportedscan.selectState0();
    }

    @Override
    public PageInfo<EforcesTransportedscan> selectAllByState0(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesTransportedscan> list = transportedscan.selectState0();
        final PageInfo<EforcesTransportedscan> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public EforcesTransportedscan selectByPrimaryKey(Integer id) {
        return transportedscan.selectByPrimaryKey(id);
    }

    @Override
    public EforcesTransportedscan selectByBillsnumber(String billsnumber) {
        return transportedscan.selectByBillsnumber(billsnumber);
    }

    @Override
    public EforcesTransportedscan selectByNextnumber(String nextnumber) {
        return transportedscan.selectByNextnumber(nextnumber);
    }

    @Override
    public int updateByPrimaryKeySelective(EforcesTransportedscan record) {
        return transportedscan.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateState(List<String> billsNumber) {
        return transportedscan.updateState(billsNumber);
    }
}
