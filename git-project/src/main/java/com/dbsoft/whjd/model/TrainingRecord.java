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
 * TrainingRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trainingRecord", schema = "dbo", catalog = "whjd")
public class TrainingRecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private SysUser sysUser;
	private Timestamp trainingTime;
	private String trainingAddress;
	private Integer trainingType;
	private String trainingDetail;
	private Integer status;

	// Constructors

	/** default constructor */
	public TrainingRecord() {
	}

	/** full constructor */
	public TrainingRecord(SysUser sysUser, Timestamp trainingTime,
			String trainingAddress, Integer trainingType,
			String trainingDetail, Integer status) {
		this.sysUser = sysUser;
		this.trainingTime = trainingTime;
		this.trainingAddress = trainingAddress;
		this.trainingType = trainingType;
		this.trainingDetail = trainingDetail;
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
	@JoinColumn(name = "userID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "trainingTime", length = 23)
	public Timestamp getTrainingTime() {
		return this.trainingTime;
	}

	public void setTrainingTime(Timestamp trainingTime) {
		this.trainingTime = trainingTime;
	}

	@Column(name = "trainingAddress", length = 50)
	public String getTrainingAddress() {
		return this.trainingAddress;
	}

	public void setTrainingAddress(String trainingAddress) {
		this.trainingAddress = trainingAddress;
	}

	@Column(name = "trainingType")
	public Integer getTrainingType() {
		return this.trainingType;
	}

	public void setTrainingType(Integer trainingType) {
		this.trainingType = trainingType;
	}

	@Column(name = "trainingDetail", length = 50)
	public String getTrainingDetail() {
		return this.trainingDetail;
	}

	public void setTrainingDetail(String trainingDetail) {
		this.trainingDetail = trainingDetail;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}