package game;

import stock.Stock;
import storage.StockFileReader;
import storage.StockInventory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameClient {

    public static void main(String[] args) throws FileNotFoundException {
        Game game=new Game();
        game.gameOn();
        System.out.println("Do you wish to play again?");
        System.out.println("Enter 1 for YES \n Enter 2 for NO");
        Scanner scanMe = new Scanner(System.in);
        int options = scanMe.nextInt();
        while (options < 2){
            game.gameOn();
            options++;
        }

    }
}
