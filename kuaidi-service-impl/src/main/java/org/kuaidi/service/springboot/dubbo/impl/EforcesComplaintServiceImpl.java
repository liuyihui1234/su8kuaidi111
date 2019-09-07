package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesComplaint;
import org.kuaidi.dao.EforcesComplaintMapper;
import org.kuaidi.iservice.IEforcesComplaintService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(version = "1.0.0")
public class EforcesComplaintServiceImpl implements IEforcesComplaintService {

    @Autowired
    EforcesComplaintMapper complaintMapper;

    /**
     * 投诉记录
     * @param incNumber
     * @return
     */
    @Override
    public PageInfo<EforcesComplaint> getcomplaint(Integer pageNum , Integer pageSize , String incNumber) {
    	PageHelper.startPage(pageNum,pageSize);
		List<EforcesComplaint> list = complaintMapper.getcomplaint(incNumber);
		final PageInfo<EforcesComplaint> pageInfo = new PageInfo<>(list);
		return pageInfo;
    }
}
