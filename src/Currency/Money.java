package Currency;

import java.io.Serializable;

//represent amount of money
public class Money implements Serializable {

    private double amount;

    public Money(double amount){
        this.amount = amount;
    }

    //remove certain amount of money
    public boolean deductMoney(double amount){
        if(this.amount>amount){
            this.amount -= amount;
            return true;
        }
        return false;
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

    @Override
    public String toString() {
        return ""+amount;
    }
}
