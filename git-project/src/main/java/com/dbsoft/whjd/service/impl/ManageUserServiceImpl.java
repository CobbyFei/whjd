package com.dbsoft.whjd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.InspectionStation;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.SysUserPage;
import com.dbsoft.whjd.service.IHuanBaoBuService;
import com.dbsoft.whjd.service.IManageUserService;
import com.dbsoft.whjd.util.ChangeTimeFormat;
import com.dbsoft.whjd.util.Encrypt;
import com.dbsoft.whjd.util.TrimString;

@Service(value = "manageUserService")
public class ManageUserServiceImpl implements IManageUserService{
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<InspectionStation> inspectionStationDao;
	private IHuanBaoBuService huanBaoBuService;
	public IHuanBaoBuService getHuanBaoBuService() {
		return huanBaoBuService;
	}
	@Autowired
	public void setHuanBaoBuService(IHuanBaoBuService huanBaoBuService) {
		this.huanBaoBuService = huanBaoBuService;
	}
	/**
	 * 拼接hql中的排序信息
	 * @author mark 
	 * @param sysUserPage
	 * @param hql
	 * @return
	 */
	private String addOrder(SysUserPage sysUserPage, String hql){
		if (sysUserPage.getSort()!=null && sysUserPage.getOrder()!=null){
			hql += " ORDER BY " + sysUserPage.getSort() + " "+sysUserPage.getOrder(); 
		}
		return hql;
	}
	
