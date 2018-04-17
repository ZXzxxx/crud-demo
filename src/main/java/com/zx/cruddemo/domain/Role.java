package com.zx.cruddemo.domain;
import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class Role implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	protected Long id;

	protected String roleName;


	@OneToMany(mappedBy = "role")
	protected Set<UserRole> userRole;


	@ManyToMany( mappedBy = "roles")
	private Set<Resource> resources;

	public Role(){
		super();
	}

	public Role(Long id, String roleName, Set<UserRole> userRole) {
		this.id = id;
		this.roleName = roleName;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}

