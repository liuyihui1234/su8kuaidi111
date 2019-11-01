package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesGroupModule implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -9149620637881310309L;

	private Integer id;

    private Integer groupid;

    private Integer moduleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }
}