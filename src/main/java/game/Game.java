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

import java.io.FileNotFoundException;
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


                ui.playerVsBrotherReports(day, player, brother, mktReturnOfTheDay, newsIndexOfTheDay, inventory);

                do {
                    ui.mainMenu();
                    mainMenuSelection = stdInt.nextInt();

                    switch (mainMenuSelection) {
                        // trading room
                        case 1:
                            showTradingRoomStockDashboard(day, newsIndexOfTheDay,
                                    mktReturnOfTheDay);


                            ui.tradingRoomMenu();
                            String userInputForBuyAndSale = ui.userInput();
                            //BUY LOGIC
                            if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_ONE)) {
                                System.out.println("Please enter the symbol of the stock that you want to purchase:");
                                String stockSymbol = ui.userInput();

                                if (inventory.findBySymbol(stockSymbol) == null) {

                                    while (inventory.findBySymbol(stockSymbol) == null) {
                                        System.out.println("This stock is not offered.");
                                        System.out.println("Please try again. Please select from the List");
                                        showTradingRoomStockDashboard(day, newsIndexOfTheDay,
                                                mktReturnOfTheDay);
                                        System.out.println("Please enter the symbol of the stock" +
                                                " that you want to purchase:");
                                        stockSymbol = ui.userInput();
                                    }

                                }
                                System.out.println("How many shares would you like? " +
                                        "Fractional is not allowed! (Enter whole number ONLY)");

                                int numberOfStockPurchaseByPlayer = Integer.parseInt(ui.userInput());

                                Stock playerStock = inventory.findBySymbol(stockSymbol);
                                double valueOfStockPurchasedByPlayer = numberOfStockPurchaseByPlayer * playerStock.getCurrentPrice();
                                if (valueOfStockPurchasedByPlayer > player.getAccount().getCashBalance()) {
                                    System.out.println("Unauthorized Purchase! Not Enough Balance!");
                                } else {
                                    if (playerStockMap.containsKey(stockSymbol)) {
                                        playerStockMap.put(playerStock.getSymbol(), playerStockMap.get(stockSymbol) + numberOfStockPurchaseByPlayer);
                                    } else {
                                        playerStockMap.put(playerStock.getSymbol(), numberOfStockPurchaseByPlayer);
                                    }

                                    playerStocks.add(playerStock.getSymbol());
                                    player.setStockNames(playerStocks);
                                    player.setStocks(playerStockMap);
                                    player.getAccount().deductBalance(numberOfStockPurchaseByPlayer * playerStock.getCurrentPrice());
                                    System.out.println("Successfully Purchased!");
                                }
                                // brother randomly purchase the stock
                                int numberOfStockPurchasedByBrother = 1 + (int) (Math.random() * 6);
                                Stock brotherStock = inventory.getRandomStock();
                                brotherStockMap.put(brotherStock.getSymbol(), numberOfStockPurchasedByBrother);
                                brother.setStocks(brotherStockMap);
                                brother.getAccount().deductBalance(numberOfStockPurchasedByBrother * brotherStock.getCurrentPrice());

                            } else if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_TWO)) {
                                ArrayList<String> keyList = new ArrayList<String>(playerStockMap.keySet());
                                System.out.println("Yours current Holdings!");
                                System.out.format("%-15s%-15s\n", "Stock Symbol", "Quantity");
                                for (int i = 0; i < keyList.size(); i++) {
                                    System.out.format("%-15s%-15s\n", keyList.get(i),
                                            playerStockMap.get(keyList.get(i)));
                                }

                                boolean isSellMenuRunning = true;

                                System.out.println("Please enter the stock symbol that you want to sell.");
                                String stockSymbol = ui.userInput();
                                // edge cases player cannot enter more than what they have
                                while (isSellMenuRunning) {
                                    System.out.println("Please enter the quantity!");
                                    int quantity = Integer.parseInt(ui.userInput());

                                    if (playerStockMap.get(stockSymbol) >= quantity) {
                                        player.getAccount().calculateBalance(quantity *
                                                inventory.findBySymbol(stockSymbol).getCurrentPrice());
                                        // update map once the sell is completed
                                        playerStockMap.put(stockSymbol, playerStockMap.get(stockSymbol) - quantity);
                                        if (playerStockMap.get(stockSymbol) == 0) {
                                            playerStockMap.remove(stockSymbol);
                                        }
                                        isSellMenuRunning = false;
                                    } else {
                                        System.out.println("Please try again and enter the valid stock quantity!\n");
                                    }

                                }

                                System.out.println("Yours current Holdings After Sell!\n");
                                System.out.format("%-15s%-15s\n", "Stock Symbol", "Quantity");
                                for (int i = 0; i < keyList.size(); i++) {
                                    System.out.format("%-15s%-15s\n", keyList.get(i),
                                            playerStockMap.get(keyList.get(i)));
                                }

                            } else if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_THREE)) {
                                ui.playerVsBrotherReports(day, player, brother,
                                        mktReturnOfTheDay, newsIndexOfTheDay, inventory);
                            }
                            break;

                        // news room
                        case 2:

                            newsRoomOps(todayNews);
                            break;

                        // Next Day Logic
                        case 3:

                            nextDayOps(day, newsIndexOfTheDay, mktReturnOfTheDay);
                            break;

                        default:
                            ui.invalidChoice();
                    }

                } while (mainMenuSelection != 3);

                day++;

            }
        } catch (InputMismatchException e) {
            System.out.print("Please provide valid value and try again.\n");

        }

    }

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

    //option 3
    private void nextDayOps(int day, int newsIndexOfTheDay, double mktReturnOfTheDay) {
        double totalPlayerBalance = player.getAccount().getCashBalance();
        double totalBrotherBalance = brother.getAccount().getCashBalance();

        if (day == 4) {
            for (Map.Entry<String, Integer> entry : playerStockMap.entrySet()) {
                totalPlayerBalance += inventory.findBySymbol(entry.getKey())
                        .UpdateStockPriceForTheDay(inventory.findBySymbol(entry.getKey()).getCurrentPrice(), mktReturnOfTheDay, newsIndexOfTheDay) * entry.getValue();
            }

            for (Map.Entry<String, Integer> entry : brotherStockMap.entrySet()) {
                totalBrotherBalance += inventory.findBySymbol(entry.getKey())
                        .UpdateStockPriceForTheDay(inventory.findBySymbol(entry.getKey()).getCurrentPrice(), mktReturnOfTheDay, newsIndexOfTheDay) * entry.getValue();
            }

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

    private void showTradingRoomStockDashboard(int day, int newsIndexOfTheDay,
                                               double mktReturnOfTheDay) {
        ui.titleBarForInventory(day);

        for (Stock stock : inventory.getAllStocks()) {
            System.out.println(stock.toString());
        }

        //the following two are for testing verification purpose;removed b4 final release
        //System.out.println("mktReturn:" + mktReturnOfTheDay);
        //System.out.println("newsIndexOfTheDay:" + newsIndexOfTheDay);

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

}