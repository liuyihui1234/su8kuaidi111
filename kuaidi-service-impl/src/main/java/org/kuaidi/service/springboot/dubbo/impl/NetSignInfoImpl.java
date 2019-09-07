package org.kuaidi.service.springboot.dubbo.impl;

import java.util.Date;
import java.util.List;

import org.kuaidi.bean.domain.EforcesNetsign;
import org.kuaidi.dao.EforcesNetsignMapper;
import org.kuaidi.iservice.NetSignInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.Config;

//注册为 Dubbo 服务
@Service(version = "1.0.0")
public class NetSignInfoImpl implements NetSignInfo {

	@Autowired
	EforcesNetsignMapper netSignDao;

	public int saveNetSignInfo(EforcesNetsign netSign) {
		// TODO Auto-generated method stub
		netSign.setCrttime(new Date());
		int rst = netSignDao.insertSelective(netSign);
		System.out.println(rst);
		if (rst > 0) {
			return netSign.getId();
		}
		return 0;
	}

	public void updateNetSignInfo(EforcesNetsign netSign) {
		// TODO Auto-generated method stub
		netSignDao.updateByPrimaryKeySelective(netSign);
	}


	public Integer updateNetSignIdentityPics(Integer netSignId, String identityFontPic, String identityBackPic) {
		// TODO Auto-generated method stub
		EforcesNetsign netSignInfo = netSignDao.selectByPrimaryKey(netSignId);
		if (netSignInfo != null) {
			if (identityFontPic != null && identityFontPic.length() > 0) {
				netSignInfo.setIdentityfont(identityFontPic);
			}
			if (identityBackPic != null && identityBackPic.length() > 0) {
				netSignInfo.setIdentityback(identityBackPic);
			}
			return netSignDao.updateByPrimaryKeySelective(netSignInfo);
		}
		return 0;
	}

	public EforcesNetsign getNetSignById(Integer netSignId) {
		// TODO Auto-generated method stub
		return netSignDao.selectByPrimaryKey(netSignId);
	}

	public EforcesNetsign selectProvinces(EforcesNetsign eforcesNetsign) {
        return netSignDao.selectProvinces(eforcesNetsign);
    }
	
	public PageInfo<EforcesNetsign> selectNetsignSort(Integer pageNum , EforcesNetsign record) {
		if(pageNum == null || pageNum == 0 ) {
			PageHelper.startPage(1, Config.pageSize);
		}else {
            PageHelper.startPage(pageNum, Config.pageSize);
		}
		List<EforcesNetsign> list = netSignDao.selectNetsignSort(record);
        final PageInfo<EforcesNetsign> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

	public EforcesNetsign selectNetsignById(Integer id) {
		return netSignDao.selectByPrimaryKey(id);
	}

	public Integer updateNetsignSort(EforcesNetsign record) {
		return netSignDao.updateStatusById(record);
	}

	@Override
	public List<EforcesNetsign> getNetSignByUserId(Integer userId) {
		// TODO Auto-generated method stub
		EforcesNetsign netSign = new EforcesNetsign();
		netSign.setUserid(userId);
		return netSignDao.selectNetsignSort(netSign);
	}

	@Override
	public EforcesNetsign selectSort(EforcesNetsign record) {
		return netSignDao.selectBySort(record);
	}

	/**
	 * 查询我的合同pdf
	 * @return
	 */
	@Override
	public List<EforcesNetsign> selectPath(String incNumber) {
		return netSignDao.selectPath(incNumber);
	}
}