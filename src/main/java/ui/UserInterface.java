package ui;
import players.Computer;
import players.Player;
import storage.StockInventory;

import java.util.Scanner;
import static ui.GlobalMethodsAndAttributes.*;
public class UserInterface {
    private Scanner myScanner;

    public UserInterface()  {
        myScanner = new Scanner(System.in);
    }

    public void showBanner() throws InterruptedException {
        System.out.println(ANSI_PURPLE+"\n\n                                               WELCOME TO:\n"+ANSI_RESET);
        String banner = ANSI_YELLOW+
                " ██████╗  █████╗ ██████╗██╗████████╗ █████╗ ██╗          ██████╗██╗      █████╗ ███████╗██╗  ██╗\n" +
                "██╔════╝██╔══██╗██╔══██╗██║╚══██╔══╝██╔══██╗██║         ██╔════╝██║     ██╔══██╗██╔════╝██║  ██║\n" +
                "██║     ███████║██████╔╝██║   ██║   ███████║██║         ██║     ██║     ███████║███████╗███████║\n" +
                "██║     ██╔══██║██╔═══╝ ██║   ██║   ██╔══██║██║         ██║     ██║     ██╔══██║╚════██║██╔══██║\n" +
                "╚██████╗██║  ██║██║     ██║   ██║   ██║  ██║███████╗    ╚██████╗███████╗██║  ██║███████║██║  ██║\n" +
                " ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝     ╚═════╝╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
                "                                                                                                \n\n"+ANSI_RESET;
        for (int i = 0; i < banner.length(); i++) {
            System.out.print(banner.charAt(i));
            Thread.sleep(4);

        }
//=======
//
//        System.out.println(" -------------WELCOME TO!-----------");
//        System.out.println(ANSI_PURPLE + "========================================================================================== \n" +ANSI_GREEN+
//                "|#(1)*UNITED STATES OF AMERICA*(1)#|\n" +
//                "|#**          /===\\   ********  **#|\n" +
//                "|*# [C.C]    | (\") |             #*|\n" +
//                "|#*  ******  | /v\\ |    O N E    *#|\n" +
//                "|#(1)         \\===/            (1)#|\n" +
//                "|##=======CAPITAL CLASH==========##|\n" +
//                ANSI_RESET +
//                ANSI_PURPLE +"========================================================================================== \n" + ANSI_RESET);

    }

    public void showWinBanner() throws InterruptedException {

        String banner = ANSI_GREEN+
                "" +
                "╔═╗╔═╗╔╗╔╔═╗╦═╗╔═╗╔╦╗╔═╗  ╦ ╦╔═╗╦ ╦  ╦ ╦╦╔╗╔\n" +
                "║  ║ ║║║║║ ╦╠╦╝╠═╣ ║ ╚═╗  ╚╦╝║ ║║ ║  ║║║║║║║\n" +
                "╚═╝╚═╝╝╚╝╚═╝╩╚═╩ ╩ ╩ ╚═╝   ╩ ╚═╝╚═╝  ╚╩╝╩╝╚╝\n"
                +ANSI_RESET;
        for (int i = 0; i < banner.length(); i++) {
            System.out.print(banner.charAt(i));
            Thread.sleep(2);

        }
    }

    public void showTieGameBanner() throws InterruptedException {
        String banner = ANSI_RED+"                              "+

                "╔═╗╔═╗╦═╗╦═╗╦ ╦  ╦ ╦╔═╗╦ ╦  ╦  ╔═╗╔═╗╔═╗\n" +
                "╚═╗║ ║╠╦╝╠╦╝╚╦╝  ╚╦╝║ ║║ ║  ║  ║ ║╚═╗║╣ \n" +
                "╚═╝╚═╝╩╚═╩╚═ ╩    ╩ ╚═╝╚═╝  ╩═╝╚═╝╚═╝╚═╝\n"
                +ANSI_RESET;
        for (int i = 0; i < banner.length(); i++) {
            System.out.print(banner.charAt(i));
            Thread.sleep(4);

        }
    }

    public void showLoseBanner() throws InterruptedException {
        String banner = ANSI_PURPLE+
                "" +
                "╔╦╗╦╔═╗  ╔═╗╔═╗╔╦╗╔═╗\n" +
                " ║ ║║╣   ║ ╦╠═╣║║║║╣ \n" +
                " ╩ ╩╚═╝  ╚═╝╩ ╩╩ ╩╚═╝\n"+ANSI_RESET;
        for (int i = 0; i < banner.length(); i++) {
            System.out.print(banner.charAt(i));
            Thread.sleep(4);

        }
    }

    public void showGameStoryBanner() throws InterruptedException {
        String banner = ANSI_PURPLE+
                "" +
               " ╔═╗╔╦╗╔═╗╦═╗╦ ╦\n" +
                "╚═╗ ║ ║ ║╠╦╝╚╦╝\n" +
                "╚═╝ ╩ ╚═╝╩╚═ ╩ \n"+
                ANSI_RESET;
        for (int i = 0; i < banner.length(); i++) {
            System.out.print(banner.charAt(i));
            Thread.sleep(4);

        }
    }



