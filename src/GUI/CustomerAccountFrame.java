package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountFrame extends JFrame implements ActionListener {
    String username;
    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton accountButton = new JButton("Check your accounts");
    JButton checkTransactionButton = new JButton("View transactions");
    JButton profileButton = new JButton("GUI.Profile");
    JButton createAccountButton = new JButton("Create a checkings/savings account");
    JButton loanButton = new JButton("Request Loan");
    JButton makeTransactionButton = new JButton("Make transaction");
    JButton logoutButton = new JButton("Logout");


    public CustomerAccountFrame(String username) {
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
            Profile.getProfile(username);
        }
        if (e.getSource() == accountButton) {

        }
        if (e.getSource() == checkTransactionButton) {

        }
        if (e.getSource() == createAccountButton) {

        }
        if (e.getSource() == loanButton) {

        }
        if (e.getSource() == makeTransactionButton) {


        }
        if (e.getSource() == logoutButton) {
            dispose();
        }
    }

//    public String selectPassword(String userText){
//        String sql = "SELECT * "
//                + "FROM loginInfo WHERE username = '" + userText + "'";
//        String url = "jdbc:sqlite:bankAtm.db";
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt  = conn.createStatement();
//             ResultSet rs    = stmt.executeQuery(sql)){
//
////            // set the value
////            pstmt.setDouble(1,capacity);
//            //
////            ResultSet rs  = pstmt.executeQuery();
//
//            // loop through the result set
//            while (rs.next()) {
////                System.out.println(rs.getInt("id") +  "\t" +
////                        rs.getString("name") + "\t" +
////                        rs.getDouble("capacity"));
//                return rs.getString("password");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

}