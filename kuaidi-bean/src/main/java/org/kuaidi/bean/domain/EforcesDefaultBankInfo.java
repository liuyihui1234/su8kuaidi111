package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesDefaultBankInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5348979601461325538L;

	private Integer id;

    private String bankcustomer;

    private String banknumber;

    private String banktype;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankcustomer() {
        return bankcustomer;
    }

    public void setBankcustomer(String bankcustomer) {
        this.bankcustomer = bankcustomer == null ? null : bankcustomer.trim();
    }

    public String getBanknumber() {
        return banknumber;
    }

    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber == null ? null : banknumber.trim();
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype == null ? null : banktype.trim();
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }
}