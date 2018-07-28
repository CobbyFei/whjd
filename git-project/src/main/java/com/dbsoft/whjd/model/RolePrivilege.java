package com.dbsoft.whjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * RolePrivilege entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rolePrivilege", schema = "dbo", catalog = "whjd")
public class RolePrivilege implements java.io.Serializable {

	// Fields

	private Integer id;
	private Menu menu;
	private Roles roles;
	private Integer relationStatus;

	// Constructors

	/** default constructor */
	public RolePrivilege() {
	}

	/** full constructor */
	public RolePrivilege(Menu menu, Roles roles, Integer relationStatus) {
		this.menu = menu;
		this.roles = roles;
		this.relationStatus = relationStatus;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menuID")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleID")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Column(name = "relationStatus")
	public Integer getRelationStatus() {
		return this.relationStatus;
	}

	public void setRelationStatus(Integer relationStatus) {
		this.relationStatus = relationStatus;
	}

}