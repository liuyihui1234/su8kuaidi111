package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EforcesCustomerSign implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1714990123582885579L;

	private Integer id;

    private String number;
    
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date signtime;

    private String tousersignature;

    private String deliveryusername;

    private String deliveryuserid;

    private String incname;

    private String incid;

    private String operators;

    private String operatorid;

    private String fannex;

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

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public String getTousersignature() {
        return tousersignature;
    }

    public void setTousersignature(String tousersignature) {
        this.tousersignature = tousersignature == null ? null : tousersignature.trim();
    }

    public String getDeliveryusername() {
        return deliveryusername;
    }

    public void setDeliveryusername(String deliveryusername) {
        this.deliveryusername = deliveryusername == null ? null : deliveryusername.trim();
    }

    public String getDeliveryuserid() {
        return deliveryuserid;
    }

    public void setDeliveryuserid(String deliveryuserid) {
        this.deliveryuserid = deliveryuserid == null ? null : deliveryuserid.trim();
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

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators == null ? null : operators.trim();
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid == null ? null : operatorid.trim();
    }

    public String getFannex() {
        return fannex;
    }

    public void setFannex(String fannex) {
        this.fannex = fannex == null ? null : fannex.trim();
    }
}