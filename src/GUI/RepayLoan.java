package GUI;

import Account.Account;
import Collection.AccountCollection;
import Collection.LoanCollection;
import Collection.UserCollection;
import Currency.Money;
import Loan.Loan;
import User.User;
import Utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class RepayLoan extends JFrame{
    private JLabel repayLoan;
    private JComboBox comboBoxLoanAccount;
    private JLabel dueAmt;
    private JTextField payment;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel accountField;
    private JLabel dueAmtLabel;
    private JLabel paymentLabel;
    private JPanel repayLoanPanel;
    private ID id;
    private List<Loan> loans;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public RepayLoan(int userID){
        setTitle("Repay Loan");
        setContentPane(repayLoanPanel);
        setResizable(true);
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        repayLoan.setFont(new Font("Serif", Font.BOLD, 20));

        payment.addKeyListener(new KeyAdapter() {
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

        loans = LoanCollection.getInstance().getLoanAccounts();

        for(Loan loan: loans){
            comboBoxLoanAccount.addItem(loan.getLoanID().toString().substring(0,8));
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        Loan loan = LoanCollection.getInstance().getLoanFromID(comboBoxLoanAccount.getSelectedItem().toString());
        Double dueAmount = (1 + (Loan.getInterest() / 100)) * loan.getLoanAmount().getAmount();
        dueAmt.setText(dueAmount.toString());
        ID id = loan.getLoanID();
        setId(id);
        comboBoxLoanAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Updating due amount based on selected account
                Loan loan = LoanCollection.getInstance().getLoanFromID(comboBoxLoanAccount.getSelectedItem().toString());
                Double dueAmount = (1 + (Loan.getInterest() / 100)) * loan.getLoanAmount().getAmount();
                dueAmt.setText(dueAmount.toString());
                ID id = loan.getLoanID();
                setId(id);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Account> accounts = AccountCollection.getInstance().getUserAccounts(userID);
                String pay = payment.getText();
                if(pay.isEmpty()){
                    JOptionPane.showMessageDialog(repayLoanPanel, "Please enter payment amount");
                }
                else if(accounts.isEmpty()){
                    JOptionPane.showMessageDialog(repayLoanPanel, "Please create a checking account to make the payment");
                    dispose();
                }
                else{
                    Account curAccount = null;
                    for(Account account: accounts){
                        if(account.getType() == Account.AccountType.CHECKING_ACCOUNT) {
                            curAccount = account;
                            break;
                        }
                    }
                    if(curAccount == null) {
                        JOptionPane.showMessageDialog(repayLoanPanel, "Please create a checking account to make the payment");
                        dispose();
                    }
                    else if(Double.parseDouble(pay) > curAccount.getDeposit().getAmount()){
                        JOptionPane.showMessageDialog(repayLoanPanel, "You don't have enough balance");
                    }
                    else if(Double.parseDouble(pay) > Double.parseDouble(dueAmt.getText())){
                        JOptionPane.showMessageDialog(repayLoanPanel, "Please enter an amount less than the due amount");
                    }
                    else{
                        List<Loan> loans = LoanCollection.getInstance().getLoanAccounts();
                        for(Loan loan: loans){
                            if(loan.getLoanID() == getId()){
                                Double amt = loan.getLoanAmount().getAmount() - Double.parseDouble(pay);
                                if(amt <= 0) {
                                    // Loan cleared
                                    loans.remove(loan);
                                    JOptionPane.showMessageDialog(repayLoanPanel, "Loan Cleared");
//                                    dispose();
                                }
                                else
                                    loan.setLoanAmount(new Money(amt));
//                                curAccount.setDeposit(new Money(curAccount.getDeposit().getAmount() - Double.parseDouble(pay)));
//                                loan.setLoanAmount(new Money(amt));
                                break;
                            }
                        }
                        // resetting accounts and loans with updated amounts
                        curAccount.setDeposit(new Money(curAccount.getDeposit().getAmount() - Double.parseDouble(pay)));
                        LoanCollection.getInstance().saveAccountToCSV(loans);
                        AccountCollection.getInstance().saveAccountToCSV(accounts);
                        JOptionPane.showMessageDialog(repayLoanPanel, "Payment successful");
                        dispose();
                    }
                }
            }
        });

    }
}
