package com.dbsoft.whjd.service.impl;

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
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.TwoSpeedIdleMethod;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.TwoSpeedIdleMethodPage;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.service.ITwoSpeedIdleMethodService;
import com.dbsoft.whjd.util.ChangeTimeFormat;

@Service("twoSpeedIdleMethodService")
public class TwoSpeedIdleMethodServiceImpl implements ITwoSpeedIdleMethodService {
	private IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao;
	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	private IBaseDao<LimitValueReference> limitValueReferenceDao;
	private IBaseDao<DetectionLine> lineDao;
	private IBaseDao<InspectionStation> stationDao;
	
	public IBaseDao<InspectionStation> getStationDao() {
		return stationDao;
	}

	@Resource(name = "baseDao")
	public void setStationDao(IBaseDao<InspectionStation> stationDao) {
		this.stationDao = stationDao;
	}

	public IBaseDao<LimitValueReference> getLimitValueReferenceDao() {
		return limitValueReferenceDao;
	}

	public IBaseDao<DetectionLine> getLineDao() {
		return lineDao;
	}

	@Resource(name = "baseDao")
	public void setLineDao(IBaseDao<DetectionLine> lineDao) {
		this.lineDao = lineDao;
	}

	@Resource(name = "baseDao")
	public void setLimitValueReferenceDao(IBaseDao<LimitValueReference> limitValueReferenceDao) {
		this.limitValueReferenceDao = limitValueReferenceDao;
	}

	public IBaseDao<TwoSpeedIdleMethod> getTwoSpeedIdleMethodDao() {
		return twoSpeedIdleMethodDao;
	}

