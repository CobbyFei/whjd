package com.dbsoft.whjd.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;
import com.dbsoft.whjd.service.ILimitValueReferenceService;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "freeAccelerationLimitValueReferenceAction", results = { @Result(name = "editLimit", location = "/jsp/limitValueReference/editFreeAccelerationLimitValue.jsp") })
public class FreeAccelerationLimitValueReferenceAction extends BaseAction
		implements ModelDriven<LimitValueReferencePage> {
	private LimitValueReferencePage referencePage = new LimitValueReferencePage();
	private ILimitValueReferenceService referenceService;

	@Override
	public LimitValueReferencePage getModel() {
		return referencePage;
	}

	public ILimitValueReferenceService getReferenceService() {
		return referenceService;
	}

	@Autowired
	@Qualifier("freeAccelerationLimitValueReferenceService")
	public void setReferenceService(ILimitValueReferenceService referenceService) {
		this.referenceService = referenceService;
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
		super.writeJson(referenceService.editLimitValue(referencePage));
	}

	/**
	 * 删除限值信息
	 * 
	 * @author gsw
	 */
	public void deleteLimitValue() {
		Json j = new Json();
		try {
			referenceService.deleteLimitValue(referencePage);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setMsg("删除失败");
		}
		super.writeJson(j);
	}
}
