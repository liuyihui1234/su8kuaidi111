package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.Date;

public class EforcesIncmentProfit implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3290474447056025821L;

	private Integer id;

    private String incid;

    private String incname;

    private String billsnumber;

    private Float profit;

    private Date createtime;

    private Date billstime;

    private String operateusernumber;

    private Integer profittype;

    private String bigzonename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIncid() {
        return incid;
    }

    public void setIncid(String incid) {
        this.incid = incid == null ? null : incid.trim();
    }

    public String getIncname() {
        return incname;
    }

    public void setIncname(String incname) {
        this.incname = incname == null ? null : incname.trim();
    }

    public String getBillsnumber() {
        return billsnumber;
    }

    public void setBillsnumber(String billsnumber) {
        this.billsnumber = billsnumber == null ? null : billsnumber.trim();
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getBillstime() {
        return billstime;
    }

    public void setBillstime(Date billstime) {
        this.billstime = billstime;
    }

    public String getOperateusernumber() {
        return operateusernumber;
    }

    public void setOperateusernumber(String operateusernumber) {
        this.operateusernumber = operateusernumber == null ? null : operateusernumber.trim();
    }

    public Integer getProfittype() {
        return profittype;
    }

    public void setProfittype(Integer profittype) {
        this.profittype = profittype;
    }

    public String getBigzonename() {
        return bigzonename;
    }

    public void setBigzonename(String bigzonename) {
        this.bigzonename = bigzonename == null ? null : bigzonename.trim();
    }

}