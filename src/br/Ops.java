package br;

import java.util.List;
import java.util.Map;

import Model.Stock;
import Model.StockSymbol;
import Model.StockType;
import Model.Trade;

public class Ops {

	public   double calculateGBCE(List<Trade>  tds ,  Map<StockSymbol,Stock> stocks ) {

		double gbceIndex = 0 ;
		int i = 0 ;
		for (StockSymbol s : stocks.keySet()) {
			double stockPrice = calculateStockPrice(tds,stocks.get(s));
			if (gbceIndex == 0) { 
				gbceIndex = stockPrice;
			} else {
				gbceIndex = gbceIndex * stockPrice ;
			}
			i++;
		}
		return Math.pow (gbceIndex, 1/i);

	}

	public   double calculateStockPrice( List<Trade>  tds ,  Stock  stock  ) {

		double StockPrice;
		double sum = 0 ;
		int quantity = 0;
		 
		if (tds.size()>0) {
			for (Trade t : tds) {
				sum = sum + t.getPrice() * t.getQuantityOfShares();
				quantity = quantity + t.getQuantityOfShares();
			}
			StockPrice = sum /quantity ;
		} else	
			StockPrice = stock.getParValue() ;

		return StockPrice;

	}

	public   double calculatePERatio(Stock  st , Trade tr) {

		double peRatio ;
		//Stock st = stocks.get(s);
		//Trade tr = getLastTradeOfSymbol(s);
		
		if (st.getLastDividend() != 0 ) 
			if (tr != null) {	
				peRatio =  tr.getPrice() / st.getLastDividend() ;		 
			} else {
				// no trades of same symbol, calculate with par value
				peRatio =  st.getParValue() / st.getLastDividend() ; 
			}	
		else 
			peRatio = 0 ;
		return peRatio ;

	}

	public   double calculateDividendYield(Stock  st , Trade tr) {


		Double dividendYield ;
		//Stock st = stocks.get(s);
		//Trade tr = getLastTradeOfSymbol(s);

		if (tr != null) {

			// preferred
			if ( StockType.preferred.equals(st.getType().name())) {
				dividendYield =  st.getFixedDividend() * st.getParValue() / tr.getPrice() * 100 ;					 
			} else {
				// common
				dividendYield =   st.getLastDividend()/tr.getPrice()  ;
			}	
		} else {
			// no trades of same symbol, calculate with par value
			// preferred
			if ( StockType.preferred.equals(st.getType().name())) {
				dividendYield =  (double) (st.getFixedDividend() / 100);
			} else {
				// common
				dividendYield =    ((double)st.getLastDividend())/((double)st.getParValue());
			}
		}

		return dividendYield;
	}

	

}
