package com.dbsoft.whjd.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.DeviceInfoPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDeviceInfoService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 检测设备基本信息
 * 
 * @author gsw
 */
@Action(value = "deviceInfoAction", results = { @Result(name = "infoEdit", location = "/jsp/deviceInfo/editDeviceInfo.jsp") })
public class DeviceInfoAction extends BaseAction implements
		ModelDriven<DeviceInfoPage> {
	private IDeviceInfoService deviceInfoService;
	private DeviceInfoPage deviceInfoPage = new DeviceInfoPage();

	public IDeviceInfoService getDeviceInfoService() {
		return deviceInfoService;
	}

	@Autowired
	public void setDeviceInfoService(IDeviceInfoService deviceInfoService) {
		this.deviceInfoService = deviceInfoService;
	}

	@Override
	public DeviceInfoPage getModel() {
		return deviceInfoPage;
	}

	/**
	 * 获取所有设备信息，用于Datagrid显示
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getAllInfos() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			deviceInfoPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(deviceInfoService.getAllDeviceInfo(deviceInfoPage));
	}

	/**
	 * 添加一条设备信息
	 * 
	 * @author gsw
	 */
	public void saveInfo() {
		super.writeJson(deviceInfoService.saveDeviceInfo(deviceInfoPage));
	}

	/**
	 * 修改一条设备信息
	 * 
	 * @author gsw
	 */
	public void editInfo() {
		super.writeJson(deviceInfoService.editDeviceInfo(deviceInfoPage));
	}

	/**
	 * 注销一批设备信息
	 * 
	 * @author gsw
	 */
	public void cancelDeviceInfo() {
		Json j = new Json();
		try {
			deviceInfoService.cancelDeviceInfo(deviceInfoPage);
			j.setSuccess(true);
			j.setMsg("注销成功");
			j.setObj(deviceInfoPage);
		} catch (Exception e) {
			j.setMsg("注销失败");
		}
		super.writeJson(j);
	}

	/**
	 * 导出一批设备信息
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void exportDeviceInfo() throws Exception {
		String path = deviceInfoService.exportDeviceInfo(deviceInfoPage)
				.getObj().toString();
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(path);
		super.writeJson(j);
	}

	/**
	 * 修改一条设备信息
	 * 
	 * @author gsw
	 */
	public String infoEdit() {
		return "infoEdit";
	}

	/**
	 * 仅凭id获得一条设备信息
	 * 
	 * @author gsw
	 */
	public void getDeviceInfo() {
		super.writeJson(deviceInfoService.getDeviceInfo(deviceInfoPage));
	}

	/**
	 * 设备名称的联想
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getDeviceNames() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			deviceInfoPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(deviceInfoService.getDeviceNames(deviceInfoPage));
	}

	/**
	 * 型号规格的联想
	 * 
	 * @author gsw
	 * @throws Exception
	 */
	public void getDeviceModels() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.getAttribute("stationName").equals("市局"))
			deviceInfoPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(deviceInfoService.getDeviceModels(deviceInfoPage));
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
			deviceInfoPage.setStationName(session.getAttribute("stationName")
					.toString());
		super.writeJson(deviceInfoService.getManufacturers(deviceInfoPage));
	}
}
