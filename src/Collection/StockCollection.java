package Collection;


import Stock.*;
import Transaction.Transaction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//a collection for each stock
public class StockCollection {
    private List<Stock> stocks;
    private static StockCollection instance;
    private final String CSV_Location = "src/Data/Stocks.csv";

    public StockCollection() {
        this.stocks = new ArrayList<>();
    }

    //singleton pattern
    public static StockCollection getInstance() {
        if(instance == null) instance = new StockCollection();
        return instance;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    //add entire stock from cvs file
    public void addAllStocks(){
        this.stocks = getStockFromFile();
    }

    //save all Stocks into csv file
    public void saveStockToCSV(List<Stock> Stocks){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(CSV_Location);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            for (Stock stock : Stocks) {
                outputStream.writeObject(stock);
                outputStream.reset();
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get stock from disk
    public List<Stock> getStockFromFile(){
        Object obj;
        List<Stock> stocks = new ArrayList<>();
        try
        {
            FileInputStream fileInputStream = new FileInputStream(CSV_Location);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            obj = inputStream.readObject();
            while(obj!= null){
                stocks.add((Stock) obj);
                obj = inputStream.readObject();
            }

        }
        catch(Exception ee)
        {
            //ee.printStackTrace();
        }
        return stocks;

    }

    //return stock according to name
    public Stock findStockByName(String stockName) {

        for (Stock stock : stocks) {
            if (stock.getName().equals(stockName)) {
                return stock;
            }
        }
        return null;
    }

    //add one stock to list
    public void addStock(Stock stock){
        stocks.removeIf(current -> current.getName().equals(stock.getName()));
        for(Stock stock1:stocks){
            System.out.println(stock1.getName());
        }
        System.out.println("");
        stocks.add(stock);
    }


}
