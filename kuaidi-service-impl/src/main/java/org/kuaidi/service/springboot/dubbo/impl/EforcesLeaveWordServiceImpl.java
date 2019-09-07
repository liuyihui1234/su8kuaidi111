package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesLeaveword;
import org.kuaidi.dao.EforcesLeavewordMapper;
import org.kuaidi.iservice.IEforcesLeaveWordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(version = "1.0.0")
public class EforcesLeaveWordServiceImpl implements IEforcesLeaveWordService {
    @Autowired
    EforcesLeavewordMapper leavewordMapper;

    /**
     * 我的留言
     * @param incNumber
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesLeaveword> listMsg(String incNumber, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesLeaveword> list = leavewordMapper.listMsg(incNumber);
        final PageInfo<EforcesLeaveword> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
