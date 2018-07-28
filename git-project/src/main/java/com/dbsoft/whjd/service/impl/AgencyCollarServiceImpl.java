package com.dbsoft.whjd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.AgencyEnvironmentalLabelCollar;
import com.dbsoft.whjd.model.EnvironmentalLabel;
import com.dbsoft.whjd.model.EnvironmentalLabelCollar;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.AgencyEnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.EnvironmentalLabelCollarPage;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.service.IAgencyCollarService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.dbsoft.whjd.util.TrimString;
@Service("agencyCollarService")
public class AgencyCollarServiceImpl implements IAgencyCollarService{
	private IBaseDao<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelCollarDao;
	private IBaseDao<Object> objectDao;
	

	public IBaseDao<Object> getObjectDao() {
		return objectDao;
	}
	@Resource(name="baseDao")
	public void setObjectDao(IBaseDao<Object> objectDao) {
		this.objectDao = objectDao;
	}
	public IBaseDao<AgencyEnvironmentalLabelCollar> getAgencyEnvironmentalLabelCollarDao() {
		return agencyEnvironmentalLabelCollarDao;
	}
    @Resource(name="baseDao")
	public void setAgencyEnvironmentalLabelCollarDao(
			IBaseDao<AgencyEnvironmentalLabelCollar> agencyEnvironmentalLabelCollarDao) {
		this.agencyEnvironmentalLabelCollarDao = agencyEnvironmentalLabelCollarDao;
	}
    
