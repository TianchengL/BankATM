package Account;

import Currency.Currency;

import java.util.Date;
import java.util.UUID;

public class StockAccount extends Account {
    public StockAccount(UUID id, Currency currency, Date openDate) {
        super(id, currency, openDate);
    }
}
