package Account;

import Currency.Currency;

import java.util.Date;
import java.util.UUID;

public class SavingAccount extends Account {
    public SavingAccount(UUID id, Currency currency, Date openDate) {
        super(id, currency, openDate);
    }
}
