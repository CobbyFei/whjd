package com.dbsoft.whjd.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.PasswordPage;
import com.dbsoft.whjd.service.IPasswordService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 口令
 * @author mark
 *
 */
@Action(value = "passwordAction")
public class PasswordAction extends BaseAction implements ModelDriven<PasswordPage>{
	
	@Autowired
	private IPasswordService passwordService;
	
	private PasswordPage passwordPage = new PasswordPage();
	@Override
	public PasswordPage getModel(){
		return passwordPage;
	}
	
	private static final Logger logger = Logger.getLogger(PasswordAction.class);
	
	/**
	 * 更改口令
	 */
	public void changePassword(){
		try{
			Json json = passwordService.changePassword(passwordPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误！");
		}
	}
	/**
	 * 重置口令
	 */
	public void resetPassword(){
		try{
			Json json = passwordService.resetPassword(passwordPage);
			super.writeJson(json);
		}catch(Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
	    	System.out.println("出现错误!");
		}
	}
}
