package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesNetsign implements Serializable {

    private static final long serialVersionUID = 1970264748462422520L;

    private Integer id;

    private String incnumber;

    private Integer signtype;

    private String identityfont;

    private String identityback;
    
    private String identitynum;

    private String companyname;

    private String legalperson;

    private String signpic;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crttime;

    private String province;

    private String city;

    private String network;

    private String county;

    private Integer userid;

    private Integer paytype;

    private Float paymoney;

    private String networkareacode;

    private Integer status;
    
    private String contractpath;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date contractstarttime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date contractendtime;
    
    private Integer delflage;

    private Integer checkuserid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date checktime;

    private String default1;

    private String default2;


    private EforcesBankPayInfo eforcesBankPayInfo;
    private EforcesPaydetai eforcesPaydetai;
    private EforcesIncment eforcesIncment;

    public EforcesIncment getEforcesIncment() {
        return eforcesIncment;
    }

    public void setEforcesIncment(EforcesIncment eforcesIncment) {
        this.eforcesIncment = eforcesIncment;
    }

    public EforcesBankPayInfo getEforcesBankPayInfo() {
        return eforcesBankPayInfo;
    }

    public void setEforcesBankPayInfo(EforcesBankPayInfo eforcesBankPayInfo) {
        this.eforcesBankPayInfo = eforcesBankPayInfo;
    }

    public EforcesPaydetai getEforcesPaydetai() {
        return eforcesPaydetai;
    }

    public void setEforcesPaydetai(EforcesPaydetai eforcesPaydetai) {
        this.eforcesPaydetai = eforcesPaydetai;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIncnumber() {
        return incnumber;
    }

    public void setIncnumber(String incnumber) {
        this.incnumber = incnumber == null ? null : incnumber.trim();
    }

    public Integer getSigntype() {
        return signtype;
    }

    public void setSigntype(Integer signtype) {
        this.signtype = signtype;
    }

    public String getIdentityfont() {
        return identityfont;
    }

    public void setIdentityfont(String identityfont) {
        this.identityfont = identityfont == null ? null : identityfont.trim();
    }

    public String getIdentityback() {
        return identityback;
    }

    public void setIdentityback(String identityback) {
        this.identityback = identityback == null ? null : identityback.trim();
    }

    public String getIdentitynum() {
		return identitynum;
	}

	public void setIdentitynum(String identitynum) {
		this.identitynum = identitynum == null ? null : identitynum.trim();
	}

	public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson == null ? null : legalperson.trim();
    }

    public String getSignpic() {
        return signpic;
    }

    public void setSignpic(String signpic) {
        this.signpic = signpic == null ? null : signpic.trim();
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Float getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Float paymoney) {
        this.paymoney = paymoney;
    }

    public String getNetworkareacode() {
        return networkareacode;
    }

    public void setNetworkareacode(String networkareacode) {
        this.networkareacode = networkareacode == null ? null : networkareacode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getContractpath() {
        return contractpath;
    }

    public void setContractpath(String contractpath) {
        this.contractpath = contractpath == null ? null : contractpath.trim();
    }

    public Date getContractstarttime() {
        return contractstarttime;
    }

    public void setContractstarttime(Date contractstarttime) {
        this.contractstarttime = contractstarttime;
    }

    public Date getContractendtime() {
        return contractendtime;
    }

    public void setContractendtime(Date contractendtime) {
        this.contractendtime = contractendtime;
    }
    
    public Integer getDelflage() {
        return delflage;
    }

    public void setDelflage(Integer delflage) {
        this.delflage = delflage;
    }

    public Integer getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Integer checkuserid) {
        this.checkuserid = checkuserid;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public String getDefault1() {
        return default1;
    }

    public void setDefault1(String default1) {
        this.default1 = default1 == null ? null : default1.trim();
    }

    public String getDefault2() {
        return default2;
    }

    public void setDefault2(String default2) {
        this.default2 = default2 == null ? null : default2.trim();
    }
}