package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesLeaveword;

import java.util.List;

public interface IEforcesLeaveWordService {

    /**
     * 我的留言
     * @param incNumber
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<EforcesLeaveword> listMsg(String incNumber,Integer pageNum,Integer pageSize);
}
