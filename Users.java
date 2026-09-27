package geekforgeek;

import java.util.ArrayList;
import java.util.HashMap;

public class Users {

    private String name;
    private double cashBalance;
    private  HashMap<String, Integer> portfolio;
    private  ArrayList<Transaction> transactionHistory;

    public Users(String name, double cashBalance){
        this.name = name;
        this.cashBalance = cashBalance;
        this.portfolio = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public HashMap<String, Integer> getPortfolio() {
        return portfolio;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void buyStock(Stock stock, int quantity){
        double cost = stock.getPrice() * quantity;
        if (cashBalance >= cost){
          cashBalance -= cost;

            int currentShares = portfolio.getOrDefault(stock.getSymbol(), 0);
            portfolio.put(stock.getSymbol(), currentShares + quantity);

            Transaction t = new Transaction(stock.getSymbol(), quantity, stock.getPrice(), "BUY");
            transactionHistory.add(t);
        }else {
            System.out.println("Insufficient Balance!");
        }

    }

    public void sellStock(Stock stock, int quantity){
        double sell = stock.getPrice() * quantity;
        if (portfolio.getOrDefault(stock.getSymbol(), 0) >= quantity){
            cashBalance += sell;

            int currentShares = portfolio.getOrDefault(stock.getSymbol(), 0);
            portfolio.put(stock.getSymbol(), currentShares - quantity);

            Transaction t = new Transaction(stock.getSymbol(), quantity, stock.getPrice(), "SELL");
            transactionHistory.add(t);
        }else {
            System.out.println("Error: Not Enough Shares To Sell");
        }
    }

    public double findStockPrice(String symbol, ArrayList<Stock> allStock){
        for (Stock stock : allStock){
            if (stock.getSymbol().equals(symbol)){
               return stock.getPrice();
            }
        }
        return 0;
    }
    public double getPortfolioValue(ArrayList<Stock> allStock){
       double total = 0;
        for (String symbol : portfolio.keySet()){
            int shares = portfolio.get(symbol);
            double price = findStockPrice(symbol, allStock);
            total +=  shares * price;
        }
        return  total;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", cashBalance=" + cashBalance +
                '}';
    }
}
