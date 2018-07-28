package com.dbsoft.whjd.model;
// Generated 2018-2-12 14:13:31 by Hibernate Tools 3.6.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * RestoreSteadyStateMethodResult generated by hbm2java
 */
@Entity
@Table(name = "SteadyStateMethodBackup", schema = "dbo", catalog = "whjd")
public class SteadyStateMethodBackup implements java.io.Serializable {

	private int id;
	private String jcbgbh;
	private Float hc5025limit;
	private Float hc5025;
	private String hc5025judge;
	private Float co5025limit;
	private Float co5025;
	private String co5025judge;
	private Float no5025limit;
	private Float no5025;
	private String no5025judge;
	private Float fdjzs5025;
	private Float fdjyw5025;
	private Float hc2540limit;
	private Float hc2540;
	private String hc2540judge;
	private Float co2540limit;
	private Float co2540;
	private String co2540judge;
	private Float no2540limit;
	private Float no2540;
	private String no2540judge;
	private Float fdjzs2540;
	private Float fdjyw2540;

	public SteadyStateMethodBackup() {
	}

	public SteadyStateMethodBackup(int id, String jcbgbh, Float hc5025limit, Float hc5025, String hc5025judge,
			Float co5025limit, Float co5025, String co5025judge, Float no5025limit, Float no5025,
			String no5025judge, Float fdjzs5025, Float fdjyw5025, Float hc2540limit, Float hc2540,
			String hc2540judge, Float co2540limit, Float co2540, String co2540judge, Float no2540limit,
			Float no2540, String no2540judge, Float fdjzs2540, Float fdjyw2540) {
		this.id = id;
		this.jcbgbh = jcbgbh;
		this.hc5025limit = hc5025limit;
		this.hc5025 = hc5025;
		this.hc5025judge = hc5025judge;
		this.co5025limit = co5025limit;
		this.co5025 = co5025;
		this.co5025judge = co5025judge;
		this.no5025limit = no5025limit;
		this.no5025 = no5025;
		this.no5025judge = no5025judge;
		this.fdjzs5025 = fdjzs5025;
		this.fdjyw5025 = fdjyw5025;
		this.hc2540limit = hc2540limit;
		this.hc2540 = hc2540;
		this.hc2540judge = hc2540judge;
		this.co2540limit = co2540limit;
		this.co2540 = co2540;
		this.co2540judge = co2540judge;
		this.no2540limit = no2540limit;
		this.no2540 = no2540;
		this.no2540judge = no2540judge;
		this.fdjzs2540 = fdjzs2540;
		this.fdjyw2540 = fdjyw2540;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "JCBGBH", nullable = false, length = 21)
	public String getJcbgbh() {
		return this.jcbgbh;
	}

	public void setJcbgbh(String jcbgbh) {
		this.jcbgbh = jcbgbh;
	}

	@Column(name = "HC5025Limit", nullable = false, precision = 24, scale = 0)
	public Float getHc5025limit() {
		return this.hc5025limit;
	}

	public void setHc5025limit(Float hc5025limit) {
		this.hc5025limit = hc5025limit;
	}

	@Column(name = "HC5025", nullable = false, precision = 24, scale = 0)
	public Float getHc5025() {
		return this.hc5025;
	}

	public void setHc5025(Float hc5025) {
		this.hc5025 = hc5025;
	}

	@Column(name = "HC5025Judge", nullable = false, length = 1)
	public String getHc5025judge() {
		return this.hc5025judge;
	}

	public void setHc5025judge(String hc5025judge) {
		this.hc5025judge = hc5025judge;
	}

	@Column(name = "CO5025Limit", nullable = false, precision = 24, scale = 0)
	public Float getCo5025limit() {
		return this.co5025limit;
	}

	public void setCo5025limit(Float co5025limit) {
		this.co5025limit = co5025limit;
	}

	@Column(name = "CO5025", nullable = false, precision = 24, scale = 0)
	public Float getCo5025() {
		return this.co5025;
	}

	public void setCo5025(Float co5025) {
		this.co5025 = co5025;
	}

	@Column(name = "CO5025Judge", nullable = false, length = 1)
	public String getCo5025judge() {
		return this.co5025judge;
	}

