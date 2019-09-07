package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesCity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6441541799590142719L;

	private Integer id;

    private String code;

    private String name;

    private String provinceid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }
}