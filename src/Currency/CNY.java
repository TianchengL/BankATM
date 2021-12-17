package Currency;

import java.text.NumberFormat;
import java.util.Locale;

//concrete class for china currency
public class  CNY extends Currency{

    private static CNY cny;

    //only return singleton
    public static Currency getInstance(){
        if(cny == null){
            cny = new CNY();
        }
        return cny;
    }

    @Override
    String showMoney(Money money) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.CHINA);
        return "¥" + numberFormat.format(money.getAmount());
    }

    @Override
    public String toString() {
        return "CNY";
    }
}
