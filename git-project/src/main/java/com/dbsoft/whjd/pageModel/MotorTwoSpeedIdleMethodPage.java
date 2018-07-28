package com.dbsoft.whjd.pageModel;
public class MotorTwoSpeedIdleMethodPage {
	/*
	 * 委托单属性
	 */
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
	private String  commisionSheetStatus;////
	private Integer emissionStandard;
	private String reportNumber;
	private String  conclusion;//结论
	/*
	 * 检测数据表属性
	 */
	private Integer recordId;
	private Integer sysUserId;//检测员ID
	private String  sysUserName;//检测员姓名
	private Integer detectionLineId;//检测线ID
	private String  detectionLineName;
	private String vehicleManufacturer;
	private Integer strokes;
	private Double maxRpm;
	private Double idleSpeedRpm;
	private Double highIdleSpeedRpm;
	private String fuelSpecification;
	private String lubeSpecification;
	private String fuelSupplyStyle;
	private String fuelJetSystem;
	private String pollutionControlDevice;
	private String exhaustAnalyzerModel;
	private String tachometerModel;
	private Double airPressure;
	private Double temperature;
	private Double humidity;
	private Integer wheelNums;
	private String experimentAddress;
	private String experimentTime;//试验日期
	private String licenseColor;
	//测量结果
	private Double HCoresult;
	private Double HCo2result;
	private Double HHcresult;
	private Double coresult;
	private Double co2result;
	private Double hcresult;
	//修正
	private Double HCoreviseResult;
	private Double coreviseResult;
	//修约
	private Double HCoroundResult;
	private Double HCo2roundResult;
	private Double HHcroundResult;
	private Double coroundResult;
	private Double co2roundResult;
	private Double hcroundResult;
	
	private Integer reportStatus;
	
	private Double colimit;
	private Double hclimit;
	private Double HColimit;
	private Double HHclimit;
	//搜索条件
	private String  inspectionStationId;//核发点ID
    private String beforeDetectionTime;
    private String afterDetectionTime;
    private String beginRegisterDate;
    private String endRegisterDate;
	/*
	 * 分页属性
	 */
	private String ids;
	private int page;
	private int rows;
	private String phoneNum;
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
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getInspectionStationId() {
		return inspectionStationId;
	}
	public void setInspectionStationId(String  inspectionStationId) {
		this.inspectionStationId = inspectionStationId;
	}
	
