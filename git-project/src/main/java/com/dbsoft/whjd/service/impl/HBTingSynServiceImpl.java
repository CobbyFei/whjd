package com.dbsoft.whjd.service.impl;

import javax.annotation.Resource;

import org.dom4j.DocumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.DeviceInfo;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.LugDownMethod;
import com.dbsoft.whjd.model.LugDownMethodBackup;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.model.RestoreDectectionBaseBackup;
//import com.dbsoft.whjd.model.RestoreLugDownMethodResult;
//import com.dbsoft.whjd.model.RestoreSteadyStateMethodResult;
//import com.dbsoft.whjd.model.RestoreTwoSpeedIdleMethodResult;
import com.dbsoft.whjd.model.TwoSpeedIdleMethodBackup;

import com.dbsoft.whjd.model.RestoreVehicleInfoBackup;
import com.dbsoft.whjd.model.SteadyStateMethod;
import com.dbsoft.whjd.model.SteadyStateMethodBackup;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.TwoSpeedIdleMethod;
import com.dbsoft.whjd.service.IHBTingSynService;
import com.dbsoft.whjd.util.SOAPService;
import com.dbsoft.whjd.service.impl.md5;

import net.sourceforge.jtds.jdbc.DateTime;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hBTingSynService")
public class HBTingSynServiceImpl implements IHBTingSynService {

	private IBaseDao<InspectionStation> inspectionStationDao;
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<DeviceInfo> deviceInfoDao;
	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	private IBaseDao<BlackNameList> blackNameListDao;
	private IBaseDao<ReferenceMaterialsRecord> referenceMaterialsRecordDao;
	private IBaseDao<SteadyStateMethod> steadyStateMethodDao;
	private IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao;
	private IBaseDao<LugDownMethod> lugDownMethodDao;
	private IBaseDao<DetectionLine> detectionLineDao;// 添加于2018-2-4

	//private IBaseDao<RestoreVehicleInfo> restoreVehicleDao;
	private IBaseDao<RestoreVehicleInfoBackup> restoreVehicleBackupDao;
	private IBaseDao<RestoreDectectionBaseBackup> restoreDectectionBaseBackupDao;
	private IBaseDao<TwoSpeedIdleMethodBackup> restoreTwoSpeedIdleMethodResultBackupDao;
	
	private IBaseDao<SteadyStateMethodBackup> restoreSteadyStateMethodResultDao;
	//private IBaseDao<RestoreTwoSpeedIdleMethodResult> restoreTwoSpeedIdleMethodResultDao;
	private IBaseDao<LugDownMethodBackup> restoreLugDownMethodResultDao;

	public IBaseDao<LugDownMethodBackup> getRestoreLugDownMethodResultDao() {
		return restoreLugDownMethodResultDao;
	}

	@Resource(name = "baseDao")
	public void setRestoreLugDownMethodResultBackupDao(IBaseDao<LugDownMethodBackup> restoreLugDownMethodResultDao) {
		this.restoreLugDownMethodResultDao = restoreLugDownMethodResultDao;
	}

	public IBaseDao<TwoSpeedIdleMethodBackup> getRestoreTwoSpeedIdleMethodResultBackupDao() {
		return restoreTwoSpeedIdleMethodResultBackupDao;
	}

	@Resource(name = "baseDao")
	public void setRestoreTwoSpeedIdleMethodResultBackupDao(
			IBaseDao<TwoSpeedIdleMethodBackup> restoreTwoSpeedIdleMethodResultBackupDao) {
		this.restoreTwoSpeedIdleMethodResultBackupDao = restoreTwoSpeedIdleMethodResultBackupDao;
	}

	public IBaseDao<SteadyStateMethodBackup> getRestoreSteadyStateMethodResultDao() {
		return restoreSteadyStateMethodResultDao;
	}

	@Resource(name = "baseDao")
	public void setRestoreSteadyStateMethodResultDao(
			IBaseDao<SteadyStateMethodBackup> restoreSteadyStateMethodResultDao) {
		this.restoreSteadyStateMethodResultDao = restoreSteadyStateMethodResultDao;
	}

	public IBaseDao<RestoreDectectionBaseBackup> getRestoreDectectionBaseBackupDao() {
		return restoreDectectionBaseBackupDao;
	}

	@Resource(name = "baseDao")
	public void setRestoreDectectionBaseBackupDao(IBaseDao<RestoreDectectionBaseBackup> restoreDectectionBaseBackupDao) {
		this.restoreDectectionBaseBackupDao = restoreDectectionBaseBackupDao;
	}

