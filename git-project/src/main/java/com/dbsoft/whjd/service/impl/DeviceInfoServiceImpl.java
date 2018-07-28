package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.DeviceInfo;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DeviceInfoPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDeviceInfoService;
import com.dbsoft.whjd.util.ExportUtil;

@Service(value = "deviceInfoService")
public class DeviceInfoServiceImpl implements IDeviceInfoService {
	private IBaseDao<DeviceInfo> deviceInfoDao;
	private IBaseDao<DetectionLine> detectionLineDao;
	private IBaseDao<InspectionStation> stationDao;

	public IBaseDao<InspectionStation> getStationDao() {
		return stationDao;
	}

	@Resource(name = "baseDao")
	public void setStationDao(IBaseDao<InspectionStation> stationDao) {
		this.stationDao = stationDao;
	}

	public IBaseDao<DetectionLine> getDetectionLineDao() {
		return detectionLineDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionLineDao(IBaseDao<DetectionLine> detectionLineDao) {
		this.detectionLineDao = detectionLineDao;
	}

	public IBaseDao<DeviceInfo> getDeviceInfoDao() {
		return deviceInfoDao;
	}

	@Resource(name = "baseDao")
	public void setDeviceInfoDao(IBaseDao<DeviceInfo> deviceInfoDao) {
		this.deviceInfoDao = deviceInfoDao;
	}

	/**
	 * 将一个DeviceInfoPage对象复制到DeviceInfo对象中，并将DeviceInfo对象返回
	 * 
	 * @param deviceInfoPage
	 * @return 复制得到的DeviceInfo对象
	 * @author gsw
	 */
	public DeviceInfo copyToInfo(DeviceInfoPage deviceInfoPage) {
		DeviceInfo deviceInfo = new DeviceInfo();
		DetectionLine detectionLine = new DetectionLine();
		if (deviceInfoPage.getLineId() != null) {
			detectionLine = detectionLineDao
					.get("FROM DetectionLine as d WHERE d.lineId="
							+ deviceInfoPage.getLineId());
		} else if (deviceInfoPage.getLineName() != null) {
			detectionLine = detectionLineDao
					.get("FROM DetectionLine as d WHERE d.lineName= '"
							+ deviceInfoPage.getLineName()
							+ "' AND d.inspectionStation.stationName='"
							+ deviceInfoPage.getStationName()
							+ "' AND d.lineStatus=0");
		}
		BeanUtils.copyProperties(deviceInfoPage, deviceInfo);
		deviceInfo.setDetectionLine(detectionLine);
		return deviceInfo;
	}

	/**
	 * 将一个DeviceInfo对象复制到DeviceInfoPage对象中，并将DeviceInfoPage对象返回
	 * 
	 * @author gsw
	 */
	public DeviceInfoPage copyToPage(DeviceInfo info) {
		DeviceInfoPage page = new DeviceInfoPage();
		BeanUtils.copyProperties(info, page);
		page.setLineId(info.getDetectionLine().getLineId());
		page.setLineName(info.getDetectionLine().getLineName());
		page.setStationName(info.getDetectionLine().getInspectionStation()
				.getStationName());
		return page;
	}

	@Override
	/**
	 * 查询对应检测线上是否存在同名未注销的设备。若存在则无法添加。
	 * @author gsw
	 */
	public Json saveDeviceInfo(DeviceInfoPage deviceInfoPage) {
		Json j = new Json();
		deviceInfoPage.setDeviceStatus(0);
		DeviceInfo info = copyToInfo(deviceInfoPage);
		if (hasDeviceInfo(info)) {
			j.setSuccess(false);
			j.setMsg("所选检测线上已有该设备，无法添加！");
		} else {
			deviceInfoDao.save(info);
			j.setSuccess(true);
			j.setMsg("保存成功");
		}
		return j;
	}

	/**
	 * 判断对应检测线上是否存在同名未注销的设备。
	 * 
	 * @param deviceInfoPage
	 * @return
	 */
	public boolean hasDeviceInfo(DeviceInfo deviceInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lineId", deviceInfo.getDetectionLine().getLineId());
		params.put("deviceName", deviceInfo.getDeviceName());
		String hql = "FROM DeviceInfo as d WHERE d.deviceName= :deviceName AND d.deviceStatus=0";
		if (deviceInfo.getDetectionLine().getLineId() != null) {
			params.put("lineId", deviceInfo.getDetectionLine().getLineId());
			hql += " AND d.detectionLine.lineId= :lineId";
		} else if (deviceInfo.getDetectionLine().getLineName() != null) {
			params.put("lineName", deviceInfo.getDetectionLine().getLineName());
			hql += "fAND d.detectionLine.lineName= :lineName";
		}
		DeviceInfo test = deviceInfoDao.get(hql, params);
		if (test == null)
			return false;
		else
			return true;
	}

	@Override
	public List<DeviceInfoPage> getDeviceNames(DeviceInfoPage deviceInfoPage)
			throws Exception {
		String q;
		if (deviceInfoPage.getQ() == null
				|| deviceInfoPage.getQ().trim().equals(""))
			q = "";
		else
			q = deviceInfoPage.getQ();
		List<DeviceInfo> infoList = new ArrayList<DeviceInfo>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q + "%");
		String hql = "SELECT DISTINCT d.deviceName FROM DeviceInfo as d  WHERE d.deviceName like :text";
		if (deviceInfoPage.getStationName() != null
				&& !deviceInfoPage.getStationName().trim().equals("")) {
			tMap.put("stationName", deviceInfoPage.getStationName());
			hql += " AND d.detectionLine.inspectionStation.stationName= :stationName";
		}
		if (deviceInfoPage.getLineId() != null) {
			tMap.put("lineId", deviceInfoPage.getLineId());
			hql += " AND d.detectionLine.lineId= :lineId";
		}
		infoList = deviceInfoDao.find(hql, tMap, 1, 10);
		List<DeviceInfoPage> pageList = new ArrayList<DeviceInfoPage>();
		for (Object info : infoList) {
			DeviceInfoPage page = new DeviceInfoPage();
			page.setDeviceName(info.toString());
			pageList.add(page);
		}
		return pageList;
	}

