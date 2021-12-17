package GUI;

import Collection.StockCollection;
import Collection.TransactionCollection;
import User.BankManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerAccountFrame extends JFrame implements ActionListener {
    private final String username;
    private final Container container;
    private final JLabel userLabel;
    private final JButton checkCustomerInfoButton;
    private final JButton viewTransactionButton;
    private final JButton profileButton;
    private final JButton viewProfitButton;
    private final JButton interestButton;
    private final JButton stocksButton;
    private JTextField interestField;
    private final JButton logoutButton;


    public ManagerAccountFrame(String username) {
        setTitle("Manager Account Form");
        setVisible(true);
        setBounds(10,10,1000,800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        container = getContentPane();
        userLabel = new JLabel();
        checkCustomerInfoButton = new JButton("Check customer accounts");
        viewTransactionButton = new JButton("View transactions");
        profileButton = new JButton("Profile");
        viewProfitButton = new JButton("View profit");
        stocksButton = new JButton("Manage stocks");
        interestButton = new JButton("Set Interest Rate");
        logoutButton = new JButton("Logout");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.username = username;
        userLabel.setText("Username: " + this.username);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(700, 100, 200, 50);
        profileButton.setBounds(300, 250, 400, 40);
        checkCustomerInfoButton.setBounds(300, 300, 400, 40);
        viewTransactionButton.setBounds(300, 350, 400, 40);
        viewProfitButton.setBounds(300, 400, 400, 40);
        stocksButton.setBounds(300, 450, 400, 40);
        interestButton.setBounds(300, 500, 400, 40);
//        makeTransactionButton.setBounds(250, 350, 200, 40);
        logoutButton.setBounds(700, 600, 100, 35);

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(profileButton);
        container.add(checkCustomerInfoButton);
        container.add(viewTransactionButton);
        container.add(viewProfitButton);
        container.add(stocksButton);
        container.add(interestButton);
        container.add(logoutButton);
    }

    public void addActionEvent() {
        profileButton.addActionListener(this);
        checkCustomerInfoButton.addActionListener(this);
        viewTransactionButton.addActionListener(this);
        viewProfitButton.addActionListener(this);
        stocksButton.addActionListener(this);
        interestButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == profileButton) {
            new ProfileFrame(username);
        }
        if (e.getSource() == checkCustomerInfoButton) {
            new CustomerList();
        }
        if (e.getSource() == viewTransactionButton) {
            TransactionCollection.getInstance().addAllTransactions();
            new ViewTransactions(TransactionCollection.getInstance().getTransactions());
        }
        if (e.getSource() == viewProfitButton) {
            JOptionPane.showMessageDialog(this, "Total Profit: " + BankManager.getProfit());
        }
        if (e.getSource() == stocksButton) {
            StockCollection.getInstance().addAllStocks();
            new ManageStock();
        }
        if (e.getSource() == interestButton) {
            new InterestRateFrame();
        }
        if (e.getSource() == logoutButton) {
            dispose();
        }
    }


}