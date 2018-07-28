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
 * TwoSpeedIdleMethod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "twoSpeedIdleMethod", schema = "dbo", catalog = "whjd")
public class TwoSpeedIdleMethod implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUserByInspecDriverId;
	private DetectionLine detectionLine;
	private SysUser sysUserByInspecOperatorId;
	private String vehicleManufacturer;
	private String engineManufacturer;
	private String fuelSpecification;
	private Double singleAxleLoad;
	private String chassisModel;
	private String driveMode;
	private Double tirePressure;
	private String transmissionForm;
	private Integer gearNumber;
	private Integer cylinderNumber;
	private String catalyticConverter;
	private String deviceAuthNumber;
	private String deviceName;
	private String deviceModel;
	private String deviceManufacturer;
	private Double temperature;
	private Double airPressure;
	private Double relativeHumidity;
	private Double sdsLambda;
	private Double sdsLCo;
	private Double sdsLHc;
	private Double sdsHCo;
	private Double sdsHHc;
	private Double sdsLambdaLimit;
	private Double sdsLCoLimit;
	private Double sdsLHcLimit;
	private Double sdsHCoLimit;
	private Double sdsHHcLimit;
	private Boolean sdsLambdaConclusion;
	private Boolean sdsLConclusion;
	private Boolean sdsHConclusion;
	private Integer reportStatus;
	private Integer wheelNums;
	private Set<DetectionCommisionSheet> detectionCommisionSheets = new HashSet<DetectionCommisionSheet>(0);

	private Integer isDetResultPush;
	private Integer isDetProcessPush;
	// Constructors

	/** default constructor */
	public TwoSpeedIdleMethod() {
	}

	/** full constructor */
	public TwoSpeedIdleMethod(SysUser sysUserByInspecDriverId, DetectionLine detectionLine, SysUser sysUserByInspecOperatorId, String vehicleManufacturer, String engineManufacturer, String fuelSpecification, Double singleAxleLoad, String chassisModel, String driveMode, Double tirePressure, String transmissionForm, Integer gearNumber, Integer cylinderNumber, String catalyticConverter, String deviceAuthNumber, String deviceName, String deviceModel, String deviceManufacturer, Double temperature, Double airPressure, Double relativeHumidity, Double sdsLambda, Double sdsLCo, Double sdsLHc, Double sdsHCo, Double sdsHHc, Double sdsLambdaLimit, Double sdsLCoLimit, Double sdsLHcLimit, Double sdsHCoLimit, Double sdsHHcLimit, Boolean sdsLambdaConclusion, Boolean sdsLConclusion,
			Boolean sdsHConclusion, Integer reportStatus, Integer wheelNums, Set<DetectionCommisionSheet> detectionCommisionSheets,
			Integer isDetResultPush,Integer isDetProcessPush) {
		this.sysUserByInspecDriverId = sysUserByInspecDriverId;
		this.detectionLine = detectionLine;
		this.sysUserByInspecOperatorId = sysUserByInspecOperatorId;
		this.vehicleManufacturer = vehicleManufacturer;
		this.engineManufacturer = engineManufacturer;
		this.fuelSpecification = fuelSpecification;
		this.singleAxleLoad = singleAxleLoad;
		this.chassisModel = chassisModel;
		this.driveMode = driveMode;
		this.tirePressure = tirePressure;
		this.transmissionForm = transmissionForm;
		this.gearNumber = gearNumber;
		this.cylinderNumber = cylinderNumber;
		this.catalyticConverter = catalyticConverter;
		this.deviceAuthNumber = deviceAuthNumber;
		this.deviceName = deviceName;
		this.deviceModel = deviceModel;
		this.deviceManufacturer = deviceManufacturer;
		this.temperature = temperature;
		this.airPressure = airPressure;
		this.relativeHumidity = relativeHumidity;
		this.sdsLambda = sdsLambda;
		this.sdsLCo = sdsLCo;
		this.sdsLHc = sdsLHc;
		this.sdsHCo = sdsHCo;
		this.sdsHHc = sdsHHc;
		this.sdsLambdaLimit = sdsLambdaLimit;
		this.sdsLCoLimit = sdsLCoLimit;
		this.sdsLHcLimit = sdsLHcLimit;
		this.sdsHCoLimit = sdsHCoLimit;
		this.sdsHHcLimit = sdsHHcLimit;
		this.sdsLambdaConclusion = sdsLambdaConclusion;
		this.sdsLConclusion = sdsLConclusion;
		this.sdsHConclusion = sdsHConclusion;
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
	@JoinColumn(name = "InspecDriverID")
	public SysUser getSysUserByInspecDriverId() {
		return this.sysUserByInspecDriverId;
	}

	public void setSysUserByInspecDriverId(SysUser sysUserByInspecDriverId) {
		this.sysUserByInspecDriverId = sysUserByInspecDriverId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lineID")
	public DetectionLine getDetectionLine() {
		return this.detectionLine;
	}

	public void setDetectionLine(DetectionLine detectionLine) {
		this.detectionLine = detectionLine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InspecOperatorID")
	public SysUser getSysUserByInspecOperatorId() {
		return this.sysUserByInspecOperatorId;
	}

	public void setSysUserByInspecOperatorId(SysUser sysUserByInspecOperatorId) {
		this.sysUserByInspecOperatorId = sysUserByInspecOperatorId;
	}

	@Column(name = "vehicleManufacturer", length = 30)
	public String getVehicleManufacturer() {
		return this.vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	@Column(name = "engineManufacturer", length = 40)
	public String getEngineManufacturer() {
		return this.engineManufacturer;
	}

	public void setEngineManufacturer(String engineManufacturer) {
		this.engineManufacturer = engineManufacturer;
	}

	@Column(name = "fuelSpecification", length = 15)
	public String getFuelSpecification() {
		return this.fuelSpecification;
	}

	public void setFuelSpecification(String fuelSpecification) {
		this.fuelSpecification = fuelSpecification;
	}

	@Column(name = "singleAxleLoad", precision = 53, scale = 0)
	public Double getSingleAxleLoad() {
		return this.singleAxleLoad;
	}

	public void setSingleAxleLoad(Double singleAxleLoad) {
		this.singleAxleLoad = singleAxleLoad;
	}

	@Column(name = "chassisModel", length = 30)
	public String getChassisModel() {
		return this.chassisModel;
	}

	public void setChassisModel(String chassisModel) {
		this.chassisModel = chassisModel;
	}

	@Column(name = "driveMode", length = 40)
	public String getDriveMode() {
		return this.driveMode;
	}

	public void setDriveMode(String driveMode) {
		this.driveMode = driveMode;
	}

	@Column(name = "tirePressure", precision = 53, scale = 0)
	public Double getTirePressure() {
		return this.tirePressure;
	}

	public void setTirePressure(Double tirePressure) {
		this.tirePressure = tirePressure;
	}

	@Column(name = "transmissionForm", length = 30)
	public String getTransmissionForm() {
		return this.transmissionForm;
	}

	public void setTransmissionForm(String transmissionForm) {
		this.transmissionForm = transmissionForm;
	}

	@Column(name = "gearNumber")
	public Integer getGearNumber() {
		return this.gearNumber;
	}

	public void setGearNumber(Integer gearNumber) {
		this.gearNumber = gearNumber;
	}

	@Column(name = "cylinderNumber")
	public Integer getCylinderNumber() {
		return this.cylinderNumber;
	}

	public void setCylinderNumber(Integer cylinderNumber) {
		this.cylinderNumber = cylinderNumber;
	}

	@Column(name = "catalyticConverter", length = 40)
	public String getCatalyticConverter() {
		return this.catalyticConverter;
	}

	public void setCatalyticConverter(String catalyticConverter) {
		this.catalyticConverter = catalyticConverter;
	}

	@Column(name = "deviceAuthNumber", length = 30)
	public String getDeviceAuthNumber() {
		return this.deviceAuthNumber;
	}

	public void setDeviceAuthNumber(String deviceAuthNumber) {
		this.deviceAuthNumber = deviceAuthNumber;
	}

	@Column(name = "deviceName", length = 30)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "deviceModel", length = 30)
	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Column(name = "deviceManufacturer", length = 30)
	public String getDeviceManufacturer() {
		return this.deviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
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

	@Column(name = "SDS_lambda", precision = 53, scale = 0)
	public Double getSdsLambda() {
		return this.sdsLambda;
	}

	public void setSdsLambda(Double sdsLambda) {
		this.sdsLambda = sdsLambda;
	}

	@Column(name = "SDS_L_CO", precision = 53, scale = 0)
	public Double getSdsLCo() {
		return this.sdsLCo;
	}

	public void setSdsLCo(Double sdsLCo) {
		this.sdsLCo = sdsLCo;
	}

	@Column(name = "SDS_L_HC", precision = 53, scale = 0)
	public Double getSdsLHc() {
		return this.sdsLHc;
	}

	public void setSdsLHc(Double sdsLHc) {
		this.sdsLHc = sdsLHc;
	}

	@Column(name = "SDS_H_CO", precision = 53, scale = 0)
	public Double getSdsHCo() {
		return this.sdsHCo;
	}

	public void setSdsHCo(Double sdsHCo) {
		this.sdsHCo = sdsHCo;
	}

	@Column(name = "SDS_H_HC", precision = 53, scale = 0)
	public Double getSdsHHc() {
		return this.sdsHHc;
	}

	public void setSdsHHc(Double sdsHHc) {
		this.sdsHHc = sdsHHc;
	}

	@Column(name = "SDS_lambda_Limit", precision = 53, scale = 0)
	public Double getSdsLambdaLimit() {
		return this.sdsLambdaLimit;
	}

	public void setSdsLambdaLimit(Double sdsLambdaLimit) {
		this.sdsLambdaLimit = sdsLambdaLimit;
	}

	@Column(name = "SDS_L_CO_Limit", precision = 53, scale = 0)
	public Double getSdsLCoLimit() {
		return this.sdsLCoLimit;
	}

	public void setSdsLCoLimit(Double sdsLCoLimit) {
		this.sdsLCoLimit = sdsLCoLimit;
	}

	@Column(name = "SDS_L_HC_Limit", precision = 53, scale = 0)
	public Double getSdsLHcLimit() {
		return this.sdsLHcLimit;
	}

	public void setSdsLHcLimit(Double sdsLHcLimit) {
		this.sdsLHcLimit = sdsLHcLimit;
	}

	@Column(name = "SDS_H_CO_Limit", precision = 53, scale = 0)
	public Double getSdsHCoLimit() {
		return this.sdsHCoLimit;
	}

	public void setSdsHCoLimit(Double sdsHCoLimit) {
		this.sdsHCoLimit = sdsHCoLimit;
	}

	@Column(name = "SDS_H_HC_Limit", precision = 53, scale = 0)
	public Double getSdsHHcLimit() {
		return this.sdsHHcLimit;
	}

	public void setSdsHHcLimit(Double sdsHHcLimit) {
		this.sdsHHcLimit = sdsHHcLimit;
	}

	@Column(name = "SDS_lambda_Conclusion")
	public Boolean getSdsLambdaConclusion() {
		return this.sdsLambdaConclusion;
	}

	public void setSdsLambdaConclusion(Boolean sdsLambdaConclusion) {
		this.sdsLambdaConclusion = sdsLambdaConclusion;
	}

	@Column(name = "SDS_L_Conclusion")
	public Boolean getSdsLConclusion() {
		return this.sdsLConclusion;
	}

	public void setSdsLConclusion(Boolean sdsLConclusion) {
		this.sdsLConclusion = sdsLConclusion;
	}

	@Column(name = "SDS_H_Conclusion")
	public Boolean getSdsHConclusion() {
		return this.sdsHConclusion;
	}

	public void setSdsHConclusion(Boolean sdsHConclusion) {
		this.sdsHConclusion = sdsHConclusion;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "twoSpeedIdleMethod")
	public Set<DetectionCommisionSheet> getDetectionCommisionSheets() {
		return this.detectionCommisionSheets;
	}

	public void setDetectionCommisionSheets(Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.detectionCommisionSheets = detectionCommisionSheets;
	}
	@Column(name = "isDetResultPush")
	public Integer getIsDetResultPush() {
		return isDetResultPush;
	}

	public void setIsDetResultPush(Integer isDetResultPush) {
		this.isDetResultPush = isDetResultPush;
	}
	@Column(name = "isDetProcessPush")
	public Integer getIsDetProcessPush() {
		return isDetProcessPush;
	}

	public void setIsDetProcessPush(Integer isDetProcessPush) {
		this.isDetProcessPush = isDetProcessPush;
	}
	
	

}