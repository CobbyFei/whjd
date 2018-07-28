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
 * SteadyStateMethod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "steadyStateMethod", schema = "dbo", catalog = "whjd")
public class SteadyStateMethod implements java.io.Serializable {

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
	private String chassisDynamometer;
	private String exhaustGasAnalyser;
	private Double temperature;
	private Double airPressure;
	private Double relativeHumidity;
	private Double wtHcAsm5025;
	private Double wtHcAsm2540;
	private Double wtCoAsm5025;
	private Double wtCoAsm2540;
	private Double wtNoAsm5025;
	private Double wtNoAsm2540;
	private Double wtHcAsm5025Limit;
	private Double wtHcAsm2540Limit;
	private Double wtCoAsm5025Limit;
	private Double wtCoAsm2540Limit;
	private Double wtNoAsm5025Limit;
	private Double wtNoAsm2540Limit;
	private Boolean wtHcAsm5025Conclusion;
	private Boolean wtHcAsm2540Conclusion;
	private Boolean wtCoAsm5025Conclusion;
	private Boolean wtCoAsm2540Conclusion;
	private Boolean wtNoAsm5025Conclusion;
	private Boolean wtNoAsm2540Conclusion;
	private Boolean wtHcAsm5025Judge;
	private Boolean wtHcAsm2540Judge;
	private Boolean wtCoAsm5025Judge;
	private Boolean wtCoAsm2540Judge;
	private Boolean wtNoAsm5025Judge;
	private Boolean wtNoAsm2540Judge;
	private Integer reportStatus;
	private Integer wheelNums;
	private Set<DetectionCommisionSheet> detectionCommisionSheets = new HashSet<DetectionCommisionSheet>(0);
	
	
	
	private Integer isDetResultPush;
	private Integer isDetProcessPush;
	// Constructors

	/** default constructor */
	public SteadyStateMethod() {
	}

