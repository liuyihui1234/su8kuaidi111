package org.kuaidi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kuaidi.bean.domain.EforcesDistributedScan;

public interface EforcesDistributedScanMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByid(List<Integer> list);

    List<EforcesDistributedScan> selectByCreateincnumber(String createincnumber);

    List<EforcesDistributedScan> selectNoByCreateincnumber(String number);

    int insert(EforcesDistributedScan record);

    int insertSelective(EforcesDistributedScan record);

    EforcesDistributedScan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EforcesDistributedScan record);

    int updateByPrimaryKeyWithBLOBs(EforcesDistributedScan record);

    int updateByPrimaryKey(EforcesDistributedScan record);

    /**
               *  根据快递员员工编号查询待派送订单
     * g
     * @param postmanid
     * @return
     */
    List<EforcesDistributedScan> selectDisByPostmanId(@Param("postmanid") String postmanid, @Param("number") List<String> number);

    List<EforcesDistributedScan> getAlldistribute(String incid);
    
    /*
               * 根据订单号查询该订单是否派送
     */
	List<EforcesDistributedScan> selectByBillNumber(String billsNum);
	
}