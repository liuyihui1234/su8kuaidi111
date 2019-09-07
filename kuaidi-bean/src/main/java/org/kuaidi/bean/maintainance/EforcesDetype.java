package org.kuaidi.bean.maintainance;

import java.io.Serializable;

public class EforcesDetype implements Serializable {

    private static final long serialVersionUID = -7495604809494077943L;

    private Integer id;

    private String number;

    private String name;

    private String recipient;

    private Integer isdelete;

    private String remarks;

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
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "EforcesDetype{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", recipient='" + recipient + '\'' +
                ", isdelete=" + isdelete +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}