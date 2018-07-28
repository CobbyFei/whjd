package com.dbsoft.whjd.model;

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
 * StationManagement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stationManagement", schema = "dbo", catalog = "whjd")
public class StationManagement implements java.io.Serializable {

	// Fields

	private Integer id;
	private InspectionStation inspectionStation;
	private SysUser sysUser;
	private Integer status;
	private String remark;

	// Constructors

	/** default constructor */
	public StationManagement() {
	}

	/** full constructor */
	public StationManagement(InspectionStation inspectionStation,
			SysUser sysUser, Integer status, String remark) {
		this.inspectionStation = inspectionStation;
		this.sysUser = sysUser;
		this.status = status;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Ins_stationID")
	public InspectionStation getInspectionStation() {
		return this.inspectionStation;
	}

	public void setInspectionStation(InspectionStation inspectionStation) {
		this.inspectionStation = inspectionStation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remark", length = 40)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}