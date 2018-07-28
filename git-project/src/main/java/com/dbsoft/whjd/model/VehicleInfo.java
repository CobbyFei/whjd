package com.dbsoft.whjd.model;

import javax.persistence.Entity;
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

@Entity
@Table(name = "vehicleGA", schema = "dbo", catalog = "whjd")
public class VehicleInfo implements java.io.Serializable{
	
	private Integer id;
	private String CLSBDH;
	private String HPZL;
	private String HPHM;
	private String CLXH;
	private String FDJH;
	private String CLLX;
	private String CCDJRQ;
	private String DJRQ;
	private String YXQZ;
	private String FDJXH;
	private String RLZL;
	private Double PL;
	private Double ZZL;
	private Double ZBZL;
	private Integer HDZK;
	private String PFBZ;
	private String FZJG;
	
	public VehicleInfo() {
	}
	
	public VehicleInfo(String cLSBDH, String hPZL, String hPHM, String cLXH, String fDJH, String cLLX,
			String cCDJRQ, String dJRQ, String yXQZ, String fDJXH, String rLZL, Double pL, Double zZL, Double zBZL,
			Integer hDZK, String pFBZ, String FZJG) {
		super();
		CLSBDH = cLSBDH;
		HPZL = hPZL;
		HPHM = hPHM;
		CLXH = cLXH;
		FDJH = fDJH;
		CLLX = cLLX;
		CCDJRQ = cCDJRQ;
		DJRQ = dJRQ;
		YXQZ = yXQZ;
		FDJXH = fDJXH;
		RLZL = rLZL;
		PL = pL;
		ZZL = zZL;
		ZBZL = zBZL;
		HDZK = hDZK;
		PFBZ = pFBZ;
	}
	
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
	
	@Column(name = "CLSBDH", length = 30)
	public String getCLSBDH() {
		return CLSBDH;
	}

	public void setCLSBDH(String cLSBDH) {
		CLSBDH = cLSBDH;
	}
	@Column(name = "HPZL", length = 10)
	public String getHPZL() {
		return HPZL;
	}

	public void setHPZL(String hPZL) {
		HPZL = hPZL;
	}
	@Column(name = "HPHM", length = 20)
	public String getHPHM() {
		return HPHM;
	}

	public void setHPHM(String hPHM) {
		HPHM = hPHM;
	}
	@Column(name = "CLXH", length = 40)
	public String getCLXH() {
		return CLXH;
	}

	public void setCLXH(String cLXH) {
		CLXH = cLXH;
	}
	@Column(name = "FDJH", length = 20)
	public String getFDJH() {
		return FDJH;
	}

	public void setFDJH(String fDJH) {
		FDJH = fDJH;
	}
	@Column(name = "CLLX", length = 10)
	public String getCLLX() {
		return CLLX;
	}

	public void setCLLX(String cLLX) {
		CLLX = cLLX;
	}
	@Column(name = "CCDJRQ", length = 10)
	public String getCCDJRQ() {
		return CCDJRQ;
	}

	public void setCCDJRQ(String cCDJRQ) {
		CCDJRQ = cCDJRQ;
	}
	@Column(name = "DJRQ", length = 10)
	public String getDJRQ() {
		return DJRQ;
	}

	public void setDJRQ(String dJRQ) {
		DJRQ = dJRQ;
	}
	@Column(name = "YXQZ", length = 10)
	public String getYXQZ() {
		return YXQZ;
	}

	public void setYXQZ(String yXQZ) {
		YXQZ = yXQZ;
	}
	@Column(name = "FDJXH", length = 100)
	public String getFDJXH() {
		return FDJXH;
	}

	public void setFDJXH(String fDJXH) {
		FDJXH = fDJXH;
	}
	@Column(name = "RLZL", length = 10)
	public String getRLZL() {
		return RLZL;
	}

	public void setRLZL(String rLZL) {
		RLZL = rLZL;
	}
	@Column(name = "PL")
	public Double getPL() {
		return PL;
	}

	public void setPL(Double pL) {
		PL = pL;
	}
	@Column(name = "ZZL")
	public Double getZZL() {
		return ZZL;
	}

	public void setZZL(Double zZL) {
		ZZL = zZL;
	}
	@Column(name = "ZBZL")
	public Double getZBZL() {
		return ZBZL;
	}

	public void setZBZL(Double zBZL) {
		ZBZL = zBZL;
	}
	@Column(name = "HDZK")
	public Integer getHDZK() {
		return HDZK;
	}

	public void setHDZK(Integer hDZK) {
		HDZK = hDZK;
	}
	@Column(name = "PFBZ", length = 10)
	public String getPFBZ() {
		return PFBZ;
	}

	public void setPFBZ(String pFBZ) {
		PFBZ = pFBZ;
	}
	@Column(name = "FZJG", length = 20)
	public String getFZJG() {
		return FZJG;
	}

	public void setFZJG(String fZJG) {
		FZJG = fZJG;
	}
	
	
	
	
	
	
	
	
	
	
}
