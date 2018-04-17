package com.zx.cruddemo.domain;
import java.io.Serializable;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
public class UserRole implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;


	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User user;


	@ManyToOne
	@JoinColumn(name = "role_id")
	protected Role role;


	public UserRole(){
		super();
	}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}

