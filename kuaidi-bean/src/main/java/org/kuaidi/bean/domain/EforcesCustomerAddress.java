package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class EforcesCustomerAddress implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3037079121887285972L;

	private Integer id;

    private String openid;

    private String channelsource;

    private String addressid;

    private String memtype;

    private String contactname;

    private String contacttel;

    private String country;

    private String province;

    private String provincecode;

    private String city;

    private String citycode;

    private String county;

    private String countycode;
    
    private String areastreet;
    
    private String areastreetcode;

    private String locationcode;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String address;

    private String companyname;

    private String contactspell;

    private String postcode;

    private String type;

    private String contactphone;

    private String countrycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getChannelsource() {
        return channelsource;
    }

    public void setChannelsource(String channelsource) {
        this.channelsource = channelsource == null ? null : channelsource.trim();
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid == null ? null : addressid.trim();
    }

    public String getMemtype() {
        return memtype;
    }

    public void setMemtype(String memtype) {
        this.memtype = memtype == null ? null : memtype.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel == null ? null : contacttel.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getCountycode() {
        return countycode;
    }

    public void setCountycode(String countycode) {
        this.countycode = countycode == null ? null : countycode.trim();
    }
    
    public String getAreastreet() {
		return areastreet;
	}

	public void setAreastreet(String areastreet) {
		this.areastreet = areastreet == null ? "" : areastreet.trim();
	}

	public String getAreastreetcode() {
		return areastreetcode;
	}

	public void setAreastreetcode(String areastreetcode) {
		this.areastreetcode = areastreetcode == null ? "" : areastreetcode.trim();
	}

	public String getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode == null ? null : locationcode.trim();
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getContactspell() {
        return contactspell;
    }

    public void setContactspell(String contactspell) {
        this.contactspell = contactspell == null ? null : contactspell.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }
}