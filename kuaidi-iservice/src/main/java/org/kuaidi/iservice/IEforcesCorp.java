package org.kuaidi.iservice;

import java.util.List;

import org.kuaidi.bean.domain.EforcesCorp;

public interface IEforcesCorp {
	
	
	List<EforcesCorp> getAllEforcesCorp();
	
	EforcesCorp  getEforcesCorpById(Integer id);

}
