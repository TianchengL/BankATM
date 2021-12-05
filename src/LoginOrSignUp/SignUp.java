package LoginOrSignUp;

import GUI.SignupFrame;

import javax.swing.*;

public class SignUp {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String passwordConfirm;

    public SignUp(String firstName, String lastName, String userName, String password, String confirmPW){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = confirmPW;
    }




   /** Might need to provide some method to check if input password meet some constraints
    * such as at least one Capital letter. ect...**/

    //check if password and confirm password the same
    //return true if its same
    public boolean checkPassword(){
        return false;
    }


    //Every GUI staff needs to put into GUI directory
    public static void signUp(){

        SignupFrame frame=new SignupFrame();
        frame.setTitle("Signup Form");
        frame.setVisible(true);
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }

}