	public IBaseDao<RestoreVehicleInfoBackup> getRestoreVehicleDao() {
		return restoreVehicleBackupDao;
	}

	@Resource(name = "baseDao")
	public void setRestoreVehicleDao(IBaseDao<RestoreVehicleInfoBackup> restoreVehicleBackupDao) {
		this.restoreVehicleBackupDao = restoreVehicleBackupDao;
	}

	// 添加于2018-2-4
	public IBaseDao<DetectionLine> getDetectionLineDao() {
		return detectionLineDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionLineDao(IBaseDao<DetectionLine> detectionLineDao) {
		this.detectionLineDao = detectionLineDao;
	}

	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}

	@Resource(name = "baseDao")
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}

	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}

	@Resource(name = "baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public IBaseDao<DeviceInfo> getDeviceInfoDao() {
		return deviceInfoDao;
	}

	@Resource(name = "baseDao")
	public void setDeviceInfoDao(IBaseDao<DeviceInfo> deviceInfoDao) {
		this.deviceInfoDao = deviceInfoDao;
	}

	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}

	public IBaseDao<BlackNameList> getBlackNameListDao() {
		return blackNameListDao;
	}

	@Resource(name = "baseDao")
	public void setBlackNameListDao(IBaseDao<BlackNameList> blackNameListDao) {
		this.blackNameListDao = blackNameListDao;
	}

	public IBaseDao<ReferenceMaterialsRecord> getReferenceMaterialsRecordDao() {
		return referenceMaterialsRecordDao;
	}

	@Resource(name = "baseDao")
	public void setReferenceMaterialsRecordDao(IBaseDao<ReferenceMaterialsRecord> referenceMaterialsRecordDao) {
		this.referenceMaterialsRecordDao = referenceMaterialsRecordDao;
	}

	public IBaseDao<SteadyStateMethod> getSteadyStateMethodDao() {
		return steadyStateMethodDao;
	}

	@Resource(name = "baseDao")
	public void setSteadyStateMethodDao(IBaseDao<SteadyStateMethod> steadyStateMethodDao) {
		this.steadyStateMethodDao = steadyStateMethodDao;
	}

	public IBaseDao<TwoSpeedIdleMethod> getTwoSpeedIdleMethodDao() {
		return twoSpeedIdleMethodDao;
	}

	@Resource(name = "baseDao")
	public void setTwoSpeedIdleMethodDao(IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao) {
		this.twoSpeedIdleMethodDao = twoSpeedIdleMethodDao;
	}

	public IBaseDao<LugDownMethod> getLugDownMethodDao() {
		return lugDownMethodDao;
	}

	@Resource(name = "baseDao")
	public void setLugDownMethodDao(IBaseDao<LugDownMethod> lugDownMethodDao) {
		this.lugDownMethodDao = lugDownMethodDao;
	}

	@Override
	public void uploadStation() throws JSONException {
		String hql = "FROM InspectionStation as r where r.isPush != 1 or r.isPush is NULL ";
		List<InspectionStation> inspectionStaionList = inspectionStationDao.find(hql);
		for (InspectionStation station : inspectionStaionList) {
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject stationInfo = new JSONObject();

			// get站点负责人
			SysUser sysUser = station.getStationManagements().iterator().next().getSysUser();
			stationInfo.put("JCZBH", "340200" + station.getStationId());
			stationInfo.put("XKZH", "A1000001");
			if (station.getRegistrationTime() != null)
				stationInfo.put("DJRQ", station.getRegistrationTime().toString().substring(0, 10));
			else
				stationInfo.put("DJRQ", "2016-01-01");

			if (station.getStationName() != null)
				stationInfo.put("TestStation", station.getStationName());
			else
				stationInfo.put("TestStation", "检测站");

			if (station.getQulificationTime() != null)
				stationInfo.put("WTRQ", station.getQulificationTime().toString().substring(0, 10));
			else
				stationInfo.put("WTRQ", "2016-01-01");

			if (station.getStationAddress() != null || !station.getStationAddress().equals(""))
				stationInfo.put("JYDZ", station.getStationAddress());
			else
				stationInfo.put("JYDZ", "地址");
			if (sysUser == null) {
				stationInfo.put("FRDBXM", "法人代表");
				stationInfo.put("FZR", "负责人");
			} else {
				stationInfo.put("FRDBXM", sysUser.getUserName());
				stationInfo.put("FZR", sysUser.getUserName());
			}

			stationInfo.put("ZYZS", station.getSysUsers().size());
			if (station.getRemarks() == null || station.getRemarks().equals(""))
				stationInfo.put("JCZDH", "138XXXXXXXX");
			else
				stationInfo.put("JCZDH", station.getRemarks());
			stationInfo.put("XKZYXQZ", station.getValidPeriod().toString().substring(0, 10));
			stationInfo.put("ZZJGDM", "340200" + station.getStationId());
			stationInfo.put("SCHJWTRQ", station.getRegistrationTime().toString().substring(0, 10));
			stationInfo.put("CZSGRS", station.getSysUsers().size());
			stationInfo.put("SFCDAJ", "1");
			stationInfo.put("SFCDZJ", "0");
			jsonArray.put(stationInfo);
			jsonObject.put("H_JCZ_JBXX", jsonArray);
			System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("ZZ001", jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			if (res.get("code").equals("0")) {
				station.setIsPush(1);
				inspectionStationDao.update(station);
			}
		}
	}

	@Override
	public void uploadSysUser() throws JSONException {
		String hql = "FROM SysUser as r where r.isPush != 1 or r.isPush is NULL";
		List<SysUser> sysUserList = sysUserDao.find(hql);
		for (SysUser sysuser : sysUserList) {
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject sysUserInfo = new JSONObject();

			sysUserInfo.put("JCZBH", "340200" + sysuser.getInspectionStation().getStationId());
			sysUserInfo.put("HGZBH", "A2000001");
			sysUserInfo.put("PersonName", sysuser.getUserName());
			// sysUserInfo.put("Sex", sysuser.getSex());
			sysUserInfo.put("HGZFZRQ", sysuser.getCertificateTime());
			sysUserInfo.put("HGZYXQ", "2020-01-01");
			sysUserInfo.put("JobCertificateNo", "340200" + sysuser.getUserId());
			sysUserInfo.put("JobDuty", sysuser.getJobTitle());
			sysUserInfo.put("State", 1);

			jsonArray.put(sysUserInfo);
			jsonObject.put("H_JCZ_RY", jsonArray);
			System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("RY001", jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			if (res.get("code").equals("0")) {
				sysuser.setIsPush(1);
				sysUserDao.update(sysuser);
			}
		}
	}

	@Override
	public void uploadDeteLine() throws JSONException {
		String hql = "FROM DeviceInfo as r where r.isPush != 1 or r.isPush is NULL or r.isPush!=2";
		List<DeviceInfo> deviceInfoList = deviceInfoDao.find(hql);
		// int records=0;
		for (DeviceInfo deviceInfo : deviceInfoList) {
			// records+=1;
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject sysUserInfo = new JSONObject();

			sysUserInfo.put("JCZBH", "340200" + deviceInfo.getDetectionLine().getInspectionStation().getStationId());
			// sysUserInfo.put("JCXDH",deviceInfo.getId());
			// 修改于2018-2-4,将检测线id格式化为两个字符串
			int locID = deviceInfo.getDetectionLine().getLocaleId();
			if (locID <= 99) {
				sysUserInfo.put("JCXDH", String.format("%02d", locID));
			} else {
				sysUserInfo.put("JCXDH", String.format("%02d", locID % 100));
			}
			sysUserInfo.put("JCFF", 2);
			sysUserInfo.put("JCXZT", 1);
			sysUserInfo.put("SBNAME", deviceInfo.getDeviceName());
			sysUserInfo.put("SBXH", deviceInfo.getDeviceModel());
			sysUserInfo.put("JDYXQZ", "2020-01-01");

			jsonArray.put(sysUserInfo);
			jsonObject.put("H_JCZ_SB", jsonArray);
			System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("SB001", jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			if (res.get("code").equals("0")) {
				updateDeteLine(deviceInfo, 1);
				// deviceInfo.setIsPush(1);
			} else {
				updateDeteLine(deviceInfo, 2);
				// deviceInfo.setIsPush(2);
			}
			deviceInfoDao.update(deviceInfo);
			// deviceInfoDao.update(deviceInfo);
			// if(records%100==0 || records==deviceInfoList.size()){
			// deviceInfoDao.flush();
			// }

		}

	}

	@Override
	public void uploadVehicleInfo() throws Exception {
		System.out.println("开始上传车辆信息！！");
		String hql = "FROM RestoreVehicleInfoBackup";
		List<RestoreVehicleInfoBackup> VehicleInfoSheetListBackup = restoreVehicleBackupDao.find(hql);
		System.out.println(VehicleInfoSheetListBackup.size());
		for (RestoreVehicleInfoBackup vehicleInfoSheetBackup : VehicleInfoSheetListBackup) {
			System.out.println("委托单ID号为：" + vehicleInfoSheetBackup.getId());
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject vehicleInfoSheetBackupInfo = new JSONObject();
			

			vehicleInfoSheetBackupInfo.put("Vehicle",vehicleInfoSheetBackup.getVehicle());
			vehicleInfoSheetBackupInfo.put("CPHM",vehicleInfoSheetBackup.getCphm());
			vehicleInfoSheetBackupInfo.put("CLLX",vehicleInfoSheetBackup.getCllx());
			vehicleInfoSheetBackupInfo.put("CZMC",vehicleInfoSheetBackup.getCzmc());
			vehicleInfoSheetBackupInfo.put("LXDZ",vehicleInfoSheetBackup.getLxdz());
			vehicleInfoSheetBackupInfo.put("LXDH",vehicleInfoSheetBackup.getLxdh());
			vehicleInfoSheetBackupInfo.put("CPMC",vehicleInfoSheetBackup.getCpmc());
			vehicleInfoSheetBackupInfo.put("CLXH",vehicleInfoSheetBackup.getClxh());
			vehicleInfoSheetBackupInfo.put("SYXZ",vehicleInfoSheetBackup.getSyxz());
			vehicleInfoSheetBackupInfo.put("CLSBM",vehicleInfoSheetBackup.getClsbm());
			vehicleInfoSheetBackupInfo.put("ZDZZL",vehicleInfoSheetBackup.getZdzzl());
			vehicleInfoSheetBackupInfo.put("JZZL",vehicleInfoSheetBackup.getJzzl());
			vehicleInfoSheetBackupInfo.put("CCRQ",vehicleInfoSheetBackup.getCcrq());
			vehicleInfoSheetBackupInfo.put("FZRQ",vehicleInfoSheetBackup.getFzrq());
			vehicleInfoSheetBackupInfo.put("CCDJRQ",vehicleInfoSheetBackup.getCcdjrq());
			vehicleInfoSheetBackupInfo.put("FDJXH",vehicleInfoSheetBackup.getFdjxh());
			vehicleInfoSheetBackupInfo.put("FDJH",vehicleInfoSheetBackup.getFdjh());
			vehicleInfoSheetBackupInfo.put("LJXSLC",vehicleInfoSheetBackup.getLjxslc());
			vehicleInfoSheetBackupInfo.put("JCFF",vehicleInfoSheetBackup.getJcff());
			vehicleInfoSheetBackupInfo.put("PFBZ",vehicleInfoSheetBackup.getPfbz());
			vehicleInfoSheetBackupInfo.put("QDFS",vehicleInfoSheetBackup.getQdfs());
			vehicleInfoSheetBackupInfo.put("QDLTQY",vehicleInfoSheetBackup.getQdltqy());
			vehicleInfoSheetBackupInfo.put("FDJPL",vehicleInfoSheetBackup.getFdjpl());

			jsonArray.put(vehicleInfoSheetBackupInfo);
			jsonObject.put("VEHICLE", jsonArray);
			//System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("CL002", jsonObject);
				System.out.println("成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void uploadBlackName() throws JSONException {
		String hql = "FROM BlackNameList as r where r.isPush != 1 or r.isPush is NULL";
		List<BlackNameList> blackNameList = blackNameListDao.find(hql);
		for (BlackNameList BlackName : blackNameList) {

			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject BlackNameInfo = new JSONObject();

			BlackNameInfo.put("LOCK_ID", "340200" + BlackName.getId());
			BlackNameInfo.put("CPHM", BlackName.getLicence());
			BlackNameInfo.put("CPYS", BlackName.getLicenceColor());
			BlackNameInfo.put("SDRQ", BlackName.getViolationTime());
			BlackNameInfo.put("SDR", "锁定人");
			if (BlackName.getViolationType().equals("1"))
				BlackNameInfo.put("SDYY", "违规限行");
			else
				BlackNameInfo.put("SDYY", "超标排放");
			BlackNameInfo.put("SFJS", BlackName.getIsCancel() ? 1 : 0);

			jsonArray.put(BlackNameInfo);
			jsonObject.put("H_BASE_LOCK", jsonArray);
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("LK001", jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			System.out.println(res.get("code") + "=====" + res.get("code").equals("0"));
			if (res.get("code").equals("0")) {
				BlackName.setIsPush(1);
				blackNameListDao.update(BlackName);
			}
		}

	}

	@Override
	public void uploadReferenceMaterials() throws JSONException {
		String hql = "FROM ReferenceMaterialsRecord as r where r.isPush != 1 or r.isPush is NULL";
		List<ReferenceMaterialsRecord> referenceMaterialsRecordList = referenceMaterialsRecordDao.find(hql);
		for (ReferenceMaterialsRecord referenceMaterialsRecord : referenceMaterialsRecordList) {
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject referenceMaterialsRecordInfo = new JSONObject();

			referenceMaterialsRecordInfo.put("JCZBH", "340200" + referenceMaterialsRecord.getRecordId());
			referenceMaterialsRecordInfo.put("BZWZLX", referenceMaterialsRecord.getMaterialName());
			referenceMaterialsRecordInfo.put("BZWZZZDW", referenceMaterialsRecord.getManufacturer());
			referenceMaterialsRecordInfo.put("JDYXQZ", "2010-01-01");

			jsonArray.put(referenceMaterialsRecordInfo);
			jsonObject.put("H_JCZ_BZWZ", jsonArray);
			System.out.print(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("SB002", jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray res_json_arr = (JSONArray) res_json.get("SYSTEM");
			JSONObject res = (JSONObject) res_json_arr.get(0);
			if (res.get("code").equals("0")) {
				referenceMaterialsRecord.setIsPush(1);
				referenceMaterialsRecordDao.update(referenceMaterialsRecord);
			}
		}
	}

	@Override
	public void uploadDectectionBase() throws Exception {

		String hql = "FROM RestoreDectectionBaseBackup";
		List<RestoreDectectionBaseBackup> restoreDectectionBaseListBackup = restoreDectectionBaseBackupDao.find(hql);
		for (RestoreDectectionBaseBackup restoreDectectionBaseBackup : restoreDectectionBaseListBackup) {
			System.out.println("委托单编号：" + restoreDectectionBaseBackup.getId());
			//RestoreDectectionBaseBackup restoreDectectionBase = new RestoreDectectionBaseBackup();
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject detectionCommisionSheetInfo = new JSONObject();
			
			detectionCommisionSheetInfo.put("TestNo",restoreDectectionBaseBackup.getTestNo());
			detectionCommisionSheetInfo.put("JCZBH",restoreDectectionBaseBackup.getJczbh());
			detectionCommisionSheetInfo.put("JCZMC",restoreDectectionBaseBackup.getJczmc());
			detectionCommisionSheetInfo.put("WTZSBH",restoreDectectionBaseBackup.getWtzsbh());
			detectionCommisionSheetInfo.put("jcrq",restoreDectectionBaseBackup.getJcrq());
			detectionCommisionSheetInfo.put("JCLRY",restoreDectectionBaseBackup.getJclry());
			detectionCommisionSheetInfo.put("JCCZY",restoreDectectionBaseBackup.getJcczy());
			detectionCommisionSheetInfo.put("JCJSY",restoreDectectionBaseBackup.getJcjsy());
			detectionCommisionSheetInfo.put("CLSBDH",restoreDectectionBaseBackup.getClsbdh());
			detectionCommisionSheetInfo.put("CPHM",restoreDectectionBaseBackup.getCphm());
			detectionCommisionSheetInfo.put("VehicleType",restoreDectectionBaseBackup.getVehicleType());
			detectionCommisionSheetInfo.put("CLLX",restoreDectectionBaseBackup.getCllx());
			detectionCommisionSheetInfo.put("CCRQ",restoreDectectionBaseBackup.getCcrq());
			detectionCommisionSheetInfo.put("YYXZ",restoreDectectionBaseBackup.getYyxz());
			detectionCommisionSheetInfo.put("CZMC",restoreDectectionBaseBackup.getCzmc());
			detectionCommisionSheetInfo.put("CCDJRQ",restoreDectectionBaseBackup.getCcdjrq());
			detectionCommisionSheetInfo.put("RLZL",restoreDectectionBaseBackup.getRlzl());
			detectionCommisionSheetInfo.put("TestType",restoreDectectionBaseBackup.getTestType());
			detectionCommisionSheetInfo.put("PFBZ",restoreDectectionBaseBackup.getPfbz());
			detectionCommisionSheetInfo.put("HBFLBZ",restoreDectectionBaseBackup.getHbflbz());
			detectionCommisionSheetInfo.put("JCCS",restoreDectectionBaseBackup.getJccs());
			detectionCommisionSheetInfo.put("WD",restoreDectectionBaseBackup.getWd());
			detectionCommisionSheetInfo.put("SD",restoreDectectionBaseBackup.getSd());
			detectionCommisionSheetInfo.put("DQY",restoreDectectionBaseBackup.getDqy());
			detectionCommisionSheetInfo.put("pdjg",restoreDectectionBaseBackup.getPdjg());
			detectionCommisionSheetInfo.put("jclxbh",restoreDectectionBaseBackup.getJclxbh());
			
			jsonArray.put(detectionCommisionSheetInfo);
			jsonObject.put("H_DATA_BASE", jsonArray);
			System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("JC000", jsonObject);
				System.out.println("成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			restoreDectectionBase.setTestNo(restoreDectectionBaseBackup.getTestNo());
//			System.out.println(restoreDectectionBaseBackup.getTestNo());
//			restoreDectectionBase.setJczbh(restoreDectectionBaseBackup.getJczbh());
//			restoreDectectionBase.setJczmc(restoreDectectionBaseBackup.getJczmc());
//			restoreDectectionBase.setWtzsbh(restoreDectectionBaseBackup.getWtzsbh());
//			restoreDectectionBase.setJcrq(restoreDectectionBaseBackup.getJcrq());
//			restoreDectectionBase.setJclry(restoreDectectionBaseBackup.getJclry());
//			restoreDectectionBase.setJcczy(restoreDectectionBaseBackup.getJcczy());
//			restoreDectectionBase.setJcjsy(restoreDectectionBaseBackup.getJcjsy());
//			restoreDectectionBase.setClsbdh(restoreDectectionBaseBackup.getClsbdh());
//			restoreDectectionBase.setCphm(restoreDectectionBaseBackup.getCphm());
//			restoreDectectionBase.setVehicleType(restoreDectectionBaseBackup.getVehicleType());
//			restoreDectectionBase.setCllx(restoreDectectionBaseBackup.getCllx());
//			restoreDectectionBase.setCcrq(restoreDectectionBaseBackup.getCcrq());
//			restoreDectectionBase.setYyxz(restoreDectectionBaseBackup.getYyxz());
//			restoreDectectionBase.setCzmc(restoreDectectionBaseBackup.getCzmc());
//			restoreDectectionBase.setCcdjrq(restoreDectectionBaseBackup.getCcdjrq());
//			restoreDectectionBase.setRlzl(restoreDectectionBaseBackup.getRlzl());
//			restoreDectectionBase.setTestType(restoreDectectionBaseBackup.getTestType());
//			restoreDectectionBase.setPfbz(restoreDectectionBaseBackup.getPfbz());
//			restoreDectectionBase.setHbflbz(restoreDectectionBaseBackup.getHbflbz());
//			restoreDectectionBase.setJccs(restoreDectectionBaseBackup.getJccs());
//			restoreDectectionBase.setWd(restoreDectectionBaseBackup.getWd());
//			restoreDectectionBase.setSd(restoreDectectionBaseBackup.getSd());
//			restoreDectectionBase.setDqy(restoreDectectionBaseBackup.getDqy());
//			restoreDectectionBase.setPdjg(restoreDectectionBaseBackup.getPdjg());
//			restoreDectectionBase.setJclxbh(restoreDectectionBaseBackup.getJclxbh());

//			try {
//				restoreDectectionBaseBackupDao.saveOrUpdate(detectionCommisionSheetInfo);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override
	public void uploadSteadyStateMethodResult() throws Exception {
		String hql = "FROM SteadyStateMethodBackup";
		List<SteadyStateMethodBackup> steadyStateMethodListBackup = restoreSteadyStateMethodResultDao.find(hql);
		for (SteadyStateMethodBackup steadyStateMethodBackup : steadyStateMethodListBackup) {
			System.out.println("稳态法编号为：" + steadyStateMethodBackup.getId());
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject detectionCommisionSheetInfo = new JSONObject();

			detectionCommisionSheetInfo.put("JCBGBH",steadyStateMethodBackup.getJcbgbh());
			detectionCommisionSheetInfo.put("HC5025Limit",steadyStateMethodBackup.getHc5025limit());
			detectionCommisionSheetInfo.put("HC5025",steadyStateMethodBackup.getHc5025());
			detectionCommisionSheetInfo.put("HC5025Judge",steadyStateMethodBackup.getHc5025judge());
			detectionCommisionSheetInfo.put("CO5025Limit",steadyStateMethodBackup.getCo5025limit());
			detectionCommisionSheetInfo.put("CO5025",steadyStateMethodBackup.getCo5025());
			detectionCommisionSheetInfo.put("CO5025Judge",steadyStateMethodBackup.getCo5025judge());
			detectionCommisionSheetInfo.put("NO5025Limit",steadyStateMethodBackup.getNo5025limit());
			detectionCommisionSheetInfo.put("NO5025",steadyStateMethodBackup.getNo5025());
			detectionCommisionSheetInfo.put("NO5025Judge",steadyStateMethodBackup.getNo5025judge());
			detectionCommisionSheetInfo.put("FDJZS5025",steadyStateMethodBackup.getFdjzs5025());
			detectionCommisionSheetInfo.put("FDJYW5025",steadyStateMethodBackup.getFdjyw5025());
			detectionCommisionSheetInfo.put("HC2540Limit",steadyStateMethodBackup.getHc2540limit());
			detectionCommisionSheetInfo.put("HC2540",steadyStateMethodBackup.getHc2540());
			detectionCommisionSheetInfo.put("HC2540Judge",steadyStateMethodBackup.getHc2540judge());
			detectionCommisionSheetInfo.put("CO2540Limit",steadyStateMethodBackup.getCo2540limit());
			detectionCommisionSheetInfo.put("CO2540",steadyStateMethodBackup.getCo2540());
			detectionCommisionSheetInfo.put("CO2540Judge",steadyStateMethodBackup.getCo2540judge());
			detectionCommisionSheetInfo.put("NO2540Limit",steadyStateMethodBackup.getNo2540limit());
			detectionCommisionSheetInfo.put("NO2540",steadyStateMethodBackup.getNo2540());
			detectionCommisionSheetInfo.put("NO2540Judge",steadyStateMethodBackup.getNo2540judge());
			detectionCommisionSheetInfo.put("FDJZS2540",steadyStateMethodBackup.getFdjzs2540());
			detectionCommisionSheetInfo.put("FDJYW2540",steadyStateMethodBackup.getFdjyw2540());

			jsonArray.put(detectionCommisionSheetInfo);
			jsonObject.put("H_DATA_BASE", jsonArray);
			//System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("JC002", jsonObject);
				System.out.println("成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void uploadSteadyStateMethodProcess() throws JSONException {
		// TODO

	}

	@Override
	public void uploadTwoSpeedIdleMethodResult() throws Exception {
		String hql = "FROM TwoSpeedIdleMethodBackup";
		List<TwoSpeedIdleMethodBackup> twoSpeedIdleMethodListBackup = restoreTwoSpeedIdleMethodResultBackupDao.find(hql);
		for (TwoSpeedIdleMethodBackup twoSpeedIdleMethodBackup : twoSpeedIdleMethodListBackup) {
			System.out.println("双怠速编号为：" + twoSpeedIdleMethodBackup.getId());
			//RestoreTwoSpeedIdleMethodResultBackup restoreTwoSpeedIdleMethodResultbackup = new RestoreTwoSpeedIdleMethodResultBackup();
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject detectionCommisionSheetInfo = new JSONObject();
			
			detectionCommisionSheetInfo.put("JCBGBH",twoSpeedIdleMethodBackup.getJcbgbh());
			detectionCommisionSheetInfo.put("Lambda",twoSpeedIdleMethodBackup.getLambda());
			detectionCommisionSheetInfo.put("LambdaLimit",twoSpeedIdleMethodBackup.getLambdaLimit());
			detectionCommisionSheetInfo.put("LambdaJudge",twoSpeedIdleMethodBackup.getLambdaJudge());
			detectionCommisionSheetInfo.put("LowIdleCOLimit",twoSpeedIdleMethodBackup.getLowIdleColimit());
			detectionCommisionSheetInfo.put("LowIdleCO",twoSpeedIdleMethodBackup.getLowIdleCo());
			detectionCommisionSheetInfo.put("LowIdleCOJudge",twoSpeedIdleMethodBackup.getLowIdleCojudge());
			detectionCommisionSheetInfo.put("LowIdleHCLimit",twoSpeedIdleMethodBackup.getLowIdleHclimit());
			detectionCommisionSheetInfo.put("LowIdleHC",twoSpeedIdleMethodBackup.getLowIdleHc());
			detectionCommisionSheetInfo.put("LowIdleHCJudge",twoSpeedIdleMethodBackup.getLowIdleHcjudge());
			detectionCommisionSheetInfo.put("FDJDSZS",twoSpeedIdleMethodBackup.getFdjdszs());
			detectionCommisionSheetInfo.put("DDSJYWD",twoSpeedIdleMethodBackup.getDdsjywd());
			detectionCommisionSheetInfo.put("HighIdleCOLimit",twoSpeedIdleMethodBackup.getHighIdleColimit());
			detectionCommisionSheetInfo.put("HighIdleCO",twoSpeedIdleMethodBackup.getHighIdleCo());
			detectionCommisionSheetInfo.put("HighIdleCOJudge",twoSpeedIdleMethodBackup.getHighIdleCojudge());
			detectionCommisionSheetInfo.put("HighIdleHCLimit",twoSpeedIdleMethodBackup.getHighIdleHclimit());
			detectionCommisionSheetInfo.put("HighIdleHC",twoSpeedIdleMethodBackup.getHighIdleHc());
			detectionCommisionSheetInfo.put("HighIdleHCJudge",twoSpeedIdleMethodBackup.getHighIdleHcjudge());
			detectionCommisionSheetInfo.put("GDSZS",twoSpeedIdleMethodBackup.getGdszs());
			detectionCommisionSheetInfo.put("GDSJYWD",twoSpeedIdleMethodBackup.getGdsjywd());
			
			jsonArray.put(detectionCommisionSheetInfo);
			jsonObject.put("H_DATA_BASE", jsonArray);
			//System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("JC004", jsonObject);
				System.out.println("成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void uploadTwoSpeedIdleMethodProcess() throws JSONException {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadLugDownMethodResult() throws Exception {
		String hql = "FROM LugDownMethodBackup";
		List<LugDownMethodBackup> lugDownMethodListBackup = restoreLugDownMethodResultDao.find(hql);
		for (LugDownMethodBackup lugDownMethodBackup : lugDownMethodListBackup) {
			System.out.println("加载减速法编号为：" + lugDownMethodBackup.getId());
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject detectionCommisionSheetInfo = new JSONObject();
			
			detectionCommisionSheetInfo.put("JCBGBH",lugDownMethodBackup.getJcbgbh());
			detectionCommisionSheetInfo.put("VELMAXHP",lugDownMethodBackup.getVelmaxhp());
			detectionCommisionSheetInfo.put("VELMAXHPZS",lugDownMethodBackup.getVelmaxhpzs());
			detectionCommisionSheetInfo.put("MaxPower",lugDownMethodBackup.getMaxPower());
			detectionCommisionSheetInfo.put("MaxPowerLimit",lugDownMethodBackup.getMaxPowerLimit());
			detectionCommisionSheetInfo.put("ZDLBGLPDJG",lugDownMethodBackup.getZdlbglpdjg());
			detectionCommisionSheetInfo.put("SmokeKLimit",lugDownMethodBackup.getSmokeKlimit());
			detectionCommisionSheetInfo.put("YDPDJG",lugDownMethodBackup.getYdpdjg());
			detectionCommisionSheetInfo.put("K100",lugDownMethodBackup.getK100());
			detectionCommisionSheetInfo.put("K90",lugDownMethodBackup.getK90());
			detectionCommisionSheetInfo.put("K80",lugDownMethodBackup.getK80());
			detectionCommisionSheetInfo.put("FDJZSXZ",lugDownMethodBackup.getFdjzsxz());
			detectionCommisionSheetInfo.put("FDJZSPD",lugDownMethodBackup.getFdjzspd());
			detectionCommisionSheetInfo.put("YW",lugDownMethodBackup.getYw());

			jsonArray.put(detectionCommisionSheetInfo);
			jsonObject.put("H_DATA_BASE", jsonArray);
			//System.out.println(jsonObject.toString());
			SOAPService soapService = new SOAPService();
			JSONObject res_json = null;
			try {
				res_json = soapService.HBTingUpload("JC004", jsonObject);
				System.out.println("成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void uploadLugDownMethodProcess() throws JSONException {
		// TODO Auto-generated method stub

	}

	public void updateDeteLine(DeviceInfo deviceInfo, int flag) {
		deviceInfo.setIsPush(flag);
		deviceInfoDao.update(deviceInfo);
	}

	public void updateDetectionCommisionSheet0(DetectionCommisionSheet detectionCommisionSheet, int flag) {
		detectionCommisionSheet.setIsPush(flag);
		detectionCommisionSheetDao.update(detectionCommisionSheet);
	}

	public void updateDetectionCommisionSheet1(DetectionCommisionSheet detectionCommisionSheet, int flag) {
		detectionCommisionSheet.setIsDecBasePush(flag);
		detectionCommisionSheetDao.update(detectionCommisionSheet);
	}

	public void updateSteadyStateMethod(SteadyStateMethod steadyStateMethod, int flag) {
		steadyStateMethod.setIsDetResultPush(flag);
		steadyStateMethodDao.update(steadyStateMethod);
	}

	public void updateTwoSpeedIdleMethod(TwoSpeedIdleMethod twoSpeedIdleMethod, int flag) {
		twoSpeedIdleMethod.setIsDetResultPush(flag);
		twoSpeedIdleMethodDao.update(twoSpeedIdleMethod);
	}

	public void updateLugDownMethod(LugDownMethod lugDownMethod, int flag) {
		lugDownMethod.setIsDetResultPush(flag);
		lugDownMethodDao.update(lugDownMethod);
	}

}
