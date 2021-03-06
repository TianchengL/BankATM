package GUI;

import Collection.StockCollection;
import Stock.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManageStock extends JFrame{
    private JTextField price;
    private JTextField stockName;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel ManageStock;
    private JTextArea stockList;

    public ManageStock(){
        setContentPane(ManageStock);
        setTitle("Manage Stock");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        List<Stock> List = StockCollection.getInstance().getStocks();
        for(Stock stock:List){
            stockList.append(stock.getName()+ " "+stock.getPrice()+ "\n\n");
        }


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockCollection.getInstance().addStock(new Stock(getStockname(),getPrice()));
                List<Stock> currentStockList= StockCollection.getInstance().getStocks();
                StockCollection.getInstance().saveStockToCSV(currentStockList);
                JOptionPane.showMessageDialog(ManageStock, "stock added");
                dispose();
            }
        });




    }

    private double getPrice(){
        return Double.parseDouble(price.getText());
    }
    private String getStockname() { return stockName.getText();}
}
