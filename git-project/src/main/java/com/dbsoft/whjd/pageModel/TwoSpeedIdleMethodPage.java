package com.dbsoft.whjd.pageModel;

public class TwoSpeedIdleMethodPage {
	private String ids;
	private int page;
	private int rows;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/*委托单属性*/
	private Integer id;
	private String licence;
	private String stationName;//********
	private String vehicleOwnerName;
	private String vehicleOwnerAddress;
	private String detectionTime;///
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
	private String  vehicleRegisterDate;///
	private Integer vehicleType;
	private Integer labelDistributeType;
	private Integer isCancel;
	private String detectionMethod;
	private Integer yearCount;
	private String vehicleClass;
	private String  commisionSheetStatus;////
	private Integer emissionStandard;
	private String reportNumber;
	/*数据表属性*/
	private String licenseColor;
	private Integer recordId;//
	private Integer  sysUserByInspecDriverId;
	//private String sysUserByInspecDriverId;
	private String  sysUserByInspecDriverName;
	private Integer  detectionLineId;
	private String detectionLineName;
	private String inspectionStationId;	
	private Integer sysUserByInspecOperatorId;
	private String  sysUserByInspecOperatorName;	
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
	private String sdsLambdaConclusion;//
	private String  sdsLConclusion;//
	private String  sdsHConclusion;//
	private String  conclusion;//
	private Integer reportStatus;
	private String phoneNum;
	private Integer wheelNums;
    private String beginRegisterDate;
	
	private String endRegisterDate;
    private Double maxTotalQualityLowerBound;
	private Double  maxTotalQualityUpperBound;
	private String stationAddress;
	private String remarks;
    private String institutionNum;
	
	
	public String getInstitutionNum() {
		return institutionNum;
	}
	public void setInstitutionNum(String institutionNum) {
		this.institutionNum = institutionNum;
	}
	public String getStationAddress() {
		return stationAddress;
	}
	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Double getMaxTotalQualityLowerBound() {
		return maxTotalQualityLowerBound;
	}

	public void setMaxTotalQualityLowerBound(Double maxTotalQualityLowerBound) {
		this.maxTotalQualityLowerBound = maxTotalQualityLowerBound;
	}

	public Double getMaxTotalQualityUpperBound() {
		return maxTotalQualityUpperBound;
	}

	public void setMaxTotalQualityUpperBound(Double maxTotalQualityUpperBound) {
		this.maxTotalQualityUpperBound = maxTotalQualityUpperBound;
	}

	
	public String getBeginRegisterDate() {
		return beginRegisterDate;
	}

	public void setBeginRegisterDate(String beginRegisterDate) {
		this.beginRegisterDate = beginRegisterDate;
	}

	public String getEndRegisterDate() {
		return endRegisterDate;
	}

	public void setEndRegisterDate(String endRegisterDate) {
		this.endRegisterDate = endRegisterDate;
	}

	

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	//搜索时间
    private String beforeDetectionTime;
    private String afterDetectionTime;
    
    

