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
    private JScrollPane scrollPane;
    private JTextArea accountsInfo;


    public ViewCustomerAccountsFrame(int userID){
        setContentPane(viewAccountsPanel);
        setTitle("View Accounts Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        userIDLabel.setText(String.valueOf(userID));

        scrollPane.createHorizontalScrollBar();
        scrollPane.createVerticalScrollBar();

        //accountList.add
        List<Account> list = AccountCollection.getInstance().getUserAccounts(userID);
        for (Account account : list) {
            accountsInfo.append(account.toString() + "\n\n");
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
