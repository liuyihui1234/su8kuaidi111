package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesMenus implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1351676896577097120L;

	private Integer id;

    private String parentid;

    private String text;

    private String leaf;

    private String iconcls;

    private Integer number;

    private String url;
    
    private Integer isDelete;

    private String component;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf == null ? null : leaf.trim();
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	@Override
	public String toString() {
		return "EforcesMenus [id=" + id + ", parentid=" + parentid + ", text=" + text + ", leaf=" + leaf + ", iconcls="
				+ iconcls + ", number=" + number + ", url=" + url + ", isDelete=" + isDelete + "]";
	}

	
    
}