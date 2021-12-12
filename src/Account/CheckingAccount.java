package Account;

import Currency.*;
import Utility.*;
import User.*;

import java.io.Serializable;
import java.util.Date;

public class CheckingAccount extends Account implements Serializable {
    public CheckingAccount(ID accountID, Currency currency, Date openDate, Money deposit, User user) {
        super(accountID, currency, openDate, deposit, user);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKING_ACCOUNT;
    }
}
