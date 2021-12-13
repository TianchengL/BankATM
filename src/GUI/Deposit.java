package GUI;

import Account.*;
import Account.AccountFactory;
import Collection.AccountCollection;
import User.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class Deposit extends JFrame{
    private JPanel DepositPanel;
    private JTextField depositAmount;
    private JButton cancelButton;
    private JButton depositButton;
    private JComboBox<String> checking;


    public Deposit(User user){
        setContentPane(DepositPanel);
        setTitle("View Accounts Form");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            if(account.getType()== Account.AccountType.CHECKING_ACCOUNT){
                checking.addItem(account.getId().toString());
            }
        }

        depositAmount.addKeyListener(new KeyAdapter() {
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

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(depositAmount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(DepositPanel, "Please enter the deposit amount");
                }
                else if(getDeposit() < 100.0){
                    JOptionPane.showMessageDialog(DepositPanel, "Deposit cannot less than 100");
                }else{
                    List<Account> accounts = AccountCollection.getInstance().getAccounts();
                    for(Account account: accounts){
                        if(Objects.equals(account.getId().toString(), checking.getSelectedItem().toString())){
                            account.deposit(getDeposit(),account.getCurrency(),true,"Deposit");
                            break;
                        }
                    }
//                    Account account = AccountCollection.getInstance().getAccountById(checking.getSelectedItem().toString());
////                    Account account=(Account)checking.getSelectedItem();
//                    account.deposit(getDeposit(),account.getCurrency(),true,"Deposit:");
                    AccountCollection.getInstance().saveAccountToCSV(accounts);
                    JOptionPane.showMessageDialog(DepositPanel, "Amount deposited");
                    dispose();
                }
            }
        });
    }
    //get  deposit
    private double getDeposit(){
        return Double.parseDouble(depositAmount.getText().toString());
    }

    public static void main(String[] args) {
        User user = new Customers("tiancheng", "l", "ttt");
        user.setId(1);
        Deposit c = new Deposit(user);
        //Account.Account.AccountType t = (Account.AccountType) c.comboBoxAccountType.getSelectedItem();

    }
}
