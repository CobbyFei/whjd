package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionCommisionSheet;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.FreeAccelerationMethod;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.LugDownMethod;
import com.dbsoft.whjd.model.LimitValueReference;
import com.dbsoft.whjd.model.LugDownMethod;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.LugDownMethodPage;
import com.dbsoft.whjd.pageModel.LugDownMethodPage;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.service.ILugDownMethodService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.TrimString;

@Service(value = "lugDownMethodService")
public class LugDownMethodServiceImpl implements ILugDownMethodService {
	private IBaseDao<LugDownMethod> methodDao;
	private IBaseDao<SysUser> userDao;
	private IBaseDao<DetectionCommisionSheet> sheetDao;
	private IBaseDao<InspectionStation> stationDao;
	private IBaseDao<DetectionLine> lineDao;
	private IBaseDao<LimitValueReference> limitDao;

	public IBaseDao<InspectionStation> getStationDao() {
		return stationDao;
	}

	@Resource(name = "baseDao")
	public void setStationDao(IBaseDao<InspectionStation> stationDao) {
		this.stationDao = stationDao;
	}
	
	public IBaseDao<LimitValueReference> getLimitDao() {
		return limitDao;
	}

	@Resource(name = "baseDao")
	public void setLimitDao(IBaseDao<LimitValueReference> limitDao) {
		this.limitDao = limitDao;
	}

	public IBaseDao<DetectionLine> getLineDao() {
		return lineDao;
	}

	@Resource(name = "baseDao")
	public void setLineDao(IBaseDao<DetectionLine> lineDao) {
		this.lineDao = lineDao;
	}

	public IBaseDao<SysUser> getUserDao() {
		return userDao;
	}

	@Resource(name = "baseDao")
	public void setUserDao(IBaseDao<SysUser> userDao) {
		this.userDao = userDao;
	}

	public IBaseDao<DetectionCommisionSheet> getSheetDao() {
		return sheetDao;
	}

	@Resource(name = "baseDao")
	public void setSheetDao(IBaseDao<DetectionCommisionSheet> sheetDao) {
		this.sheetDao = sheetDao;
	}

	public IBaseDao<LugDownMethod> getMethodDao() {
		return methodDao;
	}

	@Resource(name = "baseDao")
	public void setMethodDao(IBaseDao<LugDownMethod> methodDao) {
		this.methodDao = methodDao;
	}
	
	public IEnvironmentalLabelService getEnvironmentalLabelService() {
		return environmentalLabelService;
	}

	@Resource(name = "environmentalLabelService")
	public void setEnvironmentalLabelService(IEnvironmentalLabelService environmentalLabelService) {
		this.environmentalLabelService = environmentalLabelService;
	}

	private IEnvironmentalLabelService environmentalLabelService;

	/**
	 * 将LugDownMethodPage对象复制到LugDownMethod对象中
	 * 
	 * @author gsw
	 */
	public void copyToMethod(LugDownMethodPage page, LugDownMethod method) {
		BeanUtils.copyProperties(page, method, new String[] { "id" });
		method.setId(page.getMethodId());
		if (page.getAccessorId() != null)
			method.setSysUserByAccessorId(userDao.get(SysUser.class,
					page.getAccessorId()));
		if (page.getApproverId() != null)
			method.setSysUserByApproverId(userDao.get(SysUser.class,
					page.getApproverId()));
		if (page.getInspectorId() != null)
			method.setSysUserByInspectorId(userDao.get(SysUser.class,
					page.getInspectorId()));
		method.setDetectionLine(lineDao.get(DetectionLine.class,
				page.getLineId()));
		if (page.getIntEmissionProcessDevice() == 0)
			method.setEmissionProcessDevice(false);
		else
			method.setEmissionProcessDevice(true);
	}

