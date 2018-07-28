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
 * DevicePurchaseRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "devicePurchaseRecord", schema = "dbo", catalog = "whjd")
public class DevicePurchaseRecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private InspectionStation inspectionStation;
	private String deviceName;
	private String manufacturer;
	private String deviceModel;
	private Integer purchaseNum;
	private Timestamp purchaseTime;
	private String remarks;
	private Integer status;
	private String specification;

	// Constructors

	/** default constructor */
	public DevicePurchaseRecord() {
	}

	/** full constructor */
	public DevicePurchaseRecord(InspectionStation inspectionStation,
			String deviceName, String manufacturer, String deviceModel,
			Integer purchaseNum, Timestamp purchaseTime, String remarks,
			Integer status, String specification) {
		this.inspectionStation = inspectionStation;
		this.deviceName = deviceName;
		this.manufacturer = manufacturer;
		this.deviceModel = deviceModel;
		this.purchaseNum = purchaseNum;
		this.purchaseTime = purchaseTime;
		this.remarks = remarks;
		this.status = status;
		this.specification = specification;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "recordID", unique = true, nullable = false)
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stationID")
	public InspectionStation getInspectionStation() {
		return this.inspectionStation;
	}

	public void setInspectionStation(InspectionStation inspectionStation) {
		this.inspectionStation = inspectionStation;
	}

	@Column(name = "deviceName", length = 20)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "manufacturer", length = 40)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "deviceModel", length = 20)
	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Column(name = "purchaseNum")
	public Integer getPurchaseNum() {
		return this.purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	@Column(name = "purchaseTime", length = 23)
	public Timestamp getPurchaseTime() {
		return this.purchaseTime;
	}

	public void setPurchaseTime(Timestamp purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@Column(name = "remarks", length = 50)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "specification", length = 10)
	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

}