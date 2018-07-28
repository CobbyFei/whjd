package com.dbsoft.whjd.action;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.UserLoginPage;
import com.dbsoft.whjd.service.IUserLoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
@Action(value="userLoginAction")
public class UserLoginAction extends BaseAction implements ModelDriven<UserLoginPage>{
	private UserLoginPage userLoginPage=new UserLoginPage();
	private IUserLoginService userLoginService;

	public IUserLoginService getUserLoginService() {
		return userLoginService;
	}
	@Autowired
	public void setUserLoginService(IUserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}
	/**
	 * 处理用户登录
	 */
	public void checkUserLogin()
	{
		UserLoginPage res=new UserLoginPage();
	     try {
	    	if(userLoginPage.getSimplifyName()!=null)
	    	{
	    		userLoginPage.setSimplifyName(userLoginPage.getSimplifyName().toUpperCase());
	    	}
			res=userLoginService.checkUserLogin(userLoginPage);
			if(res.isSuccess()==true)
			{
				HttpSession session=ServletActionContext.getRequest().getSession(true);
				session.setAttribute("simplifyName", res.getSimplifyName());
				session.setAttribute("userRole", res.getUserRoleName());
				session.setAttribute("stationName", res.getStationName());
				session.setAttribute("stationId", res.getStationId());
				session.setAttribute("userName", res.getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setSuccess(false);
			res.setMsg("登录出现异常");
		}
	     super.writeJson(res);
	}
	
	/**
	 * 发送心跳包
	 */
	public void sendBeatHearts(){
		UserLoginPage res=new UserLoginPage();
		HttpSession session=ServletActionContext.getRequest().getSession();
		String simplifyName=session.getAttribute("simplifyName").toString();
		userLoginPage.setSimplifyName(simplifyName);
		try{
			res=userLoginService.sendHeartsBeat(userLoginPage);
			if(res.isSuccess()==false)
			{
				session.invalidate();
			}
		}catch (Exception e) {
			e.printStackTrace();
			res.setSuccess(false);
			res.setMsg("服务器出现异常");
		}
		super.writeJson(res);
	}
	
	/**
	 * 安全退出系统
	 */
	public void logOut(){
		UserLoginPage res=new UserLoginPage();
		HttpSession session=ServletActionContext.getRequest().getSession();
		String simplifyName=session.getAttribute("simplifyName").toString();
		userLoginPage.setSimplifyName(simplifyName);
		try{
			res=userLoginService.logOut(userLoginPage);
			if(res.isSuccess())
			{
				session.invalidate();
			}
		}catch (Exception e) {
			e.printStackTrace();
			res.setSuccess(false);
			res.setMsg("退出系统出现异常");
		}
		super.writeJson(res);
	}
	@Override
	public UserLoginPage getModel() {
		// TODO Auto-generated method stub
		return userLoginPage;
	}
	

}
