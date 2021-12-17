package Account;

import Collection.StockCollection;
import Currency.*;
import Stock.Stock;
import Utility.ID;
import User.*;
import java.util.Date;


public class StockAccount extends Account {
    public StockAccount(ID accountID, Currency currency, Date openDate, Money deposit, User user) {
        super(accountID, currency, openDate, deposit, user);
    }

    public boolean buyStock(String stockName,int amount){
        Stock stock = StockCollection.getInstance().findStockByName(stockName);
        double cost =amount*stock.getPrice();
        if(super.getDeposit().getAmount()>=cost){
            withdraw(cost,true,"Buy stocks: "+stockName+" amount:"+amount);
            super.getStockOrderHistory().add(new Stock(stock.getName(),stock.getPrice(),amount));
            return true;
        }
        return false;
    }

    public boolean sellStock(String stockName,int amount){

        for(Stock stock:super.getStockOrderHistory()){
            if(stock.getName().equals(stockName)){
                Stock presentStock = StockCollection.getInstance().findStockByName(stockName);
                double income = presentStock.getPrice()*amount;
                if(stock.isSold==false&&stock.getAmount()>=amount){
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
