package com.dbsoft.whjd.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.NewCarAdmitSheetPage;
import com.dbsoft.whjd.pageModel.VehicleTypeInfoPage;
import com.dbsoft.whjd.service.INewCarAdmitService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.dbsoft.whjd.util.TwoDimensionCode;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="newCarAdmitAction", results = {
		@Result(name = "newCarAdmitPrint", location = "/jsp/NewCarAdmit/printNewCarAdmitSheet.jsp") } )
public class NewCarAdmitAction extends BaseAction implements ModelDriven<NewCarAdmitSheetPage>{

	private static final Logger logger = Logger.getLogger(NewCarAdmitAction.class);
	
	
	NewCarAdmitSheetPage newCarAdmitSheetPage = new NewCarAdmitSheetPage();
	
	
	private INewCarAdmitService newCarAdmitService;
	
	
	public INewCarAdmitService getNewCarAdmitService() {
		return newCarAdmitService;
	}

	@Autowired
	public void setNewCarAdmitService(INewCarAdmitService newCarAdmitService) {
		this.newCarAdmitService = newCarAdmitService;
	}


	public void getNewCarAdmitSheet(){
		try{
	    	DataGrid nca=newCarAdmitService.getNewCarAdmitSheet(newCarAdmitSheetPage);
	    	if(nca== null){
	    		throw new Exception();
	    	}else{
	    		super.writeJson(nca);
		    }
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println(" newCarAdmitDataGrid返回值为空！");
	    	return;
		}
	}
	
	
	public void changeNewCarAdmitStatus(){
		try{
			Json json = newCarAdmitService.changeNewCarAdmitStatus(newCarAdmitSheetPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	
	public String newCarAdmitPrint(){
		HttpServletRequest req = ServletActionContext.getRequest();
		NewCarAdmitSheetPage npage = newCarAdmitService.gerNewCarAdmitSheetById(newCarAdmitSheetPage.getID());
		req.getSession().setAttribute("newCarAdmitSheetPage", npage);
		return "newCarAdmitPrint";
	}
	
	public void outPutQRcode() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		// 调用service的方法返回环保标志信息
		String licence = newCarAdmitSheetPage.getLicence();
		String licenceColor = newCarAdmitSheetPage.getLicenseColor();
		String vin = newCarAdmitSheetPage.getVin();
		Integer emissionStandard = newCarAdmitSheetPage.getEmissionStandard();
		//TODO
		
		//String labelId = newCarAdmitSheetPage.getLabelId();
		String content = "车牌号:" + licence + "；车牌颜色："+licenceColor+"；车辆识别码："+vin+"；车辆排放标准：国"+emissionStandard;
		System.out.println(content);
		TwoDimensionCode handler = new TwoDimensionCode();
		try {
			handler.encoderQRCode(content, resp.getOutputStream());
			resp.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public NewCarAdmitSheetPage getModel() {
		// TODO Auto-generated method stub
		return newCarAdmitSheetPage;
	}

}
