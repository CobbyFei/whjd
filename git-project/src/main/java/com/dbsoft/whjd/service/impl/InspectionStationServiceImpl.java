package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hamcrest.core.Is;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.DetectionLine;
import com.dbsoft.whjd.model.DevicePurchaseRecord;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.ReferenceMaterialsRecord;
import com.dbsoft.whjd.model.StationManagement;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.ReferenceMaterialsRecordPage;
import com.dbsoft.whjd.service.IHuanBaoBuService;
import com.dbsoft.whjd.service.IInspectionStationService;
import com.dbsoft.whjd.util.ChangeTimeFormat;



@Service("inspectionStationService")
public class InspectionStationServiceImpl implements IInspectionStationService{
	private IBaseDao<InspectionStation> inspectionStationDao;
	private IBaseDao<StationManagement> stationManagementDao;
	private IBaseDao<SysUser> sysUserDao;
	private IHuanBaoBuService huanBaoBuService;
	
	

	public IHuanBaoBuService getHuanBaoBuService() {
		return huanBaoBuService;
	}
	@Autowired
	public void setHuanBaoBuService(IHuanBaoBuService huanBaoBuService) {
		this.huanBaoBuService = huanBaoBuService;
	}
	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}
	@Resource(name = "baseDao")
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}
	public IBaseDao<StationManagement> getStationManagementDao() {
		return stationManagementDao;
	}
	@Resource(name = "baseDao")
	public void setStationManagementDao(IBaseDao<StationManagement> stationManagementDao) {
		this.stationManagementDao = stationManagementDao;
	}
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Resource(name = "baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	/**
        从model复制时间到pagemodel
	 */
	public void copyTimeToStr(InspectionStation is,InspectionStationPage isPage) {
		if(is.getRegistrationTime()!=null)
		   isPage.setRegistrationTime(ChangeTimeFormat.getInstance().timeStampToString(is.getRegistrationTime()));
		if(is.getValidPeriod()!=null)
		   isPage.setValidPeriod(ChangeTimeFormat.getInstance().timeStampToString(is.getValidPeriod()));
		if(is.getQulificationTime()!=null)
		   isPage.setQulificationTime(ChangeTimeFormat.getInstance().timeStampToString(is.getQulificationTime()));			
	}
	/**
         从pagemodel复制时间到model
    */
	public void copyStrToTime(InspectionStationPage isPage,InspectionStation is) {
	  if(isPage.getRegistrationTime().trim()!=""&&isPage.getRegistrationTime()!=null)
		is.setRegistrationTime(ChangeTimeFormat.getInstance().strToTimeStamp(isPage.getRegistrationTime()));
	  if(isPage.getValidPeriod().trim()!=""&&isPage.getValidPeriod()!=null)	
		is.setValidPeriod(ChangeTimeFormat.getInstance().strToTimeStamp(isPage.getValidPeriod()));
	  if(isPage.getQulificationTime().trim()!=""&&isPage.getQulificationTime()!=null)	
		is.setQulificationTime(ChangeTimeFormat.getInstance().strToTimeStamp(isPage.getQulificationTime()));	
	}
	@Override
	public DataGrid getInspectionStation(InspectionStationPage inspectionStationPage) {
		DataGrid dg = new DataGrid();
		Map<String, Object> tMap = new HashMap<String, Object>();
		String hql = "FROM StationManagement sm where 1=1";
		List<InspectionStationPage> ls = new ArrayList<InspectionStationPage>();
		//List<InspectionStation> l=new ArrayList<InspectionStation>();
		if (inspectionStationPage.getStationId()!=null) {
			tMap.put("stationId", inspectionStationPage.getStationId());
			hql+=" and sm.inspectionStation.stationId=:stationId";
		}
		if (inspectionStationPage.getDirectorId()!=null) {
			tMap.put("userId", inspectionStationPage.getDirectorId());
			hql+=" and sm.sysUser.userId=:userId";
		}
		if(inspectionStationPage.getStatus()!=null&&!inspectionStationPage.getStatus().trim().equals("")){
			if (inspectionStationPage.getStatus().trim().equals("正常")) {
				tMap.put("status", 0);
				hql+=" and sm.inspectionStation.status=:status";
			}
			if (inspectionStationPage.getStatus().trim().equals("注销")) {
				tMap.put("status", 1);
				hql+=" and sm.inspectionStation.status=:status";
			}
		}else {
			tMap.put("status", 0);
			hql+=" and sm.inspectionStation.status=:status";
		}
		dg.setTotal(inspectionStationDao.count("SELECT COUNT(*) " + hql, tMap));
		hql+=" order by sm.id desc";
		List<StationManagement> lsm = stationManagementDao.find(hql,tMap,inspectionStationPage.getPage(),inspectionStationPage.getRows());
		for (int i = 0; i < lsm.size(); i++) {
			//l.set(i, lsm.get(i).getInspectionStation());
			InspectionStationPage isPage = new InspectionStationPage();
			InspectionStation inspectionStation=lsm.get(i).getInspectionStation();
			BeanUtils.copyProperties(inspectionStation,isPage,new String[] {"status","registrationTime","qulificationTime","validPeriod"});
			copyTimeToStr(inspectionStation,isPage);
			if(lsm.get(i).getSysUser()!=null){
			isPage.setUserName(lsm.get(i).getSysUser().getUserName());
			}
			if(inspectionStation.getStatus()!=null){
				if (inspectionStation.getStatus()==0) {
					isPage.setStatus("正常");
				}else if(inspectionStation.getStatus()==1){
					isPage.setStatus("注销");
				}else {
					isPage.setStatus("未设置状态");
				}
			}
			ls.add(isPage);
		}
		dg.setRows(ls);
		return dg;
	}
	@Override
	public Json addInspectionStation(InspectionStationPage inspectionStationPage) {
		InspectionStation inspectionStation=new InspectionStation();
		StationManagement stationManagement=new StationManagement();
		SysUser sysUser=new SysUser();
		Json json=new Json();
		String flag="";
		if (inspectionStationPage.getStationName().trim().equals("")&&inspectionStationPage.getStationName()!=null) {
			flag=inspectionStationPage.getStationName().trim();
		}
		try {
		BeanUtils.copyProperties(inspectionStationPage, inspectionStation,new String[] {"status","registrationTime","qulificationTime","validPeriod"});
		inspectionStation.setStationName(inspectionStationPage.getStationName().trim());
		copyStrToTime(inspectionStationPage, inspectionStation);
		inspectionStation.setStatus(0);
		inspectionStation.setIsPush(0);
		inspectionStationDao.save(inspectionStation);
		stationManagement.setInspectionStation(inspectionStation);
		stationManagement.setStatus(0);
		if(inspectionStationPage.getDirectorId()!=null){
			sysUser.setUserId(inspectionStationPage.getDirectorId());
			stationManagement.setSysUser(sysUser);
		}
		stationManagementDao.save(stationManagement);
		//inspectionStationDao.save(inspectionStation);
		json.setMsg("添加检测站成功!");
		json.setSuccess(true);	
		} catch (Exception e) {
		    json.setMsg("添加检测站名为"+flag+"的检测站失败！");
		}
		if(json.isSuccess()){
			try {
				String hql="FROM SysUser as sa where sa.userId =:userId ";
				Map<String, Object> tMap = new HashMap<String, Object>();
				tMap.put("userId", inspectionStationPage.getDirectorId());
				List<SysUser> sysUserLs = sysUserDao.find(hql, tMap);
				for(SysUser su : sysUserLs)
					sysUser = su;
				//huanBaoBuService.addInspectionStation(inspectionStation,sysUser);
				//json.setMsg(json.getMsg()+"，数据上传成功");
			} catch (Exception e) {
				//json.setMsg(json.getMsg()+"数据上传出现异常，此条数据将会在网络连通时上传.");
			}
		}
		return json;
	}

	@Override
	public Json updateInspectionStation(InspectionStationPage inspectionStationPage) {
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		try {
			tMap.put("stationId", inspectionStationPage.getStationId());
			String hql="FROM InspectionStation as r where r.stationId =:stationId ";
			InspectionStation inspectionStaion = inspectionStationDao.get(hql, tMap);
			BeanUtils.copyProperties(inspectionStationPage, inspectionStaion,new String[] {"status","registrationTime","qulificationTime","validPeriod"});
            copyStrToTime(inspectionStationPage, inspectionStaion);
            if(inspectionStationPage.getStatus()!=null&&!inspectionStationPage.getStatus().trim().equals("")){
            	if (inspectionStationPage.getStatus().trim().equals("正常")) {
            		inspectionStaion.setStatus(0);
				}else {
					inspectionStaion.setStatus(1);
				}
            }
			inspectionStationDao.update(inspectionStaion);
			if(inspectionStationPage.getDirectorId()!=null){
				//tMap.put("userId", inspectionStationPage.getStationId());
				SysUser sUser=new SysUser();
				sUser.setUserId(inspectionStationPage.getDirectorId());				
				hql="FROM StationManagement as sm where sm.inspectionStation.stationId =:stationId ";
				StationManagement stationManagement=stationManagementDao.get(hql, tMap);
                stationManagement.setSysUser(sUser);
                stationManagementDao.update(stationManagement);
			}
			json.setMsg("更新核发点基本信息成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		    json.setMsg("更新核发点基本信息失败！");
		}
		return json;
	}



	@Override
	public List<InspectionStationPage> getInspectionStationName(String q) {
		if (q == null)
			   q = "";
		List<InspectionStation> recordList = new ArrayList<InspectionStation>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("text", "%" + q.trim() + "%");
		String hql = "from InspectionStation as r where r.stationName like :text";
		recordList = inspectionStationDao.find(hql, tMap);
		List<InspectionStationPage> pageList = new ArrayList<InspectionStationPage>();
		for(InspectionStation rl : recordList) {
			InspectionStationPage up = new InspectionStationPage();
			if(rl.getStatus()==0){
			BeanUtils.copyProperties(rl, up,new String[] {"status","registrationTime","qulificationTime","validPeriod"});
			copyTimeToStr(rl, up);	
			pageList.add(up);
			}
		}
		return pageList;
	}
	

	@Override
	public Json deleteInspectionStation(InspectionStationPage inspectionStationPage) {
	    Json json=new Json();
	    String flag="";
	    //hql="";
		if(inspectionStationPage.getIds()!=null)
		{
			try {
				if(!inspectionStationPage.getIds().trim().equals(""))
				{
					for(String id:inspectionStationPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("stationId", Integer.parseInt(id));
					        String hql="FROM StationManagement s where s.inspectionStation.stationId=:stationId";
							StationManagement sManagement=stationManagementDao.get(hql, tMap);
							stationManagementDao.delete(sManagement);
							hql="FROM InspectionStation d where d.stationId=:stationId ";
							InspectionStation dpr=inspectionStationDao.get(hql, tMap);
							inspectionStationDao.delete(dpr);
						}
					}
					json.setSuccess(true);
					json.setMsg("删除标志核发点成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("删除stationId为"+flag+"的标志核发点失败");
				return json;
			}
		}
		return json;
	}
	@Override
	public Json hasInspectionStation(String q) {
		Json json=new Json();
		String  hql="from InspectionStation as istation where istation.stationName=:stationName";
		Map<String, Object> tMap=new HashMap<String, Object>();
		String nameString =q.trim();
		tMap.put("stationName", nameString);
		
		List<InspectionStation> list=inspectionStationDao.find(hql, tMap);
		if(list!=null && list.size()>0)
		{
			json.setSuccess(true);
			json.setMsg("核发点名称已存在");
		}
		else {
			json.setSuccess(false);
			json.setMsg("名称可用");
		}
	    return json;
	}
	@Override
	public Json cancelInspectionStation(InspectionStationPage inspectionStationPage) {
	    Json json=new Json();
	    String flag="";
		if(inspectionStationPage.getIds()!=null)
		{
			try {
				if(!inspectionStationPage.getIds().trim().equals(""))
				{
					for(String id:inspectionStationPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("stationId", Integer.parseInt(id));
							String hql="FROM InspectionStation d where d.stationId=:stationId ";
							InspectionStation dpr=inspectionStationDao.get(hql, tMap);
							dpr.setStatus(1);
							inspectionStationDao.update(dpr);
						}
					}
					json.setSuccess(true);
					json.setMsg("注销核发点成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("注销stationId为"+flag+"的核发点失败");
				return json;
			}
		}
		return json;
	}


}
