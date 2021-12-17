package GUI;

import Account.*;
import Collection.AccountCollection;
import Collection.StockCollection;
import Stock.Stock;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class StockGUI extends JFrame{
    private JPanel Stock;
    private JTextArea holdStocks;
    private JTextArea stockList;
    private JTextField stockName;
    private JButton buyButton;
    private JButton sellButton;
    private JLabel stockAmount;
    private JTextField amount;
    private JButton cancelButton;

    public StockGUI(User user) {
        setContentPane(Stock);
        setTitle("Stock");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        StockAccount stockAccount = getStockAccount(user);
        List<Stock> stockHoldList = stockAccount.getStockOrderHistory();
        for(Stock stock:stockHoldList){
            holdStocks.append(stock.getName()+ " "+stock.getAmount()+ "\n\n");
        }
        List<Stock> List = StockCollection.getInstance().getStocks();
        for(Stock stock:List){
            stockList.append(stock.getName()+ " "+stock.getPrice()+ "\n\n");
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

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockAccount stockAccount = getStockAccount(user);

                if(amount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(Stock, "Please enter the stock amount");

                }
                if(stockName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(Stock, "Please enter the stock name");
                }

                if(stockAccount.buyStock(getStockname(),getAmount())){
                    JOptionPane.showMessageDialog(Stock, "Paid!");
                    dispose();
                }
            }
        });

         sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockAccount stockAccount = getStockAccount(user);

                if(amount.getText().isEmpty()){
                    JOptionPane.showMessageDialog(Stock, "Please enter the stock amount");
                }
                if(stockName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(Stock, "Please enter the stock name");
                }

                if(stockAccount.sellStock(getStockname(),getAmount())){
                    JOptionPane.showMessageDialog(Stock, "Sold!");
                    dispose();
                }
            }
        });


    }

    private StockAccount getStockAccount(User user) {
        List<Account> accounts = AccountCollection.getInstance().getUserAccounts(user.getId());
        StockAccount stockAccount = null;
        for (Account account : accounts) {
            if (account instanceof StockAccount) {
                stockAccount = (StockAccount) account;
            }
        }
        return stockAccount;
    }


    private int getAmount(){
        return Integer.valueOf(amount.getText());
    }
    private String getStockname() { return stockName.getText();}

}