	public String getInspectionStationId() {
		return inspectionStationId;
	}
	public void setInspectionStationId(String inspectionStationId) {
		this.inspectionStationId = inspectionStationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getBeforeDetectionTime() {
		return beforeDetectionTime;
	}
	public void setBeforeDetectionTime(String beforeDetectionTime) {
		this.beforeDetectionTime = beforeDetectionTime;
	}
	public String getAfterDetectionTime() {
		return afterDetectionTime;
	}
	public void setAfterDetectionTime(String afterDetectionTime) {
		this.afterDetectionTime = afterDetectionTime;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public String getDetectionTime() {
		return detectionTime;
	}
	public void setDetectionTime(String detectionTime) {
		this.detectionTime = detectionTime;
	}
	public Double getVehicleLength() {
		return vehicleLength;
	}
	public void setVehicleLength(Double vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public String getDetectionMethod() {
		return detectionMethod;
	}
	public void setDetectionMethod(String detectionMethod) {
		this.detectionMethod = detectionMethod;
	}
	public Integer getYearCount() {
		return yearCount;
	}
	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}

	public String getCommisionSheetStatus() {
		return commisionSheetStatus;
	}
	public void setCommisionSheetStatus(String commisionSheetStatus) {
		this.commisionSheetStatus = commisionSheetStatus;
	}
	public Integer getEmissionStandard() {
		return emissionStandard;
	}
	public void setEmissionStandard(Integer emissionStandard) {
		this.emissionStandard = emissionStandard;
	}
	public void setBaseQuality(Double baseQuality) {
		this.baseQuality = baseQuality;
	}
	public void setMaxTotalQuality(Double maxTotalQuality) {
		this.maxTotalQuality = maxTotalQuality;
	}
	public void setTotalMiles(Double totalMiles) {
		this.totalMiles = totalMiles;
	}
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}
	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}
	public String getEngineManufacturer() {
		return engineManufacturer;
	}
	public void setEngineManufacturer(String engineManufacturer) {
		this.engineManufacturer = engineManufacturer;
	}
	public String getFuelSpecification() {
		return fuelSpecification;
	}
	public void setFuelSpecification(String fuelSpecification) {
		this.fuelSpecification = fuelSpecification;
	}
	public Double getSingleAxleLoad() {
		return singleAxleLoad;
	}
	public void setSingleAxleLoad(Double singleAxleLoad) {
		this.singleAxleLoad = singleAxleLoad;
	}
	public String getChassisModel() {
		return chassisModel;
	}
	public void setChassisModel(String chassisModel) {
		this.chassisModel = chassisModel;
	}
	public String getDriveMode() {
		return driveMode;
	}
	public void setDriveMode(String driveMode) {
		this.driveMode = driveMode;
	}
	public Double getTirePressure() {
		return tirePressure;
	}
	public void setTirePressure(Double tirePressure) {
		this.tirePressure = tirePressure;
	}
	public String getTransmissionForm() {
		return transmissionForm;
	}
	public void setTransmissionForm(String transmissionForm) {
		this.transmissionForm = transmissionForm;
	}
	public Integer getGearNumber() {
		return gearNumber;
	}
	public void setGearNumber(Integer gearNumber) {
		this.gearNumber = gearNumber;
	}
	public Integer getCylinderNumber() {
		return cylinderNumber;
	}
	public void setCylinderNumber(Integer cylinderNumber) {
		this.cylinderNumber = cylinderNumber;
	}
	public String getCatalyticConverter() {
		return catalyticConverter;
	}
	public void setCatalyticConverter(String catalyticConverter) {
		this.catalyticConverter = catalyticConverter;
	}
	public String getDeviceAuthNumber() {
		return deviceAuthNumber;
	}
	public void setDeviceAuthNumber(String deviceAuthNumber) {
		this.deviceAuthNumber = deviceAuthNumber;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}
	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}
	public Double getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(Double relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public Double getSdsLambda() {
		return sdsLambda;
	}
	public void setSdsLambda(Double sdsLambda) {
		this.sdsLambda = sdsLambda;
	}
	public Double getSdsLCo() {
		return sdsLCo;
	}
	public void setSdsLCo(Double sdsLCo) {
		this.sdsLCo = sdsLCo;
	}
	public Double getSdsLHc() {
		return sdsLHc;
	}
	public void setSdsLHc(Double sdsLHc) {
		this.sdsLHc = sdsLHc;
	}
	public Double getSdsHCo() {
		return sdsHCo;
	}
	public void setSdsHCo(Double sdsHCo) {
		this.sdsHCo = sdsHCo;
	}
	public Double getSdsHHc() {
		return sdsHHc;
	}
	public void setSdsHHc(Double sdsHHc) {
		this.sdsHHc = sdsHHc;
	}
	public Double getSdsLambdaLimit() {
		return sdsLambdaLimit;
	}
	public void setSdsLambdaLimit(Double sdsLambdaLimit) {
		this.sdsLambdaLimit = sdsLambdaLimit;
	}
	public Double getSdsLCoLimit() {
		return sdsLCoLimit;
	}
	public void setSdsLCoLimit(Double sdsLCoLimit) {
		this.sdsLCoLimit = sdsLCoLimit;
	}
	public Double getSdsLHcLimit() {
		return sdsLHcLimit;
	}
	public void setSdsLHcLimit(Double sdsLHcLimit) {
		this.sdsLHcLimit = sdsLHcLimit;
	}
	public Double getSdsHCoLimit() {
		return sdsHCoLimit;
	}
	public void setSdsHCoLimit(Double sdsHCoLimit) {
		this.sdsHCoLimit = sdsHCoLimit;
	}
	public Double getSdsHHcLimit() {
		return sdsHHcLimit;
	}
	public void setSdsHHcLimit(Double sdsHHcLimit) {
		this.sdsHHcLimit = sdsHHcLimit;
	}

	public String getSdsLambdaConclusion() {
		return sdsLambdaConclusion;
	}
	public void setSdsLambdaConclusion(String sdsLambdaConclusion) {
		this.sdsLambdaConclusion = sdsLambdaConclusion;
	}
	public String getSdsLConclusion() {
		return sdsLConclusion;
	}
	public void setSdsLConclusion(String sdsLConclusion) {
		this.sdsLConclusion = sdsLConclusion;
	}
	public String getSdsHConclusion() {
		return sdsHConclusion;
	}
	public void setSdsHConclusion(String sdsHConclusion) {
		this.sdsHConclusion = sdsHConclusion;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getEngineCode() {
		return engineCode;
	}
	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}
	public String getEngineModel() {
		return engineModel;
	}
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	public Double getEngineemissionAmount() {
		return engineemissionAmount;
	}
	public void setEngineemissionAmount(Double engineemissionAmount) {
		this.engineemissionAmount = engineemissionAmount;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Integer getLabelDistributeType() {
		return labelDistributeType;
	}
	public void setLabelDistributeType(Integer labelDistributeType) {
		this.labelDistributeType = labelDistributeType;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getVehicleModelCode() {
		return vehicleModelCode;
	}
	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}
	public String getVehicleOwnerAddress() {
		return vehicleOwnerAddress;
	}
	public void setVehicleOwnerAddress(String vehicleOwnerAddress) {
		this.vehicleOwnerAddress = vehicleOwnerAddress;
	}
	public String getVehicleOwnerName() {
		return vehicleOwnerName;
	}
	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}
	public String getVehicleRegisterDate() {
		return vehicleRegisterDate;
	}
	public void setVehicleRegisterDate(String vehicleRegisterDate) {
		this.vehicleRegisterDate = vehicleRegisterDate;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Double getBaseQuality() {
		return baseQuality;
	}
	public Double getMaxTotalQuality() {
		return maxTotalQuality;
	}
	public Double getTotalMiles() {
		return totalMiles;
	}
	public Integer getIsCancel() {
		return isCancel;
	}
	public Integer getSysUserByInspecDriverId() {
		return sysUserByInspecDriverId;
	}
	public void setSysUserByInspecDriverId(Integer sysUserByInspecDriverId) {
		this.sysUserByInspecDriverId = sysUserByInspecDriverId;
	}

	public Integer getDetectionLineId() {
		return detectionLineId;
	}
	public void setDetectionLineId(Integer detectionLineId) {
		this.detectionLineId = detectionLineId;
	}
	public Integer getSysUserByInspecOperatorId() {
		return sysUserByInspecOperatorId;
	}
	public void setSysUserByInspecOperatorId(Integer sysUserByInspecOperatorId) {
		this.sysUserByInspecOperatorId = sysUserByInspecOperatorId;
	}
	public String getSysUserByInspecDriverName() {
		return sysUserByInspecDriverName;
	}
	public void setSysUserByInspecDriverName(String sysUserByInspecDriverName) {
		this.sysUserByInspecDriverName = sysUserByInspecDriverName;
	}
	public String getSysUserByInspecOperatorName() {
		return sysUserByInspecOperatorName;
	}
	public void setSysUserByInspecOperatorName(String sysUserByInspecOperatorName) {
		this.sysUserByInspecOperatorName = sysUserByInspecOperatorName;
	}
	public String getVehicleClass() {
		return vehicleClass;
	}
	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}
	public String getDetectionLineName() {
		return detectionLineName;
	}
	public void setDetectionLineName(String detectionLineName) {
		this.detectionLineName = detectionLineName;
	}
	public Integer getWheelNums() {
		return wheelNums;
	}
	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}
	public String getLicenseColor() {
		return licenseColor;
	}
	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}

	
}
