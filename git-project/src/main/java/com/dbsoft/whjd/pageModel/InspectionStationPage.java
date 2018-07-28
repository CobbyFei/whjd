package com.dbsoft.whjd.pageModel;

import java.sql.Timestamp;

public class InspectionStationPage {
	private Integer stationId;
	private String stationName;
	private String stationAddress;
	private String  registrationTime;
	private String qulificationType;
	private String qulificationTime;
	private String validPeriod;
	private Integer directorId;	
	private String userName;
	private String status;
	private String remarks;
	private String ids;
	private int page;
	private int rows;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationAddress() {
		return stationAddress;
	}
	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	public String getQulificationType() {
		return qulificationType;
	}
	public void setQulificationType(String qulificationType) {
		this.qulificationType = qulificationType;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getQulificationTime() {
		return qulificationTime;
	}
	public void setQulificationTime(String qulificationTime) {
		this.qulificationTime = qulificationTime;
	}
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	
	@Override
	public String toString() {
		return "DevicePurchaseRecord [stationId=" + stationId + ", stationName="
				+ stationName + ", stationAddress=" + stationAddress
				+ ", registrationTime=" + registrationTime + ", qulificationType="
				+ qulificationType + ", qulificationTime=" + qulificationTime
				+ ", validPeriod=" + validPeriod +", remarks=" + remarks
				+"]";
	}
	
	
	
	
}
