package ui;

import players.Computer;
import players.Player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Scanner myScanner;
    public UserInterface() throws FileNotFoundException {
        myScanner = new Scanner(System.in);
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

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
                "Let's see who has an eye for stocks. \n"+
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
        System.out.println("Where would you like to go?");
        System.out.println("1) Trading Room (you can buy/sell) \n2) News Room (you can get news)" +
                " \n3) Next Day(Round)\n" + ANSI_RED +
                "=================================================================================" + ANSI_RESET + ""
        );

    }

    public  void tradingRoomMenu() {
    
        System.out.println("\nMake a decision... Please enter numbers only(1-3)");
        System.out.println("1) Buy \n2) Sell \n3) Exit ");

    }

    public  void titleBarForInventory(int day) {
        System.out.println(String.format("%-60s DAY: %-10s\n","",day));
        System.out.println(String.format("%-10s %-20s %-15s %-18s %-11s","","" + ANSI_RED_BACKGROUND +
                "Stock Name","    Symbol","Current Price","        Sector       " + ANSI_RESET));
    }

    public String userInput() {
        return myScanner.nextLine();
    }

    public void startMenu() {
        String challenge = "ARE YOU UP TO THE CHALLENGE?\n";
        int i;
        for(i = 0; i < challenge.length(); i++){
            System.out.printf("%c", challenge.charAt(i));
            try{
                Thread.sleep(200);
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(ANSI_GREEN + "1: Yes" + ANSI_RESET + " \n" + ANSI_RED +"2: No"+ ANSI_RESET);
    }

    public void playerVsBrotherReports(int day, Player player, Computer brother, double mktReturnOfTheDay,
                                       int newsIndexOfTheDay, StockInventory inventory) {
        if(player != null && brother != null && inventory != null) {

            double playerStockBalance = player.getStockBalance(mktReturnOfTheDay,newsIndexOfTheDay,inventory);
            double brotherStockBalance = brother.getStockBalance(mktReturnOfTheDay,newsIndexOfTheDay,inventory);
            System.out.println(String.format(ANSI_YELLOW + "%-42s DAY: %-10s\n","",day+ANSI_RESET));
            System.out.println(String.format("%-18s %-42s %-14s","",ANSI_RED_BACKGROUND+"You"+ANSI_RESET,ANSI_RED_BACKGROUND+"Brother"+ANSI_RESET));
            System.out.println(String.format("%-18s Stocks: %-25s Stocks: %-10s","",player.getStocks(), brother.getStocks()));
            System.out.println(String.format("%-18s Cash Balance:$%-19.2f Cash Balance:$%-10.2f",
                    "",player.getAccount().getCashBalance(),brother.getAccount().getCashBalance()));
            System.out.println(String.format("%-18s Stock Balance:$%-18.2f Stock Balance:$%-10.2f",
                    "",playerStockBalance,brotherStockBalance));
            System.out.println(String.format("%-18s Net Balance:$%-20.2f Net Balance:$%-10.2f\n",
                    "",playerStockBalance+player.getAccount().getCashBalance(),brotherStockBalance+brother.getAccount().getCashBalance()));
        }

    }

    public void invalidChoice() {
        System.out.println("Invalid choice! Please Try Again!");
    }

    public void newsRoomInfo() {
        System.out.println(ANSI_YELLOW + "Would you like to see The Breaking News? (y/n)"+ ANSI_RESET);
    }


    public void newsDecline() {
        System.out.println("You've declined today's market intelligence.");
    }

    public void nextDay() {
        System.out.println("Thank you for your service. We will see you tomorrow.");
        System.out.println(ANSI_RESET +"=================================================================================\n" + ANSI_RESET);
    }

    public void thankYouMessage() {
        System.out.println("Thank yor visiting!");
    }

    public void playerWinMessage() {
        System.out.println(String.format("%-20s",ANSI_GREEN + "You beat your brother!!\n"+ ANSI_RESET));

    }

    public void brotherWinMessage() {
        System.out.println(ANSI_RED + "Sorry, You loose with your brother!!\n"+ ANSI_RESET);
    }

    public void lastDay() {
        System.out.println(ANSI_YELLOW +"This is the last day to invest! \n"+ ANSI_RESET);
    }
}
