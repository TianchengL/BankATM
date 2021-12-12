package Account;

import Currency.*;
import Utility.*;
import User.*;

import java.io.Serializable;
import java.util.Date;


public abstract class Account implements Serializable {

    private ID accountID;
    private Currency currency;
    private Date openDate;
    private Money deposit;
    private final User user;

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
}
