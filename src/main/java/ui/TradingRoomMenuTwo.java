package ui;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;

import static ui.GlobalMethodsAndAttributes.*;

public class TradingRoomMenuTwo {
    public static void menuTwoSell() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        if (playerStockMap.isEmpty()) {
            System.out.println(ANSI_RED+"                          ***No Current Holdings. Transactions cannot be completed***\n"+ANSI_RESET);
        } else {
            ArrayList<String> playersStocksLists = new ArrayList<String>(playerStockMap.keySet());

            showHoldings(playersStocksLists);
            boolean isSellMenuRunning = true;
            System.out.println("Please enter the stock symbol that you want to sell.");
            String stockSymbol = ui.userInput();
            //handle unrecognized symbol error
            while (!playerStockMap.containsKey(stockSymbol)) {
                System.out.println(ANSI_RED+"                          ***This stock is not in your holdings***"+ANSI_RESET);
                System.out.println(ANSI_RED+"                          ***Please try again and select from your holdings.***\n"+ANSI_RESET);
                showHoldings(playersStocksLists);
                System.out.println("Please enter the stock symbol that you want to sell.");
                stockSymbol = ui.userInput().toUpperCase();
            }

            String quantityInput = "";
            // edge cases player cannot enter more than what they have
            while (isSellMenuRunning) {
                System.out.println("Please enter the quantity:");

                quantityInput = ui.userInput();
                while (!isPositiveInteger(quantityInput)) {


                    System.out.println(ANSI_RED+"                          ***Your input is not an integer. Please try again.***\n"+ANSI_RESET);

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
                        // plays sound after sell transactions
                        GlobalMethodsAndAttributes.playAudio("sell.wav");
                    }
                    isSellMenuRunning = false;
                } else {
                    System.out.println(ANSI_RED+"                          ***Please try again and enter the valid stock quantity.***\n"+ANSI_RESET);

                }

            }
            System.out.println(ANSI_GREEN+"                          ***Successfully Sold "+quantityInput
                    +" shares of "+ inventory.findBySymbol(stockSymbol).getStockName()+  "***"+           ANSI_RESET);

        }
    }

}
