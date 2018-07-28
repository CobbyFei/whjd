package com.dbsoft.whjd.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.ReferenceMaterialsRecordPage;
import com.dbsoft.whjd.service.IReferenceMaterialsRecordService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 标准物质采购记录
 * 
 * @author gsw
 * 
 */
@Action(value = "referenceMaterialsRecordAction", results = { @Result(name = "recordEdit", location = "/jsp/referenceMaterialsRecord/referenceMaterialsRecordEdit.jsp") })
public class ReferenceMaterialsRecordAction extends BaseAction implements
		ModelDriven<ReferenceMaterialsRecordPage> {
	private IReferenceMaterialsRecordService recordService;
	private ReferenceMaterialsRecordPage recordPage = new ReferenceMaterialsRecordPage();

	@Override
	public ReferenceMaterialsRecordPage getModel() {
		// TODO Auto-generated method stub
		return recordPage;
	}

	public IReferenceMaterialsRecordService getRecordService() {
		return recordService;
	}

	@Autowired
	public void setRecordService(IReferenceMaterialsRecordService recordService) {
		this.recordService = recordService;
	}

	/**
	 * 保存采购记录，用于添加或修改
	 * 
	 * @author gsw
	 */
	public void saveRecord() {
		Json j = new Json();
		recordService.saveReferenceMaterialsRecord(recordPage);
		j.setSuccess(true);
		j.setMsg("保存成功");
		super.writeJson(j);
	}

	/**
	 * 获取所有采购记录，显示在datagrid中
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getAllRecords() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			recordPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(recordService
				.getAllReferenceMaterialsRecord(recordPage));
	}

	/**
	 * 删除一批采购记录
	 * 
	 * @author gsw
	 */
	public void deleteRecords() {
		Json j = new Json();
		try {
			recordService.deleteReferenceMaterialsRecord(recordPage);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setMsg("删除失败");
		}
		super.writeJson(j);
	}

	/**
	 * 修改一条采购记录
	 * 
	 * @author gsw
	 */
	public String recordEdit() throws Exception {
		return "recordEdit";
	}

	/**
	 * 导出一批设备信息
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void exportRecord() throws Exception {
		String path = recordService.exportReferenceMaterialsRecord(recordPage)
				.getObj().toString();
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(path);
		super.writeJson(j);
	}

	/**
	 * 标准物质名称的联想
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getMaterialNames() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			recordPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(recordService.getMaterialNames(recordPage));
	}

	/**
	 * 制造厂商的联想
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getManufacturers() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			recordPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(recordService.getManufacturers(recordPage));
	}

	/**
	 * 型号的联想
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getModels() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			recordPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(recordService.getModels(recordPage));
	}
}
