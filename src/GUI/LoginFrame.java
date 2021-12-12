package GUI;

import Database.Database;
import LoginOrSignUp.Login;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginFrame extends JFrame implements ActionListener {
//    private JPanel LoginPanel;
//    private JPanel westLabelPanel;
//    private JPanel southLabelPanel;
//    private JLabel usernameLabel;
//    private JPanel centerLabelPanel;
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JLabel passwordLabel;
//    private JButton loginButton;
//    private JButton resetButton;
//    private JCheckBox showPwdCheckBox;
//
//    public LoginFrame(){
//        setContentPane(LoginPanel);
//        setTitle("Login Form");
//        setSize(450, 450);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        loginButton.addActionListener(this);
//        resetButton.addActionListener(this);
//        showPwdCheckBox.addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == loginButton) {
//            String userText;
//            char[] pwdText;
//            userText = usernameField.getText();
//            pwdText = passwordField.getPassword();
//
//            if(userText.isEmpty()){
//                JOptionPane.showMessageDialog(this, "Please enter a username");
//            }
//            else if(pwdText.length == 0){
//                JOptionPane.showMessageDialog(this, "Please enter a password");
//            }else{
//
//                Login login = new Login(userText, String.valueOf(pwdText));
//                if(login.findUser().isEmpty()){
//                    JOptionPane.showMessageDialog(this, "No user found, Please go signUp");
//                }else if(login.run()){
//                    if(login.getUserType().equals("Customer")){
//                        //need to jump to customer account frame
//                        JOptionPane.showMessageDialog(this, "Customer Login Successful");
//                    }else{
//                        //jump to manager account frame
//                        JOptionPane.showMessageDialog(this, "Manager Login Successful");
//                    }
//
//                    //dispose();
//
//                }
//            }
//
//        }
//        //Coding Part of showPassword JCheckBox
//        if (e.getSource() == showPwdCheckBox) {
//            if (showPwdCheckBox.isSelected()) {
//                passwordField.setEchoChar((char) 0);
//            } else {
//                passwordField.setEchoChar('*');
//            }
//        }
//        //Coding Part of RESET button
//        if (e.getSource() == resetButton) {
//            usernameField.setText("");
//            passwordField.setText("");
//        }
//    }


    private final Container container = getContentPane();
    private final JLabel userLabel;
    private final JLabel passwordLabel;
    private final JTextField userTextField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton resetButton;
    private final JCheckBox showPassword;

//    public LoginFrame(){
//        setContentPane(LoginPanel);
//        setTitle("Login Form");
//        setSize(450, 450);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        loginButton.addActionListener(this);
//        resetButton.addActionListener(this);
//        showPwdCheckBox.addActionListener(this);
//    }

    public LoginFrame() {
        setTitle("Login Form");
        setVisible(true);
        setBounds(10,10,600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        userLabel = new JLabel("USERNAME");
        passwordLabel = new JLabel("PASSWORD");
        userTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("LOGIN");
        resetButton = new JButton("RESET");
        showPassword = new JCheckBox("Show Password");
        setResizable(false);
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

    public void reset() {
        userTextField.setText("");
        passwordField.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button

        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());

            if(userText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(pwdText.length() == 0){
                JOptionPane.showMessageDialog(this, "Please enter a password");
            }
            else if(!Objects.equals(Database.checkUserExist(userText), pwdText)){
                JOptionPane.showMessageDialog(this, "Invalid username or password");
                reset();
            }
            else{

                Login login = new Login(userText, pwdText);
                if(login.findUser().isEmpty()){
                    JOptionPane.showMessageDialog(this, "No user found, Please create an account");
                }else if(login.run()){
                    if(login.getUserType().equals("Customer")){
                        //need to jump to customer account frame
                        JOptionPane.showMessageDialog(this, "Customer Login Successful");
                        dispose();
                        CustomerMainMenuFrame.getCustomerAccount(userText);
                    }else{
                        //jump to manager account frame
                        JOptionPane.showMessageDialog(this, "Manager Login Successful");
                        dispose();
                        ManagerAccountFrame.getManagerAccount(userText);
                    }

                    //dispose();

                }
            }

        }


        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            reset();
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
}
