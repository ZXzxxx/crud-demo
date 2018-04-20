package com.zx.cruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 没有配置数据源时，要加上这个
 * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
 * @EnableCaching 注解配置启用缓存，自动配置配置文件的配置信息进行条件注入缓存所需实例
 */

@RestController
//@EnableTransactionManagement
//@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
//@EnableJpaRepositories(
//		basePackages = "com.zx.cruddemo.dao",
//		repositoryFactoryBeanClass = MyRepositoryFactoryBean.class)
public class CruddemoApplication {


	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
}
