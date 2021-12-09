package GUI;

import Database.Database;
import LoginOrSignUp.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginFrame extends JFrame implements ActionListener {
    private JPanel LoginPanel;
    private JPanel westLabelPanel;
    private JPanel southLabelPanel;
    private JLabel usernameLabel;
    private JPanel centerLabelPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton resetButton;
    private JCheckBox showPwdCheckBox;

    public LoginFrame(){
        setContentPane(LoginPanel);
        setTitle("Login Form");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPwdCheckBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            char[] pwdText;
            userText = usernameField.getText();
            pwdText = passwordField.getPassword();

            if(userText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(pwdText.length == 0){
                JOptionPane.showMessageDialog(this, "Please enter a password");
            }else{

                Login login = new Login(userText, String.valueOf(pwdText));
                if(!login.findUser()){
                    JOptionPane.showMessageDialog(this, "No user found, Please go signUp");
                }else if(login.run()){
                    if(login.getUserType().equals("Customer")){
                        //need to jump to customer account frame
                        JOptionPane.showMessageDialog(this, "Customer Login Successful");
                    }else{
                        //jump to manager account frame
                        JOptionPane.showMessageDialog(this, "Manager Login Successful");
                    }

                    //dispose();

                }
            }

        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPwdCheckBox) {
            if (showPwdCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
