package org.kuaidi.bean.domain;

import java.io.Serializable;
//省市区县
public class EforcesRegion implements Serializable{

    private static final long serialVersionUID = 7916182217297336703L;

    private String code;

    private String name;

    private String parentCode;

    private String remark;

    private Integer bigZoneId;

    private int level;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getBigZoneId() {
		return bigZoneId;
	}

	public void setBigZoneId(Integer bigZoneId) {
		this.bigZoneId = bigZoneId;
	}

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}