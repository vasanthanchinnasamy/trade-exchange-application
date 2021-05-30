package com.innovate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovate.entity.Trade;
import com.innovate.model.FetchTrade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
	
	public List<Trade> getBySellerCodeOrBuyerCodeOrStockCodeOrTradeDateOrderByTradeDateDesc(
			String sellerCode,String buyerCode,String stockCode,LocalDate tradeDate);
	
	default public List<Trade> getTrades(FetchTrade trade){
        return getBySellerCodeOrBuyerCodeOrStockCodeOrTradeDateOrderByTradeDateDesc(
        		trade.getSellerCode(),trade.getBuyerCode(),trade.getStockCode(),trade.getTradeDate());
    }

}
