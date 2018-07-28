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
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.TrainingRecord;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;

import com.dbsoft.whjd.pageModel.TrainingRecordPage;
import com.dbsoft.whjd.service.ITrainingRecordService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.TrimString;

@Service(value = "trainingRecordService")
public class TrainingRecordServiceImpl implements ITrainingRecordService {
	
	private IBaseDao<TrainingRecord> trainingRecordDao;
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<InspectionStation> inspectionStationDao;
	
	
	private String addOrder(TrainingRecordPage trainingRecordPage, String hql){
		String sortName = trainingRecordPage.getSort();
		//对前端传来的排序Feild名进行转换。
		if("trainingRecordId".equals(sortName)){
			sortName = "recordId";
		}
		if (sortName!=null &&trainingRecordPage.getOrder()!=null){
			hql += " ORDER BY " + sortName + " "+trainingRecordPage.getOrder(); 
		}
		return hql;
	}
	
	/**
	 * 增加筛选条件
	 * @author mark
	 * @param trainingRecordPage
	 * @param hql
	 * @param params
	 * @return
	 */
private String addTrainingRecordWhere(TrainingRecordPage trainingRecordPage, String hql, Map<String, Object> params){
		
		String userName = trainingRecordPage.getUserName();
		String stationName = trainingRecordPage.getStationName();
		Integer trainingRecordStatus = trainingRecordPage.getTrainingRecordStatus();
		
		if (userName != null && !userName.trim().equals("")){
			hql += " AND tr.sysUser.userName LIKE :userName ";
			params.put("userName", "%%" + userName + "%%");
		}
		
		if (stationName != null && !stationName.trim().equals("")){
			hql += " AND tr.sysUser.inspectionStation.stationName LIKE :stationName ";
			params.put("stationName", "%%" + stationName + "%%");
		}
		
		if (trainingRecordStatus != null && trainingRecordStatus >= 0 && trainingRecordStatus <= 1){
			hql += " AND tr.status =:trainingRecordStatus" ;
			params.put("trainingRecordStatus", trainingRecordStatus);
		}
		
		return hql;
	}
	
	/**
	 * 新增培训或考核记录
	 * @author mark
	 */
	@Override
	public Json addTrainingRecord(TrainingRecordPage trainingRecordPage) {
		TrainingRecord trainingRecord = new TrainingRecord();
		
		Json json = new Json();
		if(trainingRecordPage != null){
			
			Integer userId = trainingRecordPage.getUserId();
			String strTrainingTime = trainingRecordPage.getStrTrainingTime();
			String trainingAddress = trainingRecordPage.getTrainingAddress();
			Integer trainingType = trainingRecordPage.getTrainingType();
			String trainingDetail = trainingRecordPage.getTrainingDetail();
			
			if(userId == null){
				json.setMsg("姓名不能为空！");
				json.setSuccess(false);
				return json;
			} 
			
			if(strTrainingTime == null ||!ChangeTimeFormat.getInstance().isTimeFormat(strTrainingTime)){
				json.setMsg("时间必须选择！");
				json.setSuccess(false);
				return json;
			}
			if(trainingAddress == null){
				json.setMsg("地点不能为空!");
				json.setSuccess(false);
				return json;
			}
			
			if(trainingType == null || trainingType < 0 ||trainingType > 1){
				json.setMsg("类别必选！");
				json.setSuccess(false);
				return json;
			}

			if(trainingDetail == null){
				json.setMsg("培训或考核内容不能为空！");
				json.setSuccess(false);
				return json;
			}
			
			try{
				trainingRecordPage = (TrainingRecordPage)TrimString.getInstance().trimObjectString(trainingRecordPage);
			}catch(Exception e){
				json.setSuccess(false);
	
				json.setMsg("新增记录失败！");
				return json;
			}
			
			BeanUtils.copyProperties(trainingRecordPage, trainingRecord);
	
			
			//查询人员
			String hql = "FROM SysUser as s WHERE s.userId=:userId AND s.status=:status";
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("userId", userId);
			tMap.put("status", 0);
			SysUser sysUser = sysUserDao.get(hql, tMap);
			
			if( sysUser != null ){
				trainingRecord.setSysUser(sysUser);
			}else{
				
				json.setMsg("新增记录失败！");
				json.setSuccess(false);
				return json;
			}
			//转为sql date格式
			trainingRecord.setTrainingTime(ChangeTimeFormat.getInstance().strToTimeStamp(strTrainingTime));
			
			//默认正常
			trainingRecord.setStatus(0);
			
			try{
				trainingRecordDao.save(trainingRecord);
				json.setMsg("新增记录成功！");
				json.setSuccess(true);
			}catch(Exception e){
			    e.printStackTrace();
				json.setMsg("新增记录失败！");
				json.setSuccess(false);
			}
			
		}else{
			json.setSuccess(false);
			json.setMsg("新增培训或考核记录失败！");
		}
		return json;
	}

	/**
	 * 查询培训及考核记录并返回datagrid
	 * @author mark
	 */

	@Override
	public DataGrid findTrainingRecords(TrainingRecordPage trainingRecordPage){
		
		List<TrainingRecordPage> trainingRecordPages = new ArrayList<TrainingRecordPage>();
		//取得session 中的人员的站信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		String sessionStationName = (String)httpSession.getAttribute("stationName");
		
		String hql = "FROM TrainingRecord as tr  WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(trainingRecordPage!=null){
			if(sessionStationName!=null && !sessionStationName.equals("市局")){
				trainingRecordPage.setStationName(sessionStationName);
			}
			hql = this.addTrainingRecordWhere(trainingRecordPage,hql,params);
			
		}else{
			return null;
		}
		
		DataGrid dg = new DataGrid();
		
