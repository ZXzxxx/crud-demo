package com.zx.cruddemo.domain;
import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zx.cruddemo.jsonDeserialize.SchoolDeserialize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 */
 
@javax.persistence.Entity
@DynamicUpdate(true)
@DynamicInsert(true)
//@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Teacher extends Actor implements Serializable
{

	/**
	 * 多对一关系
	 * 外键设在多端
	 *
	 * optional属性的默认值是true。
	 * optional 属性实际上指定关联类与被关联类的join 查询关系
	 * 如optional=false 时join 查询关系为inner join  [等值查询]
	 * optional=true 时join 查询关系为left join  [左连接查询]
	 * https://www.cnblogs.com/assasion/p/7768931.html
	 *
	 *
	 * 外键置空
	 * http://blog.weiqinxue.cn/blogs/index.php/UserExt/TopicsDetail/UserID/2/NoteID/72
	 *
	 */

	//zhujie
	@ManyToOne
	@JoinColumn(name = "school_id")
	@JsonDeserialize( using = SchoolDeserialize.class)
	private School school;


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Teacher(){
		super();
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}

