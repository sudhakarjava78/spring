package com.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/cfg/applicationContext.xml");

		Order order = (Order) context.getBean("order");
		order.getOrderDetails();
	}
}
