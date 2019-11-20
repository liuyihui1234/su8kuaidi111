package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import java.util.List;

public interface IEforcesUserGroupService {
    /**
     * 查詢升級相關信息
     * @return
     */
    PageInfo<EforcesUsersgroup> selectAllIGroup(Integer page, Integer limit);
    
    List<EforcesUsersgroup> selectAllUserGroup();

    EforcesUsersgroup selectByPrimaryKey(Integer id);


    Integer updateUserGroup(EforcesUsersgroup userGroup);
    
    Integer addUserGroup(EforcesUsersgroup userGroup);

	int deleteByid(List<Integer> array);

}
