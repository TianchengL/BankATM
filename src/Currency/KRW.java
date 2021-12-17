package Currency;

import java.text.NumberFormat;
import java.util.Locale;

//concrete class for korean currency
public class KRW extends Currency{

    private static KRW instance;

    public static KRW getInstance(){
        if(instance == null) instance = new KRW();
        return instance;
    }

    @Override
    String showMoney(Money money) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREAN);
        return "₩" + numberFormat.format(money.getAmount());
    }

    @Override
    public String toString() {
        return "KRW";
    }
}
