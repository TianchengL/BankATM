package GUI;

import Transaction.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewTransactions extends JFrame{
    private JLabel userIDLabel;
    private JTextArea dateField;
    private JTextArea amountField;
    private JTextArea memoField;
    private JButton backButton;
    private JLabel dateLabel;
    private JLabel amountLabel;
    private JLabel memoLabel;
    private JPanel transactionPanel;
    private JLabel userIdLabel;
    private JTextArea userIdField;

    public ViewTransactions(List<Transaction> transactions){
        setTitle("Transactions");
        setContentPane(transactionPanel);
        setResizable(true);
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        for (Transaction transaction : transactions) {
            System.out.println(transaction);
            userIdField.append(transaction.getUser().getId() + "\n\n");
            memoField.append(transaction.getMemo() + "\n\n");
            amountField.append("" + transaction.getAmount() + "\n\n");
            dateField.append("" + transaction.getDate() + "\n\n");
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
