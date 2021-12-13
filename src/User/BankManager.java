package User;


public class BankManager extends User {
    private static Double profit;

    public static Double getProfit() {
        return profit;
    }

    public static void setProfit(Double profit) {
        BankManager.profit = profit;
    }

    public static void addProfit(Double profit) {
        BankManager.profit += profit;
    }

    public BankManager(String firstname, String lastname, String username) {
        super(firstname, lastname, username);
        profit = 0.0;
    }


    @Override
    public UserType getType() {
        return UserType.MANAGER;
    }

}
