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
 * FreeAccelerationMethod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "freeAccelerationMethod", schema = "dbo", catalog = "whjd")
public class FreeAccelerationMethod implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUserByApproverId;
	private SysUser sysUserByAccessorId;
	private SysUser sysUserByInspectorId;
	private DetectionLine detectionLine;
	private String vehicleManufacturer;
	private String fuelGrade;
	private String airInletMode;
	private String fuelSupplySystemModel;
	private Boolean hasEmissionProcessDevice;
	private Double temperature;
	private Double airPressure;
	private Double relativeHumidity;
	private Double rpm;
	private Double lastOneTest;
	private Double lastTwoTest;
	private Double lastThreeTest;
	private Double average;
	private Double zjLimit;
	private String testDeviceModel;
	private Integer reportStatus;
	private Integer wheelNums;
	private Set<DetectionCommisionSheet> detectionCommisionSheets = new HashSet<DetectionCommisionSheet>(
			0);

	// Constructors

	/** default constructor */
	public FreeAccelerationMethod() {
	}

	/** full constructor */
	public FreeAccelerationMethod(SysUser sysUserByApproverId,
			SysUser sysUserByAccessorId, SysUser sysUserByInspectorId,
			DetectionLine detectionLine, String vehicleManufacturer,
			String fuelGrade, String airInletMode,
			String fuelSupplySystemModel, Boolean hasEmissionProcessDevice,
			Double temperature, Double airPressure, Double relativeHumidity,
			Double rpm, Double lastOneTest, Double lastTwoTest,
			Double lastThreeTest, Double average, Double zjLimit,
			String testDeviceModel, Integer reportStatus, Integer wheelNums,
			Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.sysUserByApproverId = sysUserByApproverId;
		this.sysUserByAccessorId = sysUserByAccessorId;
		this.sysUserByInspectorId = sysUserByInspectorId;
		this.detectionLine = detectionLine;
		this.vehicleManufacturer = vehicleManufacturer;
		this.fuelGrade = fuelGrade;
		this.airInletMode = airInletMode;
		this.fuelSupplySystemModel = fuelSupplySystemModel;
		this.hasEmissionProcessDevice = hasEmissionProcessDevice;
		this.temperature = temperature;
		this.airPressure = airPressure;
		this.relativeHumidity = relativeHumidity;
		this.rpm = rpm;
		this.lastOneTest = lastOneTest;
		this.lastTwoTest = lastTwoTest;
		this.lastThreeTest = lastThreeTest;
		this.average = average;
		this.zjLimit = zjLimit;
		this.testDeviceModel = testDeviceModel;
		this.reportStatus = reportStatus;
		this.wheelNums = wheelNums;
		this.detectionCommisionSheets = detectionCommisionSheets;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ApproverID")
	public SysUser getSysUserByApproverId() {
		return this.sysUserByApproverId;
	}

	public void setSysUserByApproverId(SysUser sysUserByApproverId) {
		this.sysUserByApproverId = sysUserByApproverId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AccessorID")
	public SysUser getSysUserByAccessorId() {
		return this.sysUserByAccessorId;
	}

	public void setSysUserByAccessorId(SysUser sysUserByAccessorId) {
		this.sysUserByAccessorId = sysUserByAccessorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InspectorID")
	public SysUser getSysUserByInspectorId() {
		return this.sysUserByInspectorId;
	}

	public void setSysUserByInspectorId(SysUser sysUserByInspectorId) {
		this.sysUserByInspectorId = sysUserByInspectorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lineID")
	public DetectionLine getDetectionLine() {
		return this.detectionLine;
	}

	public void setDetectionLine(DetectionLine detectionLine) {
		this.detectionLine = detectionLine;
	}

	@Column(name = "vehicleManufacturer", length = 50)
	public String getVehicleManufacturer() {
		return this.vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	@Column(name = "FuelGrade", length = 15)
	public String getFuelGrade() {
		return this.fuelGrade;
	}

	public void setFuelGrade(String fuelGrade) {
		this.fuelGrade = fuelGrade;
	}

	@Column(name = "airInletMode", length = 10)
	public String getAirInletMode() {
		return this.airInletMode;
	}

	public void setAirInletMode(String airInletMode) {
		this.airInletMode = airInletMode;
	}

	@Column(name = "fuelSupplySystemModel", length = 30)
	public String getFuelSupplySystemModel() {
		return this.fuelSupplySystemModel;
	}

	public void setFuelSupplySystemModel(String fuelSupplySystemModel) {
		this.fuelSupplySystemModel = fuelSupplySystemModel;
	}

	@Column(name = "hasEmissionProcessDevice")
	public Boolean getHasEmissionProcessDevice() {
		return this.hasEmissionProcessDevice;
	}

	public void setHasEmissionProcessDevice(Boolean hasEmissionProcessDevice) {
		this.hasEmissionProcessDevice = hasEmissionProcessDevice;
	}

	@Column(name = "temperature", precision = 53, scale = 0)
	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	@Column(name = "airPressure", precision = 53, scale = 0)
	public Double getAirPressure() {
		return this.airPressure;
	}

	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	@Column(name = "relativeHumidity", precision = 53, scale = 0)
	public Double getRelativeHumidity() {
		return this.relativeHumidity;
	}

	public void setRelativeHumidity(Double relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	@Column(name = "rpm", precision = 53, scale = 0)
	public Double getRpm() {
		return this.rpm;
	}

	public void setRpm(Double rpm) {
		this.rpm = rpm;
	}

	@Column(name = "lastOneTest", precision = 53, scale = 0)
	public Double getLastOneTest() {
		return this.lastOneTest;
	}

	public void setLastOneTest(Double lastOneTest) {
		this.lastOneTest = lastOneTest;
	}

	@Column(name = "lastTwoTest", precision = 53, scale = 0)
	public Double getLastTwoTest() {
		return this.lastTwoTest;
	}

	public void setLastTwoTest(Double lastTwoTest) {
		this.lastTwoTest = lastTwoTest;
	}

	@Column(name = "lastThreeTest", precision = 53, scale = 0)
	public Double getLastThreeTest() {
		return this.lastThreeTest;
	}

	public void setLastThreeTest(Double lastThreeTest) {
		this.lastThreeTest = lastThreeTest;
	}

	@Column(name = "average", precision = 53, scale = 0)
	public Double getAverage() {
		return this.average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	@Column(name = "ZJ_Limit", precision = 53, scale = 0)
	public Double getZjLimit() {
		return this.zjLimit;
	}

	public void setZjLimit(Double zjLimit) {
		this.zjLimit = zjLimit;
	}

	@Column(name = "testDeviceModel", length = 20)
	public String getTestDeviceModel() {
		return this.testDeviceModel;
	}

	public void setTestDeviceModel(String testDeviceModel) {
		this.testDeviceModel = testDeviceModel;
	}

	@Column(name = "reportStatus")
	public Integer getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	@Column(name = "wheelNums")
	public Integer getWheelNums() {
		return this.wheelNums;
	}

	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "freeAccelerationMethod")
	public Set<DetectionCommisionSheet> getDetectionCommisionSheets() {
		return this.detectionCommisionSheets;
	}

	public void setDetectionCommisionSheets(
			Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.detectionCommisionSheets = detectionCommisionSheets;
	}

}