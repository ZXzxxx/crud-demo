package com.zx.cruddemo.domain;
import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zx.cruddemo.jsonDeserialize.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Calendar;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class Student extends Actor implements Serializable
{


	/**
	 *
	 @JsonSerialize(using = CustomDateSerializer.class)
	 private Calendar submitDate;
	 *
	 */

	@JsonSerialize( using = JsonDateSerializer.class)
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Calendar birth;

	/**
	 * 比如说年龄，可以根据当前年份和出生日期计算出来。
	 * 不需要被映射为数据库表字段
	 */
	@Transient
	private String age;

	@Column(name = "hometown",unique = true)
	private String birthPlace;


	/**
	 * 把图片直接存到数据库，而不是存地址
	 * @Lob 注解属性将被持久化为 Blog 或 Clob 类型。
	 * java.sql.Clob, Character[], char[] 和 java.lang.String 将被持久化为 Clob 类型.
	 * java.sql.Blob, Byte[], byte[] 和 serializable type 将被持久化为 Blob 类型。
	 * @Lob 持久化为Blob或者Clob类型,根据get方法的不同,自动进行Clob和Blob的转换
	 */

	@Lob
	@Column(columnDefinition = "LongBlob")
	private byte[] picture;

	/**
	 * 创建实例时，必需要有无参数构造方法
	 * 否则会执出实例化异常（InstantiationException）。
	 */
	public Student() {
		super();
	}


	/**
	 *  有参构造器    根据某些情况下  需要传的参数 再写
	 */


	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(Calendar birth) {
		this.birth = birth;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}

