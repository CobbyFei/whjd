package com.dbsoft.whjd.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.comparator.MenuComparator;
import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.Menu;
import com.dbsoft.whjd.model.RolePrivilege;
import com.dbsoft.whjd.pageModel.MenuPage;
import com.dbsoft.whjd.pageModel.TreeNode;
import com.dbsoft.whjd.service.IMenuService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	private IBaseDao<Menu> menuDao;
	private IBaseDao<RolePrivilege> rolePrivilegeDao;

	public IBaseDao<RolePrivilege> getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	@Resource(name = "baseDao")
	public void setRolePrivilegeDao(IBaseDao<RolePrivilege> rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

	public IBaseDao<Menu> getMenuDao() {
		return menuDao;
	}

	@Resource(name = "baseDao")
	public void setMenuDao(IBaseDao<Menu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<MenuPage> getAllTreeNode() {
		List<MenuPage> nl = new ArrayList<MenuPage>();
		String hql = "FROM Menu";
		List<Menu> l = menuDao.find(hql);

		if (l != null && l.size() > 0) {
			for (Menu tm : l) {
				MenuPage menu = new MenuPage();
				BeanUtils.copyProperties(tm, menu);
				// 前台只有接受Map才能进行解析
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", tm.getUrl());
				menu.setAttributes(attributes);
				Menu t = tm.getMenu(); // 获得当前节点的父节点对象
				if (t != null) {
					menu.setPid(tm.getMenu().getId());
				}
				nl.add(menu);
			}
		}
		return nl;
	}

	@Override
	public List<TreeNode> tree(MenuPage menu, Boolean b) {

		List<Menu> l = new ArrayList<Menu>();
		List<RolePrivilege> rp = new ArrayList<RolePrivilege>();
		String hql = null;
		if ("superAdmin".equals(menu.getRole())) {
			Map<String, Object> params = new HashMap<String, Object>();
			hql = "FROM Menu WHERE menu IS NULL ORDER BY seq";
			if (menu != null && menu.getId() != null && !menu.getId().trim().equals("")) {
				hql = "FROM Menu WHERE menu.id = :id ORDER BY seq";
				params.put("id", menu.getId());
			}
			l = menuDao.find(hql, params);
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			hql = "FROM RolePrivilege as r WHERE r.menu.menu IS NULL and r.roles.roleName = :roles ORDER BY r.menu.seq";
			if (menu != null && menu.getId() != null && !menu.getId().trim().equals("")) {
				// hql = "FROM Menu WHERE menu.id = :id ORDER BY seq";
				hql = "FROM RolePrivilege as r WHERE r.menu.menu.id = :id and r.roles.roleName = :roles ORDER BY r.menu.seq";
				params.put("id", menu.getId());
			}
			params.put("roles", menu.getRole());
			rp = rolePrivilegeDao.find(hql, params);
			for (RolePrivilege rolePrivilege : rp) {
				l.add(rolePrivilege.getMenu());
			}
		}

		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Menu t : l) {
			tree.add(this.tree(t, b, menu.getRole()));
		}
		return tree;
	}

	@Override
	public List<MenuPage> treegrid(MenuPage menu) {
		String hql = "";
		Map<String, Object> params = new HashMap<String, Object>();
		if (menu != null && menu.getId() != null && !menu.getId().trim().equals("")) {
			hql = "FROM Menu WHERE menu.id = :id ORDER BY seq";
			params.put("id", menu.getId());
		} else {
			hql = "FROM Menu WHERE menu IS NULL ORDER BY seq";
		}
		List<Menu> l = menuDao.find(hql, params);
		return changeModel(l);
	}

	@Override
	public void delete(MenuPage menu) {
		del(menu.getId());
	}

	private void del(String id) {
		Menu t = menuDao.get(Menu.class, id);
		if (t != null) {
			Set<Menu> menus = t.getMenus();
			if (menus != null && !menus.isEmpty()) {
				// 说明当前菜单下面还有子菜单
				for (Menu tmenu : menus) {
					del(tmenu.getId());
				}
			}
			menuDao.delete(t);
		}
	}

	@Override
	public void add(MenuPage menu) {
		Menu t = new Menu();
		BeanUtils.copyProperties(menu, t);
		t.setId(UUID.randomUUID().toString());
		if (menu.getPid() != null) {
			t.setMenu(menuDao.get(Menu.class, menu.getPid()));
		}
		menuDao.save(t);
	}

	@Override
	public void edit(MenuPage menu) {
		Menu t = menuDao.get(Menu.class, menu.getId());
		BeanUtils.copyProperties(menu, t);
		if (menu.getPid() != null && !menu.getPid().equals(menu.getId())) {
			Menu pt = menuDao.get(Menu.class, menu.getPid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Menu> tmenus = t.getMenus();// 当前要修改的节点的所有下级节点
					if (tmenus != null && tmenus.size() > 0) {
						for (Menu tmenu : tmenus) {
							if (tmenu != null) {
								tmenu.setMenu(null);
							}
						}
					}
				}
				t.setMenu(pt);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Menu t, Menu pt) {
		if (pt.getMenu() != null) {
			if (pt.getMenu().getId().equals(t.getId())) {
				return true;
			} else {
				return isDown(t, pt.getMenu());
			}
		}
		return false;
	}

	private List<MenuPage> changeModel(List<Menu> ts) {
		List<MenuPage> l = new ArrayList<MenuPage>();
		if (ts != null && ts.size() > 0) {
			for (Menu t : ts) {
				MenuPage m = new MenuPage();
				BeanUtils.copyProperties(t, m);
				if (t.getMenu() != null) {
					m.setPid(t.getMenu().getId());
					m.setPname(t.getMenu().getText());
				}
				if (countChildren(t.getId()) > 0) {
					m.setState("closed");
				}
				if (t.getIconCls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getIconCls());
				}
				l.add(m);
			}
		}
		return l;
	}

	/**
	 * 统计有多少子节点
	 */
	private Long countChildren(String pid) {
		String hql = "SELECT COUNT(*) FROM Menu WHERE menu.id = :pid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		return menuDao.count(hql, params);
	}

	private TreeNode tree(Menu t, boolean recursive, String roleName) {
		TreeNode node = new TreeNode();
		node.setId(t.getId());
		node.setText(t.getText());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", t.getUrl());
		node.setAttributes(attributes);
		if (t.getIconCls() != null) {
			node.setIconCls(t.getIconCls());
		} else {
			node.setIconCls("");
		}

		List<Menu> menus = new ArrayList<Menu>();
		if ("superAdmin".equals(roleName)) {
			for (Menu m : t.getMenus()) {
				menus.add(m);
			}
		} else {
			String hql = "FROM RolePrivilege as r WHERE r.menu.menu.id = :pid and r.roles.roleName = :roles";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", t.getId());
			params.put("roles", roleName);
			List<RolePrivilege> rpChild = rolePrivilegeDao.find(hql, params);
			for (RolePrivilege rolePrivilege : rpChild) {
				menus.add(rolePrivilege.getMenu());
			}
		}

		if (menus != null && menus.size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				// List<Menu> l = new ArrayList<Menu>(t.getMenus());
				Collections.sort(menus, new MenuComparator()); // 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Menu r : menus) {
					TreeNode tn = tree(r, true, roleName);
					children.add(tn);
				}
				node.setChildren(children);
				node.setState("open");
			}
		}
		return node;
	}

}
