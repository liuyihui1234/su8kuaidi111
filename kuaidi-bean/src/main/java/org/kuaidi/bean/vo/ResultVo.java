package org.kuaidi.bean.vo;

import org.kuaidi.bean.Config;

import java.io.Serializable;

public class ResultVo implements Serializable {
	
	private int code = Config.OK ; 
	
	private String msg ;
	
	private Object data ;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	
}
