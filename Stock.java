package geekforgeek;

public class Stock {

    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, double price){
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(){
        double change = (Math.random() - 0.5) * 10;
        price += change;
        if (price<0)
            price = 0;
    }

    @Override
    public String toString() {
        return String.format(
                "Symbol: %s |Name: %s |  Price: %s |",
                symbol, name, price);
    }
}
