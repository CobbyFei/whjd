package com.dbsoft.whjd.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LimitValueReferencePage;
import com.dbsoft.whjd.service.ILimitValueReferenceService;
import com.opensymphony.xwork2.ModelDriven;
@Action(value = "motorTwoSpeedIdleLimitValueReferenceAction", results = { @Result(name = "editLimit", location = "/jsp/limitValueReference/editMotorTwoSpeedIdleLimitValue.jsp") })
public class MotorTwoSpeedIdleLimitValueReferenceAction extends BaseAction implements ModelDriven<LimitValueReferencePage>{
	private LimitValueReferencePage referencePage = new LimitValueReferencePage();
	private ILimitValueReferenceService referenceService;
	public ILimitValueReferenceService getReferenceService() {
		return referenceService;
	}
	@Resource(name = "motorTwoSpeedIdleLimitValueReferenceService")
	public void setReferenceService(ILimitValueReferenceService referenceService) {
		this.referenceService = referenceService;
	}
	@Override
	public LimitValueReferencePage getModel() {
		// TODO Auto-generated method stub
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
		Json j = new Json();
		try {
			j=referenceService.saveLimitValue(referencePage);
			j.setSuccess(true);
			j.setMsg("添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		super.writeJson(j);
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
		Json j = new Json();
		try {
			j=referenceService.editLimitValue(referencePage);
			j.setSuccess(true);
			j.setMsg("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			j.setMsg("修改失败");
			j.setSuccess(false);
		}
		super.writeJson(j);
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
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		super.writeJson(j);
	}
}
