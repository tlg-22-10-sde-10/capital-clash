package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        boolean startGame=true;
        Scanner scanMe = new Scanner(System.in);

            while(startGame){
                Game game=new Game();
                game.gameOn();
                System.out.println("Would you like to play again?");
                System.out.println("1) YES \n2) NO\n");

                String sc = scanMe.nextLine();
                while( !sc.equals("1") && !sc.equals("2") ){
                    System.out.println("Please select either 1 or 2.");
                    sc = scanMe.nextLine();

                }
                if(sc.equals("2")){
                    startGame=false;
                }
            }
        }
}
//
//        Scanner scanner = new Scanner(System.in);
//
//        File file = new File("src/main/java/cashier.wav");
//        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioStream);
//
//        clip.start();
//        String response = scanner.next();


