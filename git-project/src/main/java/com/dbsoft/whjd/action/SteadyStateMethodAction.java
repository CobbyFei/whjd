package com.dbsoft.whjd.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.SteadyStateMethodPage;
import com.dbsoft.whjd.service.ISteadyStateMethodService;
import com.dbsoft.whjd.service.IWebServiceDataService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 稳态工况法
 * @author mark
 *
 */
@Action(value = "steadyStateMethodAction", results = {
		@Result(name ="steadyStateMethodEdit", location="/jsp/SteadyStateMethodManage/steadyStateMethodEdit.jsp"),
		@Result(name ="methodPrint", location="/jsp/SteadyStateMethodManage/steadyStateMethodPrint.jsp")
})
public class SteadyStateMethodAction extends BaseAction implements ModelDriven<SteadyStateMethodPage> {

	private static final Logger logger = Logger.getLogger(SteadyStateMethodAction.class);
	
	@Autowired
	@Qualifier("steadyStateMethodService")
	private ISteadyStateMethodService steadyStateMethodService;
	
	@Autowired
	@Qualifier("webServiceDataService")
	private IWebServiceDataService webServiceDataService;
	
	private SteadyStateMethodPage steadyStateMethodPage = new SteadyStateMethodPage();
	
	//查询稳态工况法的委托单
	public void getCommisionSheetsOfSteadyStateMethod(){
		 try{
		    	DataGrid dg=steadyStateMethodService.findCommisionSheetsOfSteadyStateMethod(steadyStateMethodPage);	
		    	super.writeJson(dg);
			  
		    }catch (Exception e) {
		    	logger.error(ExceptionUtil.getExceptionMessage(e));
		    	e.printStackTrace();
			}
	}
	
	
	public String steadyStateMethodEdit(){
		return "steadyStateMethodEdit";
	}
	
	/**
	 * 编辑稳态工况法检测数据
	 */
	public void editSteadyStateMethod(){
		try {
			Json json = steadyStateMethodService.updateSteadyStateMethodService(steadyStateMethodPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量下结论
	 */
	public void batchConclusion()
	{
		Json json=new Json();
		try {
			json=steadyStateMethodService.batchConclusion(steadyStateMethodPage);
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			json.setSuccess(false);
			json.setMsg("批量下结论失败");
		}
		super.writeJson(json);
	}
	
	/**
	 * 打印一个检测报告（包括检测数据）
	 * 
	 * @author mark
	 */
	public String methodPrint() {
		HttpServletRequest req = ServletActionContext.getRequest();
		SteadyStateMethodPage page = steadyStateMethodService
				.getDetectionById(steadyStateMethodPage.getSheetId());
		System.out.println(page.getFuelType());
		req.getSession().setAttribute("methodPage", page);
		return "methodPrint";
	}
	
	public void beginDetection() {
		// 开始检测双怠速法数据信息
		Json json=new Json();
		try {
			json=steadyStateMethodService.beginDetection(steadyStateMethodPage);
			if(json.isSuccess())
			{
				json=webServiceDataService.addWebServiceData(steadyStateMethodPage.getSheetId());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtil.getExceptionMessage(e));
		   	json.setSuccess(false);
	    	json.setMsg("添加检测任务失败");
		}
		super.writeJson(json);
	}
	
	
	@Override
	public SteadyStateMethodPage getModel(){
		return steadyStateMethodPage;
	}
	
}
