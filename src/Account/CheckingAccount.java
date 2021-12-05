package Account;

import Currency.Currency;

import java.util.Date;
import java.util.UUID;

public class CheckingAccount extends Account{
    public CheckingAccount(UUID id, Currency currency, Date openDate) {
        super(id, currency, openDate);
    }
}
