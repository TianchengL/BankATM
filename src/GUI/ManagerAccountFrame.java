package GUI;

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
    private final JButton stocksButton;
    private final JButton logoutButton;


    public ManagerAccountFrame(String username) {
        setTitle("Manager Account Form");
        setVisible(true);
        setBounds(10,10,600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        container = getContentPane();
        userLabel = new JLabel();
        checkCustomerInfoButton = new JButton("Check customer information");
        viewTransactionButton = new JButton("View customer transactions");
        profileButton = new JButton("Profile");
        viewProfitButton = new JButton("View customer profile");
        stocksButton = new JButton("Manage stocks");
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
        userLabel.setBounds(400, 50, 200, 50);
        profileButton.setBounds(150, 150, 300, 40);
        checkCustomerInfoButton.setBounds(150, 200, 300, 40);
        viewTransactionButton.setBounds(150, 250, 300, 40);
        viewProfitButton.setBounds(150, 300, 300, 40);
        stocksButton.setBounds(150, 350, 300, 40);
//        makeTransactionButton.setBounds(250, 350, 200, 40);
        logoutButton.setBounds(400, 450, 100, 35);

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(profileButton);
        container.add(checkCustomerInfoButton);
        container.add(viewTransactionButton);
        container.add(viewProfitButton);
        container.add(stocksButton);
        container.add(logoutButton);
    }

    public void addActionEvent() {
        profileButton.addActionListener(this);
        checkCustomerInfoButton.addActionListener(this);
        viewTransactionButton.addActionListener(this);
        viewProfitButton.addActionListener(this);
        stocksButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == profileButton) {
            new ProfileFrame(username);
        }
        if (e.getSource() == checkCustomerInfoButton) {

        }
        if (e.getSource() == viewTransactionButton) {

        }
        if (e.getSource() == viewProfitButton) {

        }
        if (e.getSource() == stocksButton) {

        }
        if (e.getSource() == logoutButton) {
            dispose();
        }
    }


}