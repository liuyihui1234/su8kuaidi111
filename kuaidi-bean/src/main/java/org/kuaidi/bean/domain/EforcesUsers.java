package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesUsers implements Serializable{
    
	private static final long serialVersionUID = -341493534419902508L;

	private Integer uid;

    private String username;

    private String password;

    private String lastip;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lasttime;

    private Integer loginnumber;

    private String name;

    private String depaid;

    private Integer points;

    private String cardid;

    private String cardnumber;

    private Integer incmentid;

    private Integer type;

    private Integer ismac;

    private Integer gongxian;

    private String showmodule;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepaid() {
        return depaid;
    }

    public void setDepaid(String depaid) {
        this.depaid = depaid == null ? null : depaid.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    public Integer getIncmentid() {
        return incmentid;
    }

    public void setIncmentid(Integer incmentid) {
        this.incmentid = incmentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsmac() {
        return ismac;
    }

    public void setIsmac(Integer ismac) {
        this.ismac = ismac;
    }

    public Integer getGongxian() {
        return gongxian;
    }

    public void setGongxian(Integer gongxian) {
        this.gongxian = gongxian;
    }

    public String getShowmodule() {
        return showmodule;
    }

    public void setShowmodule(String showmodule) {
        this.showmodule = showmodule == null ? null : showmodule.trim();
    }
}