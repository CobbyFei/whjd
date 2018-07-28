package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DeviceInfo;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.MaintainceRecord;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MaintainceRecordPage;
import com.dbsoft.whjd.pageModel.ReferenceMaterialsRecordPage;
import com.dbsoft.whjd.service.IMaintainceRecordService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.ExportUtil;
import com.dbsoft.whjd.util.TrimString;

@Service(value = "maintainceRecordService")
public class MaintainceRecordServiceImpl implements IMaintainceRecordService {
	private IBaseDao<DeviceInfo> deviceInfoDao;
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<MaintainceRecord> recordDao;
	private IBaseDao<InspectionStation> stationDao;

	public IBaseDao<InspectionStation> getStationDao() {
		return stationDao;
	}

	@Resource(name = "baseDao")
	public void setStationDao(IBaseDao<InspectionStation> stationDao) {
		this.stationDao = stationDao;
	}

	public IBaseDao<DeviceInfo> getDeviceInfoDao() {
		return deviceInfoDao;
	}

	@Resource(name = "baseDao")
	public void setDeviceInfoDao(IBaseDao<DeviceInfo> deviceInfoDao) {
		this.deviceInfoDao = deviceInfoDao;
	}

	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}

	@Resource(name = "baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public IBaseDao<MaintainceRecord> getRecordDao() {
		return recordDao;
	}

	@Resource(name = "baseDao")
	public void setRecordDao(IBaseDao<MaintainceRecord> recordDao) {
		this.recordDao = recordDao;
	}

	/**
	 * 将一个MaintainceRecordPage对象复制到MaintainceRecord对象
	 * 
	 * @author gsw
	 */
	public void copyToRecord(MaintainceRecordPage page, MaintainceRecord record) {
		BeanUtils.copyProperties(page, record);
		record.setMaintainceTime(ChangeTimeFormat.getInstance().strToTimeStamp(
				page.getStrMaintainceTime()));
		DeviceInfo deviceInfo = new DeviceInfo();
		SysUser sysUser = new SysUser();
		if (page.getDeviceId() != null) {
			deviceInfo = deviceInfoDao.get("FROM DeviceInfo as d WHERE d.id= "
					+ page.getDeviceId());
			record.setDeviceInfo(deviceInfo);
		}
		if (page.getUserId() != null) {
			sysUser = sysUserDao.get("FROM SysUser as s WHERE s.userId="
					+ page.getUserId());
			record.setSysUser(sysUser);
		}
		if (page.getIntIsNormal() != null) {
			if (page.getIntIsNormal() == 0)
				record.setIsNormal(true);
			else
				record.setIsNormal(false);
		}
	}

	/**
	 * 将一个MaintainceRecord对象复制到MaintainRecordPage对象，并将MaintainceRecordPage对象返回
	 * 
	 * @author gsw
	 */
	public MaintainceRecordPage copyToPage(MaintainceRecord record) {
		MaintainceRecordPage page = new MaintainceRecordPage();
		BeanUtils.copyProperties(record, page);
		page.setStrMaintainceTime(ChangeTimeFormat.getInstance()
				.timeStampToString(record.getMaintainceTime()));
		if (record.getIsNormal() != null) {
			if (record.getIsNormal())
				page.setIntIsNormal(0);
			else
				page.setIntIsNormal(1);
		}
		if (record.getDeviceInfo() != null) {
			page.setDeviceId(record.getDeviceInfo().getId());
			page.setDeviceName(record.getDeviceInfo().getDeviceName());
			if (record.getDeviceInfo().getDetectionLine() != null) {
				page.setLineId(record.getDeviceInfo().getDetectionLine()
						.getLineId());
				page.setLineName(record.getDeviceInfo().getDetectionLine()
						.getLineName());
				page.setStationName(record.getDeviceInfo().getDetectionLine()
						.getInspectionStation().getStationName());
			}
		}
		if (record.getSysUser() != null) {
			page.setUserId(record.getSysUser().getUserId());
			page.setUserName(record.getSysUser().getUserName());
		}
		return page;
	}

	@Override
	public DataGrid getAllMaintainceRecord(MaintainceRecordPage recordPage)
			throws Exception {
		DataGrid dg = new DataGrid();
		recordPage = (MaintainceRecordPage) TrimString.getInstance()
				.trimObjectString(recordPage);
		List<MaintainceRecordPage> recordPageList = new ArrayList<MaintainceRecordPage>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "FROM MaintainceRecord as r WHERE 1=1";
		if (recordPage.getStationName() != null
				&& !recordPage.getStationName().trim().equals("")) {
			params.put("stationName", recordPage.getStationName());
			hql += " AND r.deviceInfo.detectionLine.inspectionStation.stationName= :stationName";
		}
		if (recordPage.getLineId() != null) {
			params.put("lineId", recordPage.getLineId());
			hql += " AND r.deviceInfo.detectionLine.lineId= :lineId";
		}
		if (recordPage.getDeviceName() != null) {
			params.put("deviceName", "%" + recordPage.getDeviceName() + "%");
			hql += " AND r.deviceInfo.deviceName like :deviceName";
		}
		if (recordPage.getUserName() != null) {
			params.put("userName", "%" + recordPage.getUserName() + "%");
			hql += " AND r.sysUser.userName like :userName";
		}
		if (recordPage.getBeginTime() != null
				&& !recordPage.getBeginTime().equals("")) {
			params.put("beginTime", ChangeTimeFormat.getInstance()
					.strToTimeStamp(recordPage.getBeginTime()));
			hql += " AND r.maintainceTime >= :beginTime";
			System.out.println("beginTime: " + recordPage.getBeginTime());
		}
		if (recordPage.getEndTime() != null
				&& !recordPage.getEndTime().equals("")) {
			params.put("endTime", ChangeTimeFormat.getInstance()
					.strToTimeStamp(recordPage.getEndTime()));
			hql += " AND r.maintainceTime <= :endTime";
		}
		if (recordPage.getStatus() != null && recordPage.getStatus() != 2) {
			params.put("status", recordPage.getStatus());
			hql += " AND r.status= :status";
		}
		if (recordPage.getIntIsNormal() != null
				&& recordPage.getIntIsNormal() != 2) {
			if (recordPage.getIntIsNormal() == 0)
				params.put("isNormal", true);
			else
				params.put("isNormal", false);
			hql += " AND r.isNormal= :isNormal";
		}
		List<MaintainceRecord> recordList = recordDao.find(hql, params,
				recordPage.getPage(), recordPage.getRows());
		dg.setTotal(recordDao.count("SELECT COUNT(*)" + hql, params));
		for (MaintainceRecord record : recordList) {
			MaintainceRecordPage page = new MaintainceRecordPage();
			page = copyToPage(record);
			recordPageList.add(page);
		}
		dg.setRows(recordPageList);
		return dg;
	}

	@Override
	public void saveMaintainceRecord(MaintainceRecordPage recordPage)
			throws Exception {
		recordPage = (MaintainceRecordPage) TrimString.getInstance()
				.trimObjectString(recordPage);
		MaintainceRecord record;
		if (recordPage.getRecordId() == null) {
			record = new MaintainceRecord();
			copyToRecord(recordPage, record);
			recordDao.save(record);
		} else {
			record = recordDao.get(MaintainceRecord.class,
					recordPage.getRecordId());
			copyToRecord(recordPage, record);
			recordDao.update(record);
		}
	}

	@Override
	public void deleteMaintainceRecord(MaintainceRecordPage recordPage) {
		if (recordPage.getIds() != null) {
			for (String id : recordPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					MaintainceRecord record = recordDao.get(
							MaintainceRecord.class, Integer.valueOf(id));
					if (record != null) {
						record.setStatus(1);
						recordDao.update(record);
					}
				}
			}
		}
	}

	@Override
	public Json exportDeviceInfo(MaintainceRecordPage recordPage)
			throws Exception {
		List<Object> pageList = new ArrayList<Object>();
		if (recordPage.getIds() != null) {
			for (String id : recordPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					MaintainceRecord record = recordDao.get(
							MaintainceRecord.class, Integer.valueOf(id));
					if (record != null) {
						MaintainceRecordPage page = new MaintainceRecordPage();
						page = copyToPage(record);
						pageList.add(page);
					}
				}
			}
		}
		String filePath = ExportUtil
				.ExportToExcelByResultSet(pageList, null, "formatExcel",
						"formatTitle", MaintainceRecordServiceImpl.class);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		return retJson;
	}

	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((MaintainceRecordPage) obj).getRecordId().toString());
		result.add(((MaintainceRecordPage) obj).getStationName().toString());
		result.add(((MaintainceRecordPage) obj).getLineName().toString());
		result.add(((MaintainceRecordPage) obj).getDeviceName().toString());
		result.add(((MaintainceRecordPage) obj).getUserName().toString());
		result.add(((MaintainceRecordPage) obj).getStrMaintainceTime()
				.toString());
		if (((MaintainceRecordPage) obj).getStatus() == 0)
			result.add("正常");
		else
			result.add("注销");
		if (((MaintainceRecordPage) obj).getIntIsNormal() == 0)
			result.add("正常");
		else
			result.add("不正常");
		result.add(((MaintainceRecordPage) obj).getMeasures().toString());
		result.add(((MaintainceRecordPage) obj).getMaintainceOutcome()
				.toString());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("所属检测站");
		result.add("所属检测线");
		result.add("设备名称");
		result.add("维护人员");
		result.add("维护时间");
		result.add("记录状态");
		result.add("是否正常");
		result.add("采取措施");
		result.add("维护结果");
		return result;
	}
}
