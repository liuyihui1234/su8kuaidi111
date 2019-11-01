package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesGroupModule;
import java.util.List;

public interface IEforcesGroupModuleService {
	
	Integer insertListSelect(List<Integer> list, Integer groupId);
	
	List<EforcesGroupModule>  selectByGroupId(Integer groupId);

}
