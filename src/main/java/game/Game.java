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
import java.util.*;




public class Game {
    News news=new News();

    Player player = new Player("Player",new Account("checking"));
    Computer brother = new Player("Brother",new Account("checking"));

    public  UserInterface ui = new UserInterface();
    private StockInventory inventory;
    private final int GAME_DAYS = 5;
    private final String REPLY_WITH_YES = "y";
    private final String NUMBER_ONE = "1";
    private final String NUMBER_TWO = "2";


    Map<String,Integer> playerStockMap = new HashMap<>();
    Map<String,Integer> brotherStockMap = new HashMap<>();

    List<String> playerStocks = new ArrayList<>();
    List<String> brotherStocks = new ArrayList<>();


    public Game() throws FileNotFoundException {
        inventory = new StockInventory();
    }

    public void gameOn() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        ui.displayASCII();
       boolean isStartMenuRunning = true;
       while(isStartMenuRunning) {
           ui.startMenu();
           String selection = ui.userInput();
           if (selection.equalsIgnoreCase(NUMBER_ONE)) {
               play();
               isStartMenuRunning=false;
           } else if (selection.equalsIgnoreCase(NUMBER_TWO)){
               ui.thankYouMessage();
               isStartMenuRunning=false;
           } else {
               ui.invalidChoice();
           }
       }
    }

    private void play() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        ui.displayGameInfo();
        int day = 0;
        Scanner stdInt = new Scanner(System.in);

        while (day < GAME_DAYS) {
            int newsIndexOfTheDay=RandomNumberForNews.getRandomNumber();
            String todayNews=news.getNewsContent(newsIndexOfTheDay);
            int mainMenuSelection;

            MarketReturnGenerator generator=new MarketReturnGenerator();
            double mktReturnOfTheDay=generator.nextMarketReturn(newsIndexOfTheDay);

           ui.playerVsBrotherReports(day,player,brother,mktReturnOfTheDay,newsIndexOfTheDay,inventory);
            do {
                ui.mainMenu();
                mainMenuSelection = stdInt.nextInt();

                switch (mainMenuSelection) {
                    // trading room
                    case 1:

                        ui.titleBarForInventory(day);

                        //first day of trading: stock price is directly from csv file
//                        System.out.println("mktReturn:"+mktReturnOfTheDay);
//                        System.out.println("newsIndexOfTheDay:"+newsIndexOfTheDay);
                        if(day==0) {
                            for (Stock stock : inventory.getAllStocks()) {
                                System.out.println(stock.toString());
                            }
                        }
                        // the following days: function needed to be run to update stock price
                        else{
                            // player Report1
                            for (Stock stock : inventory.getAllStocks()) {
                                 //stock
                                double nextPrice=stock.nextDayPrice(stock.getCurrentPrice(),
                                        mktReturnOfTheDay,newsIndexOfTheDay);
                                stock.setCurrentPrice(nextPrice);
                            }

                            for (Stock stock : inventory.getAllStocks()) {
                                System.out.println(stock.toString());
                            }

                        }

                        ui.tradingRoomMenu();
                        String userInputForBuyAndSale = ui.userInput();
                        if(userInputForBuyAndSale.equalsIgnoreCase(NUMBER_ONE)) {
                            System.out.println("Please enter the symbol of the stock that you want to purchase:");
                            String stockSymbol = ui.userInput();
                            System.out.println("How many shares would you like? Fractional is not allowed! (Enter whole number ONLY)");

                            int numberOfStockPurchaseByPlayer = Integer.parseInt(ui.userInput());

                            Stock playerStock = inventory.findBySymbol(stockSymbol);
                            double valueOfStockPurchasedByPlayer = numberOfStockPurchaseByPlayer*playerStock.getCurrentPrice();
                            if(valueOfStockPurchasedByPlayer > player.getAccount().getCashBalance()) {
                                System.out.println("Unauthorized Purchase! Not Enough Balance!");
                            } else {
                                if(playerStockMap.containsKey(stockSymbol)) {
                                    playerStockMap.put(playerStock.getSymbol(),playerStockMap.get(stockSymbol)+numberOfStockPurchaseByPlayer);
                                }else{
                                    playerStockMap.put(playerStock.getSymbol(),numberOfStockPurchaseByPlayer);
                                }

                                playerStocks.add(playerStock.getSymbol());
                                player.setStockNames(playerStocks);
                                player.setStocks(playerStockMap);
                                player.getAccount().deductBalance(numberOfStockPurchaseByPlayer*playerStock.getCurrentPrice());
                                System.out.println("Successfully Purchased!");

                                //SOUND
                                Scanner scanner = new Scanner(System.in);
                                File file = new File("src/main/java/cashier.wav");
                                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioStream);

                                clip.start();
                            }
                            // brother randomly purchase the stock
                            int numberOfStockPurchasedByBrother = 1 + (int)(Math.random() * 6);
                            Stock brotherStock = inventory.getRandomStock();
                            brotherStockMap.put(brotherStock.getSymbol(),numberOfStockPurchasedByBrother);
                            brother.setStocks(brotherStockMap);
                            brother.getAccount().deductBalance(numberOfStockPurchasedByBrother*brotherStock.getCurrentPrice());

                        } else if(userInputForBuyAndSale.equalsIgnoreCase(NUMBER_TWO)) {
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
                            while(isSellMenuRunning) {
                                System.out.println("Please enter the quantity!");
                                int quantity = Integer.parseInt(ui.userInput());

                                if(playerStockMap.get(stockSymbol)>=quantity) {
                                    player.getAccount().calculateBalance(quantity*
                                            inventory.findBySymbol(stockSymbol).getCurrentPrice());
                                    // update map once the sell is completed
                                    playerStockMap.put(stockSymbol,playerStockMap.get(stockSymbol)-quantity);
                                    if(playerStockMap.get(stockSymbol) == 0) {
                                        playerStockMap.remove(stockSymbol);

                                        Scanner scanner = new Scanner(System.in);
                                        File file = new File("src/main/java/sell.wav");
                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                                        Clip clip = AudioSystem.getClip();
                                        clip.open(audioStream);

                                        clip.start();


                                    }
                                    isSellMenuRunning= false;
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

                        }
                        break;
                    // news room
                    case 2:
                        ui.newsRoomInfo();
                        String newsAnswer= ui.userInput();
                        if(newsAnswer.equalsIgnoreCase(REPLY_WITH_YES)){
                            System.out.println(todayNews);
                        }else{
                           ui.newsDecline();
                        }
                        break;
                    // Next Day Logic
                    case 3:
                        double totalPlayerBalance=player.getAccount().getCashBalance();
                        double totalBrotherBalance=brother.getAccount().getCashBalance();

                        if(day == 4) {
                            for (Map.Entry<String, Integer> entry : playerStockMap.entrySet()) {
                                totalPlayerBalance+=inventory.findBySymbol(entry.getKey())
                                        .nextDayPrice(inventory.findBySymbol(entry.getKey()).getCurrentPrice(),mktReturnOfTheDay,newsIndexOfTheDay)*entry.getValue();
                            }

                            for (Map.Entry<String, Integer> entry : brotherStockMap.entrySet()) {
                                totalBrotherBalance+=inventory.findBySymbol(entry.getKey())
                                        .nextDayPrice(inventory.findBySymbol(entry.getKey()).getCurrentPrice(),mktReturnOfTheDay,newsIndexOfTheDay)*entry.getValue();
                            }

                            if(totalPlayerBalance>totalBrotherBalance) {
                                ui.playerWinMessage();
                                //SOUND
                                Scanner scanner = new Scanner(System.in);
                                File file = new File("src/main/java/success.wav");
                                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioStream);

                                clip.start();

                            } else if(totalPlayerBalance<totalBrotherBalance){
                                ui.brotherWinMessage();
                            } else {
                                System.out.println("Tie Game! ");
                            }

                        }else if(day == 3) {
                            ui.lastDay();
                        }else {
                            ui.nextDay();
                        }

                        break;
                    default:
                        ui.invalidChoice();
                }
            } while (mainMenuSelection != 3);
            day++;
        }
    }

}

