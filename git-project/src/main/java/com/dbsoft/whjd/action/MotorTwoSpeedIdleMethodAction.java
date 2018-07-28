package com.dbsoft.whjd.action;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MotorTwoSpeedIdleMethodPage;
import com.dbsoft.whjd.pageModel.TwoSpeedIdleMethodPage;
import com.dbsoft.whjd.service.IMotorTwoSpeedIdleMethodService;
import com.dbsoft.whjd.service.IWebServiceDataService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "motorTwoSpeedIdleMethodAction",results = {
		@Result(name="editMotorTwoSpeedIdleMethod",location="/jsp/MotorTwoSpeedIdleMethodManage/modifyMotorTwoSpeedIdleMethod.jsp"),
		@Result(name="methodPrint",location="/jsp/MotorTwoSpeedIdleMethodManage/printMotorTwoSpeedIdleMethod.jsp")
		})
public class MotorTwoSpeedIdleMethodAction extends BaseAction implements ModelDriven<MotorTwoSpeedIdleMethodPage>{
	private String q;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	private MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage= new MotorTwoSpeedIdleMethodPage();
	private static final Logger logger = Logger.getLogger(InspectionStationAction.class);
    private IMotorTwoSpeedIdleMethodService iMotorTwoSpeedIdleMethodService;
	private IWebServiceDataService iWebServiceDataService;
	public IWebServiceDataService getiWebServiceDataService() {
		return iWebServiceDataService;
	}
	@Resource(name = "webServiceDataService")
	public void setiWebServiceDataService(IWebServiceDataService iWebServiceDataService) {
		this.iWebServiceDataService = iWebServiceDataService;
	}
	public IMotorTwoSpeedIdleMethodService getiMotorTwoSpeedIdleMethodService() {
		return iMotorTwoSpeedIdleMethodService;
	}
	@Resource(name = "motorTwoSpeedIdleMethodService")
	public void setiMotorTwoSpeedIdleMethodService(IMotorTwoSpeedIdleMethodService iMotorTwoSpeedIdleMethodService) {
		this.iMotorTwoSpeedIdleMethodService = iMotorTwoSpeedIdleMethodService;
	}
	public static Logger getLogger() {
		return logger;
	}
	@Override
	public MotorTwoSpeedIdleMethodPage getModel() {
		// TODO Auto-generated method stub
		return motorTwoSpeedIdleMethodPage;
	}
	
	//批量下结论******************************
	public void batchConclusion()
	{
		Json json=new Json();
		try {
		json=iMotorTwoSpeedIdleMethodService.batchConclusion(motorTwoSpeedIdleMethodPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("批量下结论失败");
	}
		super.writeJson(json);
	}
	public void getMotorTwoSpeedIdleMethod() {       // 获取摩托车双怠速法数据信息
		Json json=new Json();
		try {
			super.writeJson(iMotorTwoSpeedIdleMethodService.getMotorTwoSpeedIdleMethod(motorTwoSpeedIdleMethodPage));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("获取摩托车双怠速法数据失败");
		}
	}
	public void updateMotorTwoSpeedIdleMethod() {
		//System.out.println(twoSpeedIdleMethodPage.getSysUserByInspecDriverId());
		// 更新双怠速法数据信息
		Json json=new Json();
		try {
			json=iMotorTwoSpeedIdleMethodService.updateMotorTwoSpeedIdleMethod(motorTwoSpeedIdleMethodPage);
			json.setSuccess(true);
			json.setMsg("修改摩托车双怠速法数据成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("修改摩托车双怠速法数据失败");
		}
		super.writeJson(json);
	}
	public String editMotorTwoSpeedIdleMethod() {//跳转编辑页面
		return "editMotorTwoSpeedIdleMethod";		
	}
	
	/**
	 * 打印一个检测报告（包括检测数据）
	 */
	public String methodPrint() {
		HttpServletRequest req = ServletActionContext.getRequest();
		MotorTwoSpeedIdleMethodPage page = iMotorTwoSpeedIdleMethodService.getDetectionById(motorTwoSpeedIdleMethodPage.getId());
		req.getSession().setAttribute("methodPage", page);
		return "methodPrint";
	}
	
	/*
	 * 开始检测
	 */
	public void beginDetection() {
		// 开始检测双怠速法数据信息
		Json json=new Json();
		try {
			json=iMotorTwoSpeedIdleMethodService.beginDetection(motorTwoSpeedIdleMethodPage);
			if(json.isSuccess())
			{
				json=iWebServiceDataService.addWebServiceData(motorTwoSpeedIdleMethodPage.getId());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("添加检测任务失败");
		}
		super.writeJson(json);
	}
}
