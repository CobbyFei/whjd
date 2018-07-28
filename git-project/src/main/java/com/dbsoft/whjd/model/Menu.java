package com.dbsoft.whjd.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu", schema = "dbo", catalog = "whjd")
public class Menu implements java.io.Serializable {

	// Fields

	private String id;
	private Menu menu;
	private String iconCls;
	private String text;
	private String url;
	private Integer admin;
	private Integer seq;
	private Set<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>(0);
	private Set<Menu> menus = new HashSet<Menu>(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(Menu menu, String iconCls, String text, String url,
			Integer admin, Integer seq, Set<RolePrivilege> rolePrivileges,
			Set<Menu> menus) {
		this.menu = menu;
		this.iconCls = iconCls;
		this.text = text;
		this.url = url;
		this.admin = admin;
		this.seq = seq;
		this.rolePrivileges = rolePrivileges;
		this.menus = menus;
	}

	// Property accessors
	
	@Id
	@Column(name = "Id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "iconCls", length = 50)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "text", length = 50)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "admin")
	public Integer getAdmin() {
		return this.admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	@Column(name = "seq")
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<RolePrivilege> getRolePrivileges() {
		return this.rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

}