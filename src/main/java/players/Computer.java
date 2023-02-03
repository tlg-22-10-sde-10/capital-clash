package players;

import account.Account;
import stock.Stock;

import java.util.List;
import java.util.Map;

//fields
public class Computer {
    private String name;
    private Account account;

    private Map<String,Integer> stocks;
    private List<String> stockNames;

    //constructor
    public Computer(String name, Account account) {
        this.name = name;
        this.account = account;
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

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    public void setStocks(Map<String, Integer> stocks) {
        this.stocks = stocks;
    }

    public List<String> getStockNames() {
        return stockNames;
    }

    public void setStockNames(List<String> stockNames) {

        this.stockNames = stockNames;
    }
}
