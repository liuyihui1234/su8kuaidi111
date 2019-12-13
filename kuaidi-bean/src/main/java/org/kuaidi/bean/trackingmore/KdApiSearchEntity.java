package org.kuaidi.bean.trackingmore;

import java.util.ArrayList;
import java.util.List;

public class KdApiSearchEntity {
	
	public String EBusinessID;
	
	public String OrderCode;
	
	public String ShipperCode;
	
	public String LogisticCode;
	
	public boolean Success;
	
	public int State; 
	
	public Object Reason ;
	
	public List<TraceEntity> Traces = new ArrayList<TraceEntity>();
	
	public String getEBusinessID() {
		return EBusinessID;
	}




	public void setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
	}




	public String getOrderCode() {
		return OrderCode;
	}




	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}




	public String getShipperCode() {
		return ShipperCode;
	}




	public void setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
	}




	public String getLogisticCode() {
		return LogisticCode;
	}




	public void setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
	}




	public boolean isSuccess() {
		return Success;
	}




	public void setSuccess(boolean success) {
		Success = success;
	}




	public int getState() {
		return State;
	}




	public void setState(int state) {
		State = state;
	}




	public Object getReason() {
		return Reason;
	}

	public void setReason(Object reason) {
		Reason = reason;
	}

	public List<TraceEntity> getTraces() {
		return Traces;
	}

	public void setTraces(List<TraceEntity> traces) {
		Traces = traces;
	}


}
