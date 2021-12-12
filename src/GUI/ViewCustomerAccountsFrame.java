package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCustomerAccountsFrame extends JFrame {
    private JPanel viewAccountsPanel;
    private JButton backButton;
    private JLabel userIDLabel;
    private JScrollPane scrollPane;

    public ViewCustomerAccountsFrame(int userID){
        setContentPane(viewAccountsPanel);
        setTitle("View Accounts Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        userIDLabel.setText(String.valueOf(userID));


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }
}
