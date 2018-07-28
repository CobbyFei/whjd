package com.dbsoft.whjd.pageModel;

import java.sql.Timestamp;

import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.SysUser;

/**
 * @author mark
 *
 */
public class SteadyStateMethodPage {
	
	//检测记录表
	private Integer sheetId;
	private String reportNumber;
	private String licence;
	private String vehicleOwnerName;
	private String vehicleOwnerAddress;
	private String detectionTime;
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
	private String  vehicleRegisterDate;
	private Integer vehicleType;
	private Integer labelDistributeType;
	private Integer isCancel;
	private String detectionMethod;
	private Integer yearCount;
	private Integer commisionSheetStatus;
	private Integer emissionStandard;
	private String stationName;
	private Integer conclusion;
	private String beginSearchTimeString;
	private String endSearchTimeString;
	private String licenseColor;

	//数据表
	private Integer recordId;
	private String  inspectionStationId;
	private Integer inspecDriverId;
	private String  inspecDriverName;
	private Integer localeId;
	private Integer lineId;
	private String  lineName;
	private Integer inspecOperatorId;
	private String inspecOperatorName;
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
	
	
	
	private String q;
	private int page;
	private int rows;
	private String ids;
	
	
	private String sort;
	private String order;
	
	private String phoneNum;
	private Double maxTotalQualityLowerBound;
	private Double maxTotalQualityUpperBound;
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

	private String beginRegisterDate;
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

