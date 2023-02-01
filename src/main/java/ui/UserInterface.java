package ui;

import java.util.Scanner;

public class UserInterface {
   Scanner myScanner = new Scanner(System.in);

    public UserInterface() {
    }

    public void displayASCII(){
        System.out.println("\nWelcome to Capital Clash.");
    }

    public void displayGameInfo() {
        // refactor it to small paragraph with all the game details
        System.out.println("PLEASE READ THE GAME INFO CAREFULLY BEFORE YOU START INVESTING! \n");
        System.out.println("You are the son of a prestigious fund manager. Your father brings you and \n" +
                "your brother to Wall Street, and puts you guys in a trading game. You are given $10,000, and 5 days. \n" +
                "The game will be T+1 settlement, which means, on each day, after you buy a stock, the soonest you can \n" +
                "sell is the next day. you can select from a variety of stocks, decide which to buy or sell if \n" +
                "already holding, or hold cash. You long for a high return. Your goal is, on day 4, your account \n" +
                "balance is more than your brother. You'll be able to go the news feed room for stocks insight.\n" +
                "The winner would be the one with higher account balance on day 4. You want to show you have \n" +
                "great investment acumen to your father. \n");
    }
    public void gameBegin() {
        System.out.println("Would you like to take the challenge? Choose either 1 or 2 \n1)Yes\n2)No");
    }
    public String  mainMenu() {
        System.out.println("Which room would you like to go?");
        System.out.println("1) News Feed Room");
        System.out.println("2) Trading Room");
        System.out.println("3) Exit");

        String userResponse = userInput();
        return userResponse;
    }

    public String tradingRoomMenu() {
        System.out.println("Welcome to the Trading Room! Choose 1 or 2 or 3");
        System.out.println("1) Buy \n2) Sell \n3)Exit\n ");
        return userInput();
    }

    public String userInput() {
        return myScanner.nextLine();
    }

    public void getNewsfeed() {
        System.out.println("News from News Feed!\n");
    }
}
