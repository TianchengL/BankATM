package Collection;

import Account.Account;
import Loan.Loan;
import Transaction.Transaction;
import Transaction.Transaction;
import Transaction.Transaction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TransactionCollection {
    private List<Transaction> transactions;
    private static TransactionCollection instance;
    private final String CSV_Location = "src/Data/Transactions.csv";

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public TransactionCollection(){
        transactions = new ArrayList<>();
        
    }
    //singleton only init once
    public static TransactionCollection getInstance(){
        if(instance == null) instance = new TransactionCollection();
        return instance;
    }

    //add all Transactions from file
    public void addAllTransactions(){
        this.transactions = getTransactionFromFile();
    }

    //save all Transactions into csv file
    public void saveTransactionToCSV(List<Transaction> Transactions){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(CSV_Location);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            for (Transaction Transaction : Transactions) {
                outputStream.writeObject(Transaction);
                outputStream.reset();
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionFromFile(){
        Object obj;
        List<Transaction> transactions = new ArrayList<>();
        try
        {
            FileInputStream fileInputStream = new FileInputStream(CSV_Location);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            obj = inputStream.readObject();
            while(obj!= null){
                transactions.add((Transaction) obj);
                obj = inputStream.readObject();
            }

        }
        catch(Exception ee)
        {
            //ee.printStackTrace();
        }
        return transactions;

    }

    public List<Transaction> getUserTransactions(int userID){

        List<Transaction> list = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction.getUser().getId() == userID){
                list.add(transaction);
            }
        }
        return list;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }


}
