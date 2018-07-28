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
 * LimitValueReference entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "limitValueReference", schema = "dbo", catalog = "whjd")
public class LimitValueReference implements java.io.Serializable {

	// Fields

	private Integer id;
	private DetectionMethodReference detectionMethodReference;
	private Timestamp minRegisterTime;
	private Timestamp maxRegisterTime;
	private Double minBaseQuality;
	private Double maxBaseQuality;
	private String airInletMode;
	private Integer strokes;
	private Integer wheelNums;
	private Double wtHcAsm5025;
	private Double wtHcAsm2540;
	private Double wtCoAsm5025;
	private Double wtCoAsm2540;
	private Double wtNoAsm5025;
	private Double wtNoAsm2540;
	private Double zjLimit;
	private Double sdsLambda;
	private Double sdsLCo;
	private Double sdsLHc;
	private Double sdsHCo;
	private Double sdsHHc;
	private Double jzLimit;
	private Double jzMaxRpm;
	private Integer vehicleDetailType;

	// Constructors

	/** default constructor */
	public LimitValueReference() {
	}

	/** full constructor */
	public LimitValueReference(
			DetectionMethodReference detectionMethodReference,
			Timestamp minRegisterTime, Timestamp maxRegisterTime,
			Double minBaseQuality, Double maxBaseQuality, String airInletMode,
			Integer strokes, Integer wheelNums, Double wtHcAsm5025,
			Double wtHcAsm2540, Double wtCoAsm5025, Double wtCoAsm2540,
			Double wtNoAsm5025, Double wtNoAsm2540, Double zjLimit,
			Double sdsLambda, Double sdsLCo, Double sdsLHc, Double sdsHCo,
			Double sdsHHc, Double jzLimit, Double jzMaxRpm,
			Integer vehicleDetailType) {
		this.detectionMethodReference = detectionMethodReference;
		this.minRegisterTime = minRegisterTime;
		this.maxRegisterTime = maxRegisterTime;
		this.minBaseQuality = minBaseQuality;
		this.maxBaseQuality = maxBaseQuality;
		this.airInletMode = airInletMode;
		this.strokes = strokes;
		this.wheelNums = wheelNums;
		this.wtHcAsm5025 = wtHcAsm5025;
		this.wtHcAsm2540 = wtHcAsm2540;
		this.wtCoAsm5025 = wtCoAsm5025;
		this.wtCoAsm2540 = wtCoAsm2540;
		this.wtNoAsm5025 = wtNoAsm5025;
		this.wtNoAsm2540 = wtNoAsm2540;
		this.zjLimit = zjLimit;
		this.sdsLambda = sdsLambda;
		this.sdsLCo = sdsLCo;
		this.sdsLHc = sdsLHc;
		this.sdsHCo = sdsHCo;
		this.sdsHHc = sdsHHc;
		this.jzLimit = jzLimit;
		this.jzMaxRpm = jzMaxRpm;
		this.vehicleDetailType = vehicleDetailType;
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
	@JoinColumn(name = "detectionMedhod")
	public DetectionMethodReference getDetectionMethodReference() {
		return this.detectionMethodReference;
	}

	public void setDetectionMethodReference(
			DetectionMethodReference detectionMethodReference) {
		this.detectionMethodReference = detectionMethodReference;
	}

	@Column(name = "minRegisterTime", length = 23)
	public Timestamp getMinRegisterTime() {
		return this.minRegisterTime;
	}

	public void setMinRegisterTime(Timestamp minRegisterTime) {
		this.minRegisterTime = minRegisterTime;
	}

	@Column(name = "maxRegisterTime", length = 23)
	public Timestamp getMaxRegisterTime() {
		return this.maxRegisterTime;
	}

	public void setMaxRegisterTime(Timestamp maxRegisterTime) {
		this.maxRegisterTime = maxRegisterTime;
	}

	@Column(name = "minBaseQuality", precision = 53, scale = 0)
	public Double getMinBaseQuality() {
		return this.minBaseQuality;
	}

	public void setMinBaseQuality(Double minBaseQuality) {
		this.minBaseQuality = minBaseQuality;
	}

	@Column(name = "maxBaseQuality", precision = 53, scale = 0)
	public Double getMaxBaseQuality() {
		return this.maxBaseQuality;
	}

	public void setMaxBaseQuality(Double maxBaseQuality) {
		this.maxBaseQuality = maxBaseQuality;
	}

	@Column(name = "airInletMode", length = 15)
	public String getAirInletMode() {
		return this.airInletMode;
	}

	public void setAirInletMode(String airInletMode) {
		this.airInletMode = airInletMode;
	}

	@Column(name = "strokes")
	public Integer getStrokes() {
		return this.strokes;
	}

	public void setStrokes(Integer strokes) {
		this.strokes = strokes;
	}

	@Column(name = "wheelNums")
	public Integer getWheelNums() {
		return this.wheelNums;
	}

	public void setWheelNums(Integer wheelNums) {
		this.wheelNums = wheelNums;
	}

	@Column(name = "WT_HC_ASM5025", precision = 53, scale = 0)
	public Double getWtHcAsm5025() {
		return this.wtHcAsm5025;
	}

	public void setWtHcAsm5025(Double wtHcAsm5025) {
		this.wtHcAsm5025 = wtHcAsm5025;
	}

	@Column(name = "WT_HC_ASM2540", precision = 53, scale = 0)
	public Double getWtHcAsm2540() {
		return this.wtHcAsm2540;
	}

	public void setWtHcAsm2540(Double wtHcAsm2540) {
		this.wtHcAsm2540 = wtHcAsm2540;
	}

	@Column(name = "WT_CO_ASM5025", precision = 53, scale = 0)
	public Double getWtCoAsm5025() {
		return this.wtCoAsm5025;
	}

	public void setWtCoAsm5025(Double wtCoAsm5025) {
		this.wtCoAsm5025 = wtCoAsm5025;
	}

	@Column(name = "WT_CO_ASM2540", precision = 53, scale = 0)
	public Double getWtCoAsm2540() {
		return this.wtCoAsm2540;
	}

	public void setWtCoAsm2540(Double wtCoAsm2540) {
		this.wtCoAsm2540 = wtCoAsm2540;
	}

	@Column(name = "WT_NO_ASM5025", precision = 53, scale = 0)
	public Double getWtNoAsm5025() {
		return this.wtNoAsm5025;
	}

	public void setWtNoAsm5025(Double wtNoAsm5025) {
		this.wtNoAsm5025 = wtNoAsm5025;
	}

	@Column(name = "WT_NO_ASM2540", precision = 53, scale = 0)
	public Double getWtNoAsm2540() {
		return this.wtNoAsm2540;
	}

	public void setWtNoAsm2540(Double wtNoAsm2540) {
		this.wtNoAsm2540 = wtNoAsm2540;
	}

	@Column(name = "ZJ_Limit", precision = 53, scale = 0)
	public Double getZjLimit() {
		return this.zjLimit;
	}

	public void setZjLimit(Double zjLimit) {
		this.zjLimit = zjLimit;
	}

	@Column(name = "SDS_lambda", precision = 53, scale = 0)
	public Double getSdsLambda() {
		return this.sdsLambda;
	}

	public void setSdsLambda(Double sdsLambda) {
		this.sdsLambda = sdsLambda;
	}

	@Column(name = "SDS_L_CO", precision = 53, scale = 0)
	public Double getSdsLCo() {
		return this.sdsLCo;
	}

	public void setSdsLCo(Double sdsLCo) {
		this.sdsLCo = sdsLCo;
	}

	@Column(name = "SDS_L_HC", precision = 53, scale = 0)
	public Double getSdsLHc() {
		return this.sdsLHc;
	}

	public void setSdsLHc(Double sdsLHc) {
		this.sdsLHc = sdsLHc;
	}

	@Column(name = "SDS_H_CO", precision = 53, scale = 0)
	public Double getSdsHCo() {
		return this.sdsHCo;
	}

	public void setSdsHCo(Double sdsHCo) {
		this.sdsHCo = sdsHCo;
	}

	@Column(name = "SDS_H_HC", precision = 53, scale = 0)
	public Double getSdsHHc() {
		return this.sdsHHc;
	}

	public void setSdsHHc(Double sdsHHc) {
		this.sdsHHc = sdsHHc;
	}

	@Column(name = "JZ_Limit", precision = 53, scale = 0)
	public Double getJzLimit() {
		return this.jzLimit;
	}

	public void setJzLimit(Double jzLimit) {
		this.jzLimit = jzLimit;
	}

	@Column(name = "JZ_MaxRpm", precision = 53, scale = 0)
	public Double getJzMaxRpm() {
		return this.jzMaxRpm;
	}

	public void setJzMaxRpm(Double jzMaxRpm) {
		this.jzMaxRpm = jzMaxRpm;
	}

	@Column(name = "vehicleDetailType")
	public Integer getVehicleDetailType() {
		return this.vehicleDetailType;
	}

	public void setVehicleDetailType(Integer vehicleDetailType) {
		this.vehicleDetailType = vehicleDetailType;
	}

}