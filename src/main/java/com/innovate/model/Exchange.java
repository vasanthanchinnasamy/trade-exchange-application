package com.innovate.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Exchange implements Serializable{
	
	private static final long serialVersionUID = 1234567L;
	
	private String customerCode;
	
	private Integer exchangeType;
	
	private String stockCode;
	
	private BigDecimal stockPrice;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Integer getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(Integer exchangeType) {
		this.exchangeType = exchangeType;
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

	public Exchange(String customerCode, Integer exchangeType, String stockCode, BigDecimal stockPrice) {
		super();
		this.customerCode = customerCode;
		this.exchangeType = exchangeType;
		this.stockCode = stockCode;
		this.stockPrice = stockPrice;
	}

	public Exchange() {
		super();
	}	

}
