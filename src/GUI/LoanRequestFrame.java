package GUI;

import Collection.LoanCollection;
import Currency.Currency;
import Loan.LoanFactory;
import User.User;
import Currency.KRW;
import Currency.USD;
import Currency.CNY;
import Loan.Loan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoanRequestFrame extends JFrame{
    private JTextField loanAmount;
    private JTextField collateralAmount;
    private JLabel loanAmountField;
    private JLabel currencyField;
    private JLabel collateralType;
    private JLabel collateralAmountField;
    private JComboBox comboBoxCurrency;
    private JPanel loanRequestPanel;
    private JTextField collateral;
    private JButton requestLoanButton;
    private JButton backButton;
    private JLabel loanRequest;

    public LoanRequestFrame(User user){
        setTitle("Loan Request Form");
        setContentPane(loanRequestPanel);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loanRequest.setFont(new Font("Serif", Font.BOLD, 20));
        comboBoxCurrency.addItem(Currency.CurrencyType.CNY);
        comboBoxCurrency.addItem(Currency.CurrencyType.KRW);
        comboBoxCurrency.addItem(Currency.CurrencyType.USD);

        loanAmount.addKeyListener(new KeyAdapter() {
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

        collateralAmount.addKeyListener(new KeyAdapter() {
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        loanAmount.addKeyListener(new KeyAdapter() {
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

        collateralAmount.addKeyListener(new KeyAdapter() {
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

        requestLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanAmt = loanAmount.getText();
                String collateralAmt = collateralAmount.getText();
                String collateralType = collateral.getText();
                if(loanAmt.isEmpty()){
                    JOptionPane.showMessageDialog(loanRequestPanel, "Please enter the loan Amount");
                }
                else if(collateralType.isEmpty()){
                    JOptionPane.showMessageDialog(loanRequestPanel, "Please enter the type of collateral");
                }
                else if(collateralAmt.isEmpty()){
                    JOptionPane.showMessageDialog(loanRequestPanel, "Please enter the collateral amount");
                }
                else if(Double.parseDouble(loanAmt) > Double.parseDouble(collateralAmt)){
                    JOptionPane.showMessageDialog(loanRequestPanel, "You cannot request loan greater than the collateral amount");
                }
                else{
                    LoanFactory.createLoanAccount(user.getId(), Loan.getInterest(),
                            collateralType, Double.parseDouble(loanAmt), getCurrency());

                    //save to disk (csv file)
                    LoanCollection.getInstance().saveAccountToCSV(LoanCollection.getInstance().getLoanAccounts());
                    JOptionPane.showMessageDialog(loanRequestPanel, "Loan Approved!");
                    dispose();
                }
            }
        });
    }

    private Currency getCurrency(){
        Currency currency = null;
        if(comboBoxCurrency.getSelectedItem() == Currency.CurrencyType.USD){
            currency = USD.getInstance();
        }else if(comboBoxCurrency.getSelectedItem() == Currency.CurrencyType.CNY){
            currency = CNY.getInstance();
        }else if(comboBoxCurrency.getSelectedItem() == Currency.CurrencyType.KRW){
            currency = KRW.getInstance();
        }
        return currency;
    }
}
