package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.Menu;
import com.dbsoft.whjd.model.RolePrivilege;
import com.dbsoft.whjd.model.Roles;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.UserRole;
import com.dbsoft.whjd.pageModel.DataGrid;
import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.Json_RolePrivilege;
import com.dbsoft.whjd.pageModel.RoleAuthPage;
import com.dbsoft.whjd.pageModel.SysUserPage;
import com.dbsoft.whjd.service.IManageRoleAuthService;
import com.dbsoft.whjd.util.TrimString;

@Service(value = "manageRoleAuthService")
public class ManageRoleAuthServiceImpl implements IManageRoleAuthService {
	private IBaseDao<Roles> rolesDao;
	private IBaseDao<SysUser> sysUserDao;
	private IBaseDao<Menu> menuDao;
	private IBaseDao<UserRole> userRoleDao;
	private IBaseDao<RolePrivilege> rolePrivilegeDao;

	/**
	 * 拼接hql中的排序信息
	 * 
	 * @author mark
	 * @param sysUserPage
	 * @param hql
	 * @return
	 */
	private String addOrder(RoleAuthPage roleAuthPage, String hql) {
		if (roleAuthPage.getSort() != null && roleAuthPage.getOrder() != null) {
			hql += " ORDER BY " + roleAuthPage.getSort() + " " + roleAuthPage.getOrder();
		}
		return hql;
	}

