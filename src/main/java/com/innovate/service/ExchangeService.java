package com.innovate.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.consumer.ExchangeConsumer;
import com.innovate.model.Exchange;
import com.innovate.model.FetchOrder;

@Service
public class ExchangeService {
	
	@Autowired
	private ExchangeConsumer exchangeConsumer;
	
	public List<Exchange> getUnmatchedOrders(FetchOrder fetchOrder){
		
		List<Exchange> currentExchangeList = exchangeConsumer.getExchangeList();		
		Stream<Exchange> exchangeStream = currentExchangeList.stream();
		
		if(!Objects.isNull(fetchOrder.getStockCode()))
			exchangeStream.filter(currentExchange->currentExchange.getStockCode().equals(fetchOrder.getStockCode()));
		
		if(!Objects.isNull(fetchOrder.getStockPrice()))
			exchangeStream.filter(currentExchange->currentExchange.getStockPrice().equals(fetchOrder.getStockPrice()));
		
		return exchangeStream.collect(Collectors.toList());
	}

	

}
