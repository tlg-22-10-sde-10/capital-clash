package ui;
import account.Account;
import news.News;
import players.Computer;
import players.Player;
import stock.Stock;
import storage.StockInventory;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalMethodsAndAttributes {
    public static News news = new News();
    public static UserInterface ui;
    public static StockInventory inventory;
    public static Map<String, Integer> playerStockMap;
    public static Map<String, Integer> brotherStockMap;
    public static final int GAME_DAYS = 5;
    public static DecimalFormat df = null;
    public static final String REPLY_WITH_YES = "y";
    public static final String NUMBER_ONE = "1";
    public static final String NUMBER_TWO = "2";
    public static final String NUMBER_THREE = "3";
    public static final String NUMBER_FOUR = "4";
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

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void nextDayOps(int day) {

        double totalPlayerBalance = player.getAccount().getCashBalance();
        double totalBrotherBalance = brother.getAccount().getCashBalance();
        if (day == 4) {
            totalPlayerBalance += calculatePriceFromMap(playerStockMap);
            totalBrotherBalance += calculatePriceFromMap(brotherStockMap);
            if (totalPlayerBalance > totalBrotherBalance) {
                ui.playerWinMessage();

            } else if (totalPlayerBalance < totalBrotherBalance) {
                ui.brotherWinMessage();
            } else {
                System.out.println("Tie Game! ");
            }
        } else if (day == 3) {
            ui.lastDay();
        } else {
            ui.nextDay();
        }
    }
    private static double calculatePriceFromMap(Map<String, Integer> map) {
        double totalBalance=0.0;
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
}
