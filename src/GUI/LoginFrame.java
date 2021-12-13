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

    private final Container container = getContentPane();
    private final JLabel userLabel;
    private final JLabel passwordLabel;
    private final JTextField userTextField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton resetButton;
    private final JCheckBox showPassword;


    public LoginFrame() {
        setTitle("Login Form");
        setVisible(true);
        setBounds(10,10,1000,800);
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
        userLabel.setBounds(300, 250, 100, 50);
        passwordLabel.setBounds(300, 400, 100, 50);
        userTextField.setBounds(550, 250, 150, 40);
        passwordField.setBounds(550, 400, 150, 40);
        showPassword.setBounds(550, 450, 150, 40);
        loginButton.setBounds(300, 500, 100, 40);
        resetButton.setBounds(600, 500, 100, 40);


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
                        new CustomerMainMenuFrame(userText);
                    }else{
                        //jump to manager account frame
                        JOptionPane.showMessageDialog(this, "Manager Login Successful");
                        dispose();
                        new ManagerAccountFrame(userText);
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
