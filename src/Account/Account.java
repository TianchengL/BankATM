package Account;

import Collection.AccountCollection;
import Currency.*;
import Transaction.*;
import Utility.*;
import User.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public abstract class Account implements Serializable, TransactionInterface {

    protected static final double TransactionFee = 5.00;

    private ID accountID;
    private Currency currency;
    private Date openDate;
    private Money deposit;
    private final User user;
    private ArrayList<Transaction> transactions;


    public enum AccountType{CHECKING_ACCOUNT, SAVING_ACCOUNT, STOCK_ACCOUNT}

    public Account(ID accountID, Currency currency, Date openDate, Money deposit, User user){
        this.accountID = accountID;
        this.currency = currency;
        this.openDate = openDate;
        this.deposit = deposit;
        this.user = user;
    }

    public Money getDeposit() {
        return deposit;
    }
    public void setDeposit(Money deposit) {
        this.deposit = deposit;
    }

    public User getUser(){
        return user;
    }
    public ID getId() {
        return accountID;
    }
    public void setId(ID id) {
        this.accountID = id;
    }
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    abstract AccountType getType();

    @Override
    public String toString() {
        return "{" +
                "accountID: " + accountID +
                ", currency: " + currency +
                ", openDate: " + openDate +
                ", deposit: " + deposit +
                '}';
    }


    @Override
    public boolean withdraw(double amount,  boolean isCharged, String memo) {
        if(isCharged){
            Transaction serviceFee = new Transaction("Withdraw Fee",TransactionFee);
            deposit.deductMoney(TransactionFee);
        }

        Transaction transaction = new Transaction(memo,amount);
        if(deposit.deductMoney(amount)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deposit(double amount, Currency.CurrencyType curType, boolean isCharged, String memo) {
        double exchangedAmount = Currency.exchange(curType,amount);//exchange to usd

        if(isCharged){
            Transaction serviceFee = new Transaction("Deposit Fee",TransactionFee);
            deposit.deductMoney(TransactionFee);
        }

        Transaction transaction = new Transaction(memo,exchangedAmount);
        deposit.addMoney(amount);
        return true;
    }

    public boolean transferTo(ID accountID,double amount){
        Account account = AccountCollection.getInstance().getAccountById(accountID);
        if(withdraw(amount,true,"Transfer to"+account.getId())){
            account.deposit(amount, Currency.CurrencyType.USD,true,"Transfer from"+accountID);
            return true;
        }
       return false;
    }


}
