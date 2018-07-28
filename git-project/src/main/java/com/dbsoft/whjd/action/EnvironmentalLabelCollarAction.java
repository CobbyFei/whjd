package com.dbsoft.whjd.action;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IEnvironmentalLabelCollarService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;
@Action(value = "environmentalLabelCollarAction",results = {
		@Result(name="editEnvironmentalLabelCollar",location="/jsp/EnvironmentalLabelCollarManage/modifyEnvironmentalLabelCollar.jsp")
		})
public class EnvironmentalLabelCollarAction extends BaseAction implements ModelDriven<EnvironmentalLabelCollarPage>{
	private String q;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	private static final Logger logger = Logger.getLogger(EnvironmentalLabelCollarAction.class);
	private EnvironmentalLabelCollarPage environmentalLabelCollarPage= new EnvironmentalLabelCollarPage();
	public IEnvironmentalLabelCollarService getEnvironmentalLabelCollarService() {
		return environmentalLabelCollarService;
	}
	
	@Resource(name = "environmentalLabelCollarService")
	public void setEnvironmentalLabelCollarService(IEnvironmentalLabelCollarService environmentalLabelCollarService) {
		this.environmentalLabelCollarService = environmentalLabelCollarService;
	}
	public static Logger getLogger() {
		return logger;
	}


	private IEnvironmentalLabelCollarService environmentalLabelCollarService;
	@Override
	public EnvironmentalLabelCollarPage getModel() {
		// TODO Auto-generated method stub
		return environmentalLabelCollarPage;
	}
	
	
	public void cancelEnvironmentalLabelCollar() {       // 注销环保标志领用记录
		Json json=new Json();
		try {
		json=environmentalLabelCollarService.cancelEnvironmentalLabelCollar(environmentalLabelCollarPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("注销环保标志领用记录失败");
	}
		super.writeJson(json);
	}
	
	public String  editEnvironmentalLabelCollar() {//跳转编辑页面
		return "editEnvironmentalLabelCollar";
	}
	
	public void updateEnvironmentalLabelCollar() {       // 修改环保标志领用记录
		Json json=new Json();
		try {
		json=environmentalLabelCollarService.updateEnvironmentalLabelCollar(environmentalLabelCollarPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("修改环保标志领用记录失败");
	}
		super.writeJson(json);
	}
	
	public void addEnvironmentalLabelCollar() {       // 添加环保标志领用记录
		Json json=new Json();
		try {
		json=environmentalLabelCollarService.addEnvironmentalLabelCollar(environmentalLabelCollarPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("添加环保标志领用记录失败");
	}
		super.writeJson(json);
	}
	
	public void exportEnvironmentalLabelCollar() {       // 导出环保标志领用记录
		Json json=new Json();
		try {
		json=environmentalLabelCollarService.exportEnvironmentalLabelCollar(environmentalLabelCollarPage);
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
    	json.setSuccess(false);
    	json.setMsg("导出环保标志领用记录失败");
	}
		super.writeJson(json);
	}
	
	public void getEnvironmentalLabelCollar() {       // 获取环保标志领用记录
	try {
		super.writeJson(environmentalLabelCollarService.getEnvironmentalLabelCollar(environmentalLabelCollarPage));
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
	}
	}
	
	public void getLeaveEnvironmentalLabelCollar() {       // 获取库存环保标志数量
	try {
		super.writeJson(environmentalLabelCollarService.getLeaveLabel(environmentalLabelCollarPage));
	}catch (Exception e) {
		logger.error(ExceptionUtil.getExceptionMessage(e));
	}
	}

}
