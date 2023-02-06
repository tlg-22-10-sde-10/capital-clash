package account;

import stock.Stock;
import java.util.ArrayList;


public class Account {

    private String accountName;

    private double cashBalance=10000;

    public Account(String accountName) {
        this.accountName = accountName;

    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void deductBalance(double amount) {
        if(cashBalance>amount) {
            this.cashBalance -= amount;
        }
    }

    public void calculateBalance(Double priceOfStock) {
        this.cashBalance+=priceOfStock;

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



