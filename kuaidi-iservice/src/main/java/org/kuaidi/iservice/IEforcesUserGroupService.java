package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.domain.EforcesVersioninfo;

import java.util.List;

public interface IEforcesUserGroupService {
    /**
     * 查詢升級相關信息
     * @return
     */
    PageInfo<EforcesUsersgroup> selectAllIGroup(Integer page, Integer limit);

    EforcesUsersgroup selectByPrimaryKey(Integer id);






}
