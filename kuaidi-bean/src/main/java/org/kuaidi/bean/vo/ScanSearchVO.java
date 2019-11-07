package org.kuaidi.bean.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScanSearchVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2018522894005474723L;

	//订单号，多个用\\r 隔开
	private String billsNumber ;
	
	//派件员的编号
	private String distributeNum;
	
	//扫码类型
	private String scanType ; 
	
	//收件员编号
	private String receiveNumber ; 
	
	//物品类型
	private String goodsType;
	
	//发件站点（或者到件站点）
	private String incNumber;
	
	//提货人
	private String toName;
	
	//快件类型
	private String expresstype;
	
	//扫描网点
	private String scanIncNumber;

	//扫描人编号
	private String scannerNumber; 
	
	//班次
	private String flightsnumber;
	
	private String scanTime; 
	//扫码开始时间
	private String scanStartTime;
	
	//扫描结束时间
	private String scanEndTime;
	
	private List<String>  billsNumberList = new ArrayList<String>(); 

	public String getDistributeNum() {
		return distributeNum;
	}

	public void setDistributeNum(String distributeNum) {
		this.distributeNum = distributeNum;
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getReceiveNumber() {
		return receiveNumber;
	}

	public void setReceiveNumber(String receiveNumber) {
		this.receiveNumber = receiveNumber;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getIncNumber() {
		return incNumber;
	}

	public void setIncNumber(String incNumber) {
		this.incNumber = incNumber;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getExpresstype() {
		return expresstype;
	}

	public void setExpresstype(String expresstype) {
		this.expresstype = expresstype;
	}

	public String getScanIncNumber() {
		return scanIncNumber;
	}

	public void setScanIncNumber(String scanIncNumber) {
		this.scanIncNumber = scanIncNumber;
	}

	public String getScannerNumber() {
		return scannerNumber;
	}

	public void setScannerNumber(String scannerNumber) {
		this.scannerNumber = scannerNumber;
	}

	public String getFlightsnumber() {
		return flightsnumber;
	}

	public void setFlightsnumber(String flightsnumber) {
		this.flightsnumber = flightsnumber;
	}

	public String getScanStartTime() {
		return scanStartTime;
	}

	public void setScanStartTime(String scanStartTime) {
		this.scanStartTime = scanStartTime;
	}

	public String getScanEndTime() {
		return scanEndTime;
	}

	public void setScanEndTime(String scanEndTime) {
		this.scanEndTime = scanEndTime;
	}

	public String getBillsNumber() {
		return billsNumber;
	}

	public void setBillsNumber(String billsNumber) {
		this.billsNumber = billsNumber;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}
	
	public List<String> getBillsNumberList() {
		return billsNumberList;
	}

	public void setBillsNumberList(List<String> billsNumberList) {
		this.billsNumberList = billsNumberList;
	}

	@Override
	public String toString() {
		return "ScanSearchVO [billsNumber=" + billsNumber + ", distributeNum="
				+ distributeNum + ", scanType=" + scanType + ", receiveNumber="
				+ receiveNumber + ", goodsType=" + goodsType + ", incNumber=" + incNumber + ", toName=" + toName
				+ ", expresstype=" + expresstype + ", scanIncNumber=" + scanIncNumber + ", scannerNumber="
				+ scannerNumber + ", flightsnumber=" + flightsnumber + ", scanTime=" + scanTime + ", scanStartTime="
				+ scanStartTime + ", scanEndTime=" + scanEndTime + "]";
	}

}
