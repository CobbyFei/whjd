package com.dbsoft.whjd.pageModel;

public class PasswordPage {
	private String oldPassword;
	private String newPassword;
	private String rePassword;
	private Integer resetPassword;
	private Integer userId;
	private String simplifyName;
	private Integer stationId;
	private String stationName;
		
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
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public Integer getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(Integer resetPassword) {
		this.resetPassword = resetPassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSimplifyName() {
		return simplifyName;
	}
	public void setSimplifyName(String simplifyName) {
		this.simplifyName = simplifyName;
	}
	
	
}
