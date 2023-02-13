package ui;
import stock.Stock;
import javax.sound.sampled.*;
import java.io.IOException;
import static ui.GlobalMethodsAndAttributes.*;

public class TradingRoomMenuOne {

    public static void menuOneBuy(int day) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("Please enter the symbol of the stock that you want to purchase:");
        String stockSymbol = ui.userInput().toUpperCase();
        //handle unrecognized symbol error
        while (inventory.findBySymbol(stockSymbol) == null) {
            System.out.println(ANSI_RED+"                       ***Stock not offered. Please select from the list below***\n"+ANSI_RESET);
            showTradingRoomStockDashboard(day);
            System.out.println("\nPlease enter the symbol of the stock that you want to purchase:");
            stockSymbol = ui.userInput().toUpperCase();
        }
        System.out.println("How many shares would you like? " +
                "Fractional numbers are not allowed! (Enter an integer ONLY)");
        //handle quantity-is-not-an-integer problem
        String quantityInput = ui.userInput();
        while (!isPositiveInteger(quantityInput)) {
            System.out.println(ANSI_RED+"                       ***Your input is not a positive integer. Please try again***\n"+ANSI_RESET);
            System.out.println("How many shares would you like? " +
                    "Fractional numbers are not allowed. (Enter an integer ONLY)");
            quantityInput = ui.userInput();
        }
        int numberOfStockPurchaseByPlayer = Integer.parseInt(quantityInput);

        Stock playerStock = inventory.findBySymbol(stockSymbol);
        double valueOfStockPurchasedByPlayer = numberOfStockPurchaseByPlayer * playerStock.getCurrentPrice();

        if (valueOfStockPurchasedByPlayer > player.getAccount().getCashBalance()) {
            System.out.println(ANSI_RED+"                          ***Unauthorized Purchased!Not enough balance!***\n"+ANSI_RESET);
        } else {
            if (playerStockMap.containsKey(stockSymbol)) {
                playerStockMap.put(playerStock.getSymbol(), playerStockMap.get(stockSymbol) + numberOfStockPurchaseByPlayer);
            } else {
                playerStockMap.put(playerStock.getSymbol(), numberOfStockPurchaseByPlayer);
            }
            playerStocks.add(playerStock.getSymbol());
            player.setStockNames(playerStocks);
            player.setStocks(playerStockMap);
            player.getAccount().deductBalance(numberOfStockPurchaseByPlayer
                    * playerStock.getCurrentPrice());

            System.out.println(ANSI_GREEN+"                          ***Successfully Purchased "+numberOfStockPurchaseByPlayer
                    +" shares of "+ inventory.findBySymbol(stockSymbol).getStockName()+  "***\n"+           ANSI_RESET);

            GlobalMethodsAndAttributes.playAudio("cashier.wav.wav");

        }
        // brother randomly purchase the stock
        int numberOfStockPurchasedByBrother = 1 + (int) (Math.random() * 6);
        Stock brotherStock = inventory.getRandomStock();
        if(brotherStockMap.containsKey(brotherStock.getSymbol())) {
            brotherStockMap.put(brotherStock.getSymbol(), numberOfStockPurchasedByBrother+brotherStockMap.get(brotherStock.getSymbol()));
        } else {
            brotherStockMap.put(brotherStock.getSymbol(), numberOfStockPurchasedByBrother);
        }

        brother.setStocks(brotherStockMap);
        brother.getAccount().deductBalance(numberOfStockPurchasedByBrother * brotherStock.getCurrentPrice());
    }

    private static void showTradingRoomStockDashboard(int day) {
        ui.titleBarForInventory(day);
        for (Stock stock : inventory.getAllStocks()) {
            System.out.println(stock.toString());
        }
    }
}
