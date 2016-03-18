package controller;


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.Ops;

import Model.Stock;
import Model.StockSymbol;
import Model.StockType;
import Model.Trade;
import Model.TradeType;


public class Calc {

	static	int option = 0;
	static	int quit = 6 ;
	static  List<Trade>  trades ;
	static  Map<StockSymbol,Stock> stocks ;
	static Scanner scanner ;
	static Ops ops ;
	public static void start() {
		init();
		while (option != quit) {
			loadMenu ();
			System.out.print ("Please enter your input: ");
			int choice  = Integer.parseInt (getInput());
			option = choice;
			execute(option);
		}
		System.out.println("Thank you for using our Stocks calculator!");

	}

	private static void init() {
		System.out.println(" ----- MENU ----- ");

		trades = new ArrayList<Trade>();

		stocks = new HashMap<StockSymbol ,Stock> () ;

		stocks.put(StockSymbol.TEA, new Stock (StockSymbol.TEA, StockType.common,    0,  null, 100));
		stocks.put(StockSymbol.POP, new Stock (StockSymbol.POP ,StockType.common ,   8 , null, 100));
		stocks.put(StockSymbol.ALE, new Stock (StockSymbol.ALE ,StockType.common ,  23 , null,  60));
		stocks.put(StockSymbol.GIN, new Stock (StockSymbol.GIN ,StockType.preferred, 8 ,    2, 100));
		stocks.put(StockSymbol.JOE, new Stock (StockSymbol.JOE ,StockType.common,   13 , null, 250));

		scanner = new Scanner(new InputStreamReader(System.in));
		ops = new Ops ();
	}

	private static void execute(int option2) {

		switch (option2) { 
		case 1:
			calculateDividendYield();
			break;
		case 2:
			calculatePERatio();
			break;
		case 3:
			tradeRecord();
			break;
		case 4:
			calculateStockPrice();
			break;
		case 5:
			calculateGBCE();
			break;
		case 6:	
			break;
		}
	}

	private static void calculateGBCE() {

		double gbceIndex = ops.calculateGBCE(trades, stocks);
		if ( gbceIndex!= 0)
			System.out.println(" the GBCE All Share Index is : " + gbceIndex );
		else 
			System.out.println(" No trades registered " );
		return  ;

	}

	private static void calculateStockPrice() {

		System.out.print ("Please input type of share (TEA ,POP ,ALE ,GIN ,JOE) : ");
		String symb  =    getInput() ;
		StockSymbol ss = getSymbol(symb);
		double StockPrice = ops.calculateStockPrice(getLatestTradesOfSymbol(ss), stocks.get(ss));
		System.out.println(" ----- The Stock Prize is :   " + StockPrice);  
		return  ;
	}


	private static void tradeRecord() {
		// ----- warning - no input validation ------
		//3. record a trade : quantity of shares, buy or sell indicator and price
		System.out.print ("Please input type of share (TEA ,POP ,ALE ,GIN ,JOE) : ");
		String symb  =   getInput() ;

		System.out.print ("Please quantity of shares: ");
		int quantityOfShares  = Integer.parseInt (getInput());

		System.out.print ("Buy (1) or Sell (2): ");
		int buyOrSell  = Integer.parseInt (getInput());

		System.out.print ("Price: ");
		double price  = Double.parseDouble (getInput());

		trades.add( new Trade (new Date(), quantityOfShares, buyOrSell == 1 ? TradeType.BUY : TradeType.SELL , price, getSymbol(symb) ) );

		System.out.println(" ----- Trade recorded ----- ");

	}

	private static void calculatePERatio() {

		System.out.print ("Please input type of share (TEA ,POP ,ALE ,GIN ,JOE) : ");
		String symb  =    getInput() ;
		StockSymbol ss = getSymbol(symb);
		double peRatio = ops.calculatePERatio(stocks.get(ss), getLastTradeOfSymbol(ss));
		System.out.println( " -------- The P/E Ratio is : " + peRatio );
		return   ;

	}

	private static void calculateDividendYield() {

		System.out.print ("Please input type of share (TEA ,POP ,ALE ,GIN ,JOE) : ");
		String symb  =    getInput() ;
		StockSymbol ss = getSymbol(symb);
		double dividendYield = ops.calculateDividendYield(stocks.get(ss), getLastTradeOfSymbol(ss));
		System.out.println( " -------- Dividend Yield is : " + (dividendYield) ); 
		return ;
	}

	private static StockSymbol getSymbol(String symb) {
		for (StockSymbol  s: stocks.keySet() ) {
			if ( s.name().equals(symb)) 
				return s;
		}
		return null;
	}


	/**
	 * Gets the last trade of the given symbol
	 * @param StockSymbol s
	 * @return 
	 */
	private static Trade getLastTradeOfSymbol(StockSymbol s) {

		int length = trades.size() - 1 ;
		if (length >= 0)
			for (int i = length ; i >= 0; i--) {
				if ( s.equals(trades.get(i).getSymbol()) ) {
					return trades.get(i);
				}
			}
		return null;
	}

	/**
	 * Gets the trades of past 15 minutes
	 * @param symb
	 * @return
	 */
	private static List<Trade> getLatestTradesOfSymbol(StockSymbol symb) {

		int length = trades.size() - 1 ;
		List<Trade> tradeList = new ArrayList <Trade> () ;

		if (length >= 0) {
			Date aQuarterAgo = new Date (new Date().getTime() - 15*60*1000 ); // current time minus 15 min
			//System.out.println("Current our minus 15 min " + aQuarterAgo );
			for (Trade t : trades) {
				long diff = Math.abs(t.getTime().getTime() - aQuarterAgo.getTime());
				if (diff > 0 && t.getSymbol().equals(symb)) 
					tradeList.add(t);
			}
		}
		return tradeList;
	}


	private static String getInput() {
		String input = scanner.nextLine();
		return input;

	}

	private static void loadMenu() {

		System.out.println(" ----- MENU -----");
		System.out.println("1. Calculate the dividend yield");
		System.out.println("2. Calculate the P/E Ratio");
		System.out.println("3. Record a trade : quantity of shares, buy or sell indicator and price");
		System.out.println("4. Calculate Stock Price based on trades recorded in past 15 minutes");
		System.out.println("5. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks" );
		System.out.println("6. Exit");
		System.out.println(" ---------------- ");
	}

}
