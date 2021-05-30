package com.innovate;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.innovate.consumer.ExchangeConsumer;
import com.innovate.controller.ExchangeController;
import com.innovate.entity.Trade;
import com.innovate.model.Exchange;
import com.innovate.model.FetchOrder;
import com.innovate.model.FetchTrade;

@SpringBootTest
class TradeExchangeApplicationTests {
	
	@Autowired
	private ExchangeController exchangeController;
	
	@Autowired
	private ExchangeConsumer exchangeConsumer;

	
	@Test
	void testExchange() throws Exception {
		Exchange exchange1 = new Exchange("Party A", 2, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange1);
		Exchange exchange2 = new Exchange("Party B", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange2);
		
		Boolean exchange1NotExist = !isExist(exchangeConsumer.getExchangeList(), exchange1);
		Boolean exchange2NotExist = !isExist(exchangeConsumer.getExchangeList(), exchange2);
		
		Assert.isTrue(exchange1NotExist&&exchange2NotExist, "Exchanges are not matched properly");
		
	}
	
	@Test
	void testExchangeWithOrder() throws Exception {
		Exchange exchange1 = new Exchange("Party A", 2, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange1);
		Exchange exchange2 = new Exchange("Party B", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange2);
		Exchange exchange3 = new Exchange("Party C", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange3);
		
		Boolean exchange1NotExist = !isExist(exchangeConsumer.getExchangeList(), exchange1);
		Boolean exchange2Exist = isExist(exchangeConsumer.getExchangeList(), exchange2);
		Boolean exchange3NotExist = !isExist(exchangeConsumer.getExchangeList(), exchange3);
		
		Assert.isTrue(exchange1NotExist && exchange3NotExist && exchange2Exist
													, "Exchanges are matched in wrong order");
	}
	
	@Test
	void testGetUnmatchedOrders() throws Exception {
		
		Exchange exchange1 = new Exchange("Party A", 2, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange1);
		Exchange exchange2 = new Exchange("Party A", 2, "INFY", BigDecimal.valueOf(600));
		exchangeConsumer.doMatch(exchange2);
		Exchange exchange3 = new Exchange("Party A", 2, "GOOG", BigDecimal.valueOf(500));
		exchangeConsumer.doMatch(exchange3);
		Exchange exchange4 = new Exchange("Party B", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange4);
		Exchange exchange5 = new Exchange("Party C", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange5);
		Exchange exchange6 = new Exchange("Party C", 1, "INFY", BigDecimal.valueOf(600));
		exchangeConsumer.doMatch(exchange6);
		
		List<Exchange> unmatchedOrdersList = exchangeController.getUnmatchedOrders(new FetchOrder()).getBody();
		
		Boolean exchange1NotExist = !isExist(unmatchedOrdersList, exchange1);
		Boolean exchange2NotExist = !isExist(unmatchedOrdersList, exchange2);
		Boolean exchange3Exist = isExist(unmatchedOrdersList, exchange3);		
		Boolean exchange4NotExist = !isExist(unmatchedOrdersList, exchange4);
		Boolean exchange5Exist = isExist(unmatchedOrdersList, exchange5);
		Boolean exchange6NotExist = !isExist(unmatchedOrdersList, exchange3);
		
		Assert.isTrue(exchange1NotExist && exchange2NotExist && exchange3Exist 
				&& exchange4NotExist && exchange5Exist && exchange6NotExist
				, "Unmatched records are fetched wrong");
	}
	
	@Test
	void testGetTrades() throws Exception {
		
		Exchange exchange1 = new Exchange("Party A", 2, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange1);
		Exchange exchange2 = new Exchange("Party A", 2, "INFY", BigDecimal.valueOf(600));
		exchangeConsumer.doMatch(exchange2);
		Exchange exchange3 = new Exchange("Party A", 2, "GOOG", BigDecimal.valueOf(500));
		exchangeConsumer.doMatch(exchange3);
		Exchange exchange4 = new Exchange("Party B", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange4);
		Exchange exchange5 = new Exchange("Party C", 1, "IBM", BigDecimal.valueOf(110));
		exchangeConsumer.doMatch(exchange5);
		Exchange exchange6 = new Exchange("Party C", 1, "INFY", BigDecimal.valueOf(600));
		exchangeConsumer.doMatch(exchange6);
		FetchTrade fetchTrade = new FetchTrade();	fetchTrade.setSellerCode("Party A");
		List<Trade> tradeList = exchangeController.getTrades(fetchTrade).getBody();
		
		Assert.isTrue(tradeList.size() == 2	, "Trades are not fetched properly");
	}
	
	
	Boolean isExist(List<Exchange> currentExchangeList,Exchange exchange) {
		exchangeConsumer.getExchangeList();
		return currentExchangeList.stream().anyMatch(currentExchange->
		currentExchange.getCustomerCode().equals(exchange.getCustomerCode()) 
		&& currentExchange.getStockCode().equals(exchange.getStockCode())
		&& currentExchange.getStockPrice().equals(exchange.getStockPrice())
		&& currentExchange.getExchangeType() == exchange.getExchangeType());
	}

}