	/**
	 * 拼接hql中的搜索条件
	 * 
	 * @param sysUserPage
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addRoleWhere(RoleAuthPage roleAuthPage, String hql, Map<String, Object> params) {

		String roleName = roleAuthPage.getRoleName();
		Integer roleStatus = roleAuthPage.getRoleStatus();

		if (roleName != null && !roleName.trim().equals("")) {
			hql += " AND r.roleName LIKE :roleName ";
			params.put("roleName", "%%" + roleName + "%%");
		}

		if (roleStatus != null && roleStatus >= 0 && roleStatus <= 1) {
			hql += " AND r.roleStatus =:roleStatus ";
			params.put("roleStatus", roleStatus);
		}
		return hql;
	}

	/**
	 * 拼接hql中的搜索条件
	 * 
	 * @param sysUserPage
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addUserRoleWhere(RoleAuthPage roleAuthPage, String hql, Map<String, Object> params) {

		String userName = roleAuthPage.getUserName();
		String roleName = roleAuthPage.getRoleName();
		Integer relationStatus = roleAuthPage.getRelationStatus();

		if (userName != null && !userName.trim().equals("")) {
			hql += " AND r.sysUser.simplifyName LIKE :simplifyName ";
			params.put("simplifyName", "%" + userName.trim() + "%");
		}
		if (roleName != null && !roleName.trim().equals("")) {
			hql += " AND r.roles.roleName LIKE :roleName ";
			params.put("roleName", "%" + roleName.trim() + "%");
		}

		if (relationStatus != null && relationStatus >= 0 && relationStatus <= 1) {
			hql += " AND r.relationStatus =:relationStatus ";
			params.put("relationStatus", relationStatus);
		}
		return hql;
	}

	/**
	 * 添加用户信息
	 * 
	 * @author mark
	 */
	@Override
	public Json addNewRole(RoleAuthPage rolePage) {

		Json json = new Json();

		String roleName = rolePage.getRoleName();
		if (roleName == null || "".equals(roleName)) {
			json.setMsg("角色名不能空！");
			json.setSuccess(false);
			return json;
		}

		// 判断sysUserPage 中的simplifyName不能为空
		Integer staus = rolePage.getRoleStatus();
		if (staus == null) {
			json.setMsg("请选择角色状态！");
			json.setSuccess(false);
			return json;
		}
		Roles roles = new Roles();
		try {
			rolePage = (RoleAuthPage) TrimString.getInstance().trimObjectString(rolePage);
			BeanUtils.copyProperties(rolePage, roles);
			rolesDao.save(roles);
			json.setMsg("添加角色成功！");
			json.setSuccess(true);

		} catch (Exception e) {
			json.setMsg("添加角色失败！");
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 判断用户名是否已存在
	 * 
	 * @author mark
	 */
	@Override
	public Json hasTheRole(RoleAuthPage roleAuthPage) {

		String roleName = roleAuthPage.getQ();
		Json json = new Json();

		if (roleName == null || "".equals(roleName)) {
			json.setSuccess(true);
			json.setMsg("角色名不能为空！");
			return json;
		}

		roleName = roleName.trim();

		String hql = "from Roles as s Where s.roleName = :roleName";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("roleName", roleName);

		Roles r = rolesDao.get(hql, tMap);

		if (r != null) {
			json.setSuccess(true);
			json.setMsg("用户名已存在！");
		} else {
			json.setSuccess(false);
			json.setMsg("用户名可用！");
		}

		return json;

	}

	/*
	 * @Override public List<RoleAuthPage> getAuthUrls(RoleAuthPage
	 * roleAuthPage) { String q = roleAuthPage.getQ(); if (q != null) { String
	 * textString = "%" + q + "%"; String hql =
	 * "from GisMenu as s Where s.url like :textString"; Map<String, Object>
	 * tMap = new HashMap<String, Object>(); tMap.put("textString", textString);
	 * List<Menu> gs = menuDao.find(hql, tMap, 1, 10); List<RoleAuthPage> nl =
	 * new ArrayList<RoleAuthPage>(); for (Menu g : gs) { RoleAuthPage rap = new
	 * RoleAuthPage(); rap.setUrl(g.getUrl()); nl.add(rap); } return nl; }
	 * return null; }
	 */

	/**
	 * 查找权限资源信息返回datagrid
	 * 
	 * @author mark
	 */

	@Override
	public DataGrid findRoles(RoleAuthPage roleAuthPage) {
		List<RoleAuthPage> roleAuthPages = new ArrayList<RoleAuthPage>();

		String hql = "FROM Roles as r WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();

		// 增加where
		if (roleAuthPage != null) {
			hql = this.addRoleWhere(roleAuthPage, hql, params);
		}

		DataGrid dg = new DataGrid();

		dg.setTotal(rolesDao.count("SELECT COUNT(*) " + hql, params));
		hql = this.addOrder(roleAuthPage, hql);

		List<Roles> tMaps = rolesDao.find(hql, params, roleAuthPage.getPage(), roleAuthPage.getRows());
		if (tMaps != null && tMaps.size() > 0) {
			for (Roles r : tMaps) {
				RoleAuthPage rap = new RoleAuthPage();
				BeanUtils.copyProperties(r, rap, new String[] {});
				roleAuthPages.add(rap);
			}
		}

		dg.setRows(roleAuthPages);
		return dg;
	}

	public IBaseDao<Roles> getRolesDao() {
		return rolesDao;
	}

	@Resource(name = "baseDao")
	public void setRolesDao(IBaseDao<Roles> rolesDao) {
		this.rolesDao = rolesDao;
	}

	public IBaseDao<Menu> getMenuDao() {
		return menuDao;
	}

	@Resource(name = "baseDao")
	public void setMenuDao(IBaseDao<Menu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public Json updateRole(RoleAuthPage roleAuthPage) {
		Json json = new Json();

		Roles roles = rolesDao.get(Roles.class, roleAuthPage.getRoleId());

		try {
			roleAuthPage = (RoleAuthPage) TrimString.getInstance().trimObjectString(roleAuthPage);

		} catch (Exception e) {
			json.setMsg("更新失败！");
			json.setSuccess(false);
			return json;
		} // 判断sysUserPage 中的userName不能为空 String userName =
		String roleName = roleAuthPage.getRoleName();
		if (roleName == null || "".equals(roleName)) {
			json.setMsg("更新失败资源名名不能为空！");
			json.setSuccess(false);
			return json;
		}

		roles.setRoleName(roleName);
		roles.setRoleDescription(roleAuthPage.getRoleDescription());
		roles.setRoleStatus(roleAuthPage.getRoleStatus());

		try {
			rolesDao.update(roles);
			json.setMsg("更新成功！");
			json.setSuccess(true);

		} catch (Exception e) {
			json.setMsg("更新失败！");
			json.setSuccess(false);
		}

		return json;
	}

	@Override
	public Json changeRoleStatus(RoleAuthPage roleAuthPage) {
		String ids = roleAuthPage.getIds();
		Integer changeToStatus = roleAuthPage.getRoleStatus();
		Json json = new Json();
		if (ids != null && ids != "" && changeToStatus != null && changeToStatus >= 0 && changeToStatus <= 1) {

			String[] strIds = ids.split(",");

			for (int i = 0; i < strIds.length; i++) {
				Roles roles = rolesDao.get(Roles.class, Integer.parseInt(strIds[i].trim()));
				roles.setRoleStatus(changeToStatus);
				try {
					rolesDao.update(roles);
				} catch (Exception e) {
					json.setMsg("操作失败！");
					json.setSuccess(false);
					return json;
				}
			}
			json.setMsg("操作成功！");
			json.setSuccess(true);
			return json;
		} else {
			json.setMsg("操作失败！");
			json.setSuccess(false);
			return json;
		}
	}

	@Override
	public List<RoleAuthPage> getAuthRoleNames(RoleAuthPage roleAuthPage) {
		String textString = "%" + roleAuthPage.getQ() + "%";
		String hql = "from Roles as r Where r.roleName like :textString";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("textString", textString);
		List<Roles> rs = rolesDao.find(hql, tMap);
		List<RoleAuthPage> nl = new ArrayList<RoleAuthPage>();
		for (Roles r : rs) {
			RoleAuthPage rap = new RoleAuthPage();
			rap.setRoleName(r.getRoleName());
			rap.setRoleId(r.getRoleId());
			nl.add(rap);
		}
		return nl;
	}

	@Override
	public Json hasTheRoleDiff(RoleAuthPage roleAuthPage) {
		String roleName = roleAuthPage.getQ();
		String myRoleName = roleAuthPage.getRoleName();
		Json json = new Json();

		if (roleName == null || "".equals(roleName)) {
			json.setSuccess(true);
			json.setMsg("角色名不能为空！");
			return json;
		}

		roleName = roleName.trim();
		myRoleName = myRoleName.trim();
		String hql = "from Roles as s Where s.roleName = :roleName";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("roleName", roleName);

		Roles r = rolesDao.get(hql, tMap);

		if (r != null) {
			if (!myRoleName.equals(roleName)) {
				// 不是本身
				json.setSuccess(true);
				json.setMsg("用户名已存在！");
			} else {
				// 是本身
				json.setSuccess(false);
				json.setMsg("用户名可用！");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("用户名可用！");
		}

		return json;
	}

	@Override
	public List<SysUserPage> getUserSimplifyNames(String q) {
		String simplifyName = "%" + q + "%";
		String hql = "from SysUser as s Where s.simplifyName like :simplifyName";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("simplifyName", simplifyName);
		List<SysUser> ss = sysUserDao.find(hql, tMap, 1, 10);
		List<SysUserPage> nl = new ArrayList<SysUserPage>();
		for (SysUser r : ss) {
			SysUserPage sup = new SysUserPage();
			sup.setSimplifyName(r.getSimplifyName());
			sup.setUserId(r.getUserId());
			nl.add(sup);
		}
		return nl;
	}

	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}

	@Resource(name = "baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	@Override
	public List<RoleAuthPage> getRoleNames(String q) {
		List<Roles> rs;
		if (q == null || "".equals(q)) {
			String hql = "from Roles as r Where r.roleStatus = 1";
			rs = rolesDao.find(hql, 1, 10);
		} else {
			String roleName = "%" + q + "%";
			String hql = "from Roles as r Where r.roleName like :roleName and r.roleStatus = 1";
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("roleName", roleName);
			rs = rolesDao.find(hql, tMap, 1, 10);
		}

		List<RoleAuthPage> nl = new ArrayList<RoleAuthPage>();
		for (Roles r : rs) {
			RoleAuthPage rap = new RoleAuthPage();
			rap.setRoleName(r.getRoleName());
			rap.setRoleId(r.getRoleId());
			nl.add(rap);
		}
		return nl;
	}

	@Override
	public Json addUserRole(RoleAuthPage roleAuthPage) {
		Json json = new Json();
		Integer userId = roleAuthPage.getUserId();
		Integer roleId = roleAuthPage.getRoleId();
		if (roleId == null) {
			json.setMsg("角色不能空！");
			json.setSuccess(false);
			return json;
		}
		if (userId == null) {
			json.setMsg("用户不能空！");
			json.setSuccess(false);
			return json;
		}

		if (UserRoleHasUser(userId)) {
			json.setMsg("该用户已有角色！");
			json.setSuccess(false);
			return json;
		}
		// 判断sysUserPage 中的simplifyName不能为空
		Integer relationStatus = roleAuthPage.getRelationStatus();
		if (relationStatus == null) {
			json.setMsg("请选择状态！");
			json.setSuccess(false);
			return json;
		}
		UserRole userRole = new UserRole();
		try {
			roleAuthPage = (RoleAuthPage) TrimString.getInstance().trimObjectString(roleAuthPage);
			userRole.setRelationStatus(roleAuthPage.getRelationStatus());
			userRole.setRoles(rolesDao.get(Roles.class, roleId));
			userRole.setSysUser(sysUserDao.get(SysUser.class, userId));
			userRoleDao.save(userRole);
			json.setMsg("添加成功！");
			json.setSuccess(true);

		} catch (Exception e) {
			json.setMsg("添加失败！");
			json.setSuccess(false);
		}
		return json;
	}

	private boolean UserRoleHasUser(Integer userId) {
		String hql = "from UserRole as u Where u.sysUser.userId = :userId";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("userId", userId);
		UserRole ur = userRoleDao.get(hql, tMap);
		if (ur != null) {
			return true;
		} else {
			return false;
		}
	}

	public IBaseDao<UserRole> getUserRoleDao() {
		return userRoleDao;
	}

	@Resource(name = "baseDao")
	public void setUserRoleDao(IBaseDao<UserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Override
	public DataGrid findUserRoles(RoleAuthPage roleAuthPage) {
		List<RoleAuthPage> roleAuthPages = new ArrayList<RoleAuthPage>();

		String hql = "FROM UserRole as r WHERE 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();

		// 增加where
		if (roleAuthPage != null) {
			hql = this.addUserRoleWhere(roleAuthPage, hql, params);
		}

		DataGrid dg = new DataGrid();
		dg.setTotal(userRoleDao.count("SELECT COUNT(*) " + hql, params));
		hql = this.addOrder(roleAuthPage, hql);

		List<UserRole> tMaps = userRoleDao.find(hql, params, roleAuthPage.getPage(), roleAuthPage.getRows());
		if (tMaps != null && tMaps.size() > 0) {
			for (UserRole r : tMaps) {
				RoleAuthPage rap = new RoleAuthPage();
				rap.setId(r.getId());
				rap.setRoleName(r.getRoles().getRoleName());
				rap.setUserName(r.getSysUser().getSimplifyName());
				rap.setRoleId(r.getRoles().getRoleId());
				rap.setUserId(r.getSysUser().getUserId());
				rap.setRelationStatus(r.getRelationStatus());
				roleAuthPages.add(rap);
			}
		}

		dg.setRows(roleAuthPages);
		return dg;
	}

	@Override
	public Json changeUserRoleStatus(RoleAuthPage roleAuthPage) {
		String ids = roleAuthPage.getIds();
		Integer changeToStatus = roleAuthPage.getRelationStatus();
		Json json = new Json();
		if (ids != null && ids != "" && changeToStatus != null && changeToStatus >= 0 && changeToStatus <= 1) {

			String[] strIds = ids.split(",");

			for (int i = 0; i < strIds.length; i++) {
				UserRole userRole = userRoleDao.get(UserRole.class, Integer.parseInt(strIds[i].trim()));
				userRole.setRelationStatus(changeToStatus);
				try {
					userRoleDao.update(userRole);
				} catch (Exception e) {
					json.setMsg("操作失败！");
					json.setSuccess(false);
					return json;
				}
			}
			json.setMsg("操作成功！");
			json.setSuccess(true);
			return json;
		} else {
			json.setMsg("操作失败！");
			json.setSuccess(false);
			return json;
		}
	}

	@Override
	public Json updateUserRole(RoleAuthPage roleAuthPage) {
		Json json = new Json();

		UserRole userRole = userRoleDao.get(UserRole.class, roleAuthPage.getId());

		try {
			roleAuthPage = (RoleAuthPage) TrimString.getInstance().trimObjectString(roleAuthPage);

		} catch (Exception e) {
			json.setMsg("更新失败！");
			json.setSuccess(false);
			return json;
		} // 判断sysUserPage 中的userName不能为空 String userName =
		Integer roleId = roleAuthPage.getRoleId();
		if (roleId == null || rolesDao.get(Roles.class, roleId) == null) {
			json.setMsg("更新失败,请重选角色");
			json.setSuccess(false);
			return json;
		}

		userRole.setRelationStatus(roleAuthPage.getRelationStatus());
		userRole.setRoles(rolesDao.get(Roles.class, roleAuthPage.getRoleId()));
		userRole.setSysUser(sysUserDao.get(SysUser.class, roleAuthPage.getUserId()));

		try {
			userRoleDao.update(userRole);
			json.setMsg("更新成功！");
			json.setSuccess(true);

		} catch (Exception e) {
			json.setMsg("更新失败！");
			json.setSuccess(false);
		}

		return json;
	}

	@Override
	public Json addRolePrivilegs(RoleAuthPage roleAuthPage) {
		Json json = new Json();

		// 先删除原先的所有该role记录
		Integer roleId = roleAuthPage.getRoleId();

		String hql = "from RolePrivilege as r Where r.roles.roleId = :roleId";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("roleId", roleId);
		List<RolePrivilege> rps = rolePrivilegeDao.find(hql, tMap);
		if(rps==null)
		{
			json.setSuccess(false);
			json.setMsg("角色名不存在");
			return json;
		}
		try {
			for (RolePrivilege r : rps) {
				rolePrivilegeDao.delete(r);
			}
		} catch (Exception e) {
			json.setMsg("该角色旧权限资源删除失败！");
			json.setSuccess(false);
			return json;
		}

		// 给该role添加权限资源
		String ids = roleAuthPage.getIds();
		String[] strIds = ids.split(",");

		try {
			for (String tString : strIds) {
				Menu menu = menuDao.get(Menu.class, tString.trim());
				RolePrivilege rp = new RolePrivilege();
				rp.setMenu(menu);
				rp.setRoles(rolesDao.get(Roles.class, roleId));
				rolePrivilegeDao.save(rp);
			}
		} catch (Exception e) {
			json.setMsg("添加失败！");
			json.setSuccess(false);
			return json;
		}

		json.setMsg("添加成功！");
		json.setSuccess(true);
		return json;
	}

	public IBaseDao<RolePrivilege> getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	@Resource(name = "baseDao")
	public void setRolePrivilegeDao(IBaseDao<RolePrivilege> rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

	@Override
	public Json_RolePrivilege getRolePrivilegs(RoleAuthPage roleAuthPage) {
		Json_RolePrivilege jsonRP = new Json_RolePrivilege();
		Integer roleId = roleAuthPage.getRoleId();
		String hql = "from RolePrivilege as r Where r.roles.roleId = :roleId";
		Map<String, Object> tMap = new HashMap<String, Object>();
		tMap.put("roleId", roleId);
		List<RolePrivilege> rps = rolePrivilegeDao.find(hql, tMap);
		List<String> ids = new ArrayList<String>();
		for (RolePrivilege rolePrivilege : rps) {
			String string = rolePrivilege.getMenu().getId();
			ids.add(string);
		}
		jsonRP.setIds(ids);
		// jsonRP.setMsg("添加成功！");
		jsonRP.setSuccess(true);
		return jsonRP;
	}
}
