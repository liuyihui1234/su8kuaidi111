package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.domain.EforcesUsersgrouprele;

import java.util.List;

public interface IEforcesUserGrouproleService {

    int deleteByUserId(Integer userid) ;

    int insertForeach(List<EforcesUsersgrouprele> list);
}
