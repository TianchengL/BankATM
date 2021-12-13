package GUI;

import Account.Account;
import Collection.AccountCollection;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Pay extends JFrame{
    private JButton cancelButton;
    private JTextField receiver;
    private JTextField amount;
    private JComboBox<String> accountsList;
    private JButton payButton;
    private JPanel PayPanel;

    public Pay(User user){
        setContentPane(PayPanel);
        setTitle("View Accounts Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            if(account.getType()== Account.AccountType.CHECKING_ACCOUNT || account.getType()== Account.AccountType.SAVING_ACCOUNT){
                accountsList.addItem(account.getId().toString());
            }
        }
        amount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        receiver.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
                super.keyTyped(e);
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account=(Account)accountsList.getSelectedItem();
                account.transferTo(getReceiver(),getamount());
            }
        });
    }

    private int getReceiver(){
        return Integer.valueOf(receiver.getText());
    }
    private double getamount(){
        return Double.parseDouble(amount.getText());
    }
}
