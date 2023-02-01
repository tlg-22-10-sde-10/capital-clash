package game;

import java.util.Scanner;

public class Game {

    private final int GAME_DAYS = 5;


    public void play() {


            displayASCII();

            //refactor and use a user interface class
            System.out.println("Would you like to take the challenge?");
            System.out.println("1: Yes \n2: No");
            Scanner userResponse = new Scanner(System.in);
            int selection = userResponse.nextInt();

            if (selection == 1){
                //Game logic goes here
                //Which room would you like to go to
                //--1.News feed room -- news for the day
                //--2.Trade room -- place order for the day
                //--3.Go Home -- move on to the next day
                System.out.println("Let's play");
            }
            else{
                //exit out of game
                System.out.println("Bummer");
            }


    }

    public static void displayASCII(){

        System.out.println("Welcome to Capital Clash.");
    }
}
