package com.dbsoft.whjd.pageModel;

import java.sql.Timestamp;

import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.SysUser;

public class EnvironmentalLabelCollarPage {
	private Integer id;
	private Integer stationId;
	private Integer userId;
	private String stationName;
	private String userName;
	private String  collarTime;
	private Integer year;
	private Integer yellowLabelNum;
	private Integer greenLabelNum;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCollarTime() {
		return collarTime;
	}
	public void setCollarTime(String collarTime) {
		this.collarTime = collarTime;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getYellowLabelNum() {
		return yellowLabelNum;
	}
	public void setYellowLabelNum(Integer yellowLabelNum) {
		this.yellowLabelNum = yellowLabelNum;
	}
	public Integer getGreenLabelNum() {
		return greenLabelNum;
	}
	public void setGreenLabelNum(Integer greenLabelNum) {
		this.greenLabelNum = greenLabelNum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "EnvironmentalLabelCollar [yellowLabelNum=" + yellowLabelNum
				+ ", greenLabelNum=" + greenLabelNum + ", year="
				+ year + ", remarks=" + remarks +  "]";
	}
}
