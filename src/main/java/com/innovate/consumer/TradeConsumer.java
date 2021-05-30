package com.innovate.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovate.configuration.MessagingConfiguration;
import com.innovate.entity.Trade;
import com.innovate.repository.TradeRepository;

@Component
public class TradeConsumer {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@RabbitListener(queues = MessagingConfiguration.MATCH_QUEUE)
	public void  performTrade(Trade trade) throws Exception{		
		tradeRepository.save(trade);		
	}

}
