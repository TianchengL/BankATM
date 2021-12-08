package GUI;

import LoginOrSignUp.SignUp;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupFrame extends JFrame implements ActionListener{

    private JTextField firstName;
    private JTextField lastName;
    private JTextField username;
    private JTextField password;
    private JTextField passwordConfirm;
    private JButton OKButton;
    private JButton resetButton;
    private JPanel signupPanel;

    public SignupFrame(){
        setContentPane(signupPanel);
        setTitle("Signup Form");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        OKButton.addActionListener(this);
        resetButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OKButton) {
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
                JOptionPane.showMessageDialog(this, "Please enter a name");
            }
            else if(lastname.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(userName.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
            else if(pw.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a password");
            }
//            else if(this.select(userText)){
//                JOptionPane.showMessageDialog(this, "This user exists. " +
//                        "Please enter a different username");
//                reset();
//            }
            else{
                SignUp signUp = new SignUp(firstname, lastname, userName, User.UserType.CUSTOMER, pw, pc);
                signUp.addUser();



                JOptionPane.showMessageDialog(this,
                        "Account.Account created! Please login to your account");
                dispose();
                //Login.login();
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            reset();
        }
    }

    private void reset(){
        firstName.setText("");
        lastName.setText("");
        username.setText("");
        password.setText("");
        passwordConfirm.setText("");
    }


    public static void main(String[] args) {
        SignupFrame signupFrame = new SignupFrame();
    }
}
