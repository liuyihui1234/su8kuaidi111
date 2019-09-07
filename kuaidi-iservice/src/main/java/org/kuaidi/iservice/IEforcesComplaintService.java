package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesComplaint;

import com.github.pagehelper.PageInfo;


public interface IEforcesComplaintService {

    /**
     * 投诉记录
     * @param incNumber
     * @return
     */
	PageInfo<EforcesComplaint> getcomplaint(Integer pageNum , Integer pageSize , String incNumber);
}
