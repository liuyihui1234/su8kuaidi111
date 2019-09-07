package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesComplaint implements Serializable {
    private static final long serialVersionUID = 5596282723991530931L;
    private Integer id;

    private String title;

    private String remark;

    private String phone;

    private String customername;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date crttime;

    private Integer status;

    private String incnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIncnumber() {
        return incnumber;
    }

    public void setIncnumber(String incnumber) {
        this.incnumber = incnumber == null ? null : incnumber.trim();
    }
}