    public void displayGameInfo() {
        System.out.println(ANSI_PURPLE + "========================================================================================== \n" + ANSI_RESET +
                ANSI_GREEN+""+
                "                                ╔═╗╔═╗╔╦╗╔═╗  ╔═╗╔╦╗╔═╗╦═╗╦ ╦\n" +
                "                                ║ ╦╠═╣║║║║╣   ╚═╗ ║ ║ ║╠╦╝╚╦╝\n" +
                "                                ╚═╝╩ ╩╩ ╩╚═╝  ╚═╝ ╩ ╚═╝╩╚═ ╩ "
                        +ANSI_RESET+
                "\nIt's time to put your stock picking skills to the test. The question is, who has\n" +
                "a better eye for stocks - you or your brother? The game is a simulation of the\n" +
                "stock market, lasting for a total of 5 trading days. Each player will start with\n" +
                "a balance of $10,000 and have the opportunity to choose from a selection of 10\n" +
                "different stocks. The objective of the game is to make strategic buy, sell, or\n" +
                "hold decisions to increase your account balance and become the ultimate winner.\n"
                + "\n" +
                "Throughout the game, you will be able to access a news feed room to stay updated\n" +
                "on current events, a trading room where you can make your transactions, and\n" +
                "advance to the next round to start a new trading day. The ultimate winner will\n" +
                "be determined on day 4 and will be the player with the highest account balance.\n" +
                "So, may the HOLD be with you, and let the stock market games begin!\n"
                + "\n" + ANSI_PURPLE +
                "========================================================================================== \n" +
                ANSI_RESET + ""

        );
    }

    public void mainMenu() {
        System.out.println("Where would you like to go?");
        System.out.println("1) Trading Room  \n2) News Room " +
                " \n3) Next Round(Day)\n" + ANSI_PURPLE +
                "========================================================================================== \n" + ANSI_RESET + ""
        );

    }

    public void tradingRoomMenu() {

        System.out.println("\nMake a decision... Please select your option from 1 to 4.");
        System.out.println("1) Buy Stock \n2) Sell Stock\n3) Leave Trading Room ");

    }

    public void titleBarForInventory(int day) {
        System.out.println(String.format(ANSI_YELLOW +"%-42s DAY: %-10s\n", "", day+ANSI_RESET));
        System.out.println(String.format("%-10s %-25s %-15s %-18s  %-18s", " ",ANSI_CYAN_BACKGROUND +

                "Stock Name", "Symbol", "Current Price", "Sector               " + ANSI_RESET));
    }

    public String userInput() {
        return myScanner.nextLine();
    }

    public void startMenu() {
        String challenge = "ARE YOU UP TO THE CHALLENGE?\n";
        int i;
        for (i = 0; i < challenge.length(); i++) {
            System.out.printf("%c", challenge.charAt(i));
            try {
                Thread.sleep(85);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(ANSI_GREEN + "1: Yes" + ANSI_RESET + " \n" + ANSI_RED + "2: No" + ANSI_RESET);
    }

    public void showStoryMessage() {
        String story = "ARE YOU UP TO THE CHALLENGE?\n";
        int i;
        for (i = 0; i < story.length(); i++) {
            System.out.printf("%c", story.charAt(i));
            try {
                Thread.sleep(85);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(ANSI_GREEN + "1: Yes" + ANSI_RESET + " \n" + ANSI_RED + "2: No" + ANSI_RESET);
    }

    public void playerVsBrotherReports(int day, Player player, Computer brother, StockInventory inventory) {
        if (player != null && brother != null && inventory != null) {

            double playerStockBalance = player.getBalanceFromHolding(inventory);
            double brotherStockBalance = brother.getBalanceFromHolding(inventory);
            System.out.println(String.format(ANSI_YELLOW + "%-42s DAY: %-10s\n", "", day + ANSI_RESET));
            System.out.println(String.format("%-18s %-42s %-14s", "", ANSI_CYAN_BACKGROUND+"You         " + ANSI_RESET, ANSI_CYAN_BACKGROUND + "Brother     " + ANSI_RESET));

            System.out.println(String.format("%-18s Stocks: %-25s Stocks: %-10s", "",
                    player.getStocks() == null ? "Empty" : player.getStocks(),
                    brother.getStocks() == null ? "Empty" : brother.getStocks()));

            System.out.println(String.format("%-18s Cash Balance:$%-19.2f Cash Balance:$%-10.2f",
                    "", player.getAccount().getCashBalance(), brother.getAccount().getCashBalance()));
            System.out.println(String.format("%-18s Stock Balance:$%-18.2f Stock Balance:$%-10.2f",
                    "", playerStockBalance, brotherStockBalance));
            System.out.println(String.format("%-18s Net Balance:$%-20.2f Net Balance:$%-10.2f\n",
                    "", playerStockBalance + player.getAccount().getCashBalance(), brotherStockBalance + brother.getAccount().getCashBalance()));
        }

    }

    public void invalidChoice() {
        System.out.println(ANSI_RED+"                          ***Invalid Choice.Please Try Again***\n"+ANSI_RESET);
       
    }


    public void nextDay() {
        System.out.println("Thank you for your service. We will see you tomorrow.");
        System.out.println(ANSI_PURPLE + "========================================================================================== \n" + ANSI_RESET);
    }


    public void lastDay() {
        System.out.println("                                     "+ANSI_RED_BACKGROUND+"LAST DAY TO INVEST"+ANSI_RESET);
    }

    public void newsRoomOps(String todayNews) {
        System.out.println(ANSI_PURPLE + "=================================================================================\n" + ANSI_RESET);
        System.out.println("                             ***BREAKING NEWS***                                 \n");

        System.out.println(ANSI_YELLOW + todayNews + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "=================================================================================\n" + ANSI_RESET);

    }
}
