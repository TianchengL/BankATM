import GUI.WelcomeFrame;

import javax.swing.*;

public class Welcome {
    public static void welcome(){
        WelcomeFrame frame=new WelcomeFrame();
        frame.setTitle("Welcome Form");
        frame.setVisible(true);
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }

}