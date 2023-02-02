package game;

import stock.Stock;
import storage.StockInventory;
import ui.UserInterface;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Game {
    private StockInventory inventory;

    private final int GAME_DAYS = 5;

    public Game() throws FileNotFoundException {
        inventory = new StockInventory();
    }


    public void gameOn() {
        UserInterface.displayASCII();
        //refactor and use a user interface class
        System.out.println("Would you like to take the challenge?");
        System.out.println("1: Yes \n2: No");
        Scanner userResponse = new Scanner(System.in);
        int selection = userResponse.nextInt();

        if (selection == 1) {
            play();

        } else {
            //exit out of game
            System.out.println("Bummer");
        }

    }

    private void play() {
        UserInterface.displayGameInfo();
        //UserInterface.tradingRoomMenu();

        int day = 0;
        Scanner stdInt = new Scanner(System.in);

        while (day < GAME_DAYS) {

            System.out.println("It is day " + day + ".");

            int mainMenuSelection = 0;
            do {
                UserInterface.mainMenu();
                mainMenuSelection = stdInt.nextInt();
                switch (mainMenuSelection) {
                    case 1://going to trading room
                        UserInterface.titleBarForInventory();
                        for(Stock stock : inventory.getAllStocks()) {
                            System.out.println(stock.toString());
                        }

                        System.out.println("You are in trading room.");
                        break;
                    case 2:
                        System.out.println("You are in news room.");
                        break;
                    case 3:
                        System.out.println("You are done for the day. The game will move to the next day.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }


            } while (mainMenuSelection != 3);

            day++;

        }

        //Which room would you like to go to
        //--1.News feed room -- news for the day
        //--2.Trade room -- place order for the day
        //--3.Go Home -- move on to the next day


        System.out.println("Let's play");

    }


}
