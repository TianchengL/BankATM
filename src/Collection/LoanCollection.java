package Collection;

import Account.Account;
import Loan.Loan;
import Utility.ID;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//a collection for loan
public class LoanCollection {
    private List<Loan> loans;
    private static LoanCollection instance;
    private final String CSV_Location = "src/Data/Loans.csv";

    public LoanCollection(){
        loans = new ArrayList<>();
        addAllAccounts();
    }

    //singleton only init once
    public static LoanCollection getInstance(){
        if(instance == null) instance = new LoanCollection();
        return instance;
    }

    //add all accounts from file
    private void addAllAccounts(){
        this.loans = getAccountFromFile();
    }

    //save all accounts into csv file
    public void saveAccountToCSV(List<Loan> loans){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(CSV_Location);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            for (Loan loan : loans) {
                outputStream.writeObject(loan);
                outputStream.reset();
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get the list of accounts from disk which is csv file
    public List<Loan> getAccountFromFile(){
        Object obj;
        List<Loan> loans = new ArrayList<>();
        try
        {
            FileInputStream fileInputStream = new FileInputStream(CSV_Location);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            obj = inputStream.readObject();
            while(obj!= null){
                loans.add((Loan) obj);
                obj = inputStream.readObject();
            }

        }
        catch(Exception ee)
        {
            //ee.printStackTrace();
        }
        return loans;

    }

    //return all accounts that stored in memory
    public List<Loan> getLoanAccounts(){
        return loans;
    }

    //add one account to memory
    public void addLoanAccount(Loan loan){
        loans.add(loan);
    }

    //get current user accounts
    public List<Loan> getUserLoanAccounts(int userID){

        List<Loan> list = new ArrayList<>();
        for (Loan loan : loans) {
            if(loan.getUser().getId() == userID){
                list.add(loan);
            }
        }
        return list;
    }

    public Loan getLoanFromID(String loanID){
        for (Loan loan : loans) {
            String id = loan.getLoanID().toString().substring(0,8);
            if(id.equals(loanID)){
                return loan;
            }
        }
        return null;
    }
}