	@Override
	public Json addAgencyCollarRecord(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage) {
		Json json=new Json();
		json.setSuccess(false);
		if(agencyEnvironmentalLabelCollarPage!=null)
		{
			try {
				agencyEnvironmentalLabelCollarPage=(AgencyEnvironmentalLabelCollarPage)TrimString.getInstance().trimObjectString(agencyEnvironmentalLabelCollarPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			AgencyEnvironmentalLabelCollar agencyEnvironmentalLabelCollar=new AgencyEnvironmentalLabelCollar();
			BeanUtils.copyProperties(agencyEnvironmentalLabelCollarPage, agencyEnvironmentalLabelCollar, new String[]{"collarTime"});
			if(agencyEnvironmentalLabelCollarPage.getCollarTime()!=null && !agencyEnvironmentalLabelCollarPage.getCollarTime().equals(""))
			{
				agencyEnvironmentalLabelCollar.setCollarTime(Timestamp.valueOf(agencyEnvironmentalLabelCollarPage.getCollarTime()+" 00:00:00"));
			}
			if(agencyEnvironmentalLabelCollarPage.getUserId()!=null)
			{
				SysUser user=new SysUser();
				user.setUserId(agencyEnvironmentalLabelCollarPage.getUserId());
				agencyEnvironmentalLabelCollar.setSysUser(user);
			}
			agencyEnvironmentalLabelCollarDao.save(agencyEnvironmentalLabelCollar);
			json.setSuccess(true);
			json.setMsg("添加市局领用记录成功");
		}
		return json;
	}
	@Override
	public DataGrid getAllAgencyCollarRecord(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage) {
		DataGrid dg=new DataGrid();
		try {
			agencyEnvironmentalLabelCollarPage=(AgencyEnvironmentalLabelCollarPage)TrimString.getInstance().trimObjectString(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String hql = " from AgencyEnvironmentalLabelCollar as aelc where 1=1";
		List<AgencyEnvironmentalLabelCollarPage> ls = new ArrayList<AgencyEnvironmentalLabelCollarPage>();
		Map<String, Object> tMap=new HashMap<String, Object>();
		if(agencyEnvironmentalLabelCollarPage.getUserName()!=null && !agencyEnvironmentalLabelCollarPage.getUserName().equals(""))
		{
		   	hql+=" and aelc.sysUser.userName like :userName ";
		   	tMap.put("userName", "%"+agencyEnvironmentalLabelCollarPage.getUserName()+"%");
		}
		if(agencyEnvironmentalLabelCollarPage.getYear()!=null)
		{
			hql+=" and aelc.year=:year ";
			tMap.put("year", agencyEnvironmentalLabelCollarPage.getYear());
		}
		if(agencyEnvironmentalLabelCollarPage.getStatus()!=null &&agencyEnvironmentalLabelCollarPage.getStatus()!=2)
		{
			hql+=" and aelc.status=:status ";
			tMap.put("status", agencyEnvironmentalLabelCollarPage.getStatus());
		}
		if(agencyEnvironmentalLabelCollarPage.getBeginTime()!=null && !agencyEnvironmentalLabelCollarPage.getBeginTime().equals(""))
		{
			hql+=" and aelc.collarTime>=:beginTime ";
			tMap.put("beginTime", Timestamp.valueOf(agencyEnvironmentalLabelCollarPage.getBeginTime()+" 00:00:00"));
		}
		if(agencyEnvironmentalLabelCollarPage.getEndTime()!=null && !agencyEnvironmentalLabelCollarPage.getEndTime().equals(""))
		{
			hql+=" and aelc.collarTime<=:endTime ";
			tMap.put("endTime", Timestamp.valueOf(agencyEnvironmentalLabelCollarPage.getEndTime()+" 00:00:00"));
		}
		
		dg.setTotal(agencyEnvironmentalLabelCollarDao.count("SELECT COUNT(*) " + hql,tMap));
		hql+=" order by aelc.id desc";
		List<AgencyEnvironmentalLabelCollar> lsm = agencyEnvironmentalLabelCollarDao.find(hql,tMap,agencyEnvironmentalLabelCollarPage.getPage(),agencyEnvironmentalLabelCollarPage.getRows());
		for (int i = 0; i < lsm.size(); i++) {
			AgencyEnvironmentalLabelCollarPage elcp = new AgencyEnvironmentalLabelCollarPage();
			AgencyEnvironmentalLabelCollar elc=lsm.get(i);
			BeanUtils.copyProperties(elc, elcp,new String[] {"collarTime"});
			if (elc.getCollarTime()!=null) {
				elcp.setCollarTime(ChangeTimeFormat.getInstance().timeStampToString(elc.getCollarTime()));
			}
			if(elc.getSysUser()!=null){
			     elcp.setUserName(elc.getSysUser().getUserName());
			     elcp.setUserId(elc.getSysUser().getUserId());
			}
			ls.add(elcp);
		}
		dg.setRows(ls);
		return dg;
	}
	@Override
	public Json cancelAgencyCollarRecord(
		   AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage) {
		   Json json=new Json();
		   json.setSuccess(false);
		   json.setMsg("没有数据传入");
		    String flag="";
			if(agencyEnvironmentalLabelCollarPage.getIds()!=null)
			{
				try {
					if(!agencyEnvironmentalLabelCollarPage.getIds().trim().equals(""))
					{
						for(String id:agencyEnvironmentalLabelCollarPage.getIds().split(","))
						{
							if(id!=null && !id.trim().equals(""))
							{
								flag=id;
								Map<String, Object> tMap=new HashMap<String, Object>();
								tMap.put("id", Integer.parseInt(id));
								
						        String hql="FROM AgencyEnvironmentalLabelCollar as aelc where aelc.id=:id " ;
						        AgencyEnvironmentalLabelCollar ls=agencyEnvironmentalLabelCollarDao.get(hql, tMap);
	                            ls.setStatus(1);
	                            agencyEnvironmentalLabelCollarDao.update(ls);
							}
						}
						json.setSuccess(true);
						json.setMsg("注销环保标志领用记录成功");
					}
				} catch (Exception e) {
					e.printStackTrace();
					json.setSuccess(false);
					json.setMsg("注销id为"+flag+"的环保标志领用记录失败");
					return json;
				}
			}
			return json;
	}
	@Override
	public Json deleteAgencyCollarRecord(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage) {
	
			   Json json=new Json();
			   json.setSuccess(false);
			   json.setMsg("没有数据传入");
			    String flag="";
				if(agencyEnvironmentalLabelCollarPage.getIds()!=null)
				{
					try {
						if(!agencyEnvironmentalLabelCollarPage.getIds().trim().equals(""))
						{
							for(String id:agencyEnvironmentalLabelCollarPage.getIds().split(","))
							{
								if(id!=null && !id.trim().equals(""))
								{
									flag=id;
									Map<String, Object> tMap=new HashMap<String, Object>();
									tMap.put("id", Integer.parseInt(id));
									
							        String hql="FROM AgencyEnvironmentalLabelCollar as aelc where aelc.id=:id " ;
							        AgencyEnvironmentalLabelCollar ls=agencyEnvironmentalLabelCollarDao.get(hql, tMap);
		                            agencyEnvironmentalLabelCollarDao.delete(ls);
								}
							}
							json.setSuccess(true);
							json.setMsg("删除环保标志领用记录成功");
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
	public Json modifyAgencyCollarRecord(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage) {
		Json json=new Json();
		Map<String, Object> tMap = new HashMap<String, Object>();
		try {
			tMap.put("id", agencyEnvironmentalLabelCollarPage.getId());
			String hql="FROM AgencyEnvironmentalLabelCollar as r where r.id=:id ";
			AgencyEnvironmentalLabelCollar agencyEnvironmentalLabelCollar = agencyEnvironmentalLabelCollarDao.get(hql, tMap);
			
			BeanUtils.copyProperties(agencyEnvironmentalLabelCollarPage, agencyEnvironmentalLabelCollar,new String[] {"collarTime"});
	        if(agencyEnvironmentalLabelCollarPage.getCollarTime()!=null && agencyEnvironmentalLabelCollarPage.getCollarTime().equals(""))
	        {
	        	agencyEnvironmentalLabelCollar.setCollarTime(Timestamp.valueOf(agencyEnvironmentalLabelCollarPage.getCollarTime()+" 00:00:00"));
	        }
	        if(agencyEnvironmentalLabelCollarPage.getUserId()!=null)
	        {
	        	System.out.println(agencyEnvironmentalLabelCollarPage.getUserId());
	        	SysUser user=new SysUser();
	        	user.setUserId(agencyEnvironmentalLabelCollarPage.getUserId());
	        	agencyEnvironmentalLabelCollar.setSysUser(user);
	        }
	        else {
	        	SysUser user=new SysUser();
	        	user.setUserId(agencyEnvironmentalLabelCollarPage.getUserId1());
	        	agencyEnvironmentalLabelCollar.setSysUser(user);
			}
	        agencyEnvironmentalLabelCollarDao.update(agencyEnvironmentalLabelCollar);
			json.setMsg("修改市局标志领用记录成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		    json.setMsg("更新环保标志发放记录失败！");
		    json.setSuccess(false);
		}
		return json;
	}
	@Override
	public DataGrid getSumOfLabel(
			AgencyEnvironmentalLabelCollarPage agencyEnvironmentalLabelCollarPage) {
		DataGrid dg=new DataGrid();
		try {
			agencyEnvironmentalLabelCollarPage=(AgencyEnvironmentalLabelCollarPage)TrimString.getInstance().trimObjectString(agencyEnvironmentalLabelCollarPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String hql = "select year,sum(yellowLabelNum) as yellowLabelNum,sum(greenLabelNum) as greenLabelNum from AgencyEnvironmentalLabelCollar  where status=0 group by year order by year desc ";
		List<AgencyEnvironmentalLabelCollarPage> ls = new ArrayList<AgencyEnvironmentalLabelCollarPage>();
		List<Object> lsm =objectDao.find(hql,null,agencyEnvironmentalLabelCollarPage.getPage(),agencyEnvironmentalLabelCollarPage.getRows());
		for (int i = 0; i < lsm.size(); i++) {
			
			AgencyEnvironmentalLabelCollarPage elcp = new AgencyEnvironmentalLabelCollarPage();
			Object[] elc=(Object[])lsm.get(i);
			elcp.setYear(Integer.parseInt(elc[0].toString()));
			elcp.setYellowLabelNum(Integer.parseInt(elc[1].toString()));
			elcp.setGreenLabelNum(Integer.parseInt(elc[2].toString()));
		     
			System.out.println(elcp.getYear()+" "+elcp.getYellowLabelNum()+" "+elcp.getGreenLabelNum());
			BeanUtils.copyProperties(elc, elcp,new String[] {"collarTime"});
			ls.add(elcp);
		}
		if(lsm!=null && lsm.size()>0)
		{
			dg.setTotal((long)lsm.size());
		}
		else {
			dg.setTotal(0L);
		}
		dg.setRows(ls);
		return dg;
	}
	
	

}
