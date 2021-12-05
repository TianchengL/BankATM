package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    public LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(100, 150, 100, 40);
        passwordLabel.setBounds(100, 220, 100, 40);
        userTextField.setBounds(250, 150, 150, 30);
        passwordField.setBounds(250, 220, 150, 30);
        showPassword.setBounds(250, 250, 150, 30);
        loginButton.setBounds(100, 300, 100, 35);
        resetButton.setBounds(300, 300, 100, 35);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if(userText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(pwdText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a password");
            }
            else if (pwdText.equalsIgnoreCase(this.selectPassword(userText))) {
                JOptionPane.showMessageDialog(this, "Login.Login Successful");
                dispose();
                CustomerAccount.customerAccount(userText);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

    public String selectPassword(String userText){
        String sql = "SELECT * "
                + "FROM loginInfo WHERE username = '" + userText + "'";
        String url = "jdbc:sqlite:bankAtm.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

//            // set the value
//            pstmt.setDouble(1,capacity);
            //
//            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
//                System.out.println(rs.getInt("id") +  "\t" +
//                        rs.getString("name") + "\t" +
//                        rs.getDouble("capacity"));
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}