package org.kuaidi.bean.domain;

import java.io.Serializable;

public class MenusUsersActionVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8899719112277499404L;
	private Integer id;
	private String  text;
	private String 	actionName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	@Override
	public String toString() {
		return "MenusUsersActionVo [id=" + id + ", text=" + text + ", actionName=" + actionName + "]";
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
		MenusUsersActionVo other = (MenusUsersActionVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
