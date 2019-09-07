package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesBankPayInfo implements Serializable{

    private static final long serialVersionUID = -7652377813978836255L;

	private Integer id;

    private String customername;

    private String banknum;

    private String banktype;

    private String billpic;

    private Integer signid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum == null ? null : banknum.trim();
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype == null ? null : banktype.trim();
    }

    public String getBillpic() {
        return billpic;
    }

    public void setBillpic(String billpic) {
        this.billpic = billpic == null ? null : billpic.trim();
    }

    public Integer getSignid() {
        return signid;
    }

    public void setSignid(Integer signid) {
        this.signid = signid;
    }
}