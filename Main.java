package geekforgeek;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TradingPlatform tradingPlatform = new TradingPlatform();
        Scanner sc = new Scanner(System.in);

        tradingPlatform.loadFromFile("trades.txt");

                while (true) {
                    System.out.println("\n=== STOCK TRADING PLATFORM ===");
                    System.out.println("1. Add Stock");
                    System.out.println("2. Add User");
                    System.out.println("3. View Market");
                    System.out.println("4. Buy Stock");
                    System.out.println("5. Sell Stock");
                    System.out.println("6. View Portfolio");
                    System.out.println("7. Simulate Market");
                    System.out.println("8. Exit");
                    System.out.print("Choose: ");
                    int choice = sc.nextInt();
                    sc.nextLine(); // consume newline

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter Stock Symbol: ");
                            String symbol = sc.nextLine();
                            System.out.print("Enter Stock Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Starting Price: ");
                            double price = sc.nextDouble();
                            sc.nextLine();
                            tradingPlatform.addStock(symbol, name, price);
                            System.out.println("Stock added successfully");
                        }
                        case 2 -> {
                            System.out.print("Enter Username: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Starting Cash Balance: ");
                            double balance = sc.nextDouble();
                            sc.nextLine();
                            tradingPlatform.addUser(name, balance);
                            System.out.println("User added successfully");
                        }
                        case 3 -> {
                            tradingPlatform.displayMarket();
                        }
                        case 4 -> {
                            System.out.print("Enter Username: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Stock symbol to buy: ");
                            String sym = sc.nextLine();
                            System.out.print("Enter Stock Quantity: ");
                            int quantity= sc.nextInt();
                            sc.nextLine();
                            tradingPlatform.buyForUser(name, sym, quantity);
                            System.out.println("Stock Bought successfully");
                        }
                        case 5 -> {
                            System.out.print("Enter Username: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Stock symbol to sell: ");
                            String sym = sc.nextLine();
                            System.out.print("Enter Stock Quantity: ");
                            int quantity= sc.nextInt();
                            sc.nextLine();
                            tradingPlatform.sellForUser(name, sym, quantity);
                            System.out.println("Stock Sold successfully");
                        }
                        case 6 -> {
                            System.out.print("Enter Username: ");
                            String name = sc.nextLine();
                            Users user = tradingPlatform.findUser(name);
                            if (user != null) {
                                double value = user.getPortfolioValue(tradingPlatform.getStocks());
                                System.out.printf("Portfolio value: %2f  | Cash: %2f | Total: %2f%n",
                                        value, user.getCashBalance(), value + user.getCashBalance());
                            }else {
                                System.out.println("User not found");
                            }
                        }
                        case 7 -> {
                            tradingPlatform.updateAllPrices();
                            System.out.println("Market Prices updated");
                        }
                        case 8 -> {
                                tradingPlatform.saveToFile("trade.txt");
                                System.out.println("Goodbye!");
                                sc.close();
                                return;
                        }

                        default -> System.out.println("Invalid choice!");
                    }
                }
            }
}