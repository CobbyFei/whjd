package com.dbsoft.whjd.pageModel;

/**
 * 培训以及考核模块的pageModel。
 * @author mark
 */
public class TrainingRecordPage {
	
	private Integer trainingRecordId;
	
	private Integer userId;
	
	private String  userName;
	
	private Integer userStatus;
	
	private String strTrainingTime;
	
	private String trainingAddress;
	
	private Integer trainingType;
	
	private String trainingDetail;
	
	private Integer stationId;
	
	private String stationName;
	
	private Integer stationStatus;
	
	private Integer trainingRecordStatus;
	
	private String ids;
	
	
	private Integer changeToStatus;
	
	/**
	 * 排序列的名称，在pageModel中的列
	 */
	private String sort;
	
	/**
	 * 升序还是降序
	 */
	private String order;
	/**
	 * 第几页数据
	 */
	private Integer page;
	
	/**
	 * 每页记录数
	 */
	private Integer rows;
	
	
	@Override
	public  String toString(){
		String str = "trainingRecordId: "+ trainingRecordId +"userId: "+userId +"userName: " +userName 
				+"strTrainingTime: " +strTrainingTime + "trainingAddress: " + trainingAddress
				+"traningType:" + trainingType + "trainingDetail: " + trainingDetail;
		return str; 
	}
	public Integer getTrainingRecordId() {
		return trainingRecordId;
	}
	public void setTrainingRecordId(Integer trainingRecordId) {
		this.trainingRecordId = trainingRecordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getStrTrainingTime() {
		return strTrainingTime;
	}

	public void setStrTrainingTime(String strTrainingTime) {
		this.strTrainingTime = strTrainingTime;
	}

	public String getTrainingAddress() {
		return trainingAddress;
	}


	public void setTrainingAddress(String trainingAddress) {
		this.trainingAddress = trainingAddress;
	}

	public Integer getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(Integer trainingType) {
		this.trainingType = trainingType;
	}

	public String getTrainingDetail() {
		return trainingDetail;
	}

	public void setTrainingDetail(String trainingDetail) {
		this.trainingDetail = trainingDetail;
	}
	
	public Integer getStationStatus() {
		return stationStatus;
	}
	public void setStationStatus(Integer stationStatus) {
		this.stationStatus = stationStatus;
	}
	
	public Integer getTrainingRecordStatus() {
		return trainingRecordStatus;
	}
	public void setTrainingRecordStatus(Integer trainingRecordStatus) {
		this.trainingRecordStatus = trainingRecordStatus;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Integer getChangeToStatus() {
		return changeToStatus;
	}
	public void setChangeToStatus(Integer changeToStatus) {
		this.changeToStatus = changeToStatus;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
	
	
}
