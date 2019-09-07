package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesPaydetai implements Serializable {


    private static final long serialVersionUID = 6408297701934195159L;


    private Integer id;

    private String outTradeNo;

    private String tradeNo;

    private String totalAmount;

    private Integer netsignid;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount == null ? null : totalAmount.trim();
    }

    public Integer getNetsignid() {
        return netsignid;
    }

    public void setNetsignid(Integer netsignid) {
        this.netsignid = netsignid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}