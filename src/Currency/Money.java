package Currency;

import java.io.Serializable;

//represent amount of money
public class Money implements Serializable {

    private double amount;

    public Money(double amount){
        this.amount = amount;
    }

    //remove certain amount of money
    public void deductMoney(double amount){
        this.amount -= amount;
    }

    //add certain amount of money
    public void addMoney(double amount){
        this.amount += amount;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
