package Currency;

import java.io.Serializable;

//all the currency should be singleton
public abstract class Currency implements Serializable {

    //show the input amount of money according to currency
    abstract String showMoney(Money money);
    public enum CurrencyType{CNY, KRW, USD}

    public static double exchange(CurrencyType currType, double amount){
        switch(currType){
            case CNY -> {return amount/6.4;}
            case KRW ->{return amount/1180;}
            default -> {return amount;}
        }

    }


}
