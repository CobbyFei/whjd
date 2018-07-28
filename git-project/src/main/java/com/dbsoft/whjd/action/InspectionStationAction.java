package com.dbsoft.whjd.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IInspectionStationService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "inspectionStationAction",results = {
		@Result(name="editInspectionStation",location="/jsp/InspectionStationManage/modifyInspectionStation.jsp")
		})
public class InspectionStationAction extends BaseAction implements ModelDriven<InspectionStationPage> {

	private String q;

	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}


	private static final Logger logger = Logger.getLogger(InspectionStationAction.class);

	private InspectionStationPage inspectionStationPage = new InspectionStationPage();
	private IInspectionStationService inspectionStationService;

	public IInspectionStationService getInspectionStationService() {
		return inspectionStationService;
	}

	@Resource(name = "inspectionStationService")
	public void setInspectionStationService(IInspectionStationService inspectionStationService) {
		this.inspectionStationService = inspectionStationService;
	}

	@Override
	public InspectionStationPage getModel() {
		return inspectionStationPage;
	}

	public static Logger getLogger() {
		return logger;
	}
	public void getInspectionStation() {       // 获取检测站
		try {
			super.writeJson(inspectionStationService.getInspectionStation(inspectionStationPage));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		
	}
	public void getInspectionStationName() {     //模糊查询检测站名字用
		try {
			super.writeJson(inspectionStationService.getInspectionStationName(q));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}
	public void cancelInspectionStation() {       // 删除检测站
		Json json=new Json();
		try {
		json=inspectionStationService.cancelInspectionStation(inspectionStationPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("删除检测站失败");
	}
		super.writeJson(json);
	}
	public void addInspectionStation() {       // 添加检测站
		try {
			super.writeJson(inspectionStationService.addInspectionStation(inspectionStationPage));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}
	public void updateInspectionStation() {       // 更新检测站信息
		try {
			super.writeJson(inspectionStationService.updateInspectionStation(inspectionStationPage));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}
	public String editInspectionStation() throws Exception{       // 转到编辑页面
         return "editInspectionStation";
	}
	public void hasInspectionStation() {
		try {
			super.writeJson(inspectionStationService.hasInspectionStation(q));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}
}
