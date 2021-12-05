import javax.swing.*;

public class CustomerAccount {
    public static void customerAccount(String userText){
        CustomerAccountFrame frame=new CustomerAccountFrame(userText);
        frame.setTitle("User Account Form");
        frame.setVisible(true);
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }

}