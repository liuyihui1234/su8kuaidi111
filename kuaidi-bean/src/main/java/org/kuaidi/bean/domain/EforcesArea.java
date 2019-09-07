package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesArea implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4249216347097614722L;

	private Integer id;

    private String code;

    private String cityid;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "EforcesArea [id=" + id + ", code=" + code + ", cityid=" + cityid + ", name=" + name + "]";
	}
    
    
}