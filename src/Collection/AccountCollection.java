package Collection;

import Account.*;
import Utility.ID;


import java.io.*;
import java.util.*;

//a list of storage of all counts stored in disk
public class AccountCollection {

    private List<Account> accounts;
    private static AccountCollection instance;
    private final String CSV_Location = "src/Data/Accounts.csv";

    public AccountCollection(){
        accounts = new ArrayList<>();
        addAllAccounts();
    }

    //singleton only init once
    public static AccountCollection getInstance(){
        if(instance == null) instance = new AccountCollection();
        return instance;
    }

    //add all accounts from file
    private void addAllAccounts(){
        this.accounts = getAccountFromFile();
    }

    //save all accounts into csv file
    public void saveAccountToCSV(List<Account> accounts){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(CSV_Location);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            for (Account account : accounts) {
                outputStream.writeObject(account);
                outputStream.reset();
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get the list of accounts from disk which is csv file
    public List<Account> getAccountFromFile(){
        Object obj;
        List<Account> accounts = new ArrayList<>();
        try
        {
            FileInputStream fileInputStream = new FileInputStream(CSV_Location);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            obj = inputStream.readObject();
            while(obj!= null){
                accounts.add((Account) obj);
                obj = inputStream.readObject();
            }

        }
        catch(Exception ee)
        {
            //ee.printStackTrace();
        }
        return accounts;

    }
    public Account getAccountById(ID accountId){
        for(Account account:accounts){
            if(account.getId()==accountId){
                return account;
            }
        }
        return null;
    }
    //return all accounts that stored in memory
    public List<Account> getAccounts(){
        return accounts;
    }

    //add one account to memory
    public void addAccount(Account account){
        accounts.add(account);
    }

    //get current user accounts
    public List<Account> getUserAccounts(int userID){

        List<Account> list = new ArrayList<>();
        for (Account account : accounts) {
            if(account.getUser().getId() == userID){
                list.add(account);
            }
        }
        return list;
    }

    public static void main(String[] args) {


        List<Account> account = AccountCollection.getInstance().getAccountFromFile();
        for (Account a : account) {
            System.out.println("1111111111 : " + a.getDeposit().getAmount());
        }

    }

}
