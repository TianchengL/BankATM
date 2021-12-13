package Transaction;

import User.User;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private final String memo;
    private final double amount;
    private Date date;
    private final User user;

    public User getUser() {
        return user;
    }


    public Transaction(String memo,double amount, User user) {
        this.memo = memo;
        this.amount = amount;
        this.user = user;
    }

    public Transaction(String memo,double amount, Date date, User user) {
        this.memo = memo;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public String getMemo() {
        return memo;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
