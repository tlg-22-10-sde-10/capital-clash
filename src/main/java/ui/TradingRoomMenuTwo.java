package ui;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static ui.GlobalMethodsAndAttributes.*;

public class TradingRoomMenuTwo {
    public static void menuTwoSell() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        if (playerStockMap.isEmpty()) {
            System.out.println("You currently do not have any stock. So you are not able to " +
                    "do sell transaction.\n");
        } else {
            ArrayList<String> keyList = new ArrayList<String>(playerStockMap.keySet());
            System.out.println("Yours current Holdings:");
            System.out.format("%-15s%-15s\n", "Stock Symbol", "Quantity");
            for (int i = 0; i < keyList.size(); i++) {
                System.out.format("%-15s%-15s\n", keyList.get(i),
                        playerStockMap.get(keyList.get(i)));
            }

            boolean isSellMenuRunning = true;
            System.out.println("Please enter the stock symbol that you want to sell.");
            String stockSymbol = ui.userInput();

            //handle unrecognized symbol error
            while (!playerStockMap.containsKey(stockSymbol)) {
                System.out.println("This stock is not in your holding.");
                System.out.println("Please try again. Please select from your holding.");
                System.out.format("%-15s%-15s\n", "Stock Symbol", "Quantity");
                for (int i = 0; i < keyList.size(); i++) {
                    System.out.format("%-15s%-15s\n", keyList.get(i),
                            playerStockMap.get(keyList.get(i)));
                }
                System.out.println("Please enter the stock symbol that you want to sell.");
                stockSymbol = ui.userInput();
            }

            String quantityInput = "";
            // edge cases player cannot enter more than what they have
            while (isSellMenuRunning) {
                System.out.println("Please enter the quantity:");

                quantityInput = ui.userInput();
                while (!isPositiveInteger(quantityInput)) {

                    System.out.println("Your input is not a positive integer. Please try again.");
                    System.out.println("How many shares would you like? " +
                            "Fractional numbers are not allowed! (Enter an integer ONLY)");
                    quantityInput = ui.userInput();
                }
                int quantity = Integer.parseInt(quantityInput);

                if (playerStockMap.get(stockSymbol) >= quantity) {
                    player.getAccount().calculateBalance(quantity *
                            inventory.findBySymbol(stockSymbol).getCurrentPrice());
                    // update map once the sell is completed
                    playerStockMap.put(stockSymbol, playerStockMap.get(stockSymbol) - quantity);
                    if (playerStockMap.get(stockSymbol) == 0) {
                        playerStockMap.remove(stockSymbol);

                        //SOUNDS**************************************
                        Scanner scanner = new Scanner(System.in);

                        File file = new File("src/main/resources/sell.wav");
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.start();
                    }
                    isSellMenuRunning = false;
                } else {
                    System.out.println("Please try again and enter the valid stock quantity.\n");
                }

            }
            System.out.println("You have sold " + quantityInput
                    + " shares of " + inventory.findBySymbol(stockSymbol).getStockName() + ".\n");
            System.out.println("Yours current Holdings After the transaction:\n");
            System.out.format("%-15s%-15s\n", "Stock Symbol", "Quantity");
            for (int i = 0; i < keyList.size(); i++) {
                System.out.format("%-15s%-15s\n", keyList.get(i),
                        playerStockMap.get(keyList.get(i)) == null ?
                                "0" : playerStockMap.get(keyList.get(i)));
            }
        }
    }

}
