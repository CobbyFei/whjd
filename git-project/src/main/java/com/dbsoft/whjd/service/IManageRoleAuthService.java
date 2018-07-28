package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.Json_RolePrivilege;
import com.dbsoft.whjd.pageModel.RoleAuthPage;
import com.dbsoft.whjd.pageModel.SysUserPage;


public interface IManageRoleAuthService {
	/**
	 * 增加角色信息
	 * @param rolePage
	 * @return
	 */
	public Json addNewRole(RoleAuthPage rolePage);
	/**
	 * 判断用户名是否已存在
	 * @param name
	 * @return
	 */
	public Json hasTheRole(RoleAuthPage roleAuthPage);
	
/*	*//**
	 * 获取菜单中所有的url
	 * @param q
	 * @return
	 *//*
	public List<RoleAuthPage> getAuthUrls(RoleAuthPage roleAuthPage);*/
	
	/**
	 * 查找人员信息
	 * @param role
	 * @return
	 */
	public DataGrid findRoles(RoleAuthPage roleAuthPage);
	public Json updateRole(RoleAuthPage roleAuthPage);
	public Json changeRoleStatus(RoleAuthPage roleAuthPage);
	public List<RoleAuthPage> getAuthRoleNames(RoleAuthPage roleAuthPage);
	
	/**
	 * 判断用户名是否已存在,但是不同于本身
	 * @param name
	 * @return
	 */
	public Json hasTheRoleDiff(RoleAuthPage roleAuthPage);
	public List<SysUserPage> getUserSimplifyNames(String q);
	public List<RoleAuthPage> getRoleNames(String q);
	public Json addUserRole(RoleAuthPage roleAuthPage);
	public DataGrid findUserRoles(RoleAuthPage roleAuthPage);
	public Json changeUserRoleStatus(RoleAuthPage roleAuthPage);
	public Json updateUserRole(RoleAuthPage roleAuthPage);
	public Json addRolePrivilegs(RoleAuthPage roleAuthPage);
	public Json_RolePrivilege getRolePrivilegs(RoleAuthPage roleAuthPage);
	
}
