package com.zx.cruddemo;

import com.zx.cruddemo.jpaRepository.MyRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 没有配置数据源时，要加上这个
 * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
 *
 */

@RestController
@EnableTransactionManagement
@EnableAutoConfiguration
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
