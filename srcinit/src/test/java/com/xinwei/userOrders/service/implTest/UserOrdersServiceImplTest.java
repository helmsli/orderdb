package com.xinwei.userOrders.service.implTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.domain.ContextRequest;
import com.xinwei.orderDb.domain.OrderMain;
import com.xinwei.orderDb.service.OrderDbService;
import com.xinwei.userOrders.service.UserOrdersService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserOrdersServiceImplTest {

	@Autowired
	UserOrdersService userOrdersService;

	@Autowired
	OrderDbService orderDbService;

	@Test
	public void insertUserOrdersTest() {
		// UserOrders userOrders = new UserOrders();
		// userOrders.setCatetory("c");
		// userOrders.setCreateTime(new Date());
		// userOrders.setCurrentStatus(0);
		// userOrders.setOrderId("123132131321");
		// userOrders.setOwnerKey("ownerKey");
		// ProcessResult processResult = userOrdersService.insertUserOrders(userOrders);
		// System.out.println(processResult);
		Map<String, String> map = new HashMap<>(16);
		map.put("1", "a");
		OrderMain orderMain = new OrderMain();
		orderMain.setCatetory("1");
		orderMain.setCurrentStatus(0);
		orderMain.setCurrentStep("testaaa");
		orderMain.setFlowId("testaaaaa");
		orderMain.setIsFinished(0);
		orderMain.setOwnerKey("test");
		orderMain.setParentOrderCategory("testaaa");
		orderMain.setOrderId("abcdabcdabcdasdadasdas");
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(3000);
		requestFactory.setReadTimeout(3000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		ContextRequest contextRequest = new ContextRequest();
		contextRequest.setOrderMain(orderMain);
		contextRequest.setContextDatas(map);
		ProcessResult processResult = restTemplate.postForObject(
				"http://localhost:8088/orderDb/1/abcdabcdabcdasdadasdas/addOrderMain", contextRequest,
				ProcessResult.class);
		System.out.println(processResult);
	}

}
