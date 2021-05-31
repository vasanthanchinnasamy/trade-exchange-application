package com.innovate.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.innovate.consumer.ExchangeConsumer;
import com.innovate.entity.Trade;
import com.innovate.model.Exchange;
import com.innovate.model.FetchOrder;
import com.innovate.model.FetchTrade;
import com.innovate.repository.TradeRepository;

@Service
public class ExchangeService {
	
	@Autowired
	private ExchangeConsumer exchangeConsumer;
	
	@Autowired
	private TradeRepository tradeRepository;
	
	/**
	 * getUnmatchedOrders fetch unmatched records based on Seller Code, Buyer Code, Stock Price when filters are passed
	 * fetch all unmatched records when no filters are passed.							
	 * @param fetchOrder
	 * @return
	 */  
	public List<Exchange> getUnmatchedOrders(FetchOrder fetchOrder){
		
		List<Exchange> currentExchangeList = exchangeConsumer.getExchangeList();		
		Stream<Exchange> exchangeStream = currentExchangeList.stream();
		
		if(!Objects.isNull(fetchOrder.getStockCode()))
			exchangeStream.filter(currentExchange->currentExchange.getStockCode().equals(fetchOrder.getStockCode()));
		
		if(!Objects.isNull(fetchOrder.getStockPrice()))
			exchangeStream.filter(currentExchange->currentExchange.getStockPrice().equals(fetchOrder.getStockPrice()));
		
		return exchangeStream.collect(Collectors.toList());
	}
	
	/**
	 * getTrades fetch trades based on Seller Code, Buyer Code, Stock Code, Trade Date when filters are passed.
	 * fetch all trades when no filters are passed.
	 * @param fetchTrade
	 * @return
	 */
	public List<Trade> getTrades(@RequestBody FetchTrade fetchTrade){		
		return tradeRepository.getTrades(fetchTrade);		
	}

	

}
