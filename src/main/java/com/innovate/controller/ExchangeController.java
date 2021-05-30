package com.innovate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.entity.Trade;
import com.innovate.model.Exchange;
import com.innovate.model.FetchOrder;
import com.innovate.model.FetchTrade;
import com.innovate.repository.TradeRepository;
import com.innovate.service.ExchangeService;

@RequestMapping("/exchange")
@RestController
public class ExchangeController {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private ExchangeService exchangeService;
	
	
	
	/**
	 * getTrades fetch trades based on Seller Code, Buyer Code, Stock Code, Trade Date when filters are passed.
	 * fetch all trades when no filters are passed.
	 * @param fetchTrade
	 * @return
	 */
	@GetMapping("/getTrades")
	public ResponseEntity<List<Trade>> getTrades(@RequestBody FetchTrade fetchTrade){		
		return new ResponseEntity<>(tradeRepository.getTrades(fetchTrade),HttpStatus.OK);		
	}	 
	
	
	/**
	 * getUnmatchedOrders fetch unmatched records based on Seller Code, Buyer Code, Stock Price when filters are passed
	 * fetch all unmatched records when no filters are passed.							
	 * @param fetchOrder
	 * @return
	 */  
	@GetMapping("/getUnmatchedOrders")
	public ResponseEntity<List<Exchange>> getUnmatchedOrders(@RequestBody FetchOrder fetchOrder){		
		return new ResponseEntity<>(exchangeService.getUnmatchedOrders(fetchOrder),HttpStatus.OK);		
	}	
	
	

}
