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

public class Withdraw extends JFrame{
    private JTextField amount;
    private JButton cancelButton;
    private JButton withdrawButton;
    private JPanel WithdrawPanel;
    private JComboBox<String> checking;

    public Withdraw(User user){
        setContentPane(WithdrawPanel);
        setTitle("View Accounts Form");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        for(Account account : accounts){
            if(account.getType()== Account.AccountType.CHECKING_ACCOUNT){
                checking.addItem(account.getId().toString());
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

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account=(Account)checking.getSelectedItem();
                account.withdraw(getWithdraw(),true,"Withdraw:");

            }
        });
    }
    private double getWithdraw(){
        return Double.parseDouble(amount.getText());
    }
}
