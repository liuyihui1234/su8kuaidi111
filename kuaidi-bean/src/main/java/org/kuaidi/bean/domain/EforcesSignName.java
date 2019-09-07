package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesSignName implements Serializable {
    private static final long serialVersionUID = -3301781320287483990L;
    
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crttime;

    private Integer userid;

    private String incnumber;

    private Float longitude;

    private Float latitude;

    private Integer receivepointflage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getReceivepointflage() {
        return receivepointflage;
    }

    public void setReceivepointflage(Integer receivepointflage) {
        this.receivepointflage = receivepointflage;
    }
}