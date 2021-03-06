package GUI;

import Account.Account;
import Collection.AccountCollection;
import Collection.TransactionCollection;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class Pay extends JFrame{
    private JButton cancelButton;
    private JTextField receiver;
    private JTextField amount;
    private JComboBox<String> accountsList;
    private JButton payButton;
    private JPanel PayPanel;

    public Pay(User user){
        setContentPane(PayPanel);
        setTitle("View Accounts Form");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            if(account.getType()== Account.AccountType.CHECKING_ACCOUNT || account.getType()== Account.AccountType.SAVING_ACCOUNT){
                accountsList.addItem(account.getId().toString().substring(0,8));
            }
        }
        amount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        receiver.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
                super.keyTyped(e);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Account account=(Account)accountsList.getSelectedItem();
                if(receiver.getText().isEmpty()){
                    JOptionPane.showMessageDialog(PayPanel, "Please enter the receiver ID");
                }
                else if(amount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(PayPanel, "Please enter the deposit amount");
                }
                else if(Integer.parseInt(receiver.getText().toString()) > AccountCollection.getInstance().getAccounts().size() || Integer.parseInt(receiver.getText().toString()) <= 1){
                    JOptionPane.showMessageDialog(PayPanel, "User index out of range");
                }
                else {
                    List<Account> accounts = AccountCollection.getInstance().getAccounts();
                    for (Account account : accounts) {
                        if (Objects.equals(account.getId().toString().substring(0, 8), accountsList.getSelectedItem().toString())) {
                            if(account.transferTo(getReceiver(), getamount(), accounts)) {
                                AccountCollection.getInstance().saveAccountToCSV(accounts);
                                TransactionCollection.getInstance().saveTransactionToCSV(TransactionCollection.getInstance().getTransactions());
                                JOptionPane.showMessageDialog(PayPanel, "Amount paid!");
                                break;
                            }
                            else{
                                JOptionPane.showMessageDialog(PayPanel, "The receiver does not have a checking account!");
                                break;
                            }
                        }
                    }
                    dispose();
                }
            }
        });
    }

    private int getReceiver(){
        return Integer.valueOf(receiver.getText());
    }
    private double getamount(){
        return Double.parseDouble(amount.getText());
    }
}
