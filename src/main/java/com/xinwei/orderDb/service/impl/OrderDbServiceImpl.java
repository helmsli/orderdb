package com.xinwei.orderDb.service.impl;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xinwei.nnl.common.domain.JsonRequest;
import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.domain.OrderFlow;
import com.xinwei.orderDb.domain.OrderMain;
import com.xinwei.orderDb.domain.OrderMainContext;
import com.xinwei.orderDb.domain.StepJumpingRequest;
import com.xinwei.orderDb.service.OrderDbService;
import com.xinwei.userOrders.domain.UserOrders;

@Service
public class OrderDbServiceImpl implements OrderDbService {

	private SimpleClientHttpRequestFactory initRequestFactory() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(3000);
		requestFactory.setReadTimeout(3000);
		return requestFactory;
	}

	@Override
	public ProcessResult insertNewOrderMain(String url, OrderMainContext orderMainContext) {

		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
//		if (!contextDatas.isEmpty()) {
//			contextRequest.setContextDatas(contextDatas);
//			processResult = restTemplate.postForObject(url, orderMainContext, ProcessResult.class);
//			return processResult;
//		}
		processResult = restTemplate.postForObject(url, orderMainContext, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult updateOrderFlowStatus(String url, OrderFlow orderFlow) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, orderFlow, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult updateOrderFlowStepJumping(String url, OrderFlow nextOrderFlow, OrderFlow preOrderFlow,
			int nextOrderAutoRun, int preOrderAutoRun) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		StepJumpingRequest stepJumpingRequest = new StepJumpingRequest();
		stepJumpingRequest.setNextOrderAutoRun(nextOrderAutoRun);
		stepJumpingRequest.setNextOrderFlow(nextOrderFlow);
		stepJumpingRequest.setPreOrderAutoRun(preOrderAutoRun);
		stepJumpingRequest.setPreOrderFlow(preOrderFlow);
		processResult = restTemplate.postForObject(url, stepJumpingRequest, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult suspendOrRestartOrderFlow(String url, OrderFlow orderFlow) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, orderFlow, ProcessResult.class);

		return processResult;
	}

	@Override
	public ProcessResult updateOrderMain(String url, OrderMain orderMain) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, orderMain, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult getOrderContextData(String url, JsonRequest jsonRequest) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, jsonRequest, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult putOrderContextData(String url, OrderMainContext orderMainContext) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, orderMainContext, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult selectOrderDefByCategory(String url) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, null, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult selectOrderStepDefsByCategory(String url) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, null, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult selectUserOrdersByOrderId(String url, UserOrders userOrders) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, userOrders, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult selectUserOrdersByOrderStatus(String url, UserOrders userOrders) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, userOrders, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult updateUserOrdersStatus(String url, UserOrders userOrders) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, userOrders, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult insertUserOrders(String url, UserOrders userOrders) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, userOrders, ProcessResult.class);
		return processResult;
	}

	@Override
	public ProcessResult deleteUserOrders(String url) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(initRequestFactory());
		ProcessResult processResult = new ProcessResult();
		processResult = restTemplate.postForObject(url, null, ProcessResult.class);
		return processResult;
	}
	
	

}
