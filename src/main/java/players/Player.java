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

        //get&set


        public String getName() {  //returns players name
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Account getAccount() {  //return account value
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }
