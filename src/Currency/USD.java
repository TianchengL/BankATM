package Currency;

import java.text.NumberFormat;
import java.util.Locale;

//concrete class for USA currency
public class USD extends Currency{

    private static USD instance;


    public static USD getInstance(){
        if(instance == null) instance = new USD();
        return instance;
    }

    @Override
    public String showMoney(Money money) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return "$" + numberFormat.format(money.getAmount());

    }

    @Override
    public String toString() {
        return "USD";
    }


}
