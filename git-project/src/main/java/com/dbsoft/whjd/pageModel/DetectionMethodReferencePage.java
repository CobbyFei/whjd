package com.dbsoft.whjd.pageModel;

public class DetectionMethodReferencePage {
	  private String q;
	  private int page;
	  private int rows;
	  private Integer id;
	  private String ids;
	  private String fuelType;
	  private double lengthMin;
	  private double weightMin;
      private String detectionMedhodName;
	  private double lengthMax;
	  private double weightMax;
	  private double vehicleLength;
	  private double maxTotalQuality;
	  
	public double getVehicleLength() {
		return vehicleLength;
	}
	public void setVehicleLength(double vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public double getMaxTotalQuality() {
		return maxTotalQuality;
	}
	public void setMaxTotalQuality(double maxTotalQuality) {
		this.maxTotalQuality = maxTotalQuality;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public double getLengthMin() {
		return lengthMin;
	}
	public void setLengthMin(double lengthMin) {
		this.lengthMin = lengthMin;
	}
	public double getWeightMin() {
		return weightMin;
	}
	public void setWeightMin(double weightMin) {
		this.weightMin = weightMin;
	}
	public String getDetectionMedhodName() {
		return detectionMedhodName;
	}
	public void setDetectionMedhodName(String detectionMedhodName) {
		this.detectionMedhodName = detectionMedhodName;
	}
	public double getLengthMax() {
		return lengthMax;
	}
	public void setLengthMax(double lengthMax) {
		this.lengthMax = lengthMax;
	}
	public double getWeightMax() {
		return weightMax;
	}
	public void setWeightMax(double weightMax) {
		this.weightMax = weightMax;
	}
	
}
