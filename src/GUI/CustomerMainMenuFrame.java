package GUI;

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
    private int customerID;
    private final User user;//current user

    public CustomerMainMenuFrame(String username) {
        setTitle("Customer Account Form");
        setVisible(true);
        setBounds(10,10,600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        container = getContentPane();
        userLabel = new JLabel();
        accountButton = new JButton("Check your accounts");
        checkTransactionButton = new JButton("View transactions");
        profileButton = new JButton("Profile");
        createAccountButton = new JButton("Create a checkings/savings account");
        loanButton = new JButton("Request Loan");
        makeTransactionButton = new JButton("Make transaction");
        logoutButton = new JButton("Logout");
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
        userLabel.setBounds(400, 50, 200, 50);
        profileButton.setBounds(50, 150, 200, 40);
        accountButton.setBounds(250, 150, 200, 40);
        checkTransactionButton.setBounds(50, 250, 200, 40);
        createAccountButton.setBounds(250, 250, 200, 40);
        loanButton.setBounds(50, 350, 200, 40);
        makeTransactionButton.setBounds(250, 350, 200, 40);
        logoutButton.setBounds(400, 450, 100, 35);

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
    }

    public void addActionEvent() {
        profileButton.addActionListener(this);
        accountButton.addActionListener(this);
        checkTransactionButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        loanButton.addActionListener(this);
        makeTransactionButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == profileButton) {
            ProfileFrame.getProfile(username);
        }
        if (e.getSource() == accountButton) {

        }
        if (e.getSource() == checkTransactionButton) {

        }
        if (e.getSource() == createAccountButton) {
            new CreateAccountFrame(this.user);
        }
        if (e.getSource() == loanButton) {

        }
        if (e.getSource() == makeTransactionButton) {


        }
        if (e.getSource() == logoutButton) {
            dispose();
        }
    }

    public static void getCustomerAccount(String username) {
        new CustomerMainMenuFrame(username);
    }

}