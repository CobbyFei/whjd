package com.dbsoft.whjd.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dbsoft.whjd.pageModel.Json;
import com.dbsoft.whjd.pageModel.MenuPage;
import com.dbsoft.whjd.service.IMenuService;
import com.dbsoft.whjd.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

@Action(value = "menuAction", results = { @Result(name = "menu", location = "/admin/menu.jsp"),
		@Result(name = "menuAdd", location = "/admin/menuAdd.jsp"),
		@Result(name = "menuEdit", location = "/admin/menuEdit.jsp") })
public class MenuAction extends BaseAction implements ModelDriven<MenuPage> {

	// log
	private static final Logger logger = Logger.getLogger(MenuAction.class);

	private IMenuService menuService;
	private MenuPage menuPage = new MenuPage();

	public IMenuService getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	public MenuPage getModel() {
		return menuPage;
	}

	public void getAllTreeNode() {
		super.writeJson(menuService.getAllTreeNode());
	}

	/**
	 * 首页获得菜单树
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(menuService.tree(menuPage, false));
	}

	public void doNotNeedSession_treeRecursive() {
		//TODO 正式运行的时候可以考虑关闭，现在默认无权限，全菜单
		HttpSession session=ServletActionContext.getRequest().getSession();
		String roleName=(String) session.getAttribute("userRole");
		System.out.println(roleName);
		menuPage.setRole(roleName);
		if (menuPage.getRole() == null || "".equals(menuPage.getRole())) {
			menuPage.setRole("superAdmin");
		}
		super.writeJson(menuService.tree(menuPage, true));
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		super.writeJson(menuService.treegrid(menuPage));
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			menuService.edit(menuPage);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	public void add() {
		Json j = new Json();
		try {
			menuService.add(menuPage);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			menuService.delete(menuPage);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(menuPage.getId());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	public String menuAdd() {
		return "menuAdd";
	}

	public String menuEdit() {
		return "menuEdit";
	}

}
