package com.dbsoft.whjd.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles", schema = "dbo", catalog = "whjd")
public class Roles implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleDescription;
	private Integer roleStatus;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>(0);

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** full constructor */
	public Roles(String roleName, String roleDescription, Integer roleStatus,
			Set<UserRole> userRoles, Set<RolePrivilege> rolePrivileges) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleStatus = roleStatus;
		this.userRoles = userRoles;
		this.rolePrivileges = rolePrivileges;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "roleID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "roleName", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleDescription", length = 50)
	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Column(name = "roleStatus")
	public Integer getRoleStatus() {
		return this.roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<RolePrivilege> getRolePrivileges() {
		return this.rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}