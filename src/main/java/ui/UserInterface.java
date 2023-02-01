package ui;

import java.util.Scanner;

public class UserInterface {
    private Scanner myScanner;

    public UserInterface(Scanner myScanner) {
        this.myScanner = myScanner;
    }

    public static void displayASCII(){
        System.out.println("Welcome to Capital Clash.");
    }

    public static void displayGameInfo() {
        // refactor it to small paragraph with all the game details
        System.out.println("You are the son of a prestigious fund manager. It is summer. Your father brings you and " +
                "your brother to Wall Street, and  puts you guys in a trading game. You are given $10,000, and 5 days. " +
                "The game will be T+1 settlement, which means, on each day, after you buy a stock, the soonest you can " +
                "sell is the next day. you can select from a variety of stocks, decide which to buy or sell if " +
                "already holding, or hold cash. You long for a high return. Your goal is, on day 4, your account " +
                "balance is more than your brother. You'll be able to go the news feed room for stocks insight." +
                "The winner would be the one with higher account balance on day 4. You want to show you have " +
                "great investment acumen to your father. ");
    }
    public String  mainMenu() {
        //Which room would you like to go to
        //--1.News feed room -- news for the day
        //--2.Trade room -- place order for the day
        System.out.println("Which room would you like to go to");
        String userResponse = myScanner.nextLine();
        return userResponse;
    }

    public static void tradingRoomMenu() {
        System.out.println("Welcome to the Trading Room! ");
        System.out.println("1) Buy \n2) Sell \n3)Exit ");
    }

}
