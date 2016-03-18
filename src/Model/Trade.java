package Model;

import java.util.Date;


public class Trade {

	public Trade(Date date, int quantityOfShares2, TradeType tradeType,
			double price2, StockSymbol symbol2) {
		time = date ;
		QuantityOfShares = quantityOfShares2;
		operationType = tradeType;
		price = price2;
		symbol = symbol2 ;
	}
	
	Date time ;
	int QuantityOfShares ;
	TradeType operationType ;
	Double price;
	StockSymbol symbol;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getQuantityOfShares() {
		return QuantityOfShares;
	}
	public void setQuantityOfShares(int quantityOfShares) {
		QuantityOfShares = quantityOfShares;
	}
	public TradeType getOperationType() {
		return operationType;
	}
	public void setOperationType(TradeType operationType) {
		this.operationType = operationType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public StockSymbol getSymbol() {
		return symbol;
	}
	public void setSymbol(StockSymbol symbol) {
		this.symbol = symbol;
	}
}
