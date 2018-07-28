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
 * DetectionCommisionSheet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DetectionCommisionSheet", schema = "dbo", catalog = "whjd")
public class DetectionCommisionSheet implements java.io.Serializable {

	// Fields

	private Integer id;
	private LugDownMethod lugDownMethod;
	private SteadyStateMethod steadyStateMethod;
	private TwoSpeedIdleMethod twoSpeedIdleMethod;
	private EnvironmentalLabel environmentalLabel;
	private FreeAccelerationMethod freeAccelerationMethod;
	private MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod;
	private String licence;
	private String vehicleOwnerName;
	private String vehicleOwnerAddress;
	private Timestamp detectionTime;
	private String vin;
	private String engineCode;
	private String vehicleModelCode;
	private String engineModel;
	private String fuelType;
	private Double baseQuality;
	private Double maxTotalQuality;
	private Double vehicleLength;
	private Double engineemissionAmount;
	private Double totalMiles;
	private Timestamp vehicleRegisterDate;
	private Integer vehicleType;
	private Integer labelDistributeType;
	private Integer isCancel;
	private String detectionMethod;
	private Integer yearCount;
	private Integer commisionSheetStatus;
	private Integer emissionStandard;
	private String reportNumber;
	private String stationName;
	private Integer conclusion;
	private Integer requestStatus;
	private Timestamp vechileIssueCertTime;
	private Timestamp validatePeriod;
	private String licenseColor;
	private Integer vehicleLoadNum;
	private String errorReason;
	private String vehicleClass;
	private Integer receiveStatus;
	//车辆信息是否上传
	private Integer isPush;
	private String phoneNum;
	//检测基本信息是否上传
	private Integer isDecBasePush;
	
	
	// Constructors

	/** default constructor */
	public DetectionCommisionSheet() {
	}

