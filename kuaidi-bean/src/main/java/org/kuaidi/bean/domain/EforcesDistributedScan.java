package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesDistributedScan implements Serializable, Cloneable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8875834669340155814L;

	private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String flightsnumber;

    private String goodstype;

    private String expresstype;

    private Integer expressid;

    private String postman;

    private String postmanid;

    private String scantype;

    private String scanners;

    private String scannerid;

    private String incname;

    private String incid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date scantime;

    private Integer amount;

    private String billsnumber;

    private String bz;

    private EforcesOrder eforcesOrder;

    public EforcesOrder getEforcesOrder() {
        return eforcesOrder;
    }

    public void setEforcesOrder(EforcesOrder eforcesOrder) {
        this.eforcesOrder = eforcesOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype == null ? null : goodstype.trim();
    }

    public String getExpresstype() {
        return expresstype;
    }

    public void setExpresstype(String expresstype) {
        this.expresstype = expresstype == null ? null : expresstype.trim();
    }

    public Integer getExpressid() {
        return expressid;
    }

    public void setExpressid(Integer expressid) {
        this.expressid = expressid;
    }

    public String getPostman() {
        return postman;
    }

    public void setPostman(String postman) {
        this.postman = postman == null ? null : postman.trim();
    }

    public String getPostmanid() {
        return postmanid;
    }

    public void setPostmanid(String postmanid) {
        this.postmanid = postmanid == null ? null : postmanid.trim();
    }

    public String getScantype() {
        return scantype;
    }

    public void setScantype(String scantype) {
        this.scantype = scantype == null ? null : scantype.trim();
    }

    public String getScanners() {
        return scanners;
    }

    public void setScanners(String scanners) {
        this.scanners = scanners == null ? null : scanners.trim();
    }

    public String getScannerid() {
        return scannerid;
    }

    public void setScannerid(String scannerid) {
        this.scannerid = scannerid == null ? null : scannerid.trim();
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

    public String getBillsnumber() {
        return billsnumber;
    }

    public void setBillsnumber(String billsnumber) {
        this.billsnumber = billsnumber == null ? null : billsnumber.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
    
    @Override
   	public EforcesDistributedScan clone() throws CloneNotSupportedException {
           return (EforcesDistributedScan)super.clone();
       }

	@Override
	public String toString() {
		return "EforcesDistributedScan [id=" + id + ", createtime=" + createtime + ", flightsnumber=" + flightsnumber
				+ ", goodstype=" + goodstype + ", expresstype=" + expresstype + ", expressid=" + expressid
				+ ", postman=" + postman + ", postmanid=" + postmanid + ", scantype=" + scantype + ", scanners="
				+ scanners + ", scannerid=" + scannerid + ", incname=" + incname + ", incid=" + incid + ", scantime="
				+ scantime + ", amount=" + amount + ", billsnumber=" + billsnumber + ", bz=" + bz + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EforcesDistributedScan other = (EforcesDistributedScan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}