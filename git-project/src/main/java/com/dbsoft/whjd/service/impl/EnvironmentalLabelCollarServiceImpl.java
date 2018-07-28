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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.AgencyEnvironmentalLabelCollar;
import com.dbsoft.whjd.model.DevicePurchaseRecord;
import com.dbsoft.whjd.model.EnvironmentalLabelCollar;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.StationManagement;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.AgencyEnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.DevicePurchaseRecordPage;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.InspectionStationPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IAgencyCollarService;
import com.dbsoft.whjd.service.IEnvironmentalLabelCollarService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.ExportUtil;
import com.dbsoft.whjd.util.TrimString;
@Service("environmentalLabelCollarService")
public class EnvironmentalLabelCollarServiceImpl implements IEnvironmentalLabelCollarService{
	private IBaseDao<InspectionStation> inspectionStationDao;
	private IBaseDao<EnvironmentalLabelCollar> environmentalLabelCollarDao;
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelcollarDao;
	private IBaseDao<Object> objectDao;
	private IAgencyCollarService  agencyCollarService;

	public IAgencyCollarService getAgencyCollarService() {
		return agencyCollarService;
	}
	@Autowired
	public void setAgencyCollarService(IAgencyCollarService agencyCollarService) {
		this.agencyCollarService = agencyCollarService;
	}
	public IBaseDao<Object> getObjectDao() {
		return objectDao;
	}
	@Resource(name = "baseDao")
	public void setObjectDao(IBaseDao<Object> objectDao) {
		this.objectDao = objectDao;
	}
	public IBaseDao<InspectionStation> getInspectionStationDao() {
		return inspectionStationDao;
	}
	public IBaseDao<AgencyEnvironmentalLabelCollar> getAgencyEnvironmentalLabelcollarDao() {
		return agencyEnvironmentalLabelcollarDao;
	}
	@Resource(name = "baseDao")
	public void setAgencyEnvironmentalLabelcollarDao(IBaseDao<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelcollarDao) {
		this.agencyEnvironmentalLabelcollarDao = agencyEnvironmentalLabelcollarDao;
	}
	@Resource(name = "baseDao")
	public void setInspectionStationDao(IBaseDao<InspectionStation> inspectionStationDao) {
		this.inspectionStationDao = inspectionStationDao;
	}

