package org.kuaidi.bean.domain;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesUserPoint implements Serializable {
	
    private static final long serialVersionUID = -3585374578746189505L;
    private Integer id;

    private Integer type;

    private Integer pointnum;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crttime;

    private Integer userid;

    private String incnumber;
    
    private String typeRemark; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPointnum() {
        return pointnum;
    }

    public void setPointnum(Integer pointnum) {
        this.pointnum = pointnum;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getIncnumber() {
        return incnumber;
    }

    public void setIncnumber(String incnumber) {
        this.incnumber = incnumber == null ? null : incnumber.trim();
    }

	public String getTypeRemark() {
		return typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark == null ? "" : typeRemark.trim();
	}
    
    
}