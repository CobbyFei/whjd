package com.dbsoft.whjd.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.SysUserPage;
import com.dbsoft.whjd.service.IManageUserService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author mark 作用：管理用户信息
 * */

@Action(value = "manageUserAction", results = {
		@Result(name ="sysUserEdit", location="/jsp/UserManage/sysUserEdit.jsp")
})
public class ManageUserAction extends BaseAction implements ModelDriven<SysUserPage> {
	
	private static final Logger logger = Logger.getLogger(ManageUserAction.class);
	
	@Autowired
	@Qualifier("manageUserService")
	private IManageUserService manageUserService;
	
	private SysUserPage sysUserPage = new SysUserPage();
	
	private String q;
	
	public String getQ() {
		return q;
	}
	
	public void setQ(String q) {
		this.q = q;
	}
	
	/**
	 * 增加 人员信息
	 * */
	public void addNewUser(){
		try{
			Json json = manageUserService.addNewUser(sysUserPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	
	/**
	 * 判断是用户名是否可用
	 * */
	public void hasUserOfSimplifyName(){
		try{
			Json json = manageUserService.hasUserOfSimplifyName(q);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	
	/**
	 * 查找人员信息返回datagrid
	 * */
	public void getSysUserDatagrid(){
		 try{
	    	DataGrid syu=manageUserService.findSysUsers(sysUserPage);
	    	if(syu== null){
	    		throw new Exception();
	    	}else{
	    		super.writeJson(syu);
		    }
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("UserDatagrid 返回值为空！");
	    	return;
		}
	}
	/**
	 * 据q 查找人员信息返回list
	 */
	public void getSysUsers(){
		sysUserPage.setUserName(q);
		
		try{	
			HttpSession session=ServletActionContext.getRequest().getSession();
			String stationNameStr = (String)session.getAttribute("stationName");
			if(stationNameStr == null){
				throw new Exception();
			}
			if(!stationNameStr.equals("市局"))
			{
				sysUserPage.setStationName(stationNameStr);
			}
	    	 List<SysUserPage> sp =manageUserService.getAllSysUserPages(sysUserPage);
	    	 if(sp==null){
	    		 throw new Exception();
	    	 }else{
	    		 super.writeJson(sp);
	    	 }
		    
	    }catch (Exception e) {
	    	logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("UserList 返回为空！");
	    	
		}
	}
	
	public String sysUserEdit() throws Exception{
			return "sysUserEdit";
	}
	
	/**
	 * 更改人员状态
	 */
	public void changeSysUserStatus(){
		try{
			Json json = manageUserService.changeSysUserStatus(sysUserPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	/**
	 *更新人员信息 
	 */
	public void editSysUserRecord(){
		try{
			Json json = manageUserService.updateSysUser(sysUserPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误");
		}
	}
	 
	
	@Override
	public SysUserPage getModel(){
		return sysUserPage;
	}
}
