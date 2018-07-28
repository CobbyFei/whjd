package com.dbsoft.whjd.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * DetectionLine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "detectionLine", schema = "dbo", catalog = "whjd")
public class DetectionLine implements java.io.Serializable {

	// Fields

	private Integer lineId;
	private InspectionStation inspectionStation;
	private String lineName;
	private Integer localeId;
	private Integer maxDetectionNum;
	private Integer lineStatus;
	private Integer isPush;
	private Set<SteadyStateMethod> steadyStateMethods = new HashSet<SteadyStateMethod>(
			0);
	private Set<CalibrationRecord> calibrationRecords = new HashSet<CalibrationRecord>(
			0);
	private Set<DeviceInfo> deviceInfos = new HashSet<DeviceInfo>(0);
	private Set<TwoSpeedIdleMethod> twoSpeedIdleMethods = new HashSet<TwoSpeedIdleMethod>(
			0);
	private Set<FreeAccelerationMethod> freeAccelerationMethods = new HashSet<FreeAccelerationMethod>(
			0);
	private Set<LugDownMethod> lugDownMethods = new HashSet<LugDownMethod>(0);
	private Set<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethods = new HashSet<MotorTwoSpeedIdleMethod>(
			0);

	// Constructors

	/** default constructor */
	public DetectionLine() {
	}

	/** full constructor */
	public DetectionLine(InspectionStation inspectionStation, String lineName,
			Integer localeId, Integer maxDetectionNum, Integer lineStatus, Integer isPush,
			Set<SteadyStateMethod> steadyStateMethods,
			Set<CalibrationRecord> calibrationRecords,
			Set<DeviceInfo> deviceInfos,
			Set<TwoSpeedIdleMethod> twoSpeedIdleMethods,
			Set<FreeAccelerationMethod> freeAccelerationMethods,
			Set<LugDownMethod> lugDownMethods,
			Set<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethods) {
		this.inspectionStation = inspectionStation;
		this.lineName = lineName;
		this.localeId = localeId;
		this.maxDetectionNum = maxDetectionNum;
		this.lineStatus = lineStatus;
		this.isPush = isPush;
		this.steadyStateMethods = steadyStateMethods;
		this.calibrationRecords = calibrationRecords;
		this.deviceInfos = deviceInfos;
		this.twoSpeedIdleMethods = twoSpeedIdleMethods;
		this.freeAccelerationMethods = freeAccelerationMethods;
		this.lugDownMethods = lugDownMethods;
		this.motorTwoSpeedIdleMethods = motorTwoSpeedIdleMethods;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "lineID", unique = true, nullable = false)
	public Integer getLineId() {
		return this.lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stationID")
	public InspectionStation getInspectionStation() {
		return this.inspectionStation;
	}

	public void setInspectionStation(InspectionStation inspectionStation) {
		this.inspectionStation = inspectionStation;
	}

	@Column(name = "lineName", length = 20)
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "localeID")
	public Integer getLocaleId() {
		return this.localeId;
	}

	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
	}

	@Column(name = "maxDetectionNum")
	public Integer getMaxDetectionNum() {
		return this.maxDetectionNum;
	}

	public void setMaxDetectionNum(Integer maxDetectionNum) {
		this.maxDetectionNum = maxDetectionNum;
	}

	@Column(name = "lineStatus")
	public Integer getLineStatus() {
		return this.lineStatus;
	}
	public void setLineStatus(Integer lineStatus) {
		this.lineStatus = lineStatus;
	}
	
	
	@Column(name = "isPush" , nullable=false , columnDefinition="INT default 0")
	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<SteadyStateMethod> getSteadyStateMethods() {
		return this.steadyStateMethods;
	}

	public void setSteadyStateMethods(Set<SteadyStateMethod> steadyStateMethods) {
		this.steadyStateMethods = steadyStateMethods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<CalibrationRecord> getCalibrationRecords() {
		return this.calibrationRecords;
	}

	public void setCalibrationRecords(Set<CalibrationRecord> calibrationRecords) {
		this.calibrationRecords = calibrationRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<DeviceInfo> getDeviceInfos() {
		return this.deviceInfos;
	}

	public void setDeviceInfos(Set<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<TwoSpeedIdleMethod> getTwoSpeedIdleMethods() {
		return this.twoSpeedIdleMethods;
	}

	public void setTwoSpeedIdleMethods(
			Set<TwoSpeedIdleMethod> twoSpeedIdleMethods) {
		this.twoSpeedIdleMethods = twoSpeedIdleMethods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<FreeAccelerationMethod> getFreeAccelerationMethods() {
		return this.freeAccelerationMethods;
	}

	public void setFreeAccelerationMethods(
			Set<FreeAccelerationMethod> freeAccelerationMethods) {
		this.freeAccelerationMethods = freeAccelerationMethods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<LugDownMethod> getLugDownMethods() {
		return this.lugDownMethods;
	}

	public void setLugDownMethods(Set<LugDownMethod> lugDownMethods) {
		this.lugDownMethods = lugDownMethods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionLine")
	public Set<MotorTwoSpeedIdleMethod> getMotorTwoSpeedIdleMethods() {
		return this.motorTwoSpeedIdleMethods;
	}

	public void setMotorTwoSpeedIdleMethods(
			Set<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethods) {
		this.motorTwoSpeedIdleMethods = motorTwoSpeedIdleMethods;
	}

}