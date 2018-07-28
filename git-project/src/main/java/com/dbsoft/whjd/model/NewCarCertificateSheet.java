package com.dbsoft.whjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "NewCarCertificateSheet", schema = "dbo", catalog = "whjd")
public class NewCarCertificateSheet implements java.io.Serializable{
	private Integer ID;
	private String certificateID;
	private String vehicleOwnerName;
	private String vehicleOwnerAddress;
	private String phoneNum;
	private String vin;
	private String vehicleModelCode;
	private String engineCode;
	private String engineModel;
	private Double maxTotalQuality;
	private String vehicleRegisterDate;
	private String fuelType;
	private Integer emissionStandard;
	private Integer emissionAdmitStandard;
	private String vehicleIssueCertDate;
	private String fillStationName;
	private String fillUserName;
	private Integer isPrint;
	private Integer status;
	
	private String licence;
	private String licenseColor;
	
	private Integer vehicleType;
	
	
	public NewCarCertificateSheet(){
	}

	public NewCarCertificateSheet(Integer iD, String certificateID, String vehicleOwnerName, String vehicleOwnerAddress,
			String phoneNum, String vin, String vehicleModelCode, String engineCode, String engineModel,
			Double maxTotalQuality, String vehicleRegisterDate, String fuelType, Integer emissionStandard,Integer emissionAdmitStandard,
			String vehicleIssueCertDate, String fillStationName, String fillUserName,Integer isPrint,Integer status,String licence,String licenseColor,Integer vehicleType) {
		super();
		ID = iD;
		this.certificateID = certificateID;
		this.vehicleOwnerName = vehicleOwnerName;
		this.vehicleOwnerAddress = vehicleOwnerAddress;
		this.phoneNum = phoneNum;
		this.vin = vin;
		this.vehicleModelCode = vehicleModelCode;
		this.engineCode = engineCode;
		this.engineModel = engineModel;
		this.maxTotalQuality = maxTotalQuality;
		this.vehicleRegisterDate = vehicleRegisterDate;
		this.fuelType = fuelType;
		this.emissionStandard = emissionStandard;
		this.vehicleIssueCertDate = vehicleIssueCertDate;
		this.fillStationName = fillStationName;
		this.fillUserName = fillUserName;
		this.isPrint = isPrint;
		this.status = status;
		this.emissionAdmitStandard = emissionAdmitStandard;
		this.licence = licence;
		this.licenseColor = licenseColor;
		this.vehicleType = vehicleType;
	}
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	
	@Column(name = "certificateID", length = 100)
	public String getCertificateID() {
		return certificateID;
	}

	public void setCertificateID(String certificateID) {
		this.certificateID = certificateID;
	}
	
	@Column(name = "vehicleOwnerName", length = 100)
	public String getVehicleOwnerName() {
		return vehicleOwnerName;
	}

	public void setVehicleOwnerName(String vehicleOwnerName) {
		this.vehicleOwnerName = vehicleOwnerName;
	}
	
	@Column(name = "vehicleOwnerAddress", length = 100)
	public String getVehicleOwnerAddress() {
		return vehicleOwnerAddress;
	}

	public void setVehicleOwnerAddress(String vehicleOwnerAddress) {
		this.vehicleOwnerAddress = vehicleOwnerAddress;
	}
	
	@Column(name = "phoneNum", length = 100)
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Column(name = "vin", length = 100)
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	@Column(name = "vehicleModelCode", length = 100)
	public String getVehicleModelCode() {
		return vehicleModelCode;
	}

	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}
	
	@Column(name = "engineCode", length = 100)
	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}
	
	@Column(name = "engineModel", length = 100)
	public String getEngineModel() {
		return engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	
	@Column(name = "maxTotalQuality")
	public Double getMaxTotalQuality() {
		return maxTotalQuality;
	}

	public void setMaxTotalQuality(Double maxTotalQuality) {
		this.maxTotalQuality = maxTotalQuality;
	}
	
	@Column(name = "vehicleRegisterDate")
	public String getVehicleRegisterDate() {
		return vehicleRegisterDate;
	}

	public void setVehicleRegisterDate(String vehicleRegisterDate) {
		this.vehicleRegisterDate = vehicleRegisterDate;
	}
	
	@Column(name = "fuelType", length = 50)
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	@Column(name = "emissionStandard")
	public Integer getEmissionStandard() {
		return emissionStandard;
	}

	public void setEmissionStandard(Integer emissionStandard) {
		this.emissionStandard = emissionStandard;
	}
	
	@Column(name = "vehicleIssueCertDate")
	public String getVehicleIssueCertDate() {
		return vehicleIssueCertDate;
	}

	public void setVehicleIssueCertDate(String vehicleIssueCertDate) {
		this.vehicleIssueCertDate = vehicleIssueCertDate;
	}
	
	@Column(name = "fillStationName", length = 100)
	public String getFillStationName() {
		return fillStationName;
	}

	public void setFillStationName(String fillStationName) {
		this.fillStationName = fillStationName;
	}
	
	@Column(name = "fillUserName", length = 100)
	public String getFillUserName() {
		return fillUserName;
	}

	public void setFillUserName(String fillUserName) {
		this.fillUserName = fillUserName;
	}
	
	@Column(name = "isPrint")
	public Integer getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "emissionAdmitStandard")
	public Integer getEmissionAdmitStandard() {
		return emissionAdmitStandard;
	}

	public void setEmissionAdmitStandard(Integer emissionAdmitStandard) {
		this.emissionAdmitStandard = emissionAdmitStandard;
	}
	@Column(name = "licence", length = 100)
	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}
	@Column(name = "licenseColor", length = 100)
	public String getLicenseColor() {
		return licenseColor;
	}

	public void setLicenseColor(String licenseColor) {
		this.licenseColor = licenseColor;
	}
	@Column(name = "vehicleType")
	public Integer getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
}
