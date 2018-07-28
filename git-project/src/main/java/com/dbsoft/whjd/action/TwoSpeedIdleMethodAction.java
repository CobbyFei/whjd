package com.dbsoft.whjd.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TwoSpeedIdleMethodPage;
import com.dbsoft.whjd.service.ITwoSpeedIdleMethodService;
import com.dbsoft.whjd.service.IWebServiceDataService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "twoSpeedIdleMethodAction",results = {
		@Result(name="editTwoSpeedIdleMethod",location="/jsp/TwoSpeedIdleMethodManage/modifyTwoSpeedIdleMethod.jsp")
		,
		@Result(name = "methodPrint", location = "/jsp/TwoSpeedIdleMethodManage/printTwoSpeedIdleMethod.jsp")})
public class TwoSpeedIdleMethodAction  extends BaseAction implements ModelDriven<TwoSpeedIdleMethodPage>{
	private String q;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	private static final Logger logger = Logger.getLogger(InspectionStationAction.class);
	private ITwoSpeedIdleMethodService iTwoSpeedIdleMethodService;
	private IWebServiceDataService iWebServiceDataService;
	public IWebServiceDataService getiWebServiceDataService() {
		return iWebServiceDataService;
	}
	@Resource(name = "webServiceDataService")
	public void setiWebServiceDataService(IWebServiceDataService iWebServiceDataService) {
		this.iWebServiceDataService = iWebServiceDataService;
	}
	public ITwoSpeedIdleMethodService getiTwoSpeedIdleMethodService() {
		return iTwoSpeedIdleMethodService;
	}
	@Resource(name = "twoSpeedIdleMethodService")
	public void setiTwoSpeedIdleMethodService(ITwoSpeedIdleMethodService iTwoSpeedIdleMethodService) {
		this.iTwoSpeedIdleMethodService = iTwoSpeedIdleMethodService;
	}
	public static Logger getLogger() {
		return logger;
	}
	private TwoSpeedIdleMethodPage twoSpeedIdleMethodPage=new TwoSpeedIdleMethodPage();
	@Override
	public TwoSpeedIdleMethodPage getModel() {
		return twoSpeedIdleMethodPage;
	}
	//批量下结论******************************
	public void batchConclusion()
	{
		Json json=new Json();
		try {
		json=iTwoSpeedIdleMethodService.batchConclusion(twoSpeedIdleMethodPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("批量下结论失败");
	}
		super.writeJson(json);
	}
	public void getTwoSpeedIdleMethod() {       // 获取双怠速法数据信息
		Json json=new Json();
		try {
			super.writeJson(iTwoSpeedIdleMethodService.getTwoSpeedIdleMethod(twoSpeedIdleMethodPage));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("获取双怠速法数据失败");
		}
	}
	public void updateTwoSpeedIdleMethod() {
		System.out.println(twoSpeedIdleMethodPage.getSysUserByInspecDriverId());
		// 更新双怠速法数据信息
		Json json=new Json();
		try {
			json=iTwoSpeedIdleMethodService.updateTwoSpeedIdleMethod(twoSpeedIdleMethodPage);
			json.setSuccess(true);
			json.setMsg("修改双怠速法数据成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("修改双怠速法数据失败");
		}
		super.writeJson(json);
	}
	public String editTwoSpeedIdleMethod() {//跳转编辑页面
		return "editTwoSpeedIdleMethod";		
	}
	
	/**
	 * 打印一个检测报告（包括检测数据）
	 */
	public String methodPrint() {
		HttpServletRequest req = ServletActionContext.getRequest();
		TwoSpeedIdleMethodPage page = iTwoSpeedIdleMethodService.getDetectionById(twoSpeedIdleMethodPage.getId());
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
			json=iTwoSpeedIdleMethodService.beginDetection(twoSpeedIdleMethodPage);
			if(json.isSuccess())
			{
				json=iWebServiceDataService.addWebServiceData(twoSpeedIdleMethodPage.getId());
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
