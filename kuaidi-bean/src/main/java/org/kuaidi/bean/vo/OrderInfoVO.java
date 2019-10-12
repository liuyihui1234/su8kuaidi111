package org.kuaidi.bean.vo;

import java.io.Serializable;

//
public class OrderInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String sendStartTime ; 
	
	private String sendEndTime;
	
	//录入开始时间
	private String inputStartTime;
	
	//录入结束时间
	private String inputEndTime;
	
	private String remark ;

	public String getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public String getInputStartTime() {
		return inputStartTime;
	}

	public void setInputStartTime(String inputStartTime) {
		this.inputStartTime = inputStartTime;
	}

	public String getInputEndTime() {
		return inputEndTime;
	}

	public void setInputEndTime(String inputEndTime) {
		this.inputEndTime = inputEndTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
