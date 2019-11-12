package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EforcesLogisticStracking implements Serializable, Cloneable {

    private static final long serialVersionUID = -6172060025201329469L;

    private Integer id;

    private String billsnumber;

    @JsonFormat(pattern="MM-dd HH:mm",timezone="GMT+8")
    private Date operationtime;

    private String operators;

    private String incname;

    private String incid;

    private Integer mark;

    private String description;

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

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators == null ? null : operators.trim();
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
		this.incid = incid == null ?"" : incid.trim() ;
	}

	public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    @Override
	public EforcesLogisticStracking clone() throws CloneNotSupportedException {
        return (EforcesLogisticStracking)super.clone();
    }

    @Override
    public String toString() {
        return "EforcesLogisticStracking{" +
                "id=" + id +
                ", billsnumber='" + billsnumber + '\'' +
                ", operationtime=" + operationtime +
                ", operators='" + operators + '\'' +
                ", incname='" + incname + '\'' +
                ", incid=" + incid +
                ", mark=" + mark +
                ", description='" + description + '\'' +
                '}';
    }
}