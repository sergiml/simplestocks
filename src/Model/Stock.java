package Model;

public class Stock {
 
	public Stock(StockSymbol symbol, StockType type, Integer lastDividend,
			Integer fixedDividend, Integer parValue) {
		super();
		Symbol = symbol;
		Type = type;
		LastDividend = lastDividend;
		FixedDividend = fixedDividend;
		ParValue = parValue;
	}
	
	StockSymbol Symbol ;
	StockType Type;
	Integer LastDividend;
	Integer FixedDividend;
	Integer ParValue;
	double price;
	
	public StockSymbol getSymbol() {
		return Symbol;
	}
	public void setSymbol(StockSymbol symbol) {
		Symbol = symbol;
	}
	public StockType getType() {
		return Type;
	}
	public void setType(StockType type) {
		Type = type;
	}
	public Integer getLastDividend() {
		return LastDividend;
	}
	public void setLastDividend(Integer lastDividend) {
		LastDividend = lastDividend;
	}
	public Integer getFixedDividend() {
		return FixedDividend;
	}
	public void setFixedDividend(Integer fixedDividend) {
		FixedDividend = fixedDividend;
	}
	public Integer getParValue() {
		return ParValue;
	}
	public void setParValue(Integer parValue) {
		ParValue = parValue;
	}
	
	
}