	/** full constructor */
	public DetectionCommisionSheet(LugDownMethod lugDownMethod,
			SteadyStateMethod steadyStateMethod,
			TwoSpeedIdleMethod twoSpeedIdleMethod,
			EnvironmentalLabel environmentalLabel,
			FreeAccelerationMethod freeAccelerationMethod,
			MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod, String licence,
			String vehicleOwnerName, String vehicleOwnerAddress,
			Timestamp detectionTime, String vin, String engineCode,
			String vehicleModelCode, String engineModel, String fuelType,
			Double baseQuality, Double maxTotalQuality, Double vehicleLength,
			Double engineemissionAmount, Double totalMiles,
			Timestamp vehicleRegisterDate, Integer vehicleType,
			Integer labelDistributeType, Integer isCancel,
			String detectionMethod, Integer yearCount,
			Integer commisionSheetStatus, Integer emissionStandard,
			String reportNumber, String stationName, Integer conclusion,
			Integer requestStatus, Timestamp vechileIssueCertTime,
			Timestamp validatePeriod, String licenseColor,
			Integer vehicleLoadNum, String errorReason, String vehicleClass,
			Integer receiveStatus,Integer isPush, String phoneNum,
			Integer isDecBasePush) {
		this.lugDownMethod = lugDownMethod;
		this.steadyStateMethod = steadyStateMethod;
		this.twoSpeedIdleMethod = twoSpeedIdleMethod;
		this.environmentalLabel = environmentalLabel;
		this.freeAccelerationMethod = freeAccelerationMethod;
		this.motorTwoSpeedIdleMethod = motorTwoSpeedIdleMethod;
		this.licence = licence;
		this.vehicleOwnerName = vehicleOwnerName;
		this.vehicleOwnerAddress = vehicleOwnerAddress;
		this.detectionTime = detectionTime;
		this.vin = vin;
		this.engineCode = engineCode;
		this.vehicleModelCode = vehicleModelCode;
		this.engineModel = engineModel;
		this.fuelType = fuelType;
		this.baseQuality = baseQuality;
		this.maxTotalQuality = maxTotalQuality;
		this.vehicleLength = vehicleLength;
		this.engineemissionAmount = engineemissionAmount;
		this.totalMiles = totalMiles;
		this.vehicleRegisterDate = vehicleRegisterDate;
		this.vehicleType = vehicleType;
		this.labelDistributeType = labelDistributeType;
		this.isCancel = isCancel;
		this.detectionMethod = detectionMethod;
		this.yearCount = yearCount;
		this.commisionSheetStatus = commisionSheetStatus;
		this.emissionStandard = emissionStandard;
		this.reportNumber = reportNumber;
		this.stationName = stationName;
		this.conclusion = conclusion;
		this.requestStatus = requestStatus;
		this.vechileIssueCertTime = vechileIssueCertTime;
		this.validatePeriod = validatePeriod;
		this.licenseColor = licenseColor;
		this.vehicleLoadNum = vehicleLoadNum;
		this.errorReason = errorReason;
		this.vehicleClass = vehicleClass;
		this.receiveStatus = receiveStatus;
		this.isPush = isPush;
		this.phoneNum = phoneNum;
		this.isDecBasePush = isDecBasePush;
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
	@JoinColumn(name = "lug_ID")
	public LugDownMethod getLugDownMethod() {
		return this.lugDownMethod;
	}

	public void setLugDownMethod(LugDownMethod lugDownMethod) {
		this.lugDownMethod = lugDownMethod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ste_ID")
	public SteadyStateMethod getSteadyStateMethod() {
		return this.steadyStateMethod;
	}

	public void setSteadyStateMethod(SteadyStateMethod steadyStateMethod) {
		this.steadyStateMethod = steadyStateMethod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "two_ID")
	public TwoSpeedIdleMethod getTwoSpeedIdleMethod() {
		return this.twoSpeedIdleMethod;
	}

	public void setTwoSpeedIdleMethod(TwoSpeedIdleMethod twoSpeedIdleMethod) {
		this.twoSpeedIdleMethod = twoSpeedIdleMethod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "env_ID")
	public EnvironmentalLabel getEnvironmentalLabel() {
		return this.environmentalLabel;
	}

	public void setEnvironmentalLabel(EnvironmentalLabel environmentalLabel) {
		this.environmentalLabel = environmentalLabel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "free_ID")
	public FreeAccelerationMethod getFreeAccelerationMethod() {
		return this.freeAccelerationMethod;
	}

	public void setFreeAccelerationMethod(
			FreeAccelerationMethod freeAccelerationMethod) {
		this.freeAccelerationMethod = freeAccelerationMethod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mot_ID")
	public MotorTwoSpeedIdleMethod getMotorTwoSpeedIdleMethod() {
		return this.motorTwoSpeedIdleMethod;
	}

	public void setMotorTwoSpeedIdleMethod(
			MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod) {
		this.motorTwoSpeedIdleMethod = motorTwoSpeedIdleMethod;
	}

	@Column(name = "licence", length = 20)
	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	@Column(name = "vehicleOwnerName", length = 100)
	public String getVehicleOwnerName() {
		return this.vehicleOwnerName;
	}

	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}

	@Column(name = "vehicleOwnerAddress", length = 200)
	public String getVehicleOwnerAddress() {
		return this.vehicleOwnerAddress;
	}

	public void setVehicleOwnerAddress(String vehicleOwnerAddress) {
		this.vehicleOwnerAddress = vehicleOwnerAddress;
	}

	@Column(name = "detectionTime", length = 23)
	public Timestamp getDetectionTime() {
		return this.detectionTime;
	}

	public void setDetectionTime(Timestamp detectionTime) {
		this.detectionTime = detectionTime;
	}

	@Column(name = "vin", length = 30)
	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "engineCode", length = 20)
	public String getEngineCode() {
		return this.engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	@Column(name = "vehicleModelCode", length = 40)
	public String getVehicleModelCode() {
		return this.vehicleModelCode;
	}

	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}

	@Column(name = "engineModel", length = 20)
	public String getEngineModel() {
		return this.engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	@Column(name = "fuelType", length = 15)
	public String getFuelType() {
		return this.fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(name = "baseQuality", precision = 53, scale = 0)
	public Double getBaseQuality() {
		return this.baseQuality;
	}

	public void setBaseQuality(Double baseQuality) {
		this.baseQuality = baseQuality;
	}

	@Column(name = "maxTotalQuality", precision = 53, scale = 0)
	public Double getMaxTotalQuality() {
		return this.maxTotalQuality;
	}

	public void setMaxTotalQuality(Double maxTotalQuality) {
		this.maxTotalQuality = maxTotalQuality;
	}

	@Column(name = "vehicleLength", precision = 53, scale = 0)
	public Double getVehicleLength() {
		return this.vehicleLength;
	}

	public void setVehicleLength(Double vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	@Column(name = "EngineemissionAmount", precision = 53, scale = 0)
	public Double getEngineemissionAmount() {
		return this.engineemissionAmount;
	}

	public void setEngineemissionAmount(Double engineemissionAmount) {
		this.engineemissionAmount = engineemissionAmount;
	}

	@Column(name = "totalMiles", precision = 53, scale = 0)
	public Double getTotalMiles() {
		return this.totalMiles;
	}

	public void setTotalMiles(Double totalMiles) {
		this.totalMiles = totalMiles;
	}

	@Column(name = "vehicleRegisterDate", length = 23)
	public Timestamp getVehicleRegisterDate() {
		return this.vehicleRegisterDate;
	}

	public void setVehicleRegisterDate(Timestamp vehicleRegisterDate) {
		this.vehicleRegisterDate = vehicleRegisterDate;
	}

	@Column(name = "vehicleType")
	public Integer getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "labelDistributeType")
	public Integer getLabelDistributeType() {
		return this.labelDistributeType;
	}

	public void setLabelDistributeType(Integer labelDistributeType) {
		this.labelDistributeType = labelDistributeType;
	}

	@Column(name = "isCancel")
	public Integer getIsCancel() {
		return this.isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	@Column(name = "detectionMethod", length = 25)
	public String getDetectionMethod() {
		return this.detectionMethod;
	}

	public void setDetectionMethod(String detectionMethod) {
		this.detectionMethod = detectionMethod;
	}

	@Column(name = "year_count")
	public Integer getYearCount() {
		return this.yearCount;
	}

	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}

	@Column(name = "commisionSheetStatus")
	public Integer getCommisionSheetStatus() {
		return this.commisionSheetStatus;
	}

	public void setCommisionSheetStatus(Integer commisionSheetStatus) {
		this.commisionSheetStatus = commisionSheetStatus;
	}

	@Column(name = "emissionStandard")
	public Integer getEmissionStandard() {
		return this.emissionStandard;
	}
		
	public void setEmissionStandard(Integer emissionStandard) {
		this.emissionStandard = emissionStandard;
	}

	@Column(name = "reportNumber", length = 20)
	public String getReportNumber() {
		return this.reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	@Column(name = "stationName", length = 100)
	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Column(name = "conclusion")
	public Integer getConclusion() {
		return this.conclusion;
	}

	public void setConclusion(Integer conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "requestStatus")
	public Integer getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Column(name = "vechileIssueCertTime", length = 23)
	public Timestamp getVechileIssueCertTime() {
		return this.vechileIssueCertTime;
	}

	public void setVechileIssueCertTime(Timestamp vechileIssueCertTime) {
		this.vechileIssueCertTime = vechileIssueCertTime;
	}

	@Column(name = "validatePeriod", length = 23)
	public Timestamp getValidatePeriod() {
		return this.validatePeriod;
	}

	public void setValidatePeriod(Timestamp validatePeriod) {
		this.validatePeriod = validatePeriod;
	}

	@Column(name = "licenseColor", length = 10)
	public String getLicenseColor() {
		return this.licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}

	@Column(name = "vehicleLoadNum")
	public Integer getVehicleLoadNum() {
		return this.vehicleLoadNum;
	}

	public void setVehicleLoadNum(Integer vehicleLoadNum) {
		this.vehicleLoadNum = vehicleLoadNum;
	}

	@Column(name = "errorReason", length = 50)
	public String getErrorReason() {
		return this.errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	@Column(name = "vehicleClass", length = 30)
	public String getVehicleClass() {
		return this.vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	@Column(name = "receiveStatus")
	public Integer getReceiveStatus() {
		return this.receiveStatus;
	}

	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	
	@Column(name = "isPush" )
	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	@Column(name = "phoneNum", length = 30)
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Column(name = "isDecBasePush" )
	public Integer getIsDecBasePush() {
		return isDecBasePush;
	}

	public void setIsDecBasePush(Integer isDecBasePush) {
		this.isDecBasePush = isDecBasePush;
	}
	
	
	
	
	
}