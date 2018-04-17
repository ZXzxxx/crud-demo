package com.zx.cruddemo.domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Embeddable;

/**
 * 联系方式
 *
 * 是个内嵌对象
 * 不映射在数据库表中的实体类，嵌在Entity类中作为属性存在
 * 然后在引用该类作为属性的上面加上注解@Embedded
 */

@Embeddable
@DynamicUpdate(true)
@DynamicInsert(true)
public class Contact
{

	protected Integer qq;

	protected String email;

	public Contact(){
		super();
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

