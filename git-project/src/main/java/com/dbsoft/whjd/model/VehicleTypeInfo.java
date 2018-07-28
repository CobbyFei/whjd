package com.dbsoft.whjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "O_VEHICLES", schema = "dbo", catalog = "whjd")
public class VehicleTypeInfo implements java.io.Serializable{
	private Integer ID;
	private String CLXH;
	private String CLMC;
	private String FDJXH;
	private String FDJSCC;
	private String MANUF;
	private String CLSB;
	private String FILENAME;
	private String PF;
	private String CLLB;
	private String VIN;
	private String BZ;
	
	
	public VehicleTypeInfo(){
	}

	public VehicleTypeInfo(Integer iD, String cLXH, String cLMC, String fDJXH, String fDJSCC, String mANUF, String cLSB,
			String fILENAME, String pF, String cLLB, String vIN, String bZ) {
		super();
		ID = iD;
		CLXH = cLXH;
		CLMC = cLMC;
		FDJXH = fDJXH;
		FDJSCC = fDJSCC;
		MANUF = mANUF;
		CLSB = cLSB;
		FILENAME = fILENAME;
		PF = pF;
		CLLB = cLLB;
		VIN = vIN;
		BZ = bZ;
		
		
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
	@Column(name = "CLXH", length = 200)
	public String getCLXH() {
		return CLXH;
	}

	public void setCLXH(String cLXH) {
		CLXH = cLXH;
	}
	@Column(name = "CLMC", length = 200)
	public String getCLMC() {
		return CLMC;
	}

	public void setCLMC(String cLMC) {
		CLMC = cLMC;
	}
	@Column(name = "FDJXH", length = 200)
	public String getFDJXH() {
		return FDJXH;
	}

	public void setFDJXH(String fDJXH) {
		FDJXH = fDJXH;
	}
	@Column(name = "FDJSCC", length = 200)
	public String getFDJSCC() {
		return FDJSCC;
	}

	public void setFDJSCC(String fDJSCC) {
		FDJSCC = fDJSCC;
	}
	@Column(name = "MANUF", length = 200)
	public String getMANUF() {
		return MANUF;
	}

	public void setMANUF(String mANUF) {
		MANUF = mANUF;
	}
	@Column(name = "CLSB", length = 100)
	public String getCLSB() {
		return CLSB;
	}

	public void setCLSB(String cLSB) {
		CLSB = cLSB;
	}
	@Column(name = "FILENAME", length = 100)
	public String getFILENAME() {
		return FILENAME;
	}

	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}
	@Column(name = "PF", length = 10)
	public String getPF() {
		return PF;
	}

	public void setPF(String pF) {
		PF = pF;
	}
	@Column(name = "CLLB", length = 50)
	public String getCLLB() {
		return CLLB;
	}

	public void setCLLB(String cLLB) {
		CLLB = cLLB;
	}
	@Column(name = "VIN", length = 250)
	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}
	@Column(name = "BZ", length = 255)
	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bZ) {
		BZ = bZ;
	}
	
	
}
