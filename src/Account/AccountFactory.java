package Account;

import Collection.AccountCollection;
import Collection.UserCollection;
import Currency.*;
import User.*;
import Utility.*;

import java.util.Date;

public class AccountFactory {

    //only responsible for create checking and saving accounts
    public static Account createAccount(int userID, double startDeposit, Currency currency, Account.AccountType accountType){

        User user = UserCollection.getInstance().getUserById(userID);
        ID accountId = new ID();
        Date date = new Date();
        Money money = new Money(startDeposit);

        Account account = null;
        if(accountType == Account.AccountType.CHECKING_ACCOUNT){
            account = new CheckingAccount(accountId, currency, date, money, user);
            AccountCollection.getInstance().addAccount(account);
        }else if(accountType == Account.AccountType.SAVING_ACCOUNT){
            account = new SavingAccount(accountId, currency, date, money, user);
            AccountCollection.getInstance().addAccount(account);
        }

        return account;
    }

    public static Account createStockAccount(int userID, double startDeposit, Currency currency, String collateral){
        User user = UserCollection.getInstance().getUserById(userID);
        ID accountId = new ID();
        return null;
    }
}
