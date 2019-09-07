package org.kuaidi.service.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesDictionary;
import org.kuaidi.dao.EforcesDictionaryMapper;
import org.kuaidi.iservice.IEforcesDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class EforcesDictionaryServiceImpl implements IEforcesDictionaryService {
    @Autowired
    EforcesDictionaryMapper dictionaryMapper;

    /**
     * 显示数据字典
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EforcesDictionary> getlist(Integer pageNum,Integer pageSize,EforcesDictionary dictionary) {
        PageHelper.startPage(pageNum,pageSize);
        List<EforcesDictionary> listResult = dictionaryMapper.getlist(dictionary);
        final PageInfo<EforcesDictionary> pageInfo = new PageInfo<>(listResult);
        return pageInfo;
    }

    /**
     * 修改数据字典信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(EforcesDictionary record) {
        return dictionaryMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加数据字典信息
     * @param record
     * @return
     */
    @Override
    public int insertSelective(EforcesDictionary record) {
        return dictionaryMapper.insertSelective(record);
    }

    /**
     * 删除数据字典信息
     * @param id
     * @return
     */
    @Override
    public Integer removeUpdate(Integer[] id) {
        return dictionaryMapper.removeUpdate(id);
    }

    /**
     * 查询修改数据
     * @param id
     * @return
     */
    @Override
    public EforcesDictionary selectByPrimaryKey(Integer id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }
}
