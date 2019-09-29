package org.kuaidi.iservice;



import java.util.List;
import org.kuaidi.bean.domain.EforcesRemovingBagScan;
import com.github.pagehelper.PageInfo;

public interface IEforcesRemovingBagScanService {
	PageInfo<EforcesRemovingBagScan> getAll(Integer page,Integer size,Integer id);

	EforcesRemovingBagScan getById(Integer id);

	void addWeighingScan(EforcesRemovingBagScan record);


	int deleteById(List<Integer> id);

	void setById(EforcesRemovingBagScan record);

	Integer addRecordList(List<EforcesRemovingBagScan> baggingScan);
}
