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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * InspectionStation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "InspectionStation", schema = "dbo", catalog = "whjd")
public class InspectionStation implements java.io.Serializable {

	// Fields

	private Integer stationId;
	private String stationName;
	private String stationAddress;
	private Timestamp registrationTime;
	private String qulificationType;
	private Timestamp qulificationTime;
	private Timestamp validPeriod;
	private String remarks;
	private String institutionNum;
	
	private Integer status;
	private Integer isPush;
	private Set<EnvironmentalLabelCollar> environmentalLabelCollars = new HashSet<EnvironmentalLabelCollar>(
			0);
	private Set<DetectionLine> detectionLines = new HashSet<DetectionLine>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	private Set<StationManagement> stationManagements = new HashSet<StationManagement>(
			0);
	private Set<ReferenceMaterialsRecord> referenceMaterialsRecords = new HashSet<ReferenceMaterialsRecord>(
			0);
	private Set<EnvironmentalLabel> environmentalLabels = new HashSet<EnvironmentalLabel>(
			0);
	private Set<DevicePurchaseRecord> devicePurchaseRecords = new HashSet<DevicePurchaseRecord>(
			0);

	// Constructors

	/** default constructor */
	public InspectionStation() {
	}

	/** full constructor */
	public InspectionStation(String institutionNum,String stationName, String stationAddress,
			Timestamp registrationTime, String qulificationType,
			Timestamp qulificationTime, Timestamp validPeriod, String remarks,
			Integer status, Integer isPush,
			Set<EnvironmentalLabelCollar> environmentalLabelCollars,
			Set<DetectionLine> detectionLines, Set<SysUser> sysUsers,
			Set<StationManagement> stationManagements,
			Set<ReferenceMaterialsRecord> referenceMaterialsRecords,
			Set<EnvironmentalLabel> environmentalLabels,
			Set<DevicePurchaseRecord> devicePurchaseRecords) {
		this.stationName = stationName;
		this.stationAddress = stationAddress;
		this.registrationTime = registrationTime;
		this.qulificationType = qulificationType;
		this.qulificationTime = qulificationTime;
		this.validPeriod = validPeriod;
		this.remarks = remarks;
		this.status = status;
		this.isPush = isPush;
		this.environmentalLabelCollars = environmentalLabelCollars;
		this.detectionLines = detectionLines;
		this.sysUsers = sysUsers;
		this.stationManagements = stationManagements;
		this.referenceMaterialsRecords = referenceMaterialsRecords;
		this.environmentalLabels = environmentalLabels;
		this.devicePurchaseRecords = devicePurchaseRecords;
		this.institutionNum = institutionNum;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "stationID", unique = true, nullable = false)
	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getInstitutionNum() {
		return institutionNum;
	}

	public void setInstitutionNum(String institutionNum) {
		this.institutionNum = institutionNum;
	}

	@Column(name = "stationName", length = 100)
	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Column(name = "stationAddress", length = 100)
	public String getStationAddress() {
		return this.stationAddress;
	}

	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	@Column(name = "registrationTime", length = 23)
	public Timestamp getRegistrationTime() {
		return this.registrationTime;
	}

	public void setRegistrationTime(Timestamp registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Column(name = "qulificationType", length = 2)
	public String getQulificationType() {
		return this.qulificationType;
	}

	public void setQulificationType(String qulificationType) {
		this.qulificationType = qulificationType;
	}

	@Column(name = "qulificationTime", length = 23)
	public Timestamp getQulificationTime() {
		return this.qulificationTime;
	}

	public void setQulificationTime(Timestamp qulificationTime) {
		this.qulificationTime = qulificationTime;
	}

	@Column(name = "validPeriod", length = 23)
	public Timestamp getValidPeriod() {
		return this.validPeriod;
	}

	public void setValidPeriod(Timestamp validPeriod) {
		this.validPeriod = validPeriod;
	}

	@Column(name = "remarks", length = 40)
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
	@Column(name = "isPush" , nullable=false , columnDefinition="INT default 0")
	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<EnvironmentalLabelCollar> getEnvironmentalLabelCollars() {
		return this.environmentalLabelCollars;
	}

	public void setEnvironmentalLabelCollars(
			Set<EnvironmentalLabelCollar> environmentalLabelCollars) {
		this.environmentalLabelCollars = environmentalLabelCollars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<DetectionLine> getDetectionLines() {
		return this.detectionLines;
	}

	public void setDetectionLines(Set<DetectionLine> detectionLines) {
		this.detectionLines = detectionLines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<StationManagement> getStationManagements() {
		return this.stationManagements;
	}

	public void setStationManagements(Set<StationManagement> stationManagements) {
		this.stationManagements = stationManagements;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<ReferenceMaterialsRecord> getReferenceMaterialsRecords() {
		return this.referenceMaterialsRecords;
	}

	public void setReferenceMaterialsRecords(
			Set<ReferenceMaterialsRecord> referenceMaterialsRecords) {
		this.referenceMaterialsRecords = referenceMaterialsRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<EnvironmentalLabel> getEnvironmentalLabels() {
		return this.environmentalLabels;
	}

	public void setEnvironmentalLabels(
			Set<EnvironmentalLabel> environmentalLabels) {
		this.environmentalLabels = environmentalLabels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inspectionStation")
	public Set<DevicePurchaseRecord> getDevicePurchaseRecords() {
		return this.devicePurchaseRecords;
	}

	public void setDevicePurchaseRecords(
			Set<DevicePurchaseRecord> devicePurchaseRecords) {
		this.devicePurchaseRecords = devicePurchaseRecords;
	}

}