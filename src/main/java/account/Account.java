package account;

import stock.Stock;
import java.util.ArrayList;


public class Account {

    private String accountName;
    private double stockValueBalance;
    private ArrayList<Stock> stockHolding;
    private Stock stock;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getCashBalance() {
        return 10000;
    }

    public void setCashBalance(double cashBalance) {
    }

    public double getStockValueBalance() {
        return stockValueBalance;
    }

    public void setStockValueBalance(double stockValueBalance) {
        this.stockValueBalance = stockValueBalance;
    }

    public ArrayList<Stock> getStockHolding() {
        return stockHolding;
    }

    public void setStockHolding(ArrayList<Stock> stockHolding) {
        this.stockHolding = stockHolding;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


    @Override
    //refactor formula to show end results of last day.
    public String toString() {
        return "Both players balance: {" +
                "John's balance =" + getCashBalance() +
                ", Ben's Balance =" + getCashBalance() +
                '}';
    }


}



