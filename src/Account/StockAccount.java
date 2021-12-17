package Account;

import Collection.StockCollection;
import Currency.*;
import Stock.Stock;
import Utility.ID;
import User.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//concrete class for stock account
public class StockAccount extends Account {
    public StockAccount(ID accountID, Currency currency, Date openDate, Money deposit, User user) {
        super(accountID, currency, openDate, deposit, user);
    }

    //buy stock according to stock name
    public boolean buyStock(String stockName,int amount){
        Stock stock = StockCollection.getInstance().findStockByName(stockName);
        double cost = amount*stock.getPrice();
        if(super.getDeposit().getAmount()>=cost){
            withdraw(cost,true,"Buy stocks: "+stockName+" amount:"+amount);
            List<Stock> stocks = super.getStockOrderHistory();
            for(Stock stock1: stocks){
                if(Objects.equals(stock1.getName(), stockName)){
                    stock1.setAmount(stock1.getAmount() + amount);
                    return true;
                }
            }
            super.getStockOrderHistory().add(new Stock(stock.getName(),stock.getPrice(),amount));
            return true;
        }
        return false;
    }

    //sell stock according to stock name
    public boolean sellStock(String stockName,int amount){

        for(Stock stock:super.getStockOrderHistory()){
            if(stock.getName().equals(stockName)){
//                Stock presentStock = StockCollection.getInstance().findStockByName(stockName);
                double income = stock.getPrice()*amount;
                if(!stock.isSold && stock.getAmount()>=amount){
                    deposit(income,super.getCurrency(),true,"Sell stocks:"+ stockName+ " amount:"+amount);
                    stock.deductAmount(amount);
                    stock.setSold();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public AccountType getType() {
        return AccountType.STOCK_ACCOUNT;
    }
}
