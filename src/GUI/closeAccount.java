package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Account.Account;
import Collection.AccountCollection;
import Collection.TransactionCollection;
import Transaction.TransactionFactory;
import User.User;
import User.BankManager;

public class closeAccount extends JFrame{
    private JComboBox accounts;
    private JButton closeAccountButton;
    private JPanel closeAccountPanel;
    private JLabel closeAccount;
    private JLabel selectField;
    private JButton backButton;

    public closeAccount(User user){
        setTitle("Close Account");
        setContentPane(closeAccountPanel);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Account> userAccounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : userAccounts){
            accounts.addItem(account.getId().toString().substring(0,8));
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        closeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Account> accountList = AccountCollection.getInstance().getAccounts();
                for(Account account: accountList){
                    if(account.getId().toString().substring(0, 8).equals(accounts.getSelectedItem().toString())){
                        accountList.remove(account);
                        AccountCollection.getInstance().saveAccountToCSV(accountList);
                        JOptionPane.showMessageDialog(closeAccountPanel, "Account closed");
                        BankManager.addProfit(5.0);
                        TransactionFactory.createTransaction("Account closing Fee", 5, user);
                        TransactionCollection.getInstance().saveTransactionToCSV(TransactionCollection.getInstance().getTransactions());
                        dispose();
                        break;
                    }
                }
            }
        });


    }
}
