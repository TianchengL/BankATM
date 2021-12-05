package GUI;

import GUI.ProfileFrame;

import javax.swing.*;

public class Profile {
    public static void getProfile(String username){
        ProfileFrame frame=new ProfileFrame(username);
        frame.setTitle("User GUI.Profile");
        frame.setVisible(true);
        frame.setBounds(10,10,600,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }

}