	@Override
	public void cancelDeviceInfo(DeviceInfoPage deviceInfoPage) {
		if (deviceInfoPage.getIds() != null) {
			for (String id : deviceInfoPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					DeviceInfo info = deviceInfoDao.get(DeviceInfo.class,
							Integer.valueOf(id));
					if (info != null) {
						info.setDeviceStatus(1);
						deviceInfoDao.update(info);
					}
				}
			}
		}
	}

	@Override
	public Json exportDeviceInfo(DeviceInfoPage deviceInfoPage)
			throws Exception {
		List<Object> pageList = new ArrayList<Object>();
		if (deviceInfoPage.getIds() != null) {
			for (String id : deviceInfoPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					DeviceInfo info = deviceInfoDao.get(DeviceInfo.class,
							Integer.valueOf(id));
					if (info != null) {
						DeviceInfoPage page = copyToPage(info);
						pageList.add(page);
					}
				}
			}
		}
		String filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
				"formatExcel", "formatTitle", DeviceInfoServiceImpl.class);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		return retJson;
	}

	@Override
	public DataGrid getAllDeviceInfo(DeviceInfoPage deviceInfoPage)
			throws Exception {
		DataGrid dg = new DataGrid();
		List<DeviceInfoPage> pageList = new ArrayList<DeviceInfoPage>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "FROM DeviceInfo as d WHERE 1=1";
		if (deviceInfoPage.getStationName() != null
				&& !deviceInfoPage.getStationName().equals("")) {
			params.put("stationName", deviceInfoPage.getStationName());
			hql += " AND d.detectionLine.inspectionStation.stationName= :stationName";
		}
		if (deviceInfoPage.getDeviceName() != null
				&& !deviceInfoPage.getDeviceName().trim().equals("")) {
			params.put("deviceName", "%" + deviceInfoPage.getDeviceName() + "%");
			hql += " and d.deviceName like :deviceName";
		}
		if (deviceInfoPage.getManufacturer() != null
				&& !deviceInfoPage.getManufacturer().trim().equals("")) {
			params.put("manufacturer", "%" + deviceInfoPage.getManufacturer()
					+ "%");
			hql += " and d.manufacturer like :manufacturer";
		}
		if (deviceInfoPage.getDeviceModel() != null
				&& !deviceInfoPage.getDeviceModel().trim().equals("")) {
			params.put("deviceModel", "%" + deviceInfoPage.getDeviceModel()
					+ "%");
			hql += " and d.deviceModel like :deviceModel";
		}
		if (deviceInfoPage.getLineId() != null) {
			params.put("lineId", deviceInfoPage.getLineId());
			hql += " and d.detectionLine.lineId= :lineId";
		}
		if (deviceInfoPage.getDeviceStatus() != null
				&& deviceInfoPage.getDeviceStatus() != 2) {
			params.put("deviceStatus", deviceInfoPage.getDeviceStatus());
			hql += " and d.deviceStatus= :deviceStatus";
		}
		List<DeviceInfo> infoList = deviceInfoDao.find(hql, params,
				deviceInfoPage.getPage(), deviceInfoPage.getRows());
		dg.setTotal(deviceInfoDao.count("SELECT COUNT(*)" + hql, params));
		for (DeviceInfo info : infoList) {
			DeviceInfoPage page = copyToPage(info);
			pageList.add(page);
		}
		dg.setRows(pageList);
		return dg;
	}

	@Override
	public Json editDeviceInfo(DeviceInfoPage deviceInfoPage) {
		Json j = new Json();
		DeviceInfo info = copyToInfo(deviceInfoPage);
		if (hasDeviceInfo(info)) {
			j.setSuccess(false);
			j.setMsg("所选检测线上已有该设备，无法修改！");
		} else {
			deviceInfoDao.update(info);
			j.setSuccess(true);
			j.setMsg("修改成功");
		}
		return j;
	}

	@Override
	public List<DeviceInfoPage> getDeviceModels(DeviceInfoPage deviceInfoPage)
			throws Exception {
		String q;
		if (deviceInfoPage.getQ() == null
				|| deviceInfoPage.getQ().trim().equals(""))
			q = "";
		else
			q = deviceInfoPage.getQ();
		List<DeviceInfo> infoList = new ArrayList<DeviceInfo>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q + "%");
		String hql = "SELECT DISTINCT d.deviceModel FROM DeviceInfo as d where d.deviceModel like :text";
		if (deviceInfoPage.getStationName() != null
				&& !deviceInfoPage.getStationName().trim().equals("")) {
			tMap.put("stationName", deviceInfoPage.getStationName());
			hql += " AND d.detectionLine.inspectionStation.stationName= :stationName";
		}
		if (deviceInfoPage.getDeviceName() != null
				&& !deviceInfoPage.getDeviceName().trim().equals("")) {
			tMap.put("deviceName", "%" + deviceInfoPage.getDeviceName() + "%");
			hql += " AND d.deviceName like :deviceName";
		}
		infoList = deviceInfoDao.find(hql, tMap, 1, 10);
		List<DeviceInfoPage> pageList = new ArrayList<DeviceInfoPage>();
		for (Object info : infoList) {
			DeviceInfoPage page = new DeviceInfoPage();
			page.setDeviceModel(info.toString());
			pageList.add(page);
		}
		return pageList;
	}

	@Override
	public List<DeviceInfoPage> getManufacturers(DeviceInfoPage deviceInfoPage) {
		String q;
		if (deviceInfoPage.getQ() == null
				|| deviceInfoPage.getQ().trim().equals(""))
			q = "";
		else
			q = deviceInfoPage.getQ();
		List<DeviceInfo> infoList = new ArrayList<DeviceInfo>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q + "%");
		String hql = "SELECT DISTINCT d.manufacturer FROM DeviceInfo as d where d.manufacturer like :text";
		if (deviceInfoPage.getStationName() != null
				&& !deviceInfoPage.getStationName().trim().equals("")) {
			tMap.put("stationName", deviceInfoPage.getStationName());
			hql += " AND d.detectionLine.inspectionStation.stationName= :stationName";
		}
		if (deviceInfoPage.getDeviceName() != null
				&& !deviceInfoPage.getDeviceName().trim().equals("")) {
			tMap.put("deviceName", "%" + deviceInfoPage.getDeviceName() + "%");
			hql += " AND d.deviceName like :deviceName";
		}
		infoList = deviceInfoDao.find(hql, tMap, 1, 10);
		List<DeviceInfoPage> pageList = new ArrayList<DeviceInfoPage>();
		for (Object info : infoList) {
			DeviceInfoPage page = new DeviceInfoPage();
			page.setManufacturer(info.toString());
			pageList.add(page);
		}
		return pageList;
	}

	@Override
	public List<DeviceInfoPage> getDeviceInfo(DeviceInfoPage deviceInfoPage) {
		List<DeviceInfoPage> pageList = new ArrayList<DeviceInfoPage>();
		List<DeviceInfo> infoList = new ArrayList<DeviceInfo>();
		String hql = "FROM DeviceInfo as d WHERE d.deviceStatus=0";
		if (deviceInfoPage.getLineId() != null) {
			hql += " AND d.detectionLine.lineId=" + deviceInfoPage.getLineId();
		} else if (deviceInfoPage.getLineName() != null) {
			hql += " AND d.detectionLine.lineName= '"
					+ deviceInfoPage.getLineName() + "'";
		}
		infoList = deviceInfoDao.find(hql, 1, 10);
		for (DeviceInfo info : infoList) {
			DeviceInfoPage page = new DeviceInfoPage();
			page = copyToPage(info);
			pageList.add(page);
		}
		return pageList;
	}

	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((DeviceInfoPage) obj).getId().toString());
		result.add(((DeviceInfoPage) obj).getDeviceName().toString());
		result.add(((DeviceInfoPage) obj).getDeviceModel().toString());
		result.add(((DeviceInfoPage) obj).getManufacturer().toString());
		result.add(((DeviceInfoPage) obj).getLineName().toString());
		if (((DeviceInfoPage) obj).getDeviceStatus() == 0)
			result.add("正常");
		else
			result.add("注销");
		result.add(((DeviceInfoPage) obj).getRemarks().toString());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("设备名称");
		result.add("型号规格");
		result.add("制造厂商");
		result.add("所属检测线");
		result.add("状态");
		result.add("备注");
		return result;
	}

}
