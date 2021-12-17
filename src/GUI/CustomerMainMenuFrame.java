package GUI;

import Collection.StockCollection;
import Collection.TransactionCollection;
import Collection.UserCollection;
import Database.Database;
import User.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMainMenuFrame extends JFrame implements ActionListener {
    private final String username;
    private final Container container;
    private final JLabel userLabel;
    private final JButton accountButton;
    private final JButton checkTransactionButton;
    private final JButton profileButton;
    private final JButton createAccountButton;
    private final JButton loanButton;
    private final JButton makeTransactionButton;
    private final JButton logoutButton;
    private final JButton stockButton;
    private int customerID;
    private final User user;//current user

    public CustomerMainMenuFrame(String username) {
        setTitle("Customer Account Form");
        setVisible(true);
        setBounds(10,10,1000,800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        container = getContentPane();
        userLabel = new JLabel();
        accountButton = new JButton("Check your accounts");
        checkTransactionButton = new JButton("View transactions");
        profileButton = new JButton("Profile");
        createAccountButton = new JButton("Create a checkings/savings/stock account");
        loanButton = new JButton("Manage Loans");
        makeTransactionButton = new JButton("Make transaction");
        logoutButton = new JButton("Logout");
        stockButton = new JButton("Stock");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.username = username;
        userLabel.setText("Username: " + this.username);
        this.customerID = Database.getUserId();
        user = UserCollection.getInstance().retrieveUser(username);
    }



    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(700, 100, 200, 50);
        profileButton.setBounds(300, 250, 400, 40);
        accountButton.setBounds(300, 300, 400, 40);
        checkTransactionButton.setBounds(300, 350, 400, 40);
        createAccountButton.setBounds(300, 400, 400, 40);
        loanButton.setBounds(300, 450, 400, 40);
        makeTransactionButton.setBounds(300, 500, 400, 40);
        logoutButton.setBounds(700, 600, 100, 35);
        stockButton.setBounds(300,550,400,40);

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(profileButton);
        container.add(accountButton);
        container.add(checkTransactionButton);
        container.add(createAccountButton);
        container.add(loanButton);
        container.add(makeTransactionButton);
        container.add(logoutButton);
        container.add(stockButton);
    }

    public void addActionEvent() {
        profileButton.addActionListener(this);
        accountButton.addActionListener(this);
        checkTransactionButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        loanButton.addActionListener(this);
        makeTransactionButton.addActionListener(this);
        logoutButton.addActionListener(this);
        stockButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == profileButton) {
            new ProfileFrame(username);
        }
        if (e.getSource() == accountButton) {
            new ViewCustomerAccountsFrame(user.getId());
        }
        if (e.getSource() == checkTransactionButton) {
            TransactionCollection.getInstance().addAllTransactions();
            new ViewTransactions(TransactionCollection.getInstance().getUserTransactions(user.getId()));
        }
        if (e.getSource() == createAccountButton) {
            new CreateAccountFrame(this.user);
        }
        if (e.getSource() == loanButton) {
            new LoanFrame(user);
        }
        if (e.getSource() == makeTransactionButton) {
            new ChooseTransactions(user);
        }
        if (e.getSource() == logoutButton) {
            dispose();
        }
        if(e.getSource() == stockButton){
            StockCollection.getInstance().addAllStocks();
            new StockGUI(user);
        }
    }

}