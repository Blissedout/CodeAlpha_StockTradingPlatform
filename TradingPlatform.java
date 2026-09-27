package geekforgeek;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class TradingPlatform {

    private ArrayList<Stock> stocks = new ArrayList<>();
    private ArrayList<Users> users = new ArrayList<>();

    public TradingPlatform() {
        this.stocks = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addStock(String symbol, String name, double price){
        stocks.add(new Stock(symbol, price));
    }

    public  void  addUser(String name, double startingCash){
        users.add(new Users(name, startingCash));
    }

    public Users findUser(String name){
       for (Users user : users){
           if (user.getName().equals(name)){
               return user;
           }
       }
       return null;
    }

    public Stock findStock(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) {
                return stock;
            }

        }
        return null;
    }

    public  void displayMarket(){
        for (Stock stock : stocks){
            System.out.println(stock);
        }
    }

    public void updateAllPrices(){
        for (Stock stock : stocks){
            stock.updatePrice();
        }
    }

    public void buyForUser(String userName, String symbol, int quantity){
        Users user = findUser(userName);
        Stock stock = findStock(symbol);
        if (user != null && stock != null){
            user.buyStock(stock,  quantity);
        }else {
            System.out.println("User or Stock not found");
        }
    }

    public void sellForUser(String userName, String symbol, int quantity){
        Users user = findUser(userName);
        Stock stock = findStock(symbol);
        if (user != null && stock != null){
            user.sellStock(stock, quantity);
        }else {
            System.out.println("User or Stock not found");
        }

    }

    private String portfolioToString(Users users){
        StringBuilder sb = new StringBuilder();
        for (String symbol : users.getPortfolio().keySet()){
            sb.append(symbol).append(":").append(users.getPortfolio().get(symbol)).append(";");
        }
        return sb.toString();
    }

    private String transactionToString(Users users){
        StringBuilder sb = new StringBuilder();
        for (Transaction t : users.getTransactionHistory()){
            sb.append(t.getStockSymbol()).append("@")
                    .append(t.getQuantity()).append("@")
                    .append(t.getPricePerShare()).append("@")
                    .append(t.getType()).append("@").append(t.getTimeStamp())
                    .append("|");
        }
        return sb.toString();
    }

    public void saveToFile(String filename){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            for (Users user : users){
                String line = user.getName() + "," + transactionToString(user);
                writer.println(line);
            }
        }catch (IOException e){
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename){
        File file = new File(filename);
        if (!file.exists()){
            return;
        }
        try(Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",", -1);
                String name = parts[0];
                double cashBalance = Double.parseDouble(parts[1]);
                String portfolioData = parts.length > 2 ? parts[2] : "";
                String transactionData = parts.length > 3 ? parts[3] : "";

                Users user = new Users(name, cashBalance);

                if (!portfolioData.isEmpty()){
                    for (String entry : portfolioData.split(";")){
                        String[] entryParts = entry.split(":");
                        user.getPortfolio().put(entryParts[0], Integer.parseInt(entryParts[1]));
                    }
                }

                if (!transactionData.isEmpty()){
                    for (String entry : transactionData.split("\\|")){
                        String[] entryParts = entry.split("@");
                        Transaction t = new Transaction(entryParts[0],
                                Integer.parseInt(entryParts[1]),
                                Double.parseDouble(entryParts[2]),
                                entryParts[3],
                                LocalDateTime.parse(entryParts[4]));
                        user.getTransactionHistory().add(t);
                    }
                }

                users.add(user);
            }
        }catch (IOException e){
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }
}
