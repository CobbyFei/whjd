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
 * CalibrationRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "calibrationRecord", schema = "dbo", catalog = "whjd")
public class CalibrationRecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private SysUser sysUser;
	private DetectionLine detectionLine;
	private Timestamp calibrationTime;
	private Double nostandardValue;
	private Double noafterValue;
	private Double hcstandardValue;
	private Double hcafterValue;
	private Double costandardValue;
	private Double coafterValue;
	private Double co2standardValue;
	private Double co2afterValue;
	private Integer status;

	// Constructors

	/** default constructor */
	public CalibrationRecord() {
	}

	/** full constructor */
	public CalibrationRecord(SysUser sysUser, DetectionLine detectionLine,
			Timestamp calibrationTime, Double nostandardValue,
			Double noafterValue, Double hcstandardValue, Double hcafterValue,
			Double costandardValue, Double coafterValue,
			Double co2standardValue, Double co2afterValue, Integer status) {
		this.sysUser = sysUser;
		this.detectionLine = detectionLine;
		this.calibrationTime = calibrationTime;
		this.nostandardValue = nostandardValue;
		this.noafterValue = noafterValue;
		this.hcstandardValue = hcstandardValue;
		this.hcafterValue = hcafterValue;
		this.costandardValue = costandardValue;
		this.coafterValue = coafterValue;
		this.co2standardValue = co2standardValue;
		this.co2afterValue = co2afterValue;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lineID")
	public DetectionLine getDetectionLine() {
		return this.detectionLine;
	}

	public void setDetectionLine(DetectionLine detectionLine) {
		this.detectionLine = detectionLine;
	}

	@Column(name = "calibrationTime", length = 23)
	public Timestamp getCalibrationTime() {
		return this.calibrationTime;
	}

	public void setCalibrationTime(Timestamp calibrationTime) {
		this.calibrationTime = calibrationTime;
	}

	@Column(name = "NOStandardValue", precision = 53, scale = 0)
	public Double getNostandardValue() {
		return this.nostandardValue;
	}

	public void setNostandardValue(Double nostandardValue) {
		this.nostandardValue = nostandardValue;
	}

	@Column(name = "NOAfterValue", precision = 53, scale = 0)
	public Double getNoafterValue() {
		return this.noafterValue;
	}

	public void setNoafterValue(Double noafterValue) {
		this.noafterValue = noafterValue;
	}

	@Column(name = "HCStandardValue", precision = 53, scale = 0)
	public Double getHcstandardValue() {
		return this.hcstandardValue;
	}

	public void setHcstandardValue(Double hcstandardValue) {
		this.hcstandardValue = hcstandardValue;
	}

	@Column(name = "HCAfterValue", precision = 53, scale = 0)
	public Double getHcafterValue() {
		return this.hcafterValue;
	}

	public void setHcafterValue(Double hcafterValue) {
		this.hcafterValue = hcafterValue;
	}

	@Column(name = "COStandardValue", precision = 53, scale = 0)
	public Double getCostandardValue() {
		return this.costandardValue;
	}

	public void setCostandardValue(Double costandardValue) {
		this.costandardValue = costandardValue;
	}

	@Column(name = "COAfterValue", precision = 53, scale = 0)
	public Double getCoafterValue() {
		return this.coafterValue;
	}

	public void setCoafterValue(Double coafterValue) {
		this.coafterValue = coafterValue;
	}

	@Column(name = "CO2StandardValue", precision = 53, scale = 0)
	public Double getCo2standardValue() {
		return this.co2standardValue;
	}

	public void setCo2standardValue(Double co2standardValue) {
		this.co2standardValue = co2standardValue;
	}

	@Column(name = "CO2AfterValue", precision = 53, scale = 0)
	public Double getCo2afterValue() {
		return this.co2afterValue;
	}

	public void setCo2afterValue(Double co2afterValue) {
		this.co2afterValue = co2afterValue;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}