package com.dbsoft.whjd.action;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.AgencyEnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IAgencyCollarService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.google.common.base.Function;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.internal.ws.processor.modeler.annotation.ModelBuilder;
@Action(value="agencyCollarAction",results={
		@Result(name="edit",location="/jsp/EnvironmentalLabelCollarManage/modifyAgencyCollarRecord.jsp")
})
public class AgencyCollarAction extends BaseAction implements ModelDriven<AgencyEnvironmentalLabelCollarPage>{
   AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage=new AgencyEnvironmentalLabelCollarPage();
   IAgencyCollarService  agencyCollarService;
   private static final Logger logger=Logger.getLogger(AgencyCollarAction.class);
   private static final String EDIT_AGENCY_COLLAR_RECORD="edit";
   //添加市局领用标志记录
   public void addAgencyCollarRecord()
   {
	   Json res=new Json();
	   try {
		   res=agencyCollarService.addAgencyCollarRecord(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setMsg("添加领用记录出现异常");
			res.setSuccess(false);
		}
		super.writeJson(res); 
   }
   
   public void getAgencyCollarRecord()
   {
	   DataGrid dg=new DataGrid();
	   try {
		  dg=agencyCollarService.getAllAgencyCollarRecord(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	   super.writeJson(dg);
   }
   
   public void cancelCollarRecord()
   {
	   Json res=new Json();
	   try {
		  res=agencyCollarService.cancelAgencyCollarRecord(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("注销领用记录出现异常");
		}
	   super.writeJson(res);
   }
   
   public void removeCollarRecord()
   {
	   Json res=new Json();
	   try {
		  res=agencyCollarService.deleteAgencyCollarRecord(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			res.setSuccess(false);
			res.setMsg("删除领用记录出现异常");
		}
	   super.writeJson(res);
   }
   
   public String edit()
   {
	   return EDIT_AGENCY_COLLAR_RECORD;
   }
   
   public  void updateAgencyCollar()
   {
	   Json res=new Json();
	   try{
		  res=agencyCollarService.modifyAgencyCollarRecord(agencyEnvironmentalLabelCollarPage);
	   }catch(Exception e)
	   {
		   logger.error(ExceptionUtil.getExceptionMessage(e));
		   res.setSuccess(false);
		   res.setMsg("修改领用记录出现异常");
	   }
	   super.writeJson(res);
   }
   
   public void getSumOfLabel()
   {
	   DataGrid dg=new DataGrid();
	   try {
		   dg=agencyCollarService.getSumOfLabel(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	   super.writeJson(dg);
   }
   @Override
   public AgencyEnvironmentalLabelCollarPage getModel() {
		return agencyEnvironmentalLabelCollarPage;
    }


	public IAgencyCollarService getAgencyCollarService() {
		return agencyCollarService;
	}
	
	@Autowired
	public void setAgencyCollarService(IAgencyCollarService agencyCollarService) {
		this.agencyCollarService = agencyCollarService;
	}
	

}
