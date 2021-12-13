package GUI;

import Collection.LoanCollection;
import Loan.Loan;
import Loan.LoanFactory;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanFrame extends JFrame{
    private JButton requestLoanButton;
    private JButton checkCurrentLoansButton;
    private JLabel loanForm;
    private JPanel loanPanel;
    private JButton backButton;

    public LoanFrame(User user){
        setTitle("Loan Form");
        setContentPane(loanPanel);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loanForm.setFont(new Font("Serif", Font.BOLD, 20));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        requestLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoanRequestFrame(user);
            }
        });

        checkCurrentLoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CurrentLoansFrame(user.getId());
            }
        });
    }
}
