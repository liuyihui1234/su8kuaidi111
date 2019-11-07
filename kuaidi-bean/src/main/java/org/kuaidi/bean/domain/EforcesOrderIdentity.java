package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesOrderIdentity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3569176546240230422L;

	private Integer id;

    private String billsnumber;

    private String identitynum;

    private String identitypic1;

    private String identitypic2;

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

    public String getIdentitynum() {
        return identitynum;
    }

    public void setIdentitynum(String identitynum) {
        this.identitynum = identitynum == null ? null : identitynum.trim();
    }

    public String getIdentitypic1() {
        return identitypic1;
    }

    public void setIdentitypic1(String identitypic1) {
        this.identitypic1 = identitypic1 == null ? null : identitypic1.trim();
    }

    public String getIdentitypic2() {
        return identitypic2;
    }

    public void setIdentitypic2(String identitypic2) {
        this.identitypic2 = identitypic2 == null ? null : identitypic2.trim();
    }
}