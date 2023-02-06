package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class GameClient {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        Game game=new Game();
        game.gameOn();
        System.out.println("Would you like to play again?");
        System.out.println("Enter 1 for YES \nEnter 2 for NO");
        Scanner scanMe = new Scanner(System.in);
        int options = scanMe.nextInt();
        while (options < 2){
            game.gameOn();
            options++;
        }

        Scanner scanner = new Scanner(System.in);

        File file = new File("src/main/java/cashier.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
        String response = scanner.next();


    }
}
