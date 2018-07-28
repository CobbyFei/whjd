package com.dbsoft.whjd.pageModel;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
public class RoleAuthPage implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleDescription;
	private Integer roleStatus;

	private Integer id;
	private String url;
	private String text;
	
	private Integer userId;
	private String userName;
	private Integer relationStatus;

	private String ids;

	private String menuId;
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}


	/**
	 * 排序列的名称，在pageModel中的列
	 */
	private String sort;
	/**
	 * 升序还是降序
	 */
	private String order;
	/**
	 * 第几页数据
	 */
	private Integer page;

	/**
	 * 每页记录数
	 */
	private Integer rows;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(Integer relationStatus) {
		this.relationStatus = relationStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}