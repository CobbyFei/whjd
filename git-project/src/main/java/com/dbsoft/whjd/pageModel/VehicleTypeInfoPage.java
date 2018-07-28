package com.dbsoft.whjd.pageModel;

public class VehicleTypeInfoPage {
	
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
	private String fuelType;
	private String maxQuality;
	private String application;
	
	//2017-12-20添加
	private String ids;
	private String beginTime;
	private String endTime;
	/**
	 * 第几页数据
	 */
	private Integer page;
	/**
	 * 每页记录数
	 */
	private Integer rows;
	
	
	public String getCLXH() {
		return CLXH;
	}
	public void setCLXH(String cLXH) {
		CLXH = cLXH;
	}
	public String getFDJXH() {
		return FDJXH;
	}
	public void setFDJXH(String fDJXH) {
		FDJXH = fDJXH;
	}
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getMaxQuality() {
		return maxQuality;
	}
	public void setMaxQuality(String maxQuality) {
		this.maxQuality = maxQuality;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}

	
	public String getCLMC() {
		return CLMC;
	}
	public void setCLMC(String cLMC) {
		CLMC = cLMC;
	}
	public String getFDJSCC() {
		return FDJSCC;
	}
	public void setFDJSCC(String fDJSCC) {
		FDJSCC = fDJSCC;
	}
	public String getMANUF() {
		return MANUF;
	}
	public void setMANUF(String mANUF) {
		MANUF = mANUF;
	}
	public String getCLSB() {
		return CLSB;
	}
	public void setCLSB(String cLSB) {
		CLSB = cLSB;
	}
	public String getPF() {
		return PF;
	}
	public void setPF(String pF) {
		PF = pF;
	}
	public String getCLLB() {
		return CLLB;
	}
	public void setCLLB(String cLLB) {
		CLLB = cLLB;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public String toJSONString() {
		return "{\"CLXH\":\"" + CLXH + "\", \"FDJXH\":\"" + FDJXH + "\", \"FILENAME\":\"" + FILENAME + "\", \"fuelType\":\""
				+ fuelType + "\", \"maxQuality\":\"" + maxQuality + "\", \"application\":\"" + application + "\",";
	}
	
	
	//2017-12-20添加以下
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
}
