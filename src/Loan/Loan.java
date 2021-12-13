package Loan;

import Currency.Currency;
import Currency.Money;
import User.User;
import Utility.ID;

import java.io.Serializable;

public class Loan implements Serializable {
    private ID loanID;
    private String collateral;
    private Money loanAmount;
    private static double interest = 10;
    private Currency currency;
    private final User user;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ID getLoanID() {
        return loanID;
    }

    public void setLoanID(ID loanID) {
        this.loanID = loanID;
    }

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    public Money getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Money loanAmount) {
        this.loanAmount = loanAmount;
    }

    public static double getInterest() {
        return interest;
    }

    public static void setInterest(double interest) {
        Loan.interest = interest;
    }

    public User getUser() {
        return user;
    }

    public Loan(ID loanID, String collateral, Money loanAmount, double interest, User user, Currency currency) {
        this.loanID = loanID;
        this.collateral = collateral;
        this.loanAmount = loanAmount;
        this.interest = interest;
        this.user = user;
        this.currency = currency;
    }
}
