package com.zx.cruddemo.domain;
import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 多表继承策略：
 * SINGLE_TABLE（单一表继承---1个表）/
 * JOINED_TABLE（注册表策略---父表[共有属性]和所有的子类表[只有自己的属性]）/
 * TABLE_PER_CLASS（每个类表策略--父类里有共有属性和子类各自属性，子类有共有属性和自己的属性）
 *
 *
 */
 
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor implements Serializable
{

	/**
	 *
	 * 主键约束
	 * TABLE/IDENTITY/SEQUENCE/AUTO

	 * 因为TABLE_PER_CLASS策略每个表都是单独的，没有并且各表的主键没有任何关系
	 * 所以不能使用GenerationType.AUTO或GenerationType.IDENTITY主键生成策略
	 * 可以使用GenerationType.TABLE。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Long id;

	protected String name;

	protected String no;


	@JsonIgnore
	@Embedded
	private Contact contact;

	/**
	 * 可以mappedBy
	 * 也可以@JoinColum
	 * @JoinColumn(referencedColumnName = "actor_name") 这是将外键关联到其他表的非空字段上
	 */
	@JsonIgnore
	@OneToOne(mappedBy = "actor")
	protected User user;


	public Actor(){
		super();
	}

	public Actor(String name, String no, Contact contact, User user) {
		this.name = name;
		this.no = no;
		this.contact = contact;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