	public IBaseDao<EnvironmentalLabelCollar> getEnvironmentalLabelCollarDao() {
		return environmentalLabelCollarDao;
	}
	@Resource(name = "baseDao")
	public void setEnvironmentalLabelCollarDao(IBaseDao<EnvironmentalLabelCollar> environmentalLabelCollarDao) {
		this.environmentalLabelCollarDao = environmentalLabelCollarDao;
	}

	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Resource(name = "baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	
	private void copyStrToTime (EnvironmentalLabelCollarPage environmentalLabelCollarPage,EnvironmentalLabelCollar environmentalLabelCollar) {
		environmentalLabelCollar.setCollarTime(ChangeTimeFormat.getInstance().strToTimeStamp(environmentalLabelCollarPage.getCollarTime()));
	}
	private void copyTimeToStr(EnvironmentalLabelCollar environmentalLabelCollar,EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		environmentalLabelCollarPage.setCollarTime(ChangeTimeFormat.getInstance().timeStampToString(environmentalLabelCollar.getCollarTime()));
	}
	
	
	@Override
	public DataGrid getEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		DataGrid dg = new DataGrid();
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession hSession=req.getSession();
		String userStationName=(String) hSession.getAttribute("stationName");
		System.out.println(userStationName);
		Map<String, Object> tMap = new HashMap<String, Object>();		
		String hql = "FROM EnvironmentalLabelCollar as elc where 1=1";
		List<EnvironmentalLabelCollarPage> ls = new ArrayList<EnvironmentalLabelCollarPage>();
		if (!userStationName.trim().equals("市局")) {
			tMap.put("stationName", userStationName.trim());
			hql+=" and elc.inspectionStation.stationName=:stationName";
		}
		if (environmentalLabelCollarPage.getStationId()!=null&&userStationName.trim().equals("市局")) {
			tMap.put("stationId", environmentalLabelCollarPage.getStationId());
			hql+=" and elc.inspectionStation.stationId=:stationId";
		}
		if (environmentalLabelCollarPage.getUserId()!=null) {
			tMap.put("userId", environmentalLabelCollarPage.getUserId());
			hql+=" and elc.sysUser.userId=:userId";
		}
		if (environmentalLabelCollarPage.getYear()!=null) {
			tMap.put("year", environmentalLabelCollarPage.getYear());
			hql+=" and elc.year=:year";
		}
		if (environmentalLabelCollarPage.getStatus()!=null&&!environmentalLabelCollarPage.getStatus().trim().equals("")) {
			if(environmentalLabelCollarPage.getStatus().trim().equals("正常")){
				tMap.put("status", 0);
				hql+=" and elc.status=:status";
			}
			if (environmentalLabelCollarPage.getStatus().trim().equals("注销")) {
				tMap.put("status", 1);
				hql+=" and elc.status=:status";
			}
		}else {
			tMap.put("status", 0);
			hql+=" and elc.status=:status";
		}
		
		//hql+=" group by elc.collarTime order by elc.collarTime desc";
		dg.setTotal(environmentalLabelCollarDao.count("SELECT COUNT(*) " + hql, tMap));
		hql+=" order by elc.id desc";
		List<EnvironmentalLabelCollar> lsm = environmentalLabelCollarDao.find(hql,tMap,environmentalLabelCollarPage.getPage(),environmentalLabelCollarPage.getRows());
		for (int i = 0; i < lsm.size(); i++) {
			EnvironmentalLabelCollarPage elcp = new EnvironmentalLabelCollarPage();
			EnvironmentalLabelCollar elc=lsm.get(i);
			BeanUtils.copyProperties(elc, elcp,new String[] {"collarTime","status"});
			if (elc.getCollarTime()!=null) {
				copyTimeToStr(elc,elcp);
			}
			if(elc.getSysUser()!=null){
			elcp.setUserName(elc.getSysUser().getUserName());
			}
			if (elc.getInspectionStation()!=null) {
				elcp.setStationName(elc.getInspectionStation().getStationName());
			}
			if (elc.getStatus()!=null) {
				if (elc.getStatus()==0) {
					elcp.setStatus("正常");
				}
				else {
					elcp.setStatus("注销");
				}
			}
			ls.add(elcp);
		}
		dg.setRows(ls);
		return dg;
	}
	@Override
	/*
	 * 得到剩余标志数
	 */
	public DataGrid getLeaveLabel(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		DataGrid dg=new DataGrid();
		DataGrid agencyDg=new DataGrid();
		AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage=new AgencyEnvironmentalLabelCollarPage();
		agencyEnvironmentalLabelCollarPage.setPage(environmentalLabelCollarPage.getPage());
		agencyEnvironmentalLabelCollarPage.setRows(environmentalLabelCollarPage.getRows());
		String hql = "select year,sum(yellowLabelNum) as yellowLabelNum,sum(greenLabelNum) as greenLabelNum from EnvironmentalLabelCollar  where status=0 group by year order by year desc ";
		List<EnvironmentalLabelCollarPage> ls = new ArrayList<EnvironmentalLabelCollarPage>();
		List<Object> lsm =objectDao.find(hql,null,environmentalLabelCollarPage.getPage(),environmentalLabelCollarPage.getRows());
		agencyDg=agencyCollarService.getSumOfLabel(agencyEnvironmentalLabelCollarPage);
		for (int i = 0; i < agencyDg.getTotal(); i++) {
			AgencyEnvironmentalLabelCollarPage aelc=(AgencyEnvironmentalLabelCollarPage) agencyDg.getRows().get(i);
			EnvironmentalLabelCollarPage elcp = new EnvironmentalLabelCollarPage();
			elcp.setYear(aelc.getYear());
			elcp.setYellowLabelNum(aelc.getYellowLabelNum());
			elcp.setGreenLabelNum(aelc.getGreenLabelNum());
			ls.add(elcp); 
		}
		for (int i = 0; i < ls.size(); i++) {			
			for (int j = 0; j < lsm.size(); j++) {
				Object[] elc=(Object[])lsm.get(j);
				if (ls.get(i).getYear()==Integer.parseInt(elc[0].toString())) {
					ls.get(i).setYellowLabelNum(ls.get(i).getYellowLabelNum()-Integer.parseInt(elc[1].toString()));
					ls.get(i).setGreenLabelNum(ls.get(i).getGreenLabelNum()-Integer.parseInt(elc[2].toString()));
				}
			}
		}				
		if(agencyDg!=null && agencyDg.getTotal()>0)
		{
			dg.setTotal(agencyDg.getTotal());
		}
		else {
			dg.setTotal(0L);
		}
		dg.setRows(ls);
		return dg;
	}
	@Override
	public Json addEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		InspectionStation inspectionStation=new InspectionStation();
		EnvironmentalLabelCollar environmentalLabelCollar=new EnvironmentalLabelCollar();
		SysUser sysUser=new SysUser();
		Json json=new Json();			
        DataGrid dg=this.computeLeaveNum(environmentalLabelCollarPage); 
        EnvironmentalLabelCollarPage ePage=new EnvironmentalLabelCollarPage();
		for (int i = 0; i < dg.getTotal(); i++) {
			EnvironmentalLabelCollarPage eLeavePage=(EnvironmentalLabelCollarPage) dg.getRows().get(i);
			System.out.println(eLeavePage.getYear());
			System.out.println(environmentalLabelCollarPage.getYear());
			int aInteger=eLeavePage.getYear();
			int bInteger=environmentalLabelCollarPage.getYear();
			//if(eLeavePage.getYear() == environmentalLabelCollarPage.getYear()){
			if(aInteger==bInteger){
				System.out.println(environmentalLabelCollarPage.getYear());
				ePage=eLeavePage;
				break;
			}
		}
		if (ePage!=null&&ePage.getGreenLabelNum()!=null&&ePage.getYellowLabelNum()!=null) {
			if (environmentalLabelCollarPage.getGreenLabelNum()>ePage.getGreenLabelNum()) {
				json.setMsg("领用的绿标数大于库存数量!");
				json.setSuccess(false);
				return json;
			}
			if (environmentalLabelCollarPage.getYellowLabelNum()>ePage.getYellowLabelNum()) {
				json.setMsg("领用的黄标数大于库存数量!");
				json.setSuccess(false);
				return json;
			}
		}

