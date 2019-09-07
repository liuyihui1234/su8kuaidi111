package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesAreaStreet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1887793957688858927L;

	private Integer id;

    private String code;

    private String areaid;

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

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}