package org.kuaidi.service.springboot.dubbo.impl;

import java.util.ArrayList;
import java.util.List;
import org.kuaidi.bean.domain.EforcesGroupModule;
import org.kuaidi.dao.EforcesGroupModuleMapper;
import org.kuaidi.iservice.IEforcesGroupModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0",interfaceClass=IEforcesGroupModuleService.class,timeout=12000)
public class EforcesGroupModuleServiceImpl implements IEforcesGroupModuleService {
	
	@Autowired
	private EforcesGroupModuleMapper  groupModuleDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insertListSelect(List<Integer> list , Integer groupId) {
		// TODO Auto-generated method stub
		List<EforcesGroupModule> groupModuleList = new ArrayList<EforcesGroupModule>();
		if(list != null  && list.size() > 0 ) {
			for(int i = 0 ; i < list.size() ; i++) {
				EforcesGroupModule  groupModule = new EforcesGroupModule();
				groupModule.setGroupid(groupId);
				groupModule.setModuleid(list.get(i));
				groupModuleList.add(groupModule);
			}
		}
		groupModuleDao.deleteByGroupId(groupId);
		int rst = 1 ; 
		if(groupModuleList.size() > 0 ) {
			rst = groupModuleDao.insertList(groupModuleList);
		}
		return rst;
	}

	@Override
	public List<EforcesGroupModule> selectByGroupId(Integer groupId) {
		// TODO Auto-generated method stub
		return groupModuleDao.selectByGroupId(groupId);
	}

	

}
