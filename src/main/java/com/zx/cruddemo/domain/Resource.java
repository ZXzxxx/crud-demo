package com.zx.cruddemo.domain;
import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class Resource implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;

	private String resourceDescription;

	/**
	 * Resource维护中间表
	 */
	@ManyToMany
	@JoinTable(name = "role_resource",
			joinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private Set<Role> roles;

	public Resource() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}

