package com.dbsoft.whjd.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IFreeAccelerationMethodService;
import com.dbsoft.whjd.service.IWebServiceDataService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "freeAccelerationMethodAction", results = {
		@Result(name = "methodEdit", location = "/jsp/freeAccelerationMethodDetection/editFreeAccelerationMethodDetection.jsp"),
		@Result(name = "methodPrint", location = "/jsp/freeAccelerationMethodDetection/printFreeAccelerationMethodDetection.jsp") })
public class FreeAccelerationMethodAction extends BaseAction implements
		ModelDriven<FreeAccelerationMethodPage> {
	FreeAccelerationMethodPage methodPage = new FreeAccelerationMethodPage();
	private static final Logger logger = Logger
			.getLogger(InspectionStationAction.class);
	IFreeAccelerationMethodService methodService;
	private IWebServiceDataService iWebServiceDataService;

	public IWebServiceDataService getiWebServiceDataService() {
		return iWebServiceDataService;
	}

	@Resource(name = "webServiceDataService")
	public void setiWebServiceDataService(
			IWebServiceDataService iWebServiceDataService) {
		this.iWebServiceDataService = iWebServiceDataService;
	}

	@Override
	public FreeAccelerationMethodPage getModel() {
		return methodPage;
	}

	public IFreeAccelerationMethodService getMethodService() {
		return methodService;
	}

	@Autowired
	public void setMethodService(IFreeAccelerationMethodService methodService) {
		this.methodService = methodService;
	}

	/**
	 * 获取所有检测记录，用于datagrid显示
	 * 
	 * @author gsw
	 */
	public void getAllMethodDetections() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			methodPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(methodService.getAllDetections(methodPage));
	}

	/**
	 * 保存检测信息（包括检测数据），用于编辑修改
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void updateMethod() throws Exception {
		super.writeJson(methodService.updateMethod(methodPage));
	}

	/**
	 * 修改一条检测信息（包括检测数据）
	 * 
	 * @author gsw
	 */
	public String methodEdit() {
		return "methodEdit";
	}

	/**
	 * 打印一个检测报告（包括检测数据）
	 * 
	 * @author gsw
	 */
	public String methodPrint() {
		HttpServletRequest req = ServletActionContext.getRequest();
		int temp = methodPage.getId();
		FreeAccelerationMethodPage page = methodService.getDetectionById(temp);
		req.getSession().setAttribute("methodPage", page);
		return "methodPrint";
	}

	/**
	 * 批量下结论
	 * 
	 * @author gsw
	 */
	public void batchConclusion() {
		super.writeJson(methodService.batchConclusion(methodPage));
	}

	/*
	 * 开始检测
	 */
	public void beginDetection() {
		// 开始检测自由加速法数据信息
		Json json = new Json();
		try {
			json = methodService.beginDetection(methodPage);
			if (json.isSuccess()) {
				json = iWebServiceDataService.addWebServiceData(methodPage
						.getId());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("添加检测任务失败");
		}
		super.writeJson(json);
	}
}
