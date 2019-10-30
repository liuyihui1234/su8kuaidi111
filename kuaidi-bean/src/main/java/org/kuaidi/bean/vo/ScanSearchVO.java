package org.kuaidi.bean.vo;

public class ScanSearchVO {
	
	//发送地址所在城市名字
	private String fromCityNumber; 
	
	//派件员的编号
	private String distributeNum;
	
	//扫码类型
	private int scanType ; 
	
	//目的地城市
	private String toCityNumber; 
	
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
	
	//扫码开始时间
	private String scanStartTime;
	
	//扫描结束时间
	private String scanEndTime;

	public String getFromCityNumber() {
		return fromCityNumber;
	}

	public void setFromCityNumber(String fromCityNumber) {
		this.fromCityNumber = fromCityNumber;
	}

	public String getToCityNumber() {
		return toCityNumber;
	}

	public void setToCityNumber(String toCityNumber) {
		this.toCityNumber = toCityNumber;
	}

	public String getDistributeNum() {
		return distributeNum;
	}

	public void setDistributeNum(String distributeNum) {
		this.distributeNum = distributeNum;
	}

	public int getScanType() {
		return scanType;
	}

	public void setScanType(int scanType) {
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

	@Override
	public String toString() {
		return "ScanSearchVO [fromCityNumber=" + fromCityNumber + ", distributeNum=" + distributeNum + ", scanType="
				+ scanType + ", toCityNumber=" + toCityNumber + ", receiveNumber=" + receiveNumber + ", goodsType="
				+ goodsType + ", incNumber=" + incNumber + ", toName=" + toName + ", expresstype=" + expresstype
				+ ", scanIncNumber=" + scanIncNumber + ", scannerNumber=" + scannerNumber + ", flightsnumber="
				+ flightsnumber + ", scanStartTime=" + scanStartTime + ", scanEndTime=" + scanEndTime + "]";
	}
	
}
