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
 * MotorTwoSpeedIdleMethod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "motorTwoSpeedIdleMethod", schema = "dbo", catalog = "whjd")
public class MotorTwoSpeedIdleMethod implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUser;
	private DetectionLine detectionLine;
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
	private String experimentAddress;
	private Timestamp experimentTime;
	private Double HCoresult;
	private Double HCo2result;
	private Double HHcresult;
	private Double coresult;
	private Double co2result;
	private Double hcresult;
	private Double HCoreviseResult;
	private Double coreviseResult;
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
	private Integer wheelNums;
	private Set<DetectionCommisionSheet> detectionCommisionSheets = new HashSet<DetectionCommisionSheet>(
			0);

	// Constructors

	/** default constructor */
	public MotorTwoSpeedIdleMethod() {
	}

	/** full constructor */
	public MotorTwoSpeedIdleMethod(SysUser sysUser,
			DetectionLine detectionLine, String vehicleManufacturer,
			Integer strokes, Double maxRpm, Double idleSpeedRpm,
			Double highIdleSpeedRpm, String fuelSpecification,
			String lubeSpecification, String fuelSupplyStyle,
			String fuelJetSystem, String pollutionControlDevice,
			String exhaustAnalyzerModel, String tachometerModel,
			Double airPressure, Double temperature, Double humidity,
			String experimentAddress, Timestamp experimentTime,
			Double HCoresult, Double HCo2result, Double HHcresult,
			Double coresult, Double co2result, Double hcresult,
			Double HCoreviseResult, Double coreviseResult,
			Double HCoroundResult, Double HCo2roundResult,
			Double HHcroundResult, Double coroundResult, Double co2roundResult,
			Double hcroundResult, Integer reportStatus, Double colimit,
			Double hclimit, Double HColimit, Double HHclimit,
			Integer wheelNums,
			Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.sysUser = sysUser;
		this.detectionLine = detectionLine;
		this.vehicleManufacturer = vehicleManufacturer;
		this.strokes = strokes;
		this.maxRpm = maxRpm;
		this.idleSpeedRpm = idleSpeedRpm;
		this.highIdleSpeedRpm = highIdleSpeedRpm;
		this.fuelSpecification = fuelSpecification;
		this.lubeSpecification = lubeSpecification;
		this.fuelSupplyStyle = fuelSupplyStyle;
		this.fuelJetSystem = fuelJetSystem;
		this.pollutionControlDevice = pollutionControlDevice;
		this.exhaustAnalyzerModel = exhaustAnalyzerModel;
		this.tachometerModel = tachometerModel;
		this.airPressure = airPressure;
		this.temperature = temperature;
		this.humidity = humidity;
		this.experimentAddress = experimentAddress;
		this.experimentTime = experimentTime;
		this.HCoresult = HCoresult;
		this.HCo2result = HCo2result;
		this.HHcresult = HHcresult;
		this.coresult = coresult;
		this.co2result = co2result;
		this.hcresult = hcresult;
		this.HCoreviseResult = HCoreviseResult;
		this.coreviseResult = coreviseResult;
		this.HCoroundResult = HCoroundResult;
		this.HCo2roundResult = HCo2roundResult;
		this.HHcroundResult = HHcroundResult;
		this.coroundResult = coroundResult;
		this.co2roundResult = co2roundResult;
		this.hcroundResult = hcroundResult;
		this.reportStatus = reportStatus;
		this.colimit = colimit;
		this.hclimit = hclimit;
		this.HColimit = HColimit;
		this.HHclimit = HHclimit;
		this.wheelNums = wheelNums;
		this.detectionCommisionSheets = detectionCommisionSheets;
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
	@JoinColumn(name = "userID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lineID")
	public DetectionLine getDetectionLine() {
		return this.detectionLine;
	}

	public void setDetectionLine(DetectionLine detectionLine) {
		this.detectionLine = detectionLine;
	}

	@Column(name = "vehicleManufacturer", length = 50)
	public String getVehicleManufacturer() {
		return this.vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	@Column(name = "strokes")
	public Integer getStrokes() {
		return this.strokes;
	}

	public void setStrokes(Integer strokes) {
		this.strokes = strokes;
	}

	@Column(name = "maxRpm", precision = 53, scale = 0)
	public Double getMaxRpm() {
		return this.maxRpm;
	}

	public void setMaxRpm(Double maxRpm) {
		this.maxRpm = maxRpm;
	}

	@Column(name = "idleSpeedRpm", precision = 53, scale = 0)
	public Double getIdleSpeedRpm() {
		return this.idleSpeedRpm;
	}

	public void setIdleSpeedRpm(Double idleSpeedRpm) {
		this.idleSpeedRpm = idleSpeedRpm;
	}

	@Column(name = "highIdleSpeedRpm", precision = 53, scale = 0)
	public Double getHighIdleSpeedRpm() {
		return this.highIdleSpeedRpm;
	}

	public void setHighIdleSpeedRpm(Double highIdleSpeedRpm) {
		this.highIdleSpeedRpm = highIdleSpeedRpm;
	}

	@Column(name = "fuelSpecification", length = 30)
	public String getFuelSpecification() {
		return this.fuelSpecification;
	}

	public void setFuelSpecification(String fuelSpecification) {
		this.fuelSpecification = fuelSpecification;
	}

	@Column(name = "lubeSpecification", length = 30)
	public String getLubeSpecification() {
		return this.lubeSpecification;
	}

	public void setLubeSpecification(String lubeSpecification) {
		this.lubeSpecification = lubeSpecification;
	}

	@Column(name = "fuelSupplyStyle", length = 15)
	public String getFuelSupplyStyle() {
		return this.fuelSupplyStyle;
	}

	public void setFuelSupplyStyle(String fuelSupplyStyle) {
		this.fuelSupplyStyle = fuelSupplyStyle;
	}

	@Column(name = "fuelJetSystem", length = 20)
	public String getFuelJetSystem() {
		return this.fuelJetSystem;
	}

	public void setFuelJetSystem(String fuelJetSystem) {
		this.fuelJetSystem = fuelJetSystem;
	}

	@Column(name = "pollutionControlDevice", length = 50)
	public String getPollutionControlDevice() {
		return this.pollutionControlDevice;
	}

	public void setPollutionControlDevice(String pollutionControlDevice) {
		this.pollutionControlDevice = pollutionControlDevice;
	}

	@Column(name = "exhaustAnalyzerModel", length = 20)
	public String getExhaustAnalyzerModel() {
		return this.exhaustAnalyzerModel;
	}

	public void setExhaustAnalyzerModel(String exhaustAnalyzerModel) {
		this.exhaustAnalyzerModel = exhaustAnalyzerModel;
	}

	@Column(name = "tachometerModel", length = 20)
	public String getTachometerModel() {
		return this.tachometerModel;
	}

	public void setTachometerModel(String tachometerModel) {
		this.tachometerModel = tachometerModel;
	}

	@Column(name = "airPressure", precision = 53, scale = 0)
	public Double getAirPressure() {
		return this.airPressure;
	}

	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	@Column(name = "temperature", precision = 53, scale = 0)
	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	@Column(name = "humidity", precision = 53, scale = 0)
	public Double getHumidity() {
		return this.humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	@Column(name = "experimentAddress", length = 40)
	public String getExperimentAddress() {
		return this.experimentAddress;
	}

	public void setExperimentAddress(String experimentAddress) {
		this.experimentAddress = experimentAddress;
	}

	@Column(name = "experimentTime", length = 23)
	public Timestamp getExperimentTime() {
		return this.experimentTime;
	}

	public void setExperimentTime(Timestamp experimentTime) {
		this.experimentTime = experimentTime;
	}

	@Column(name = "H_COResult", precision = 53, scale = 0)
	public Double getHCoresult() {
		return this.HCoresult;
	}

	public void setHCoresult(Double HCoresult) {
		this.HCoresult = HCoresult;
	}

	@Column(name = "H_CO2Result", precision = 53, scale = 0)
	public Double getHCo2result() {
		return this.HCo2result;
	}

	public void setHCo2result(Double HCo2result) {
		this.HCo2result = HCo2result;
	}

	@Column(name = "H_HCResult", precision = 53, scale = 0)
	public Double getHHcresult() {
		return this.HHcresult;
	}

	public void setHHcresult(Double HHcresult) {
		this.HHcresult = HHcresult;
	}

	@Column(name = "COResult", precision = 53, scale = 0)
	public Double getCoresult() {
		return this.coresult;
	}

	public void setCoresult(Double coresult) {
		this.coresult = coresult;
	}

	@Column(name = "CO2Result", precision = 53, scale = 0)
	public Double getCo2result() {
		return this.co2result;
	}

	public void setCo2result(Double co2result) {
		this.co2result = co2result;
	}

	@Column(name = "HCResult", precision = 53, scale = 0)
	public Double getHcresult() {
		return this.hcresult;
	}

	public void setHcresult(Double hcresult) {
		this.hcresult = hcresult;
	}

	@Column(name = "H_COReviseResult", precision = 53, scale = 0)
	public Double getHCoreviseResult() {
		return this.HCoreviseResult;
	}

	public void setHCoreviseResult(Double HCoreviseResult) {
		this.HCoreviseResult = HCoreviseResult;
	}

	@Column(name = "COReviseResult", precision = 53, scale = 0)
	public Double getCoreviseResult() {
		return this.coreviseResult;
	}

	public void setCoreviseResult(Double coreviseResult) {
		this.coreviseResult = coreviseResult;
	}

	@Column(name = "H_CORoundResult", precision = 53, scale = 0)
	public Double getHCoroundResult() {
		return this.HCoroundResult;
	}

	public void setHCoroundResult(Double HCoroundResult) {
		this.HCoroundResult = HCoroundResult;
	}

	@Column(name = "H_CO2RoundResult", precision = 53, scale = 0)
	public Double getHCo2roundResult() {
		return this.HCo2roundResult;
	}

	public void setHCo2roundResult(Double HCo2roundResult) {
		this.HCo2roundResult = HCo2roundResult;
	}

	@Column(name = "H_HCRoundResult", precision = 53, scale = 0)
	public Double getHHcroundResult() {
		return this.HHcroundResult;
	}

	public void setHHcroundResult(Double HHcroundResult) {
		this.HHcroundResult = HHcroundResult;
	}

	@Column(name = "CORoundResult", precision = 53, scale = 0)
	public Double getCoroundResult() {
		return this.coroundResult;
	}

	public void setCoroundResult(Double coroundResult) {
		this.coroundResult = coroundResult;
	}

	@Column(name = "CO2RoundResult", precision = 53, scale = 0)
	public Double getCo2roundResult() {
		return this.co2roundResult;
	}

	public void setCo2roundResult(Double co2roundResult) {
		this.co2roundResult = co2roundResult;
	}

	@Column(name = "HCRoundResult", precision = 53, scale = 0)
	public Double getHcroundResult() {
		return this.hcroundResult;
	}

	public void setHcroundResult(Double hcroundResult) {
		this.hcroundResult = hcroundResult;
	}

	@Column(name = "reportStatus")
	public Integer getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	@Column(name = "COLimit", precision = 53, scale = 0)
	public Double getColimit() {
		return this.colimit;
	}

	public void setColimit(Double colimit) {
		this.colimit = colimit;
	}

	@Column(name = "HCLimit", precision = 53, scale = 0)
	public Double getHclimit() {
		return this.hclimit;
	}

	public void setHclimit(Double hclimit) {
		this.hclimit = hclimit;
	}

	@Column(name = "H_COLimit", precision = 53, scale = 0)
	public Double getHColimit() {
		return this.HColimit;
	}

	public void setHColimit(Double HColimit) {
		this.HColimit = HColimit;
	}

	@Column(name = "H_HCLimit", precision = 53, scale = 0)
	public Double getHHclimit() {
		return this.HHclimit;
	}

	public void setHHclimit(Double HHclimit) {
		this.HHclimit = HHclimit;
	}

	@Column(name = "wheelNums")
	public Integer getWheelNums() {
		return this.wheelNums;
	}

	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "motorTwoSpeedIdleMethod")
	public Set<DetectionCommisionSheet> getDetectionCommisionSheets() {
		return this.detectionCommisionSheets;
	}

	public void setDetectionCommisionSheets(
			Set<DetectionCommisionSheet> detectionCommisionSheets) {
		this.detectionCommisionSheets = detectionCommisionSheets;
	}

}