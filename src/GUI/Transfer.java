package GUI;

import Account.*;
import Collection.AccountCollection;
import Currency.Money;
import User.User;
import User.BankManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class Transfer extends JFrame{
    private JComboBox<String> withdrawAccount;
    private JComboBox<String> depositAccount;
    private JButton transferButton;
    private JButton cancelButton;
    private JTextField amount;
    private JPanel transferPanel;

    public Transfer(User user){
        setContentPane(transferPanel);
        setTitle("Transfer");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            withdrawAccount.addItem(account.getType().toString());
            depositAccount.addItem(account.getType().toString());
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

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(amount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(transferPanel, "Please enter the deposit amount");
                }
                Account in = null;
                Account out= null;
                for(Account account : accounts){
                    if(account.getType().toString().equals(withdrawAccount.getSelectedItem())){
                        out = account;
                    }else if(account.getType().toString().equals(depositAccount.getSelectedItem())){
                        in = account;
                    }
                }

                Account inAcc = null;
                Account outAcc= null;
                List<Account> allAccounts = AccountCollection.getInstance().getAccounts();
                for(Account account : allAccounts){
                    if(Objects.equals(account.getId().toString(), out.getId().toString())){
                        outAcc = account;
                    }else if(Objects.equals(account.getId().toString(), in.getId().toString())){
                        inAcc = account;
                    }
                }
                if(outAcc != null && inAcc != null) {
                    if(inAcc instanceof StockAccount){
                        if(getAmount()>=1000){
                            outAcc.transfer(inAcc, getAmount());
                            AccountCollection.getInstance().saveAccountToCSV(allAccounts);
                            JOptionPane.showMessageDialog(transferPanel, "Amount transferred!");
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(transferPanel, "Amount must over 1000!");
                            dispose();
                        }
                    }else{
                    outAcc.transfer(inAcc, getAmount());
                    AccountCollection.getInstance().saveAccountToCSV(allAccounts);
                    JOptionPane.showMessageDialog(transferPanel, "Amount transferred!");
                    dispose();
                    }
                }

            }
        });



    }
    private double getAmount(){
        return Double.parseDouble(amount.getText());
    }
}
