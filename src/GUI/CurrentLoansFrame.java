package GUI;

import Collection.LoanCollection;
import Loan.Loan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CurrentLoansFrame extends JFrame{
    private JTextArea loanId;
    private JTextArea collateral;
    private JTextArea loanAmt;
    private JTextArea roi;
    private JTextArea currency;
    private JButton repayLoanButton;
    private JButton backButton;
    private JLabel loanIdField;
    private JPanel currentLoansPanel;
    private JLabel collateralField;
    private JLabel roiField;
    private JLabel currencyField;
    private JLabel loanAmtField;
    private JLabel currentLoans;

    public CurrentLoansFrame(int userID){
        setTitle("Loan Accounts");
        setContentPane(currentLoansPanel);
        setResizable(true);
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentLoans.setFont(new Font("Serif", Font.BOLD, 20));

        List<Loan> list = LoanCollection.getInstance().getUserLoanAccounts(userID);
        for (Loan loan : list) {
            loanId.append("" + loan.getLoanID().toString().substring(0,8) + "\n\n");
            collateral.append(loan.getCollateral() + "\n\n");
            loanAmt.append("" + loan.getLoanAmount() + "\n\n");
            currency.append("" + loan.getCurrency() + "\n\n");
            roi.append("" + Loan.getInterest() + "\n\n");
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        repayLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.isEmpty())
                    JOptionPane.showMessageDialog(currentLoansPanel, "You don't have any loans");
                else
                    new RepayLoan(userID);
                dispose();
            }
        });
    }
}
