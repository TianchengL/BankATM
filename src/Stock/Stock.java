package Stock;

import java.io.Serializable;

public class Stock implements Serializable {
    private final String name;
    private double price;
    private int amount;
    public boolean isSold;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Stock(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        isSold = false;
    }

    public void deductAmount(int amount) {
        this.amount -= amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setSold() {
        if(amount==0) {
            isSold = true;
        }
    }

    @Override
    public String toString() {
        return "Stock" +
                 name  +
                ", price=" + price ;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

