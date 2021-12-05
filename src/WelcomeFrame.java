import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel welcomeLabel = new JLabel("Bank ATM");
    JButton loginButton = new JButton("Login");
    JButton createButton = new JButton("Create Account");


    WelcomeFrame() {
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
            Login.login();
        }
        else if(e.getSource() == createButton) {
            SignUp.signUp();
        }
    }

}