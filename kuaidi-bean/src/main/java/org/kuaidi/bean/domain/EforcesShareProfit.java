package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesShareProfit implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4292897822918953201L;

	private Integer id;

    private Integer type;

    private Float total;

    private Float province;

    private Float city;

    private Float area;

    private Float areastreet;

    private Float company;

    private String fromprovinceid;

    private String toprovinceid;
    
    private Integer weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getProvince() {
        return province;
    }

    public void setProvince(Float province) {
        this.province = province;
    }

    public Float getCity() {
        return city;
    }

    public void setCity(Float city) {
        this.city = city;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getAreastreet() {
        return areastreet;
    }

    public void setAreastreet(Float areastreet) {
        this.areastreet = areastreet;
    }

    public Float getCompany() {
        return company;
    }

    public void setCompany(Float company) {
        this.company = company;
    }

    public String getFromprovinceid() {
        return fromprovinceid;
    }

    public void setFromprovinceid(String fromprovinceid) {
        this.fromprovinceid = fromprovinceid == null ? null : fromprovinceid.trim();
    }

    public String getToprovinceid() {
        return toprovinceid;
    }

    public void setToprovinceid(String toprovinceid) {
        this.toprovinceid = toprovinceid == null ? null : toprovinceid.trim();
    }

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EforcesShareProfit [id=" + id + ", type=" + type + ", total=" + total + ", province=" + province
				+ ", city=" + city + ", area=" + area + ", areastreet=" + areastreet + ", company=" + company
				+ ", fromprovinceid=" + fromprovinceid + ", toprovinceid=" + toprovinceid + "]";
	}
    
    
}