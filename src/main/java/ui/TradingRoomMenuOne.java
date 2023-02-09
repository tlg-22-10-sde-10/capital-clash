package ui;
import stock.Stock;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import static ui.GlobalMethodsAndAttributes.*;

public class TradingRoomMenuOne {

    public static void menuOneBuy(int day) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("Please enter the symbol of the stock that you want to purchase:");
        String stockSymbol = ui.userInput();
        //handle unrecognized symbol error
        while (inventory.findBySymbol(stockSymbol) == null) {
            System.out.println("This stock is not offered. Please select from the list below.\n");
            showTradingRoomStockDashboard(day);
            System.out.println("Please enter the symbol of the stock that you want to purchase:");
            stockSymbol = ui.userInput();
        }
        System.out.println("How many shares would you like? " +
                "Fractional numbers are not allowed! (Enter an integer ONLY)");
        //handle quantity-is-not-an-integer problem
        String quantityInput = ui.userInput();
        while (!isInteger(quantityInput)) {
            System.out.println("Your input is not an integer. Please try again");
            System.out.println("How many shares would you like? " +
                    "Fractional numbers are not allowed. (Enter an integer ONLY)");
            quantityInput = ui.userInput();
        }
        int numberOfStockPurchaseByPlayer = Integer.parseInt(quantityInput);

        Stock playerStock = inventory.findBySymbol(stockSymbol);
        double valueOfStockPurchasedByPlayer = numberOfStockPurchaseByPlayer * playerStock.getCurrentPrice();

        if (valueOfStockPurchasedByPlayer > player.getAccount().getCashBalance()) {
            System.out.println("Unauthorized Purchase: Not Enough Balance");
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

            System.out.println("You have purchased "+numberOfStockPurchaseByPlayer
                    +" shares of "+ inventory.findBySymbol(stockSymbol).getStockName()+".\n");

            Scanner scanner = new Scanner(System.in);
            File file = new File("src/main/resources/cashier.wav.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        }
        // brother randomly purchase the stock
        int numberOfStockPurchasedByBrother = 1 + (int) (Math.random() * 6);
        Stock brotherStock = inventory.getRandomStock();
        brotherStockMap.put(brotherStock.getSymbol(), numberOfStockPurchasedByBrother);
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
