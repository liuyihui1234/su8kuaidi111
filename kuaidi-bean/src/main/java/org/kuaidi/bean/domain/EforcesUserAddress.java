package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.Date;

public class EforcesUserAddress implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3923602261721852651L;

	private Integer id;

    private String provincecode;

    private String provincename;

    private String citycode;

    private String cityname;

    private String areacode;

    private String areaname;
    
    private String areastreet;
    
    private String areastreetcode; 

    private String customname;

    private String mobile;

    private String companyname;

    private Date ctrime;

    private String address;

    private Float latitude;

    private Float longitude;

    private Integer isdelete;

    private Integer userid;
    
    private Integer status; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename == null ? null : provincename.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getAreaname() {
        return areaname;
    }
    
    

    public String getAreastreet() {
		return areastreet;
	}

	public void setAreastreet(String areastreet) {
		this.areastreet = areastreet == null ? null : areastreet.trim();
	}

	public String getAreastreetcode() {
		return areastreetcode;
	}

	public void setAreastreetcode(String areastreetcode) {
		this.areastreetcode = areastreetcode == null ? null : areastreetcode.trim();
	}

	public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        this.customname = customname == null ? null : customname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public Date getCtrime() {
        return ctrime;
    }

    public void setCtrime(Date ctrime) {
        this.ctrime = ctrime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
}