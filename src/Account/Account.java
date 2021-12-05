package Account;

import Currency.Currency;
import java.util.Date;
import java.util.UUID;

public abstract class Account {

    private UUID id;
    private Currency currency;
    private Date openDate;

    public Account(UUID id, Currency currency, Date openDate){
        this.id = id;
        this.currency = currency;
        this.openDate = openDate;

    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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
}
