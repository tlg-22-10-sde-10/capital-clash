package players;

import account.Account;

//fields
public class Computer {
    private String name;
    private Account account;

    //constructor
    public Computer(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public Computer() {

    }

    //get&set


    public String getName() {  //returns players name
        return "Brother";
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
