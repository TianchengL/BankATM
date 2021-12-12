package GUI;

import Database.Database;
import LoginOrSignUp.Login;
import LoginOrSignUp.SignUp;
import User.User;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame implements ActionListener {

    private final Container container;
    private final JLabel welcomeLabel;
    private final JButton loginButton;
    private final JButton createButton;

    public WelcomeFrame() {
        setTitle("Welcome Form");
        setVisible(true);
        setBounds(10,10,600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        container = getContentPane();
        welcomeLabel = new JLabel("Bank ATM");
        loginButton = new JButton("Login");
        createButton = new JButton("Create Account");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        welcomeLabel.setFont(new Font("Courier", Font.BOLD,40));
        welcomeLabel.setBounds(200, 150, 200, 60);
        loginButton.setBounds(100, 300, 100, 40);
        createButton.setBounds(350, 300, 150, 40);
    }

    public void addComponentsToContainer() {
        container.add(welcomeLabel);
        container.add(loginButton);
        container.add(createButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        createButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            new LoginFrame();
            dispose();
        }
        else if(e.getSource() == createButton) {
            new SignupFrame();
            dispose();
        }
    }

    public static void main(String[] args) {
        Database database = new Database();
        Database.addPreviousUsers();
        //default manager
//        SignUp signUp = new SignUp("Cristine", "CPK", "boss",
//                User.UserType.MANAGER, "123", "123");
//        signUp.addUser();
        WelcomeFrame frame=new WelcomeFrame();
        frame.setTitle("Welcome Form");
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setVisible(true);
        //new WelcomeFrame();
    }

}