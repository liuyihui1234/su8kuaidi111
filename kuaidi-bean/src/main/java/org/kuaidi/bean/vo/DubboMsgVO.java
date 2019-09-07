package org.kuaidi.bean.vo;

import java.io.Serializable;


/*
 * 从servic端传过来的操作结果。
 * 如果操作有事物处理的时候， 需要在service 从执行完，将结果返回回来。
 */
public class DubboMsgVO implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6595570280735200576L;
	
	private boolean rstFlage ; 
	
	private String msg;
	
	public DubboMsgVO() {
		rstFlage = false; 
		msg = "执行操作失败！";
	}

	public boolean isRstFlage() {
		return rstFlage;
	}

	public void setRstFlage(boolean rstFlage) {
		this.rstFlage = rstFlage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "dubboMsgVO [rstFlage=" + rstFlage + ", msg=" + msg + "]";
	}
	
}
