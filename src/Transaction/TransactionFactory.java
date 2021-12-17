package Transaction;

import Collection.LoanCollection;
import Collection.TransactionCollection;
import Collection.UserCollection;
import User.User;

import java.util.Date;

//purpose of creating and transaction
public class TransactionFactory {
    public static Transaction createTransaction(String memo, double amount, User user){
//        User user = UserCollection.getInstance().getUserById(userID);
        Date date = new Date();
        System.out.println(date);
        Transaction transaction = new Transaction(memo, amount, new Date(), user);
        TransactionCollection.getInstance().addTransaction(transaction);
        return transaction;
    }
}
