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

public class Transfer extends JFrame{
    private JComboBox<String> withdrawAccount;
    private JComboBox<String> depositAccount;
    private JButton transferButton;
    private JButton cancelButton;
    private JTextField amount;
    private JPanel transfer;

    public Transfer(User user){
        setContentPane(transfer);
        setTitle("View Accounts Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            withdrawAccount.addItem(account.getId().toString());
            depositAccount.addItem(account.getId().toString());
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
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
    private double getAmount(){
        return Double.parseDouble(amount.getText());
    }
}
