package com.dbsoft.whjd.action;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbsoft.whjd.model.DevicePurchaseRecord;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.pageModel.DevicePurchaseRecordPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDevicePurchaseRecordService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;
@Action(value = "devicePurchaseRecordAction",results = {
		@Result(name="editDevicePurchaseRecord",location="/jsp/DevicePurchaseRecordManage/editDevicePurchaseRecord.jsp")
		})
public class DevicePurchaseRecordAction extends BaseAction implements ModelDriven<DevicePurchaseRecordPage> {
	private static final Logger logger = Logger.getLogger(DevicePurchaseRecordAction.class);

	private String q;
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	

	/********************************/
	private DevicePurchaseRecordPage devicePurchaseRecordPage = new DevicePurchaseRecordPage();
	private IDevicePurchaseRecordService devicePurchaseRecordService;
	/********************************/
	
	public IDevicePurchaseRecordService getDevicePurchaseRecordService() {
		return devicePurchaseRecordService;
	}
	@Resource(name = "devicePurchaseRecordService")
	public void setDevicePurchaseRecordService(
			IDevicePurchaseRecordService devicePurchaseRecordService) {
		this.devicePurchaseRecordService = devicePurchaseRecordService;
	}
	@Override
	public DevicePurchaseRecordPage getModel() {
		// TODO Auto-generated method stub
		return devicePurchaseRecordPage;
	}
	/*获得设备购买记录*/
	public void getAllDevicePurchaseRecord(){
		try { 
		super.writeJson(devicePurchaseRecordService.getDevicePurchaseRecord(devicePurchaseRecordPage));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}

	/*增加一条设备购买记录*/
	public void addDevicePurchaseRecord() {
		try {
			Json json=devicePurchaseRecordService.addDevicePurchaseRecord(devicePurchaseRecordPage);
			super.writeJson(json);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		
	}
	/*导出设备购买记录*/
	public void exportDevicePurchaseRecord() {
		Json res=new Json();
		res.setSuccess(true);
		try {
			res=devicePurchaseRecordService.exportDevicePurchaseRecord(devicePurchaseRecordPage);
			
		} catch (Exception e) {
			  logger.error(ExceptionUtil.getExceptionMessage(e));
			  res.setSuccess(false);
			  res.setMsg("导出发生异常");
		}
		super.writeJson(res);
	}

	public static Logger getLogger() {
		return logger;
	}

	/*修改选中的记录*/
	public void updateDevicePurchaseRecord(){
		Json json=new Json();
		try {
			super.writeJson(devicePurchaseRecordService.updateDevicePurchaseRecord(devicePurchaseRecordPage));
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("删除信息失败");
	    	super.writeJson(json);
		}
	}
	/*修改选中的记录*/
/*	public String devicePurchaseRecordEdit() {
		return 
	}*/
	/*删除选中的记录*/
	public void deleteDevicePurchaseRecord()
	{
		Json json=new Json();
		try {
		json=devicePurchaseRecordService.deleteDevicePurchaseRecord(devicePurchaseRecordPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("删除信息失败");
	}
		super.writeJson(json);
	}
	public String editDevicePurchaseRecord() throws Exception{
		return "editDevicePurchaseRecord";
	}
}
