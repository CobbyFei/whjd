package com.dbsoft.whjd.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * MaintainceRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "maintainceRecord", schema = "dbo", catalog = "whjd")
public class MaintainceRecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private DeviceInfo deviceInfo;
	private SysUser sysUser;
	private Timestamp maintainceTime;
	private Boolean isNormal;
	private String measures;
	private String maintainceOutcome;
	private Integer status;

	// Constructors

	/** default constructor */
	public MaintainceRecord() {
	}

	/** full constructor */
	public MaintainceRecord(DeviceInfo deviceInfo, SysUser sysUser,
			Timestamp maintainceTime, Boolean isNormal, String measures,
			String maintainceOutcome, Integer status) {
		this.deviceInfo = deviceInfo;
		this.sysUser = sysUser;
		this.maintainceTime = maintainceTime;
		this.isNormal = isNormal;
		this.measures = measures;
		this.maintainceOutcome = maintainceOutcome;
		this.status = status;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "recordID", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deviceID")
	public DeviceInfo getDeviceInfo() {
		return this.deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "maintainceTime", length = 23)
	public Timestamp getMaintainceTime() {
		return this.maintainceTime;
	}

	public void setMaintainceTime(Timestamp maintainceTime) {
		this.maintainceTime = maintainceTime;
	}

	@Column(name = "isNormal")
	public Boolean getIsNormal() {
		return this.isNormal;
	}

	public void setIsNormal(Boolean isNormal) {
		this.isNormal = isNormal;
	}

	@Column(name = "measures", length = 30)
	public String getMeasures() {
		return this.measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	@Column(name = "maintainceOutcome", length = 30)
	public String getMaintainceOutcome() {
		return this.maintainceOutcome;
	}

	public void setMaintainceOutcome(String maintainceOutcome) {
		this.maintainceOutcome = maintainceOutcome;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}