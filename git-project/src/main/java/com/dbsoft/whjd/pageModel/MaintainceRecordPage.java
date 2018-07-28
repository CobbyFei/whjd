package com.dbsoft.whjd.pageModel;

public class MaintainceRecordPage {
	private Integer recordId;
	private Integer lineId;
	private String lineName;
	private String stationName;
	private Integer deviceId;
	private String deviceName;
	private Integer userId;
	private String userName;
	private String strMaintainceTime;
	private Integer intIsNormal;
	private String measures;
	private String maintainceOutcome;
	private Integer status;
	private String beginTime;
	private String endTime;
	private String q; // 查询专用字符串
	private Integer page;
	private Integer rows;
	private String ids;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getStrMaintainceTime() {
		return strMaintainceTime;
	}

	public void setStrMaintainceTime(String strMaintainceTime) {
		this.strMaintainceTime = strMaintainceTime;
	}

	public Integer getIntIsNormal() {
		return intIsNormal;
	}

	public void setIntIsNormal(Integer intIsNormal) {
		this.intIsNormal = intIsNormal;
	}

	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	public String getMaintainceOutcome() {
		return maintainceOutcome;
	}

	public void setMaintainceOutcome(String maintainceOutcome) {
		this.maintainceOutcome = maintainceOutcome;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
