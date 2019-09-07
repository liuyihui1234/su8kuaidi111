package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EforcesBaggingScan  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5106413540492850075L;

	private Integer id;
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date scantime;

    private String code;

    private Integer num;

    private String baggingid;

    private String baggingname;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String createid;

    private String createname;

    private String incid;

    private String incname;

    private String numberlist;
    
    private Integer isDelete ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Date getScantime() {
		return scantime;
	}

	public void setScantime(Date scantime) {
		this.scantime = scantime;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBaggingid() {
        return baggingid;
    }

    public void setBaggingid(String baggingid) {
        this.baggingid = baggingid == null ? null : baggingid.trim();
    }

    public String getBaggingname() {
        return baggingname;
    }

    public void setBaggingname(String baggingname) {
        this.baggingname = baggingname == null ? null : baggingname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid == null ? null : createid.trim();
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public String getIncid() {
        return incid;
    }

    public void setIncid(String incid) {
        this.incid = incid == null ? null : incid.trim();
    }

    public String getIncname() {
        return incname;
    }

    public void setIncname(String incname) {
        this.incname = incname == null ? null : incname.trim();
    }

    public String getNumberlist() {
        return numberlist;
    }

    public void setNumberlist(String numberlist) {
        this.numberlist = numberlist == null ? null : numberlist.trim();
    }

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "EforcesBaggingScan [id=" + id + ", scantime=" + scantime + ", code=" + code + ", num=" + num
				+ ", baggingid=" + baggingid + ", baggingname=" + baggingname + ", createtime=" + createtime
				+ ", createid=" + createid + ", createname=" + createname + ", incid=" + incid + ", incname=" + incname
				+ ", numberlist=" + numberlist + ", isDelete=" + isDelete + "]";
	}

    
}