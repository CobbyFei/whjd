package com.dbsoft.whjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "emissionAdmitStandard", schema = "dbo", catalog = "whjd")
public class EmissionAdmitStandard implements java.io.Serializable{
	
	private Integer ID;
	private String CLLB;
	private String YT;
	private Integer ZRBZ;
	private String SSSJ;
	
	public EmissionAdmitStandard(){
	}

	public EmissionAdmitStandard(Integer iD, String cLLB, String yT, Integer zRBZ, String sSSJ) {
		super();
		ID = iD;
		CLLB = cLLB;
		YT = yT;
		ZRBZ = zRBZ;
		SSSJ = sSSJ;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	
	@Column(name = "CLLB", length = 200)
	public String getCLLB() {
		return CLLB;
	}

	public void setCLLB(String cLLB) {
		CLLB = cLLB;
	}
	
	@Column(name = "YT", length = 200)
	public String getYT() {
		return YT;
	}

	public void setYT(String yT) {
		YT = yT;
	}
	
	@Column(name = "ZRBZ")
	public Integer getZRBZ() {
		return ZRBZ;
	}

	public void setZRBZ(Integer zRBZ) {
		ZRBZ = zRBZ;
	}
	
	@Column(name = "SSSJ", length = 200)
	public String getSSSJ() {
		return SSSJ;
	}

	public void setSSSJ(String sSSJ) {
		SSSJ = sSSJ;
	}
	
	
	
	
}
