package com.dbsoft.whjd.service;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.UserLoginPage;

/**
 * check user login
 * @author njustwzr
 *
 */
public interface IUserLoginService {
	/**
	 * 用户登录
	 * @param userLoginPage
	 * @return
	 */
	public UserLoginPage checkUserLogin(UserLoginPage userLoginPage);
	
	/**
	 * 发送心跳包
	 * @param userLoginPage
	 * @return
	 */
	public UserLoginPage sendHeartsBeat(UserLoginPage userLoginPage);
	/**
	 * 安全退出系统
	 * @param userLoginPage
	 * @return
	 */
	public UserLoginPage logOut(UserLoginPage userLoginPage);

}
