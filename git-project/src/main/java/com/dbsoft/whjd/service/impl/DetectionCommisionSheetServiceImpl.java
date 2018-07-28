package com.dbsoft.whjd.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.DocumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.BlackNameList;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.FreeAccelerationMethod;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.LugDownMethod;
import com.dbsoft.whjd.model.MotorTwoSpeedIdleMethod;
import com.dbsoft.whjd.model.SteadyStateMethod;
import com.dbsoft.whjd.model.TwoSpeedIdleMethod;
import com.dbsoft.whjd.model.VehicleInfo;
import com.dbsoft.whjd.model.VehicleTypeInfo;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DetectionCommisionSheetPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IDetectionCommisionSheetService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.DateFormat;
import com.dbsoft.whjd.util.ExportUtil;
import com.dbsoft.whjd.util.SOAPService;
import com.dbsoft.whjd.util.TrimString;

@Service("detectionCommisionSheetService")
public class DetectionCommisionSheetServiceImpl implements IDetectionCommisionSheetService {
	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	private IBaseDao<VehicleInfo> VehicleInfoDao;
	private IBaseDao<VehicleTypeInfo> vehicleTypeInfoDao;
	private IBaseDao<InspectionStation> inspectionStationDao;
	private IBaseDao<BlackNameList> blackNameListDao;
	private IBaseDao<LugDownMethod> lugDownMethodDao;
	private IBaseDao<SteadyStateMethod> steayStateMehodDao;
	private IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao;
	private IBaseDao<FreeAccelerationMethod> freeAcclerationMethodDao;
	private IBaseDao<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleDao;

	public IBaseDao<MotorTwoSpeedIdleMethod> getMotorTwoSpeedIdleDao() {
		return motorTwoSpeedIdleDao;
	}

