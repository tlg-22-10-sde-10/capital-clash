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

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    public void displayASCII(){


        System.out.println(" -------------WELCOME TO!-----------");
        System.out.println(ANSI_GREEN + "|#######====================#######|\n" +
                "|#(1)*UNITED STATES OF AMERICA*(1)#|\n" +
                "|#**          /===\\   ********  **#|\n" +
                "|*# [C.C]    | (\") |             #*|\n" +
                "|#*  ******  | /v\\ |    O N E    *#|\n" +
                "|#(1)         \\===/            (1)#|\n" +
                "|##=======CAPITAL CLASH==========##|\n" +
                ANSI_RESET  +
                " ===================================" + ANSI_RESET  + "                                                                               "
        );
    }

    public  void displayGameInfo() {
        // refactor it to small paragraph with all the game details
        System.out.println(ANSI_RED + "================================================================================= \n" + ANSI_RESET +
                "The objective is to figure out who has an eye for stocks. \n"+
                "The game consist of 5 trading days and a balance of $10,000 each. \n"+
                "The game will be [T+1 settlement] meaning, that on each day after a stock is bought, \n" +
                "the sooner you can sell them the next day. \n" +
                "You will be able to choose from 10 stocks to buy, sell, or hold. \n" +
                "You will be able to go to the news feed room for current news, \n" +
                        "go to the trading room for another day of investing,\n" +
                "or go to the next round for a new day. \n" +
                "To determine the winner you will have to have the highest account balance on day 4. \n" +
                "May the HODL be with you. \n" + ANSI_RED +
                "=================================================================================" + ANSI_RESET + ""

        );
    }

    public  void mainMenu() {
        System.out.println("Which room would you like to go to?");
        System.out.println("1) Trading Room (you can buy/sell) \n2) News Room (you can get news)" +
                " \n3) Next Day(Round)\n" + ANSI_RED +
                "=================================================================================" + ANSI_RESET + ""
        );

    }

    public  void tradingRoomMenu() {
        System.out.println("\nWhat would you like to do next? Please enter numbers only(1,2,3)");
        System.out.println("1) Buy \n2) Sell \n3) Exit ");
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
        System.out.println(ANSI_GREEN + "1: Yes" + ANSI_RESET + " \n" + ANSI_RED +"2: No"+ ANSI_RESET);
    }

    public void playerVsBrotherReports(int day, Player player, Computer brother) {

        System.out.println(String.format("%-32s DAY: %-10s","",day));
        System.out.println(String.format("%-18s %-33s %-14s","","You","Brother"));
        System.out.println(String.format("%-18s Stocks: %-25s Stocks: %-10s","",player.getStockNames(),brother.getStockNames()));
        System.out.println(String.format("%-18s Balance:$%-24s Balance:$%-10s\n",
                "",player.getAccount().getCashBalance(),brother.getAccount().getCashBalance()));

    }

    public void invalidChoice() {
        System.out.println("Invalid choice!");
    }

    public void newsRoomInfo() {
        System.out.println(ANSI_YELLOW + "Would you like to get today's market intelligence? y/n"+ ANSI_RESET);
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