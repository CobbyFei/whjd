package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.SysUserPage;


public interface IManageUserService {
	/**
	 * 增加人员信息
	 * @param sysUserPage
	 * @return
	 */
	public Json addNewUser(SysUserPage sysUserPage);
	/**
	 * 判断用户名是否已存在
	 * @param simplifyName
	 * @return
	 */
	public Json hasUserOfSimplifyName(String simplifyName);
	/**
	 * 查找人员信息
	 * @param sysUserPage
	 * @return
	 */
	public DataGrid findSysUsers(SysUserPage sysUserPage) throws Exception;
	
	/**
	 * 据q查找人员信息
	 * @param q
	 * @return
	 */
	public List<SysUserPage> getAllSysUserPages(SysUserPage sysUserPage);
	
	/**
	 * 注销人员信息
	 * @param sysUserPage
	 * @return
	 */
	
	public Json changeSysUserStatus(SysUserPage sysUserPage);
	
	/**
	 * 更新人员信息
	 * @param sysUserPage
	 * @return
	 */
	public Json updateSysUser(SysUserPage sysUserPage);
	
}
