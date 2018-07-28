package com.dbsoft.whjd.pageModel;

import java.sql.Timestamp;

import com.dbsoft.whjd.model.InspectionStation;

public class DevicePurchaseRecordPage {
	private Integer recordId;
	private Integer inspectionStationId;
	private String userStationName;//************
	private String stationName;
	private String deviceName;
	private String manufacturer;
	private String deviceModel;
	private String specification;
	private Integer purchaseNum;
	private String selectPurchaseTime;//选择的时间（前台）
	private String beforePurchaseTime;//购买时间前
	private String afterPurchaseTime;//购买时间后
	private String remarks;
	private String status;
	private String ids;
	private int page;
	private int rows;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getUserStationName() {
		return userStationName;
	}
	public void setUserStationName(String userStationName) {
		this.userStationName = userStationName;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getBeforePurchaseTime() {
		return beforePurchaseTime;
	}
	public void setBeforePurchaseTime(String beforePurchaseTime) {
		this.beforePurchaseTime = beforePurchaseTime;
	}
	public String getAfterPurchaseTime() {
		return afterPurchaseTime;
	}
	public void setAfterPurchaseTime(String afterPurchaseTime) {
		this.afterPurchaseTime = afterPurchaseTime;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getInspectionStationId() {
		return inspectionStationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public void setInspectionStationId(Integer inspectionStationId) {
		this.inspectionStationId = inspectionStationId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public Integer getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getSelectPurchaseTime() {
		return selectPurchaseTime;
	}
	public void setSelectPurchaseTime(String selectPurchaseTime) {
		this.selectPurchaseTime = selectPurchaseTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "DevicePurchaseRecord [deviceName="
				+ deviceName + ", manufacturer=" + manufacturer
				+ ", deviceModel=" + deviceModel + ", purchaseNum="
				+ purchaseNum + ", remarks=" + remarks +  "]";
	}

}
