package com.dbsoft.whjd.pageModel;

public class EmissionAdmitStandardPage {
	private Integer ID;
	private String CLLB;
	private String YT;
	private Integer ZRBZ;
	private String SSSJ;
	private String ids;
	
	/**
	 * 第几页数据
	 */
	private Integer page;
	
	/**
	 * 每页记录数
	 */
	private Integer rows;
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getCLLB() {
		return CLLB;
	}
	public void setCLLB(String cLLB) {
		CLLB = cLLB;
	}
	public String getYT() {
		return YT;
	}
	public void setYT(String yT) {
		YT = yT;
	}
	public Integer getZRBZ() {
		return ZRBZ;
	}
	public void setZRBZ(Integer zRBZ) {
		ZRBZ = zRBZ;
	}
	public String getSSSJ() {
		return SSSJ;
	}
	public void setSSSJ(String sSSJ) {
		SSSJ = sSSJ;
	}
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
