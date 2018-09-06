package com.xinwei.orderDb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan({"com.xinwei.orderDb.mapper","com.xinwei.userOrders.mapper","com.xinwei.userOrder.mapper","com.xinwei.number.mapper"})
@ComponentScan("com.xinwei.orderDb, com.xinwei.userOrders,com.xinwei.userOrder,com.xinwei.number")

// @ImportResource ({ "classpath:hessian/hessian-client.xml",
// "classpath:hessian/hessian-server.xml" })

public class OrderDbApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SpringApplication.run(OrderDbApplication.class, args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
