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
import com.dbsoft.whjd.model.DetectionMethodReference;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.LimitValueReference;
import com.dbsoft.whjd.model.SteadyStateMethod;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.TwoSpeedIdleMethod;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.FreeAccelerationMethodPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.SteadyStateMethodPage;
import com.dbsoft.whjd.pageModel.TrainingRecordPage;
import com.dbsoft.whjd.service.IEnvironmentalLabelService;
import com.dbsoft.whjd.service.ISteadyStateMethodService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.TrimString;


@Service (value="steadyStateMethodService")
public class SteadyStateMethodServiceImpl implements ISteadyStateMethodService{
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<SteadyStateMethod> steadyStateMethodDao;
	private IBaseDao<DetectionLine> detectionLineDao;
	private IBaseDao<LimitValueReference> limitValueReferenceDao;
	private IBaseDao<DetectionMethodReference> detectionMethodReferenceDao;
	private IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao;
	private IBaseDao<InspectionStation> inspectionStationDao;
	
	
	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}
	
	@Resource(name="baseDao")
	public void setInspectionStationDao(
			IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}
	
	public IBaseDao<LimitValueReference> getLimitValueReferenceDao() {
		return limitValueReferenceDao;
	}
	@Resource(name="baseDao")
	public void setLimitValueReferenceDao(
			IBaseDao<LimitValueReference> limitValueReferenceDao) {
		this.limitValueReferenceDao = limitValueReferenceDao;
	}

	
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}

	@Resource(name="baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}


	public IBaseDao<SteadyStateMethod> getSteadyStateMethodDao() {
		return steadyStateMethodDao;
	}

	@Resource(name="baseDao")
	public void setSteadyStateMethodDao(
			IBaseDao<SteadyStateMethod> steadyStateMethodDao) {
		this.steadyStateMethodDao = steadyStateMethodDao;
	}


	public IBaseDao<DetectionLine> getDetectionLineDao() {
		return detectionLineDao;
	}

	@Resource(name="baseDao")
	public void setDetectionLineDao(IBaseDao<DetectionLine> detectionLineDao) {
		this.detectionLineDao = detectionLineDao;
	}


	public IBaseDao<DetectionMethodReference> getDetectionMethodReferenceDao() {
		return detectionMethodReferenceDao;
	}
	@Resource(name="baseDao")
	public void setDetectionMethodReferenceDao(
			IBaseDao<DetectionMethodReference> detectionMethodReferenceDao) {
		this.detectionMethodReferenceDao = detectionMethodReferenceDao;
	}
	
	
	public IBaseDao<DetectionCommisionSheet> getDetectionCommisionSheetDao() {
		return detectionCommisionSheetDao;
	}
	
	@Resource(name="baseDao")
	public void setDetectionCommisionSheetDao(
			IBaseDao<DetectionCommisionSheet> detectionCommisionSheetDao) {
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
	
	
	//为搜索委托单添加条件
	/**
	 * @author mark
	 * @param steadyStateMethodPage
	 * @param hql
	 * @param params
	 * @return
	 */
	public String addDetectionCommisionSheetWhere(SteadyStateMethodPage steadyStateMethodPage, String hql, Map<String, Object> params){
					
		Integer commisionSheetStatus = steadyStateMethodPage.getCommisionSheetStatus();
		
		String beginSearchTimeString = steadyStateMethodPage.getBeginSearchTimeString();
		
		String endSearchTimeString = steadyStateMethodPage.getEndSearchTimeString();
		
		String reportNumber  = steadyStateMethodPage.getReportNumber();
		
		String vin  = steadyStateMethodPage.getVin();
		
		String vehicleOwnerName = steadyStateMethodPage.getVehicleOwnerName();
		
		String licence = steadyStateMethodPage.getLicence();
		
		String stationName = steadyStateMethodPage.getStationName();
		
	    Integer  conclusion= steadyStateMethodPage.getConclusion();
	    
	    Integer    emissionStandard= steadyStateMethodPage.getEmissionStandard();
	    
	    String  beginRegisterDate= steadyStateMethodPage.getBeginRegisterDate();
	    
	    String  endRegisterDate= steadyStateMethodPage.getEndRegisterDate();
	    Double  maxTotalQualityLowerBound=steadyStateMethodPage.getMaxTotalQualityLowerBound();
	    
	    Double  maxTotalQualityUpperBound=steadyStateMethodPage.getMaxTotalQualityUpperBound();
	    if (maxTotalQualityLowerBound != null&& !maxTotalQualityLowerBound.equals("")) {
			params.put("maxTotalQualityLowerBound",steadyStateMethodPage.getMaxTotalQualityLowerBound());
			hql += " AND dt.maxTotalQuality >= :maxTotalQualityLowerBound";
		}
		if (maxTotalQualityUpperBound != null && !maxTotalQualityUpperBound.equals("")) {
			params.put("maxTotalQualityUpperBound", steadyStateMethodPage.getMaxTotalQualityUpperBound());
			hql += " AND dt.maxTotalQuality <= :maxTotalQualityUpperBound";
		}
		
	    if (beginRegisterDate != null&& !beginRegisterDate.equals("")) {
	    	params.put("beginRegisterDate", ChangeTimeFormat.getInstance()
					.strToTimeStamp(steadyStateMethodPage.getBeginRegisterDate()));
			hql += " AND dt.vehicleRegisterDate >= :beginRegisterDate";
		}
	    if (endRegisterDate != null&& !endRegisterDate.equals("")) {
	    	params.put("endRegisterDate", ChangeTimeFormat.getInstance()
					.strToTimeStamp(steadyStateMethodPage.getEndRegisterDate()));
			hql += " AND dt.vehicleRegisterDate <= :endRegisterDate";
		}
	    
	    if(emissionStandard != null && emissionStandard !=-1){
			hql += " AND dt.emissionStandard =:emissionStandard ";
			params.put("emissionStandard", emissionStandard);
		}
	    if(conclusion != null && conclusion !=-1){
			hql += " AND dt.conclusion =:conclusion ";
			params.put("conclusion", conclusion);
		}
		if(commisionSheetStatus != null && commisionSheetStatus >= 0){
			hql += " AND dt.commisionSheetStatus =:commisionSheetStatus ";
			params.put("commisionSheetStatus", commisionSheetStatus);
		}
		
		if(beginSearchTimeString!=null && !beginSearchTimeString.trim().equals("")){
			
			 Timestamp beginSearchTime=ChangeTimeFormat.getInstance().strToTimeStamp(beginSearchTimeString);
			 params.put("beginSearchTime", beginSearchTime);
			 hql+=" AND dt.detectionTime >=:beginSearchTime ";
		}
		
		if(endSearchTimeString !=null && !endSearchTimeString.trim().equals("")){
			Timestamp endSearchTime=ChangeTimeFormat.getInstance().strToTimeStamp(endSearchTimeString);
			params.put("endSearchTime", endSearchTime);
			hql+=" AND dt.detectionTime <=:endSearchTime ";
		}
		
		if(reportNumber != null && !reportNumber.trim().equals("")){
			hql += " AND dt.reportNumber LIKE :reportNumber ";
			params.put("reportNumber", "%"+reportNumber+"%");
		}
		
		if(vin != null && !vin.trim().equals("")){
			hql += " AND dt.vin LIKE :vin ";
			params.put("vin", "%"+vin+"%");
		}
		
		if( vehicleOwnerName != null && ! vehicleOwnerName.trim().equals("")){
			hql += " AND dt.vehicleOwnerName LIKE :vehicleOwnerName ";
			params.put("vehicleOwnerName", "%"+vehicleOwnerName+"%");
		}
		
		
		if(licence != null && !licence.trim().equals("")){
			hql += " AND dt.licence LIKE :licence ";
			params.put("licence", "%%"+licence+"%%");
		}
		
		if(stationName != null && !stationName.trim().equals("")){
			hql += " AND dt.stationName LIKE :stationName ";
			params.put("stationName", "%"+stationName+"%");
		}
		
				
					
								
		hql += " AND dt.detectionMethod=:detectionMethod ";
		params.put("detectionMethod", "稳态工况法");
		return hql;
		
	}
	
	//设定排序方式
	private String addOrder(SteadyStateMethodPage steadyStateMethodPage, String hql){
		String sortName = steadyStateMethodPage.getSort();
		if(sortName != null && sortName.equals("sheetId")){
			sortName = "id";
		}
		if (sortName!=null &&steadyStateMethodPage.getOrder()!=null){
			hql += " ORDER BY " + sortName + " "+steadyStateMethodPage.getOrder(); 
		}
		return hql;
	}
	
	
	/**
	 * 查询委托单datagrid
	 * @author mark
	 */
	public DataGrid findCommisionSheetsOfSteadyStateMethod(SteadyStateMethodPage steadyStateMethodPage){
		
		List<SteadyStateMethodPage> steadyStateMethodPages = new  ArrayList<SteadyStateMethodPage>();
		
		String hql = " FROM  DetectionCommisionSheet as dt WHERE 1 = 1 ";
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		//取得session 中的人员的站信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		String sessionStationName = (String)httpSession.getAttribute("stationName");
		
		if(steadyStateMethodPage != null){
			if(sessionStationName !=null && !sessionStationName.equals("市局")){
				steadyStateMethodPage.setStationName(sessionStationName);
			}
			
			hql = this.addDetectionCommisionSheetWhere(steadyStateMethodPage, hql, params);
		}else{
			return null;
		}
		
		// 获取session
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession = req.getSession();
		String userStationName = (String) hSession.getAttribute("stationName");
		System.out.println(userStationName);

		if (!userStationName.trim().equals("市局") && !userStationName.trim().equals("")) {
			params.put("stationName", userStationName.trim());
			hql += " and dt.stationName=:stationName";
		}
		if (steadyStateMethodPage.getInspectionStationId() != null && !steadyStateMethodPage.getInspectionStationId().trim().equals("") && userStationName.trim().equals("市局")) {
			params.put("stationName", steadyStateMethodPage.getInspectionStationId().trim());
			hql += " and dt.stationName=:stationName";
		}	
		
		
		DataGrid dg = new DataGrid();
		
		dg.setTotal(detectionCommisionSheetDao.count("SELECT COUNT(*) " + hql, params));
		
		
		hql = this.addOrder(steadyStateMethodPage, hql);
		
		List<DetectionCommisionSheet> tMaps = detectionCommisionSheetDao.find(hql,params, 
				steadyStateMethodPage.getPage(), steadyStateMethodPage.getRows());
		
		
		if(tMaps!=null && tMaps.size()>0){
			for (DetectionCommisionSheet t : tMaps) {
				
				SteadyStateMethodPage m = new SteadyStateMethodPage();
				SteadyStateMethod s = t.getSteadyStateMethod();
				m.setSheetId(t.getId());
				
				if(s!=null){
					
					BeanUtils.copyProperties(s, m, new String[]{});
					m.setRecordId(s.getId());
					if(s.getSysUserByInspecDriverId()!=null){
						m.setInspecDriverId(s.getSysUserByInspecDriverId().getUserId());
						m.setInspecDriverName(s.getSysUserByInspecDriverId().getUserName());
					}
					if(s.getSysUserByInspecOperatorId()!=null){
						m.setInspecOperatorId(s.getSysUserByInspecOperatorId().getUserId());
						m.setInspecOperatorName(s.getSysUserByInspecOperatorId().getUserName());
					}
					if(s.getDetectionLine()!=null){
						m.setLineId(s.getDetectionLine().getLineId());
						m.setLocaleId(s.getDetectionLine().getLocaleId());
						m.setLineName(s.getDetectionLine().getLineName());
					}
				}
				
				BeanUtils.copyProperties(t, m, new String[]{"detectionTime","vehicleRegisterDate"});
				
				m.setDetectionTime(ChangeTimeFormat.getInstance().timeStampToPreciseString(t.getDetectionTime()));
				
				m.setVehicleRegisterDate(ChangeTimeFormat.getInstance().timeStampToString(t.getVehicleRegisterDate()));
								
				steadyStateMethodPages.add(m);				
			}
		}

		dg.setRows(steadyStateMethodPages);
	
		return dg ;
	}
	
	public SteadyStateMethodPage getDetectionById(Integer id){
		
		SteadyStateMethodPage page = new SteadyStateMethodPage();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("methodName", "稳态工况法");
		
		if (id!=null && id > 0) {
			params.put("id", id);	
		}else {
			return page;
		}
		
		System.out.println("id: " + id);
		String hql = "FROM DetectionCommisionSheet as s WHERE s.detectionMethod= :methodName AND s.id= :id";
		DetectionCommisionSheet t = detectionCommisionSheetDao.get(hql, params);
		SteadyStateMethod s = t.getSteadyStateMethod();
		
		page.setSheetId(t.getId());
		
		if(s!=null){
			
			BeanUtils.copyProperties(s, page, new String[]{});
			page.setRecordId(s.getId());
			if(s.getSysUserByInspecDriverId()!=null){
				page.setInspecDriverId(s.getSysUserByInspecDriverId().getUserId());
				page.setInspecDriverName(s.getSysUserByInspecDriverId().getUserName());
			}
			if(s.getSysUserByInspecOperatorId()!=null){
				page.setInspecOperatorId(s.getSysUserByInspecOperatorId().getUserId());
				page.setInspecOperatorName(s.getSysUserByInspecOperatorId().getUserName());
			}
			if(s.getDetectionLine()!=null){
				page.setLineId(s.getDetectionLine().getLineId());
				page.setLocaleId(s.getDetectionLine().getLocaleId());
				page.setLineName(s.getDetectionLine().getLineName());
			}
		}
		
		BeanUtils.copyProperties(t, page, new String[]{"detectionTime","vehicleRegisterDate"});
		
		page.setDetectionTime(ChangeTimeFormat.getInstance().timeStampToPreciseString(t.getDetectionTime()));
		
		page.setVehicleRegisterDate(ChangeTimeFormat.getInstance().timeStampToString(t.getVehicleRegisterDate()));
		
		//添加检测站地址和检测站联系电话
		String stationName=t.getStationName();
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("stationName",stationName);
		System.out.println("检测站"+stationName);
		String hql2= "FROM InspectionStation as t WHERE t.stationName= :stationName";
		InspectionStation station = inspectionStationDao.get(hql2, params2);
		
		page.setStationAddress(station.getStationAddress());
		page.setRemarks(station.getRemarks());
		page.setInstitutionNum(station.getInstitutionNum());
		return page;
		
	}
	
	public String addLimitValueReferenceWhere(DetectionCommisionSheet dct, String hql, Map<String, Object> params){
		Timestamp registerDate= dct.getVehicleRegisterDate();
		Double baseQuality = dct.getBaseQuality();
		Double maxTotalQuality = dct.getMaxTotalQuality();
		Integer vehicleLoadNum = dct.getVehicleLoadNum();	//最大载客量
		Integer wheelNums = dct.getSteadyStateMethod().getWheelNums();
		
		Integer vehicleDetailType;
		
		//左闭右开
		//车辆生产时间（登记时间）条件	
		hql +=" AND s.minRegisterTime <= :registerDate AND s.maxRegisterTime >:registerDate ";
		params.put("registerDate", registerDate);
			
		
		//车辆	
		hql += " AND s.minBaseQuality < :baseQuality AND s.maxBaseQuality >= :baseQuality ";
		params.put("baseQuality", baseQuality);
		
		
		//判断车辆分类
		if (maxTotalQuality <= 2500) { 
			
			vehicleDetailType = 1;
			
		}else{
			
			vehicleDetailType = 2;
			
		}
		
		hql += " AND s.vehicleDetailType =:vehicleDetailType ";
		params.put("vehicleDetailType", vehicleDetailType);
		
		hql += " AND s.detectionMethodReference.detectionMedhodName = :detectionMethodName";
		params.put("detectionMethodName", "稳态工况法");
		
		return hql;
	}

	
	/**
	 * 批量下结论
	 */
	@Override
	public Json batchConclusion(SteadyStateMethodPage steadyStateMethodPage){
		String ids = steadyStateMethodPage.getIds();
		Json json = new Json();
		
		//对比
		if(ids!=null && ids!=""){
			String[] strIds= ids.split(",");
			for(int i = 0; i < strIds.length; i++){
				DetectionCommisionSheet dct = detectionCommisionSheetDao.get(DetectionCommisionSheet.class, Integer.parseInt(strIds[i].trim()));
				if(dct != null){
					SteadyStateMethod steadyStateMethod= dct.getSteadyStateMethod();
					
					if (steadyStateMethod != null){
			
						Timestamp registerDate= dct.getVehicleRegisterDate();
						Double baseQuality = dct.getBaseQuality();
						Double maxTotalQuality = dct.getMaxTotalQuality();
						Integer vehicleLoadNum = dct.getVehicleLoadNum();	//最大载客量
						Integer wheelNums = dct.getSteadyStateMethod().getWheelNums();
						
						//判断依据是否已输入
						if (registerDate == null) {
							json.setMsg("车辆登记日期未填写！");
							json.setSuccess(false);
							return json;
						}
						
						if (baseQuality == null || baseQuality <= 0) {
							json.setMsg("基准质量不合法！");
							json.setSuccess(false);
							return json;
						}
						
						if(maxTotalQuality == null){
							json.setMsg("最大总质量未填写！");
							json.setSuccess(false);
							return json;
						}
						
						if(vehicleLoadNum == null || vehicleLoadNum <= 0){
							json.setMsg("载客数不合法！");
							json.setSuccess(false);
							return json;
						}
						
						if (wheelNums == null || wheelNums < 2) {
							json.setMsg("车轮数不合法！");
							json.setSuccess(false);
							return json;
						}
						
						/*if(steadyStateMethod.getWtHcAsm5025()==0.0&&steadyStateMethod.getWtHcAsm2540()==0.0&&
								steadyStateMethod.getWtCoAsm5025()==0.0&&steadyStateMethod.getWtCoAsm2540()==0.0&&
								steadyStateMethod.getWtNoAsm2540()==0.0&&steadyStateMethod.getWtNoAsm5025()==0.0){
							dct.setConclusion(1);
							json.setMsg("检测数据异常，下结论失败");
							json.setSuccess(false);
							return json;
						}*/
						
						//取限值
						String hql = " FROM LimitValueReference AS s WHERE 1 = 1 ";
						Map<String, Object> params = new HashMap<String, Object>();
						hql = addLimitValueReferenceWhere(dct, hql, params);
						
						//取得参照限值
						List<LimitValueReference> tMaps = limitValueReferenceDao.find(hql, params);
						LimitValueReference limitValueReference;
						
						if(tMaps== null && tMaps.size() != 1 ){
							json.setMsg("参照限值未设置或设置有误！");
							json.setSuccess(false);
							return json;
						}else{
							limitValueReference = tMaps.get(0);
						}
						
						Double wtHcAsm5025Limit = limitValueReference.getWtHcAsm5025();
						Double wtCoAsm5025Limit = limitValueReference.getWtCoAsm5025();
						Double wtNoAsm5025Limit = limitValueReference.getWtNoAsm5025();
						
						Double wtHcAsm2540Limit = limitValueReference.getWtHcAsm2540();
						Double wtCoAsm2540Limit = limitValueReference.getWtCoAsm2540();
						Double wtNoAsm2540Limit = limitValueReference.getWtNoAsm2540();
						
						if(wtHcAsm2540Limit==null||wtHcAsm5025Limit==null||wtCoAsm2540Limit==null||wtCoAsm5025Limit==null||wtNoAsm2540Limit==null||wtNoAsm5025Limit==null){
							json.setMsg("具体参照限值未设置!");
							json.setSuccess(false);
							return json;
						}
						
						//5025检测值
						Double wtHcAsm5025 = steadyStateMethod.getWtHcAsm5025();
						Double wtCoAsm5025 = steadyStateMethod.getWtCoAsm5025();
						Double wtNoAsm5025 = steadyStateMethod.getWtNoAsm5025();
						//2540检测值
						Double wtHcAsm2540 = steadyStateMethod.getWtHcAsm2540();
						Double wtCoAsm2540 = steadyStateMethod.getWtCoAsm2540();
						Double wtNoAsm2540 = steadyStateMethod.getWtNoAsm2540();
						
						
						
						//如果5025中某一项为空，则检测数据出错误！
						/*if ((wtHcAsm5025 == null||wtHcAsm5025==0.0) || (wtCoAsm5025 == null||wtCoAsm5025==0.0) || (wtNoAsm5025 == null||wtNoAsm5025==0.0) ) {
							json.setMsg("单号为:"+dct.getReportNumber()+ "的委托单ASM5025测数据不合规定！");
							json.setSuccess(false);
							return json;
						}*/
						
						//如果2540项目并非都为空，也并非都不为空时，检测数据出现错误！
						if (!((wtHcAsm2540 == null || wtHcAsm2540 ==0.0)  && (wtCoAsm2540 == null || wtCoAsm2540 ==0.0) && (wtNoAsm2540 == null || wtNoAsm2540==0.0))
								&& !((wtHcAsm2540 != null||wtHcAsm2540!=0.0) && (wtCoAsm2540 != null||wtCoAsm2540!=0.0) && (wtNoAsm2540 != null||wtNoAsm2540!=0.0))) {
							json.setMsg("单号为："+dct.getReportNumber()+"的委托单ASM2540检测数据不合规定!");
							json.setSuccess(false);
							return json;
						}
						
						//将当前限值录入检测数据表
						dct.getSteadyStateMethod().setWtHcAsm5025Limit(wtHcAsm5025Limit);
						dct.getSteadyStateMethod().setWtCoAsm5025Limit(wtCoAsm5025Limit);
						dct.getSteadyStateMethod().setWtNoAsm5025Limit(wtNoAsm5025Limit);
						
						dct.getSteadyStateMethod().setWtHcAsm2540Limit(wtHcAsm2540Limit);
						dct.getSteadyStateMethod().setWtCoAsm2540Limit(wtCoAsm2540Limit);
						dct.getSteadyStateMethod().setWtNoAsm2540Limit(wtNoAsm2540Limit);
						
						//如果2540未检测
						if ((wtHcAsm2540 == null||wtHcAsm2540==0.0) && (wtCoAsm2540 == null||wtHcAsm2540==0.0) && (wtNoAsm2540 == null||wtHcAsm2540==0.0)) {
							
							if(wtHcAsm5025 <= wtHcAsm5025Limit/2){
								steadyStateMethod.setWtHcAsm5025Conclusion(true);
								steadyStateMethod.setWtHcAsm5025Judge(true);
							}else{
								steadyStateMethod.setWtHcAsm5025Conclusion(false);
								steadyStateMethod.setWtHcAsm5025Judge(false);
							}
							
							if(wtCoAsm5025 <= wtCoAsm5025Limit/2){
								steadyStateMethod.setWtCoAsm5025Conclusion(true);
								steadyStateMethod.setWtCoAsm5025Judge(true);
							}else{
								steadyStateMethod.setWtCoAsm5025Conclusion(false);
								steadyStateMethod.setWtCoAsm5025Judge(false);
							}
							
							if (wtNoAsm5025 <= wtNoAsm5025Limit/2) {
								
								steadyStateMethod.setWtNoAsm5025Conclusion(true);
								steadyStateMethod.setWtNoAsm5025Judge(true);
							}else{
								steadyStateMethod.setWtNoAsm5025Conclusion(false);
								steadyStateMethod.setWtNoAsm5025Judge(false);
							}
							
							if (steadyStateMethod.getWtHcAsm5025Conclusion()&& steadyStateMethod.getWtCoAsm5025Conclusion() && steadyStateMethod.getWtNoAsm5025Conclusion()) {
								dct.setConclusion(0);
								environmentalLabelService.addEnvironmentalLabel(dct);
								dct.setCommisionSheetStatus(3);
							}else{
								dct.setConclusion(1);
								dct.setCommisionSheetStatus(3);
							}
							
						
						}else {//进行了2540检测
							//5025判断
							if(wtHcAsm5025 <= wtHcAsm5025Limit){
								steadyStateMethod.setWtHcAsm5025Conclusion(true);
								steadyStateMethod.setWtHcAsm5025Judge(true);
							}else{
								steadyStateMethod.setWtHcAsm5025Conclusion(false);
								steadyStateMethod.setWtHcAsm5025Judge(false);
							}
							
							if(wtCoAsm5025 <= wtCoAsm5025Limit){
								steadyStateMethod.setWtCoAsm5025Conclusion(true);
								steadyStateMethod.setWtCoAsm5025Judge(true);
							}else{
								steadyStateMethod.setWtCoAsm5025Conclusion(false);
								steadyStateMethod.setWtCoAsm5025Judge(false);
							}
							
							if (wtNoAsm5025 <= wtNoAsm5025Limit) {
								
								steadyStateMethod.setWtNoAsm5025Conclusion(true);
								steadyStateMethod.setWtNoAsm5025Judge(true);
							}else{
								steadyStateMethod.setWtNoAsm5025Conclusion(false);
								steadyStateMethod.setWtNoAsm5025Judge(false);
							}
							
							/*
							if (steadyStateMethod.getWtHcAsm5025Conclusion()&& steadyStateMethod.getWtCoAsm5025Conclusion() && steadyStateMethod.getWtNoAsm5025Conclusion()) {
								dct.setConclusion(0);
								environmentalLabelService.addEnvironmentalLabel(dct);
								dct.setCommisionSheetStatus(3);
							}else{
								dct.setConclusion(1);
								dct.setCommisionSheetStatus(3);
								
								
								//如果失败在进行2540判断  卓勤政 170104改
								if(wtHcAsm2540 <= wtHcAsm2540Limit){
									steadyStateMethod.setWtHcAsm2540Conclusion(true);
									steadyStateMethod.setWtHcAsm2540Judge(true);
								}else{
									steadyStateMethod.setWtHcAsm2540Conclusion(false);
									steadyStateMethod.setWtHcAsm2540Judge(false);
								}
								
								if(wtCoAsm2540 <= wtCoAsm2540Limit){
									steadyStateMethod.setWtCoAsm2540Conclusion(true);
									steadyStateMethod.setWtCoAsm2540Judge(true);
								}else{
									steadyStateMethod.setWtCoAsm2540Conclusion(false);
									steadyStateMethod.setWtCoAsm2540Judge(false);
								}
								
								if (wtNoAsm2540 <= wtNoAsm2540Limit) {
									
									steadyStateMethod.setWtNoAsm2540Conclusion(true);
									steadyStateMethod.setWtNoAsm2540Judge(true);
								}else{
									steadyStateMethod.setWtNoAsm2540Conclusion(false);
									steadyStateMethod.setWtNoAsm2540Judge(false);
								}
								
								if (steadyStateMethod.getWtHcAsm2540Conclusion()&& steadyStateMethod.getWtCoAsm2540Conclusion() && steadyStateMethod.getWtNoAsm2540Conclusion()) {
									dct.setConclusion(0);
									environmentalLabelService.addEnvironmentalLabel(dct);
									dct.setCommisionSheetStatus(3);
								}else{
									dct.setConclusion(1);
									dct.setCommisionSheetStatus(3);
								}
							}*/
							
							
							//2540判断
							if(wtHcAsm2540 <= wtHcAsm2540Limit){
								steadyStateMethod.setWtHcAsm2540Conclusion(true);
								steadyStateMethod.setWtHcAsm2540Judge(true);
							}else{
								steadyStateMethod.setWtHcAsm2540Conclusion(false);
								steadyStateMethod.setWtHcAsm2540Judge(false);
							}
							
							if(wtCoAsm2540 <= wtCoAsm2540Limit){
								steadyStateMethod.setWtCoAsm2540Conclusion(true);
								steadyStateMethod.setWtCoAsm2540Judge(true);
							}else{
								steadyStateMethod.setWtCoAsm2540Conclusion(false);
								steadyStateMethod.setWtCoAsm2540Judge(false);
							}
							
							if (wtNoAsm2540 <= wtNoAsm2540Limit) {
								
								steadyStateMethod.setWtNoAsm2540Conclusion(true);
								steadyStateMethod.setWtNoAsm2540Judge(true);
							}else{
								steadyStateMethod.setWtNoAsm2540Conclusion(false);
								steadyStateMethod.setWtNoAsm2540Judge(false);
							}
							
							if (steadyStateMethod.getWtHcAsm2540Conclusion()&& steadyStateMethod.getWtCoAsm2540Conclusion() && steadyStateMethod.getWtNoAsm2540Conclusion()
								&& steadyStateMethod.getWtHcAsm5025Conclusion()&& steadyStateMethod.getWtCoAsm5025Conclusion() && steadyStateMethod.getWtNoAsm5025Conclusion()) {
								dct.setConclusion(0);
								environmentalLabelService.addEnvironmentalLabel(dct);
								dct.setCommisionSheetStatus(3);
							}else{
								dct.setConclusion(1);
								dct.setCommisionSheetStatus(3);
							}
						}
						
						//更新至数据库
						try {
							detectionCommisionSheetDao.update(dct);
						} catch (Exception e) {
							json.setMsg("批量下结论过程中出现异常！");
							json.setSuccess(false);
							return json;
						}
						
					}
				}
			}
			json.setMsg("批量下结论成功！");
			json.setSuccess(true);
		}else{
			json.setMsg("操作失败！");
			json.setSuccess(false);
		}
		
		return json;
		
	}
	
	public Json copyPageToMethod(SteadyStateMethodPage steadyStateMethodPage, SteadyStateMethod steadyStateMethod ){
		Json json = new Json();
		
		try {
			steadyStateMethodPage = (SteadyStateMethodPage)TrimString.getInstance().trimObjectString( steadyStateMethodPage);
		} catch (Exception e) {
		
			json.setMsg("编辑失败！");
			json.setSuccess(false);
			return json;
		}
		
		Integer recordId = steadyStateMethodPage.getRecordId();
		Integer inspecDriverId = steadyStateMethodPage.getInspecDriverId();
		Integer lineId = steadyStateMethodPage.getLineId();
		Integer inspecOperatorId = steadyStateMethodPage.getInspecOperatorId();
		String vehicleManufacturer = steadyStateMethodPage.getVehicleManufacturer();
		String engineManufacturer = steadyStateMethodPage.getEngineManufacturer();
		String fuelSpecification = steadyStateMethodPage.getFuelSpecification();
		Double singleAxleLoad = steadyStateMethodPage.getSingleAxleLoad();
		String chassisModel = steadyStateMethodPage.getChassisModel();
		String driveMode = steadyStateMethodPage.getDriveMode();
		Double tirePressure = steadyStateMethodPage.getTirePressure();
		String transmissionForm = steadyStateMethodPage.getTransmissionForm();
		Integer gearNumber = steadyStateMethodPage.getGearNumber();
		Integer cylinderNumber = steadyStateMethodPage.getCylinderNumber();
		String catalyticConverter = steadyStateMethodPage.getCatalyticConverter();
		String deviceAuthNumber = steadyStateMethodPage.getDeviceAuthNumber();
		String deviceName = steadyStateMethodPage.getDeviceName();
		String deviceModel = steadyStateMethodPage.getDeviceModel();
		String deviceManufacturer = steadyStateMethodPage.getDeviceManufacturer();
		String chassisDynamometer = steadyStateMethodPage.getChassisDynamometer();
		String exhaustGasAnalyser = steadyStateMethodPage.getExhaustGasAnalyser();
		Double temperature = steadyStateMethodPage.getTemperature();
		Double airPressure = steadyStateMethodPage.getAirPressure();
		Double relativeHumidity = steadyStateMethodPage.getRelativeHumidity();
//		Double wtHcAsm5025 = steadyStateMethodPage.getWtHcAsm5025();
//		Double wtHcAsm2540 = steadyStateMethodPage.getWtHcAsm2540();
//		Double wtCoAsm5025 = steadyStateMethodPage.getWtCoAsm5025();
//		Double wtCoAsm2540 = steadyStateMethodPage.getWtCoAsm2540();
//		Double wtNoAsm5025 = steadyStateMethodPage.getWtNoAsm5025();
//		Double wtNoAsm2540 = steadyStateMethodPage.getWtNoAsm2540();
		Integer reportStatus = 0;
		Integer wheelNums = steadyStateMethodPage.getWheelNums();
		
		

	    //判断值是否合法
		if(recordId == null || recordId< 0){
			json.setMsg("编辑失败！");
			json.setSuccess(false);
			return json;
		}
		
		if (wheelNums == null || wheelNums < 2) {
			json.setMsg("轮数有误！");
			json.setSuccess(false);
			return json;
		}
		
		if(lineId == null || lineId < 0){
			json.setMsg("检测线为必选项");
			json.setSuccess(false);
			return json;
		}
		
		if(inspecDriverId == null || inspecDriverId < 0){
			json.setMsg("检测驾驶员为必选项！");
			json.setSuccess(false);
			return json;
		}

		if(inspecOperatorId == null || inspecOperatorId < 0){
			json.setMsg("检测员为必选项！");
			json.setSuccess(false);
			return json;
		}
		
		
		if(vehicleManufacturer == null || vehicleManufacturer.trim().equals("")){
			json.setMsg("生产企业不能为空！");
			json.setSuccess(false);
			return json;
		}
		
		if(engineManufacturer == null || engineManufacturer.trim().equals("")){
			json.setMsg("发动机生产企业不能为空！");
			json.setSuccess(false);
			return json;
		}
		
		if(fuelSpecification == null || fuelSpecification.trim().equals("")){
			json.setMsg("燃油规格不能为空！");
			json.setSuccess(false);
			return json;
		}
		
		if(singleAxleLoad == null || singleAxleLoad <=0){
			json.setMsg("单车轴重不能为空！");
			json.setSuccess(false);
			return json;
		}
		
//		if(chassisModel == null || chassisModel.trim().equals("")){
//			json.setMsg("底盘型号必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
		if(driveMode ==  null || driveMode.trim().equals("")){
			json.setMsg("驱动方式必须输入！");
			json.setSuccess(false);
			return json;
		}
		
//		if(tirePressure == null || tirePressure < 0){
//			json.setMsg("胎压必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
		
		if(transmissionForm == null || transmissionForm.trim().equals("")){
			json.setMsg("变速器型式必须输入！");
			json.setSuccess(false);
			return json;
		}
		
		if(gearNumber == null || gearNumber < 0){
			json.setMsg("档位数必须输入！");
			json.setSuccess(false);
			return json;
		}
		
		if(cylinderNumber == null || cylinderNumber < 0){
			json.setMsg("气缸数必须输入！");
			json.setSuccess(false);
			return json;
		}
		
		if(catalyticConverter == null || catalyticConverter.trim().equals("")){
			json.setMsg("催化转化器情况必须输入！");
			json.setSuccess(false);
			return json;
		}
		
//		if(deviceAuthNumber == null || deviceAuthNumber.trim().equals("")){
//			json.setMsg("设备认证编码必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		if(deviceName == null || deviceName.trim().equals("")){
//			json.setMsg("设备名称必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		if(deviceModel == null || deviceModel.trim().equals("")){
//			json.setMsg("设备型号必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		if(deviceManufacturer == null ||deviceManufacturer.trim().equals("")){
//			json.setMsg("设备制造商必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
		
//		if(chassisDynamometer == null || chassisDynamometer.trim().equals("")){
//			json.setMsg("底盘测功机必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		if(exhaustGasAnalyser == null || exhaustGasAnalyser.trim().equals("")){
//			json.setMsg("排气分析仪必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
		
//		if(temperature == null ){
//			json.setMsg("温度必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		if(airPressure == null){
//			json.setMsg("气压必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
		
//		if(relativeHumidity == null){
//			json.setMsg("相对湿度必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
		
//		if(wtHcAsm5025 == null){
//			json.setMsg("HC_ASM5025测试结果必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		if(wtCoAsm5025 == null){
//			json.setMsg("CO_ASM5025测试结果必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		if(wtNoAsm5025 == null){
//			json.setMsg("NO_ASM5025测试结果必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
		
//		if(wtHcAsm2540 == null){
//			json.setMsg("HC_ASM2540测试结果必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		
//		
//		if(wtCoAsm2540 == null){
//			json.setMsg("CO_ASM2540测试结果必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
//		
//		
//		
//		if(wtNoAsm2540 == null){
//			json.setMsg("NO_ASM2540测试结果必须输入！");
//			json.setSuccess(false);
//			return json;
//		}
				
		if(steadyStateMethod!=null){
			BeanUtils.copyProperties(steadyStateMethodPage, steadyStateMethod, new String[]{"id","recordId"});
		}else{
			json.setMsg("编辑失败！");
			json.setSuccess(false);
			return json;
		}
	
		Map<String, Object> tMap = new HashMap<String, Object>();
		//查询人员信息
		String hql2 = "FROM SysUser as s where s.userId = :userId ";
		tMap.put("userId", inspecDriverId);
		SysUser sysUserByInspecDriverId;
		try {
			sysUserByInspecDriverId =sysUserDao.get(hql2, tMap);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("选择检测驾驶员失败！");
			return json;
		}
		
		
		tMap.clear();
		tMap.put("userId", inspecOperatorId);
		SysUser sysUserByInspecOperatorId;
		try {
			sysUserByInspecOperatorId = sysUserDao.get(hql2, tMap);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("选择检测操作员失败！");
			return json;
		}
		
		
		
		//查询检测线信息
		String hql3 ="FROM DetectionLine as dl WHERE dl.lineId = :lineId ";
		tMap.clear();
		tMap.put("lineId", lineId);
		DetectionLine detectionLine;
		try {
			detectionLine = detectionLineDao.get(hql3, tMap);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("选择检测线失败！");
			return json;
		}
		
		
		//设置人员和检测线信息
		if (sysUserByInspecDriverId==null) {
			json.setSuccess(false);
			json.setMsg("选择检驾驶员失败！");
			return json;
		}else {
			steadyStateMethod.setSysUserByInspecDriverId(sysUserByInspecDriverId);
		}
		
		if (sysUserByInspecOperatorId==null) {
			json.setSuccess(false);
			json.setMsg("选择检测操作员失败！");
			return json;
		}else {
			steadyStateMethod.setSysUserByInspecOperatorId(sysUserByInspecOperatorId);
		}
		
		if (detectionLine==null) {
			json.setSuccess(false);
			json.setMsg("选择检测线失败！");
			return json;
		}else {
			steadyStateMethod.setDetectionLine(detectionLine);
		}
				
		//copy成功
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * 更新检测数据信息
	 * @author mark
	 */
	@Override
	public Json updateSteadyStateMethodService(SteadyStateMethodPage steadyStateMethodPage){
		
		Json json = new Json();
		
		//取出所有的数据做校验！
		Integer sheetId = steadyStateMethodPage.getSheetId();
		
		if(sheetId == null || sheetId < 0){
			json.setMsg("未找到该单！");
			json.setSuccess(false);
			return json;
		}
		
		//获取一条检测数据记录
		String hql1 = "FROM DetectionCommisionSheet WHERE id =:sheetId  ";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("sheetId", sheetId);
				
		//查出委托单
		DetectionCommisionSheet dct = detectionCommisionSheetDao.get(hql1, tMap);
		if(dct == null){
			json.setMsg("未查询到该委托单！");
			json.setSuccess(false);
			return json;
		}
		
		//判断状态
		Integer commisionSheetStatus = dct.getCommisionSheetStatus();
		if(commisionSheetStatus== null || commisionSheetStatus < 1 || commisionSheetStatus > 2){
				json.setMsg("该委托单不可再编辑！");
				json.setSuccess(false);
				return json;
		}
				
		//查出检测数据
		SteadyStateMethod steadyStateMethod = dct.getSteadyStateMethod();
		
		if (steadyStateMethod == null) {
			json.setMsg("未查到该项记录！");
			json.setSuccess(false);
			return json;	
		}
		
		Integer reportStatus = steadyStateMethod.getReportStatus();
		
		if (reportStatus == null || reportStatus == 1 || reportStatus==2) {
			json.setMsg("该单已发往检测线操作失败！");
			json.setSuccess(false);
			return json;
		}
			
		json = copyPageToMethod(steadyStateMethodPage, steadyStateMethod);
		
		if (!json.isSuccess()) {
			return json;
		}
		
		//设置委托单状态为已检测
		dct.setCommisionSheetStatus(2);
		
		try {
			detectionCommisionSheetDao.update(dct);
			json.setSuccess(true);
			json.setMsg("编辑信息成功！");
		} catch (Exception e) {
			json.setMsg("编辑信息失败！");
			json.setSuccess(false);
		}
		
		return json;	
		
	}
	
	
	@Override
	public Json beginDetection(SteadyStateMethodPage steadyStateMethodPage) {
        Json json=new Json();
        
        //取出所有的数据做校验！
      	Integer sheetId = steadyStateMethodPage.getSheetId();
      		
	      	if(sheetId == null || sheetId < 0){
	      		json.setMsg("未找到该单！");
	      		json.setSuccess(false);
	      		return json;
	      	}
      		
      		//获取一条检测数据记录
      		String hql1 = "FROM DetectionCommisionSheet WHERE id =:sheetId  ";
      		Map<String, Object> tMap = new HashMap<String, Object>();
      		tMap.put("sheetId", sheetId);
      				
      		//查出委托单
      		DetectionCommisionSheet dct = detectionCommisionSheetDao.get(hql1, tMap);
      		if(dct == null){
      			json.setMsg("未查询到该委托单！");
      			json.setSuccess(false);
      			return json;
      		}
      		
      		//判断状态
      		Integer commisionSheetStatus = dct.getCommisionSheetStatus();
      		if(commisionSheetStatus== null || commisionSheetStatus < 1 || commisionSheetStatus > 2){
      				json.setMsg("该委托单不可再编辑！");
      				json.setSuccess(false);
      				return json;
      		}
      				
      		//查出检测数据
      	SteadyStateMethod steadyStateMethod = dct.getSteadyStateMethod();
		
		if (steadyStateMethod==null) {
			json.setMsg("未查到该项记录！");
			json.setSuccess(false);
			return json;
		}
		
		Integer reportStatus = steadyStateMethod.getReportStatus();
		
		if (reportStatus == null || reportStatus == 1 || reportStatus==2) {
			json.setMsg("该单已发往检测线操作失败！");
			json.setSuccess(false);
			return json;
		}
		
		json = copyPageToMethod(steadyStateMethodPage, steadyStateMethod);
		
		if (!json.isSuccess()) {
			return json;
		}
		
		steadyStateMethod.setReportStatus(1);
		
		try {
			steadyStateMethodDao.update(steadyStateMethod);
			json.setMsg("发送检测任务成功!");
			json.setSuccess(true);
			
		} catch (Exception e) {
			
			json.setMsg("发送检测任务失败!");
			json.setSuccess(false);
		}
		
		return json;
	}
}