	public String getvehicleRegisterDate() {
		return vehicleRegisterDate;
	}
	public void setvehicleRegisterDate(String vehicleRegisterDate) {
		this.vehicleRegisterDate = vehicleRegisterDate;
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getVehicleOwnerName() {
		return vehicleOwnerName;
	}
	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}
	public String getVehicleOwnerAddress() {
		return vehicleOwnerAddress;
	}
	public void setVehicleOwnerAddress(String vehicleOwnerAddress) {
		this.vehicleOwnerAddress = vehicleOwnerAddress;
	}
	public String getDetectionTime() {
		return detectionTime;
	}
	public void setDetectionTime(String detectionTime) {
		this.detectionTime = detectionTime;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEngineCode() {
		return engineCode;
	}
	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}
	public String getVehicleModelCode() {
		return vehicleModelCode;
	}
	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}
	public String getEngineModel() {
		return engineModel;
	}
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Double getBaseQuality() {
		return baseQuality;
	}
	public void setBaseQuality(Double baseQuality) {
		this.baseQuality = baseQuality;
	}
	public Double getMaxTotalQuality() {
		return maxTotalQuality;
	}
	public void setMaxTotalQuality(Double maxTotalQuality) {
		this.maxTotalQuality = maxTotalQuality;
	}
	public Double getVehicleLength() {
		return vehicleLength;
	}
	public void setVehicleLength(Double vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public Double getEngineemissionAmount() {
		return engineemissionAmount;
	}
	public void setEngineemissionAmount(Double engineemissionAmount) {
		this.engineemissionAmount = engineemissionAmount;
	}
	public Double getTotalMiles() {
		return totalMiles;
	}
	public void setTotalMiles(Double totalMiles) {
		this.totalMiles = totalMiles;
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
	public Integer getLabelDistributeType() {
		return labelDistributeType;
	}
	public void setLabelDistributeType(Integer labelDistributeType) {
		this.labelDistributeType = labelDistributeType;
	}
	public Integer getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
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
	
	public String getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	public Integer getDetectionLineId() {
		return detectionLineId;
	}
	public void setDetectionLineId(Integer detectionLineId) {
		this.detectionLineId = detectionLineId;
	}
	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}
	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}
	public Integer getStrokes() {
		return strokes;
	}
	public void setStrokes(Integer strokes) {
		this.strokes = strokes;
	}
	public Double getMaxRpm() {
		return maxRpm;
	}
	public void setMaxRpm(Double maxRpm) {
		this.maxRpm = maxRpm;
	}
	public Double getIdleSpeedRpm() {
		return idleSpeedRpm;
	}
	public void setIdleSpeedRpm(Double idleSpeedRpm) {
		this.idleSpeedRpm = idleSpeedRpm;
	}
	public Double getHighIdleSpeedRpm() {
		return highIdleSpeedRpm;
	}
	public void setHighIdleSpeedRpm(Double highIdleSpeedRpm) {
		this.highIdleSpeedRpm = highIdleSpeedRpm;
	}
	public String getFuelSpecification() {
		return fuelSpecification;
	}
	public void setFuelSpecification(String fuelSpecification) {
		this.fuelSpecification = fuelSpecification;
	}
	public String getLubeSpecification() {
		return lubeSpecification;
	}
	public void setLubeSpecification(String lubeSpecification) {
		this.lubeSpecification = lubeSpecification;
	}
	public String getFuelSupplyStyle() {
		return fuelSupplyStyle;
	}
	public void setFuelSupplyStyle(String fuelSupplyStyle) {
		this.fuelSupplyStyle = fuelSupplyStyle;
	}
	public String getFuelJetSystem() {
		return fuelJetSystem;
	}
	public void setFuelJetSystem(String fuelJetSystem) {
		this.fuelJetSystem = fuelJetSystem;
	}
	public String getPollutionControlDevice() {
		return pollutionControlDevice;
	}
	public void setPollutionControlDevice(String pollutionControlDevice) {
		this.pollutionControlDevice = pollutionControlDevice;
	}
	public String getExhaustAnalyzerModel() {
		return exhaustAnalyzerModel;
	}
	public void setExhaustAnalyzerModel(String exhaustAnalyzerModel) {
		this.exhaustAnalyzerModel = exhaustAnalyzerModel;
	}
	public String getTachometerModel() {
		return tachometerModel;
	}
	public void setTachometerModel(String tachometerModel) {
		this.tachometerModel = tachometerModel;
	}
	public Double getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public String getExperimentAddress() {
		return experimentAddress;
	}
	public void setExperimentAddress(String experimentAddress) {
		this.experimentAddress = experimentAddress;
	}
	public String getExperimentTime() {
		return experimentTime;
	}
	public void setExperimentTime(String experimentTime) {
		this.experimentTime = experimentTime;
	}
	public Double getHCoresult() {
		return HCoresult;
	}
	public void setHCoresult(Double hCoresult) {
		HCoresult = hCoresult;
	}
	public Double getHCo2result() {
		return HCo2result;
	}
	public void setHCo2result(Double hCo2result) {
		HCo2result = hCo2result;
	}
	public Double getHHcresult() {
		return HHcresult;
	}
	public void setHHcresult(Double hHcresult) {
		HHcresult = hHcresult;
	}
	public Double getCoresult() {
		return coresult;
	}
	public void setCoresult(Double coresult) {
		this.coresult = coresult;
	}
	public Double getCo2result() {
		return co2result;
	}
	public void setCo2result(Double co2result) {
		this.co2result = co2result;
	}
	public Double getHcresult() {
		return hcresult;
	}
	public void setHcresult(Double hcresult) {
		this.hcresult = hcresult;
	}
	public Double getHCoreviseResult() {
		return HCoreviseResult;
	}
	public void setHCoreviseResult(Double hCoreviseResult) {
		HCoreviseResult = hCoreviseResult;
	}

	public Double getHCoroundResult() {
		return HCoroundResult;
	}
	public void setHCoroundResult(Double hCoroundResult) {
		HCoroundResult = hCoroundResult;
	}

	public Double getHHcroundResult() {
		return HHcroundResult;
	}
	public void setHHcroundResult(Double hHcroundResult) {
		HHcroundResult = hHcroundResult;
	}
	public Double getCoreviseResult() {
		return coreviseResult;
	}
	public void setCoreviseResult(Double coreviseResult) {
		this.coreviseResult = coreviseResult;
	}

	public Double getHCo2roundResult() {
		return HCo2roundResult;
	}
	public void setHCo2roundResult(Double hCo2roundResult) {
		HCo2roundResult = hCo2roundResult;
	}
	public Double getCoroundResult() {
		return coroundResult;
	}
	public void setCoroundResult(Double coroundResult) {
		this.coroundResult = coroundResult;
	}
	public Double getCo2roundResult() {
		return co2roundResult;
	}
	public void setCo2roundResult(Double co2roundResult) {
		this.co2roundResult = co2roundResult;
	}
	public Double getHcroundResult() {
		return hcroundResult;
	}
	public void setHcroundResult(Double hcroundResult) {
		this.hcroundResult = hcroundResult;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}
	public Double getColimit() {
		return colimit;
	}
	public void setColimit(Double colimit) {
		this.colimit = colimit;
	}
	public Double getHclimit() {
		return hclimit;
	}
	public void setHclimit(Double hclimit) {
		this.hclimit = hclimit;
	}
	public Double getHColimit() {
		return HColimit;
	}
	public void setHColimit(Double hColimit) {
		HColimit = hColimit;
	}
	public Double getHHclimit() {
		return HHclimit;
	}
	public void setHHclimit(Double hHclimit) {
		HHclimit = hHclimit;
	}
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
	public Integer getWheelNums() {
		return wheelNums;
	}
	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}
	public String getDetectionLineName() {
		return detectionLineName;
	}
	public void setDetectionLineName(String detectionLineName) {
		this.detectionLineName = detectionLineName;
	}

	public String getLicenseColor() {
		return licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}
	
}
