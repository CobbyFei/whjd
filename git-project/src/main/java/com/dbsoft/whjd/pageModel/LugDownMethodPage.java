package com.dbsoft.whjd.pageModel;

public class LugDownMethodPage {
	// 加载减速法固有属性，由于委托单号也为id，故检测记录号为methodId
	private Integer methodId;
	private Integer approverId;
	private String approverName;
	private Integer inspectorId;
	private String inspectorName;
	private Integer accessorId;
	private String accessorName;
	private Integer lineId;
	private String lineName;
	private String vehicleManufacturer;
	private String fuelGrade;
	private String airInletMode;
	private String fuelSupplySystemModel;
	private Integer intEmissionProcessDevice;
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
	private String licenseColor;
	// 委托单属性
	private Integer id;
	private String licence;
	private String vehicleOwnerName;
	private String vehicleOwnerAddress;
	private String strDetectionTime;
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
	private String strVehicleRegisterDate;
	private Integer vehicleType;
	private Integer labelDistributeType;
	private Integer isCancel;
	private String detectionMethod;
	private Integer yearCount;
	private Integer commisionSheetStatus;
	private Integer emissionStandard;
	private String stationName;
	private String reportNumber;
	private Integer conclusion;
	// 附加属性
	private Integer page;
	private Integer rows;
	private String q;
	private String beginTime;
	private String endTime;
	private String beginRegisterDate;
	private String endRegisterDate;
	private Double maxTotalQualityLowerBound;
	private Double maxTotalQualityUpperBound;
	private String ids;
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

	public Integer getWheelNums() {
		return wheelNums;
	}

	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getConclusion() {
		return conclusion;
	}

	public void setConclusion(Integer conclusion) {
		this.conclusion = conclusion;
	}

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Integer getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(Integer inspectorId) {
		this.inspectorId = inspectorId;
	}

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

	public Integer getAccessorId() {
		return accessorId;
	}

	public void setAccessorId(Integer accessorId) {
		this.accessorId = accessorId;
	}

	public String getAccessorName() {
		return accessorName;
	}

	public void setAccessorName(String accessorName) {
		this.accessorName = accessorName;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	public String getFuelGrade() {
		return fuelGrade;
	}

	public void setFuelGrade(String fuelGrade) {
		this.fuelGrade = fuelGrade;
	}

	public String getAirInletMode() {
		return airInletMode;
	}

	public void setAirInletMode(String airInletMode) {
		this.airInletMode = airInletMode;
	}

	public String getFuelSupplySystemModel() {
		return fuelSupplySystemModel;
	}

	public void setFuelSupplySystemModel(String fuelSupplySystemModel) {
		this.fuelSupplySystemModel = fuelSupplySystemModel;
	}

	public Integer getIntEmissionProcessDevice() {
		return intEmissionProcessDevice;
	}

	public void setIntEmissionProcessDevice(Integer intEmissionProcessDevice) {
		this.intEmissionProcessDevice = intEmissionProcessDevice;
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

	public Double getHundredPoint() {
		return hundredPoint;
	}

	public void setHundredPoint(Double hundredPoint) {
		this.hundredPoint = hundredPoint;
	}

	public Double getNintyPoint() {
		return nintyPoint;
	}

	public void setNintyPoint(Double nintyPoint) {
		this.nintyPoint = nintyPoint;
	}

	public Double getEightyPoint() {
		return eightyPoint;
	}

	public void setEightyPoint(Double eightyPoint) {
		this.eightyPoint = eightyPoint;
	}

	public Double getKmLimit() {
		return kmLimit;
	}

	public void setKmLimit(Double kmLimit) {
		this.kmLimit = kmLimit;
	}

	public Double getKwLimit() {
		return kwLimit;
	}

	public void setKwLimit(Double kwLimit) {
		this.kwLimit = kwLimit;
	}

	public Double getKwResult() {
		return kwResult;
	}

	public void setKwResult(Double kwResult) {
		this.kwResult = kwResult;
	}

	public String getTestDeviceModel() {
		return testDeviceModel;
	}

	public void setTestDeviceModel(String testDeviceModel) {
		this.testDeviceModel = testDeviceModel;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
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

	public String getStrDetectionTime() {
		return strDetectionTime;
	}

	public void setStrDetectionTime(String strDetectionTime) {
		this.strDetectionTime = strDetectionTime;
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

	public String getStrVehicleRegisterDate() {
		return strVehicleRegisterDate;
	}

	public void setStrVehicleRegisterDate(String strVehicleRegisterDate) {
		this.strVehicleRegisterDate = strVehicleRegisterDate;
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

	public Integer getCommisionSheetStatus() {
		return commisionSheetStatus;
	}

	public void setCommisionSheetStatus(Integer commisionSheetStatus) {
		this.commisionSheetStatus = commisionSheetStatus;
	}

	public Integer getEmissionStandard() {
		return emissionStandard;
	}

	public void setEmissionStandard(Integer emissionStandard) {
		this.emissionStandard = emissionStandard;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getLicenseColor() {
		return licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}

}
