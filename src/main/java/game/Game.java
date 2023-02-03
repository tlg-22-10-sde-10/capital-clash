package game;

import Random.RandomNumberForNews;
import news.News;
import players.Player;
import stock.Stock;
import storage.StockInventory;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Game {
    News news=new News();

    public  UserInterface ui = new UserInterface();
    private StockInventory inventory;
    private final int GAME_DAYS = 5;
    private final String REPLY_WITH_YES = "y";
    private final String NUMBER_ONE = "1";



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
            ui.playerVsBrotherReports(day);
            int newsIndexOfTheDay=RandomNumberForNews.getRandomNumber();
            String todayNews=news.getNewsContent(newsIndexOfTheDay);
            int mainMenuSelection;
            Random random = new Random();
            double mktReturnOfTheDay;

            if(newsIndexOfTheDay==1){
                //case 1:x will be a random number between 1% and 2%
                mktReturnOfTheDay=(random.nextDouble() * 1 + 1)/100.0;
            }else if(newsIndexOfTheDay==6){
                //case 6:x will be a random number between -2% to -1%
                mktReturnOfTheDay=(random.nextDouble() * 1 - 2)/100.0;
            }else if(newsIndexOfTheDay==7){
                //case 7:x will be a random number between -2% to - 3%
                mktReturnOfTheDay=(random.nextDouble() * 1 - 3)/100.0;
            }else{
                mktReturnOfTheDay=(random.nextDouble() * 6 - 3)/100.0;
            }

            do {
                ui.mainMenu();
                mainMenuSelection = stdInt.nextInt();

                switch (mainMenuSelection) {
                    // trading room
                    case 1:
                        ui.titleBarForInventory();
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
                        break;
                    default:
                        ui.invalidChoice();
                }

            } while (mainMenuSelection != 3);
            day++;
        }
    }

}
