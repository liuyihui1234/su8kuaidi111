package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesIncment implements Serializable {
    private static final long serialVersionUID = -3732413922844403025L;
    private Integer id;

    private String name;

    private String parentid;

    private String remark;

    private String leaderidlist;

    private String mnemonic;

    private String number;

    private String moneytype;

    private String tel;

    private String mobile;

    private String lagearea;

    private String regional;

    private String province;

    private String city;

    private String area;

    private String type;

    private String defaultsenda;

    private String financecenter;

    private String financecentername;

    private String parentname;

    private String deliveryrange;

    private String deliveryrangename;

    private String weburl;

    private String featurescode;

    private Integer isfinancecenter;

    private Integer istopay;

    private Integer istransfer;

    private String transfername;

    private String transfernumber;

    private Integer istariff;

    private String provincename;

    private String cityname;

    private String areaname;
    
    private Integer level; 
    
    private String address; 
    

    public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLeaderidlist() {
        return leaderidlist;
    }

    public void setLeaderidlist(String leaderidlist) {
        this.leaderidlist = leaderidlist == null ? null : leaderidlist.trim();
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic == null ? null : mnemonic.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getMoneytype() {
        return moneytype;
    }

    public void setMoneytype(String moneytype) {
        this.moneytype = moneytype == null ? null : moneytype.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLagearea() {
        return lagearea;
    }

    public void setLagearea(String lagearea) {
        this.lagearea = lagearea == null ? null : lagearea.trim();
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional == null ? null : regional.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDefaultsenda() {
        return defaultsenda;
    }

    public void setDefaultsenda(String defaultsenda) {
        this.defaultsenda = defaultsenda == null ? null : defaultsenda.trim();
    }

    public String getFinancecenter() {
        return financecenter;
    }

    public void setFinancecenter(String financecenter) {
        this.financecenter = financecenter == null ? null : financecenter.trim();
    }

    public String getFinancecentername() {
        return financecentername;
    }

    public void setFinancecentername(String financecentername) {
        this.financecentername = financecentername == null ? null : financecentername.trim();
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname == null ? null : parentname.trim();
    }

    public String getDeliveryrange() {
        return deliveryrange;
    }

    public void setDeliveryrange(String deliveryrange) {
        this.deliveryrange = deliveryrange == null ? null : deliveryrange.trim();
    }

    public String getDeliveryrangename() {
        return deliveryrangename;
    }

    public void setDeliveryrangename(String deliveryrangename) {
        this.deliveryrangename = deliveryrangename == null ? null : deliveryrangename.trim();
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl == null ? null : weburl.trim();
    }

    public String getFeaturescode() {
        return featurescode;
    }

    public void setFeaturescode(String featurescode) {
        this.featurescode = featurescode == null ? null : featurescode.trim();
    }

    public Integer getIsfinancecenter() {
        return isfinancecenter;
    }

    public void setIsfinancecenter(Integer isfinancecenter) {
        this.isfinancecenter = isfinancecenter;
    }

    public Integer getIstopay() {
        return istopay;
    }

    public void setIstopay(Integer istopay) {
        this.istopay = istopay;
    }

    public Integer getIstransfer() {
        return istransfer;
    }

    public void setIstransfer(Integer istransfer) {
        this.istransfer = istransfer;
    }

    public String getTransfername() {
        return transfername;
    }

    public void setTransfername(String transfername) {
        this.transfername = transfername == null ? null : transfername.trim();
    }

    public String getTransfernumber() {
        return transfernumber;
    }

    public void setTransfernumber(String transfernumber) {
        this.transfernumber = transfernumber == null ? null : transfernumber.trim();
    }

    public Integer getIstariff() {
        return istariff;
    }

    public void setIstariff(Integer istariff) {
        this.istariff = istariff;
    }
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? "" : address.trim();
	}

	@Override
    public String toString() {
        return "EforcesIncment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentid='" + parentid + '\'' +
                ", remark='" + remark + '\'' +
                ", leaderidlist='" + leaderidlist + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                ", number='" + number + '\'' +
                ", moneytype='" + moneytype + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", lagearea='" + lagearea + '\'' +
                ", regional='" + regional + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", type='" + type + '\'' +
                ", defaultsenda='" + defaultsenda + '\'' +
                ", financecenter='" + financecenter + '\'' +
                ", financecentername='" + financecentername + '\'' +
                ", parentname='" + parentname + '\'' +
                ", deliveryrange='" + deliveryrange + '\'' +
                ", deliveryrangename='" + deliveryrangename + '\'' +
                ", weburl='" + weburl + '\'' +
                ", featurescode='" + featurescode + '\'' +
                ", isfinancecenter=" + isfinancecenter +
                ", istopay=" + istopay +
                ", istransfer=" + istransfer +
                ", transfername='" + transfername + '\'' +
                ", transfernumber='" + transfernumber + '\'' +
                ", istariff=" + istariff +
                ", provincename='" + provincename + '\'' +
                ", cityname='" + cityname + '\'' +
                '}';
    }
}