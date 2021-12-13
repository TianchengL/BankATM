package GUI;

import User.User;
import Collection.UserCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;

public class CustomerList extends JFrame{
    private JComboBox comboBoxUserId;
    private JButton continueButton;
    private JPanel customerListPanel;
    private JButton backButton;
    private List<User> users;

    public CustomerList() {
        setTitle("Select Customer");
        setContentPane(customerListPanel);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        users = UserCollection.getInstance().getUsers();

        for(User user: users){
            if(user.getType() == User.UserType.MANAGER)
                continue;
            comboBoxUserId.addItem(user.getId());
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBoxUserId.getSelectedItem());
                new ViewCustomerAccountsFrame(Integer.parseInt(comboBoxUserId.getSelectedItem().toString()));
            }
        });
    }

    public static void main(String[] args) {
        CustomerList c = new CustomerList();
        //Account.Account.AccountType t = (Account.AccountType) c.comboBoxAccountType.getSelectedItem();

    }

}
