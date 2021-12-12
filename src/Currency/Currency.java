package Currency;

import java.io.Serializable;

//all the currency should be singleton
public abstract class Currency implements Serializable {

    //show the input amount of money according to currency
    abstract String showMoney(Money money);
    public enum CurrencyType{CNY, KRW, USD}



}
