package org.kuaidi.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EforcesRectoOrder  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1438910342686237652L;

	private Integer id;

    private String number;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operationtime;

    private Integer num;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date scantime;

    private String shift;

    private String images;

    private String postmanid;

    private String postmanname;

    private Integer detypeid;

    private String detypename;

    private Integer scantypeid;

    private String scantypename;

    private String goodstype;

    private Integer goodsid;

    private String scanman;

    private String scanmanid;

    private String createid;

    private String createname;

    private String departname;

    private String departid;

    private String jjrqm;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date jjrqmrq;

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

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getScantime() {
        return scantime;
    }

    public void setScantime(Date scantime) {
        this.scantime = scantime;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift == null ? null : shift.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getPostmanid() {
        return postmanid;
    }

    public void setPostmanid(String postmanid) {
        this.postmanid = postmanid == null ? null : postmanid.trim();
    }

    public String getPostmanname() {
        return postmanname;
    }

    public void setPostmanname(String postmanname) {
        this.postmanname = postmanname == null ? null : postmanname.trim();
    }

    public Integer getDetypeid() {
        return detypeid;
    }

    public void setDetypeid(Integer detypeid) {
        this.detypeid = detypeid;
    }

    public String getDetypename() {
        return detypename;
    }

    public void setDetypename(String detypename) {
        this.detypename = detypename == null ? null : detypename.trim();
    }

    public Integer getScantypeid() {
        return scantypeid;
    }

    public void setScantypeid(Integer scantypeid) {
        this.scantypeid = scantypeid;
    }

    public String getScantypename() {
        return scantypename;
    }

    public void setScantypename(String scantypename) {
        this.scantypename = scantypename == null ? null : scantypename.trim();
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype == null ? null : goodstype.trim();
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getScanman() {
        return scanman;
    }

    public void setScanman(String scanman) {
        this.scanman = scanman == null ? null : scanman.trim();
    }

    public String getScanmanid() {
        return scanmanid;
    }

    public void setScanmanid(String scanmanid) {
        this.scanmanid = scanmanid == null ? null : scanmanid.trim();
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

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname == null ? null : departname.trim();
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid == null ? null : departid.trim();
    }

    public String getJjrqm() {
        return jjrqm;
    }

    public void setJjrqm(String jjrqm) {
        this.jjrqm = jjrqm == null ? null : jjrqm.trim();
    }

    public Date getJjrqmrq() {
        return jjrqmrq;
    }

    public void setJjrqmrq(Date jjrqmrq) {
        this.jjrqmrq = jjrqmrq;
    }
    
}