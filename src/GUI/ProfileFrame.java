package GUI;

import Collection.UserCollection;
import Database.Database;
import User.Customers;
import User.User;
import User.BankManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ProfileFrame extends JFrame implements ActionListener {
    private User user;
    private String username;
    private final Container container = getContentPane();;
    private final JLabel firstNameLabel;
    private final JLabel lastNameLabel;
    private final JLabel userLabel;
    private final JLabel idLabel;
    private final JLabel passwordLabel;
    private final JLabel firstName;
    private final JLabel lastName;
    private final JLabel uname;
    private final JLabel id;
    private final JLabel password;
    private final JLabel resetLabel;
    private final JButton returnButton;
    private final JButton resetPasswordButton;
    private final JTextField newPwdField;


    public ProfileFrame(String username) {
        setTitle("User Profile");
        setVisible(true);
        setBounds(10,10,600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.username = username;
        firstNameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        userLabel = new JLabel("Username: ");
        idLabel = new JLabel("User Id: ");
        passwordLabel = new JLabel("Password: ");
        firstName = new JLabel();
        lastName = new JLabel();
        uname = new JLabel();
        id = new JLabel();
        password = new JLabel();
        resetLabel = new JLabel("Reset Password");
        returnButton = new JButton("Back");
        resetPasswordButton = new JButton("Reset Password");
        newPwdField = new JTextField();
        if(Objects.equals(Database.getUserType(username), "Manager"))
            user = (BankManager) UserCollection.getInstance().retrieveUser(username);
        else
            user = (Customers) UserCollection.getInstance().retrieveUser(username);
        firstName.setText(user.getFirstname());
        lastName.setText(user.getLastname());
        uname.setText(user.getUsername());
        id.setText("" + user.getId()+ "");
        password.setText(Database.checkUserExist(user.getUsername()));
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        idLabel.setBounds(100, 150, 100, 40);
        firstNameLabel.setBounds(100, 200, 100, 40);
        lastNameLabel.setBounds(100, 250, 100, 40);
        userLabel.setBounds(100, 300, 100, 40);
        passwordLabel.setBounds(100, 350, 100, 40);
        id.setBounds(250, 150, 200, 40);
        firstName.setBounds(250, 200, 200, 40);
        lastName.setBounds(250, 250, 200, 40);
        uname.setBounds(250, 300, 200, 40);
        password.setBounds(250, 350, 200, 40);
        resetLabel.setBounds(100, 400, 100, 40);
//        oldPwdField.setBounds(200, 400, 200, 30);
        newPwdField.setBounds(250, 400, 200, 30);
        returnButton.setBounds(300, 500, 100, 35);
        resetPasswordButton.setBounds(100, 500, 200, 35);
    }

    public void addComponentsToContainer() {
        container.add(firstNameLabel);
        container.add(lastNameLabel);
        container.add(userLabel);
        container.add(idLabel);
        container.add(passwordLabel);
        container.add(firstName);
        container.add(lastName);
        container.add(uname);
        container.add(id);
        container.add(password);
        container.add(resetPasswordButton);
        container.add(returnButton);
        container.add(resetLabel);
        container.add(newPwdField);
    }

    public void addActionEvent() {
        resetPasswordButton.addActionListener(this);
        returnButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == resetPasswordButton) {
            String newPwd;
            newPwd = newPwdField.getText();
            if(newPwd.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter the new password");
            }
            else{
//                user.setPassword(newPwd);
//                this.updatePassword();
                Database.updatePassword(username, newPwd);
                JOptionPane.showMessageDialog(this, "Password Updated");
                password.setText(Database.checkUserExist(user.getUsername()));
                newPwdField.setText("");
            }
        }
        //Coding Part of return button
        if (e.getSource() == returnButton) {
            dispose();
        }
    }


}