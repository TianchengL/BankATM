package Loan;

import Collection.LoanCollection;
import Collection.UserCollection;
import Currency.Currency;
import Currency.Money;
import User.User;
import Utility.ID;

//for the purpose of creating loan
public class LoanFactory {
    public static Loan createLoanAccount(int userID, double interest, String collateral, double loanAmount, Currency currency){
        User user = UserCollection.getInstance().getUserById(userID);
        ID loanId = new ID();
        Money money = new Money(loanAmount);
        Loan loan = new Loan(loanId, collateral, money, interest, user, currency);
        LoanCollection.getInstance().addLoanAccount(loan);
        return loan;
    }
}
