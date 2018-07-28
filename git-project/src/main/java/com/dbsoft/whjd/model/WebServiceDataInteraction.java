package com.dbsoft.whjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * WebServiceDataInteraction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "webServiceDataInteraction", schema = "dbo", catalog = "whjd")
public class WebServiceDataInteraction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer csId;
	private Integer reportId;
	private String methodName;
	private Integer stationId;
	private Integer lineLocalId;
	private Integer status;
	private String licence;

	// Constructors

	/** default constructor */
	public WebServiceDataInteraction() {
	}

	/** full constructor */
	public WebServiceDataInteraction(Integer csId, Integer reportId,
			String methodName, Integer stationId, Integer lineLocalId,
			Integer status, String licence) {
		this.csId = csId;
		this.reportId = reportId;
		this.methodName = methodName;
		this.stationId = stationId;
		this.lineLocalId = lineLocalId;
		this.status = status;
		this.licence = licence;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "csId")
	public Integer getCsId() {
		return this.csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	@Column(name = "reportId")
	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	@Column(name = "methodName", length = 20)
	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Column(name = "stationId")
	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	@Column(name = "lineLocalId")
	public Integer getLineLocalId() {
		return this.lineLocalId;
	}

	public void setLineLocalId(Integer lineLocalId) {
		this.lineLocalId = lineLocalId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "licence", length = 20)
	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

}