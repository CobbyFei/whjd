package com.dbsoft.whjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * DistributionTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "distributionTask", schema = "dbo", catalog = "whjd")
public class DistributionTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer commisionSheetId;
	private String licence;
	private String vechileModel;
	private String engineModel;
	private String vehicleOwnerName;
	private String vehicleAddress;
	private String vin;
	private String vehicleRegisterDate;
	private String engineCode;
	private String issueDate;
	private Double totalQuality;
	private String vehicleType;
	private String validatePeriod;
	private String stationName;
	private String remarks;
	private String licenseColor;
	private Integer vehicleLoadNum;
	private Integer handleStatus;
	private Integer labelDistributeType;

	// Constructors

	/** default constructor */
	public DistributionTask() {
	}

	/** full constructor */
	public DistributionTask(Integer commisionSheetId, String licence,
			String vechileModel, String engineModel, String vehicleOwnerName,
			String vehicleAddress, String vin, String vehicleRegisterDate,
			String engineCode, String issueDate, Double totalQuality,
			String vehicleType, String validatePeriod, String stationName,
			String remarks, String licenseColor, Integer vehicleLoadNum,
			Integer handleStatus, Integer labelDistributeType) {
		this.commisionSheetId = commisionSheetId;
		this.licence = licence;
		this.vechileModel = vechileModel;
		this.engineModel = engineModel;
		this.vehicleOwnerName = vehicleOwnerName;
		this.vehicleAddress = vehicleAddress;
		this.vin = vin;
		this.vehicleRegisterDate = vehicleRegisterDate;
		this.engineCode = engineCode;
		this.issueDate = issueDate;
		this.totalQuality = totalQuality;
		this.vehicleType = vehicleType;
		this.validatePeriod = validatePeriod;
		this.stationName = stationName;
		this.remarks = remarks;
		this.licenseColor = licenseColor;
		this.vehicleLoadNum = vehicleLoadNum;
		this.handleStatus = handleStatus;
		this.labelDistributeType = labelDistributeType;
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

	@Column(name = "commisionSheetId")
	public Integer getCommisionSheetId() {
		return this.commisionSheetId;
	}

	public void setCommisionSheetId(Integer commisionSheetId) {
		this.commisionSheetId = commisionSheetId;
	}

	@Column(name = "licence", length = 20)
	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	@Column(name = "vechileModel", length = 30)
	public String getVechileModel() {
		return this.vechileModel;
	}

	public void setVechileModel(String vechileModel) {
		this.vechileModel = vechileModel;
	}

	@Column(name = "engineModel", length = 30)
	public String getEngineModel() {
		return this.engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}

	@Column(name = "vehicleOwnerName", length = 20)
	public String getVehicleOwnerName() {
		return this.vehicleOwnerName;
	}

	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}

	@Column(name = "vehicleAddress", length = 30)
	public String getVehicleAddress() {
		return this.vehicleAddress;
	}

	public void setVehicleAddress(String vehicleAddress) {
		this.vehicleAddress = vehicleAddress;
	}

	@Column(name = "vin", length = 30)
	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "vehicleRegisterDate", length = 30)
	public String getVehicleRegisterDate() {
		return this.vehicleRegisterDate;
	}

	public void setVehicleRegisterDate(String vehicleRegisterDate) {
		this.vehicleRegisterDate = vehicleRegisterDate;
	}

	@Column(name = "engineCode", length = 30)
	public String getEngineCode() {
		return this.engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	@Column(name = "issueDate", length = 30)
	public String getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "totalQuality", precision = 53, scale = 0)
	public Double getTotalQuality() {
		return this.totalQuality;
	}

	public void setTotalQuality(Double totalQuality) {
		this.totalQuality = totalQuality;
	}

	@Column(name = "vehicleType", length = 20)
	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "validatePeriod", length = 30)
	public String getValidatePeriod() {
		return this.validatePeriod;
	}

	public void setValidatePeriod(String validatePeriod) {
		this.validatePeriod = validatePeriod;
	}

	@Column(name = "stationName", length = 20)
	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Column(name = "remarks", length = 30)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "licenseColor", length = 30)
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

	@Column(name = "handleStatus")
	public Integer getHandleStatus() {
		return this.handleStatus;
	}

	public void setHandleStatus(Integer handleStatus) {
		this.handleStatus = handleStatus;
	}

	@Column(name = "labelDistributeType")
	public Integer getLabelDistributeType() {
		return this.labelDistributeType;
	}

	public void setLabelDistributeType(Integer labelDistributeType) {
		this.labelDistributeType = labelDistributeType;
	}

}