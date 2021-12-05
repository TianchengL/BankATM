import javax.swing.*;

public class SignUp {
    public static void signUp(){
        SignupFrame frame=new SignupFrame();
        frame.setTitle("Signup Form");
        frame.setVisible(true);
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }

}