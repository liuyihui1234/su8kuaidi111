package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesTransportedscan implements Serializable {

    private static final long serialVersionUID = -8515536633752403L;

    private Integer id;

    private String billsnumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private Integer nextcorpid;

    private String nextcorpname;

    private String nextnumber;

    private String scantype;

    private String scanners;

    private String scannerid;

    private String incname;

    private String incid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date scantime;

    private String sendertel;

    private Integer amount;

    private Integer flag;

    private String bz;

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

    public Integer getNextcorpid() {
        return nextcorpid;
    }

    public void setNextcorpid(Integer nextcorpid) {
        this.nextcorpid = nextcorpid;
    }

    public String getNextcorpname() {
        return nextcorpname;
    }

    public void setNextcorpname(String nextcorpname) {
        this.nextcorpname = nextcorpname == null ? null : nextcorpname.trim();
    }

    public String getNextnumber() {
        return nextnumber;
    }

    public void setNextnumber(String nextnumber) {
        this.nextnumber = nextnumber == null ? null : nextnumber.trim();
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

    public String getSendertel() {
        return sendertel;
    }

    public void setSendertel(String sendertel) {
        this.sendertel = sendertel == null ? null : sendertel.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    @Override
    public String toString() {
        return "EforcesTransportedscan{" +
                "id=" + id +
                ", billsnumber='" + billsnumber + '\'' +
                ", createtime=" + createtime +
                ", nextcorpid=" + nextcorpid +
                ", nextcorpname='" + nextcorpname + '\'' +
                ", nextnumber='" + nextnumber + '\'' +
                ", scantype='" + scantype + '\'' +
                ", scanners='" + scanners + '\'' +
                ", scannerid='" + scannerid + '\'' +
                ", incname='" + incname + '\'' +
                ", incid='" + incid + '\'' +
                ", scantime=" + scantime +
                ", sendertel='" + sendertel + '\'' +
                ", amount=" + amount +
                ", flag=" + flag +
                ", bz='" + bz + '\'' +
                '}';
    }
}