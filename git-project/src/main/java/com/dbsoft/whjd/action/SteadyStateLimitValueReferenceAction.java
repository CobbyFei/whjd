package com.dbsoft.whjd.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;
import com.dbsoft.whjd.service.ILimitValueReferenceService;
import com.opensymphony.xwork2.ModelDriven;

@Action(value="steadyStateLimitValueReferenceAction", results = { @Result(name = "editLimit", location = "/jsp/limitValueReference/editSteadyStateLimitValue.jsp") })
public class SteadyStateLimitValueReferenceAction extends BaseAction
	implements ModelDriven<LimitValueReferencePage> {
	
	private LimitValueReferencePage referencePage = new LimitValueReferencePage();
	
	private ILimitValueReferenceService referenceService;
	
	
	public ILimitValueReferenceService getReferenceService() {
		return referenceService;
	}

	@Autowired
	@Qualifier("steadyStateLimitValueReferenceService")
	public void setReferenceService(ILimitValueReferenceService referenceService) {
		this.referenceService = referenceService;
	}


	@Override
	public LimitValueReferencePage getModel() {
		return referencePage;
	}
	
	
	/**
	 * 获得限值信息
	 * 
	 * @author gsw
	 */
	public void getLimitValues() {
		super.writeJson(referenceService.getLimitValues(referencePage));
	}

	/**
	 * 保存限值信息
	 * 
	 * @author gsw
	 */
	public void saveLimitValue() {
		super.writeJson(referenceService.saveLimitValue(referencePage));
	}

	/**
	 * 跳转至修改限值页面
	 * 
	 * @author gsw
	 */
	public String limitEdit() {
		return "editLimit";
	}

	/**
	 * 修改限值信息
	 * 
	 * @author gsw
	 */
	public void editLimit() {
	
		Json json =	referenceService.editLimitValue(referencePage);
		if(json==null){
			json = new Json();
			json.setMsg("修改不成功！");
			json.setSuccess(false);
			super.writeJson(json);
		}else{
			super.writeJson(json);
		}
	}

	/**
	 * 删除限值信息
	 * 
	 * @author gsw
	 */
	public void deleteLimitValue() {
		Json json = new Json();
		json =	referenceService.deleteLimitValue(referencePage);
		super.writeJson(json);
	}
}
