package Transaction;

import Currency.Currency;

//all transaction needs to have withdrawn and deposit functionality
public interface TransactionInterface {
    boolean withdraw(double amount, boolean isCharged, String memo);
    boolean deposit(double amount, Currency cur, boolean isCharged, String memo);
}
