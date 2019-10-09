package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EforcesWeighingScan  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7493137090469795140L;

	private Integer id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String flightsnumber;

    private String goodstype;

    private String expresstype;

    private Integer expressid;

    private String postman;

    private String postmanid;

    private String did;

    private String destination;

    private BigDecimal weight;

    private String scantype;

    private String scanners;

    private String scannerid;

    private String incname;

    private String incid;

    private Date scantime;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date entrytime;

    private Integer amount;
    
    private String  billsnumber;
    
    private String bz;
	
	public String getBillsnumber() {
        return billsnumber;
    }

    public void setBillsnumber(String billsnumber) {
        this.billsnumber = billsnumber == null ? null : billsnumber.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFlightsnumber() {
        return flightsnumber;
    }

    public void setFlightsnumber(String flightsnumber) {
        this.flightsnumber = flightsnumber == null ? null : flightsnumber.trim();
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype == null ? null : goodstype.trim();
    }

    public String getExpresstype() {
        return expresstype;
    }

    public void setExpresstype(String expresstype) {
        this.expresstype = expresstype == null ? null : expresstype.trim();
    }

    public Integer getExpressid() {
        return expressid;
    }

    public void setExpressid(Integer expressid) {
        this.expressid = expressid;
    }

    public String getPostman() {
        return postman;
    }

    public void setPostman(String postman) {
        this.postman = postman == null ? null : postman.trim();
    }

    public String getPostmanid() {
        return postmanid;
    }

    public void setPostmanid(String postmanid) {
        this.postmanid = postmanid == null ? null : postmanid.trim();
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getScantype() {
        return scantype;
    }

    public void setScantype(String scantype) {
        this.scantype = scantype == null ? null : scantype.trim();
    }

    public String getScanners() {
        return scanners;
    }

    public void setScanners(String scanners) {
        this.scanners = scanners == null ? null : scanners.trim();
    }

    public String getScannerid() {
        return scannerid;
    }

    public void setScannerid(String scannerid) {
        this.scannerid = scannerid == null ? null : scannerid.trim();
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

    public Date getScantime() {
        return scantime;
    }

    public void setScantime(Date scantime) {
        this.scantime = scantime;
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}