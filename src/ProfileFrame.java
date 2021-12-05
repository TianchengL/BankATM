import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProfileFrame extends JFrame implements ActionListener {
    Customers customer;
    String username;
    Container container = getContentPane();
    JLabel nameLabel = new JLabel("Name: ");
    JLabel userLabel = new JLabel("Username: ");
    JLabel addressLabel = new JLabel("Address: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JLabel name = new JLabel();
    JLabel uname = new JLabel();
    JLabel address = new JLabel();
    JLabel password = new JLabel();
    JLabel resetLabel = new JLabel("Reset Password");
    JButton returnButton = new JButton("Back");
    JButton resetPasswordButton = new JButton("Reset Password");
//    JTextField oldPwdField = new JTextField();
    JTextField newPwdField = new JTextField();


    ProfileFrame(String username) {
        this.username = username;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        customer = Customers.retrieveCustomer(username);
//        System.out.println(customer.getName());
        name.setText(customer.getName());
        uname.setText(customer.getUsername());
        address.setText(customer.getAddress());
        password.setText(customer.getPassword());
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(100, 150, 100, 40);
        userLabel.setBounds(100, 200, 100, 40);
        addressLabel.setBounds(100, 250, 100, 40);
        passwordLabel.setBounds(100, 300, 100, 40);
        name.setBounds(250, 150, 200, 40);
        uname.setBounds(250, 200, 200, 40);
        address.setBounds(250, 250, 200, 40);
        password.setBounds(250, 300, 200, 40);
        resetLabel.setBounds(100, 350, 100, 40);
//        oldPwdField.setBounds(200, 400, 200, 30);
        newPwdField.setBounds(250, 350, 200, 30);
        returnButton.setBounds(300, 450, 100, 35);
        resetPasswordButton.setBounds(100, 450, 200, 35);
    }

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(userLabel);
        container.add(addressLabel);
        container.add(passwordLabel);
        container.add(name);
        container.add(uname);
        container.add(address);
        container.add(password);
        container.add(resetPasswordButton);
        container.add(returnButton);
        container.add(resetLabel);
        container.add(newPwdField);
//        container.add(oldPwdField);
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
//                customer.setPassword(newPwd);
//                this.updatePassword();
                customer.updatePassword(newPwd);
                JOptionPane.showMessageDialog(this, "Password Updated");
                password.setText(customer.getPassword());
                newPwdField.setText("");
            }
        }
        //Coding Part of return button
        if (e.getSource() == returnButton) {
            dispose();
        }
    }

//    public void updatePassword(){
//        String sql = "UPDATE loginInfo "
//                + "SET password = '" + customer.getPassword() + "'"
//                + " WHERE username = '" + customer.getUsername() + "';";
//        ExecuteSqlCommand.executeCommand(sql);
//    }

}