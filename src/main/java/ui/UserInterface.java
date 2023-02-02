package ui;

import java.util.Scanner;

public class UserInterface {
    private Scanner myScanner;

    public UserInterface(Scanner myScanner) {
        this.myScanner = myScanner;
    }

    public static void displayASCII() {
        System.out.println("WELCOME TO!");
        System.out.println("  @@@@@@@  @@@@@@  @@@@@@@  @@@ @@@@@@@  @@@@@@  @@@            @@@@@@@ @@@       @@@@@@   @@@@@@ @@@  @@@\n" + " !@@      @@!  @@@ @@!  @@@ @@!   @@!   @@!  @@@ @@!           !@@      @@!      @@!  @@@ !@@     @@!  @@@\n" + " !@!      @!@!@!@! @!@@!@!  !!@   @!!   @!@!@!@! @!!           !@!      @!!      @!@!@!@!  !@@!!  @!@!@!@!\n" + " :!!      !!:  !!! !!:      !!:   !!:   !!:  !!! !!:           :!!      !!:      !!:  !!!     !:! !!:  !!!\n" + "  :: :: :  :   : :  :       :      :     :   : : : ::.: :       :: :: : : ::.: :  :   : : ::.: :   :   : :\n" + "                                                                                                          ");
    }

    public static void displayGameInfo() {
        // refactor it to small paragraph with all the game details
        System.out.println("You are the son of a prestigious fund manager. It is summer. Your father invites you and \n" +
                "your brother to a trading game. You are given $10,000, and 5 days. \n" +
                "The game will be T+1 settlement, which means, on each day, after you buy a stock, the soonest you can \n" +
                "sell is the next day. you can select from a 10 stocks, decide which to buy or sell if \n" +
                "already holding, or hold cash. You long for a high return. Your goal is, on day 4, your account \n" +
                "balance is more than your brother. You'll be able to go to the news feed room for news.\n" +
                "The winner would be the one with higher account balance on day 4. You want to show that you have \n" +
                "great investment acumen to your father.\n");
    }

    public static void mainMenu() {
        //Which room would you like to go to
        //--1.News feed room -- news for the day
        //--2.Trade room -- place order for the day
        System.out.println("Which room would you like to go to?");
        System.out.println("1) Trading Room (you can buy/sell) \n2) News Room (you can get news)" +
                " \n3) Home (done for the day) ");

    }

    public static void tradingRoomMenu() {
        System.out.println("Welcome to the Trading Room! ");
        System.out.println("1) Buy \n2) Sell \n3)Exit ");
    }

    public static void titleBarForInventory() {
        System.out.println(String.format("%-10s %-20s %-15s %-18s %-10s %-10s %-18s %-11s","",
                "Stock Name","Symbol","Current Price","Beta","Alpha","Residual Value","Sector"));
    }


}