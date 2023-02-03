package ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import stock.Stock;
import storage.StockInventory;
import account.Account;
import players.Player;
import players.Computer;

public class UserInterface {
    private Scanner myScanner;
    public UserInterface() throws FileNotFoundException {
        myScanner = new Scanner(System.in);
    }


    public void displayASCII(){
        System.out.println("WELCOME TO!");
        System.out.println("  @@@@@@@  @@@@@@  @@@@@@@  @@@ @@@@@@@  @@@@@@  @@@            @@@@@@@ @@@       @@@@@@   @@@@@@ @@@  @@@\n" +
                " !@@      @@!  @@@ @@!  @@@ @@!   @@!   @@!  @@@ @@!           !@@      @@!      @@!  @@@ !@@     @@!  @@@\n" +
                " !@!      @!@!@!@! @!@@!@!  !!@   @!!   @!@!@!@! @!!           !@!      @!!      @!@!@!@!  !@@!!  @!@!@!@!\n" +
                " :!!      !!:  !!! !!:      !!:   !!:   !!:  !!! !!:           :!!      !!:      !!:  !!!     !:! !!:  !!!\n" +
                "  :: :: :  :   : :  :       :      :     :   : : : ::.: :       :: :: : : ::.: :  :   : : ::.: :   :   : :\n" +
                "                                                                                                          "
        );
    }

    public  void displayGameInfo() {
        // refactor it to small paragraph with all the game details
        System.out.println("You are the son of a prestigious fund manager. It is summer. Your father invites you and \n" +
                "your brother to a trading game. You are given $10,000, and 5 days. The game will be T+1 settlement, \n" +
                "which means, on each day, after you buy a stock, the soonest you can sell is the next day. you can \n" +
                "select from a 10 stocks, decide which to buy or sell if already holding, or hold cash. You long for a \n" +
                "high return. Your goal is, on day 4, your account balance is more than your brother. You'll be able to \n" +
                "go to the news feed room for news. The winner would be the one with higher account balance on day 4. \n" +
                "You want to show that you have great investment acumen to your father.\n");
    }

    public  void mainMenu() {
        System.out.println("Which room would you like to go to?");
        System.out.println("1) Trading Room (you can buy/sell) \n2) News Room (you can get news)" +
                " \n3) Next Day(Round)");

    }

    public  void tradingRoomMenu() {
        System.out.println("\nWhat would you like to do next? Please enter numbers only(1,2,3)");
        System.out.println("1) Buy \n2) Sell \n3) Go Back ");
    }

    public  void titleBarForInventory(int day) {
        System.out.println(String.format("%-60s DAY: %-10s\n","",day));
        System.out.println(String.format("%-10s %-20s %-15s %-18s %-11s","",
                "Stock Name","Symbol","Current Price","Sector"));
    }

    public String userInput() {
        return myScanner.nextLine();
    }

    public void startMenu() {
        System.out.println("Would you like to take the challenge?");
        System.out.println("1: Yes \n2: No");
    }

    public void playerVsBrotherReports(int day, Player player, Computer brother) {

        System.out.println(String.format("%-32s DAY: %-10s","",day));
        System.out.println(String.format("%-18s %-33s %-14s","","You","Brother"));
        System.out.println(String.format("%-18s Stocks: %-25s Quantity:%s Stocks: %-10s","",player.getStocks(),player.getStocks(), brother.getStocks()));
        System.out.println(String.format("%-18s Balance:$%-24s Balance:$%-10s\n",
                "",player.getAccount().getCashBalance(),brother.getAccount().getCashBalance()));

    }

    public void invalidChoice() {
        System.out.println("Invalid choice!");
    }

    public void newsRoomInfo() {
        System.out.println("Would you like to get today's market intelligence? y/n");
    }


    public void newsDecline() {
        System.out.println("You declined to get today's market intelligence.");
    }

    public void nextDay() {
        System.out.println("You are done for the day. The game will move to the next day.");
    }

    public void thankYouMessage() {
        System.out.println("Thank yor visiting!");
    }
}