package org.kuaidi.iservice;

import java.util.List;

import org.kuaidi.bean.domain.EforcesNetsign;
import com.github.pagehelper.PageInfo;

public interface NetSignInfo {

	int saveNetSignInfo(EforcesNetsign  netSign );

	void updateNetSignInfo(EforcesNetsign  netSign);

	EforcesNetsign  getNetSignById(Integer  netSignId);

	Integer updateNetSignIdentityPics(Integer  netSignId, String identityFontPic , String identityBackPic);

	EforcesNetsign selectProvinces(EforcesNetsign eforcesNetsign);

	 PageInfo<EforcesNetsign> selectNetsignSort(Integer pageNum , Integer rows , EforcesNetsign record);

     EforcesNetsign selectNetsignById(Integer id);

     Integer updateNetsignSort(EforcesNetsign record);

	List<EforcesNetsign> getNetSignByUserId(Integer userId);
	
	 EforcesNetsign selectSort(EforcesNetsign record);

	/**
	 * @return
	 */
	List<EforcesNetsign> selectPath(String incNumber);
	
	Integer  delNetSignByIds(String ids);
}
