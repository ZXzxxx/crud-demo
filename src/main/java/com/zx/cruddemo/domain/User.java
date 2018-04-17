package com.zx.cruddemo.domain;
import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;


@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
public class User implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	protected Long id;

	protected String username;

	protected String password;


	@OneToOne
	@JoinColumn(name = "actor_id")
	protected Actor actor;

	@OneToMany(mappedBy = "user")
	protected Set<UserRole> userRole;

	public User(){
		super();
	}

	public User(Long id, String username, String password, Actor actor, Set<UserRole> userRole) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.actor = actor;
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}

