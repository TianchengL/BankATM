package GUI;

import Account.Account;
import Collection.AccountCollection;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class Withdraw extends JFrame{
    private JTextField amount;
    private JButton cancelButton;
    private JButton withdrawButton;
    private JPanel WithdrawPanel;
    private JComboBox<String> checking;

    public Withdraw(User user){
        setContentPane(WithdrawPanel);
        setTitle("View Accounts Form");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            if(account.getType()== Account.AccountType.CHECKING_ACCOUNT){
                checking.addItem(account.getId().toString().substring(0,8));
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

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(withdrawButton.getText().isEmpty()){
                    JOptionPane.showMessageDialog(WithdrawPanel, "Please enter the deposit amount");
                }
                else{
                    List<Account> accounts = AccountCollection.getInstance().getAccounts();
                    for(Account account: accounts){
                        if(Objects.equals(account.getId().toString().substring(0,8), checking.getSelectedItem().toString())){
                            if(account.getDeposit().getAmount() < getWithdraw()){
                                JOptionPane.showMessageDialog(WithdrawPanel, "Not enough balance");
                            }
                            else {
                                account.withdraw(getWithdraw(), true, "Withdraw");
                                AccountCollection.getInstance().saveAccountToCSV(accounts);
                                JOptionPane.showMessageDialog(WithdrawPanel, "Amount Withdrawn!");
                                dispose();
                            }
                            break;
                        }
                    }
                }
//                Account account=(Account)checking.getSelectedItem();
//                account.withdraw(getWithdraw(),true,"Withdraw:");

            }
        });
    }
    private double getWithdraw(){
        return Double.parseDouble(amount.getText());
    }
}
