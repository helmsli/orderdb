package com.xinwei.orderDb.service.implTest;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.service.OrderDbService;
import com.xinwei.orderDb.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDbService orderDbService;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void insertOrderMainTest() {
		
//		ProcessResult processResult = orderService.createNewOrder(orderMain);
//		System.out.println(processResult.getRetCode());
	}

	@Test
	public void insertOrderMainWithContextTest() {
//		OrderMain orderMain = new OrderMain();
//		orderMain.setCatetory("1");
//		orderMain.setCurrentstatus(0);
//		orderMain.setCurrentstep("testaaa");
//		orderMain.setFlowid("testaaaaa");
//		orderMain.setIsfinished(0);
//		orderMain.setOrderid("bbbbbbbbbbbbbb");
//		orderMain.setOwnerkey("test");
//		orderMain.setParentordercategory("testaaa");
//		orderMain.setParentorderid("testaa");
//		orderMain.setUpdatetime(new Date());
//		Map<String, String> contexts = new HashMap<>(16);
//		contexts.put("0", "a");
//		contexts.put("1", "b");
//		contexts.put("2", "c");
//		ProcessResult processResult = orderService.createNewOrder(orderMain, contexts);
//		System.out.println(processResult.getRetCode());
	}

	@Test
	public void updateMainOrderTest() {
//		OrderMain orderMain = new OrderMain();
//		orderMain.setCatetory("1");
//		orderMain.setCurrentstatus(0);
//		orderMain.setCurrentstep("testbbb");
//		orderMain.setFlowid("testbbbbb");
//		orderMain.setIsfinished(0);
//		orderMain.setOrderid("bbbbbbbbbbbbbb");
//		orderMain.setOwnerkey("testbbb");
//		orderMain.setParentordercategory("testbbb");
//		orderMain.setParentorderid("testbb");
//		orderMain.setUpdatetime(new Date());
//		ProcessResult processResult = orderService.updateMainOrder(orderMain);
//		System.out.println(processResult.getRetCode());

	}

	@Test
	public void updateMainOrderStatusTest() {
//		OrderMain orderMain = new OrderMain();
//		// orderMain.setCatetory("1");
//		// orderMain.setCurrentstatus(0);
//		orderMain.setCurrentstep("testccc");
//		// orderMain.setFlowid("testbbbbb");
//		// orderMain.setIsfinished(0);
//		orderMain.setOrderid("bbbbbbbbbbbbbb");
//		orderMain.setOwnerkey("testbbb");
//		orderMain.setParentordercategory("testbbb");
//		orderMain.setParentorderid("testbb");
//		orderMain.setUpdatetime(new Date());
		ProcessResult processResult = orderService.updateMainOrderStatus("bbbbbbbbbbbbbb", 1, "2");
		System.out.println(processResult.getRetCode());
	}

	@Test
	public void configOrderFlowTest() {
//		OrderFlow orderFlow = new OrderFlow();
//		orderFlow.setContextData("test");
//		orderFlow.setCreatetime(new Date());
//		orderFlow.setCurrentstatus(1);
//		orderFlow.setDataKey("test");
//		orderFlow.setFlowid("test");
//		orderFlow.setOrderid("vvvvvvvvvvvvvv");
//		orderFlow.setOwnerkey("ownerkey");
//		orderFlow.setRetCode("RetCode");
//		orderFlow.setRetMsg("RetMsg");
//		orderFlow.setRetryTimes("retryTime");
//		orderFlow.setStepid("stepId");
//		orderFlow.setUpdatetime(new Date());
//		ProcessResult processResult = orderService.configOrderFlow(orderFlow);
//		System.out.println(processResult.getRetCode());
	}

	@Test
	public void getContextDataTest() {
		List<String> contextKey = new ArrayList<>();
		contextKey.add("aa");
		contextKey.add("bb");
		contextKey.add("cc");
		ProcessResult processResult = orderService.getContextData("vvvvvvvvvvvvvv", contextKey);
		System.out.println(processResult.getRetCode());
		System.out.println(processResult.getResponseInfo());
	}

	@Test
	public void putContextDataTest() {
		Map<String, String> contextdatas = new HashMap<>();
		contextdatas.put("1", "a");
		contextdatas.put("2", "b");
		contextdatas.put("3", "c");
		ProcessResult processResult = orderService.putContextData("vvvvvvvvvvvvvv", contextdatas);
		System.out.println(processResult.getRetCode());

	}

}
