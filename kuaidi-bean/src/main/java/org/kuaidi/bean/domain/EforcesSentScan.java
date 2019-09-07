package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesSentScan implements Serializable {

    private static final long serialVersionUID = -4225566549515028959L;

    private Integer id;

    private String billsnumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String flightsnumber;

    private String goodstype;

    private String expresstype;

    private Integer expressid;

    private String nextstop;

    private String nextstopname;

    private String scantype;

    private String scanners;

    private String scannerid;

    private String incname;

    private String incid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date scantime;

    private Integer amount;

    private String nextstop1;

    private String nextstopname1;

    private String tranname;

    private String bz;
    
    private Integer isBagBill;
    
    private Integer goodsCount; 

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

    public String getNextstop() {
        return nextstop;
    }

    public void setNextstop(String nextstop) {
        this.nextstop = nextstop == null ? null : nextstop.trim();
    }

    public String getNextstopname() {
        return nextstopname;
    }

    public void setNextstopname(String nextstopname) {
        this.nextstopname = nextstopname == null ? null : nextstopname.trim();
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

    public String getNextstop1() {
        return nextstop1;
    }

    public void setNextstop1(String nextstop1) {
        this.nextstop1 = nextstop1 == null ? null : nextstop1.trim();
    }

    public String getNextstopname1() {
        return nextstopname1;
    }

    public void setNextstopname1(String nextstopname1) {
        this.nextstopname1 = nextstopname1 == null ? null : nextstopname1.trim();
    }

    public String getTranname() {
        return tranname;
    }

    public void setTranname(String tranname) {
        this.tranname = tranname == null ? null : tranname.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

	public Integer getIsBagBill() {
		return isBagBill;
	}

	public void setIsBagBill(Integer isBagBill) {
		this.isBagBill = isBagBill;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

    @Override
    public String toString() {
        return "EforcesSentScan{" +
                "id=" + id +
                ", billsnumber='" + billsnumber + '\'' +
                ", createtime=" + createtime +
                ", flightsnumber='" + flightsnumber + '\'' +
                ", goodstype='" + goodstype + '\'' +
                ", expresstype='" + expresstype + '\'' +
                ", expressid=" + expressid +
                ", nextstop='" + nextstop + '\'' +
                ", nextstopname='" + nextstopname + '\'' +
                ", scantype='" + scantype + '\'' +
                ", scanners='" + scanners + '\'' +
                ", scannerid='" + scannerid + '\'' +
                ", incname='" + incname + '\'' +
                ", incid='" + incid + '\'' +
                ", scantime=" + scantime +
                ", amount=" + amount +
                ", nextstop1='" + nextstop1 + '\'' +
                ", nextstopname1='" + nextstopname1 + '\'' +
                ", tranname='" + tranname + '\'' +
                ", bz='" + bz + '\'' +
                ", isBagBill=" + isBagBill +
                ", goodsCount=" + goodsCount +
                '}';
    }
}