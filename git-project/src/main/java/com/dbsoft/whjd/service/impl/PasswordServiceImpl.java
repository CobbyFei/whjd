package com.dbsoft.whjd.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.PasswordPage;
import com.dbsoft.whjd.service.IPasswordService;
import com.dbsoft.whjd.util.Encrypt;
import com.dbsoft.whjd.util.TrimString;

@Service(value="passwordService")
public class PasswordServiceImpl implements IPasswordService {
	
	private IBaseDao<SysUser> sysUserDao;
	
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Resource(name="baseDao" )
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	/**
	 * 重置口令
	 * @author mark
	 */
	@Override
	public Json resetPassword(PasswordPage passwordPage) {
		Json json = new Json();
		//取得session 中的人员的站信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		String sessionStationName = (String)httpSession.getAttribute("stationName");
		try {
			passwordPage = (PasswordPage)TrimString.getInstance().trimObjectString(passwordPage);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("重置口令失败！");
			e.printStackTrace();
		}

		String simplifyName = passwordPage.getSimplifyName();
			
		if(simplifyName!=null && !"".equals(simplifyName)){
			String hql = "FROM SysUser AS s WHERE s.simplifyName = :simplifyName ";
			Map<String, Object> params =  new HashMap<String, Object>();
			params.put("simplifyName", simplifyName.toUpperCase());
			SysUser sysUser = sysUserDao.get(hql, params);
			
			if(sysUser!=null){
					String stationName;
					//查看被操作人员是否属于操作人员相同的站
					if (sysUser.getInspectionStation()!=null) {
						stationName =  sysUser.getInspectionStation().getStationName();
					}else {
						stationName ="市局";
					}
					
					if(sessionStationName.equals(stationName)|| sessionStationName.equals("市局") ){
						//重置口令，加密截取
						String secretPassword = Encrypt.md5(simplifyName.toLowerCase()).substring(0,16);//重置初始密码为其小写形式
						sysUser.setPassword(secretPassword);
					}else{
						json.setMsg("您无权进行此操作！");
						json.setSuccess(false);
						return json;
					}
					try {
						sysUserDao.update(sysUser);	
						json.setSuccess(true);
						json.setMsg("操作成功！");
					} catch (Exception e) {
						json.setSuccess(false);
						json.setMsg("重置失败请重新操作！");
					}
			}else{
				json.setSuccess(false);
				json.setMsg("用户名不存在！");
			}
				  
		}else{
				json.setSuccess(false);
				json.setMsg("重置失败请重新操作！");
		}
		return json;
	}

	/**
	 * 更改口令
	 * @author mark
	 */
	@Override
	public Json changePassword(PasswordPage passwordPage) {
		
		Json json = new Json();
		
		//取得session 中的人员的站信息
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		String simplifyName= (String)httpSession.getAttribute("simplifyName");
		
		try {
			passwordPage = (PasswordPage)TrimString.getInstance().trimObjectString(passwordPage);
		} catch (Exception e) {
			json.setMsg("更改口令失败！");
			json.setSuccess(false);
			return json;
		}
		String oldPassword = passwordPage.getOldPassword();
		String newPassword = passwordPage.getNewPassword();
		String rePassword = passwordPage.getNewPassword(); 
		
		if(oldPassword == null || newPassword == null || rePassword==null || !newPassword.equals(rePassword)){
			json.setMsg("更改失败！输入项不得为空，新口令和重复口令必须相同！");
			json.setSuccess(false);
			return json;
		}
		
		String hql ="FROM SysUser WHERE simplifyName =:simplifyName ";
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(simplifyName!=null){
			params.put("simplifyName", simplifyName);
			SysUser sysUser = sysUserDao.get(hql, params);
			if(sysUser!=null){
				String password = sysUser.getPassword();
				//md5比对
				String secretOldPassword = Encrypt.md5(oldPassword).substring(0,16);
				if(!secretOldPassword.equals(password)){
					json.setMsg("更改失败！ 旧口令输入不正确！");
					json.setSuccess(false);
					return json;
				}else{
					//md5 加密比对
					sysUser.setPassword(Encrypt.md5(newPassword).substring(0,16));
					try {
						sysUserDao.update(sysUser);	
						json.setSuccess(true);
						json.setMsg("操作成功！");
					} catch (Exception e) {
						json.setSuccess(false);
						json.setMsg("更改失败！请重新操作！");
					}
				
				}
				
			}else{
				json.setMsg("未查到该用户！请重新操作!");
				json.setSuccess(false);
			}
		}
		
		
		return json;
	}

}
