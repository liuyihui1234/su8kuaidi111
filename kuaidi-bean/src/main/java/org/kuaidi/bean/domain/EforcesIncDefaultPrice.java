package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesIncDefaultPrice implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5934867351230594068L;

	private Integer id;

    private String inctypename;

    private Float joinprice;

    private String remark;
    
    private String checkRemark ; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInctypename() {
        return inctypename;
    }

    public void setInctypename(String inctypename) {
        this.inctypename = inctypename == null ? null : inctypename.trim();
    }

    public Float getJoinprice() {
        return joinprice;
    }

    public void setJoinprice(Float joinprice) {
        this.joinprice = joinprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark == null ? null : checkRemark;
	}
    
    
}