	/** full constructor */
	public SteadyStateMethod(SysUser sysUserByInspecDriverId, DetectionLine detectionLine, SysUser sysUserByInspecOperatorId, String vehicleManufacturer, String engineManufacturer, String fuelSpecification, Double singleAxleLoad, String chassisModel, String driveMode, Double tirePressure, String transmissionForm, Integer gearNumber, Integer cylinderNumber, String catalyticConverter, String deviceAuthNumber, String deviceName, String deviceModel, String deviceManufacturer, String chassisDynamometer, String exhaustGasAnalyser, Double temperature, Double airPressure, Double relativeHumidity, Double wtHcAsm5025, Double wtHcAsm2540, Double wtCoAsm5025, Double wtCoAsm2540, Double wtNoAsm5025, Double wtNoAsm2540, Double wtHcAsm5025Limit, Double wtHcAsm2540Limit, Double wtCoAsm5025Limit,
			Double wtCoAsm2540Limit, Double wtNoAsm5025Limit, Double wtNoAsm2540Limit, Boolean wtHcAsm5025Conclusion, Boolean wtHcAsm2540Conclusion, Boolean wtCoAsm5025Conclusion, Boolean wtCoAsm2540Conclusion, Boolean wtNoAsm5025Conclusion, Boolean wtNoAsm2540Conclusion, Boolean wtHcAsm5025Judge, Boolean wtHcAsm2540Judge, Boolean wtCoAsm5025Judge, Boolean wtCoAsm2540Judge, Boolean wtNoAsm5025Judge, Boolean wtNoAsm2540Judge, Integer reportStatus, Integer wheelNums, Set<DetectionCommisionSheet> detectionCommisionSheets,
			Integer isDetResultPush, Integer isDetProcessPush) {
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
		this.chassisDynamometer = chassisDynamometer;
		this.exhaustGasAnalyser = exhaustGasAnalyser;
		this.temperature = temperature;
		this.airPressure = airPressure;
		this.relativeHumidity = relativeHumidity;
		this.wtHcAsm5025 = wtHcAsm5025;
		this.wtHcAsm2540 = wtHcAsm2540;
		this.wtCoAsm5025 = wtCoAsm5025;
		this.wtCoAsm2540 = wtCoAsm2540;
		this.wtNoAsm5025 = wtNoAsm5025;
		this.wtNoAsm2540 = wtNoAsm2540;
		this.wtHcAsm5025Limit = wtHcAsm5025Limit;
		this.wtHcAsm2540Limit = wtHcAsm2540Limit;
		this.wtCoAsm5025Limit = wtCoAsm5025Limit;
		this.wtCoAsm2540Limit = wtCoAsm2540Limit;
		this.wtNoAsm5025Limit = wtNoAsm5025Limit;
		this.wtNoAsm2540Limit = wtNoAsm2540Limit;
		this.wtHcAsm5025Conclusion = wtHcAsm5025Conclusion;
		this.wtHcAsm2540Conclusion = wtHcAsm2540Conclusion;
		this.wtCoAsm5025Conclusion = wtCoAsm5025Conclusion;
		this.wtCoAsm2540Conclusion = wtCoAsm2540Conclusion;
		this.wtNoAsm5025Conclusion = wtNoAsm5025Conclusion;
		this.wtNoAsm2540Conclusion = wtNoAsm2540Conclusion;
		this.wtHcAsm5025Judge = wtHcAsm5025Judge;
		this.wtHcAsm2540Judge = wtHcAsm2540Judge;
		this.wtCoAsm5025Judge = wtCoAsm5025Judge;
		this.wtCoAsm2540Judge = wtCoAsm2540Judge;
		this.wtNoAsm5025Judge = wtNoAsm5025Judge;
		this.wtNoAsm2540Judge = wtNoAsm2540Judge;
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

	@Column(name = "vehicleManufacturer", length = 50)
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

	@Column(name = "chassisDynamometer", length = 30)
	public String getChassisDynamometer() {
		return this.chassisDynamometer;
	}

	public void setChassisDynamometer(String chassisDynamometer) {
		this.chassisDynamometer = chassisDynamometer;
	}

	@Column(name = "exhaustGasAnalyser", length = 30)
	public String getExhaustGasAnalyser() {
		return this.exhaustGasAnalyser;
	}

	public void setExhaustGasAnalyser(String exhaustGasAnalyser) {
		this.exhaustGasAnalyser = exhaustGasAnalyser;
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

	@Column(name = "WT_HC_ASM5025", precision = 53, scale = 0)
	public Double getWtHcAsm5025() {
		return this.wtHcAsm5025;
	}

	public void setWtHcAsm5025(Double wtHcAsm5025) {
		this.wtHcAsm5025 = wtHcAsm5025;
	}

	@Column(name = "WT_HC_ASM2540", precision = 53, scale = 0)
	public Double getWtHcAsm2540() {
		return this.wtHcAsm2540;
	}

	public void setWtHcAsm2540(Double wtHcAsm2540) {
		this.wtHcAsm2540 = wtHcAsm2540;
	}

	@Column(name = "WT_CO_ASM5025", precision = 53, scale = 0)
	public Double getWtCoAsm5025() {
		return this.wtCoAsm5025;
	}

	public void setWtCoAsm5025(Double wtCoAsm5025) {
		this.wtCoAsm5025 = wtCoAsm5025;
	}

	@Column(name = "WT_CO_ASM2540", precision = 53, scale = 0)
	public Double getWtCoAsm2540() {
		return this.wtCoAsm2540;
	}

	public void setWtCoAsm2540(Double wtCoAsm2540) {
		this.wtCoAsm2540 = wtCoAsm2540;
	}

	@Column(name = "WT_NO_ASM5025", precision = 53, scale = 0)
	public Double getWtNoAsm5025() {
		return this.wtNoAsm5025;
	}

	public void setWtNoAsm5025(Double wtNoAsm5025) {
		this.wtNoAsm5025 = wtNoAsm5025;
	}

	@Column(name = "WT_NO_ASM2540", precision = 53, scale = 0)
	public Double getWtNoAsm2540() {
		return this.wtNoAsm2540;
	}

	public void setWtNoAsm2540(Double wtNoAsm2540) {
		this.wtNoAsm2540 = wtNoAsm2540;
	}

	@Column(name = "WT_HC_ASM5025_Limit", precision = 53, scale = 0)
	public Double getWtHcAsm5025Limit() {
		return this.wtHcAsm5025Limit;
	}

	public void setWtHcAsm5025Limit(Double wtHcAsm5025Limit) {
		this.wtHcAsm5025Limit = wtHcAsm5025Limit;
	}

	@Column(name = "WT_HC_ASM2540_Limit", precision = 53, scale = 0)
	public Double getWtHcAsm2540Limit() {
		return this.wtHcAsm2540Limit;
	}

	public void setWtHcAsm2540Limit(Double wtHcAsm2540Limit) {
		this.wtHcAsm2540Limit = wtHcAsm2540Limit;
	}

	@Column(name = "WT_CO_ASM5025_Limit", precision = 53, scale = 0)
	public Double getWtCoAsm5025Limit() {
		return this.wtCoAsm5025Limit;
	}

	public void setWtCoAsm5025Limit(Double wtCoAsm5025Limit) {
		this.wtCoAsm5025Limit = wtCoAsm5025Limit;
	}

	@Column(name = "WT_CO_ASM2540_Limit", precision = 53, scale = 0)
	public Double getWtCoAsm2540Limit() {
		return this.wtCoAsm2540Limit;
	}

	public void setWtCoAsm2540Limit(Double wtCoAsm2540Limit) {
		this.wtCoAsm2540Limit = wtCoAsm2540Limit;
	}

	@Column(name = "WT_NO_ASM5025_Limit", precision = 53, scale = 0)
	public Double getWtNoAsm5025Limit() {
		return this.wtNoAsm5025Limit;
	}

	public void setWtNoAsm5025Limit(Double wtNoAsm5025Limit) {
		this.wtNoAsm5025Limit = wtNoAsm5025Limit;
	}

	@Column(name = "WT_NO_ASM2540_Limit", precision = 53, scale = 0)
	public Double getWtNoAsm2540Limit() {
		return this.wtNoAsm2540Limit;
	}

	public void setWtNoAsm2540Limit(Double wtNoAsm2540Limit) {
		this.wtNoAsm2540Limit = wtNoAsm2540Limit;
	}

	@Column(name = "WT_HC_ASM5025_Conclusion")
	public Boolean getWtHcAsm5025Conclusion() {
		return this.wtHcAsm5025Conclusion;
	}

	public void setWtHcAsm5025Conclusion(Boolean wtHcAsm5025Conclusion) {
		this.wtHcAsm5025Conclusion = wtHcAsm5025Conclusion;
	}

	@Column(name = "WT_HC_ASM2540_Conclusion")
	public Boolean getWtHcAsm2540Conclusion() {
		return this.wtHcAsm2540Conclusion;
	}

	public void setWtHcAsm2540Conclusion(Boolean wtHcAsm2540Conclusion) {
		this.wtHcAsm2540Conclusion = wtHcAsm2540Conclusion;
	}

	@Column(name = "WT_CO_ASM5025_Conclusion")
	public Boolean getWtCoAsm5025Conclusion() {
		return this.wtCoAsm5025Conclusion;
	}

	public void setWtCoAsm5025Conclusion(Boolean wtCoAsm5025Conclusion) {
		this.wtCoAsm5025Conclusion = wtCoAsm5025Conclusion;
	}

	@Column(name = "WT_CO_ASM2540_Conclusion")
	public Boolean getWtCoAsm2540Conclusion() {
		return this.wtCoAsm2540Conclusion;
	}

	public void setWtCoAsm2540Conclusion(Boolean wtCoAsm2540Conclusion) {
		this.wtCoAsm2540Conclusion = wtCoAsm2540Conclusion;
	}

	@Column(name = "WT_NO_ASM5025_Conclusion")
	public Boolean getWtNoAsm5025Conclusion() {
		return this.wtNoAsm5025Conclusion;
	}

	public void setWtNoAsm5025Conclusion(Boolean wtNoAsm5025Conclusion) {
		this.wtNoAsm5025Conclusion = wtNoAsm5025Conclusion;
	}

	@Column(name = "WT_NO_ASM2540_Conclusion")
	public Boolean getWtNoAsm2540Conclusion() {
		return this.wtNoAsm2540Conclusion;
	}

	public void setWtNoAsm2540Conclusion(Boolean wtNoAsm2540Conclusion) {
		this.wtNoAsm2540Conclusion = wtNoAsm2540Conclusion;
	}

	@Column(name = "WT_HC_ASM5025_Judge")
	public Boolean getWtHcAsm5025Judge() {
		return this.wtHcAsm5025Judge;
	}

	public void setWtHcAsm5025Judge(Boolean wtHcAsm5025Judge) {
		this.wtHcAsm5025Judge = wtHcAsm5025Judge;
	}

	@Column(name = "WT_HC_ASM2540_Judge")
	public Boolean getWtHcAsm2540Judge() {
		return this.wtHcAsm2540Judge;
	}

	public void setWtHcAsm2540Judge(Boolean wtHcAsm2540Judge) {
		this.wtHcAsm2540Judge = wtHcAsm2540Judge;
	}

	@Column(name = "WT_CO_ASM5025_Judge")
	public Boolean getWtCoAsm5025Judge() {
		return this.wtCoAsm5025Judge;
	}

	public void setWtCoAsm5025Judge(Boolean wtCoAsm5025Judge) {
		this.wtCoAsm5025Judge = wtCoAsm5025Judge;
	}

	@Column(name = "WT_CO_ASM2540_Judge")
	public Boolean getWtCoAsm2540Judge() {
		return this.wtCoAsm2540Judge;
	}

	public void setWtCoAsm2540Judge(Boolean wtCoAsm2540Judge) {
		this.wtCoAsm2540Judge = wtCoAsm2540Judge;
	}

	@Column(name = "WT_NO_ASM5025_Judge")
	public Boolean getWtNoAsm5025Judge() {
		return this.wtNoAsm5025Judge;
	}

	public void setWtNoAsm5025Judge(Boolean wtNoAsm5025Judge) {
		this.wtNoAsm5025Judge = wtNoAsm5025Judge;
	}

	@Column(name = "WT_NO_ASM2540_Judge")
	public Boolean getWtNoAsm2540Judge() {
		return this.wtNoAsm2540Judge;
	}

	public void setWtNoAsm2540Judge(Boolean wtNoAsm2540Judge) {
		this.wtNoAsm2540Judge = wtNoAsm2540Judge;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "steadyStateMethod")
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