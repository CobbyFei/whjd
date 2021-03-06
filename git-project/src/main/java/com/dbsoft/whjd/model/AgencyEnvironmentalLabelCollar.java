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
 * AgencyEnvironmentalLabelCollar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AgencyEnvironmentalLabelCollar", schema = "dbo", catalog = "whjd")
public class AgencyEnvironmentalLabelCollar implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUser;
	private Timestamp collarTime;
	private Integer year;
	private Integer yellowLabelNum;
	private Integer greenLabelNum;
	private Integer status;
	private String remarks;

	// Constructors

	/** default constructor */
	public AgencyEnvironmentalLabelCollar() {
	}

	/** full constructor */
	public AgencyEnvironmentalLabelCollar(SysUser sysUser,
			Timestamp collarTime, Integer year, Integer yellowLabelNum,
			Integer greenLabelNum, Integer status, String remarks) {
		this.sysUser = sysUser;
		this.collarTime = collarTime;
		this.year = year;
		this.yellowLabelNum = yellowLabelNum;
		this.greenLabelNum = greenLabelNum;
		this.status = status;
		this.remarks = remarks;
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

	@Column(name = "collarTime", length = 23)
	public Timestamp getCollarTime() {
		return this.collarTime;
	}

	public void setCollarTime(Timestamp collarTime) {
		this.collarTime = collarTime;
	}

	@Column(name = "Year")
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "yellowLabelNum")
	public Integer getYellowLabelNum() {
		return this.yellowLabelNum;
	}

	public void setYellowLabelNum(Integer yellowLabelNum) {
		this.yellowLabelNum = yellowLabelNum;
	}

	@Column(name = "greenLabelNum")
	public Integer getGreenLabelNum() {
		return this.greenLabelNum;
	}

	public void setGreenLabelNum(Integer greenLabelNum) {
		this.greenLabelNum = greenLabelNum;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remarks", length = 50)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}