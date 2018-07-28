package com.dbsoft.whjd.model;

import java.sql.Timestamp;
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
 * EnvironmentalLabel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "environmentalLabel", schema = "dbo", catalog = "whjd")
public class EnvironmentalLabel implements java.io.Serializable {

	// Fields

	private Integer id;
	private InspectionStation inspectionStation;
	private String labelId;
	private String licence;
	private String vehicleModelCode;
	private String vehicleBrand;
	private String fuelType;
	private String vehicleClassification;
	private String vehicleOwner;
	private Timestamp vehicleRegisterTime;
	private Timestamp distributionCertTime;
	private String vinNo;
	private String emissionStandard;
	private Timestamp issueDate;
	private Timestamp validPeriod;
	private String labelType;
	private Integer year;
	private Boolean isPrint;
	private Boolean isCancel;
	private String stationName;
	private Set<DetectionCommisionSheet> detectionCommisionSheets = new HashSet<DetectionCommisionSheet>(
			0);
	private String licenseColor;

	// Constructors

	/** default constructor */
	public EnvironmentalLabel() {
	}

	/** full constructor */
	public EnvironmentalLabel(InspectionStation inspectionStation,
			String labelId, String licence, String vehicleModelCode,
			String vehicleBrand, String fuelType, String vehicleClassification,
			String vehicleOwner, Timestamp vehicleRegisterTime,
			Timestamp distributionCertTime, String vinNo,
			String emissionStandard, Timestamp issueDate,
			Timestamp validPeriod, String labelType, Integer year,
			Boolean isPrint, Boolean isCancel, String stationName,
			Set<DetectionCommisionSheet> detectionCommisionSheets,String licenseColor) {
		this.inspectionStation = inspectionStation;
		this.labelId = labelId;
		this.licence = licence;
		this.vehicleModelCode = vehicleModelCode;
		this.vehicleBrand = vehicleBrand;
		this.fuelType = fuelType;
		this.vehicleClassification = vehicleClassification;
		this.vehicleOwner = vehicleOwner;
		this.vehicleRegisterTime = vehicleRegisterTime;
		this.distributionCertTime = distributionCertTime;
		this.vinNo = vinNo;
		this.emissionStandard = emissionStandard;
		this.issueDate = issueDate;
		this.validPeriod = validPeriod;
		this.labelType = labelType;
		this.year = year;
		this.isPrint = isPrint;
		this.isCancel = isCancel;
		this.stationName = stationName;
		this.detectionCommisionSheets = detectionCommisionSheets;
		this.licenseColor = licenseColor;
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
	@JoinColumn(name = "stationID")
	public InspectionStation getInspectionStation() {
		return this.inspectionStation;
	}

	public void setInspectionStation(InspectionStation inspectionStation) {
		this.inspectionStation = inspectionStation;
	}

	@Column(name = "labelID", length = 50)
	public String getLabelId() {
		return this.labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	@Column(name = "licence", length = 10)
	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	@Column(name = "vehicleModelCode", length = 50)
	public String getVehicleModelCode() {
		return this.vehicleModelCode;
	}

	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}

	@Column(name = "vehicleBrand", length = 50)
	public String getVehicleBrand() {
		return this.vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	@Column(name = "fuelType", length = 50)
	public String getFuelType() {
		return this.fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(name = "vehicleClassification", length = 30)
	public String getVehicleClassification() {
		return this.vehicleClassification;
	}

	public void setVehicleClassification(String vehicleClassification) {
		this.vehicleClassification = vehicleClassification;
	}

	@Column(name = "vehicleOwner", length = 100)
	public String getVehicleOwner() {
		return this.vehicleOwner;
	}

	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}

	@Column(name = "vehicleRegisterTime", length = 23)
	public Timestamp getVehicleRegisterTime() {
		return this.vehicleRegisterTime;
	}

	public void setVehicleRegisterTime(Timestamp vehicleRegisterTime) {
		this.vehicleRegisterTime = vehicleRegisterTime;
	}

	@Column(name = "distributionCertTime", length = 23)
	public Timestamp getDistributionCertTime() {
		return this.distributionCertTime;
	}

	public void setDistributionCertTime(Timestamp distributionCertTime) {
		this.distributionCertTime = distributionCertTime;
	}

	@Column(name = "vinNo", length = 30)
	public String getVinNo() {
		return this.vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	@Column(name = "emissionStandard", length = 30)
	public String getEmissionStandard() {
		return this.emissionStandard;
	}

	public void setEmissionStandard(String emissionStandard) {
		this.emissionStandard = emissionStandard;
	}

	@Column(name = "issueDate", length = 23)
	public Timestamp getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "validPeriod", length = 23)
	public Timestamp getValidPeriod() {
		return this.validPeriod;
	}

	public void setValidPeriod(Timestamp validPeriod) {
		this.validPeriod = validPeriod;
	}

	@Column(name = "labelType", length = 30)
	public String getLabelType() {
		return this.labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	@Column(name = "year")
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "isPrint")
	public Boolean getIsPrint() {
		return this.isPrint;
	}

	public void setIsPrint(Boolean isPrint) {
		this.isPrint = isPrint;
	}

	@Column(name = "isCancel")
	public Boolean getIsCancel() {
		return this.isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	@Column(name = "stationName", length = 100)
	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "environmentalLabel")
	public Set<DetectionCommisionSheet> getDetectionCommisionSheets() {
		return this.detectionCommisionSheets;
	}

	public void setDetectionCommisionSheets(
			Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.detectionCommisionSheets = detectionCommisionSheets;
	}
	
	@Column(name = "licenseColor", length = 20)
	public String getLicenseColor() {
		return licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}
	
	

}