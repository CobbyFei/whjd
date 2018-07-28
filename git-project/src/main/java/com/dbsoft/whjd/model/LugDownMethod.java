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
 * LugDownMethod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lugDownMethod", schema = "dbo", catalog = "whjd")
public class LugDownMethod implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUserByApproverId;
	private SysUser sysUserByInspectorId;
	private SysUser sysUserByAccessorId;
	private DetectionLine detectionLine;
	private String vehicleManufacturer;
	private String fuelGrade;
	private String airInletMode;
	private String fuelSupplySystemModel;
	private Boolean emissionProcessDevice;
	private Double temperature;
	private Double airPressure;
	private Double relativeHumidity;
	private Double hundredPoint;
	private Double nintyPoint;
	private Double eightyPoint;
	private Double kmLimit;
	private Double kwLimit;
	private Double kwResult;
	private String testDeviceModel;
	private Integer reportStatus;
	private Integer wheelNums;
	private Set<DetectionCommisionSheet> detectionCommisionSheets = new HashSet<DetectionCommisionSheet>(
			0);
	
	private Integer isDetResultPush;
	private Integer isDetProcessPush;

	// Constructors

	/** default constructor */
	public LugDownMethod() {
	}

	/** full constructor */
	public LugDownMethod(SysUser sysUserByApproverId,
			SysUser sysUserByInspectorId, SysUser sysUserByAccessorId,
			DetectionLine detectionLine, String vehicleManufacturer,
			String fuelGrade, String airInletMode,
			String fuelSupplySystemModel, Boolean emissionProcessDevice,
			Double temperature, Double airPressure, Double relativeHumidity,
			Double hundredPoint, Double nintyPoint, Double eightyPoint,
			Double kmLimit, Double kwLimit, Double kwResult,
			String testDeviceModel, Integer reportStatus, Integer wheelNums,
			Set<DetectionCommisionSheet> detectionCommisionSheets,
			Integer isDetResultPush,Integer isDetProcessPush) {
		this.sysUserByApproverId = sysUserByApproverId;
		this.sysUserByInspectorId = sysUserByInspectorId;
		this.sysUserByAccessorId = sysUserByAccessorId;
		this.detectionLine = detectionLine;
		this.vehicleManufacturer = vehicleManufacturer;
		this.fuelGrade = fuelGrade;
		this.airInletMode = airInletMode;
		this.fuelSupplySystemModel = fuelSupplySystemModel;
		this.emissionProcessDevice = emissionProcessDevice;
		this.temperature = temperature;
		this.airPressure = airPressure;
		this.relativeHumidity = relativeHumidity;
		this.hundredPoint = hundredPoint;
		this.nintyPoint = nintyPoint;
		this.eightyPoint = eightyPoint;
		this.kmLimit = kmLimit;
		this.kwLimit = kwLimit;
		this.kwResult = kwResult;
		this.testDeviceModel = testDeviceModel;
		this.reportStatus = reportStatus;
		this.wheelNums = wheelNums;
		this.detectionCommisionSheets = detectionCommisionSheets;
		this.isDetProcessPush = isDetProcessPush;
		this.isDetResultPush = isDetResultPush;
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
	@JoinColumn(name = "InspectorID")
	public SysUser getSysUserByInspectorId() {
		return this.sysUserByInspectorId;
	}

	public void setSysUserByInspectorId(SysUser sysUserByInspectorId) {
		this.sysUserByInspectorId = sysUserByInspectorId;
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
	@JoinColumn(name = "lineID")
	public DetectionLine getDetectionLine() {
		return this.detectionLine;
	}

	public void setDetectionLine(DetectionLine detectionLine) {
		this.detectionLine = detectionLine;
	}

	@Column(name = "vehicleManufacturer", length = 30)
	public String getVehicleManufacturer() {
		return this.vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	@Column(name = "fuelGrade", length = 15)
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

	@Column(name = "emissionProcessDevice")
	public Boolean getEmissionProcessDevice() {
		return this.emissionProcessDevice;
	}

	public void setEmissionProcessDevice(Boolean emissionProcessDevice) {
		this.emissionProcessDevice = emissionProcessDevice;
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

	@Column(name = "hundredPoint", precision = 53, scale = 0)
	public Double getHundredPoint() {
		return this.hundredPoint;
	}

	public void setHundredPoint(Double hundredPoint) {
		this.hundredPoint = hundredPoint;
	}

	@Column(name = "nintyPoint", precision = 53, scale = 0)
	public Double getNintyPoint() {
		return this.nintyPoint;
	}

	public void setNintyPoint(Double nintyPoint) {
		this.nintyPoint = nintyPoint;
	}

	@Column(name = "eightyPoint", precision = 53, scale = 0)
	public Double getEightyPoint() {
		return this.eightyPoint;
	}

	public void setEightyPoint(Double eightyPoint) {
		this.eightyPoint = eightyPoint;
	}

	@Column(name = "KM_Limit", precision = 53, scale = 0)
	public Double getKmLimit() {
		return this.kmLimit;
	}

	public void setKmLimit(Double kmLimit) {
		this.kmLimit = kmLimit;
	}

	@Column(name = "KW_Limit", precision = 53, scale = 0)
	public Double getKwLimit() {
		return this.kwLimit;
	}

	public void setKwLimit(Double kwLimit) {
		this.kwLimit = kwLimit;
	}

	@Column(name = "KW_Result", precision = 53, scale = 0)
	public Double getKwResult() {
		return this.kwResult;
	}

	public void setKwResult(Double kwResult) {
		this.kwResult = kwResult;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugDownMethod")
	public Set<DetectionCommisionSheet> getDetectionCommisionSheets() {
		return this.detectionCommisionSheets;
	}

	public void setDetectionCommisionSheets(
			Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.detectionCommisionSheets = detectionCommisionSheets;
	}

	public Integer getIsDetResultPush() {
		return isDetResultPush;
	}
	@Column(name = "isDetResultPush")
	public void setIsDetResultPush(Integer isDetResultPush) {
		this.isDetResultPush = isDetResultPush;
	}

	public Integer getIsDetProcessPush() {
		return isDetProcessPush;
	}
	@Column(name = "isDetProcessPush")
	public void setIsDetProcessPush(Integer isDetProcessPush) {
		this.isDetProcessPush = isDetProcessPush;
	}

}