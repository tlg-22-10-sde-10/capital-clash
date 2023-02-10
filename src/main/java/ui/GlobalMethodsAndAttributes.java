package ui;

import account.Account;
import news.News;
import players.Computer;
import players.Player;
import stock.Stock;
import storage.StockInventory;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalMethodsAndAttributes {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[35m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static News news = new News();
    public static UserInterface ui;
    public static StockInventory inventory;
    public static Map<String, Integer> playerStockMap;
    public static Map<String, Integer> brotherStockMap;
    public static final int GAME_DAYS = 5;
    public static DecimalFormat df = null;
    public static final String NUMBER_ONE = "1";
    public static final String NUMBER_TWO = "2";
    public static final String NUMBER_THREE = "3";
    public static List<String> playerStocks;
    public static Player player;
    public static Computer brother;

    public static void initializeGlobalInstances() throws FileNotFoundException {
        ui = new UserInterface();
        inventory = new StockInventory();
        playerStockMap = new HashMap<>();
        brotherStockMap = new HashMap<>();
        playerStocks = new ArrayList<>();
        player = new Player("Player", new Account("checking"));
        brother = new Player("Brother", new Account("checking"));
        df = new DecimalFormat("0.00");
    }

    public static boolean isPositiveInteger(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            int d = Integer.parseInt(strNum);
            if (d <= 0) {
                return false;
            }

        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public static void nextDayOps(int day) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
        double totalPlayerBalance = player.getAccount().getCashBalance();
        double totalBrotherBalance = brother.getAccount().getCashBalance();

        if (day == 4) {
            totalPlayerBalance +=calculatePriceFromMap(playerStockMap);
            totalBrotherBalance+=calculatePriceFromMap(brotherStockMap);
//            System.out.println("Your total balance is $"+ ANSI_RED + df.format(totalPlayerBalance) + ANSI_RESET + ".");
//            System.out.println("Your Brother's total balance is $" + df.format(totalBrotherBalance) + ".");

            if (totalPlayerBalance > totalBrotherBalance) {
                ui.showWinBanner();
                System.out.println("Your total balance is $"+ ANSI_GREEN + df.format(totalPlayerBalance) + ANSI_RESET + ".");
                System.out.println("Your Brother's total balance is $"+ ANSI_RED + df.format(totalBrotherBalance) + ANSI_RESET + ".");
                GlobalMethodsAndAttributes.playAudio("piglevelwin2mp3-14800.wav");
            }  else if (totalPlayerBalance < totalBrotherBalance) {
                ui.showLoseBanner();
                System.out.println("Your total balance is $"+ ANSI_RED + df.format(totalPlayerBalance) + ANSI_RESET + ".");
                System.out.println("Your Brother's total balance is $"+ ANSI_GREEN + df.format(totalBrotherBalance) + ANSI_RESET + ".");
                GlobalMethodsAndAttributes.playAudio("sadTrombone(1).wav");

            } else {
                ui.showTieGameBanner();
                System.out.println("Your total balance is $"+ ANSI_GREEN + df.format(totalPlayerBalance) + ANSI_RESET + ".");
                System.out.println("Your Brother's total balance is $"+ ANSI_GREEN + df.format(totalBrotherBalance) + ANSI_RESET + ".");
            }

        } else if (day == 3) {
            ui.lastDay();
        } else {
            ui.nextDay();
        }
    }

    private static double calculatePriceFromMap(Map<String, Integer> map) {
        double totalBalance = 0.0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            totalBalance += inventory.findBySymbol(entry.getKey())
                    .getCurrentPrice() * entry.getValue();
        }
        return totalBalance;
    }

    public static void updateDashboard(int day, int newsIndexOfTheDay, double mktReturnOfTheDay) {
        if (day != 0) {
            for (Stock stock : inventory.getAllStocks()) {
                //stock
                double nextPrice = stock.UpdateStockPriceForTheDay(stock.getCurrentPrice(),
                        mktReturnOfTheDay, newsIndexOfTheDay);
                stock.setCurrentPrice(nextPrice);
            }
        }
    }

    public static void showStockInventory(int day) {
        ui.titleBarForInventory(day);
        for (Stock stock : inventory.getAllStocks()) {
            System.out.println(stock.toString());
        }
    }
    public static void playAudio(String audioFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(ClassLoader.getSystemResourceAsStream(audioFile)))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
    }

    public static void showHoldings(ArrayList<String> keyList) {
        System.out.print(String.format("%-15s%-20s\n","",ANSI_CYAN_BACKGROUND+"         YOUR HOLDINGS      "+ANSI_RESET));
        System.out.format("%-15s%-20s%-15s\n","", "Stock Symbol", "Quantity");
        for (int i = 0; i < keyList.size(); i++) {
            System.out.format("%-15s%-20s%-15s\n","", keyList.get(i),
                    playerStockMap.get(keyList.get(i)));
        }
    }
}
