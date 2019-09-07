package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesFeedback implements Serializable {
    private static final long serialVersionUID = -3100540924873213375L;
    private Integer id;

    private Integer type;

    private Integer userid;

    private String incnum;

    private String remark;

    private String phone;

    private Integer allowcall;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crttime;

    private String imgpath1;

    private String imgpath2;

    private String imgpath3;

    private String imgpath4;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getIncnum() {
        return incnum;
    }

    public void setIncnum(String incnum) {
        this.incnum = incnum == null ? null : incnum.trim();
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

    public Integer getAllowcall() {
        return allowcall;
    }

    public void setAllowcall(Integer allowcall) {
        this.allowcall = allowcall;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public String getImgpath1() {
        return imgpath1;
    }

    public void setImgpath1(String imgpath1) {
        this.imgpath1 = imgpath1 == null ? null : imgpath1.trim();
    }

    public String getImgpath2() {
        return imgpath2;
    }

    public void setImgpath2(String imgpath2) {
        this.imgpath2 = imgpath2 == null ? null : imgpath2.trim();
    }

    public String getImgpath3() {
        return imgpath3;
    }

    public void setImgpath3(String imgpath3) {
        this.imgpath3 = imgpath3 == null ? null : imgpath3.trim();
    }

    public String getImgpath4() {
        return imgpath4;
    }

    public void setImgpath4(String imgpath4) {
        this.imgpath4 = imgpath4 == null ? null : imgpath4.trim();
    }
}