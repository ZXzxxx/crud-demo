package com.zx.cruddemo.jpaRepository;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


//加上@NoRepositoryBean注解，SpringDataJpa在启动时就不会去实例化这个接口
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

//里面写一些自定义的方法
}