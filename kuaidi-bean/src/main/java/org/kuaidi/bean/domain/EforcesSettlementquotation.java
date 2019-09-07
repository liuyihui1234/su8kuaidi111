package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EforcesSettlementquotation implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8539089896000932277L;

	private Integer id;

    private String isfisweight;

    private String costtype;

    private String calcumethod;

    private String goodtype;

    private String paytype;

    private BigDecimal operatpay;

    private BigDecimal discount;

    private String transporttype;

    private Integer carrierid;

    private String carriername;

    private Integer fisweight;

    private BigDecimal fwpay;

    private String isfell;

    private Integer fisnum;

    private Integer starwei1;

    private Integer endwei1;

    private String isfell1;

    private BigDecimal danwei1;

    private Integer starwei2;

    private Integer endwei2;

    private String isfell2;

    private BigDecimal danwei2;

    private Integer starwei3;

    private Integer endwei3;

    private String isfell3;

    private BigDecimal danwei3;

    private Integer starwei4;

    private Integer endwei4;

    private String isfell4;

    private BigDecimal danwei4;

    private Integer starwei5;

    private Integer endwei5;

    private String isfell5;

    private BigDecimal danwei5;

    private Integer starwei6;

    private Integer endwei6;

    private String isfell6;

    private BigDecimal danwei6;

    private Integer starwei7;

    private Integer endwei7;

    private String isfell7;

    private BigDecimal danwei7;

    private Integer starwei8;

    private Integer endwei8;

    private String isfell8;

    private BigDecimal danwei8;

    private Date startime;

    private Date endtime;

    private String incnumber;

    private String incname;

    private String weighttype;

    private BigDecimal jinse;

    private String xuweight;

    private BigDecimal fenjd1;

    private BigDecimal fenjd2;

    private String createid;

    private String createname;

    private Date createtime;
	
	private String destinationids;

    private String destinationname;

    private String recipientids;

    private String recipientname;

    private String remarks;

    public String getDestinationids() {
        return destinationids;
    }

    public void setDestinationids(String destinationids) {
        this.destinationids = destinationids == null ? null : destinationids.trim();
    }

    public String getDestinationname() {
        return destinationname;
    }

    public void setDestinationname(String destinationname) {
        this.destinationname = destinationname == null ? null : destinationname.trim();
    }

    public String getRecipientids() {
        return recipientids;
    }

    public void setRecipientids(String recipientids) {
        this.recipientids = recipientids == null ? null : recipientids.trim();
    }

    public String getRecipientname() {
        return recipientname;
    }

    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname == null ? null : recipientname.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsfisweight() {
        return isfisweight;
    }

    public void setIsfisweight(String isfisweight) {
        this.isfisweight = isfisweight == null ? null : isfisweight.trim();
    }

    public String getCosttype() {
        return costtype;
    }

    public void setCosttype(String costtype) {
        this.costtype = costtype == null ? null : costtype.trim();
    }

    public String getCalcumethod() {
        return calcumethod;
    }

    public void setCalcumethod(String calcumethod) {
        this.calcumethod = calcumethod == null ? null : calcumethod.trim();
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype == null ? null : goodtype.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public BigDecimal getOperatpay() {
        return operatpay;
    }

    public void setOperatpay(BigDecimal operatpay) {
        this.operatpay = operatpay;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getTransporttype() {
        return transporttype;
    }

    public void setTransporttype(String transporttype) {
        this.transporttype = transporttype == null ? null : transporttype.trim();
    }

    public Integer getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(Integer carrierid) {
        this.carrierid = carrierid;
    }

    public String getCarriername() {
        return carriername;
    }

    public void setCarriername(String carriername) {
        this.carriername = carriername == null ? null : carriername.trim();
    }

    public Integer getFisweight() {
        return fisweight;
    }

    public void setFisweight(Integer fisweight) {
        this.fisweight = fisweight;
    }

    public BigDecimal getFwpay() {
        return fwpay;
    }

    public void setFwpay(BigDecimal fwpay) {
        this.fwpay = fwpay;
    }

    public String getIsfell() {
        return isfell;
    }

    public void setIsfell(String isfell) {
        this.isfell = isfell == null ? null : isfell.trim();
    }

    public Integer getFisnum() {
        return fisnum;
    }

    public void setFisnum(Integer fisnum) {
        this.fisnum = fisnum;
    }

    public Integer getStarwei1() {
        return starwei1;
    }

    public void setStarwei1(Integer starwei1) {
        this.starwei1 = starwei1;
    }

    public Integer getEndwei1() {
        return endwei1;
    }

    public void setEndwei1(Integer endwei1) {
        this.endwei1 = endwei1;
    }

    public String getIsfell1() {
        return isfell1;
    }

    public void setIsfell1(String isfell1) {
        this.isfell1 = isfell1 == null ? null : isfell1.trim();
    }

    public BigDecimal getDanwei1() {
        return danwei1;
    }

    public void setDanwei1(BigDecimal danwei1) {
        this.danwei1 = danwei1;
    }

    public Integer getStarwei2() {
        return starwei2;
    }

    public void setStarwei2(Integer starwei2) {
        this.starwei2 = starwei2;
    }

    public Integer getEndwei2() {
        return endwei2;
    }

    public void setEndwei2(Integer endwei2) {
        this.endwei2 = endwei2;
    }

    public String getIsfell2() {
        return isfell2;
    }

    public void setIsfell2(String isfell2) {
        this.isfell2 = isfell2 == null ? null : isfell2.trim();
    }

    public BigDecimal getDanwei2() {
        return danwei2;
    }

    public void setDanwei2(BigDecimal danwei2) {
        this.danwei2 = danwei2;
    }

    public Integer getStarwei3() {
        return starwei3;
    }

    public void setStarwei3(Integer starwei3) {
        this.starwei3 = starwei3;
    }

    public Integer getEndwei3() {
        return endwei3;
    }

    public void setEndwei3(Integer endwei3) {
        this.endwei3 = endwei3;
    }

    public String getIsfell3() {
        return isfell3;
    }

    public void setIsfell3(String isfell3) {
        this.isfell3 = isfell3 == null ? null : isfell3.trim();
    }

    public BigDecimal getDanwei3() {
        return danwei3;
    }

    public void setDanwei3(BigDecimal danwei3) {
        this.danwei3 = danwei3;
    }

    public Integer getStarwei4() {
        return starwei4;
    }

    public void setStarwei4(Integer starwei4) {
        this.starwei4 = starwei4;
    }

    public Integer getEndwei4() {
        return endwei4;
    }

    public void setEndwei4(Integer endwei4) {
        this.endwei4 = endwei4;
    }

    public String getIsfell4() {
        return isfell4;
    }

    public void setIsfell4(String isfell4) {
        this.isfell4 = isfell4 == null ? null : isfell4.trim();
    }

    public BigDecimal getDanwei4() {
        return danwei4;
    }

    public void setDanwei4(BigDecimal danwei4) {
        this.danwei4 = danwei4;
    }

    public Integer getStarwei5() {
        return starwei5;
    }

    public void setStarwei5(Integer starwei5) {
        this.starwei5 = starwei5;
    }

    public Integer getEndwei5() {
        return endwei5;
    }

    public void setEndwei5(Integer endwei5) {
        this.endwei5 = endwei5;
    }

    public String getIsfell5() {
        return isfell5;
    }

    public void setIsfell5(String isfell5) {
        this.isfell5 = isfell5 == null ? null : isfell5.trim();
    }

    public BigDecimal getDanwei5() {
        return danwei5;
    }

    public void setDanwei5(BigDecimal danwei5) {
        this.danwei5 = danwei5;
    }

    public Integer getStarwei6() {
        return starwei6;
    }

    public void setStarwei6(Integer starwei6) {
        this.starwei6 = starwei6;
    }

    public Integer getEndwei6() {
        return endwei6;
    }

    public void setEndwei6(Integer endwei6) {
        this.endwei6 = endwei6;
    }

    public String getIsfell6() {
        return isfell6;
    }

    public void setIsfell6(String isfell6) {
        this.isfell6 = isfell6 == null ? null : isfell6.trim();
    }

    public BigDecimal getDanwei6() {
        return danwei6;
    }

    public void setDanwei6(BigDecimal danwei6) {
        this.danwei6 = danwei6;
    }

    public Integer getStarwei7() {
        return starwei7;
    }

    public void setStarwei7(Integer starwei7) {
        this.starwei7 = starwei7;
    }

    public Integer getEndwei7() {
        return endwei7;
    }

    public void setEndwei7(Integer endwei7) {
        this.endwei7 = endwei7;
    }

    public String getIsfell7() {
        return isfell7;
    }

    public void setIsfell7(String isfell7) {
        this.isfell7 = isfell7 == null ? null : isfell7.trim();
    }

    public BigDecimal getDanwei7() {
        return danwei7;
    }

    public void setDanwei7(BigDecimal danwei7) {
        this.danwei7 = danwei7;
    }

    public Integer getStarwei8() {
        return starwei8;
    }

    public void setStarwei8(Integer starwei8) {
        this.starwei8 = starwei8;
    }

    public Integer getEndwei8() {
        return endwei8;
    }

    public void setEndwei8(Integer endwei8) {
        this.endwei8 = endwei8;
    }

    public String getIsfell8() {
        return isfell8;
    }

    public void setIsfell8(String isfell8) {
        this.isfell8 = isfell8 == null ? null : isfell8.trim();
    }

    public BigDecimal getDanwei8() {
        return danwei8;
    }

    public void setDanwei8(BigDecimal danwei8) {
        this.danwei8 = danwei8;
    }

    public Date getStartime() {
        return startime;
    }

    public void setStartime(Date startime) {
        this.startime = startime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getIncnumber() {
        return incnumber;
    }

    public void setIncnumber(String incnumber) {
        this.incnumber = incnumber == null ? null : incnumber.trim();
    }

    public String getIncname() {
        return incname;
    }

    public void setIncname(String incname) {
        this.incname = incname == null ? null : incname.trim();
    }

    public String getWeighttype() {
        return weighttype;
    }

    public void setWeighttype(String weighttype) {
        this.weighttype = weighttype == null ? null : weighttype.trim();
    }

    public BigDecimal getJinse() {
        return jinse;
    }

    public void setJinse(BigDecimal jinse) {
        this.jinse = jinse;
    }

    public String getXuweight() {
        return xuweight;
    }

    public void setXuweight(String xuweight) {
        this.xuweight = xuweight == null ? null : xuweight.trim();
    }

    public BigDecimal getFenjd1() {
        return fenjd1;
    }

    public void setFenjd1(BigDecimal fenjd1) {
        this.fenjd1 = fenjd1;
    }

    public BigDecimal getFenjd2() {
        return fenjd2;
    }

    public void setFenjd2(BigDecimal fenjd2) {
        this.fenjd2 = fenjd2;
    }

    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid == null ? null : createid.trim();
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	@Override
	public String toString() {
		return "EforcesSettlementquotation [id=" + id + ", isfisweight=" + isfisweight + ", costtype=" + costtype
				+ ", calcumethod=" + calcumethod + ", goodtype=" + goodtype + ", paytype=" + paytype + ", operatpay="
				+ operatpay + ", discount=" + discount + ", transporttype=" + transporttype + ", carrierid=" + carrierid
				+ ", carriername=" + carriername + ", fisweight=" + fisweight + ", fwpay=" + fwpay + ", isfell="
				+ isfell + ", fisnum=" + fisnum + ", starwei1=" + starwei1 + ", endwei1=" + endwei1 + ", isfell1="
				+ isfell1 + ", danwei1=" + danwei1 + ", starwei2=" + starwei2 + ", endwei2=" + endwei2 + ", isfell2="
				+ isfell2 + ", danwei2=" + danwei2 + ", starwei3=" + starwei3 + ", endwei3=" + endwei3 + ", isfell3="
				+ isfell3 + ", danwei3=" + danwei3 + ", starwei4=" + starwei4 + ", endwei4=" + endwei4 + ", isfell4="
				+ isfell4 + ", danwei4=" + danwei4 + ", starwei5=" + starwei5 + ", endwei5=" + endwei5 + ", isfell5="
				+ isfell5 + ", danwei5=" + danwei5 + ", starwei6=" + starwei6 + ", endwei6=" + endwei6 + ", isfell6="
				+ isfell6 + ", danwei6=" + danwei6 + ", starwei7=" + starwei7 + ", endwei7=" + endwei7 + ", isfell7="
				+ isfell7 + ", danwei7=" + danwei7 + ", starwei8=" + starwei8 + ", endwei8=" + endwei8 + ", isfell8="
				+ isfell8 + ", danwei8=" + danwei8 + ", startime=" + startime + ", endtime=" + endtime + ", incnumber="
				+ incnumber + ", incname=" + incname + ", weighttype=" + weighttype + ", jinse=" + jinse + ", xuweight="
				+ xuweight + ", fenjd1=" + fenjd1 + ", fenjd2=" + fenjd2 + ", createid=" + createid + ", createname="
				+ createname + ", createtime=" + createtime + ", destinationids=" + destinationids
				+ ", destinationname=" + destinationname + ", recipientids=" + recipientids + ", recipientname="
				+ recipientname + ", remarks=" + remarks + "]";
	}
    
}