	@Resource(name = "baseDao")
	public void setMotorTwoSpeedIdleDao(IBaseDao<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleDao) {
		this.motorTwoSpeedIdleDao = motorTwoSpeedIdleDao;
	}

	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}

	@Resource(name = "baseDao")
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}

	public IBaseDao<LugDownMethod> getLugDownMethodDao() {
		return lugDownMethodDao;
	}

	@Resource(name = "baseDao")
	public void setLugDownMethodDao(IBaseDao<LugDownMethod> lugDownMethodDao) {
		this.lugDownMethodDao = lugDownMethodDao;
	}

	public IBaseDao<SteadyStateMethod> getSteayStateMehodDao() {
		return steayStateMehodDao;
	}

	@Resource(name = "baseDao")
	public void setSteayStateMehodDao(IBaseDao<SteadyStateMethod> steayStateMehodDao) {
		this.steayStateMehodDao = steayStateMehodDao;
	}

	public IBaseDao<TwoSpeedIdleMethod> getTwoSpeedIdleMethodDao() {
		return twoSpeedIdleMethodDao;
	}

	@Resource(name = "baseDao")
	public void setTwoSpeedIdleMethodDao(IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao) {
		this.twoSpeedIdleMethodDao = twoSpeedIdleMethodDao;
	}

	public IBaseDao<FreeAccelerationMethod> getFreeAcclerationMethodDao() {
		return freeAcclerationMethodDao;
	}

	@Resource(name = "baseDao")
	public void setFreeAcclerationMethodDao(IBaseDao<FreeAccelerationMethod> freeAcclerationMethodDao) {
		this.freeAcclerationMethodDao = freeAcclerationMethodDao;
	}

	public IBaseDao<BlackNameList> getBlackNameListDao() {
		return blackNameListDao;
	}

	@Resource(name = "baseDao")
	public void setBlackNameListDao(IBaseDao<BlackNameList> blackNameListDao) {
		this.blackNameListDao = blackNameListDao;
	}

	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}
	
	public IBaseDao<VehicleTypeInfo> getVehicleTypeInfoDao() {
		return vehicleTypeInfoDao;
	}
	@Resource(name = "baseDao")
	public void setVehicleTypeInfoDao(IBaseDao<VehicleTypeInfo> vehicleTypeInfoDao) {
		this.vehicleTypeInfoDao = vehicleTypeInfoDao;
	}

	public IBaseDao<VehicleInfo> getVehicleInfoDao() {
		return VehicleInfoDao;
	}
	@Resource(name = "baseDao")
	public void setVehicleInfoDao(IBaseDao<VehicleInfo> vehicleInfoDao) {
		VehicleInfoDao = vehicleInfoDao;
	}

	/**
	 * 新建检测委托单
	 */
	@Override
	public Json addDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json res = new Json();
		res.setMsg("没有委托单信息传入");
		// System.out.println("aaaa");
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (detectionCommisionSheetPage.getVehicleType() != null && detectionCommisionSheetPage.getVehicleType().intValue() == 0) {
				Json resultJson = checkIsFirstDetected(detectionCommisionSheetPage);
				if (resultJson.isSuccess() == false)
					return resultJson;
			}

			if (detectionCommisionSheetPage.getVehicleLength() == null) {
				res.setSuccess(false);
				res.setMsg("车身长度不能为空");
				return res;
			}
			if (detectionCommisionSheetPage.getMaxTotalQuality() == null) {
				res.setSuccess(false);
				res.setMsg("最大总质量不能为空");
				return res;
			}

			if (detectionCommisionSheetPage.getBaseQuality() == null) {
				res.setSuccess(false);
				res.setMsg("基准质量不能为空");
				return res;
			}

			if (detectionCommisionSheetPage.getTotalMiles() == null) {
				res.setSuccess(false);
				res.setMsg("总里程数不能为空");
				return res;
			}

			// 检测检测站有没有资质过期

			if (detectionCommisionSheetPage.getStationName() != null && !detectionCommisionSheetPage.getStationName().equals("")) {

				String hql = "from InspectionStation as station where station.status=0 and station.stationName=:stationName";
				Map<String, Object> tMap = new HashMap<String, Object>();
				tMap.put("stationName", detectionCommisionSheetPage.getStationName());
				List<InspectionStation> list = inspectionStationDao.find(hql, tMap);
				if (list != null && list.size() > 0) {
					InspectionStation inspectionStation = list.get(0);
					String time = DateFormat.getSimpleDateFormat().format(new Date());
					System.out.println(time);
					Timestamp timestamp = Timestamp.valueOf(time);
					System.out.println("资质" + inspectionStation.getQulificationTime().getTime());
					System.out.println("当前" + timestamp.getTime());
					if (inspectionStation.getValidPeriod().getTime() < timestamp.getTime()) {
						res.setMsg("检测站资质已经过期！不能开展检测工作");
						res.setSuccess(false);
						return res;
					}
				} else {
					res.setMsg("检测站不存在");
					res.setSuccess(false);
					return res;
				}
			} else {
				res.setSuccess(false);
				res.setMsg("请填写检测站");
				return res;
			}

			// 检测车辆是否在黑名单中
			//添加检测车牌颜色，2018-02-24
			if (detectionCommisionSheetPage.getLicence() != null && !detectionCommisionSheetPage.getLicence().equals("")) {
				String hql = "select count(*) from BlackNameList as bnl where bnl.isCancel=:isCancel and bnl.isPunished=:isPunished and (bnl.licence=:licence or bnl.licence=:vin)";
				Map<String, Object> tMap = new HashMap<String, Object>();
				tMap.put("licence", detectionCommisionSheetPage.getLicence());
				tMap.put("vin", detectionCommisionSheetPage.getVin());
				//tMap.put("licenseColor", detectionCommisionSheetPage.getLicenseColor());
				tMap.put("isCancel", false);
				tMap.put("isPunished", false);
				long counter = blackNameListDao.count(hql, tMap);
				if (counter > 0) {
					res.setSuccess(false);
					res.setMsg("该车是违章车辆，不予检测！请先到交通部门接受处罚！");
					return res;
				}
			} else {
				res.setSuccess(false);
				res.setMsg("请输入车牌号");
				return res;
			}
			// 检查当前表中是否已经存在建好却没检测的的委托单，

			String hql_query = "select count(*) from DetectionCommisionSheet as dcs where  dcs.commisionSheetStatus!=0 and dcs.commisionSheetStatus!=3 and dcs.commisionSheetStatus!=4 and dcs.vin=:vin ";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("vin", detectionCommisionSheetPage.getVin());
			long result = detectionCommisionSheetDao.count(hql_query, param);
			if (result > 0) {
				res.setSuccess(false);
				res.setMsg("系统中已经存在建好但没检测的委托单,请不要重复新建");
				return res;
			}
			// 设置当前年度检测次数
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			String detectionTime = currentYear + "-01-01 00:00:00";
			Timestamp timestamp = Timestamp.valueOf(detectionTime);
			String hql = "select count(*) from DetectionCommisionSheet as dcs where dcs.detectionTime>=:timestamp and dcs.commisionSheetStatus=3 and dcs.vin=:vin ";
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("timestamp", timestamp);
			tMap.put("vin", detectionCommisionSheetPage.getVin());
			long counter = detectionCommisionSheetDao.count(hql, tMap);
			System.out.println("--------------" + counter);
			detectionCommisionSheetPage.setYearCount((int) (counter + 1));

			// 新建一个新的检测委托单
			DetectionCommisionSheet detectionCommisionSheet = new DetectionCommisionSheet();
			// 根据检测方法进行选择相应的外键类型
			if (detectionCommisionSheetPage.getDetectionMethod().equals("自由加速法")) {
				FreeAccelerationMethod freeAccelerationMethod = new FreeAccelerationMethod();
				freeAccelerationMethod.setReportStatus(0);
				freeAcclerationMethodDao.save(freeAccelerationMethod);
				detectionCommisionSheet.setFreeAccelerationMethod(freeAccelerationMethod);
			} else if (detectionCommisionSheetPage.getDetectionMethod().equals("稳态工况法")) {
				SteadyStateMethod steadyStateMethod = new SteadyStateMethod();
				steadyStateMethod.setReportStatus(0);
				steayStateMehodDao.save(steadyStateMethod);
				detectionCommisionSheet.setSteadyStateMethod(steadyStateMethod);
			} else if (detectionCommisionSheetPage.getDetectionMethod().equals("双怠速法")) {
				TwoSpeedIdleMethod twoSpeedIdleMethod = new TwoSpeedIdleMethod();
				twoSpeedIdleMethod.setReportStatus(0);
				twoSpeedIdleMethodDao.save(twoSpeedIdleMethod);
				detectionCommisionSheet.setTwoSpeedIdleMethod(twoSpeedIdleMethod);
			} else if (detectionCommisionSheetPage.getDetectionMethod().equals("加载减速法")) {
				LugDownMethod lugDownMethod = new LugDownMethod();
				lugDownMethod.setReportStatus(0);
				lugDownMethodDao.save(lugDownMethod);
				detectionCommisionSheet.setLugDownMethod(lugDownMethod);
			} else {
				MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod = new MotorTwoSpeedIdleMethod();
				motorTwoSpeedIdleMethod.setReportStatus(0);
				motorTwoSpeedIdleDao.save(motorTwoSpeedIdleMethod);
				detectionCommisionSheet.setMotorTwoSpeedIdleMethod(motorTwoSpeedIdleMethod);
			}

			BeanUtils.copyProperties(detectionCommisionSheetPage, detectionCommisionSheet, new String[] { "detectionTime", "vehicleRegisterDate", "validatePeriod", "vechileIssueCertTime" });
			// 设置汽车登记时间
			if (detectionCommisionSheetPage.getDetectionTime() != null && !detectionCommisionSheetPage.getDetectionTime().equals("")) {
				detectionCommisionSheet.setDetectionTime(new Timestamp(System.currentTimeMillis()));
			}
			// 设置汽车检测时间
			if (detectionCommisionSheetPage.getVehicleRegisterDate() != null && !detectionCommisionSheetPage.getVehicleRegisterDate().equals("")) {
				detectionCommisionSheet.setVehicleRegisterDate(Timestamp.valueOf(detectionCommisionSheetPage.getVehicleRegisterDate() + " 00:00:00"));
			}

			// 设置汽车的发证日期
			if (detectionCommisionSheetPage.getVechileIssueCertTime() != null && !detectionCommisionSheetPage.getVechileIssueCertTime().equals("")) {
				detectionCommisionSheet.setVechileIssueCertTime(ChangeTimeFormat.getInstance().strToTimeStamp(detectionCommisionSheetPage.getVechileIssueCertTime()));
			}
			// 设置有效期
			if (detectionCommisionSheetPage.getValidatePeriod() != null && !detectionCommisionSheetPage.getValidatePeriod().equals("")) {
				detectionCommisionSheet.setValidatePeriod(ChangeTimeFormat.getInstance().strToTimeStamp(detectionCommisionSheetPage.getValidatePeriod()));
			}

			// 设置委托单状态的初始状态为1（未检测）
			detectionCommisionSheet.setCommisionSheetStatus(1);
			// 初始设置为未下结论的状态
			detectionCommisionSheet.setConclusion(2);
			// 产生报告编号
			String reportNumber = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(new Date()).replaceAll("[-:\\s]", "");
			detectionCommisionSheet.setReportNumber(reportNumber);
			// 设置请求发标的任务状态,初始状态为未请求
			detectionCommisionSheet.setRequestStatus(0);
			// 设置服务器的接收状态 0表示未排队 1表示正在排队 2表示正在处理 3表示处理完成
			detectionCommisionSheet.setReceiveStatus(0);
			//设置上传到远程服务器状态 ispush 0 未上传
			detectionCommisionSheet.setIsPush(0);
			try {
				detectionCommisionSheetDao.save(detectionCommisionSheet);
				// 如果是换发更新两张表，在黑名单表和委托单表中修改车牌号中车架号为车牌号
				if (detectionCommisionSheetPage.getLabelDistributeType() == 1) {
					// 更新黑名单表
					String update_black_hql = "from BlackNameList as bnl where bnl.licence=:vin ";
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("vin", detectionCommisionSheetPage.getVin());
					List<BlackNameList> list = blackNameListDao.find(update_black_hql, paramMap);
					for (BlackNameList bl : list) {
						bl.setLicence(detectionCommisionSheetPage.getLicence());
						blackNameListDao.update(bl);
					}
					// 更新委托单表
					/*String update_commisionSheet_hql = "from DetectionCommisionSheet as dcs where dcs.licence=:vin ";
					//List<DetectionCommisionSheet> list2 = detectionCommisionSheetDao.find(update_commisionSheet_hql, paramMap);
					List<DetectionCommisionSheet> DCSList = detectionCommisionSheetDao.find(update_commisionSheet_hql, paramMap);
					for (DetectionCommisionSheet dcs : DCSList) {
						dcs.setLicence(detectionCommisionSheetPage.getLicence());
						detectionCommisionSheetDao.update(dcs);
					}*/
				}

				res.setSuccess(true);
				res.setMsg("新建检测委托单成功!");

			} catch (Exception e) {
				res.setSuccess(false);
				res.setMsg("新建检测委托单失败!");
			}
		}
		return res;
	}

	
	
	@Override
	public DataGrid getEmissionStandard(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		//TODO
		DataGrid dg = new DataGrid();
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String vehicleModelCode = detectionCommisionSheetPage.getVehicleModelCode();
			String engineModel = detectionCommisionSheetPage.getEngineModel();
			String vehicleRegisterDate = detectionCommisionSheetPage.getVehicleRegisterDate();
			
			
			String hql = null;
			hql = "FROM VehicleTypeInfo as VT where VT.FILENAME = "
					+ "(select max(FILENAME) from VehicleTypeInfo as v "
					+ "where v.CLXH =:CLXH and v.FDJXH =:FDJXH and v.FILENAME<=:FILENAME) "
					+ "and VT.CLXH =:CLXH and VT.FDJXH =:FDJXH and VT.FILENAME<=:FILENAME";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("CLXH", vehicleModelCode);
			params.put("FDJXH", engineModel);
			params.put("FILENAME", vehicleRegisterDate);
			
			
			//hql += " order by ds.id desc ";
			List<VehicleTypeInfo> list = vehicleTypeInfoDao.find(hql, params);
			//dg.setTotal( vehicleTypeInfoDao.count("select count(ID) " + hql, params));
			dg.setTotal((long) list.size());
			List<DetectionCommisionSheetPage> resList = new ArrayList<DetectionCommisionSheetPage>();
			for (VehicleTypeInfo vehicleTypeInfo : list) {
				DetectionCommisionSheetPage dcs = new DetectionCommisionSheetPage();
				dcs.setEngineemissionAmount(Double.parseDouble(vehicleTypeInfo.getPF()));
				resList.add(dcs);

			}
			dg.setRows(resList);
		}
		return dg;
	}
	
	

	@Override
	//环保部下载接口不完善,弃用.
	public DataGrid getVehicleInfoByHBTing(DetectionCommisionSheetPage detectionCommisionSheetPage) throws JSONException {
		DataGrid dg = new DataGrid();
		String licence = detectionCommisionSheetPage.getLicence();
		String licenceColor = detectionCommisionSheetPage.getLicenseColor().substring(0, 1);
		String queryCond = licenceColor+licence;
		
		SOAPService ser = new SOAPService();
		JSONObject json=new JSONObject(); 
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObjInArr=new JSONObject(); 
		JSONObject res_json = null;
		try {
			jsonObjInArr.put("CPHM", queryCond);
			jsonArr.put(jsonObjInArr);
			json.put("QUERYCONDITION", jsonArr);  
			res_json = ser.HBTingDownload("CL001",json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res_json != null){
			JSONArray res_json_system = (JSONArray) res_json.get("SYSTEM");
			JSONObject system_msg = (JSONObject) res_json_system.get(0);
			if(system_msg.get("code").equals("0")){
				JSONArray res_json_vehicle = (JSONArray) res_json.get("VEHICLE");
				JSONObject vehicle_msg = (JSONObject) res_json_vehicle.get(0);
				List<DetectionCommisionSheetPage> resList = new ArrayList<DetectionCommisionSheetPage>();
				DetectionCommisionSheetPage resPage = new DetectionCommisionSheetPage();
				
				resPage.setVin(vehicle_msg.get("VIN").toString());
				resPage.setBaseQuality(Double.parseDouble(vehicle_msg.get("JZZL").toString()));
				
				
				
				
				dg.setTotal((long)res_json_vehicle.length());
				dg.setRows(resList);
			}
		}
				
		return dg;
	}

	@Override
    public DataGrid getVehibleInfo(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		DataGrid dg = new DataGrid();
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String hql = "from VehicleInfo as vi where 1=1 ";
			Map<String, Object> tMap = new HashMap<String, Object>();
			if (detectionCommisionSheetPage.getVin() != null && !detectionCommisionSheetPage.getVin().equals("")) {
				hql += " and vi.CLSBDH=:vin";
				tMap.put("vin", detectionCommisionSheetPage.getVin());
			}
			dg.setTotal(VehicleInfoDao.count("select count(id) " + hql, tMap));
			//hql += " order by ds.id desc ";
			List<VehicleInfo> list = VehicleInfoDao.find(hql, tMap,detectionCommisionSheetPage.getPage(), detectionCommisionSheetPage.getRows());
			List<DetectionCommisionSheetPage> resList = new ArrayList<DetectionCommisionSheetPage>();
			for (VehicleInfo vehicleInfo : list) {
				DetectionCommisionSheetPage dcs = new DetectionCommisionSheetPage();
				
				dcs.setLicence(vehicleInfo.getFZJG().substring(0, 1)+vehicleInfo.getHPHM());
				if(vehicleInfo.getFDJH()!=null)
					dcs.setEngineCode(vehicleInfo.getFDJH());
				else
					dcs.setEngineCode("");
				
				if(vehicleInfo.getFDJXH()!=null)
					dcs.setEngineModel(vehicleInfo.getFDJXH());
				else
					dcs.setEngineCode("");
				
				if(vehicleInfo.getCCDJRQ()!=null)
					dcs.setVehicleRegisterDate(vehicleInfo.getCCDJRQ().substring(0, 10));
				else
					dcs.setEngineCode("");
				
				if(vehicleInfo.getDJRQ()!=null)
					dcs.setVechileIssueCertTime(vehicleInfo.getDJRQ().substring(0, 10));
				else
					dcs.setVechileIssueCertTime("");
				
				if(vehicleInfo.getYXQZ()!=null)
					dcs.setValidatePeriod(vehicleInfo.getYXQZ().substring(0, 10));
				else
					dcs.setValidatePeriod("");
				
				String HPZL = vehicleInfo.getHPZL();
				if(HPZL.equals("02")||HPZL.equals("08"))
					dcs.setLicenseColor("蓝牌");
				else if(HPZL.equals("01")||HPZL.equals("07")||HPZL.equals("13")||HPZL.equals("14")||HPZL.equals("15")||HPZL.equals("16")||HPZL.equals("17"))
					dcs.setLicenseColor("黄牌");
				else if(HPZL.equals("03")||HPZL.equals("04")||HPZL.equals("05")||HPZL.equals("06")||HPZL.equals("09")||HPZL.equals("10")||HPZL.equals("11")||HPZL.equals("12"))
					dcs.setLicenseColor("黑牌");
				else if(HPZL.equals("20")||HPZL.equals("21")||HPZL.equals("22"))
					dcs.setLicenseColor("白牌");
				
				if(vehicleInfo.getHDZK()!=null)
					dcs.setVehicleLoadNum(vehicleInfo.getHDZK());
				else
					dcs.setVehicleLoadNum(null);
				
				if(vehicleInfo.getPL()!=null)
					dcs.setEngineemissionAmount(vehicleInfo.getPL());
				else
					dcs.setEngineemissionAmount(null);
				
				if(vehicleInfo.getCLXH()!=null)
					dcs.setVehicleModelCode(vehicleInfo.getCLXH());
				else
					dcs.setVehicleModelCode("");
				
				if(vehicleInfo.getZBZL()!=null)
					dcs.setBaseQuality(vehicleInfo.getZBZL()+100);
				else
					dcs.setBaseQuality(null);
				
				String RLZL = vehicleInfo.getRLZL();
				if(RLZL.equals("A"))
					dcs.setFuelType("汽油");
				else if(RLZL.equals("B"))
					dcs.setFuelType("柴油");
				else if(RLZL.equals("E"))
					dcs.setFuelType("天然气");
				else if(RLZL.equals("O"))
					dcs.setFuelType("混合动力");
				
				dcs.setMaxTotalQuality(vehicleInfo.getZZL());
				
				//String CLXH = vehicleInfo.getCLXH();

				
				resList.add(dcs);

			}
			dg.setRows(resList);
		}
		return dg;
	}

	/**
	 * 获取委托单的信息
	 */
	@Override
	public DataGrid getAllDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		DataGrid dg = new DataGrid();
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String hql = "from DetectionCommisionSheet as ds where 1=1 ";
			Map<String, Object> tMap = new HashMap<String, Object>();
			if (detectionCommisionSheetPage.getStationName() != null && !detectionCommisionSheetPage.getStationName().equals("")) {
				hql += " and ds.stationName like :stationName ";
				tMap.put("stationName", "%" + detectionCommisionSheetPage.getStationName() + "%");
			}
			if (detectionCommisionSheetPage.getDetectionMethod() != null && !detectionCommisionSheetPage.getDetectionMethod().equals("")) {
				hql += " and ds.detectionMethod like :detectionMethod ";
				tMap.put("detectionMethod", "%" + detectionCommisionSheetPage.getDetectionMethod() + "%");
			}
			if (detectionCommisionSheetPage.getVin() != null && !detectionCommisionSheetPage.getVin().equals("")) {
				hql += " and ds.vin=:vin and ds.commisionSheetStatus=3 ";
				tMap.put("vin", detectionCommisionSheetPage.getVin());
			}
			if (detectionCommisionSheetPage.getBeginTime() != null && !detectionCommisionSheetPage.getBeginTime().equals("")) {
				hql += " and ds.detectionTime>=:beginTime ";
				tMap.put("beginTime", Timestamp.valueOf(detectionCommisionSheetPage.getBeginTime()));
			}
			if (detectionCommisionSheetPage.getEndTime() != null && !detectionCommisionSheetPage.getEndTime().equals("")) {
				hql += " and ds.detectionTime<=:endTime";
				tMap.put("endTime", Timestamp.valueOf(detectionCommisionSheetPage.getEndTime()));
			}
			if (detectionCommisionSheetPage.getLicence() != null && !detectionCommisionSheetPage.getLicence().equals("")) {
				hql += " and ds.licence like :licence ";
				tMap.put("licence", "%" + detectionCommisionSheetPage.getLicence() + "%");
			}
			if (detectionCommisionSheetPage.getCommisionSheetStatus() != null && detectionCommisionSheetPage.getCommisionSheetStatus() != -1) {
				hql += " and ds.commisionSheetStatus=:status ";
				tMap.put("status", detectionCommisionSheetPage.getCommisionSheetStatus());
			}
			if (detectionCommisionSheetPage.getVehicleOwnerName() != null && !detectionCommisionSheetPage.getVehicleOwnerName().equals("")) {
				hql += " and ds.vehicleOwnerName like :vehicleOwnerName ";
				tMap.put("vehicleOwnerName", "%" + detectionCommisionSheetPage.getVehicleOwnerName() + "%");
			}
			if (detectionCommisionSheetPage.getReportNumber() != null && !detectionCommisionSheetPage.getReportNumber().equals("")) {
				hql += " and ds.reportNumber like :reportNumber ";
				tMap.put("reportNumber", "%" + detectionCommisionSheetPage.getReportNumber() + "%");
			}
			if (detectionCommisionSheetPage.getConclusion() != null && detectionCommisionSheetPage.getConclusion() != -1) {
				hql += " and ds.conclusion=:conclusion ";
				tMap.put("conclusion", detectionCommisionSheetPage.getConclusion());
			}
			if (detectionCommisionSheetPage.getLabelId() != null && !detectionCommisionSheetPage.getLabelId().equals("-1")) {
				if (detectionCommisionSheetPage.getLabelId().equals("0")) {
					hql += " and ds.environmentalLabel is null ";
				} else {
					hql += " and ds.environmentalLabel is not null ";
				}
			}
			if (detectionCommisionSheetPage.getId() != null) {
				hql += " and ds.id=:id ";
				tMap.put("id", detectionCommisionSheetPage.getId());
			}

			dg.setTotal(detectionCommisionSheetDao.count("select count(id) " + hql, tMap));
			hql += " order by ds.id desc ";
			List<DetectionCommisionSheet> list = detectionCommisionSheetDao.find(hql, tMap, detectionCommisionSheetPage.getPage(), detectionCommisionSheetPage.getRows());
			List<DetectionCommisionSheetPage> resList = new ArrayList<DetectionCommisionSheetPage>();
			for (DetectionCommisionSheet detectionCommisionSheet : list) {
				DetectionCommisionSheetPage dcs = new DetectionCommisionSheetPage();
				BeanUtils.copyProperties(detectionCommisionSheet, dcs, new String[] { "detectionTime", "vehicleRegisterDate", "validatePeriod", "vechileIssueCertTime" });
				if (detectionCommisionSheet.getEnvironmentalLabel() != null) {
					dcs.setLabelId(detectionCommisionSheet.getEnvironmentalLabel().getLabelId());
				} else {
					dcs.setLabelId("未发标");
				}
				if (detectionCommisionSheet.getDetectionTime() != null) {
					dcs.setDetectionTime(ChangeTimeFormat.getInstance().timeStampToPreciseString(detectionCommisionSheet.getDetectionTime()));
				}
				if (detectionCommisionSheet.getVehicleRegisterDate() != null) {
					dcs.setVehicleRegisterDate(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getVehicleRegisterDate()));
				}
				if (detectionCommisionSheet.getValidatePeriod() != null) {
					dcs.setValidatePeriod(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getValidatePeriod()));
				}
				if (detectionCommisionSheet.getVechileIssueCertTime() != null) {
					dcs.setVechileIssueCertTime(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getVechileIssueCertTime()));
				}
				resList.add(dcs);

			}
			dg.setRows(resList);
		}
		return dg;
	}

	/**
	 * 注销选中的委托单
	 */
	@Override
	public Json cancelDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json res = new Json();
		String flag = "";
		if (detectionCommisionSheetPage != null) {
			System.out.println(detectionCommisionSheetPage.getIds());
			if (detectionCommisionSheetPage.getIds() != null && !detectionCommisionSheetPage.getIds().trim().equals("")) {
				try {
					for (String id : detectionCommisionSheetPage.getIds().split(",")) {
						flag = id;
						String hql = " from DetectionCommisionSheet  as dcs where dcs.id=:id";
						Map<String, Object> tMap = new HashMap<String, Object>();
						tMap.put("id", Integer.parseInt(id));
						DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.get(hql, tMap);
						detectionCommisionSheet.setCommisionSheetStatus(0);
						detectionCommisionSheetDao.update(detectionCommisionSheet);
					}
					res.setSuccess(true);
					res.setMsg("注销检测委托单成功");
				} catch (Exception e) {
					e.printStackTrace();
					res.setMsg("注销检测委托单号为" + flag + "的委托单信息");
					res.setSuccess(false);
					return res;
				}
			} else {
				res.setMsg("请选中要注销的检测委托单信息");
				res.setSuccess(false);
			}
		}
		return res;
	}

	@Override
	public Json modifyDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json res = new Json();
		res.setMsg("没有传入修改数据");
		if (detectionCommisionSheetPage != null) {
			try {
				detectionCommisionSheetPage = (DetectionCommisionSheetPage) TrimString.getInstance().trimObjectString(detectionCommisionSheetPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String hql = "from DetectionCommisionSheet as dcs where dcs.id=:id";
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("id", detectionCommisionSheetPage.getId());
			DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.find(hql, tMap).get(0);

			BeanUtils.copyProperties(detectionCommisionSheetPage, detectionCommisionSheet, new String[] { "detectionTime", "vehicleRegisterDate", "validatePeriod", "vechileIssueCertTime" });

			if (detectionCommisionSheetPage.getVehicleRegisterDate() != null && !detectionCommisionSheetPage.getVehicleRegisterDate().equals("")) {
				detectionCommisionSheet.setVehicleRegisterDate(Timestamp.valueOf(detectionCommisionSheetPage.getVehicleRegisterDate() + " 00:00:00"));
			}
			if (detectionCommisionSheetPage.getDetectionTime() != null && !detectionCommisionSheetPage.getDetectionTime().equals("")) {
				detectionCommisionSheet.setDetectionTime(Timestamp.valueOf(detectionCommisionSheetPage.getDetectionTime()));
			}
			if (detectionCommisionSheetPage.getValidatePeriod() != null && !detectionCommisionSheetPage.getValidatePeriod().equals("")) {
				detectionCommisionSheet.setValidatePeriod(Timestamp.valueOf(detectionCommisionSheetPage.getValidatePeriod() + " 00:00:00"));
			}
			if (detectionCommisionSheetPage.getVechileIssueCertTime() != null && !detectionCommisionSheetPage.getVechileIssueCertTime().equals("")) {
				detectionCommisionSheet.setVechileIssueCertTime(Timestamp.valueOf(detectionCommisionSheetPage.getVechileIssueCertTime() + " 00:00:00"));
			}
			if (detectionCommisionSheetPage.getLabelId().equals("未发标")) {
				detectionCommisionSheet.setEnvironmentalLabel(null);
			}
			try {
				detectionCommisionSheetDao.update(detectionCommisionSheet);
				res.setSuccess(true);
				res.setMsg("修改检测委托单信息成功");
			} catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setMsg("修改检测委托单信息失败");
				return res;
			}
		}

		return res;
	}

	@Override
	public Json checkIsFirstDetected(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json res = new Json();
		System.out.println("sb");

		if (detectionCommisionSheetPage != null && detectionCommisionSheetPage.getVin() != null && !detectionCommisionSheetPage.getVin().trim().equals("")) {
			try {
				System.out.println(detectionCommisionSheetPage.getVin().trim());
				String hql = "select count(*) from DetectionCommisionSheet as dcs where dcs.commisionSheetStatus=:c and dcs.vin=:vin";
				Map<String, Object> tMap = new HashMap<String, Object>();
				tMap.put("vin", detectionCommisionSheetPage.getVin());
				tMap.put("c", 3);
				long counter = detectionCommisionSheetDao.count(hql, tMap);
				if (counter > 0) {
					res.setSuccess(false);
					res.setMsg("该车在系统中已经存在检测记录，不是新车!请重新选择车辆类型");
				} else {
					res.setSuccess(true);
					res.setMsg("输入正确");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			res.setSuccess(false);
			res.setMsg("车架号不能为空");
		}
		return res;
	}

	@Override
	public Json revokeDetectionCommistionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		Json res = new Json();
		String flag = "";
		if (detectionCommisionSheetPage != null) {
			System.out.println(detectionCommisionSheetPage.getIds());
			if (detectionCommisionSheetPage.getIds() != null && !detectionCommisionSheetPage.getIds().trim().equals("")) {
				try {
					for (String id : detectionCommisionSheetPage.getIds().split(",")) {
						flag = id;
						String hql = " from DetectionCommisionSheet  as dcs where dcs.id=:id";
						Map<String, Object> tMap = new HashMap<String, Object>();
						tMap.put("id", Integer.parseInt(id));
						DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.get(hql, tMap);
						detectionCommisionSheet.setCommisionSheetStatus(1);
						detectionCommisionSheet.setConclusion(2); // 未检测状态
						detectionCommisionSheet.setRequestStatus(0); // 未请求状态
						detectionCommisionSheet.setReceiveStatus(0);// 设置机器手的接收状态为0
						// 设置检测报告的reportStatus
						if (detectionCommisionSheet.getFreeAccelerationMethod() != null) {
							detectionCommisionSheet.getFreeAccelerationMethod().setReportStatus(0);
							freeAcclerationMethodDao.update(detectionCommisionSheet.getFreeAccelerationMethod());
						}
						if (detectionCommisionSheet.getMotorTwoSpeedIdleMethod() != null) {
							detectionCommisionSheet.getMotorTwoSpeedIdleMethod().setReportStatus(0);
							motorTwoSpeedIdleDao.update(detectionCommisionSheet.getMotorTwoSpeedIdleMethod());
						}
						if (detectionCommisionSheet.getTwoSpeedIdleMethod() != null) {
							detectionCommisionSheet.getTwoSpeedIdleMethod().setReportStatus(0);
							twoSpeedIdleMethodDao.update(detectionCommisionSheet.getTwoSpeedIdleMethod());
						}
						if (detectionCommisionSheet.getSteadyStateMethod() != null) {
							detectionCommisionSheet.getSteadyStateMethod().setReportStatus(0);
							steayStateMehodDao.update(detectionCommisionSheet.getSteadyStateMethod());
						}
						if (detectionCommisionSheet.getLugDownMethod() != null) {
							detectionCommisionSheet.getLugDownMethod().setReportStatus(0);
							lugDownMethodDao.update(detectionCommisionSheet.getLugDownMethod());
						}
						detectionCommisionSheetDao.update(detectionCommisionSheet);
					}
					res.setSuccess(true);
					res.setMsg("挡回检测委托单成功");
				} catch (Exception e) {
					e.printStackTrace();
					res.setMsg("挡回检测委托单号为" + flag + "的委托单信息");
					res.setSuccess(false);
					return res;
				}
			} else {
				res.setMsg("请选中要挡回的已注销的检测委托单信息");
				res.setSuccess(false);
			}
		}
		return res;
	}

	/**
	 * 和国家发标系统之间的交互
	 */
	@Override
	public String distributeLabel(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		String res = "";
		// TODO调用机器手的方法
		return res;
	}

	/**
	 * 导出检测委托单信息
	 */
	@Override
	public Json exportDetectionCommisionSheet(DetectionCommisionSheetPage detectionCommisionSheetPage) {
		List<Object> pageList = new ArrayList<Object>();
		if (detectionCommisionSheetPage.getIds() != null) {
			for (String id : detectionCommisionSheetPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {

					DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.get(DetectionCommisionSheet.class, Integer.valueOf(id));
					DetectionCommisionSheetPage dcs = new DetectionCommisionSheetPage();
					BeanUtils.copyProperties(detectionCommisionSheet, dcs, new String[] { "detectionTime", "vehicleRegisterDate", "vechileIssueCertTime", "validatePeriod" });
					if (detectionCommisionSheet.getEnvironmentalLabel() != null) {
						dcs.setLabelId(detectionCommisionSheet.getEnvironmentalLabel().getLabelId());
					} else {
						dcs.setLabelId("未发标");
					}
					if (detectionCommisionSheet.getErrorReason() != null) {
						dcs.setErrorReason(detectionCommisionSheet.getErrorReason());
					} else {
						dcs.setErrorReason("无");
					}

					if (detectionCommisionSheet.getDetectionTime() != null) {
						dcs.setDetectionTime(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getDetectionTime()));
					}
					if (detectionCommisionSheet.getVehicleRegisterDate() != null) {
						dcs.setVehicleRegisterDate(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getVehicleRegisterDate()));
					}
					if (detectionCommisionSheet.getVechileIssueCertTime() != null) {
						dcs.setVechileIssueCertTime(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getVechileIssueCertTime()));
					}
					if (detectionCommisionSheet.getValidatePeriod() != null) {
						dcs.setValidatePeriod(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getValidatePeriod()));
					}
					pageList.add(dcs);
				}
			}
		}
		String filePath = "";
		try {
			filePath = ExportUtil.ExportToExcelByResultSet(pageList, null, "formatExcel", "formatTitle", DetectionCommisionSheetServiceImpl.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(filePath);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		return retJson;
	}

	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((DetectionCommisionSheetPage) obj).getId().toString());
		result.add(((DetectionCommisionSheetPage) obj).getReportNumber());
		result.add(((DetectionCommisionSheetPage) obj).getStationName());
		result.add(((DetectionCommisionSheetPage) obj).getLicence());
		result.add(((DetectionCommisionSheetPage) obj).getLicenseColor());
		result.add(((DetectionCommisionSheetPage) obj).getVehicleOwnerName());
		result.add(((DetectionCommisionSheetPage) obj).getVehicleOwnerAddress());
		result.add(((DetectionCommisionSheetPage) obj).getVin());
		result.add(((DetectionCommisionSheetPage) obj).getDetectionTime());
		result.add(((DetectionCommisionSheetPage) obj).getEngineModel());
		result.add(((DetectionCommisionSheetPage) obj).getEngineCode());
		result.add(((DetectionCommisionSheetPage) obj).getVehicleModelCode());
		result.add(((DetectionCommisionSheetPage) obj).getVehicleClass());
		result.add(((DetectionCommisionSheetPage) obj).getFuelType());
		result.add(((DetectionCommisionSheetPage) obj).getBaseQuality().toString());
		result.add(((DetectionCommisionSheetPage) obj).getMaxTotalQuality().toString());
		result.add(((DetectionCommisionSheetPage) obj).getEngineemissionAmount().toString());
		result.add(((DetectionCommisionSheetPage) obj).getTotalMiles().toString());
		result.add(((DetectionCommisionSheetPage) obj).getVehicleLength().toString());
		result.add(((DetectionCommisionSheetPage) obj).getLabelDistributeType() == 0 ? "核发" : "换发");
		result.add(((DetectionCommisionSheetPage) obj).getVehicleType() == 0 ? "新车" : (((DetectionCommisionSheetPage) obj).getVehicleType() == 1 ? "外地转入车" : "其他车"));
		result.add(((DetectionCommisionSheetPage) obj).getVehicleRegisterDate());
		result.add(((DetectionCommisionSheetPage) obj).getVechileIssueCertTime());
		result.add(((DetectionCommisionSheetPage) obj).getValidatePeriod());
		result.add(((DetectionCommisionSheetPage) obj).getVehicleLoadNum().toString());
		result.add(((DetectionCommisionSheetPage) obj).getDetectionMethod());
		result.add("国" + ((DetectionCommisionSheetPage) obj).getEmissionStandard());
		result.add(((DetectionCommisionSheetPage) obj).getYearCount().toString());
		if (((DetectionCommisionSheetPage) obj).getCommisionSheetStatus() != null) {
			int status = ((DetectionCommisionSheetPage) obj).getCommisionSheetStatus();
			if (status == 0)
				result.add("已注销");
			else if (status == 1)
				result.add("未检测");
			else if (status == 2)
				result.add("已检测");
			else {
				result.add("已完成");
			}
		}

		if (((DetectionCommisionSheetPage) obj).getConclusion() != null) {
			int conclusion = ((DetectionCommisionSheetPage) obj).getConclusion();
			if (conclusion == 0) {
				result.add("合格");
			} else if (conclusion == 1) {
				result.add("不合格");
			} else {
				result.add("");
			}

		}

		if (((DetectionCommisionSheetPage) obj).getRequestStatus() != null) {
			int requestStatus = ((DetectionCommisionSheetPage) obj).getRequestStatus();
			if (requestStatus == 0) {
				result.add("未发标");
			} else if (requestStatus == 1) {
				result.add("正在处理");
			} else if (requestStatus == 2) {
				result.add("已发标");
			} else {
				result.add("发标出现错误");
			}
		}

		result.add(((DetectionCommisionSheetPage) obj).getLabelId().equals("未发标") ? "" : ((DetectionCommisionSheetPage) obj).getLabelId());
		result.add(((DetectionCommisionSheetPage) obj).getErrorReason());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("序号");
		result.add("检测单编号");
		result.add("检测站名称");
		result.add("车牌号");
		result.add("车牌颜色");
		result.add("车主姓名");
		result.add("车主地址");
		result.add("车辆识别码");
		result.add("检测时间");
		result.add("发动机型号");
		result.add("发动机号");
		result.add("车辆型号");
		result.add("车辆来源");
		result.add("燃油类型");
		result.add("基准质量");
		result.add("最大总质量");
		result.add("发动机排量");
		result.add("累计行驶里程");
		result.add("车身长度");
		result.add("检测类别");
		result.add("车辆类型");
		result.add("车辆登记日期");
		result.add("车辆发证日期");
		result.add("有效期");
		result.add("最大载客量");
		result.add("检测方法");
		result.add("排放标准");
		result.add("年检测次数");
		result.add("委托单状态");
		result.add("检测结论");
		result.add("发标请求状态");
		result.add("标志号");
		result.add("错误原因");
		return result;
	}

	@Override
	public DetectionCommisionSheet getDetectionCommisionSheetById(Integer id) {
		DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.get(DetectionCommisionSheet.class, id);
		return detectionCommisionSheet;
	}

}
