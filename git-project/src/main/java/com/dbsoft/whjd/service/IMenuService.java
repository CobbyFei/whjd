package com.dbsoft.whjd.service;

import java.util.List;

import com.dbsoft.whjd.pageModel.MenuPage;
import com.dbsoft.whjd.pageModel.TreeNode;


public interface IMenuService {
	
	/**
	 * 返回tree的节点列表
	 * @param menu pageModel中菜单VO
	 * @param b 是否一起迭代子节点
	 * @return 节点列表
	 */
	public List<TreeNode> tree(MenuPage menu, Boolean b);
	
	/**
	 * 根据节点编号获得该该节点的信息，同步获取
	 * 前台其实采用的是同步获取
	 * @return 所有的节点信息
	 */
	public List<MenuPage> getAllTreeNode();
	
	/**
	 * 获得菜单treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<MenuPage> treegrid(MenuPage menu);
	
	/**
	 * 删除菜单
	 *
	 * @param menu
	 */
	public void delete(MenuPage menu);

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 */
	public void add(MenuPage menu);

	public void edit(MenuPage menu);
}
