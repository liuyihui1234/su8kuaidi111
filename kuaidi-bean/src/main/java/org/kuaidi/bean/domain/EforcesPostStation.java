package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EforcesPostStation implements Serializable {

	private static final long serialVersionUID = 5436217964049702213L;

	private Integer  id;
	private String name; 
	private String incNumber;
	private String address;
	private  float longtitude;
	private float latitude;
	private String remark;
	private Integer delFlage;

	private String distance ;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private  Date crtTime;
	private String contactsPeople;
	private String phoneNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIncNumber() {
		return incNumber;
	}
	public void setIncNumber(String incNumber) {
		this.incNumber = incNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDelFlage() {
		return delFlage;
	}
	public void setDelFlage(Integer delFlage) {
		this.delFlage = delFlage;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	public String getContactsPeople() {
		return contactsPeople;
	}
	public void setContactsPeople(String contactsPeople) {
		this.contactsPeople = contactsPeople;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EforcesPostStation [id=" + id + ", incNumber=" + incNumber + ", address=" + address + ", longtitude="
				+ longtitude + ", latitude=" + latitude + ", remark=" + remark + ", delFlage=" + delFlage + ", crtTime="
				+ crtTime + ", contactsPeople=" + contactsPeople + ", phoneNum=" + phoneNum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EforcesPostStation other = (EforcesPostStation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}