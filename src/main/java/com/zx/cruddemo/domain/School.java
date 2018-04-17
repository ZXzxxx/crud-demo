package com.zx.cruddemo.domain;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zx.cruddemo.jsonDeserialize.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
public class School implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;

	@JsonProperty(value = "value") //自定义json名
	private String description;

	/**
	 *  序列化的时候忽略该属性
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "school")
	private Set<Teacher> teachers;

	public School() {
	}

	public School(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
}