	@Resource(name = "baseDao")
	public void setTwoSpeedIdleMethodDao(IBaseDao<TwoSpeedIdleMethod> twoSpeedIdleMethodDao) {
		this.twoSpeedIdleMethodDao = twoSpeedIdleMethodDao;
	}

	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}

	@Resource(name = "baseDao")
	public void setDetectionCommisionSheetDao(IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
		this.detectionCommisionSheetDao = detectionCommisionSheetDao;
	}
	
	public IEnvironmentalLabelService getEnvironmentalLabelService() {
		return environmentalLabelService;
	}

	@Resource(name = "environmentalLabelService")
	public void setEnvironmentalLabelService(IEnvironmentalLabelService environmentalLabelService) {
		this.environmentalLabelService = environmentalLabelService;
	}

	private IEnvironmentalLabelService environmentalLabelService;

	@Override
	public DataGrid getTwoSpeedIdleMethod(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage) {
		DataGrid dg = new DataGrid();
		Map<String, Object> tMap = new HashMap<String, Object>();

		tMap.put("detectionMethod", "双怠速法");
		String hql = "FROM DetectionCommisionSheet as t where t.detectionMethod=:detectionMethod";
		List<TwoSpeedIdleMethodPage> tsim = new ArrayList<TwoSpeedIdleMethodPage>();
		// 获取session
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession = req.getSession();
		String userStationName = (String) hSession.getAttribute("stationName");
		System.out.println(userStationName);
        
		
		    
		    Integer    emissionStandard= twoSpeedIdleMethodPage.getEmissionStandard();
		    
		    String  beginRegisterDate= twoSpeedIdleMethodPage.getBeginRegisterDate();
		    
		    String  endRegisterDate= twoSpeedIdleMethodPage.getEndRegisterDate();
		    Double  maxTotalQualityLowerBound=twoSpeedIdleMethodPage.getMaxTotalQualityLowerBound();
		    
		    Double  maxTotalQualityUpperBound=twoSpeedIdleMethodPage.getMaxTotalQualityUpperBound();
		    if (maxTotalQualityLowerBound != null&& !maxTotalQualityLowerBound.equals("")) {
		    	tMap.put("maxTotalQualityLowerBound",twoSpeedIdleMethodPage.getMaxTotalQualityLowerBound());
				hql += " AND t.maxTotalQuality >= :maxTotalQualityLowerBound";
			}
			if (maxTotalQualityUpperBound != null && !maxTotalQualityUpperBound.equals("")) {
				tMap.put("maxTotalQualityUpperBound", twoSpeedIdleMethodPage.getMaxTotalQualityUpperBound());
				hql += " AND t.maxTotalQuality <= :maxTotalQualityUpperBound";
			}
			
		    if (beginRegisterDate != null&& !beginRegisterDate.equals("")) {
		    	tMap.put("beginRegisterDate", ChangeTimeFormat.getInstance()
						.strToTimeStamp(twoSpeedIdleMethodPage.getBeginRegisterDate()));
				hql += " AND t.vehicleRegisterDate >= :beginRegisterDate";
			}
		    if (endRegisterDate != null&& !endRegisterDate.equals("")) {
		    	tMap.put("endRegisterDate", ChangeTimeFormat.getInstance()
						.strToTimeStamp(twoSpeedIdleMethodPage.getEndRegisterDate()));
				hql += " AND t.vehicleRegisterDate <= :endRegisterDate";
			}
		    
		    if(emissionStandard != null && emissionStandard !=-1){
				hql += " AND t.emissionStandard =:emissionStandard ";
				tMap.put("emissionStandard", emissionStandard);
			}
		 
		if (!userStationName.trim().equals("市局") && !userStationName.trim().equals("")) {
			tMap.put("stationName", userStationName.trim());
			hql += " and t.stationName=:stationName";
		}
		if (twoSpeedIdleMethodPage.getInspectionStationId() != null && !twoSpeedIdleMethodPage.getInspectionStationId().trim().equals("") && userStationName.trim().equals("市局")) {
			// InspectionStation iStation=new InspectionStation();
			// iStation.setStationId(twoSpeedIdleMethodPage.getInspectionStationId());
			tMap.put("stationName", "%" + twoSpeedIdleMethodPage.getInspectionStationId().trim() + "%");
			hql += " and t.stationName like:stationName";
		}
		if (twoSpeedIdleMethodPage.getReportNumber() != null && !twoSpeedIdleMethodPage.getReportNumber().trim().equals("")) {
			tMap.put("reportNumber", "%" + twoSpeedIdleMethodPage.getReportNumber().trim() + "%");
			hql += " and t.reportNumber like :reportNumber";
		}
		if (twoSpeedIdleMethodPage.getVin() != null && !twoSpeedIdleMethodPage.getVin().trim().equals("")) {
			tMap.put("vin", "%" + twoSpeedIdleMethodPage.getVin().trim() + "%");
			hql += " and t.vin like :vin";
		}
		if (twoSpeedIdleMethodPage.getBeforeDetectionTime() != null && !twoSpeedIdleMethodPage.getBeforeDetectionTime().trim().equals("")) {
			tMap.put("beforeDetectionTime", ChangeTimeFormat.getInstance().strToTimeStamp(twoSpeedIdleMethodPage.getBeforeDetectionTime().trim()));
			hql += " and t.detectionTime>=:beforeDetectionTime";
		}
		if (twoSpeedIdleMethodPage.getAfterDetectionTime() != null && !twoSpeedIdleMethodPage.getAfterDetectionTime().trim().equals("")) {
			tMap.put("afterDetectionTime", ChangeTimeFormat.getInstance().strToTimeStamp(twoSpeedIdleMethodPage.getAfterDetectionTime().trim()));
			hql += " and t.detectionTime<=:afterDetectionTime";
		}
		if (twoSpeedIdleMethodPage.getVehicleOwnerName() != null && !twoSpeedIdleMethodPage.getVehicleOwnerName().trim().equals("")) {
			tMap.put("vehicleOwnerName", "%" + twoSpeedIdleMethodPage.getVehicleOwnerName().trim() + "%");
			hql += " and t.vehicleOwnerName like :vehicleOwnerName";
		}
		if (twoSpeedIdleMethodPage.getLicence() != null && !twoSpeedIdleMethodPage.getLicence().trim().equals("")) {
			tMap.put("licence", "%" + twoSpeedIdleMethodPage.getLicence().trim() + "%");
			hql += " and t.licence like :licence";
		}
		if (twoSpeedIdleMethodPage.getCommisionSheetStatus() != null && !twoSpeedIdleMethodPage.getCommisionSheetStatus().trim().equals("")) {
			String status = twoSpeedIdleMethodPage.getCommisionSheetStatus().trim();
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
			// tMap.put("status", 1);
			// hql += " and t.commisionSheetStatus=:status";
		}
		//
		if (twoSpeedIdleMethodPage.getConclusion() != null && !twoSpeedIdleMethodPage.getConclusion().trim().equals("")) {
				String conclusion = twoSpeedIdleMethodPage.getConclusion().trim();
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
			} else {
				// tMap.put("status", 1);
				// hql += " and t.commisionSheetStatus=:status";
			}   
		dg.setTotal(detectionCommisionSheetDao.count("SELECT COUNT(*) " + hql, tMap));
		hql += " order by id desc";
		List<DetectionCommisionSheet> lsm = detectionCommisionSheetDao.find(hql, tMap, twoSpeedIdleMethodPage.getPage(), twoSpeedIdleMethodPage.getRows());
		for (int i = 0; i < lsm.size(); i++) {
			TwoSpeedIdleMethod twoSpeedIdleMethod = lsm.get(i).getTwoSpeedIdleMethod();
			TwoSpeedIdleMethodPage tPage = new TwoSpeedIdleMethodPage();
			BeanUtils.copyProperties(lsm.get(i), tPage, new String[] { "detectionTime", "vehicleRegisterDate", "commisionSheetStatus", "conclusion" });
			// tPage.setRecordId(lsm.get(i).get);
			copyTimeToStr(lsm.get(i), tPage);
			BeanUtils.copyProperties(twoSpeedIdleMethod, tPage, new String[] { "id", "sysUserByInspecDriverId", "detectionLineId", "sysUserByInspecOperatorId", "sdsLambdaConclusion", "sdsLConclusion", "sdsHConclusion" });
			tPage.setRecordId(twoSpeedIdleMethod.getId());
			if (twoSpeedIdleMethod.getSysUserByInspecDriverId() != null && !twoSpeedIdleMethod.getSysUserByInspecDriverId().getUserName().trim().equals("")) {
				tPage.setSysUserByInspecDriverName(twoSpeedIdleMethod.getSysUserByInspecDriverId().getUserName());
				tPage.setSysUserByInspecDriverId(twoSpeedIdleMethod.getSysUserByInspecDriverId().getUserId());
			}
			if (twoSpeedIdleMethod.getSysUserByInspecOperatorId() != null && !twoSpeedIdleMethod.getSysUserByInspecOperatorId().getUserName().trim().equals("")) {
				tPage.setSysUserByInspecOperatorName(twoSpeedIdleMethod.getSysUserByInspecOperatorId().getUserName());
				tPage.setSysUserByInspecOperatorId(twoSpeedIdleMethod.getSysUserByInspecOperatorId().getUserId());
			}
			if (twoSpeedIdleMethod.getDetectionLine() != null) {
				tPage.setDetectionLineId(twoSpeedIdleMethod.getDetectionLine().getLineId());
				tPage.setDetectionLineName(twoSpeedIdleMethod.getDetectionLine().getLineName());
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
			if (twoSpeedIdleMethod.getSdsLambdaConclusion() != null) {
				if (twoSpeedIdleMethod.getSdsLambdaConclusion() == true) {
					tPage.setSdsLambdaConclusion("合格");
				} else {
					tPage.setSdsLambdaConclusion("不合格");
				}
			}
			if (twoSpeedIdleMethod.getSdsHConclusion() != null) {
				if (twoSpeedIdleMethod.getSdsHConclusion() == true) {
					tPage.setSdsHConclusion("合格");
				} else {
					tPage.setSdsHConclusion("不合格");
				}
			}
			if (twoSpeedIdleMethod.getSdsLConclusion() != null) {
				if (twoSpeedIdleMethod.getSdsLConclusion() == true) {
					tPage.setSdsLConclusion("合格");
				} else {
					tPage.setSdsLConclusion("不合格");
				}
			}
			if (lsm.get(i).getConclusion() != null) {
				if (lsm.get(i).getConclusion() == 0) {
					tPage.setConclusion("合格");
				} else if (lsm.get(i).getConclusion() == 1) {
					tPage.setConclusion("不合格");
				} else {
					// tPage.setConclusion("未下结论");
				}
			}
			tsim.add(tPage);
		}
		dg.setRows(tsim);
		return dg;
	}

	public void copyTimeToStr(DetectionCommisionSheet detectionCommisionSheet, TwoSpeedIdleMethodPage twoSpeedIdleMethodPage) {
		if (detectionCommisionSheet.getDetectionTime() != null) {
			twoSpeedIdleMethodPage.setDetectionTime(ChangeTimeFormat.getInstance().timeStampToPreciseString(detectionCommisionSheet.getDetectionTime()));
		}
		if (detectionCommisionSheet.getVehicleRegisterDate() != null) {
			twoSpeedIdleMethodPage.setVehicleRegisterDate(ChangeTimeFormat.getInstance().timeStampToString(detectionCommisionSheet.getVehicleRegisterDate()));
		}
	}

	@Override
	public Json updateTwoSpeedIdleMethod(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage) {
		Json json = new Json();
		System.out.println(twoSpeedIdleMethodPage.getRecordId());
		Map<String, Object> tMap = new HashMap<String, Object>();
		SysUser sysUserByInspecDriver = new SysUser();
		SysUser sysUserByInspecOperator = new SysUser();
		DetectionLine detectionLine = new DetectionLine();
		try {

			tMap.put("id", twoSpeedIdleMethodPage.getRecordId());
			String hql = "FROM DetectionCommisionSheet as t where t.twoSpeedIdleMethod.id =:id ";
			DetectionCommisionSheet detectionCommisionSheet = detectionCommisionSheetDao.get(hql, tMap);
			TwoSpeedIdleMethod twoSpeedIdleMethod = detectionCommisionSheet.getTwoSpeedIdleMethod();
			BeanUtils.copyProperties(twoSpeedIdleMethodPage, twoSpeedIdleMethod, new String[] { "id", "sysUserByInspecDriverId", "detectionLineId", "sysUserByInspecOperatorId", "sdsLambdaConclusion", "sdsLConclusion", "sdsHConclusion" });
			if (twoSpeedIdleMethodPage.getSysUserByInspecDriverId() != null) {
				sysUserByInspecDriver.setUserId(twoSpeedIdleMethodPage.getSysUserByInspecDriverId());
				twoSpeedIdleMethod.setSysUserByInspecDriverId(sysUserByInspecDriver);
			}
			if (twoSpeedIdleMethodPage.getSysUserByInspecOperatorId() != null) {
				sysUserByInspecOperator.setUserId(twoSpeedIdleMethodPage.getSysUserByInspecOperatorId());
				twoSpeedIdleMethod.setSysUserByInspecOperatorId(sysUserByInspecOperator);
			}
			if (twoSpeedIdleMethodPage.getDetectionLineId() != null) {
				detectionLine.setLineId(twoSpeedIdleMethodPage.getDetectionLineId());
				twoSpeedIdleMethod.setDetectionLine(detectionLine);
			}
			// 如果检测数据不空，设置委托单为已检测
			if (twoSpeedIdleMethod.getSdsLambda() != null && twoSpeedIdleMethod.getSdsHCo() != null && twoSpeedIdleMethod.getSdsHHc() != null && twoSpeedIdleMethod.getSdsHCo() != null && twoSpeedIdleMethod.getSdsHHc() != null) {

				detectionCommisionSheet.setCommisionSheetStatus(2);// 修改状态位 已检测
			} else {
				detectionCommisionSheet.setCommisionSheetStatus(1);// 修改状态位 未检测
			}
            
			//twoSpeedIdleMethodDao.update(twoSpeedIdleMethod);
			twoSpeedIdleMethodDao.saveOrUpdate(twoSpeedIdleMethod);
			System.out.println(twoSpeedIdleMethod.getWheelNums());
			detectionCommisionSheetDao.update(detectionCommisionSheet);
			json.setMsg("更新双怠速法检测数据成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("更新双怠速法检测数据失败！");
		}
		return json;
	}

	@Override
	public Json batchConclusion(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage) {
		Json json = new Json();
		String flag = "";
		if (twoSpeedIdleMethodPage.getIds() != null) {
			try {
				if (!twoSpeedIdleMethodPage.getIds().trim().equals("")) {
					for (String id : twoSpeedIdleMethodPage.getIds().split(",")) {
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
								judge(tlist.get(i).getTwoSpeedIdleMethod(), tlist.get(i));
								twoSpeedIdleMethodDao.update(tlist.get(i).getTwoSpeedIdleMethod());
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

	/**
	 * 判断车辆具体类型：一类轻型、二类轻型、重型
	 * 
	 * @author gsw
	 */
	public int vehicleDetailTypeJudge(DetectionCommisionSheet sheet) {
		int detailType = 0;
		Double maxTotalQuality = sheet.getMaxTotalQuality();
		int vehicleLoadNum = sheet.getVehicleLoadNum();
		int wheelNums = sheet.getTwoSpeedIdleMethod().getWheelNums();
		if (maxTotalQuality > 3500)
			detailType = 3;
		else if (maxTotalQuality <= 2500)
			detailType = 1;
		else
			detailType = 2;
		return detailType;
	}
	
	
	// 与限值匹配进行判断是否合格
	public void judge(TwoSpeedIdleMethod two, DetectionCommisionSheet dSheet) {
		
		int detailType = vehicleDetailTypeJudge(dSheet);
		
		
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("detectionMethod", "双怠速法");
		
		tMap.put("detailType", detailType);
		
		String hql = "FROM LimitValueReference lv where lv.detectionMethodReference.detectionMedhodName=:detectionMethod AND lv.vehicleDetailType= :detailType";
		if (dSheet.getVehicleRegisterDate() != null) {
			tMap.put("vehicleRegisterDate", dSheet.getVehicleRegisterDate());
			hql += " and lv.minRegisterTime<=:vehicleRegisterDate and lv.maxRegisterTime>=:vehicleRegisterDate";
		}
		LimitValueReference limitValueReference = limitValueReferenceDao.get(hql, tMap);
		if (limitValueReference.getId() != null) {
			two.setSdsLambdaLimit(limitValueReference.getSdsLambda());
			two.setSdsLCoLimit(limitValueReference.getSdsLCo());
			two.setSdsHCoLimit(limitValueReference.getSdsHCo());
			two.setSdsLHcLimit(limitValueReference.getSdsLHc());
			two.setSdsHHcLimit(limitValueReference.getSdsHHc());

		}
		// System.out.println(two.getSdsHCo()+","+two.getSdsLambda()+","+two.getSdsHHc()+","+two.getSdsLCo()+","+two.getSdsLHc());
		if (two.getSdsLambda() != null && two.getSdsHCo() != null && two.getSdsLCo() != null && two.getSdsHHc() != null && two.getSdsLHc() != null) {
			if (two.getSdsLambda() <= (two.getSdsLambdaLimit() + 0.03) && two.getSdsLambda() >= (two.getSdsLambdaLimit() - 0.03)) {
				two.setSdsLambdaConclusion(true);
			} else {
				two.setSdsLambdaConclusion(false);
			}
			if (two.getSdsHCo() <= two.getSdsHCoLimit() && two.getSdsHHc() <= two.getSdsHHcLimit()) {
				two.setSdsHConclusion(true);
			} else {
				two.setSdsHConclusion(false);
			}
			if (two.getSdsLCo() <= two.getSdsLCoLimit() && two.getSdsLHc() <= two.getSdsLHcLimit()) {
				two.setSdsLConclusion(true);
			} else {
				two.setSdsLConclusion(false);
			}
		}
		if (two.getSdsLambdaConclusion() && two.getSdsHConclusion() && two.getSdsLConclusion()) {
			dSheet.setConclusion(0);
			environmentalLabelService.addEnvironmentalLabel(dSheet);
		} else {
			dSheet.setConclusion(1);
		}
	}

	@Override
	public TwoSpeedIdleMethodPage getDetectionById(Integer id) {
		TwoSpeedIdleMethodPage tPage = new TwoSpeedIdleMethodPage();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "双怠速法");
		params.put("id", id);
		System.out.println("id: " + id);
		String hql = "FROM DetectionCommisionSheet as s WHERE s.detectionMethod= :methodName AND s.id= :id";
		DetectionCommisionSheet lsm = detectionCommisionSheetDao.get(hql, params);
		// for (int i = 0; i < lsm.size(); i++) {
		if (lsm != null) {
			TwoSpeedIdleMethod twoSpeedIdleMethod = lsm.getTwoSpeedIdleMethod();
			BeanUtils.copyProperties(lsm, tPage, new String[] { "detectionTime", "vehicleRegisterDate", "commisionSheetStatus", "conclusion" });
			// tPage.setRecordId(lsm.get(i).get);
			copyTimeToStr(lsm, tPage);
			BeanUtils.copyProperties(twoSpeedIdleMethod, tPage, new String[] { "id", "sysUserByInspecDriverId", "detectionLineId", "sysUserByInspecOperatorId", "sdsLambdaConclusion", "sdsLConclusion", "sdsHConclusion" });
			tPage.setRecordId(twoSpeedIdleMethod.getId());
			if (twoSpeedIdleMethod.getSysUserByInspecDriverId() != null && !twoSpeedIdleMethod.getSysUserByInspecDriverId().getUserName().trim().equals("")) {
				tPage.setSysUserByInspecDriverName(twoSpeedIdleMethod.getSysUserByInspecDriverId().getUserName());
				tPage.setSysUserByInspecDriverId(twoSpeedIdleMethod.getSysUserByInspecDriverId().getUserId());
			}
			if (twoSpeedIdleMethod.getSysUserByInspecOperatorId() != null && !twoSpeedIdleMethod.getSysUserByInspecOperatorId().getUserName().trim().equals("")) {
				tPage.setSysUserByInspecOperatorName(twoSpeedIdleMethod.getSysUserByInspecOperatorId().getUserName());
				tPage.setSysUserByInspecOperatorId(twoSpeedIdleMethod.getSysUserByInspecOperatorId().getUserId());
			}
			if (twoSpeedIdleMethod.getDetectionLine() != null) {
				tPage.setDetectionLineId(twoSpeedIdleMethod.getDetectionLine().getLineId());
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
				Integer st = lsm.getConclusion();
				if (st == 0) {
					tPage.setConclusion("合格");;
				} else if (st == 1) {
					tPage.setConclusion("不合格");
				} else if (st == 2) {
					tPage.setConclusion("未下结论");
				}
			}
			if (twoSpeedIdleMethod.getSdsLambdaConclusion() != null) {
				if (twoSpeedIdleMethod.getSdsLambdaConclusion() == true) {
					tPage.setSdsLambdaConclusion("合格");
				} else {
					tPage.setSdsLambdaConclusion("不合格");
				}
			}
			if (twoSpeedIdleMethod.getSdsHConclusion() != null) {
				if (twoSpeedIdleMethod.getSdsHConclusion() == true) {
					tPage.setSdsHConclusion("合格");
				} else {
					tPage.setSdsHConclusion("不合格");
				}
			}
			if (twoSpeedIdleMethod.getSdsLConclusion() != null) {
				if (twoSpeedIdleMethod.getSdsLConclusion() == true) {
					tPage.setSdsLConclusion("合格");
				} else {
					tPage.setSdsLConclusion("不合格");
				}
			}
			if (lsm.getConclusion() != null) {
				if (lsm.getConclusion() == 0) {
					tPage.setConclusion("合格");
				} else if (lsm.getConclusion() == 1) {
					tPage.setConclusion("不合格");
				} else {
					// tPage.setConclusion("未下结论");
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
	public Json beginDetection(TwoSpeedIdleMethodPage twoSpeedIdleMethodPage) {
		Map<String, Object> params = new HashMap<String, Object>();
		Json json = new Json();
		params.put("id", twoSpeedIdleMethodPage.getRecordId());
		System.out.println("id: " + twoSpeedIdleMethodPage.getRecordId());
		String hql = "FROM TwoSpeedIdleMethod as s WHERE  s.id= :id";
		TwoSpeedIdleMethod tMethod = twoSpeedIdleMethodDao.get(hql, params);
		if (tMethod != null) {
			// DetectionLine detectionLine=new DetectionLine();

			// detectionLine.setLineId(twoSpeedIdleMethodPage.getDetectionLineId());
			// tMethod.setDetectionLine(detectionLine);
			tMethod.setDetectionLine(lineDao.get(DetectionLine.class, twoSpeedIdleMethodPage.getDetectionLineId()));
			if (twoSpeedIdleMethodPage.getSysUserByInspecOperatorId() != null) {
				SysUser sysUserByInspecOperator = new SysUser();
				sysUserByInspecOperator.setUserId(twoSpeedIdleMethodPage.getSysUserByInspecOperatorId());
				tMethod.setSysUserByInspecOperatorId(sysUserByInspecOperator);
			}
			if (twoSpeedIdleMethodPage.getSysUserByInspecDriverId() != null) {
				SysUser sysUserByInspecDriver = new SysUser();
				sysUserByInspecDriver.setUserId(twoSpeedIdleMethodPage.getSysUserByInspecDriverId());
				tMethod.setSysUserByInspecDriverId(sysUserByInspecDriver);
			}
			twoSpeedIdleMethodDao.update(tMethod);
			json.setMsg("发送检测任务成功");
			json.setSuccess(true);
		} else {
			json.setMsg("发送检测任务失败");
			json.setSuccess(false);
		}
		return json;
	}

}
