package com.dbsoft.whjd.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MaintainceRecordPage;
import com.dbsoft.whjd.service.IMaintainceRecordService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 检测设备基本信息
 * 
 * @author gsw
 */
@Action(value = "maintainceRecordAction", results = { @Result(name = "recordEdit", location = "/jsp/maintainceRecord/editMaintainceRecord.jsp") })
public class MaintainceRecordAction extends BaseAction implements
		ModelDriven<MaintainceRecordPage> {
	private IMaintainceRecordService recordService;
	private MaintainceRecordPage recordPage = new MaintainceRecordPage();

	public IMaintainceRecordService getMaintainceRecordService() {
		return recordService;
	}

	@Autowired
	public void setMaintainceRecordService(
			IMaintainceRecordService recordService) {
		this.recordService = recordService;
	}

	@Override
	public MaintainceRecordPage getModel() {
		return recordPage;
	}

	/**
	 * 获取所有设备维护记录，用于Datagrid显示
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getAllRecords() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			recordPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(recordService.getAllMaintainceRecord(recordPage));
	}

	/**
	 * 保存一条设备维护记录，用于添加或修改
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void saveRecord() throws Exception {
		Json j = new Json();
		recordService.saveMaintainceRecord(recordPage);
		j.setSuccess(true);
		j.setMsg("保存成功");
		super.writeJson(j);
	}

	/**
	 * 注销一批维护记录
	 * 
	 * @author gsw
	 */
	public void deleteRecords() {
		Json j = new Json();
		try {
			recordService.deleteMaintainceRecord(recordPage);
			j.setSuccess(true);
			j.setMsg("注销成功");
		} catch (Exception e) {
			j.setMsg("注销失败");
		}
		super.writeJson(j);
	}

	/**
	 * 修改一条维护记录
	 * 
	 * @author gsw
	 */
	public String recordEdit() {
		return "recordEdit";
	}

	/**
	 * 导出一批维护记录
	 * 
	 * @author gsw
	 * @throws Exception 
	 */
	public void exportRecord() throws Exception {
		String path = recordService.exportDeviceInfo(recordPage).getObj()
				.toString();
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(path);
		super.writeJson(j);
	}
}
