package org.kuaidi.bean.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//
public class OrderInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sendTime;
	
	private String sendStartTime ; 
	
	private String sendEndTime;
	
	private String inputTime;
	
	//录入开始时间
	private String inputStartTime;
	
	//录入结束时间
	private String inputEndTime;
	
	private String remark ;
	
	private Float modeprice;
	
	private String billsNum;
	
	private List<String> billsNumberList = new ArrayList<String>(); 
	
	private String fromname;
	
	private String toname;
	
//	private String tiJianRen ;
//	
//	private Integer flightId;
//	
//	private String dest;
//	
//	private String toProvince; 
//	
//	private String sendCustomer;
//	
//	private Integer payType ;
//	
//	private String incNumber ; 
//	
//	private Integer transportMode; 
//	
//	private String inputUser; 
//	
//	private String kuaijianType ; 
//	
//	private String recvCustomer; 
//	
//	private Integer isZiqujian; 
	
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

	public Float getModeprice() {
		return modeprice;
	}

	public void setModeprice(Float modeprice) {
		this.modeprice = modeprice;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public String getBillsNum() {
		return billsNum;
	}

	public void setBillsNum(String billsNum) {
		this.billsNum = billsNum;
	}

	public List<String> getBillsNumberList() {
		return billsNumberList;
	}

	public void setBillsNumberList(List<String> billsNumberList) {
		this.billsNumberList = billsNumberList;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}
	
}
