package players;

import account.Account;
import stock.Stock;

import java.util.List;

//fields
public class Computer {
    private String name;
    private Account account;
    private List<Stock> stocks;
    private List<String> stockNames;


//constructor


    public Computer(String name, Account account, List<Stock> stocks) {
        this.name = name;
        this.account = account;
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
    public List<String> getStockNames() {
        return stockNames;
    }

    public void setStockNames(List<String> stockNames) {
        this.stockNames = stockNames;
    }
}
