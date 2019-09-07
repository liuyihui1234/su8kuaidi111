package org.kuaidi.iservice;

import java.util.List;
import org.kuaidi.bean.domain.EforcesContraband;
import org.kuaidi.bean.domain.EforcesDefaultBankInfo;
import org.kuaidi.bean.domain.EforcesIncDefaultPrice;

public interface IDictionaryService {

	EforcesIncDefaultPrice getDefaultPriceById(Integer priceId);
	
	List<EforcesDefaultBankInfo> getAllBankInfo();
	
	List<EforcesContraband>  getContrabandByName(String name);
	
}
