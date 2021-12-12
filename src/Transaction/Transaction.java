package Transaction;

public class Transaction {
    private final String memo;
    private final double amount;
    private String date;


    public Transaction(String memo,double amount) {
        this.memo = memo;
        this.amount = amount;
    }

    public Transaction(String memo,double amount,String date) {
        this.memo = memo;
        this.amount = amount;
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
