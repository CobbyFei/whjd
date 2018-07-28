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
 * DeviceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "deviceInfo", schema = "dbo", catalog = "whjd")
public class DeviceInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private DetectionLine detectionLine;
	private Integer deviceStatus;
	private String remarks;
	private String deviceName;
	private String manufacturer;
	private String deviceModel;
	private Set<MaintainceRecord> maintainceRecords = new HashSet<MaintainceRecord>(
			0);
	private Integer isPush;

	// Constructors

	/** default constructor */
	public DeviceInfo() {
	}

	/** full constructor */
	public DeviceInfo(DetectionLine detectionLine, Integer deviceStatus,
			String remarks, String deviceName, String manufacturer,
			String deviceModel, Integer isPush , Set<MaintainceRecord> maintainceRecords) {
		this.detectionLine = detectionLine;
		this.deviceStatus = deviceStatus;
		this.remarks = remarks;
		this.deviceName = deviceName;
		this.manufacturer = manufacturer;
		this.deviceModel = deviceModel;
		this.maintainceRecords = maintainceRecords;
		this.isPush = isPush;
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
	@JoinColumn(name = "lineID")
	public DetectionLine getDetectionLine() {
		return this.detectionLine;
	}

	public void setDetectionLine(DetectionLine detectionLine) {
		this.detectionLine = detectionLine;
	}

	@Column(name = "deviceStatus")
	public Integer getDeviceStatus() {
		return this.deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	@Column(name = "remarks", length = 40)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Column(name = "isPush")
	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "deviceInfo")
	public Set<MaintainceRecord> getMaintainceRecords() {
		return this.maintainceRecords;
	}

	public void setMaintainceRecords(Set<MaintainceRecord> maintainceRecords) {
		this.maintainceRecords = maintainceRecords;
	}

}