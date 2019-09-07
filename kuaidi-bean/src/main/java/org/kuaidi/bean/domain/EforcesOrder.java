package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EforcesOrder  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9121889069902789634L;

	private Integer id;

    private String number;

    private String formguestcode;

    private String formguestname;

    private String fromname;

    private String fromcity;

    private String fromcityname;

    private String fromprovince;

    private String fromprovincename;

    private String fromarea;

    private String fromareaname;

    private String fromareastreet;

    private String fromareastreetname;

    private String fromincname;

    private String fromaddress;

    private String fromtel;

    private String fromcode;

    private String toguestname;

    private String toguestcode;

    private String toprovince;

    private String toprovincename;

    private String tocity;

    private String tocityname;

    private String toarea;

    private String toareaname;

    private String toareastreet;

    private String toareastreetname;

    private String toincname;

    private String toaddress;

    private String toname;

    private String tocode;

    private String totel;

    private Integer paymentflag;

    private BigDecimal paymentforgoods;

    private Integer isfile;

    private String isgoods;

    private String filename;

    private Integer num;

    private BigDecimal weight;

    private BigDecimal longs;

    private BigDecimal widths;

    private BigDecimal heights;

    private BigDecimal volume;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date begintime;

    private String senduserid;

    private String sendusername;

    private String fromusersignature;

    private String sendusersignature;

    private Integer iscollection;

    private Integer paymode;

    private Integer isinsured;

    private BigDecimal insuredsumprice;

    private BigDecimal insuredprice;

    private Integer ismobilesend;

    private BigDecimal mobileprice;

    private BigDecimal price;

    private Integer sendmode;

    private BigDecimal modeprice;

    private String tousersignature;

    private String collectionsignature;

    private String signaturecode;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;

    private BigDecimal sumprice;

    private String deliveryuserid;

    private String deliveryusername;
    
    private String barcodePath;
    
    private Integer isDelete;
    
    private Integer status;
    
    
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createtime;

    private String createuserid;

    private String createusername;

    private String createincnumber;

    private String createincname;

    private Integer zqj;

    private Integer zjj;

    private Integer sfdshk;

    private BigDecimal dshk;

    private BigDecimal dsfy;

    private Integer qfhd;

    private String hdh;

    private BigDecimal fdfy;

    private Integer tsps;

    private BigDecimal ywfy;

    private BigDecimal yyfy;

    private Integer bzjg;

    private BigDecimal clfy;

    private BigDecimal rgfy;

    private Integer qtgxhfw;

    private BigDecimal dzfy;

    private Integer iszf;

    private Integer isduanxin;

    private String sjrshouji;

    private Integer wupinnum;

    private BigDecimal wupinprices;

    private String kahao;

    private Integer dsfwf;

    private Integer tssp;

    private Integer isqdfh;

    private String zzfwqt;

    private Integer fyjs;

    private BigDecimal fyzl;

    private BigDecimal fyjfzl;

    private BigDecimal yyfjf;

    private String fkfs;

    private String fkfsqt;

    private String yuejiezhanghu;

    private String openid;

    private String sfz;

    private String zhcode;

    private String zhnumber;

    private Byte isgettrace;
	
	private String remark;

    private String fwyq;

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

    public String getFormguestcode() {
        return formguestcode;
    }

    public void setFormguestcode(String formguestcode) {
        this.formguestcode = formguestcode;
    }

    public String getFormguestname() {
        return formguestname;
    }

    public void setFormguestname(String formguestname) {
        this.formguestname = formguestname;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    public String getFromcityname() {
        return fromcityname;
    }

    public void setFromcityname(String fromcityname) {
        this.fromcityname = fromcityname;
    }

    public String getFromprovince() {
        return fromprovince;
    }

    public void setFromprovince(String fromprovince) {
        this.fromprovince = fromprovince;
    }

    public String getFromprovincename() {
        return fromprovincename;
    }

    public void setFromprovincename(String fromprovincename) {
        this.fromprovincename = fromprovincename;
    }

    public String getFromarea() {
        return fromarea;
    }

    public void setFromarea(String fromarea) {
        this.fromarea = fromarea;
    }

    public String getFromareaname() {
        return fromareaname;
    }

    public void setFromareaname(String fromareaname) {
        this.fromareaname = fromareaname;
    }

    public String getFromareastreet() {
        return fromareastreet;
    }

    public void setFromareastreet(String fromareastreet) {
        this.fromareastreet = fromareastreet;
    }

    public String getFromareastreetname() {
        return fromareastreetname;
    }

    public void setFromareastreetname(String fromareastreetname) {
        this.fromareastreetname = fromareastreetname;
    }

    public String getFromincname() {
        return fromincname;
    }

    public void setFromincname(String fromincname) {
        this.fromincname = fromincname;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress;
    }

    public String getFromtel() {
        return fromtel;
    }

    public void setFromtel(String fromtel) {
        this.fromtel = fromtel;
    }

    public String getFromcode() {
        return fromcode;
    }

    public void setFromcode(String fromcode) {
        this.fromcode = fromcode;
    }

    public String getToguestname() {
        return toguestname;
    }

    public void setToguestname(String toguestname) {
        this.toguestname = toguestname;
    }

    public String getToguestcode() {
        return toguestcode;
    }

    public void setToguestcode(String toguestcode) {
        this.toguestcode = toguestcode;
    }

    public String getToprovince() {
        return toprovince;
    }

    public void setToprovince(String toprovince) {
        this.toprovince = toprovince;
    }

    public String getToprovincename() {
        return toprovincename;
    }

    public void setToprovincename(String toprovincename) {
        this.toprovincename = toprovincename;
    }

    public String getTocity() {
        return tocity;
    }

    public void setTocity(String tocity) {
        this.tocity = tocity;
    }

    public String getTocityname() {
        return tocityname;
    }

    public void setTocityname(String tocityname) {
        this.tocityname = tocityname;
    }

    public String getToarea() {
        return toarea;
    }

    public void setToarea(String toarea) {
        this.toarea = toarea;
    }

    public String getToareaname() {
        return toareaname;
    }

    public void setToareaname(String toareaname) {
        this.toareaname = toareaname;
    }

    public String getToareastreet() {
        return toareastreet;
    }

    public void setToareastreet(String toareastreet) {
        this.toareastreet = toareastreet;
    }

    public String getToareastreetname() {
        return toareastreetname;
    }

    public void setToareastreetname(String toareastreetname) {
        this.toareastreetname = toareastreetname;
    }

    public String getToincname() {
        return toincname;
    }

    public void setToincname(String toincname) {
        this.toincname = toincname;
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getTocode() {
        return tocode;
    }

    public void setTocode(String tocode) {
        this.tocode = tocode;
    }

    public String getTotel() {
        return totel;
    }

    public void setTotel(String totel) {
        this.totel = totel;
    }

    public Integer getPaymentflag() {
        return paymentflag;
    }

    public void setPaymentflag(Integer paymentflag) {
        this.paymentflag = paymentflag;
    }

    public BigDecimal getPaymentforgoods() {
        return paymentforgoods;
    }

    public void setPaymentforgoods(BigDecimal paymentforgoods) {
        this.paymentforgoods = paymentforgoods;
    }

    public Integer getIsfile() {
        return isfile;
    }

    public void setIsfile(Integer isfile) {
        this.isfile = isfile;
    }

    public String getIsgoods() {
        return isgoods;
    }

    public void setIsgoods(String isgoods) {
        this.isgoods = isgoods;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getLongs() {
        return longs;
    }

    public void setLongs(BigDecimal longs) {
        this.longs = longs;
    }

    public BigDecimal getWidths() {
        return widths;
    }

    public void setWidths(BigDecimal widths) {
        this.widths = widths;
    }

    public BigDecimal getHeights() {
        return heights;
    }

    public void setHeights(BigDecimal heights) {
        this.heights = heights;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public String getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(String senduserid) {
        this.senduserid = senduserid;
    }

    public String getSendusername() {
        return sendusername;
    }

    public void setSendusername(String sendusername) {
        this.sendusername = sendusername;
    }

    public String getFromusersignature() {
        return fromusersignature;
    }

    public void setFromusersignature(String fromusersignature) {
        this.fromusersignature = fromusersignature;
    }

    public String getSendusersignature() {
        return sendusersignature;
    }

    public void setSendusersignature(String sendusersignature) {
        this.sendusersignature = sendusersignature;
    }

    public Integer getIscollection() {
        return iscollection;
    }

    public void setIscollection(Integer iscollection) {
        this.iscollection = iscollection;
    }

    public Integer getPaymode() {
        return paymode;
    }

    public void setPaymode(Integer paymode) {
        this.paymode = paymode;
    }

    public Integer getIsinsured() {
        return isinsured;
    }

    public void setIsinsured(Integer isinsured) {
        this.isinsured = isinsured;
    }

    public BigDecimal getInsuredsumprice() {
        return insuredsumprice;
    }

    public void setInsuredsumprice(BigDecimal insuredsumprice) {
        this.insuredsumprice = insuredsumprice;
    }

    public BigDecimal getInsuredprice() {
        return insuredprice;
    }

    public void setInsuredprice(BigDecimal insuredprice) {
        this.insuredprice = insuredprice;
    }

    public Integer getIsmobilesend() {
        return ismobilesend;
    }

    public void setIsmobilesend(Integer ismobilesend) {
        this.ismobilesend = ismobilesend;
    }

    public BigDecimal getMobileprice() {
        return mobileprice;
    }

    public void setMobileprice(BigDecimal mobileprice) {
        this.mobileprice = mobileprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSendmode() {
        return sendmode;
    }

    public void setSendmode(Integer sendmode) {
        this.sendmode = sendmode;
    }

    public BigDecimal getModeprice() {
        return modeprice;
    }

    public void setModeprice(BigDecimal modeprice) {
        this.modeprice = modeprice;
    }

    public String getTousersignature() {
        return tousersignature;
    }

    public void setTousersignature(String tousersignature) {
        this.tousersignature = tousersignature;
    }

    public String getCollectionsignature() {
        return collectionsignature;
    }

    public void setCollectionsignature(String collectionsignature) {
        this.collectionsignature = collectionsignature;
    }

    public String getSignaturecode() {
        return signaturecode;
    }

    public void setSignaturecode(String signaturecode) {
        this.signaturecode = signaturecode;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public BigDecimal getSumprice() {
        return sumprice;
    }

    public void setSumprice(BigDecimal sumprice) {
        this.sumprice = sumprice;
    }

    public String getDeliveryuserid() {
        return deliveryuserid;
    }

    public void setDeliveryuserid(String deliveryuserid) {
        this.deliveryuserid = deliveryuserid;
    }

    public String getDeliveryusername() {
        return deliveryusername;
    }

    public void setDeliveryusername(String deliveryusername) {
        this.deliveryusername = deliveryusername;
    }

    public String getBarcodePath() {
        return barcodePath;
    }

    public void setBarcodePath(String barcodePath) {
        this.barcodePath = barcodePath;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getCreateincnumber() {
        return createincnumber;
    }

    public void setCreateincnumber(String createincnumber) {
        this.createincnumber = createincnumber;
    }

    public String getCreateincname() {
        return createincname;
    }

    public void setCreateincname(String createincname) {
        this.createincname = createincname;
    }

    public Integer getZqj() {
        return zqj;
    }

    public void setZqj(Integer zqj) {
        this.zqj = zqj;
    }

    public Integer getZjj() {
        return zjj;
    }

    public void setZjj(Integer zjj) {
        this.zjj = zjj;
    }

    public Integer getSfdshk() {
        return sfdshk;
    }

    public void setSfdshk(Integer sfdshk) {
        this.sfdshk = sfdshk;
    }

    public BigDecimal getDshk() {
        return dshk;
    }

    public void setDshk(BigDecimal dshk) {
        this.dshk = dshk;
    }

    public BigDecimal getDsfy() {
        return dsfy;
    }

    public void setDsfy(BigDecimal dsfy) {
        this.dsfy = dsfy;
    }

    public Integer getQfhd() {
        return qfhd;
    }

    public void setQfhd(Integer qfhd) {
        this.qfhd = qfhd;
    }

    public String getHdh() {
        return hdh;
    }

    public void setHdh(String hdh) {
        this.hdh = hdh;
    }

    public BigDecimal getFdfy() {
        return fdfy;
    }

    public void setFdfy(BigDecimal fdfy) {
        this.fdfy = fdfy;
    }

    public Integer getTsps() {
        return tsps;
    }

    public void setTsps(Integer tsps) {
        this.tsps = tsps;
    }

    public BigDecimal getYwfy() {
        return ywfy;
    }

    public void setYwfy(BigDecimal ywfy) {
        this.ywfy = ywfy;
    }

    public BigDecimal getYyfy() {
        return yyfy;
    }

    public void setYyfy(BigDecimal yyfy) {
        this.yyfy = yyfy;
    }

    public Integer getBzjg() {
        return bzjg;
    }

    public void setBzjg(Integer bzjg) {
        this.bzjg = bzjg;
    }

    public BigDecimal getClfy() {
        return clfy;
    }

    public void setClfy(BigDecimal clfy) {
        this.clfy = clfy;
    }

    public BigDecimal getRgfy() {
        return rgfy;
    }

    public void setRgfy(BigDecimal rgfy) {
        this.rgfy = rgfy;
    }

    public Integer getQtgxhfw() {
        return qtgxhfw;
    }

    public void setQtgxhfw(Integer qtgxhfw) {
        this.qtgxhfw = qtgxhfw;
    }

    public BigDecimal getDzfy() {
        return dzfy;
    }

    public void setDzfy(BigDecimal dzfy) {
        this.dzfy = dzfy;
    }

    public Integer getIszf() {
        return iszf;
    }

    public void setIszf(Integer iszf) {
        this.iszf = iszf;
    }

    public Integer getIsduanxin() {
        return isduanxin;
    }

    public void setIsduanxin(Integer isduanxin) {
        this.isduanxin = isduanxin;
    }

    public String getSjrshouji() {
        return sjrshouji;
    }

    public void setSjrshouji(String sjrshouji) {
        this.sjrshouji = sjrshouji;
    }

    public Integer getWupinnum() {
        return wupinnum;
    }

    public void setWupinnum(Integer wupinnum) {
        this.wupinnum = wupinnum;
    }

    public BigDecimal getWupinprices() {
        return wupinprices;
    }

    public void setWupinprices(BigDecimal wupinprices) {
        this.wupinprices = wupinprices;
    }

    public String getKahao() {
        return kahao;
    }

    public void setKahao(String kahao) {
        this.kahao = kahao;
    }

    public Integer getDsfwf() {
        return dsfwf;
    }

    public void setDsfwf(Integer dsfwf) {
        this.dsfwf = dsfwf;
    }

    public Integer getTssp() {
        return tssp;
    }

    public void setTssp(Integer tssp) {
        this.tssp = tssp;
    }

    public Integer getIsqdfh() {
        return isqdfh;
    }

    public void setIsqdfh(Integer isqdfh) {
        this.isqdfh = isqdfh;
    }

    public String getZzfwqt() {
        return zzfwqt;
    }

    public void setZzfwqt(String zzfwqt) {
        this.zzfwqt = zzfwqt;
    }

    public Integer getFyjs() {
        return fyjs;
    }

    public void setFyjs(Integer fyjs) {
        this.fyjs = fyjs;
    }

    public BigDecimal getFyzl() {
        return fyzl;
    }

    public void setFyzl(BigDecimal fyzl) {
        this.fyzl = fyzl;
    }

    public BigDecimal getFyjfzl() {
        return fyjfzl;
    }

    public void setFyjfzl(BigDecimal fyjfzl) {
        this.fyjfzl = fyjfzl;
    }

    public BigDecimal getYyfjf() {
        return yyfjf;
    }

    public void setYyfjf(BigDecimal yyfjf) {
        this.yyfjf = yyfjf;
    }

    public String getFkfs() {
        return fkfs;
    }

    public void setFkfs(String fkfs) {
        this.fkfs = fkfs;
    }

    public String getFkfsqt() {
        return fkfsqt;
    }

    public void setFkfsqt(String fkfsqt) {
        this.fkfsqt = fkfsqt;
    }

    public String getYuejiezhanghu() {
        return yuejiezhanghu;
    }

    public void setYuejiezhanghu(String yuejiezhanghu) {
        this.yuejiezhanghu = yuejiezhanghu;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getZhcode() {
        return zhcode;
    }

    public void setZhcode(String zhcode) {
        this.zhcode = zhcode;
    }

    public String getZhnumber() {
        return zhnumber;
    }

    public void setZhnumber(String zhnumber) {
        this.zhnumber = zhnumber;
    }

    public Byte getIsgettrace() {
        return isgettrace;
    }

    public void setIsgettrace(Byte isgettrace) {
        this.isgettrace = isgettrace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFwyq() {
        return fwyq;
    }

    public void setFwyq(String fwyq) {
        this.fwyq = fwyq;
    }
    
    

    public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "EforcesOrder{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", formguestcode='" + formguestcode + '\'' +
                ", formguestname='" + formguestname + '\'' +
                ", fromname='" + fromname + '\'' +
                ", fromcity='" + fromcity + '\'' +
                ", fromcityname='" + fromcityname + '\'' +
                ", fromprovince='" + fromprovince + '\'' +
                ", fromprovincename='" + fromprovincename + '\'' +
                ", fromarea='" + fromarea + '\'' +
                ", fromareaname='" + fromareaname + '\'' +
                ", fromareastreet='" + fromareastreet + '\'' +
                ", fromareastreetname='" + fromareastreetname + '\'' +
                ", fromincname='" + fromincname + '\'' +
                ", fromaddress='" + fromaddress + '\'' +
                ", fromtel='" + fromtel + '\'' +
                ", fromcode='" + fromcode + '\'' +
                ", toguestname='" + toguestname + '\'' +
                ", toguestcode='" + toguestcode + '\'' +
                ", toprovince='" + toprovince + '\'' +
                ", toprovincename='" + toprovincename + '\'' +
                ", tocity='" + tocity + '\'' +
                ", tocityname='" + tocityname + '\'' +
                ", toarea='" + toarea + '\'' +
                ", toareaname='" + toareaname + '\'' +
                ", toareastreet='" + toareastreet + '\'' +
                ", toareastreetname='" + toareastreetname + '\'' +
                ", toincname='" + toincname + '\'' +
                ", toaddress='" + toaddress + '\'' +
                ", toname='" + toname + '\'' +
                ", tocode='" + tocode + '\'' +
                ", totel='" + totel + '\'' +
                ", paymentflag=" + paymentflag +
                ", paymentforgoods=" + paymentforgoods +
                ", isfile=" + isfile +
                ", isgoods='" + isgoods + '\'' +
                ", filename='" + filename + '\'' +
                ", num=" + num +
                ", weight=" + weight +
                ", longs=" + longs +
                ", widths=" + widths +
                ", heights=" + heights +
                ", volume=" + volume +
                ", begintime=" + begintime +
                ", senduserid='" + senduserid + '\'' +
                ", sendusername='" + sendusername + '\'' +
                ", fromusersignature='" + fromusersignature + '\'' +
                ", sendusersignature='" + sendusersignature + '\'' +
                ", iscollection=" + iscollection +
                ", paymode=" + paymode +
                ", isinsured=" + isinsured +
                ", insuredsumprice=" + insuredsumprice +
                ", insuredprice=" + insuredprice +
                ", ismobilesend=" + ismobilesend +
                ", mobileprice=" + mobileprice +
                ", price=" + price +
                ", sendmode=" + sendmode +
                ", modeprice=" + modeprice +
                ", tousersignature='" + tousersignature + '\'' +
                ", collectionsignature='" + collectionsignature + '\'' +
                ", signaturecode='" + signaturecode + '\'' +
                ", endtime=" + endtime +
                ", sumprice=" + sumprice +
                ", deliveryuserid='" + deliveryuserid + '\'' +
                ", deliveryusername='" + deliveryusername + '\'' +
                ", barcodePath='" + barcodePath + '\'' +
                ", createtime=" + createtime +
                ", createuserid='" + createuserid + '\'' +
                ", createusername='" + createusername + '\'' +
                ", createincnumber='" + createincnumber + '\'' +
                ", createincname='" + createincname + '\'' +
                ", zqj=" + zqj +
                ", zjj=" + zjj +
                ", sfdshk=" + sfdshk +
                ", dshk=" + dshk +
                ", dsfy=" + dsfy +
                ", qfhd=" + qfhd +
                ", hdh='" + hdh + '\'' +
                ", fdfy=" + fdfy +
                ", tsps=" + tsps +
                ", ywfy=" + ywfy +
                ", yyfy=" + yyfy +
                ", bzjg=" + bzjg +
                ", clfy=" + clfy +
                ", rgfy=" + rgfy +
                ", qtgxhfw=" + qtgxhfw +
                ", dzfy=" + dzfy +
                ", iszf=" + iszf +
                ", isduanxin=" + isduanxin +
                ", sjrshouji='" + sjrshouji + '\'' +
                ", wupinnum=" + wupinnum +
                ", wupinprices=" + wupinprices +
                ", kahao='" + kahao + '\'' +
                ", dsfwf=" + dsfwf +
                ", tssp=" + tssp +
                ", isqdfh=" + isqdfh +
                ", zzfwqt='" + zzfwqt + '\'' +
                ", fyjs=" + fyjs +
                ", fyzl=" + fyzl +
                ", fyjfzl=" + fyjfzl +
                ", yyfjf=" + yyfjf +
                ", fkfs='" + fkfs + '\'' +
                ", fkfsqt='" + fkfsqt + '\'' +
                ", yuejiezhanghu='" + yuejiezhanghu + '\'' +
                ", openid='" + openid + '\'' +
                ", sfz='" + sfz + '\'' +
                ", zhcode='" + zhcode + '\'' +
                ", zhnumber='" + zhnumber + '\'' +
                ", isgettrace=" + isgettrace +
                ", remark='" + remark + '\'' +
                ", fwyq='" + fwyq + '\'' +
                '}';
    }
}