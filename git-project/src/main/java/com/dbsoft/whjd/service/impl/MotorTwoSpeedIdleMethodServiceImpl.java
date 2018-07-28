package com.dbsoft.whjd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.LimitValueReference;
import com.dbsoft.whjd.model.MotorTwoSpeedIdleMethod;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MotorTwoSpeedIdleMethodPage;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.service.IMotorTwoSpeedIdleMethodService;
import com.dbsoft.whjd.util.ChangeTimeFormat;

@Service("motorTwoSpeedIdleMethodService")
public class MotorTwoSpeedIdleMethodServiceImpl implements IMotorTwoSpeedIdleMethodService {
	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	private IBaseDao<LimitValueReference> limitValueReferenceDao;
	private IBaseDao<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethodDao;
	private IBaseDao<DetectionLine> lineDao;
	private IBaseDao<InspectionStation> stationDao;
	
	
	public IBaseDao<InspectionStation> getStationDao() {
		return stationDao;
	}

	@Resource(name = "baseDao")
	public void setStationDao(IBaseDao<InspectionStation> stationDao) {
		this.stationDao = stationDao;
	}

	public IBaseDao<DetectionLine> getLineDao() {
		return lineDao;
	}

	@Resource(name = "baseDao")
	public void setLineDao(IBaseDao<DetectionLine> lineDao) {
		this.lineDao = lineDao;
	}

	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}

	public IBaseDao<LimitValueReference> getLimitValueReferenceDao() {
		return limitValueReferenceDao;
	}

	@Resource(name = "baseDao")
	public void setLimitValueReferenceDao(IBaseDao<LimitValueReference> limitValueReferenceDao) {
		this.limitValueReferenceDao = limitValueReferenceDao;
	}

	public IBaseDao<MotorTwoSpeedIdleMethod> getMotorTwoSpeedIdleMethodDao() {
		return motorTwoSpeedIdleMethodDao;
	}

	@Resource(name = "baseDao")
	public void setMotorTwoSpeedIdleMethodDao(IBaseDao<MotorTwoSpeedIdleMethod> motorTwoSpeedIdleMethodDao) {
		this.motorTwoSpeedIdleMethodDao = motorTwoSpeedIdleMethodDao;
	}

	public IEnvironmentalLabelService getEnvironmentalLabelService() {
		return environmentalLabelService;
	}

	@Resource(name = "environmentalLabelService")
	public void setEnvironmentalLabelService(IEnvironmentalLabelService environmentalLabelService) {
		this.environmentalLabelService = environmentalLabelService;
	}

	private IEnvironmentalLabelService environmentalLabelService;
	
	
	public void copyTimeToStr(DetectionCommisionSheet detectionCommisionSheet, MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage) {
		if (detectionCommisionSheet.getDetectionTime() != null) {
			motorTwoSpeedIdleMethodPage.setDetectionTime(ChangeTimeFormat.getInstance().timeStampToPreciseString(detectionCommisionSheet.getDetectionTime()));
		}
		if (detectionCommisionSheet.getVehicleRegisterDate() != null) {
			motorTwoSpeedIdleMethodPage.setVehicleRegisterDate(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getVehicleRegisterDate()));
		}
	}

	@Override
	public DataGrid getMotorTwoSpeedIdleMethod(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage) {
		DataGrid dg = new DataGrid();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("detectionMethod", "摩托车双怠速法");
		String hql = "FROM DetectionCommisionSheet as t where t.detectionMethod=:detectionMethod";
		List<MotorTwoSpeedIdleMethodPage> tsim = new ArrayList<MotorTwoSpeedIdleMethodPage>();
		// 获取session
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession = req.getSession();
		String userStationName = (String) hSession.getAttribute("stationName");
		System.out.println(userStationName);

		if (!userStationName.trim().equals("市局") && !userStationName.trim().equals("")) {
			tMap.put("stationName", userStationName.trim());
			hql += " and t.stationName=:stationName";
		}
		if (motorTwoSpeedIdleMethodPage.getInspectionStationId() != null && !motorTwoSpeedIdleMethodPage.getInspectionStationId().trim().equals("") && userStationName.trim().equals("市局")) {
			tMap.put("stationName", motorTwoSpeedIdleMethodPage.getInspectionStationId().trim());
			hql += " and t.stationName=:stationName";
		}
		if (motorTwoSpeedIdleMethodPage.getReportNumber() != null && !motorTwoSpeedIdleMethodPage.getReportNumber().trim().equals("")) {
			tMap.put("reportNumber", "%" + motorTwoSpeedIdleMethodPage.getReportNumber().trim() + "%");
			hql += " and t.reportNumber like :reportNumber";
		}
		if (motorTwoSpeedIdleMethodPage.getVin() != null && !motorTwoSpeedIdleMethodPage.getVin().trim().equals("")) {
			tMap.put("vin", "%" + motorTwoSpeedIdleMethodPage.getVin().trim() + "%");
			hql += " and t.vin like :vin";
		}
		if (motorTwoSpeedIdleMethodPage.getBeforeDetectionTime() != null && !motorTwoSpeedIdleMethodPage.getBeforeDetectionTime().trim().equals("")) {
			tMap.put("beforeDetectionTime", ChangeTimeFormat.getInstance().strToTimeStamp(motorTwoSpeedIdleMethodPage.getBeforeDetectionTime().trim()));
			hql += " and t.detectionTime>=:beforeDetectionTime";
		}
		if (motorTwoSpeedIdleMethodPage.getAfterDetectionTime() != null && !motorTwoSpeedIdleMethodPage.getAfterDetectionTime().trim().equals("")) {
			tMap.put("afterDetectionTime", ChangeTimeFormat.getInstance().strToTimeStamp(motorTwoSpeedIdleMethodPage.getAfterDetectionTime().trim()));
			hql += " and t.detectionTime<=:afterDetectionTime";
		}
		
		if (motorTwoSpeedIdleMethodPage.getBeginRegisterDate() != null && !motorTwoSpeedIdleMethodPage.getBeginRegisterDate().trim().equals("")) {
			tMap.put("beginRegisterDate", ChangeTimeFormat.getInstance().strToTimeStamp(motorTwoSpeedIdleMethodPage.getBeginRegisterDate().trim()));
			hql += " and t.vehicleRegisterDate>=:beginRegisterDate";
		}
		if (motorTwoSpeedIdleMethodPage.getEndRegisterDate() != null && !motorTwoSpeedIdleMethodPage.getEndRegisterDate().trim().equals("")) {
			tMap.put("endRegisterDate", ChangeTimeFormat.getInstance().strToTimeStamp(motorTwoSpeedIdleMethodPage.getEndRegisterDate().trim()));
			hql += " and t.vehicleRegisterDate<=:endRegisterDate";
		}
		
		if (motorTwoSpeedIdleMethodPage.getVehicleOwnerName() != null && !motorTwoSpeedIdleMethodPage.getVehicleOwnerName().trim().equals("")) {
			tMap.put("vehicleOwnerName", "%" + motorTwoSpeedIdleMethodPage.getVehicleOwnerName().trim() + "%");
			hql += " and t.vehicleOwnerName like :vehicleOwnerName";
		}
		if (motorTwoSpeedIdleMethodPage.getLicence() != null && !motorTwoSpeedIdleMethodPage.getLicence().trim().equals("")) {
			tMap.put("licence", "%" + motorTwoSpeedIdleMethodPage.getLicence().trim() + "%");
			hql += " and t.licence like :licence";
		}
		
		
		if (motorTwoSpeedIdleMethodPage.getCommisionSheetStatus() != null && !motorTwoSpeedIdleMethodPage.getCommisionSheetStatus().trim().equals("")) {
			String status = motorTwoSpeedIdleMethodPage.getCommisionSheetStatus().trim();
			if (status.equals("已注销")) {
				tMap.put("status", 0);
				hql += " and t.commisionSheetStatus=:status";
			} else if (status.equals("未检测")) {
				tMap.put("status", 1);
				hql += " and t.commisionSheetStatus=:status";
			} else if (status.equals("已检测")) {
				tMap.put("status", 2);
				hql += " and t.commisionSheetStatus=:status";
			} else if (status.equals("已完工")) {
				tMap.put("status", 3);
				hql += " and t.commisionSheetStatus=:status";
			}
		} else {
			/*
			 * tMap.put("status", 1);
			 * hql+=" and t.commisionSheetStatus=:status";
			 */
		}
		
		if (motorTwoSpeedIdleMethodPage.getConclusion() != null && !motorTwoSpeedIdleMethodPage.getConclusion().trim().equals("")) {
			String conclusion = motorTwoSpeedIdleMethodPage.getConclusion().trim();
			if (conclusion.equals("合格")) {
				tMap.put("conclusion", 0);
				hql += " and t.conclusion=:conclusion";
			} else if (conclusion.equals("不合格")) {
				tMap.put("conclusion", 1);
				hql += " and t.conclusion=:conclusion";
			} else if (conclusion.equals("未下结论")) {
				tMap.put("conclusion", 2);
				hql += " and t.conclusion=:conclusion";
			} 
		}
		
		if (motorTwoSpeedIdleMethodPage.getEmissionStandard() != null 
		        && motorTwoSpeedIdleMethodPage.getEmissionStandard() != -1) {
			tMap.put("emissionStandard",motorTwoSpeedIdleMethodPage.getEmissionStandard());
			hql += " AND t.emissionStandard= :emissionStandard";
		}
	
		dg.setTotal(detectionCommisionSheetDao.count("SELECT COUNT(*) " + hql, tMap));
		hql += " order by t.id desc";
		List<DetectionCommisionSheet> lsm = detectionCommisionSheetDao.find(hql, tMap, motorTwoSpeedIdleMethodPage.getPage(), motorTwoSpeedIdleMethodPage.getRows());
		for (int i = 0; i < lsm.size(); i++) {
			MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod = lsm.get(i).getMotorTwoSpeedIdleMethod();
			MotorTwoSpeedIdleMethodPage tPage = new MotorTwoSpeedIdleMethodPage();
			BeanUtils.copyProperties(lsm.get(i), tPage, new String[] { "detectionTime", "vehicleRegisterDate", "commisionSheetStatus", "conclusion" });
			copyTimeToStr(lsm.get(i), tPage);
			System.out.println(motorTwoSpeedIdleMethod.getHHcresult());
			System.out.println(motorTwoSpeedIdleMethod.getHCo2result());
			System.out.println(motorTwoSpeedIdleMethod.getHCoresult());
			BeanUtils.copyProperties(motorTwoSpeedIdleMethod, tPage, new String[] { "id", "experimentTime" });
			tPage.setRecordId(motorTwoSpeedIdleMethod.getId());
			if (motorTwoSpeedIdleMethod.getSysUser() != null && !motorTwoSpeedIdleMethod.getSysUser().getUserName().trim().equals("")) {
				tPage.setSysUserId(motorTwoSpeedIdleMethod.getSysUser().getUserId());
				tPage.setSysUserName(motorTwoSpeedIdleMethod.getSysUser().getUserName().trim());
			}
			if (motorTwoSpeedIdleMethod.getDetectionLine() != null) {
				tPage.setDetectionLineId(motorTwoSpeedIdleMethod.getDetectionLine().getLineId());
				tPage.setDetectionLineName(motorTwoSpeedIdleMethod.getDetectionLine().getLineName());
			}
			if (lsm.get(i).getCommisionSheetStatus() != null) {
				Integer st = lsm.get(i).getCommisionSheetStatus();
				if (st == 0) {
					tPage.setCommisionSheetStatus("已注销");
				} else if (st == 1) {
					tPage.setCommisionSheetStatus("未检测");
				} else if (st == 2) {
					tPage.setCommisionSheetStatus("已检测");
				} else if (st == 3) {
					tPage.setCommisionSheetStatus("已完工");
				}
			}

			if (lsm.get(i).getConclusion() != null) {
				Integer st = lsm.get(i).getConclusion();
				if (st == 0) {
					tPage.setConclusion("合格");
				} else if (st == 1) {
					tPage.setConclusion("不合格");
				} else if (st == 2) {
					 tPage.setConclusion("未下结论");
				}
			}
			
			tsim.add(tPage);
		}
		dg.setRows(tsim);
		return dg;
	}

	@Override
	public Json updateMotorTwoSpeedIdleMethod(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage) {
		Json json = new Json();
		System.out.println(motorTwoSpeedIdleMethodPage.getRecordId());
		Map<String, Object> tMap = new HashMap<String, Object>();
		SysUser sysUser = new SysUser();
		DetectionLine detectionLine = new DetectionLine();
		try {

			tMap.put("id", motorTwoSpeedIdleMethodPage.getRecordId());
			String hql = "FROM DetectionCommisionSheet as t where t.motorTwoSpeedIdleMethod.id =:id ";
			DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.get(hql, tMap);
			MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod = detectionCommisionSheet.getMotorTwoSpeedIdleMethod();
			BeanUtils.copyProperties(motorTwoSpeedIdleMethodPage, motorTwoSpeedIdleMethod, new String[] { "id", "experimentTime" });
			if (motorTwoSpeedIdleMethodPage.getSysUserId() != null) {
				sysUser.setUserId(motorTwoSpeedIdleMethodPage.getSysUserId());
				motorTwoSpeedIdleMethod.setSysUser(sysUser);
			}
			if (motorTwoSpeedIdleMethodPage.getDetectionLineId() != null) {
				detectionLine.setLineId(motorTwoSpeedIdleMethodPage.getDetectionLineId());
				motorTwoSpeedIdleMethod.setDetectionLine(detectionLine);
			}
			if (motorTwoSpeedIdleMethodPage.getExperimentTime() != null && !motorTwoSpeedIdleMethodPage.getExperimentTime().trim().equals("")) {
				motorTwoSpeedIdleMethod.setExperimentTime(ChangeTimeFormat.getInstance().strToTimeStamp(motorTwoSpeedIdleMethodPage.getExperimentTime().trim()));
			}
			// 判断是否有检测数据，有则修改委托单状态位
			if (motorTwoSpeedIdleMethod.getWheelNums() != null && motorTwoSpeedIdleMethod.getStrokes() != null && motorTwoSpeedIdleMethod.getHcresult() != null && motorTwoSpeedIdleMethod.getCoresult() != null && motorTwoSpeedIdleMethod.getCo2result() != null) {
				detectionCommisionSheet.setCommisionSheetStatus(2);// 修改状态位
			} else {
				detectionCommisionSheet.setCommisionSheetStatus(1);// 修改状态位 未检测
			}

			motorTwoSpeedIdleMethodDao.update(motorTwoSpeedIdleMethod);
			detectionCommisionSheetDao.update(detectionCommisionSheet);
			json.setMsg("更新摩托车双怠速法检测数据成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("更新摩托车双怠速法检测数据失败！");
		}
		return json;
	}

	@Override
	public Json batchConclusion(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage) {
		Json json = new Json();
		String flag = "";
		if (motorTwoSpeedIdleMethodPage.getIds() != null) {
			try {
				if (!motorTwoSpeedIdleMethodPage.getIds().trim().equals("")) {
					for (String id : motorTwoSpeedIdleMethodPage.getIds().split(",")) {
						if (id != null && !id.trim().equals("")) {
							flag = id;
							Map<String, Object> tMap = new HashMap<String, Object>();
							tMap.put("id", Integer.parseInt(id));
							System.out.println(Integer.parseInt(id));
							String hql = "FROM DetectionCommisionSheet as s where s.id=:id";
							List<DetectionCommisionSheet> tlist = detectionCommisionSheetDao.find(hql, tMap);
							for (int i = 0; i < tlist.size(); i++) {
								if (tlist.get(i).getCommisionSheetStatus() != 2) {
									json.setSuccess(false);
									json.setMsg("编号" + tlist.get(i).getId() + "的委托单不是 '已检测' 状态");
									return json;
								}
								MotorTwoSpeedIdleMethod method = tlist.get(i).getMotorTwoSpeedIdleMethod();
								if(method.getCoresult()==0.0||method.getHcresult()==0.0||
										method.getHCoresult()==0.0||method.getHHcresult()==0.0){
									tlist.get(i).setConclusion(1);
									json.setMsg("编号" + flag +"检测数据异常，下结论失败");
									tlist.get(i).setCommisionSheetStatus(3);
									json.setSuccess(false);
									return json;
								}
								judge(tlist.get(i).getMotorTwoSpeedIdleMethod(), tlist.get(i));
								motorTwoSpeedIdleMethodDao.update(tlist.get(i).getMotorTwoSpeedIdleMethod());
								tlist.get(i).setCommisionSheetStatus(3);
								detectionCommisionSheetDao.update(tlist.get(i));
							}
						}
					}
					json.setSuccess(true);
					json.setMsg("批量下结论成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("对编号为" + flag + "的数据下结论失败");
				return json;
			}
		}
		return json;
	}

	// 与限值匹配进行判断是否合格
	public void judge(MotorTwoSpeedIdleMethod motor, DetectionCommisionSheet dSheet) {
		Map<String, Object> tMap = new HashMap<String, Object>();
		boolean coConclusion = false;
		boolean hCoConclusion = false;
		boolean hcConclusion = false;
		boolean hHcConclusion = false;
		java.text.DecimalFormat df0 = new java.text.DecimalFormat("#.00");
		java.text.DecimalFormat df1 = new java.text.DecimalFormat("#.0");
		java.text.DecimalFormat df2 = new java.text.DecimalFormat("#.0000000000");
		tMap.put("detectionMethod", "摩托车双怠速法");

		String hql = "FROM LimitValueReference lv where lv.detectionMethodReference.detectionMedhodName=:detectionMethod";
		if (dSheet.getVehicleRegisterDate() != null) {
			tMap.put("vehicleRegisterDate", dSheet.getVehicleRegisterDate());
			hql += " and lv.minRegisterTime<=:vehicleRegisterDate and lv.maxRegisterTime>:vehicleRegisterDate";
		}
		if (motor.getStrokes() != null) {
			tMap.put("strokes", motor.getStrokes());
			hql += " and lv.strokes=:strokes";
			System.out.println(motor.getStrokes());
		}
		if (motor.getWheelNums() != null) {
			tMap.put("wheelNums", motor.getWheelNums());
			hql += " and lv.wheelNums=:wheelNums";
			System.out.println(motor.getWheelNums());
		}
		LimitValueReference limitValueReference = limitValueReferenceDao.get(hql, tMap);
		if (limitValueReference != null) {
			motor.setColimit(limitValueReference.getSdsLCo());
			motor.setHColimit(limitValueReference.getSdsHCo());
			motor.setHclimit(limitValueReference.getSdsLHc());
			motor.setHHclimit(limitValueReference.getSdsHHc());
		}
		/* 开始修正 */
		String tString = "2010-07-01";
		Timestamp tp = ChangeTimeFormat.getInstance().strToTimeStamp(tString);
		if (dSheet.getVehicleRegisterDate().before(tp)) {
			// 冲程2
			if (motor.getStrokes() == 2) {
				if (motor.getCoresult() + motor.getCo2result() < 10) {
					//motor.setCoreviseResult(motor.getCoresult() * (double) 10 / (double) (motor.getCoresult() + motor.getCo2result()));
					double x = motor.getCoresult() * (double) 10 / (double) (motor.getCoresult() + motor.getCo2result());
					motor.setCoreviseResult(Double.parseDouble(df0.format(x)));
					System.out.println(motor.getCoreviseResult());
				} else {
					motor.setCoreviseResult(motor.getCoresult());
				}
			}
			// 冲程4
			if (motor.getStrokes() == 4) {
				if (motor.getCoresult() + motor.getCo2result() < 15) {
					//motor.setCoreviseResult(motor.getCoresult() * (double) 15 / (double) (motor.getCoresult() + motor.getCo2result()));
					double x = motor.getCoresult() * (double) 15 / (double) (motor.getCoresult() + motor.getCo2result());
					motor.setCoreviseResult(Double.parseDouble(df0.format(x)));
					System.out.println(motor.getCoreviseResult());
				} else {
					motor.setCoreviseResult(motor.getCoresult());
				}
			}
		} else {// 还需要修正高怠速
				// 冲程2
			if (motor.getStrokes() == 2) {
				if (motor.getCoresult() + motor.getCo2result() < 10) {	
				    //motor.setCoreviseResult(motor.getCoresult() * (double) 10 / (double) (motor.getCoresult() + motor.getCo2result()));
					double x = motor.getCoresult() * (double) 10 / (double) (motor.getCoresult() + motor.getCo2result());
					motor.setCoreviseResult(Double.parseDouble(df0.format(x)));
					System.out.println(motor.getCoreviseResult());
				} else {
					motor.setCoreviseResult(motor.getCoresult());
				}
				if (motor.getHCoresult() + motor.getHCo2result() < 10) {
					//motor.setHCoreviseResult(motor.getHCoresult() * (double) 10 / (double) (motor.getHCoresult() + motor.getHCo2result()));
					double x = motor.getHCoresult() * (double) 10 / (double) (motor.getHCoresult() + motor.getHCo2result());
					motor.setHCoreviseResult(Double.parseDouble(df0.format(x)));
					System.out.println(motor.getHCoreviseResult());
				} else {
					motor.setHCoreviseResult(motor.getHCoresult());
				}
			}
			// 冲程4
			if (motor.getStrokes() == 4) {
				if (motor.getCoresult() + motor.getCo2result() < 15) {
					//motor.setCoreviseResult(motor.getCoresult() * (double) 15 / (double) (motor.getCoresult() + motor.getCo2result()));
					double x = motor.getCoresult() * (double) 15 / (double) (motor.getCoresult() + motor.getCo2result());
					motor.setCoreviseResult(Double.parseDouble(df0.format(x)));
					System.out.println(motor.getCoreviseResult());
				} else {
					motor.setCoreviseResult(motor.getCoresult());
				}
				if (motor.getHCoresult() + motor.getHCo2result() < 15) {
					//motor.setHCoreviseResult(motor.getHCoresult() * (double) 15 / (double) (motor.getHCoresult() + motor.getHCo2result()));
					double x = motor.getHCoresult() * (double) 15 / (double) (motor.getHCoresult() + motor.getHCo2result());
					motor.setHCoreviseResult(Double.parseDouble(df0.format(x)));
					System.out.println(motor.getHCoreviseResult());
				} else {
					motor.setHCoreviseResult(motor.getHCoresult());
				}
			}
		}

		// 其他情况不判断修正,直接用结果赋值

		/* 开始修约 */
		if (motor.getCoreviseResult() != null && motor.getCoreviseResult() != 0) {
			double a = motor.getCoreviseResult();
			motor.setCoroundResult(Double.parseDouble(df1.format(a)));
		}
		if (motor.getHcresult() != null && motor.getHcresult() != 0) {
			double b = motor.getHcresult();
			motor.setHcroundResult(Double.parseDouble(df2.format(b)));
		}
		if (motor.getCo2result() != null && motor.getCo2result() != 0) {
			motor.setCo2roundResult(motor.getCo2result());
		}

		if (motor.getHCoresult() != null && motor.getHCoresult() != 0) {
			double a = 0.0;
			
			if(motor.getHCoreviseResult()!=null)
				a = motor.getHCoreviseResult();
			else
				a = motor.getHCoresult();
			
			motor.setHCoroundResult(Double.parseDouble(df1.format(a)));	
		}
		if (motor.getHHcresult() != null && motor.getHHcresult() != 0) {
			double b = motor.getHHcresult();
			motor.setHHcroundResult(Double.parseDouble(df2.format(b)));
		}
		if (motor.getHCo2result() != null && motor.getHCo2result() != 0) {
			motor.setHCo2roundResult(motor.getHCo2result());
		}
		
		
		//判定
		if (motor.getCoroundResult() != null && motor.getHcroundResult() != null) {
			if (motor.getCoroundResult() <= motor.getColimit()) {
				coConclusion = true;
			}
			if (motor.getHcroundResult() <= motor.getHclimit()) {
				hcConclusion = true;
			}

		}
		if (motor.getHCoroundResult() != null && motor.getHHcroundResult() != null) {
			if (motor.getHColimit() != null || motor.getHHclimit() != null) {
				if (motor.getHCoroundResult() <= motor.getHColimit()) {
					hCoConclusion = true;
				}
				if (motor.getHHcroundResult() <= motor.getHHclimit()) {
					hHcConclusion = true;
				}
			}
		} else if (motor.getHCoroundResult() == null && motor.getHHcroundResult() == null && (motor.getHColimit() != null || motor.getHHclimit() != null)) {
			hCoConclusion = false;
			hHcConclusion = false;
		}
		//H没有要求
		if (motor.getHColimit() == null && motor.getHHclimit() == null) {
			hCoConclusion = true;
			hHcConclusion = true;
		}
		if (coConclusion && hcConclusion && hCoConclusion && hHcConclusion) {
			dSheet.setConclusion(0);
			environmentalLabelService.addEnvironmentalLabel(dSheet);
		} else {
			dSheet.setConclusion(1);
		}

	}

	@Override
	public MotorTwoSpeedIdleMethodPage getDetectionById(Integer id) {
		MotorTwoSpeedIdleMethodPage tPage = new MotorTwoSpeedIdleMethodPage();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "摩托车双怠速法");
		params.put("id", id);
		System.out.println("id: " + id);
		String hql = "FROM DetectionCommisionSheet as s WHERE s.detectionMethod= :methodName AND s.id= :id";
		DetectionCommisionSheet lsm = detectionCommisionSheetDao.get(hql, params);
		// for (int i = 0; i < lsm.size(); i++) {
		if (lsm != null) {
			MotorTwoSpeedIdleMethod motorTwoSpeedIdleMethod = lsm.getMotorTwoSpeedIdleMethod();
			copyTimeToStr(lsm, tPage);
			BeanUtils.copyProperties(lsm, tPage, new String[] { "detectionTime", "vehicleRegisterDate", "commisionSheetStatus", "conclusion" });
			tPage.setRecordId(motorTwoSpeedIdleMethod.getId());
			BeanUtils.copyProperties(motorTwoSpeedIdleMethod, tPage, new String[] { "id", "experimentTime" });
			if (motorTwoSpeedIdleMethod.getSysUser() != null && !motorTwoSpeedIdleMethod.getSysUser().getUserName().trim().equals("")) {
				tPage.setSysUserId(motorTwoSpeedIdleMethod.getSysUser().getUserId());
				tPage.setSysUserName(motorTwoSpeedIdleMethod.getSysUser().getUserName().trim());
			}
			if (motorTwoSpeedIdleMethod.getDetectionLine() != null) {
				tPage.setDetectionLineId(motorTwoSpeedIdleMethod.getDetectionLine().getLineId());
			}
			if (motorTwoSpeedIdleMethod.getDetectionLine() != null) {
				tPage.setDetectionLineId(motorTwoSpeedIdleMethod.getDetectionLine().getLineId());
			}
			if (lsm.getCommisionSheetStatus() != null) {
				Integer st = lsm.getCommisionSheetStatus();
				if (st == 0) {
					tPage.setCommisionSheetStatus("已注销");
				} else if (st == 1) {
					tPage.setCommisionSheetStatus("未检测");
				} else if (st == 2) {
					tPage.setCommisionSheetStatus("已检测");
				} else if (st == 3) {
					tPage.setCommisionSheetStatus("已完工");
				}
			}

			if (lsm.getConclusion() != null) {
				if (lsm.getConclusion() == 0) {
					tPage.setConclusion("合格");
				} else if (lsm.getConclusion() == 1) {
					tPage.setConclusion("不合格");
				} else if (lsm.getConclusion() == 2) {
					 tPage.setConclusion("未下结论");
				}
			}			
		}
		
		String stationName=lsm.getStationName();
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("stationName",stationName);
		System.out.println("检测站"+stationName);
		String hql2= "FROM InspectionStation as t WHERE t.stationName= :stationName";
		InspectionStation station = stationDao.get(hql2, params2);
		tPage.setStationAddress(station.getStationAddress());
		tPage.setRemarks(station.getRemarks());
		tPage.setInstitutionNum(station.getInstitutionNum());
		return tPage;
	}

	@Override
	public Json beginDetection(MotorTwoSpeedIdleMethodPage motorTwoSpeedIdleMethodPage) {
		Map<String, Object> params = new HashMap<String, Object>();
		Json json = new Json();
		params.put("id", motorTwoSpeedIdleMethodPage.getRecordId());
		System.out.println("id: " + motorTwoSpeedIdleMethodPage.getRecordId());
		String hql = "FROM MotorTwoSpeedIdleMethod as s WHERE  s.id= :id";
		MotorTwoSpeedIdleMethod tMethod = motorTwoSpeedIdleMethodDao.get(hql, params);
		if (tMethod != null) {
			/*
			 * DetectionLine detectionLine=new DetectionLine();
			 * detectionLine.setLineId
			 * (motorTwoSpeedIdleMethodPage.getDetectionLineId());
			 * tMethod.setDetectionLine(detectionLine);
			 */
			tMethod.setDetectionLine(lineDao.get(DetectionLine.class, motorTwoSpeedIdleMethodPage.getDetectionLineId()));
			if (motorTwoSpeedIdleMethodPage.getSysUserId() != null) {
				SysUser sysUser = new SysUser();
				sysUser.setUserId(motorTwoSpeedIdleMethodPage.getSysUserId());
				tMethod.setSysUser(sysUser);
			}
			motorTwoSpeedIdleMethodDao.update(tMethod);
			json.setMsg("发送检测任务成功");
			json.setSuccess(true);
		} else {
			json.setMsg("发送检测任务失败");
			json.setSuccess(false);
		}
		return json;
	}
}
