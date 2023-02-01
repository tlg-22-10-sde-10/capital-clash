package game;

import java.util.Scanner;

public class Game {

    private final int GAME_DAYS = 5;


    public void play() {

        int selection=1;
        do{
            displayASCII();
            System.out.println("Would you like to take the challenge?");
            System.out.println("1 for yes, 2 for no.");
            Scanner userResponse=new Scanner(System.in);
            selection=userResponse.nextInt();
        //System.out.println(selection);


        }while(selection==1);

    }

    public static void displayASCII(){

        System.out.println("Welcome to Capital Clash.");
    }
}
