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
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sysUser", schema = "dbo", catalog = "whjd")
public class SysUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private InspectionStation inspectionStation;
	private String userName;
	private String simplifyName;
	private String idcard;
	private String sex;
	private String jobTitle;
	private String tel;
	private String certificateName;
	private Timestamp certificateTime;
	private String degree;
	private Integer status;
	private String password;
	private Integer isPush;
	private Set<FreeAccelerationMethod> freeAccelerationMethodsForApproverId = new HashSet<FreeAccelerationMethod>(
			0);
	private Set<LugDownMethod> lugDownMethodsForAccessorId = new HashSet<LugDownMethod>(
			0);
	private Set<TwoSpeedIdleMethod> twoSpeedIdleMethodsForInspecDriverId = new HashSet<TwoSpeedIdleMethod>(
			0);
	private Set<StationManagement> stationManagements = new HashSet<StationManagement>(
			0);
	private Set<LugDownMethod> lugDownMethodsForApproverId = new HashSet<LugDownMethod>(
			0);
	private Set<FreeAccelerationMethod> freeAccelerationMethodsForInspectorId = new HashSet<FreeAccelerationMethod>(
			0);
	private Set<TrainingRecord> trainingRecords = new HashSet<TrainingRecord>(0);
	private Set<CalibrationRecord> calibrationRecords = new HashSet<CalibrationRecord>(
			0);
	private Set<LugDownMethod> lugDownMethodsForInspectorId = new HashSet<LugDownMethod>(
			0);
	private Set<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelCollars = new HashSet<AgencyEnvironmentalLabelCollar>(
			0);
	private Set<MaintainceRecord> maintainceRecords = new HashSet<MaintainceRecord>(
			0);
	private Set<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethods = new HashSet<MotorTwoSpeedIdleMethod>(
			0);
	private Set<SteadyStateMethod> steadyStateMethodsForInspecOperatorId = new HashSet<SteadyStateMethod>(
			0);
	private Set<FreeAccelerationMethod> freeAccelerationMethodsForAccessorId = new HashSet<FreeAccelerationMethod>(
			0);
	private Set<TwoSpeedIdleMethod> twoSpeedIdleMethodsForInspecOperatorId = new HashSet<TwoSpeedIdleMethod>(
			0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<EnvironmentalLabelCollar> environmentalLabelCollars = new HashSet<EnvironmentalLabelCollar>(
			0);
	private Set<SteadyStateMethod> steadyStateMethodsForInspecDriverId = new HashSet<SteadyStateMethod>(
			0);

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(
			InspectionStation inspectionStation,
			String userName,
			String simplifyName,
			String idcard,
			String sex,
			String jobTitle,
			String tel,
			String certificateName,
			Timestamp certificateTime,
			String degree,
			Integer status,
			String password,
			Integer isPush,
			Set<FreeAccelerationMethod> freeAccelerationMethodsForApproverId,
			Set<LugDownMethod> lugDownMethodsForAccessorId,
			Set<TwoSpeedIdleMethod> twoSpeedIdleMethodsForInspecDriverId,
			Set<StationManagement> stationManagements,
			Set<LugDownMethod> lugDownMethodsForApproverId,
			Set<FreeAccelerationMethod> freeAccelerationMethodsForInspectorId,
			Set<TrainingRecord> trainingRecords,
			Set<CalibrationRecord> calibrationRecords,
			Set<LugDownMethod> lugDownMethodsForInspectorId,
			Set<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelCollars,
			Set<MaintainceRecord> maintainceRecords,
			Set<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethods,
			Set<SteadyStateMethod> steadyStateMethodsForInspecOperatorId,
			Set<FreeAccelerationMethod> freeAccelerationMethodsForAccessorId,
			Set<TwoSpeedIdleMethod> twoSpeedIdleMethodsForInspecOperatorId,
			Set<UserRole> userRoles,
			Set<EnvironmentalLabelCollar> environmentalLabelCollars,
			Set<SteadyStateMethod> steadyStateMethodsForInspecDriverId) {
		this.inspectionStation = inspectionStation;
		this.userName = userName;
		this.simplifyName = simplifyName;
		this.idcard = idcard;
		this.sex = sex;
		this.jobTitle = jobTitle;
		this.tel = tel;
		this.certificateName = certificateName;
		this.certificateTime = certificateTime;
		this.degree = degree;
		this.status = status;
		this.password = password;
		this.isPush = isPush;
		this.freeAccelerationMethodsForApproverId = freeAccelerationMethodsForApproverId;
		this.lugDownMethodsForAccessorId = lugDownMethodsForAccessorId;
		this.twoSpeedIdleMethodsForInspecDriverId = twoSpeedIdleMethodsForInspecDriverId;
		this.stationManagements = stationManagements;
		this.lugDownMethodsForApproverId = lugDownMethodsForApproverId;
		this.freeAccelerationMethodsForInspectorId = freeAccelerationMethodsForInspectorId;
		this.trainingRecords = trainingRecords;
		this.calibrationRecords = calibrationRecords;
		this.lugDownMethodsForInspectorId = lugDownMethodsForInspectorId;
		this.agencyEnvironmentalLabelCollars = agencyEnvironmentalLabelCollars;
		this.maintainceRecords = maintainceRecords;
		this.motorTwoSpeedIdleMethods = motorTwoSpeedIdleMethods;
		this.steadyStateMethodsForInspecOperatorId = steadyStateMethodsForInspecOperatorId;
		this.freeAccelerationMethodsForAccessorId = freeAccelerationMethodsForAccessorId;
		this.twoSpeedIdleMethodsForInspecOperatorId = twoSpeedIdleMethodsForInspecOperatorId;
		this.userRoles = userRoles;
		this.environmentalLabelCollars = environmentalLabelCollars;
		this.steadyStateMethodsForInspecDriverId = steadyStateMethodsForInspecDriverId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stationID")
	public InspectionStation getInspectionStation() {
		return this.inspectionStation;
	}

	public void setInspectionStation(InspectionStation inspectionStation) {
		this.inspectionStation = inspectionStation;
	}

	@Column(name = "userName", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "simplifyName", length = 100)
	public String getSimplifyName() {
		return this.simplifyName;
	}

	public void setSimplifyName(String simplifyName) {
		this.simplifyName = simplifyName;
	}

	@Column(name = "IDCard", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "jobTitle", length = 20)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "tel", length = 15)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "certificateName", length = 30)
	public String getCertificateName() {
		return this.certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	@Column(name = "certificateTime", length = 23)
	public Timestamp getCertificateTime() {
		return this.certificateTime;
	}

	public void setCertificateTime(Timestamp certificateTime) {
		this.certificateTime = certificateTime;
	}

	@Column(name = "degree", length = 15)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "isPush" , nullable=false , columnDefinition="INT default 0")
	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByApproverId")
	public Set<FreeAccelerationMethod> getFreeAccelerationMethodsForApproverId() {
		return this.freeAccelerationMethodsForApproverId;
	}

	public void setFreeAccelerationMethodsForApproverId(
			Set<FreeAccelerationMethod> freeAccelerationMethodsForApproverId) {
		this.freeAccelerationMethodsForApproverId = freeAccelerationMethodsForApproverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByAccessorId")
	public Set<LugDownMethod> getLugDownMethodsForAccessorId() {
		return this.lugDownMethodsForAccessorId;
	}

	public void setLugDownMethodsForAccessorId(
			Set<LugDownMethod> lugDownMethodsForAccessorId) {
		this.lugDownMethodsForAccessorId = lugDownMethodsForAccessorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByInspecDriverId")
	public Set<TwoSpeedIdleMethod> getTwoSpeedIdleMethodsForInspecDriverId() {
		return this.twoSpeedIdleMethodsForInspecDriverId;
	}

	public void setTwoSpeedIdleMethodsForInspecDriverId(
			Set<TwoSpeedIdleMethod> twoSpeedIdleMethodsForInspecDriverId) {
		this.twoSpeedIdleMethodsForInspecDriverId = twoSpeedIdleMethodsForInspecDriverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<StationManagement> getStationManagements() {
		return this.stationManagements;
	}

	public void setStationManagements(Set<StationManagement> stationManagements) {
		this.stationManagements = stationManagements;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByApproverId")
	public Set<LugDownMethod> getLugDownMethodsForApproverId() {
		return this.lugDownMethodsForApproverId;
	}

	public void setLugDownMethodsForApproverId(
			Set<LugDownMethod> lugDownMethodsForApproverId) {
		this.lugDownMethodsForApproverId = lugDownMethodsForApproverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByInspectorId")
	public Set<FreeAccelerationMethod> getFreeAccelerationMethodsForInspectorId() {
		return this.freeAccelerationMethodsForInspectorId;
	}

	public void setFreeAccelerationMethodsForInspectorId(
			Set<FreeAccelerationMethod> freeAccelerationMethodsForInspectorId) {
		this.freeAccelerationMethodsForInspectorId = freeAccelerationMethodsForInspectorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<TrainingRecord> getTrainingRecords() {
		return this.trainingRecords;
	}

	public void setTrainingRecords(Set<TrainingRecord> trainingRecords) {
		this.trainingRecords = trainingRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<CalibrationRecord> getCalibrationRecords() {
		return this.calibrationRecords;
	}

	public void setCalibrationRecords(Set<CalibrationRecord> calibrationRecords) {
		this.calibrationRecords = calibrationRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByInspectorId")
	public Set<LugDownMethod> getLugDownMethodsForInspectorId() {
		return this.lugDownMethodsForInspectorId;
	}

	public void setLugDownMethodsForInspectorId(
			Set<LugDownMethod> lugDownMethodsForInspectorId) {
		this.lugDownMethodsForInspectorId = lugDownMethodsForInspectorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<AgencyEnvironmentalLabelCollar> getAgencyEnvironmentalLabelCollars() {
		return this.agencyEnvironmentalLabelCollars;
	}

	public void setAgencyEnvironmentalLabelCollars(
			Set<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelCollars) {
		this.agencyEnvironmentalLabelCollars = agencyEnvironmentalLabelCollars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<MaintainceRecord> getMaintainceRecords() {
		return this.maintainceRecords;
	}

	public void setMaintainceRecords(Set<MaintainceRecord> maintainceRecords) {
		this.maintainceRecords = maintainceRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<MotorTwoSpeedIdleMethod> getMotorTwoSpeedIdleMethods() {
		return this.motorTwoSpeedIdleMethods;
	}

	public void setMotorTwoSpeedIdleMethods(
			Set<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethods) {
		this.motorTwoSpeedIdleMethods = motorTwoSpeedIdleMethods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByInspecOperatorId")
	public Set<SteadyStateMethod> getSteadyStateMethodsForInspecOperatorId() {
		return this.steadyStateMethodsForInspecOperatorId;
	}

	public void setSteadyStateMethodsForInspecOperatorId(
			Set<SteadyStateMethod> steadyStateMethodsForInspecOperatorId) {
		this.steadyStateMethodsForInspecOperatorId = steadyStateMethodsForInspecOperatorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByAccessorId")
	public Set<FreeAccelerationMethod> getFreeAccelerationMethodsForAccessorId() {
		return this.freeAccelerationMethodsForAccessorId;
	}

	public void setFreeAccelerationMethodsForAccessorId(
			Set<FreeAccelerationMethod> freeAccelerationMethodsForAccessorId) {
		this.freeAccelerationMethodsForAccessorId = freeAccelerationMethodsForAccessorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByInspecOperatorId")
	public Set<TwoSpeedIdleMethod> getTwoSpeedIdleMethodsForInspecOperatorId() {
		return this.twoSpeedIdleMethodsForInspecOperatorId;
	}

	public void setTwoSpeedIdleMethodsForInspecOperatorId(
			Set<TwoSpeedIdleMethod> twoSpeedIdleMethodsForInspecOperatorId) {
		this.twoSpeedIdleMethodsForInspecOperatorId = twoSpeedIdleMethodsForInspecOperatorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sysUser")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<EnvironmentalLabelCollar> getEnvironmentalLabelCollars() {
		return this.environmentalLabelCollars;
	}

	public void setEnvironmentalLabelCollars(
			Set<EnvironmentalLabelCollar> environmentalLabelCollars) {
		this.environmentalLabelCollars = environmentalLabelCollars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserByInspecDriverId")
	public Set<SteadyStateMethod> getSteadyStateMethodsForInspecDriverId() {
		return this.steadyStateMethodsForInspecDriverId;
	}

	public void setSteadyStateMethodsForInspecDriverId(
			Set<SteadyStateMethod> steadyStateMethodsForInspecDriverId) {
		this.steadyStateMethodsForInspecDriverId = steadyStateMethodsForInspecDriverId;
	}

}