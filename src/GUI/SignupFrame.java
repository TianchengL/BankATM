package GUI;

import Database.Database;
import LoginOrSignUp.SignUp;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class SignupFrame extends JFrame implements ActionListener{

    private final Container container = getContentPane();
    private final JLabel firstNameLabel;
    private final JLabel lastNameLabel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JLabel passwordConfirmLabel;
    private final JTextField firstName;
    private final JTextField lastName;
    private final JTextField username;
    private final JPasswordField passwordConfirm;
    private final JPasswordField password;
    private final JButton createButton;
    private final JButton resetButton;


    public SignupFrame() {
        setTitle("Signup Form");
        setVisible(true);
        setBounds(10,10,600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        passwordConfirmLabel = new JLabel("Confirm password");
        firstName = new JTextField();
        lastName = new JTextField();
        username = new JTextField();
        passwordConfirm = new JPasswordField();
        password = new JPasswordField();
        createButton = new JButton("CREATE ACCOUNT");
        resetButton = new JButton("RESET");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        firstNameLabel.setBounds(100, 150, 100, 40);
        lastNameLabel.setBounds(100, 200, 100, 40);
        usernameLabel.setBounds(100, 250, 100, 40);
        passwordLabel.setBounds(100, 300, 100, 40);
        passwordConfirmLabel.setBounds(100, 350, 150, 40);
        firstName.setBounds(250, 150, 150, 30);
        lastName.setBounds(250, 200, 150, 30);
        username.setBounds(250, 250, 150, 30);
        password.setBounds(250, 300, 150, 30);
        passwordConfirm.setBounds(250, 350, 150, 30);
        createButton.setBounds(100, 400, 200, 35);
        resetButton.setBounds(300, 400, 100, 35);


    }

    public void addComponentsToContainer() {
        container.add(firstNameLabel);
        container.add(lastNameLabel);
        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(passwordConfirmLabel);
        container.add(firstName);
        container.add(lastName);
        container.add(username);
        container.add(password);
        container.add(passwordConfirm);
        container.add(createButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        createButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    private void reset(){
        firstName.setText("");
        lastName.setText("");
        username.setText("");
        password.setText("");
        passwordConfirm.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String firstname;
            String lastname;
            String userName;
            String pw;
            String pc;
            firstname = firstName.getText();
            lastname = lastName.getText();
            userName = username.getText();
            pw = password.getText();
            pc = passwordConfirm.getText();
            if(firstname.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a first name");
            }
            else if(lastname.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a last name");
            }
            else if(userName.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(pw.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a password");
            }
            else if(!Objects.equals(pc, pw)){
                JOptionPane.showMessageDialog(this, "Passwords do not match. Please re-enter passwords");
                password.setText("");
                passwordConfirm.setText("");
            }
            else if(!Database.checkUserExist(userName).isEmpty()){
                JOptionPane.showMessageDialog(this, "User already exists. Please enter a different username");
                username.setText("");
            }
            else{
                SignUp signUp = new SignUp(firstname, lastname, userName, User.UserType.CUSTOMER, pw, pc);
                signUp.addUser();

                JOptionPane.showMessageDialog(this,
                        "Account created! Please login to your account");
                dispose();
                new LoginFrame();
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            reset();
        }
    }

}
