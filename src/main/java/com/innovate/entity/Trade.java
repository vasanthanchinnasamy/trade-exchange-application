package com.innovate.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tradeId;

	private String sellerCode;
	
	private String buyerCode; 	
	
	private String stockCode;
	
	private BigDecimal stockPrice;
	
	private LocalDate tradeDate;

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Trade(String sellerCode, String buyerCode, String stockCode, BigDecimal stockPrice,
			LocalDate tradeDate) {
		super();
		this.sellerCode = sellerCode;
		this.buyerCode = buyerCode;
		this.stockCode = stockCode;
		this.stockPrice = stockPrice;
		this.tradeDate = tradeDate;
	}

	public Trade() {
		super();
	}
	
	

}
