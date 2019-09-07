package org.kuaidi.iservice;

import org.kuaidi.bean.domain.EforcesVersioninfo;

import java.util.List;

public interface IEforcesVersioninfoService {
    /**
     * 查詢版本升級相關信息
     * @return
     */
    List<EforcesVersioninfo> getlist(int type);
}
