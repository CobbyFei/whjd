package com.dbsoft.whjd.model;

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
 * DetectionMethodReference entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "detectionMethodReference", schema = "dbo", catalog = "whjd")
public class DetectionMethodReference implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fuelType;
	private Double lengthMin;
	private Double weightMin;
	private String detectionMedhodName;
	private Double lengthMax;
	private Double weightMax;
	private Set<LimitValueReference> limitValueReferences = new HashSet<LimitValueReference>(
			0);

	// Constructors

	/** default constructor */
	public DetectionMethodReference() {
	}

	/** full constructor */
	public DetectionMethodReference(String fuelType, Double lengthMin,
			Double weightMin, String detectionMedhodName, Double lengthMax,
			Double weightMax, Set<LimitValueReference> limitValueReferences) {
		this.fuelType = fuelType;
		this.lengthMin = lengthMin;
		this.weightMin = weightMin;
		this.detectionMedhodName = detectionMedhodName;
		this.lengthMax = lengthMax;
		this.weightMax = weightMax;
		this.limitValueReferences = limitValueReferences;
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

	@Column(name = "fuelType", length = 50)
	public String getFuelType() {
		return this.fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(name = "lengthMin", precision = 53, scale = 0)
	public Double getLengthMin() {
		return this.lengthMin;
	}

	public void setLengthMin(Double lengthMin) {
		this.lengthMin = lengthMin;
	}

	@Column(name = "weightMin", precision = 53, scale = 0)
	public Double getWeightMin() {
		return this.weightMin;
	}

	public void setWeightMin(Double weightMin) {
		this.weightMin = weightMin;
	}

	@Column(name = "detectionMedhodName", length = 50)
	public String getDetectionMedhodName() {
		return this.detectionMedhodName;
	}

	public void setDetectionMedhodName(String detectionMedhodName) {
		this.detectionMedhodName = detectionMedhodName;
	}

	@Column(name = "lengthMax", precision = 53, scale = 0)
	public Double getLengthMax() {
		return this.lengthMax;
	}

	public void setLengthMax(Double lengthMax) {
		this.lengthMax = lengthMax;
	}

	@Column(name = "weightMax", precision = 53, scale = 0)
	public Double getWeightMax() {
		return this.weightMax;
	}

	public void setWeightMax(Double weightMax) {
		this.weightMax = weightMax;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detectionMethodReference")
	public Set<LimitValueReference> getLimitValueReferences() {
		return this.limitValueReferences;
	}

	public void setLimitValueReferences(
			Set<LimitValueReference> limitValueReferences) {
		this.limitValueReferences = limitValueReferences;
	}

}