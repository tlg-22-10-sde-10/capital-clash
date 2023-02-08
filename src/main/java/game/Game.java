package game;

import Random.RandomNumberForNews;
import account.Account;
import marketReturn.MarketReturnGenerator;
import news.News;
import players.Computer;
import players.Player;
import stock.Stock;
import storage.StockInventory;
import ui.UserInterface;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;


public class Game {
    News news = new News();

    Player player = new Player("Player", new Account("checking"));
    Computer brother = new Player("Brother", new Account("checking"));

    public UserInterface ui = new UserInterface();
    private StockInventory inventory;
    private final int GAME_DAYS = 5;
    private final String REPLY_WITH_YES = "y";
    private final String NUMBER_ONE = "1";
    private final String NUMBER_TWO = "2";
    private final String NUMBER_THREE = "3";
    private static final DecimalFormat df=new DecimalFormat("0.00");

    Map<String, Integer> playerStockMap = new HashMap<>();
    Map<String, Integer> brotherStockMap = new HashMap<>();

    List<String> playerStocks = new ArrayList<>();
    List<String> brotherStocks = new ArrayList<>();


    public Game() throws FileNotFoundException {
        inventory = new StockInventory();
    }

    public void gameOn() {
        ui.displayASCII();
        boolean isStartMenuRunning = true;
        while (isStartMenuRunning) {
            ui.startMenu();
            String selection = ui.userInput();
            if (selection.equalsIgnoreCase(NUMBER_ONE)) {
                play();
                isStartMenuRunning = false;
            } else if (selection.equalsIgnoreCase(NUMBER_TWO)) {
                ui.thankYouMessage();
                isStartMenuRunning = false;
            } else {
                ui.invalidChoice();
            }
        }
    }


    private void play() throws IllegalArgumentException, InputMismatchException {

        try {
            ui.displayGameInfo();
            int day = 0;

            Scanner stdInt = new Scanner(System.in);

            while (day < GAME_DAYS) {

                int newsIndexOfTheDay = RandomNumberForNews.getRandomNumber();
                String todayNews = news.getNewsContent(newsIndexOfTheDay);
                int mainMenuSelection;


                MarketReturnGenerator generator = new MarketReturnGenerator();
                double mktReturnOfTheDay = generator.nextMarketReturn(newsIndexOfTheDay);

                updateDashboard(day, newsIndexOfTheDay, mktReturnOfTheDay);


                ui.playerVsBrotherReports(day, player, brother,inventory);

                do {
                    ui.mainMenu();
                    mainMenuSelection = stdInt.nextInt();

                    switch (mainMenuSelection) {
                        // trading room
                        case 1:
                            showTradingRoomStockDashboard(day);


                            ui.tradingRoomMenu();
                            String userInputForBuyAndSale = ui.userInput();
                            //BUY LOGIC
                            if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_ONE)) {
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


                                    //SOUNDS**************************************
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

                            } else if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_TWO)) {
                                if(playerStockMap.isEmpty()){
                                    System.out.println("You currently do not have any stock. So you are not able to " +
                                            "do sell transaction.\n");
                                }else{

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

                                    String quantityInput="";
                                    // edge cases player cannot enter more than what they have
                                    while (isSellMenuRunning) {
                                        System.out.println("Please enter the quantity:");

                                        quantityInput = ui.userInput();
                                        while(!isInteger(quantityInput)){

                                            System.out.println("Your input is not an integer. Please try again.");
                                            System.out.println("How many shares would you like? " +
                                                    "Fractional numbers are not allowed! (Enter an integer ONLY)");
                                            quantityInput = ui.userInput();
                                        }
                                        int quantity = Integer.parseInt(quantityInput);
                                        //int quantity = Integer.parseInt(ui.userInput());

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
                                    System.out.println("You have sold "+quantityInput
                                            +" shares of "+ inventory.findBySymbol(stockSymbol).getStockName()+".\n");
                                    System.out.println("Yours current Holdings After the transaction:\n");
                                    System.out.format("%-15s%-15s\n", "Stock Symbol", "Quantity");
                                    for (int i = 0; i < keyList.size(); i++) {
                                        System.out.format("%-15s%-15s\n", keyList.get(i),
                                                playerStockMap.get(keyList.get(i))==null?
                                                        "0":playerStockMap.get(keyList.get(i)));
                                    }
                                }


                            } else if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_THREE)) {
                                ui.playerVsBrotherReports(day, player, brother,inventory);
                            }
                            break;

                        // news room
                        case 2:

                            newsRoomOps(todayNews);
                            break;

                        // Next Day Logic
                        case 3:

                            nextDayOps(day);
                            break;

                        default:
                            ui.invalidChoice();
                    }

                } while (mainMenuSelection != 3);

                day++;

            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Please provide valid value and try again.\n");

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //case 2
    private void newsRoomOps(String todayNews) {
        ui.newsRoomInfo();
        String newsAnswer = ui.userInput();

        if (newsAnswer.equalsIgnoreCase("1")) {
            System.out.println("\n===================================");
            System.out.println("Breaking News:");
            System.out.println(todayNews + "\n===================================");
        } else if (newsAnswer.equalsIgnoreCase("2")) {
            ui.newsDecline();
        } else {
            ui.invalidChoice();
        }
    }

    //case 3
    private void nextDayOps(int day) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        double totalPlayerBalance = player.getAccount().getCashBalance();
        double totalBrotherBalance = brother.getAccount().getCashBalance();

        if (day == 4) {
            for (Map.Entry<String, Integer> entry : playerStockMap.entrySet()) {
                totalPlayerBalance += inventory.findBySymbol(entry.getKey()).getCurrentPrice()
                         * entry.getValue();
            }

            for (Map.Entry<String, Integer> entry : brotherStockMap.entrySet()) {
                totalBrotherBalance += inventory.findBySymbol(entry.getKey()).getCurrentPrice()
                        * entry.getValue();
            }

            System.out.println("Your total balance is $"+df.format(totalPlayerBalance)+".");
            System.out.println("Your Brother's total balance is $"+df.format(totalBrotherBalance)+".");

            if (totalPlayerBalance > totalBrotherBalance) {
                ui.playerWinMessage();

                //SOUNDS
                File file = new File("src/main/resources/piglevelwin2mp3-14800.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.start();

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

    private void showTradingRoomStockDashboard(int day) {

        ui.titleBarForInventory(day);

        for (Stock stock : inventory.getAllStocks()) {
            System.out.println(stock.toString());
        }

    }

    private void updateDashboard(int day, int newsIndexOfTheDay, double mktReturnOfTheDay) {

        if (day != 0) {
            for (Stock stock : inventory.getAllStocks()) {
                //stock
                double nextPrice = stock.UpdateStockPriceForTheDay(stock.getCurrentPrice(),
                        mktReturnOfTheDay, newsIndexOfTheDay);
                stock.setCurrentPrice(nextPrice);

            }
        }

    }

    private boolean isInteger(String strNum) {
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

}