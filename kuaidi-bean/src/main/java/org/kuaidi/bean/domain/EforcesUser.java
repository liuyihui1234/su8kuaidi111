package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



public class EforcesUser implements Serializable {
	
    private static final long serialVersionUID = -1905307646553497705L;
    
    private String token ; 

    private Integer id;

    private String number;

    private String password;

    private String name;

    private String incnumber;

    private String incname;

    private String incid;

    private Integer departid;

    private String departname;

    private String mobile;

    private String tel;

    private String businesshall;

    private String businesshallname;

    private String address;

    private String recipient;

    private String recipientcode;

    private String departadmin;

    private String departadminname;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creattime;

    private String lastip;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lasttime;

    private Integer loginnumber;

    private Integer type;

    private Integer method;

    private String portraitpath;

    private String identitynum;

    private String identityfontpath;

    private String identitybackpath;
    
    private String groupid;
    //页面传的验证码
    private String code;
    //页面传的验证码对应uuid
    private String access_token;
    
    private String sendReceiveZone; 

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIncnumber() {
        return incnumber;
    }

    public void setIncnumber(String incnumber) {
        this.incnumber = incnumber == null ? null : incnumber.trim();
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

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname == null ? null : departname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getBusinesshall() {
        return businesshall;
    }

    public void setBusinesshall(String businesshall) {
        this.businesshall = businesshall == null ? null : businesshall.trim();
    }

    public String getBusinesshallname() {
        return businesshallname;
    }

    public void setBusinesshallname(String businesshallname) {
        this.businesshallname = businesshallname == null ? null : businesshallname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getRecipientcode() {
        return recipientcode;
    }

    public void setRecipientcode(String recipientcode) {
        this.recipientcode = recipientcode == null ? null : recipientcode.trim();
    }

    public String getDepartadmin() {
        return departadmin;
    }

    public void setDepartadmin(String departadmin) {
        this.departadmin = departadmin == null ? null : departadmin.trim();
    }

    public String getDepartadminname() {
        return departadminname;
    }

    public void setDepartadminname(String departadminname) {
        this.departadminname = departadminname == null ? null : departadminname.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip == null ? null : lastip.trim();
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getLoginnumber() {
        return loginnumber;
    }

    public void setLoginnumber(Integer loginnumber) {
        this.loginnumber = loginnumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getPortraitpath() {
        return portraitpath;
    }

    public void setPortraitpath(String portraitpath) {
        this.portraitpath = portraitpath == null ? null : portraitpath.trim();
    }

    public String getIdentitynum() {
        return identitynum;
    }

    public void setIdentitynum(String identitynum) {
        this.identitynum = identitynum == null ? null : identitynum.trim();
    }

    public String getIdentityfontpath() {
        return identityfontpath;
    }

    public void setIdentityfontpath(String identityfontpath) {
        this.identityfontpath = identityfontpath == null ? null : identityfontpath.trim();
    }

    public String getIdentitybackpath() {
        return identitybackpath;
    }

    public void setIdentitybackpath(String identitybackpath) {
        this.identitybackpath = identitybackpath == null ? null : identitybackpath.trim();
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSendReceiveZone() {
		return sendReceiveZone;
	}

	public void setSendReceiveZone(String sendReceiveZone) {
		this.sendReceiveZone = sendReceiveZone;
	}

	@Override
	public String toString() {
		return "EforcesUser [token=" + token + ", id=" + id + ", number=" + number + ", password=" + password
				+ ", name=" + name + ", incnumber=" + incnumber + ", incname=" + incname + ", incid=" + incid
				+ ", departid=" + departid + ", departname=" + departname + ", mobile=" + mobile + ", tel=" + tel
				+ ", businesshall=" + businesshall + ", businesshallname=" + businesshallname + ", address=" + address
				+ ", recipient=" + recipient + ", recipientcode=" + recipientcode + ", departadmin=" + departadmin
				+ ", departadminname=" + departadminname + ", creattime=" + creattime + ", lastip=" + lastip
				+ ", lasttime=" + lasttime + ", loginnumber=" + loginnumber + ", type=" + type + ", method=" + method
				+ ", portraitpath=" + portraitpath + ", identitynum=" + identitynum + ", identityfontpath="
				+ identityfontpath + ", identitybackpath=" + identitybackpath + ", groupid=" + groupid + ", code="
				+ code + ", access_token=" + access_token + "]";
	}
	
    
}