	private String endRegisterDate;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public Integer getSheetId() {
		return sheetId;
	}
	
	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}
	
	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
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

	public String getInspectionStationId() {
		return inspectionStationId;
	}
	public void setInspectionStationId(String  inspectionStationId) {
		this.inspectionStationId = inspectionStationId;
	}
	
	public String getBeginSearchTimeString() {
		return beginSearchTimeString;
	}

	public void setBeginSearchTimeString(String beginSearchTimeString) {
		this.beginSearchTimeString = beginSearchTimeString;
	}

	public String getEndSearchTimeString() {
		return endSearchTimeString;
	}

	public void setEndSearchTimeString(String endSearchTimeString) {
		this.endSearchTimeString = endSearchTimeString;
	}
	//检测值的get set 方法

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getInspecDriverId() {
		return inspecDriverId;
	}
	
	public void setInspecDriverId(Integer inspecDriverId) {
		this.inspecDriverId = inspecDriverId;
	}
	public String getInspecDriverName() {
		return inspecDriverName;
	}
	public void setInspecDriverName(String inspecDriverName) {
		this.inspecDriverName = inspecDriverName;
	}
	public Integer getLocaleId() {
		return localeId;
	}
	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
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
	public Integer getInspecOperatorId() {
		return inspecOperatorId;
	}
	public void setInspecOperatorId(Integer inspecOperatorId) {
		this.inspecOperatorId = inspecOperatorId;
	}

	public String getInspecOperatorName() {
		return inspecOperatorName;
	}

	public void setInspecOperatorName(String inspecOperatorName) {
		this.inspecOperatorName = inspecOperatorName;
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
	public String getChassisDynamometer() {
		return chassisDynamometer;
	}
	public void setChassisDynamometer(String chassisDynamometer) {
		this.chassisDynamometer = chassisDynamometer;
	}
	public String getExhaustGasAnalyser() {
		return exhaustGasAnalyser;
	}
	public void setExhaustGasAnalyser(String exhaustGasAnalyser) {
		this.exhaustGasAnalyser = exhaustGasAnalyser;
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
	public Double getWtHcAsm5025() {
		return wtHcAsm5025;
	}
	public void setWtHcAsm5025(Double wtHcAsm5025) {
		this.wtHcAsm5025 = wtHcAsm5025;
	}
	public Double getWtHcAsm2540() {
		return wtHcAsm2540;
	}
	public void setWtHcAsm2540(Double wtHcAsm2540) {
		this.wtHcAsm2540 = wtHcAsm2540;
	}
	public Double getWtCoAsm5025() {
		return wtCoAsm5025;
	}
	public void setWtCoAsm5025(Double wtCoAsm5025) {
		this.wtCoAsm5025 = wtCoAsm5025;
	}
	public Double getWtCoAsm2540() {
		return wtCoAsm2540;
	}
	public void setWtCoAsm2540(Double wtCoAsm2540) {
		this.wtCoAsm2540 = wtCoAsm2540;
	}
	public Double getWtNoAsm5025() {
		return wtNoAsm5025;
	}
	public void setWtNoAsm5025(Double wtNoAsm5025) {
		this.wtNoAsm5025 = wtNoAsm5025;
	}
	public Double getWtNoAsm2540() {
		return wtNoAsm2540;
	}
	public void setWtNoAsm2540(Double wtNoAsm2540) {
		this.wtNoAsm2540 = wtNoAsm2540;
	}
	public Double getWtHcAsm5025Limit() {
		return wtHcAsm5025Limit;
	}
	public void setWtHcAsm5025Limit(Double wtHcAsm5025Limit) {
		this.wtHcAsm5025Limit = wtHcAsm5025Limit;
	}
	public Double getWtHcAsm2540Limit() {
		return wtHcAsm2540Limit;
	}
	public void setWtHcAsm2540Limit(Double wtHcAsm2540Limit) {
		this.wtHcAsm2540Limit = wtHcAsm2540Limit;
	}
	public Double getWtCoAsm5025Limit() {
		return wtCoAsm5025Limit;
	}
	public void setWtCoAsm5025Limit(Double wtCoAsm5025Limit) {
		this.wtCoAsm5025Limit = wtCoAsm5025Limit;
	}
	public Double getWtCoAsm2540Limit() {
		return wtCoAsm2540Limit;
	}
	public void setWtCoAsm2540Limit(Double wtCoAsm2540Limit) {
		this.wtCoAsm2540Limit = wtCoAsm2540Limit;
	}
	public Double getWtNoAsm5025Limit() {
		return wtNoAsm5025Limit;
	}
	public void setWtNoAsm5025Limit(Double wtNoAsm5025Limit) {
		this.wtNoAsm5025Limit = wtNoAsm5025Limit;
	}
	public Double getWtNoAsm2540Limit() {
		return wtNoAsm2540Limit;
	}
	public void setWtNoAsm2540Limit(Double wtNoAsm2540Limit) {
		this.wtNoAsm2540Limit = wtNoAsm2540Limit;
	}
	public Boolean getWtHcAsm5025Conclusion() {
		return wtHcAsm5025Conclusion;
	}
	public void setWtHcAsm5025Conclusion(Boolean wtHcAsm5025Conclusion) {
		this.wtHcAsm5025Conclusion = wtHcAsm5025Conclusion;
	}
	public Boolean getWtHcAsm2540Conclusion() {
		return wtHcAsm2540Conclusion;
	}
	public void setWtHcAsm2540Conclusion(Boolean wtHcAsm2540Conclusion) {
		this.wtHcAsm2540Conclusion = wtHcAsm2540Conclusion;
	}
	public Boolean getWtCoAsm5025Conclusion() {
		return wtCoAsm5025Conclusion;
	}
	public void setWtCoAsm5025Conclusion(Boolean wtCoAsm5025Conclusion) {
		this.wtCoAsm5025Conclusion = wtCoAsm5025Conclusion;
	}
	public Boolean getWtCoAsm2540Conclusion() {
		return wtCoAsm2540Conclusion;
	}
	public void setWtCoAsm2540Conclusion(Boolean wtCoAsm2540Conclusion) {
		this.wtCoAsm2540Conclusion = wtCoAsm2540Conclusion;
	}
	public Boolean getWtNoAsm5025Conclusion() {
		return wtNoAsm5025Conclusion;
	}
	public void setWtNoAsm5025Conclusion(Boolean wtNoAsm5025Conclusion) {
		this.wtNoAsm5025Conclusion = wtNoAsm5025Conclusion;
	}
	public Boolean getWtNoAsm2540Conclusion() {
		return wtNoAsm2540Conclusion;
	}
	public void setWtNoAsm2540Conclusion(Boolean wtNoAsm2540Conclusion) {
		this.wtNoAsm2540Conclusion = wtNoAsm2540Conclusion;
	}
	public Boolean getWtHcAsm5025Judge() {
		return wtHcAsm5025Judge;
	}
	public void setWtHcAsm5025Judge(Boolean wtHcAsm5025Judge) {
		this.wtHcAsm5025Judge = wtHcAsm5025Judge;
	}
	public Boolean getWtHcAsm2540Judge() {
		return wtHcAsm2540Judge;
	}
	public void setWtHcAsm2540Judge(Boolean wtHcAsm2540Judge) {
		this.wtHcAsm2540Judge = wtHcAsm2540Judge;
	}
	public Boolean getWtCoAsm5025Judge() {
		return wtCoAsm5025Judge;
	}
	public void setWtCoAsm5025Judge(Boolean wtCoAsm5025Judge) {
		this.wtCoAsm5025Judge = wtCoAsm5025Judge;
	}
	public Boolean getWtCoAsm2540Judge() {
		return wtCoAsm2540Judge;
	}
	public void setWtCoAsm2540Judge(Boolean wtCoAsm2540Judge) {
		this.wtCoAsm2540Judge = wtCoAsm2540Judge;
	}
	public Boolean getWtNoAsm5025Judge() {
		return wtNoAsm5025Judge;
	}
	public void setWtNoAsm5025Judge(Boolean wtNoAsm5025Judge) {
		this.wtNoAsm5025Judge = wtNoAsm5025Judge;
	}
	public Boolean getWtNoAsm2540Judge() {
		return wtNoAsm2540Judge;
	}
	public void setWtNoAsm2540Judge(Boolean wtNoAsm2540Judge) {
		this.wtNoAsm2540Judge = wtNoAsm2540Judge;
	}
	public Integer getConclusion() {
		return conclusion;
	}
	public void setConclusion(Integer conclusion) {
		this.conclusion = conclusion;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getWheelNums() {
		return wheelNums;
	}

	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLicenseColor() {
		return licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}
	
		
}
