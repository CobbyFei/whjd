package com.dbsoft.whjd.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * BlackNameList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "blackNameList", schema = "dbo", catalog = "whjd")
public class BlackNameList implements java.io.Serializable {

	// Fields

	private Integer id;
	private String licence;
	private String licenceColor;
	private String violationType;
	private Timestamp violationTime;
	private String violationAddress;
	private Boolean isPunished;
	private Boolean isCancel;
	private String remarks;
	private Integer isPush;

	// Constructors

	/** default constructor */
	public BlackNameList() {
	}

	/** full constructor */
	public BlackNameList(String licence, String violationType,
			Timestamp violationTime, String violationAddress,
			Boolean isPunished, Boolean isCancel, String remarks,
			Integer isPush, String licenceColor) {
		this.licence = licence;
		this.licenceColor = licenceColor;
		this.violationType = violationType;
		this.violationTime = violationTime;
		this.violationAddress = violationAddress;
		this.isPunished = isPunished;
		this.isCancel = isCancel;
		this.remarks = remarks;
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

	@Column(name = "licence", length = 10)
	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}
	
	@Column(name = "licenceColor", length = 10)
	public String getLicenceColor() {
		return licenceColor;
	}

	public void setLicenceColor(String licenceColor) {
		this.licenceColor = licenceColor;
	}

	@Column(name = "violationType", length = 50)
	public String getViolationType() {
		return this.violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	@Column(name = "violationTime", length = 23)
	public Timestamp getViolationTime() {
		return this.violationTime;
	}

	public void setViolationTime(Timestamp violationTime) {
		this.violationTime = violationTime;
	}

	@Column(name = "violationAddress", length = 100)
	public String getViolationAddress() {
		return this.violationAddress;
	}

	public void setViolationAddress(String violationAddress) {
		this.violationAddress = violationAddress;
	}

	@Column(name = "isPunished")
	public Boolean getIsPunished() {
		return this.isPunished;
	}

	public void setIsPunished(Boolean isPunished) {
		this.isPunished = isPunished;
	}

	@Column(name = "isCancel")
	public Boolean getIsCancel() {
		return this.isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	@Column(name = "remarks", length = 100)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "isPush")
	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}
	
	

}