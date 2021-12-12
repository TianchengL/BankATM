package Currency;

import java.text.NumberFormat;
import java.util.Locale;


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

    public static void main(String[] args) {
        USD usd = new USD();
        KRW jpy = new KRW();
        Money money = new Money(1000000000);
        String d = usd.showMoney(money);
        System.out.println(jpy.showMoney(money));
    }

}
