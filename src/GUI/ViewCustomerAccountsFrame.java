package GUI;

import Account.Account;
import Collection.AccountCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewCustomerAccountsFrame extends JFrame {
    private JPanel viewAccountsPanel;
    private JButton backButton;
    private JLabel userIDLabel;
    private JTextArea accountsInfo;
    private JTextArea idField;
    private JTextArea accountField;
    private JTextArea balanceField;
    private JTextArea currencyField;
    private JTextArea dateField;


    public ViewCustomerAccountsFrame(int userID){
        setTitle("Customer Accounts");
        setContentPane(viewAccountsPanel);
        setTitle("View Accounts Form");
        setResizable(true);
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        scrollPane = new JScrollPane(accountArea);
        userIDLabel.setText(String.valueOf(userID));

        //accountList.add
        List<Account> list = AccountCollection.getInstance().getUserAccounts(userID);
        for (Account account : list) {
            String accountType = "";
            if(account.getType() == Account.AccountType.SAVING_ACCOUNT)
                accountType = "Savings Account";
            else if(account.getType() == Account.AccountType.CHECKING_ACCOUNT)
                accountType = "Checking Account";
//            accountsInfo.append(account.toString() + "\n\n");
            System.out.println(account);
            idField.append("" + account.getId().toString().substring(0,8) + "\n\n");
            accountField.append(accountType + "\n\n");
            balanceField.append("" + account.getDeposit() + "\n\n");
            currencyField.append("" + account.getCurrency() + "\n\n");
            dateField.append("" + account.getOpenDate() + "\n\n");
        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new ViewCustomerAccountsFrame(12);
    }
}
