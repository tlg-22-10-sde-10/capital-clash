package game;

import Random.RandomNumberForNews;
import marketReturn.MarketReturnGenerator;
import account.Account;
import news.News;
import org.w3c.dom.ls.LSOutput;
import players.Computer;
import players.Player;
import stock.Stock;
import storage.StockInventory;
import ui.UserInterface;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Game {
    News news=new News();

    Player player = new Player("Player",new Account("checking",0.00),null);
    Computer brother = new Player("Brother",new Account("checking",0.00),null);

    public  UserInterface ui = new UserInterface();
    private StockInventory inventory;
    private final int GAME_DAYS = 5;
    private final String REPLY_WITH_YES = "y";
    private final String NUMBER_ONE = "1";
    List<String> playerStocks = new ArrayList<>();
    List<String> brotherStocks = new ArrayList<>();


    public Game() throws FileNotFoundException {
        inventory = new StockInventory();
    }

    public void gameOn() {
        ui.displayASCII();
        ui.startMenu();
        String selection = ui.userInput();
        if (selection.equalsIgnoreCase(NUMBER_ONE)) {
            play();
        } else {
            ui.thankYouMessage();

        }
    }

    private void play() {
        ui.displayGameInfo();
        int day = 0;

        Scanner stdInt = new Scanner(System.in);
        while (day < GAME_DAYS) {


            int newsIndexOfTheDay=RandomNumberForNews.getRandomNumber();
            String todayNews=news.getNewsContent(newsIndexOfTheDay);
            int mainMenuSelection;

            MarketReturnGenerator generator=new MarketReturnGenerator();
            double mktReturnOfTheDay=generator.nextMarketReturn(newsIndexOfTheDay);

            ui.playerVsBrotherReports(day,player,brother);


            do {
                ui.mainMenu();
                mainMenuSelection = stdInt.nextInt();

                switch (mainMenuSelection) {
                    // trading room
                    case 1:

                        ui.titleBarForInventory(day);
                        //first day of trading: stock price is directly from csv file
                        if(day==0) {
                            for (Stock stock : inventory.getAllStocks()) {
                                System.out.println(stock.toString());
                            }
                        }
                        //the following days: function needed to be run to update stock price
                        else{
                            for (Stock stock : inventory.getAllStocks()) {
                                 //stock
                                double nextPrice=stock.nextDayPrice(stock.getCurrentPrice(),
                                        mktReturnOfTheDay,newsIndexOfTheDay);
                                stock.setCurrentPrice(nextPrice);
                            }
                            //the following two souts are for testing, will be commented out for official release
                            System.out.println("mktReturn:"+mktReturnOfTheDay);
                            System.out.println("newsIndexOfTheDay:"+newsIndexOfTheDay);

                            for (Stock stock : inventory.getAllStocks()) {
                                System.out.println(stock.toString());
                            }

                        }
                        ui.tradingRoomMenu();
                        String userInputForBuySale = ui.userInput();
                        if(userInputForBuySale.equalsIgnoreCase(NUMBER_ONE)) {
                            System.out.println("Please enter the symbol of the stock that you want to purchase:");
                            String stockSymbol = ui.userInput();
                            System.out.println("How many shares would you like? Fractional is not allowed! (Enter whole number ONLY)");

                            int numberOfStockPurchaseByPlayer = Integer.parseInt(ui.userInput());
                            Stock playerStock = inventory.findBySymbol(stockSymbol);
                            double valueOfStockPurchasedByPlayer = numberOfStockPurchaseByPlayer*playerStock.getCurrentPrice();
                            if(valueOfStockPurchasedByPlayer > player.getAccount().getCashBalance()) {
                                System.out.println("Unauthorized Purchase! Not Enough Balance!");
                            } else {
                                playerStocks.add(playerStock.getStockName());
                                player.setStockNames(playerStocks);
                                player.getAccount().deductBalance(numberOfStockPurchaseByPlayer*playerStock.getCurrentPrice());
                                System.out.println("Successfully Purchased!");
                            }
                            // brother randomly purchase the stock
                            int numberOfStockPurchasedByBrother = 1 + (int)(Math.random() * 6);
                            Stock brotherStock = inventory.getRandomStock();
                            brotherStocks.add(brotherStock.getStockName());
                            brother.setStockNames(brotherStocks);
                            brother.getAccount().deductBalance(numberOfStockPurchasedByBrother*brotherStock.getCurrentPrice());


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
                        ui.nextDay();
//                        for(Stock stock: inventory.getAllStocks()) {
//                            stock.setCurrentPrice(stock.getAlpha(), stock.getBeta(), stock.getResidual());
//                        }
                        break;
                    default:
                        ui.invalidChoice();
                }

            } while (mainMenuSelection != 3);
            day++;
        }
    }

}
