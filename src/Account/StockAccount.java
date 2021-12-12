package Account;

import Currency.*;
import Utility.ID;
import User.*;
import java.util.Date;


public class StockAccount extends Account {
    public StockAccount(ID accountID, Currency currency, Date openDate, Money deposit, User user) {
        super(accountID, currency, openDate, deposit, user);
    }

    @Override
    public AccountType getType() {
        return AccountType.STOCK_ACCOUNT;
    }
}
