package GUI;

import javax.swing.*;

public class ChooseTransactions extends JFrame{
    private JPanel ChooseTransactions;
    private JButton depositButton;
    private JButton transferButton;
    private JButton withdrawButton;
    private JButton payOrTransactionsButton;

    public ChooseTransactions(){
        setContentPane(ChooseTransactions);
        setTitle("View Accounts Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
