package game;

import Random.RandomNumberForNews;
import news.News;
import players.Player;
import stock.Stock;
import storage.StockInventory;
import ui.UserInterface;

import java.io.FileNotFoundException;
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
            String todayNews=news.getNewsContent(RandomNumberForNews.getRandomNumber());
            int mainMenuSelection = 0;
            do {
                ui.mainMenu();
                mainMenuSelection = stdInt.nextInt();

                switch (mainMenuSelection) {
                    // trading room
                    case 1:
                        ui.titleBarForInventory();
                        for(Stock stock : inventory.getAllStocks()) {
                            System.out.println(stock.toString());
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
