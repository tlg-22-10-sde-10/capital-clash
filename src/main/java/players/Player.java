package players;

import account.Account;

//fields
public class Player {
    private String name;
    private Account account;

    //constructor
    public Player(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public Player() {

    }

    //get&set


    public String getName() {  //returns players name
        return "You";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {  //return account value
        return 10000;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}