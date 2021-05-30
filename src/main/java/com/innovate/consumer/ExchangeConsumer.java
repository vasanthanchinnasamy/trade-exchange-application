package com.innovate.consumer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.innovate.configuration.MessagingConfiguration;
import com.innovate.entity.Trade;
import com.innovate.model.Exchange;

@Component
public class ExchangeConsumer {
	
private List<Exchange> currentExchangeList = new LinkedList<>();

@Autowired
private RabbitTemplate template;
	
	/**
	 * doMatch consumes order messages and does matching with already existing orders
	 * 
	 * @param exchange
	 * @throws Exception
	 */
	@RabbitListener(queues = MessagingConfiguration.MATCH_QUEUE)
	public void  doMatch(@RequestBody Exchange exchange)  throws Exception{
		
		Optional<Exchange> match = currentExchangeList.stream().filter(currentExchange->
				   currentExchange.getExchangeType()!=exchange.getExchangeType()
				&& currentExchange.getStockPrice().equals(exchange.getStockPrice())
				&& currentExchange.getStockCode().equals(exchange.getStockCode())).findFirst();
		
		match.ifPresentOrElse(matchedItem->{
			
			Trade trade = new Trade(
					 matchedItem.getExchangeType() == 2?matchedItem.getCustomerCode():exchange.getCustomerCode()
					,matchedItem.getExchangeType() == 1?matchedItem.getCustomerCode():exchange.getCustomerCode()
					,matchedItem.getStockCode(),matchedItem.getStockPrice(),LocalDate.now());
			
			template.convertAndSend(MessagingConfiguration.EXCHANGE, MessagingConfiguration.MATCH_ROUTING_KEY, trade);
			
			currentExchangeList.remove(matchedItem);			
		}
		,()->currentExchangeList.add(exchange));		
	}
	
	public List<Exchange>  getExchangeList(){
		return currentExchangeList;
	}

}
