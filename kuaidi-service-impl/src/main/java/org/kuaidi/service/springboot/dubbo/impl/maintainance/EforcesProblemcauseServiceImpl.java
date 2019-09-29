package org.kuaidi.service.springboot.dubbo.impl.maintainance;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.maintainance.EforcesProblemcause;
import org.kuaidi.dao.EforcesProblemcauseMapper;
import org.kuaidi.iservice.maintainance.IEforcesProblemcauseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2019/8/10 17:26
 */
@Service(version = "1.0.0")
public class EforcesProblemcauseServiceImpl implements IEforcesProblemcauseService {
    @Autowired
    EforcesProblemcauseMapper problemcauseMapper;
    @Override
    public EforcesProblemcause selectOne(Integer id) {
        return problemcauseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<EforcesProblemcause> selectAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<EforcesProblemcause> list = problemcauseMapper.selectAll();
        final PageInfo<EforcesProblemcause> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertOne(EforcesProblemcause record) {
        return problemcauseMapper.insertSelective(record);
    }

    @Override
    public int updateOne(EforcesProblemcause record) {
        return problemcauseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateDelete(Integer[] ids) {
        return problemcauseMapper.updateDelete(ids);
    }

	@Override
	public List<EforcesProblemcause> selectAll() {
		// TODO Auto-generated method stub
		return problemcauseMapper.selectAll();
	}
}
