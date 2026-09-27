package geekforgeek;

import java.time.LocalDateTime;

public class Transaction {

    private String stockSymbol;
    private int quantity;
    private double pricePerShare;
    private String type;
    private LocalDateTime timeStamp;

    public Transaction(String stockSymbol, int quantity, double pricePerShare, String type) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.type = type;
        this.timeStamp = LocalDateTime.now();
    }

    public Transaction(String stockSymbol, int quantity, double pricePerShare, String type, LocalDateTime timeStamp){
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.type = type;
        this.timeStamp = timeStamp;
    }


    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "stockSymbol='" + stockSymbol + '\'' +
                ", quantity=" + quantity +
                ", pricePerShare=" + pricePerShare +
                ", type='" + type + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }


}