		BeanUtils.copyProperties(environmentalLabelCollarPage, environmentalLabelCollar,new String[] {"collarTime","status"});
		
	if (environmentalLabelCollarPage.getCollarTime()!=null&&!environmentalLabelCollarPage.getCollarTime().trim().equals("")) {
			copyStrToTime(environmentalLabelCollarPage,environmentalLabelCollar);
		}
	System.out.print(environmentalLabelCollarPage.getStationId());
	if (environmentalLabelCollarPage.getStationId()!=null) {
		inspectionStation.setStationId(environmentalLabelCollarPage.getStationId());
		environmentalLabelCollar.setInspectionStation(inspectionStation);
	}
	if (environmentalLabelCollarPage.getUserId()!=null) {
		sysUser.setUserId(environmentalLabelCollarPage.getUserId());
		environmentalLabelCollar.setSysUser(sysUser);
	}
		environmentalLabelCollar.setStatus(0);
		try {
			environmentalLabelCollarDao.save(environmentalLabelCollar);			
			json.setMsg("添加环保标志领用记录成功!");
			json.setSuccess(true);
		} catch (Exception e) {
		    json.setMsg("添加环保标志领用记录失败！");
		}
		return json;
	}

	public DataGrid computeLeaveNum(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		DataGrid dg=new DataGrid();
		DataGrid agencyDg=new DataGrid();
		AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage=new AgencyEnvironmentalLabelCollarPage();
		agencyEnvironmentalLabelCollarPage.setPage(environmentalLabelCollarPage.getPage());
		agencyEnvironmentalLabelCollarPage.setRows(environmentalLabelCollarPage.getRows());
		String hql = "select year,sum(yellowLabelNum) as yellowLabelNum,sum(greenLabelNum) as greenLabelNum from EnvironmentalLabelCollar  where status=0 group by year order by year desc ";
		List<EnvironmentalLabelCollarPage> ls = new ArrayList<EnvironmentalLabelCollarPage>();
		List<Object> lsm =objectDao.find(hql,null);
		agencyDg=agencyCollarService.getSumOfLabel(agencyEnvironmentalLabelCollarPage);
		for (int i = 0; i < agencyDg.getTotal(); i++) {
			AgencyEnvironmentalLabelCollarPage aelc=(AgencyEnvironmentalLabelCollarPage) agencyDg.getRows().get(i);
			EnvironmentalLabelCollarPage elcp = new EnvironmentalLabelCollarPage();
			elcp.setYear(aelc.getYear());
			elcp.setYellowLabelNum(aelc.getYellowLabelNum());
			elcp.setGreenLabelNum(aelc.getGreenLabelNum());
			ls.add(elcp); 
		}
		for (int i = 0; i < ls.size(); i++) {			
			for (int j = 0; j < lsm.size(); j++) {
				Object[] elc=(Object[])lsm.get(j);
				if (ls.get(i).getYear()==Integer.parseInt(elc[0].toString())) {
					ls.get(i).setYellowLabelNum(ls.get(i).getYellowLabelNum()-Integer.parseInt(elc[1].toString()));
					ls.get(i).setGreenLabelNum(ls.get(i).getGreenLabelNum()-Integer.parseInt(elc[2].toString()));
				}
			}
		}	
		if(agencyDg!=null && agencyDg.getTotal()>0)
		{
			dg.setTotal(agencyDg.getTotal());
		}
		else {
			dg.setTotal(0L);
		}
		dg.setRows(ls);
		return dg;
	}
	@Override
	public Json updateEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		//System.out.println(environmentalLabelCollarPage.getCollarTime()+environmentalLabelCollarPage.getId());
		try {
			tMap.put("id", environmentalLabelCollarPage.getId());
			String hql="FROM EnvironmentalLabelCollar as r where r.id=:id ";
			EnvironmentalLabelCollar environmentalLabelCollar = environmentalLabelCollarDao.get(hql, tMap);
			BeanUtils.copyProperties(environmentalLabelCollarPage, environmentalLabelCollar,new String[] {"collarTime","status"});
			if (environmentalLabelCollarPage.getCollarTime()!=null&&!environmentalLabelCollarPage.getCollarTime().trim().equals("")) {
				copyStrToTime(environmentalLabelCollarPage,environmentalLabelCollar);
			}
			if (environmentalLabelCollarPage.getStationId()!=null) {
				InspectionStation inspectionStation=new InspectionStation();
				inspectionStation.setStationId(environmentalLabelCollarPage.getStationId());
				environmentalLabelCollar.setInspectionStation(inspectionStation);
			}
			if(environmentalLabelCollarPage.getUserId()!=null){
				SysUser sUser=new SysUser();
				sUser.setUserId(environmentalLabelCollarPage.getUserId());				
		        environmentalLabelCollar.setSysUser(sUser);
			}
			if (environmentalLabelCollarPage.getStatus()!=null&&!environmentalLabelCollarPage.getStatus().trim().equals("")) {
				if (environmentalLabelCollarPage.getStatus().trim().equals("正常")) {
					environmentalLabelCollar.setStatus(0);
				}else {
					environmentalLabelCollar.setStatus(1);
				}
				
			}
			environmentalLabelCollarDao.update(environmentalLabelCollar);
			json.setMsg("更新环保标志领用记录成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		    json.setMsg("更新环保标志领用记录失败！");
		}
		return json;
	}

	@Override
	public Json cancelEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
	    Json json=new Json();
	    String flag="";
		if(environmentalLabelCollarPage.getIds()!=null)
		{
			try {
				if(!environmentalLabelCollarPage.getIds().trim().equals(""))
				{
					for(String id:environmentalLabelCollarPage.getIds().split(","))
					{
						if(id!=null && !id.trim().equals(""))
						{
							flag=id;
							Map<String, Object> tMap=new HashMap<String, Object>();
							tMap.put("id", Integer.parseInt(id));
							//tMap.put("status",1);
					        String hql="FROM EnvironmentalLabelCollar as elc where elc.id=:id " ;
							EnvironmentalLabelCollar ls=environmentalLabelCollarDao.get(hql, tMap);
                            ls.setStatus(1);
							environmentalLabelCollarDao.update(ls);
						}
					}
					json.setSuccess(true);
					json.setMsg("注销环保标志领用记录成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("删除id为"+flag+"的环保标志领用记录失败");
				return json;
			}
		}
		return json;
	}
	@Override
	public Json exportEnvironmentalLabelCollar(EnvironmentalLabelCollarPage environmentalLabelCollarPage) {
		List<Object> pageList = new ArrayList<Object>();
		if (environmentalLabelCollarPage.getIds() != null) {
			for (String id : environmentalLabelCollarPage.getIds().split(",")) {
				if (id != null && !id.trim().equals("")) {
					EnvironmentalLabelCollarPage elcp=new EnvironmentalLabelCollarPage();
					EnvironmentalLabelCollar elc=environmentalLabelCollarDao.get(EnvironmentalLabelCollar.class,Integer.valueOf(id));
					BeanUtils.copyProperties(elc, elcp,new String[] {"collarTime","status"});
					if (elc.getCollarTime()!=null) {
						copyTimeToStr(elc,elcp);
					}
					if(elc.getSysUser()!=null){
					elcp.setUserName(elc.getSysUser().getUserName());
					}
					if (elc.getInspectionStation()!=null) {
						elcp.setStationName(elc.getInspectionStation().getStationName());
					}
					if (elc.getStatus()!=null) {
						if (elc.getStatus()==0) {
							elcp.setStatus("正常");
						}
						else {
							elcp.setStatus("注销");
						}
					}
					pageList.add(elcp);
				}
			}
		}
		String filePath="";
		try {
			filePath = ExportUtil.ExportToExcelByResultSet(pageList, null,
					"formatExcel", "formatTitle", EnvironmentalLabelCollarServiceImpl.class);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(filePath);
		Json retJson = new Json();
		retJson.setSuccess(filePath.equals("") ? false : true);
		retJson.setObj(filePath);
		retJson.setMsg("导出环保标志领用记录成功!");
		return retJson;
	}
	public List<String> formatExcel(Object obj) {
		List<String> result = new ArrayList<String>();
		result.add(((EnvironmentalLabelCollarPage) obj).getId().toString());
		result.add(((EnvironmentalLabelCollarPage) obj).getStationName());
		result.add(((EnvironmentalLabelCollarPage) obj).getUserName());
		result.add(((EnvironmentalLabelCollarPage) obj).getCollarTime());
		result.add(((EnvironmentalLabelCollarPage) obj).getYear().toString());
		result.add(((EnvironmentalLabelCollarPage) obj).getStatus());
		result.add(((EnvironmentalLabelCollarPage) obj).getGreenLabelNum().toString());
		result.add(((EnvironmentalLabelCollarPage) obj).getYellowLabelNum().toString());
		result.add(((EnvironmentalLabelCollarPage) obj).getRemarks());
		//result.add(((DevicePurchaseRecordPage) obj).getStatus());
		return result;
	}

	public List<String> formatTitle() {
		List<String> result = new ArrayList<String>();
		result.add("编号");
		result.add("核发点名称");
		result.add("领用人");
		result.add("领用时间");
		result.add("标志年度号");
		result.add("状态");
		result.add("绿标数");
		result.add("黄标数");
		result.add("备注");
		return result;
	}
}
