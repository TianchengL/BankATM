package Account;

import Collection.AccountCollection;
import Collection.LoanCollection;
import Collection.StockCollection;
import Collection.TransactionCollection;
import Currency.*;
import Stock.Stock;
import Transaction.*;
import Utility.*;
import User.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This class is an abstraction of account
 */
public abstract class Account implements Serializable, TransactionInterface {

    protected static final double TransactionFee = 5.00;

    private ID accountID;
    private Currency currency;
    private Date openDate;
    private Money deposit;
    private final User user;
    private ArrayList<Transaction> transactions;
    private ArrayList<Stock> stockOrderHistory;


    public enum AccountType {CHECKING_ACCOUNT, SAVING_ACCOUNT, STOCK_ACCOUNT}

    public Account(ID accountID, Currency currency, Date openDate, Money deposit, User user) {
        this.accountID = accountID;
        this.currency = currency;
        this.openDate = openDate;
        this.deposit = deposit;
        this.user = user;
        stockOrderHistory = new ArrayList<>();
    }

    //getter and setter
    public List<Stock> getStockOrderHistory() {
        return stockOrderHistory;
    }
    public Money getDeposit() {
        return deposit;
    }
    public void setDeposit(Money deposit) {
        this.deposit = deposit;
    }
    public User getUser() {
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

    public abstract AccountType getType();

    @Override
    public String toString() {
        return "{" +
                "accountID: " + accountID +
                ", currency: " + currency +
                ", openDate: " + openDate +
                ", deposit: " + deposit +
                '}';
    }

    //account money can be withdrawn
    @Override
    public boolean withdraw(double amount, boolean isCharged, String memo) {
        if (isCharged) {
            TransactionFactory.createTransaction("Withdraw Fee", TransactionFee, getUser());
            deposit.deductMoney(TransactionFee);
            BankManager.addProfit(TransactionFee);
        }

        TransactionFactory.createTransaction(memo, amount, getUser());
        TransactionCollection.getInstance().saveTransactionToCSV(TransactionCollection.getInstance().getTransactions());
        return deposit.deductMoney(amount);
    }

    //account could deposit money
    @Override
    public boolean deposit(double amount, Currency cur, boolean isCharged, String memo) {

        if (isCharged) {
            TransactionFactory.createTransaction("Deposit Fee", TransactionFee, getUser());
            deposit.deductMoney(TransactionFee);
            BankManager.addProfit(TransactionFee);
        }

        TransactionFactory.createTransaction(memo, amount, getUser());
        deposit.addMoney(amount);
        System.out.println(TransactionCollection.getInstance().getTransactions().size());
        TransactionCollection.getInstance().saveTransactionToCSV(TransactionCollection.getInstance().getTransactions());
        return true;
    }

    //account could transfer money
    public boolean transferTo(int userId, double amount, List<Account> allAccounts) {
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(userId);
        Account acc = null;
        for (Account account : accounts) {
            if(Objects.equals(this.currency.toString(), account.getCurrency().toString())){
                acc = account;
                break;
            }
        }
        if(acc == null){
            return false;
        }
        for(Account account : allAccounts){
            if(Objects.equals(account.getId().toString(), acc.getId().toString())){
                if (withdraw(amount, true, "Transfer to " + account.getId().toString().substring(0,8))) {
                    account.deposit(amount, account.getCurrency(), true, "Transfer from " + accountID.toString().substring(0,8));
                    TransactionFactory.createTransaction("Transaction Fee", TransactionFee, getUser());
                    return true;
                }
            }
        }
        return false;
    }


    public boolean transfer(Account in,double amount){
        withdraw(amount,false,"Transfer to "+ in.getType().toString());
        in.deposit(amount,in.getCurrency(),false,"Transfer from "+ this.getType().toString());
        TransactionFactory.createTransaction("Transfer Fee", TransactionFee, getUser());
        BankManager.addProfit(TransactionFee);
        return true;
    }




}
