package com.dbsoft.whjd.pageModel;

import java.io.File;

public class BlackNameListPage {
	private String q;
	private int id;
	private int page;
	private int rows;
	private String ids;
	private String licence;
	private String licenceColor;
	private String violationType;
	private String violationTimePage;
	private String beginTime;
	private String endTime;
	private String violationAddress;
	private String isPunishedPage;
	private String isCancelPage;
	private String remarks;
	private String filePath;
	private File myFile;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}
	public String getLicenceColor() {
		return licenceColor;
	}
	public void setLicenceColor(String licenceColor) {
		this.licenceColor = licenceColor;
	}
	public String getViolationType() {
		return violationType;
	}
	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}
	public String getViolationTimePage() {
		return violationTimePage;
	}
	public void setViolationTimePage(String violationTimePage) {
		this.violationTimePage = violationTimePage;
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
	public String getViolationAddress() {
		return violationAddress;
	}
	public void setViolationAddress(String violationAddress) {
		this.violationAddress = violationAddress;
	}
	public String getIsPunishedPage() {
		return isPunishedPage;
	}
	public void setIsPunishedPage(String isPunishedPage) {
		this.isPunishedPage = isPunishedPage;
	}
	public String getIsCancelPage() {
		return isCancelPage;
	}
	public void setIsCancelPage(String isCancelPage) {
		this.isCancelPage = isCancelPage;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
}
