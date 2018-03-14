package com.xinwei.orderDb.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xinwei.orderDb.service.OrderService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderTest {
	
	@Autowired
	OrderService orderService;
	
	
	@Test
	public void creatOrderTest() {
//		OrderFlowDef orderFlowDef = orderService.s
	}

}
