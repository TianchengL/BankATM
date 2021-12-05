import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignupFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel nameLabel = new JLabel("Name");
    JLabel userLabel = new JLabel("Username");
    JLabel addressLabel = new JLabel("Address");
    JLabel passwordLabel = new JLabel("password");
    JTextField nameTextField = new JTextField();
    JTextField addressTextField = new JTextField();
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton createButton = new JButton("CREATE ACCOUNT");
    JButton resetButton = new JButton("RESET");


    SignupFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        nameLabel.setBounds(100, 150, 100, 40);
        userLabel.setBounds(100, 200, 100, 40);
        addressLabel.setBounds(100, 250, 100, 40);
        passwordLabel.setBounds(100, 300, 100, 40);
        nameTextField.setBounds(250, 150, 150, 30);
        userTextField.setBounds(250, 200, 150, 30);
        addressTextField.setBounds(250, 250, 150, 30);
        passwordField.setBounds(250, 300, 150, 30);
        createButton.setBounds(100, 400, 200, 35);
        resetButton.setBounds(300, 400, 100, 35);


    }

    public void addComponentsToContainer() {
        container.add(nameLabel);
        container.add(userLabel);
        container.add(addressLabel);
        container.add(passwordLabel);
        container.add(nameTextField);
        container.add(userTextField);
        container.add(addressTextField);
        container.add(passwordField);
        container.add(createButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        createButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void reset(){
        nameTextField.setText("");
        userTextField.setText("");
        addressTextField.setText("");
        passwordField.setText("");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == createButton) {
            String nameText;
            String userText;
            String addressText;
            String pwdText;
            nameText = nameTextField.getText();
            userText = userTextField.getText();
            addressText = addressTextField.getText();
            pwdText = passwordField.getText();
            if(nameText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a name");
            }
            else if(userText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(pwdText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a password");
            }
            else if(this.select(userText)){
                JOptionPane.showMessageDialog(this, "This user exists. Please enter a different username");
                reset();
            }
            else{
                Customers customer = new Customers();
                customer.setName(nameText);
                customer.setAddress(addressText);
                customer.setUsername(userText);
                customer.setPassword(pwdText);
                customer.insert();
                JOptionPane.showMessageDialog(this, "Account created! Please login to your account");
                dispose();
                Login.login();
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            reset();
        }
    }

    public boolean select(String userText){
        String sql = "SELECT * "
                + "FROM loginInfo WHERE username = '" + userText + "'";
//        ResultSet rs = SelectQuery.select(sql);
//        if (rs.next()) {
//            return true;
//        }
        String url = "jdbc:sqlite:bankAtm.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}