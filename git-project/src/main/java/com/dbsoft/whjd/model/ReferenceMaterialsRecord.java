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
 * ReferenceMaterialsRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "referenceMaterialsRecord", schema = "dbo", catalog = "whjd")
public class ReferenceMaterialsRecord implements java.io.Serializable {

	// Fields

	private Integer recordId;
	private InspectionStation inspectionStation;
	private String materialName;
	private String manufacturer;
	private String specification;
	private Integer purchaseNum;
	private Timestamp purchaseTime;
	private String remarks;
	private Integer status;
	private String model;
	private Integer isPush;
	// Constructors

	/** default constructor */
	public ReferenceMaterialsRecord() {
	}

	/** full constructor */
	public ReferenceMaterialsRecord(InspectionStation inspectionStation,
			String materialName, String manufacturer, String specification,
			Integer purchaseNum, Timestamp purchaseTime, String remarks,
			Integer status, String model,Integer isPush) {
		this.inspectionStation = inspectionStation;
		this.materialName = materialName;
		this.manufacturer = manufacturer;
		this.specification = specification;
		this.purchaseNum = purchaseNum;
		this.purchaseTime = purchaseTime;
		this.remarks = remarks;
		this.status = status;
		this.model = model;
		this.isPush = isPush;
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
	@JoinColumn(name = "purchaseStation")
	public InspectionStation getInspectionStation() {
		return this.inspectionStation;
	}

	public void setInspectionStation(InspectionStation inspectionStation) {
		this.inspectionStation = inspectionStation;
	}

	@Column(name = "materialName", length = 50)
	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Column(name = "manufacturer", length = 50)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "specification", length = 50)
	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	@Column(name = "model", length = 30)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getIsPush() {
		return isPush;
	}
	@Column(name = "isPush" , nullable=false , columnDefinition="INT default 0")
	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}
	
	

}