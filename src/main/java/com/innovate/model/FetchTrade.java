package com.innovate.model;

import java.time.LocalDate;

public class FetchTrade {
	
	private String sellerCode;
	
	private String buyerCode; 	
	
	private String stockCode;
	
	private LocalDate tradeDate;

	public FetchTrade() {
		super();
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

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public FetchTrade(String sellerCode, String buyerCode, String stockCode, LocalDate tradeDate) {
		super();
		this.sellerCode = sellerCode;
		this.buyerCode = buyerCode;
		this.stockCode = stockCode;
		this.tradeDate = tradeDate;
	}	

}