		dg.setTotal(trainingRecordDao.count("SELECT COUNT(*) " + hql, params));
		
		hql = this.addOrder(trainingRecordPage,hql);
		
		List<TrainingRecord> tMaps = trainingRecordDao.find(hql, params, trainingRecordPage.getPage(), trainingRecordPage.getRows());
		
		if (tMaps!=null && tMaps.size()>0){
			for (TrainingRecord t : tMaps){
				TrainingRecordPage m = new TrainingRecordPage();
				
				BeanUtils.copyProperties(t, m, new String[]{});
				m.setStrTrainingTime(ChangeTimeFormat.getInstance().timeStampToString(t.getTrainingTime()));
				m.setTrainingRecordId(t.getRecordId());
				m.setTrainingRecordStatus(t.getStatus());
				
				SysUser sysUser = t.getSysUser();	
				if (sysUser!= null){
					m.setUserId(sysUser.getUserId());
					m.setUserName(sysUser.getUserName());
							
						//查询对应的单位信息
					InspectionStation inspectionStation =  sysUser.getInspectionStation();
					if(inspectionStation != null && inspectionStation.getStationId()>0){
						m.setStationId(inspectionStation.getStationId());
						m.setStationName(inspectionStation.getStationName());
						m.setStationStatus(inspectionStation.getStatus());	
					}
					trainingRecordPages.add(m);
				} 
			}
		}
		
		dg.setRows(trainingRecordPages);
		return dg;
	}
	
	/**
	 * 更新培训及考核记录
	 * @author mark
	 */

	@Override
	public Json updateTrainingRecord(TrainingRecordPage trainingRecordPage){
		Json json = new Json();
		
		Integer recordId = trainingRecordPage.getTrainingRecordId();
		Integer userId = trainingRecordPage.getUserId();
		String strTrainingTime = trainingRecordPage.getStrTrainingTime();
		Integer trainingType = trainingRecordPage.getTrainingType();
		String trainingDetail = trainingRecordPage.getTrainingDetail();
		
		
		if(recordId == null || recordId < 1||userId == null || userId < 0){
			json.setMsg("更新失败！");
			json.setSuccess(false);
			return json;
		}
		
		if(strTrainingTime == null||"".equals(strTrainingTime.trim())){
			json.setMsg("时间为必选项目！");
			json.setSuccess(false);
			return json;
		}
		
		if(trainingType == null || trainingType < 0 || trainingType > 1){
			json.setMsg("类型为必选项！");
			json.setSuccess(false);
			return json;
		}
		
		if(trainingDetail == null ||"".equals(trainingDetail.trim())){
			json.setMsg("内容不能为空！");
			json.setSuccess(false);
			return json;
		}
		
		try{
			trainingRecordPage =(TrainingRecordPage)TrimString.getInstance().trimObjectString(trainingRecordPage);
		
			String hql = "FROM TrainingRecord as tr WHERE 1 = 1 AND tr.recordId =:recordId AND tr.status =:status";
			
			Map<String ,Object> tMap = new HashMap<String, Object>();
			tMap.put("recordId",recordId);
			tMap.put("status", 0);
			
			TrainingRecord trainingRecord = trainingRecordDao.get(hql,tMap);
			 
			
			if(trainingRecord == null){
				json.setMsg("记录已注销！不可修改！");
				json.setSuccess(false);
				return json;
			}
			
			BeanUtils.copyProperties(trainingRecordPage, trainingRecord);
			trainingRecord.setTrainingTime(ChangeTimeFormat.getInstance().strToTimeStamp(strTrainingTime.trim()));	
			
			String hqls = "FROM SysUser as s WHERE 1 = 1 AND s.userId =:userId";
			//清除之前的参数，否则还会寻找其在语句中的位置报错！
			tMap.clear();
			tMap.put("userId", userId);
			
			SysUser sysUser = sysUserDao.get(hqls, tMap);
			
			if(sysUser != null){
				trainingRecord.setSysUser(sysUser);
			}
			
			trainingRecordDao.update(trainingRecord);
			json.setMsg("更新成功！");
			json.setSuccess(true);
			
		}catch(Exception e){
			json.setMsg("更新失败!");
			json.setSuccess(false);
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Override
	public Json changeTrainingRecordStatus(TrainingRecordPage trainingRecordPage){

			String ids = trainingRecordPage.getIds();
			Integer changeToStatus = trainingRecordPage.getChangeToStatus();
			Json json = new Json();
			if(ids!=null && ids!="" && changeToStatus!=null && changeToStatus >= 0 && changeToStatus <=1 ){

				String[] strIds= ids.split(",");
				
				for(int i = 0; i < strIds.length; i++){
					TrainingRecord trainingRecord = trainingRecordDao.get(TrainingRecord.class, Integer.parseInt(strIds[i].trim()));
					trainingRecord.setStatus(changeToStatus);
					
					try{		
						trainingRecordDao.update(trainingRecord);
					}catch(Exception e){
						json.setMsg("操作失败！");
						json.setSuccess(false);
						return json;
					}
					
				}
				json.setMsg("操作成功！");
				json.setSuccess(true);
				
				return json;
				
			}else{
				json.setMsg("操作失败！");
				json.setSuccess(false);
				
				return json;
			}
			
	}
	public IBaseDao<TrainingRecord> getTrainingRecordDao() {
		return trainingRecordDao;
	}
	@Resource(name="baseDao")				
	public void setTrainingRecordDao(IBaseDao<TrainingRecord> trainingRecordDao) {
		this.trainingRecordDao = trainingRecordDao;
	}
							
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	
	@Resource(name="baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}


	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}

	@Resource(name="baseDao")
	public void setInspectionStationDao(
			IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;

	}

}
