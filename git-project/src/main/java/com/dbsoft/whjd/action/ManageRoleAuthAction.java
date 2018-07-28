package com.dbsoft.whjd.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.RoleAuthPage;
import com.dbsoft.whjd.pageModel.SysUserPage;
import com.dbsoft.whjd.service.IManageRoleAuthService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author mark 作用：管理用户信息
 * */

@Action(value = "manageRoleAuthAction", results = {
		@Result(name = "roleEdit", location = "/jsp/RoleAuthManage/roleEdit.jsp"),
		@Result(name = "userRoleEdit", location = "/jsp/RoleAuthManage/userRoleEdit.jsp") })
public class ManageRoleAuthAction extends BaseAction implements ModelDriven<RoleAuthPage> {

	private static final Logger logger = Logger.getLogger(ManageRoleAuthAction.class);

	private IManageRoleAuthService manageRoleAuthService;

	private RoleAuthPage roleAuthPage = new RoleAuthPage();

	/**
	 * 增加 角色信息
	 * */
	public void addNewRole() {
		try {
			Json json = manageRoleAuthService.addNewRole(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 增加 用户角色
	 * */
	public void addUserRole() {
		try {
			Json json = manageRoleAuthService.addUserRole(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 判断名是否可用
	 * */
	public void hasTheRole() {
		try {
			Json json = manageRoleAuthService.hasTheRole(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 判断名是否可用
	 * */
	public void hasTheRoleDiff() {
		try {
			Json json = manageRoleAuthService.hasTheRoleDiff(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 给role添加权限资源list
	 * */

	public void addRolePrivilegs() {
		try {
			super.writeJson(manageRoleAuthService.addRolePrivilegs(roleAuthPage));
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}
	
	/**
	 * 返回权限资源list
	 * */

	public void getRolePrivilegs() {
		try {
			super.writeJson(manageRoleAuthService.getRolePrivilegs(roleAuthPage));
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 获取菜单中已有的url给用户模糊选用
	 * */
	public void getAuthRoleNames() {
		try {
			super.writeJson(manageRoleAuthService.getAuthRoleNames(roleAuthPage));
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 查找权限资源信息返回datagrid
	 * */
	public void getRoleDatagrid() throws Exception {
		try {
			DataGrid pd = manageRoleAuthService.findRoles(roleAuthPage);
			if (pd == null) {
				throw new Exception();
			} else {
				super.writeJson(pd);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("UserDatagrid 返回值为空！");
		}
	}

	/**
	 * 查找权限资源信息返回datagrid
	 * */
	public void getUserRoleDatagrid() throws Exception {
		try {
			DataGrid pd = manageRoleAuthService.findUserRoles(roleAuthPage);
			if (pd == null) {
				throw new Exception();
			} else {
				super.writeJson(pd);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("UserDatagrid 返回值为空！");
		}
	}

	public String roleEdit() throws Exception {
		return "roleEdit";
	}

	public String userRoleEdit() throws Exception {
		return "userRoleEdit";
	}

	public void getUserSimplifyNames() {

		try {
			List<SysUserPage> sp = manageRoleAuthService.getUserSimplifyNames(roleAuthPage.getQ());
			if (sp == null) {
				throw new Exception();
			} else {
				super.writeJson(sp);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("UserList 返回为空！");

		}
	}

	public void getRoleNames() {

		try {
			List<RoleAuthPage> sp = manageRoleAuthService.getRoleNames(roleAuthPage.getQ());
			if (sp == null) {
				throw new Exception();
			} else {
				super.writeJson(sp);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("UserList 返回为空！");

		}
	}

	/**
	 * 更新角色信息
	 */
	public void editRoleRecord() {
		try {
			Json json = manageRoleAuthService.updateRole(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	/**
	 * 更新用户角色信息
	 */
	public void editUserRoleRecord() {
		try {
			Json json = manageRoleAuthService.updateUserRole(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	public void changeRoleStatus() {
		try {
			Json json = manageRoleAuthService.changeRoleStatus(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	public void changeUserRoleStatus() {
		try {
			Json json = manageRoleAuthService.changeUserRoleStatus(roleAuthPage);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			System.out.println("出现错误");
		}
	}

	@Override
	public RoleAuthPage getModel() {
		return roleAuthPage;
	}

	public IManageRoleAuthService getManageRoleAuthService() {
		return manageRoleAuthService;
	}

	@Resource(name = "manageRoleAuthService")
	public void setManageRoleAuthService(IManageRoleAuthService manageRoleAuthService) {
		this.manageRoleAuthService = manageRoleAuthService;
	}

}
