package players;

import account.Account;
import stock.Stock;

import java.util.List;

//fields
public class Player extends Computer {

    public Player(String name, Account account, List<Stock> stocks) {
        super(name, account, stocks);
    }
}