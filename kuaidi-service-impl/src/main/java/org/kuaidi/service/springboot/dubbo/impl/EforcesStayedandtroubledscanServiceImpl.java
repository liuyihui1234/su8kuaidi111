package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesOrder;
import org.kuaidi.bean.domain.EforcesStayedandtroubledscan;
import org.kuaidi.dao.EforcesStayedandtroubledscanMapper;
import org.kuaidi.iservice.IEforcesStayedandtroubledscanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class EforcesStayedandtroubledscanServiceImpl implements IEforcesStayedandtroubledscanService {

    @Autowired
    EforcesStayedandtroubledscanMapper stayedandtroubledscanMapper;

    /**
     * 获取问题件详细信息
     * @param incid 网店编号 incment中的number
     * @return
     */
    @Override
    public PageInfo<EforcesStayedandtroubledscan> getIssue(Integer pageNum, Integer pageSize, String incid, List<Integer> causeIds) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesStayedandtroubledscan> list = stayedandtroubledscanMapper.getIssue(incid,causeIds);
        final PageInfo<EforcesStayedandtroubledscan> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<EforcesStayedandtroubledscan> getAllIssue(Integer pageNum, Integer pageSize,String incid) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesStayedandtroubledscan> allIssue = stayedandtroubledscanMapper.getAllIssue(incid);
        final PageInfo<EforcesStayedandtroubledscan> pageInfo = new PageInfo<>(allIssue);
        return pageInfo;
    }

    @Override
    public int insertList(List<EforcesStayedandtroubledscan> list) {
        return stayedandtroubledscanMapper.insertList(list);
    }


    @Override
    public int updateByPrimaryKeySelective(EforcesStayedandtroubledscan record) {
        return stayedandtroubledscanMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public EforcesStayedandtroubledscan selectById(Integer id) {
        return stayedandtroubledscanMapper.selectById(id);
    }

	@Override
	public int deleteDestoryBill(String billNumber) {
		// TODO Auto-generated method stub
		return stayedandtroubledscanMapper.deleteStayedandtroubledByNumber(billNumber);
	}

    @Override
    public int deleteByid(List<Integer> list) {
        return stayedandtroubledscanMapper.deleteByid(list);
    }

	@Override
	public List<EforcesStayedandtroubledscan> getScanInfoByBillsNumbers(List<String> billsNums) {
		// TODO Auto-generated method stub
		return stayedandtroubledscanMapper.getScanInfoByBillsNumbers(billsNums);
	}
}
