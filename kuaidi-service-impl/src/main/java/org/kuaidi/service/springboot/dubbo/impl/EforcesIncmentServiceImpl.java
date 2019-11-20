package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesRegion;
import org.kuaidi.dao.EforcesIncmentMapper;
import org.kuaidi.dao.EforcesRegionMapper;
import org.kuaidi.iservice.IEforcesIncmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

//注册为 Dubbo 服务
@Service(version = "1.0.0")
public class EforcesIncmentServiceImpl implements IEforcesIncmentService {

    @Autowired
    EforcesIncmentMapper incmentMapper;
    
    @Autowired
    EforcesRegionMapper regionDao; 

    @Override
    public int insetIncment(EforcesIncment eforcesIncment) {
        int rst = incmentMapper.insertSelective(eforcesIncment);
        if(rst > 0) {
        	return eforcesIncment.getId();
        }
        return 0;
    }

    @Override
    public int deleteByid(List<Integer> array) {
        return incmentMapper.deleteByid(array);
    }

    public PageInfo<EforcesIncment> selectAllIcrment(Integer curr, Integer nums,String parameter, String parentId) {
        PageHelper.startPage(curr,nums);
        List<EforcesIncment> eforcesIncments = incmentMapper.selectAllIcrment(parameter, parentId);
        final PageInfo<EforcesIncment> info = new PageInfo<>(eforcesIncments);
        return info ;
    }

    @Override
    public EforcesIncment selectByPrimaryKey(Integer id) {
        return incmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(EforcesIncment record) {
        int rst = incmentMapper.insert(record);
        return rst;
    }

    @Override
    public int updateByPrimaryKey(EforcesIncment record) {
        return incmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public String getprovinceid(String name) {
        return incmentMapper.getprovinceid(name);
    }

    @Override
    public String getcityid(String name) {
        return incmentMapper.getcityid(name);
    }

    @Override
    public String getareaid(String name,String id) {
        return incmentMapper.getareaid(name,id);
    }

    @Override
    public List<EforcesIncment> selectByParentid(List<String> parentidList) {
        return incmentMapper.selectIncmentByParentid(parentidList);
    }

    @Override
    public List<EforcesIncment> selectByNumber(List<String> numberList) {
        return incmentMapper.selectIncmentByNumber(numberList);
    }

	@Override
	public EforcesIncment selectByNumber(String number) {
		// TODO Auto-generated method stub
		return incmentMapper.selectByNumber(number);
	}

	@Override
	public List<Map<String, Object>> statisticsByParentid(List<String> parentidList) {
		// TODO Auto-generated method stub
		return incmentMapper.statisticsIncmentByParentid(parentidList);
	}

	@Override
	public List<Map<String, Object>> statisticsByNumber(List<String> numbers) {
		// TODO Auto-generated method stub
		return incmentMapper.statisticsIncmentByNumber(numbers);
	}

    @Override
    public EforcesIncment getByNextStopName(String name) {
        return incmentMapper.selectNextSyopByName(name);
    }


}
