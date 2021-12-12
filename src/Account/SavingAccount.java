package Account;

import Currency.*;
import Utility.ID;
import User.*;

import java.io.Serializable;
import java.util.Date;


public class SavingAccount extends Account implements Serializable {
    public SavingAccount(ID accountID, Currency currency, Date openDate, Money deposit, User user) {
        super(accountID, currency, openDate, deposit, user);
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVING_ACCOUNT;
    }
}
