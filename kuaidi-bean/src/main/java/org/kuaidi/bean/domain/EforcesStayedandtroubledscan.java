package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesStayedandtroubledscan implements Serializable {

    private static final long serialVersionUID = -5749119116265507083L;

    private Integer id;

    private String billsnumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String flightsnumber;

    private String troubledtype;

    private String scantype;

    private String sacnners;

    private String sacnnerid;

    private String incname;

    private String incid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date scantime;

    private Integer amount;

    private String bz;
    
    private Integer causeId ; 
    
    private Integer isDelete; 

    private EforcesOrder order;

    public EforcesOrder getOrder() {
        return order;
    }

    public void setOrder(EforcesOrder order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillsnumber() {
        return billsnumber;
    }

    public void setBillsnumber(String billsnumber) {
        this.billsnumber = billsnumber == null ? null : billsnumber.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFlightsnumber() {
        return flightsnumber;
    }

    public void setFlightsnumber(String flightsnumber) {
        this.flightsnumber = flightsnumber == null ? null : flightsnumber.trim();
    }

    public String getTroubledtype() {
        return troubledtype;
    }

    public void setTroubledtype(String troubledtype) {
        this.troubledtype = troubledtype == null ? null : troubledtype.trim();
    }

    public String getScantype() {
        return scantype;
    }

    public void setScantype(String scantype) {
        this.scantype = scantype == null ? null : scantype.trim();
    }

    public String getSacnners() {
        return sacnners;
    }

    public void setSacnners(String sacnners) {
        this.sacnners = sacnners == null ? null : sacnners.trim();
    }

    public String getSacnnerid() {
        return sacnnerid;
    }

    public void setSacnnerid(String sacnnerid) {
        this.sacnnerid = sacnnerid == null ? null : sacnnerid.trim();
    }

    public String getIncname() {
        return incname;
    }

    public void setIncname(String incname) {
        this.incname = incname == null ? null : incname.trim();
    }

    public String getIncid() {
        return incid;
    }

    public void setIncid(String incid) {
        this.incid = incid == null ? null : incid.trim();
    }

    public Date getScantime() {
        return scantime;
    }

    public void setScantime(Date scantime) {
        this.scantime = scantime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

	public Integer getCauseId() {
		return causeId;
	}

	public void setCauseId(Integer causeId) {
		this.causeId = causeId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

    @Override
    public String toString() {
        return "EforcesStayedandtroubledscan{" +
                "id=" + id +
                ", billsnumber='" + billsnumber + '\'' +
                ", createtime=" + createtime +
                ", flightsnumber='" + flightsnumber + '\'' +
                ", troubledtype='" + troubledtype + '\'' +
                ", scantype='" + scantype + '\'' +
                ", sacnners='" + sacnners + '\'' +
                ", sacnnerid='" + sacnnerid + '\'' +
                ", incname='" + incname + '\'' +
                ", incid='" + incid + '\'' +
                ", scantime=" + scantime +
                ", amount=" + amount +
                ", bz='" + bz + '\'' +
                ", causeId=" + causeId +
                ", isDelete=" + isDelete +
                ", order=" + order +
                '}';
    }
}