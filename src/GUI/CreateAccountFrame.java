package GUI;

import Account.*;
import Collection.AccountCollection;
import Collection.UserCollection;
import Currency.*;
import User.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.List;

public class CreateAccountFrame extends JFrame {

    private JPanel createAccountPanel;
    private JComboBox<Account.AccountType> comboBoxAccountType;
    private JComboBox<Currency.CurrencyType> comboBoxCurrencyType;
    //private JFormattedTextField initialDepositTextField;
    private JButton createAccountButton;
    private JTextField InitialDepositTextField;

    public CreateAccountFrame(User user){
        setContentPane(createAccountPanel);
        setTitle("Create Account Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        comboBoxAccountType.addItem(Account.AccountType.CHECKING_ACCOUNT);
        comboBoxAccountType.addItem(Account.AccountType.SAVING_ACCOUNT);
        comboBoxCurrencyType.addItem(Currency.CurrencyType.CNY);
        comboBoxCurrencyType.addItem(Currency.CurrencyType.KRW);
        comboBoxCurrencyType.addItem(Currency.CurrencyType.USD);
        //initialDepositTextField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter()));

        //only input should be number
        InitialDepositTextField.addKeyListener(new KeyAdapter() {
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


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(getDeposit() < 100.0){
                    JOptionPane.showMessageDialog(createAccountPanel, "Initial Deposit cannot less than 100");
                }else{
                    //create new account and add it to collection
                    AccountFactory.createAccount(user.getId(), getDeposit(),
                            getCurrency(), (Account.AccountType) comboBoxAccountType.getSelectedItem());

                    //save to disk (csv file)
                    AccountCollection.getInstance().saveAccountToCSV(AccountCollection.getInstance().getAccounts());
                    JOptionPane.showMessageDialog(createAccountPanel, "Account Created!");
                    dispose();

//                    for (Account account : AccountCollection.getInstance().getAccounts()) {
//                        System.out.println("111111111111 : " + account.getDeposit().getAmount());
//                    }

                }
            }
        });
    }

    //get input currency
    private Currency getCurrency(){
        Currency currency = null;
        if(comboBoxCurrencyType.getSelectedItem() == Currency.CurrencyType.USD){
            currency = USD.getInstance();
        }else if(comboBoxCurrencyType.getSelectedItem() == Currency.CurrencyType.CNY){
            currency = CNY.getInstance();
        }else if(comboBoxCurrencyType.getSelectedItem() == Currency.CurrencyType.KRW){
            currency = KRW.getInstance();
        }
        return currency;
    }


    //get start deposit
    private double getDeposit(){
        return Double.parseDouble(InitialDepositTextField.getText());
    }



    public static void main(String[] args) {
        User user = new Customers("tiancheng", "l", "ttt");
        user.setId(1);
        CreateAccountFrame c = new CreateAccountFrame(user);
        //Account.Account.AccountType t = (Account.AccountType) c.comboBoxAccountType.getSelectedItem();

    }
}