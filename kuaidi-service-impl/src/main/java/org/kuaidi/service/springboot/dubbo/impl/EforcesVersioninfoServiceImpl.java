package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.kuaidi.bean.domain.EforcesVersioninfo;
import org.kuaidi.dao.EforcesVersioninfoMapper;
import org.kuaidi.iservice.IEforcesVersioninfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesVersioninfoServiceImpl implements IEforcesVersioninfoService {

    @Autowired
    EforcesVersioninfoMapper versioninfoMapper;

    @Override
    public List<EforcesVersioninfo> getlist(int type){
       return versioninfoMapper.getlist(type);
    }

    /**
     * 后台管理查询版本信息
     * @return
     */
    @Override
    public List<EforcesVersioninfo> getListMsg() {
        return versioninfoMapper.getListMsg();
    }

    /**
     * 后台管理修改版本信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesVersioninfo record) {
        return versioninfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 后台管理增加版本信息
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesVersioninfo record) {
        return versioninfoMapper.insertSelective(record);
    }

    /**
     * 后台管理删除版本信息
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return versioninfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 后台管理删除版本信息
     * @param id
     * @return
     */
    @Override
    public int delete(Integer[] id) {
        return versioninfoMapper.delete(id);
    }
}
