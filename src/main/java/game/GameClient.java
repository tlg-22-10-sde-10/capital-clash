package game;

import ui.GlobalMethodsAndAttributes;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Scanner;
public class GameClient {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        boolean startGame = true;

        Scanner scanMe = new Scanner(System.in);
        while (startGame) {
            Game game = new Game();
            game.gameOn();

            System.out.println("\nWould you like to" + ANSI_RED + " exit " + ANSI_RESET + "the game?");
            System.out.println("1) YES \n2) NO\n");

            String sc = scanMe.nextLine();
            while (!sc.equals("1") && !sc.equals("2")) {
                System.out.println("Please select either 1 or 2.");
                sc = scanMe.nextLine();
            }
            if (sc.equals("1")) {
                startGame = false;
            }
        }
    }
}