	/**
	 * 拼接hql中的搜索条件
	 * @param sysUserPage
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addSysUserWhere(SysUserPage sysUserPage, String hql, Map<String, Object> params){
		
		String userName = sysUserPage.getUserName();
		Integer stationId = sysUserPage.getStationId();
		String stationName = sysUserPage.getStationName(); 
		Integer userStatus = sysUserPage.getUserStatus();
		
		
		if (userName != null && !userName.trim().equals("")){
			hql += " AND su.userName LIKE :userName ";
			params.put("userName", "%%" + userName + "%%");
		}
		
		if(stationId!=null && stationId>0){
			hql += " AND su.inspectionStation.stationId =:stationId ";
			params.put("stationId", stationId);
		}
		
		if (stationName != null && !stationName.trim().equals("")){
			hql += " AND su.inspectionStation.stationName LIKE :stationName ";
			params.put("stationName", "%%" + stationName + "%%");
		}
		if (userStatus!=null && userStatus>=0 && userStatus<=1){
			hql += " AND su.status =:userStatus ";
			params.put("userStatus", userStatus);
		}
		return hql;
	}
	
	
	
	/**
	 * 添加用户信息
	 * @author mark
	 */
	@Override
	public Json addNewUser(SysUserPage sysUserPage){
		
		SysUser sysUser = new SysUser();
		Json json = new Json();
		//取出session中的用户信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		Integer sessionStationId = (Integer)httpSession.getAttribute("stationId");
		
		//trim一下所有的空字符串
		try {
			sysUserPage = (SysUserPage)TrimString.getInstance().trimObjectString(sysUserPage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			json.setMsg("出现异常，请重新输入！");
			json.setSuccess(false);
			return json;
		}
		
		
		//判断sysUserPage 中的userName不能为空
		String userName = sysUserPage.getUserName();
		if(userName==null || "".equals(userName)){
			json.setMsg("姓名不能为空！");
			json.setSuccess(false);
			return json;
		}
		
		//判断sysUserPage 中的simplifyName不能为空
		String simplifyName = sysUserPage.getSimplifyName();
		if(simplifyName==null || "".equals(simplifyName)){
			json.setMsg("用户名不能为空！");
			json.setSuccess(false);
			return json;
		}
			
		
				
		BeanUtils.copyProperties(sysUserPage, sysUser, new String[]{"simplifyName"});
		sysUser.setSimplifyName(simplifyName.toUpperCase()); //用户名以全大写的形式存入
		//判断检测站Id部分
		Integer stationId;
		if(sessionStationId == null){
			json.setMsg("出现异常！亲重新系登陆！");
			json.setSuccess(false);
			return json;
		}
		
		if(sessionStationId == 0){//局里添加，直接用pagemodel中的stationId
		
			stationId = sysUserPage.getStationId();
		
		}else{//检测站添加,直接用添加者自己的stationId
			
			stationId = sessionStationId;
		}
		
		if(stationId != null && stationId>0){
				String hql = "from InspectionStation as iptn where iptn.stationId=:stationId";
				Map<String, Object> tMap = new HashMap<String, Object>();
				tMap.put("stationId", stationId);
				InspectionStation inspectionStation = inspectionStationDao.get(hql, tMap);
				if(inspectionStation != null){
					sysUser.setInspectionStation(inspectionStation);
				}else{
					json.setMsg("添加人员失败！");
					json.setSuccess(false);
					return json;
				}
			
		}
		
		//判断日期是否合法
		String strCertificateTime = sysUserPage.getStrCertificateTime();
		if(strCertificateTime != null && !"".equals(strCertificateTime)){
			sysUser.setCertificateTime(ChangeTimeFormat.getInstance().strToTimeStamp(strCertificateTime));	
		}
		
		sysUser.setStatus(0);
		String secretPassword = Encrypt.md5(sysUserPage.getSimplifyName().toLowerCase()).substring(0,16);
		sysUser.setPassword(secretPassword);
		sysUser.setIsPush(0);
		
		try{
			sysUserDao.save(sysUser);
			json.setMsg("添加人员成功！");
			json.setSuccess(true);
			
		}catch(Exception e){
			json.setMsg("添加人员失败！");
			json.setSuccess(false);
		}
		//与环保部数据同步
		/*if(json.isSuccess()){
			huanBaoBuService.addUser(sysUser);
		}*/
		return json;
	}
	
	/**
	 * 判断用户名是否已存在
	 * @author mark
	 */
	@Override
	public Json hasUserOfSimplifyName(String simplifyName){
		
		Json json = new Json();
		
		if(simplifyName == null){
			json.setSuccess(true);
			json.setMsg("用户名不能为空！");
			return json;
		}
		
		simplifyName = simplifyName.trim();
		
		Pattern p = Pattern.compile("([a-z]|[A-Z])\\w{1,15}");
		Matcher m = p.matcher(simplifyName);
		
		if(!m.matches()){
			json.setSuccess(true);
			json.setMsg("用户名格式错误！");
			return json;
		}
		
				
		String hql = "from SysUser as s Where s.simplifyName =:simplifyName ";		
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("simplifyName", simplifyName.toUpperCase());
		
		SysUser su = sysUserDao.get(hql,tMap);
		
		if (su !=null){
			json.setSuccess(true);
			json.setMsg("用户名已存在！");
		}else{
			json.setSuccess(false);
			json.setMsg("用户名可用！");
		}
		
		return json;
		
	}
	
	
	/**
	 * 更改用户的状态
	 * @author mark
	 */
	@Override
	public Json changeSysUserStatus(SysUserPage sysUserPage){
		String ids = sysUserPage.getIds();
		Integer changeToStatus = sysUserPage.getChangeToStatus();
		Json json = new Json();
		
		if(ids!=null && ids!="" && changeToStatus!=null && changeToStatus >= 0 && changeToStatus <=1 ){

			String[] strIds= ids.split(",");
			
			for(int i = 0; i < strIds.length; i++){
				SysUser sysUser = sysUserDao.get(SysUser.class, Integer.parseInt(strIds[i].trim()));
				
				if (sysUser == null) {
					json.setMsg("操作失败！");
					json.setSuccess(false);
					return json;
				}
				
				if ("admin".equals(sysUser.getSimplifyName())) {
					json.setMsg("不可注销admin！");
					json.setSuccess(false);
					return json;
				}
				sysUser.setStatus(changeToStatus);
				
				try{		
					sysUserDao.update(sysUser);
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
	/**
	 * 更新人员信息
	 * @author mark
	 */
	public Json updateSysUser(SysUserPage sysUserPage){
		Json json = new Json();
		
		//取出session中的用户信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		Integer sessionStationId = (Integer)httpSession.getAttribute("stationId");
		
		SysUser sysUser =  sysUserDao.get(SysUser.class, sysUserPage.getUserId());
		
		try{
			sysUserPage =(SysUserPage)TrimString.getInstance().trimObjectString(sysUserPage);
			
		}catch(Exception e){
			json.setMsg("更新失败！");
			json.setSuccess(false);
			return json;
		}
		
		//判断sysUserPage 中的userName不能为空
		String userName = sysUserPage.getUserName();
		if(userName==null || "".equals(userName)){
				json.setMsg("更新失败姓名不能为空！");
				json.setSuccess(false);
				return json;
		}
		
		sysUser.setUserName(sysUserPage.getUserName());
		
		sysUser.setSex(sysUserPage.getSex());
		sysUser.setCertificateName(sysUserPage.getCertificateName());
		if(sysUserPage.getStrCertificateTime() != null){
			sysUser.setCertificateTime(Timestamp.valueOf(sysUserPage.getStrCertificateTime()+" 00:00:00"));	
		}
		
		sysUser.setDegree(sysUserPage.getDegree());
		sysUser.setIdcard(sysUserPage.getIdcard());
		sysUser.setJobTitle(sysUserPage.getJobTitle());
		sysUser.setTel(sysUserPage.getTel());
		
		//根据用户的情况对stationId值做判断
		Integer  stationId;
		if(sessionStationId==0){
		 stationId	= sysUserPage.getStationId();
		}else{
			stationId = sessionStationId;
		}
		
		if(stationId != null && stationId > 0){
			
			String hql = "from InspectionStation as iptn where  iptn.stationId=:stationId";
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("stationId", stationId);
			
			InspectionStation inspectionStation = inspectionStationDao.get(hql, tMap);
			if(inspectionStation!=null){
				sysUser.setInspectionStation(inspectionStation);
			}else{
				json.setMsg("更新人员单位失败！");
				json.setSuccess(false);
				return json;
			}
		}
			
		try{		
			sysUserDao.update(sysUser);
			json.setMsg("更新成功！");
			json.setSuccess(true);
			
		}catch(Exception e){
			json.setMsg("更新失败！");
			json.setSuccess(false);
		}
		
		return json;
		
	}
	
	/**
	 * 查找人员信息返回datagrid
	 * @author mark
	 */
	@Override
	public DataGrid findSysUsers(SysUserPage sysUserPage) throws Exception{
		
		
		List<SysUserPage> sysUserPages = new ArrayList<SysUserPage>();
		
		
		String hql = "FROM SysUser as su WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		
		//取出session中的用户信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		Integer sessionStationId = (Integer)httpSession.getAttribute("stationId");
		
		
		//增加where
		if(sysUserPage!=null){
			if(sessionStationId == null){
				throw new Exception(); //如果 sessionStationId 为空 则抛异常！
			}
			if(sessionStationId != 0 ){ //如果不是局里的就就显示他本站，如果是局里的就什么也不做
				sysUserPage.setStationId(sessionStationId);
			}
			hql = this.addSysUserWhere(sysUserPage, hql, params);
			
		}else{
			throw new Exception(); //出现了特殊情况会在Action 中抛异常
		}
		
		DataGrid dg = new DataGrid();
		
		dg.setTotal(sysUserDao.count("SELECT COUNT(*) " + hql, params));
		hql = this.addOrder(sysUserPage, hql);
		
		List<SysUser> tMaps = sysUserDao.find(hql, params, sysUserPage.getPage(), sysUserPage.getRows());
		if(tMaps!=null && tMaps.size()>0){
			for(SysUser t : tMaps){
				SysUserPage m = new SysUserPage();
				BeanUtils.copyProperties(t,m, new String[]{});
				if(t.getCertificateTime()!=null){
					m.setStrCertificateTime(ChangeTimeFormat.getInstance().timeStampToString(t.getCertificateTime()));
				}
				InspectionStation inspectionStation = t.getInspectionStation();
			
				
				if(inspectionStation != null){
						m.setStationId(inspectionStation.getStationId());
						m.setStationName(inspectionStation.getStationName());
						m.setStationStatus(inspectionStation.getStatus());
				}
				
				m.setUserStatus(t.getStatus());
				m.setPassword("");
				sysUserPages.add(m);
			}	
		}
		
		dg.setRows(sysUserPages);
		return dg;
	}
	
	/**
	 * 返回所有正常状态用户的信息
	 *@author mark
	 */
	@Override
	public List<SysUserPage> getAllSysUserPages(SysUserPage sysUserPage){
		
		//取出session中的用户信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		Integer sessionStationId = (Integer)httpSession.getAttribute("stationId");
		
		String hql = "FROM SysUser as su WHERE 1=1 ";
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<SysUserPage> nl = new ArrayList<SysUserPage>();
		
		if(sysUserPage != null){
			
			sysUserPage.setUserStatus(0);
			//根据session中的信息判断station的值
			if(sessionStationId != 0 ){
				sysUserPage.setStationId(sessionStationId);
			}
			hql = this.addSysUserWhere(sysUserPage, hql, params);
			
			List<SysUser> tMaps =  sysUserDao.find(hql, params);
			if(tMaps!=null && tMaps.size()>0){
				for (SysUser sysUser : tMaps) {
					
					SysUserPage m = new SysUserPage();
					
					BeanUtils.copyProperties(sysUser, m);
					
					Timestamp certificateTime = sysUser.getCertificateTime();
					if(certificateTime != null){
						m.setStrCertificateTime(ChangeTimeFormat.getInstance().timeStampToString(certificateTime));
					}
					m.setPassword("");	
					
					InspectionStation inspectionStation = sysUser.getInspectionStation();
					
					if(inspectionStation != null){
							m.setStationId(inspectionStation.getStationId());
							m.setStationName(inspectionStation.getStationName());
					}
					
					m.setPassword("");
					nl.add(m);
				}
				
			}
			return nl;
		}
		return null;
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
