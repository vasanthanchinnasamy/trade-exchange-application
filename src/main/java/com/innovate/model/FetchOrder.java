package com.innovate.model;

import java.math.BigDecimal;

public class FetchOrder {
	
	private String stockCode;
	
	private BigDecimal stockPrice;	
	

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

	public FetchOrder() {
		super();
	}

	public FetchOrder(String stockCode, BigDecimal stockPrice) {
		super();
		this.stockCode = stockCode;
		this.stockPrice = stockPrice;
	}
	
	

}