	/**
	 * 将LugDownMethod对象复制到LugDownMethodPage对象中
	 * 
	 * @author gsw
	 */
	public void copyToPage(DetectionCommisionSheet sheet, LugDownMethodPage page) {
		BeanUtils.copyProperties(sheet, page);
		page.setStrDetectionTime(ChangeTimeFormat.getInstance()
				.timeStampToPreciseString(sheet.getDetectionTime()));
		page.setStrVehicleRegisterDate(ChangeTimeFormat.getInstance()
				.timeStampToString(sheet.getVehicleRegisterDate()));
		System.out.println(sheet.getLugDownMethod());
		LugDownMethod method = sheet.getLugDownMethod();
		System.out.println("method id: " + method.getId());
		BeanUtils.copyProperties(method, page, new String[] { "id" });
		page.setMethodId(method.getId());
		if (method.getEmissionProcessDevice() != null) {
			if (method.getEmissionProcessDevice())
				page.setIntEmissionProcessDevice(1);
			else
				page.setIntEmissionProcessDevice(0);
		}
		if (method.getSysUserByAccessorId() != null) {
			page.setAccessorId(method.getSysUserByAccessorId().getUserId());
			page.setAccessorName(method.getSysUserByAccessorId().getUserName());
		}
		if (method.getSysUserByApproverId() != null) {
			page.setApproverId(method.getSysUserByApproverId().getUserId());
			page.setApproverName(method.getSysUserByApproverId().getUserName());
		}
		if (method.getSysUserByInspectorId() != null) {
			page.setInspectorId(method.getSysUserByInspectorId().getUserId());
			page.setInspectorName(method.getSysUserByInspectorId()
					.getUserName());
		}
		if (method.getDetectionLine() != null) {
			page.setLineId(method.getDetectionLine().getLineId());
			page.setLineName(method.getDetectionLine().getLineName());
		}
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
		int wheelNums = sheet.getLugDownMethod().getWheelNums();
		if (maxTotalQuality > 3500)
			detailType = 3;
		else if (maxTotalQuality <= 2500)
			detailType = 1;
		else
			detailType = 2;
		return detailType;
	}
 
