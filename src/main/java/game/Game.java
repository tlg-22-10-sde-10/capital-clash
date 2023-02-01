package game;

import ui.UserInterface;

import java.util.Scanner;


public class Game {

    private final int GAME_DAYS = 5;
    UserInterface ui  = new UserInterface();

    public Game() {
    }

    public void gameOn() {

            ui.displayASCII();
            ui.gameBegin();
            String userInput = ui.userInput();
            if (userInput.equalsIgnoreCase("1")) {
                play();

            } else {
                //exit out of game
                System.out.println("Thank you for participating!");

            }
        }


    private void play() {
        ui.displayGameInfo();
        while(true) {
            String userInput = ui.mainMenu();
            if(userInput.equalsIgnoreCase("1")) {
                ui.getNewsfeed();
            } else if(userInput.equalsIgnoreCase("2")) {
                ui.tradingRoomMenu();
            } else {
                break;
            }
        }
    }
}
