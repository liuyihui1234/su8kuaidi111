package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;
import org.kuaidi.bean.domain.EforcesProduct;

import java.util.List;

public interface IEforcesProductService {

    /**
     * 修改物料品名维护
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesProduct record);

    /**
     * 添加物料品名维护
     * @param record
     * @return
     */
    int insertSelective(EforcesProduct record);

    /**
     * 删除物料品名维护
     * @param id
     * @return
     */
    Integer removeUpdate(Integer[] id);

    /**
     * 查询物料品名维护
     * @return
     */
    PageInfo<EforcesProduct> getlist(Integer pageNum,Integer pageSize,EforcesProduct product);

    /**
     * 查询要修改的数据
     * @param id
     * @return
     */
    EforcesProduct selectByPrimaryKey(Integer id);
}
