package com.dbsoft.whjd.pageModel;

import java.util.ArrayList;
import java.util.List;

public class SysUserPage {
	
	private Integer userId;
	private String userName;
	private String simplifyName;
	private String idcard;
	private String sex;
	private String jobTitle;
	private String tel;
	private String certificateName;
	private String strCertificateTime;
	private String degree;
	private Integer userStatus;
	private String password;
	private String stationName;
	private Integer stationId; 
	private Integer stationStatus;
	

	private String ids;
	private Integer changeToStatus;
	/**
	 * 排序列的名称，在pageModel中的列
	 */
	private String sort;
	
	/**
	 * 升序还是降序
	 */
	private String order;
	/**
	 * 第几页数据
	 */
	private Integer page;
	
	/**
	 * 每页记录数
	 */
	private Integer rows;
	

	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getChangeToStatus() {
		return changeToStatus;
	}

	public void setChangeToStatus(Integer changeToStatus) {
		this.changeToStatus = changeToStatus;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	@Override
	public String toString(){
		return " userName:"+userName+ " simplifyName: " + simplifyName + " idcard:" + idcard + " sex:" +sex 
				+ " jobTitle:" + jobTitle + " tel:"+ tel + " certificateName: "+ certificateName 
				+ " strCertificateTime:" + strCertificateTime +" degree:"+ degree + " status:" +userStatus + " password"+ password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSimplifyName() {
		return simplifyName;
	}
	public void setSimplifyName(String simplifyName) {
		this.simplifyName = simplifyName;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getCertificateName() {
		return certificateName;
	}
	
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStrCertificateTime() {
		return strCertificateTime;
	}

	public void setStrCertificateTime(String strCertificateTime) {
		this.strCertificateTime = strCertificateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	
	public Integer getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(Integer stationStatus) {
		this.stationStatus = stationStatus;
	}
	
	
}