	@Override
	public Json updateMethod(LugDownMethodPage methodPage) throws Exception {
		Json j = new Json();
		methodPage = (LugDownMethodPage) TrimString.getInstance()
				.trimObjectString(methodPage);
		if (methodPage.getId() == null || methodPage.getId() < 0) {
			j.setMsg("未找到该单！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getMethodId() == null || methodPage.getMethodId() < 0) {
			j.setMsg("编辑失败！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getAccessorId() == null) {
			j.setMsg("审核员必须输入！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getApproverId() == null) {
			j.setMsg("批准人必须输入！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getInspectorId() == null) {
			j.setMsg("检测员必须输入！");
			j.setSuccess(false);
			return j;
		}
		DetectionCommisionSheet sheet = sheetDao.get(
				DetectionCommisionSheet.class, methodPage.getId());
		LugDownMethod method = methodDao.get(LugDownMethod.class,
				methodPage.getMethodId());
		// if (methodPage.getHundredPoint() != null
		// && methodPage.getNintyPoint() != null
		// && methodPage.getEightyPoint() != null
		// && methodPage.getKwResult() != null) {
		// String hql =
		// "FROM LimitValueReference as r WHERE r.detectionMethodReference.detectionMedhodName= :methodName";
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("methodName", "加载减速法");
		// Double kmLimit = limitDao.get(hql, params).getJzLimit();
		// Double kwLimit = limitDao.get(hql, params).getJzMaxRpm();
		// methodPage.setKmLimit(kmLimit);
		// if (methodPage.getHundredPoint() < kmLimit
		// && methodPage.getNintyPoint() < kmLimit
		// && methodPage.getEightyPoint() < kmLimit
		// && methodPage.getKwResult() > kwLimit)
		// methodPage.setConclusion(0);
		// else
		// methodPage.setConclusion(1);
		// }
		try {
			copyToMethod(methodPage, method);
			methodDao.update(method);
			if (methodPage.getHundredPoint() != null
					&& methodPage.getNintyPoint() != null
					&& methodPage.getEightyPoint() != null
					&& methodPage.getKwResult() != null)
				sheet.setCommisionSheetStatus(2);
			sheetDao.update(sheet);
			j.setMsg("修改成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("修改失败！");
		}
		return j;
	}

	@Override
	public DataGrid getAllDetections(LugDownMethodPage methodPage) {
		DataGrid dg = new DataGrid();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "加载减速法");
		String hql = "FROM DetectionCommisionSheet as s WHERE s.detectionMethod= :methodName";
		if (methodPage.getStationName() != null
				&& !methodPage.getStationName().trim().equals("")) {
			params.put("stationName", methodPage.getStationName());
			hql += " AND s.stationName= :stationName";
		}
		if (methodPage.getReportNumber() != null) {
			params.put("reportNumber", "%" + methodPage.getReportNumber() + "%");
			hql += " AND s.reportNumber like :reportNumber";
		}
		if (methodPage.getLicence() != null) {
			params.put("licence", "%" + methodPage.getLicence() + "%");
			hql += " AND s.licence like :licence";
		}
		if (methodPage.getVehicleOwnerName() != null) {
			params.put("vehicleOwnerName",
					"%" + methodPage.getVehicleOwnerName() + "%");
			hql += " AND s.vehicleOwnerName like :vehicleOwnerName";
		}
		if (methodPage.getVin() != null) {
			params.put("vin", "%" + methodPage.getVin() + "%");
			hql += " AND s.vin like :vin";
		}
		if (methodPage.getConclusion() != null 
		        && methodPage.getConclusion() != -1) {
			params.put("conclusion",methodPage.getConclusion());
			hql += " AND s.conclusion= :conclusion";
		}
		if (methodPage.getEmissionStandard() != null 
		        && methodPage.getEmissionStandard() != -1) {
			params.put("emissionStandard",methodPage.getEmissionStandard());
			hql += " AND s.emissionStandard= :emissionStandard";
		}
		if (methodPage.getBeginTime() != null
				&& !methodPage.getBeginTime().equals("")) {
			params.put("beginTime", ChangeTimeFormat.getInstance()
					.strToTimeStamp(methodPage.getBeginTime()));
			hql += " AND s.detectionTime >= :beginTime";
		}
		if (methodPage.getEndTime() != null
				&& !methodPage.getEndTime().equals("")) {
			params.put("endTime", ChangeTimeFormat.getInstance()
					.strToTimeStamp(methodPage.getEndTime()));
			hql += " AND s.detectionTime <= :endTime";
		}		
		if (methodPage.getBeginRegisterDate() != null
				&& !methodPage.getBeginRegisterDate().equals("")) {
			params.put("beginRegisterDate", ChangeTimeFormat.getInstance()
					.strToTimeStamp(methodPage.getBeginRegisterDate()));
			hql += " AND s.vehicleRegisterDate >= :beginRegisterDate";
		}
		if (methodPage.getEndRegisterDate() != null
				&& !methodPage.getEndRegisterDate().equals("")) {
			params.put("endRegisterDate", ChangeTimeFormat.getInstance()
					.strToTimeStamp(methodPage.getEndRegisterDate()));
			hql += " AND s.vehicleRegisterDate <= :endRegisterDate";
		}
		if (methodPage.getCommisionSheetStatus() != null
				&& methodPage.getCommisionSheetStatus() != -1) {
			params.put("status", methodPage.getCommisionSheetStatus());
			hql += " AND s.commisionSheetStatus= :status";
		}
		if (methodPage.getMaxTotalQualityLowerBound() != null
				&& !methodPage.getMaxTotalQualityLowerBound().equals("")) {
			params.put("maxTotalQualityLowerBound",methodPage.getMaxTotalQualityLowerBound());
			hql += " AND s.maxTotalQuality >= :maxTotalQualityLowerBound";
		}
		if (methodPage.getMaxTotalQualityUpperBound() != null
				&& !methodPage.getMaxTotalQualityUpperBound().equals("")) {
			params.put("maxTotalQualityUpperBound", methodPage.getMaxTotalQualityUpperBound());
			hql += " AND s.maxTotalQuality <= :maxTotalQualityUpperBound";
		}
		
		
		
		dg.setTotal(methodDao.count("SELECT COUNT(*)" + hql, params));
		hql += " ORDER BY s.id DESC";
		List<DetectionCommisionSheet> sheetList = sheetDao.find(hql, params,
				methodPage.getPage(), methodPage.getRows());
		List<LugDownMethodPage> pageList = new ArrayList<LugDownMethodPage>();
		for (DetectionCommisionSheet sheet : sheetList) {
			LugDownMethodPage page = new LugDownMethodPage();
			copyToPage(sheet, page);
			pageList.add(page);
		}
		dg.setRows(pageList);
		return dg;
	}

	@Override
	public Json batchConclusion(LugDownMethodPage methodPage) {
		Json j = new Json();
		if (methodPage.getIds() != null) {
			for (String id : methodPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					DetectionCommisionSheet sheet = sheetDao.get(
							DetectionCommisionSheet.class, Integer.valueOf(id));
					LugDownMethod method = sheet.getLugDownMethod();
					if (method != null) {
						String hql = "FROM LimitValueReference as r WHERE r.detectionMethodReference.detectionMedhodName= :methodName AND r.vehicleDetailType= :detailType AND r.minRegisterTime<= :registerTime AND r.maxRegisterTime> :registerTime";
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("methodName", "加载减速法");
						params.put("registerTime",
								sheet.getVehicleRegisterDate());
						int detailType = vehicleDetailTypeJudge(sheet);
						params.put("detailType", detailType);
						try {
							Double kmLimit = limitDao.get(hql, params)
									.getJzLimit();
							method.setKmLimit(kmLimit);
							Double kwlimit = method.getKwLimit() * 0.5;
							System.out.println(kwlimit);
							
							if(method.getHundredPoint()==0.00||method.getNintyPoint()==0.00||method.getEightyPoint()==0.00||method.getKwResult()==0.00){
								sheet.setConclusion(1);
								j.setMsg("检测数据异常，下结论失败");
								j.setSuccess(false);
								return j;
							}
							if (method.getHundredPoint() <= kmLimit
									&& method.getNintyPoint() <= kmLimit
									&& method.getEightyPoint() <= kmLimit
									&& method.getKwResult() >= kwlimit)
							{
								sheet.setConclusion(0);
								environmentalLabelService.addEnvironmentalLabel(sheet);
							}
							else
								sheet.setConclusion(1);
							sheet.setCommisionSheetStatus(3);
							sheetDao.update(sheet);
						} catch (Exception e) {
							e.printStackTrace();
							j.setSuccess(false);
							j.setMsg("批量下结论失败！");
						}
					} else {
						j.setMsg("未找到该单！");
						j.setSuccess(false);
						return j;
					}
				} else {
					j.setMsg("单号无效！");
					j.setSuccess(false);
					return j;
				}
			}
		} else {
			j.setMsg("未选择单号！");
			j.setSuccess(false);
			return j;
		}
		j.setMsg("批量下结论成功！");
		j.setSuccess(true);
		return j;
	}

	@Override
	public LugDownMethodPage getDetectionById(Integer id) {
		LugDownMethodPage page = new LugDownMethodPage();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "加载减速法");
		params.put("id", id);
		System.out.println("id: " + id);
		String hql = "FROM DetectionCommisionSheet as s WHERE s.detectionMethod= :methodName AND s.id= :id";
		DetectionCommisionSheet sheet = sheetDao.get(hql, params);
	
		String stationName=sheet.getStationName();
		System.out.println("sheet: " + sheet);
		copyToPage(sheet, page);
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("stationName",stationName);
		System.out.println("检测站"+stationName);
		String hql2= "FROM InspectionStation as t WHERE t.stationName= :stationName";
		InspectionStation station = stationDao.get(hql2, params2);
		
		page.setStationAddress(station.getStationAddress());
		page.setRemarks(station.getRemarks());
		page.setInstitutionNum(station.getInstitutionNum());
		return page;
	}

	@Override
	public Json beginDetection(LugDownMethodPage methodPage) {
		Json j = new Json();
		try {
			methodPage = (LugDownMethodPage) TrimString.getInstance()
					.trimObjectString(methodPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (methodPage.getId() == null || methodPage.getId() < 0) {
			j.setMsg("未找到该单！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getMethodId() == null || methodPage.getMethodId() < 0) {
			j.setMsg("编辑失败！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getAccessorId() == null) {
			j.setMsg("审核员必须输入！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getApproverId() == null) {
			j.setMsg("批准人必须输入！");
			j.setSuccess(false);
			return j;
		}
		if (methodPage.getInspectorId() == null) {
			j.setMsg("检测员必须输入！");
			j.setSuccess(false);
			return j;
		}
		DetectionCommisionSheet sheet = sheetDao.get(
				DetectionCommisionSheet.class, methodPage.getId());
		LugDownMethod method = methodDao.get(LugDownMethod.class,
				methodPage.getMethodId());
		try {
			copyToMethod(methodPage, method);
			method.setReportStatus(1);
			methodDao.update(method);
			sheetDao.update(sheet);
			j.setMsg("修改成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("修改失败！");
		}
		return j;

	}
}
