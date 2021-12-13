package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Loan.Loan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Locale;

public class InterestRateFrame extends JFrame{
    private JTextField saInterest;
    private JButton loanInterestButton;
    private JButton saInterestButton;
    private JTextField loanInterest;
    private JLabel loanInterestField;
    private JLabel saInterestField;
    private JPanel interestPanel;
    private JButton backButton;
    private JLabel title;

    public InterestRateFrame(){
        setTitle("Interest Rate Form");
        setContentPane(interestPanel);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        loanInterest.addKeyListener(new KeyAdapter() {
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

        loanInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loanInterest.getText().isEmpty()){
                    JOptionPane.showMessageDialog(interestPanel, "Please enter the rate of interest");
                }
                else {
                    Double interest = Double.parseDouble(loanInterest.getText().toString());
                    Loan.setInterest(interest);
                    JOptionPane.showMessageDialog(interestPanel, "Interest rate set successfully");
                }
            }
        });

        loanInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