	public void setCo5025judge(String co5025judge) {
		this.co5025judge = co5025judge;
	}

	@Column(name = "NO5025Limit", nullable = false, precision = 24, scale = 0)
	public Float getNo5025limit() {
		return this.no5025limit;
	}

	public void setNo5025limit(Float no5025limit) {
		this.no5025limit = no5025limit;
	}

	@Column(name = "NO5025", nullable = false, precision = 24, scale = 0)
	public Float getNo5025() {
		return this.no5025;
	}

	public void setNo5025(Float no5025) {
		this.no5025 = no5025;
	}

	@Column(name = "NO5025Judge", nullable = false, length = 1)
	public String getNo5025judge() {
		return this.no5025judge;
	}

	public void setNo5025judge(String no5025judge) {
		this.no5025judge = no5025judge;
	}

	@Column(name = "FDJZS5025", nullable = false, precision = 24, scale = 0)
	public Float getFdjzs5025() {
		return this.fdjzs5025;
	}

	public void setFdjzs5025(Float fdjzs5025) {
		this.fdjzs5025 = fdjzs5025;
	}

	@Column(name = "FDJYW5025", nullable = false, precision = 24, scale = 0)
	public Float getFdjyw5025() {
		return this.fdjyw5025;
	}

	public void setFdjyw5025(Float fdjyw5025) {
		this.fdjyw5025 = fdjyw5025;
	}

	@Column(name = "HC2540Limit", nullable = false, precision = 24, scale = 0)
	public Float getHc2540limit() {
		return this.hc2540limit;
	}

	public void setHc2540limit(Float hc2540limit) {
		this.hc2540limit = hc2540limit;
	}

	@Column(name = "HC2540", nullable = false, precision = 24, scale = 0)
	public Float getHc2540() {
		return this.hc2540;
	}

	public void setHc2540(Float hc2540) {
		this.hc2540 = hc2540;
	}

	@Column(name = "HC2540Judge", nullable = false, length = 1)
	public String getHc2540judge() {
		return this.hc2540judge;
	}

	public void setHc2540judge(String hc2540judge) {
		this.hc2540judge = hc2540judge;
	}

	@Column(name = "CO2540Limit", nullable = false, precision = 24, scale = 0)
	public Float getCo2540limit() {
		return this.co2540limit;
	}

	public void setCo2540limit(Float co2540limit) {
		this.co2540limit = co2540limit;
	}

	@Column(name = "CO2540", nullable = false, precision = 24, scale = 0)
	public Float getCo2540() {
		return this.co2540;
	}

	public void setCo2540(Float co2540) {
		this.co2540 = co2540;
	}

	@Column(name = "CO2540Judge", nullable = false, length = 1)
	public String getCo2540judge() {
		return this.co2540judge;
	}

	public void setCo2540judge(String co2540judge) {
		this.co2540judge = co2540judge;
	}

	@Column(name = "NO2540Limit", nullable = false, precision = 24, scale = 0)
	public Float getNo2540limit() {
		return this.no2540limit;
	}

	public void setNo2540limit(Float no2540limit) {
		this.no2540limit = no2540limit;
	}

	@Column(name = "NO2540", nullable = false, precision = 24, scale = 0)
	public Float getNo2540() {
		return this.no2540;
	}

	public void setNo2540(Float no2540) {
		this.no2540 = no2540;
	}

	@Column(name = "NO2540Judge", nullable = false, length = 1)
	public String getNo2540judge() {
		return this.no2540judge;
	}

	public void setNo2540judge(String no2540judge) {
		this.no2540judge = no2540judge;
	}

	@Column(name = "FDJZS2540", nullable = false, precision = 24, scale = 0)
	public Float getFdjzs2540() {
		return this.fdjzs2540;
	}

	public void setFdjzs2540(Float fdjzs2540) {
		this.fdjzs2540 = fdjzs2540;
	}

	@Column(name = "FDJYW2540", nullable = false, precision = 24, scale = 0)
	public Float getFdjyw2540() {
		return this.fdjyw2540;
	}

	public void setFdjyw2540(Float fdjyw2540) {
		this.fdjyw2540 = fdjyw2540;
	}

}
