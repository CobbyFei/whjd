package com.dbsoft.whjd.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EmissionAdmitStandardPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IAdmitStandardService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "admitStandardAction", results = {
		@Result(name ="admitStandardEdit", location="/jsp/NewCarAdmit/admitStandardEdit.jsp"),
		@Result(name = "openAddAdmitStandardPage", location="/jsp/NewCarAdmit/addAdmitStandard.jsp")
})
public class AdmitStandardAction extends BaseAction implements ModelDriven<EmissionAdmitStandardPage>{
	
	
	private static final Logger logger = Logger.getLogger(AdmitStandardAction.class);
	
	
	EmissionAdmitStandardPage emissionAdmitStandardPage = new EmissionAdmitStandardPage();
	
	private IAdmitStandardService admitStandardService;
	
	
	
	public IAdmitStandardService getAdmitStandardService() {
		return admitStandardService;
	}
	@Autowired
	public void setAdmitStandardService(IAdmitStandardService admitStandardService) {
		this.admitStandardService = admitStandardService;
	}

	public void getAdmitStandard(){
		 try{
		    	DataGrid syu=admitStandardService.findAdmitStandards(emissionAdmitStandardPage);
		    	if(syu== null){
		    		throw new Exception();
		    	}else{
		    		super.writeJson(syu);
			    }
		    }catch (Exception e) {
		    	logger.error(ExceptionUtil.getExceptionMessage(e));
		    	System.out.println("admitStandardDatagrid 返回值为空！");
		    	return;
			}
	}
	
	public String admitStandardEdit(){
		return "admitStandardEdit";
	}
	
	public void editAdmitStandardRecord(){
		try{
			Json json = admitStandardService.updateAdmitStandard(emissionAdmitStandardPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	
	public String openAddAdmitStandardPage(){
		return "openAddAdmitStandardPage";
	}
	
	public void AddAdmitStandard(){
		try{
			Json json = admitStandardService.addAdmitStandard(emissionAdmitStandardPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	
	public void deleteAdmitStandardValue(){
		Json j = new Json();
		try {
			j = admitStandardService.deleteAdmitStandardValue(emissionAdmitStandardPage);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setMsg("删除失败");
		}
		super.writeJson(j);
	}
	
	@Override
	public EmissionAdmitStandardPage getModel() {
		// TODO Auto-generated method stub
		return emissionAdmitStandardPage;
	}

}
