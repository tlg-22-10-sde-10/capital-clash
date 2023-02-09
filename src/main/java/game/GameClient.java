package game;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
//        playAudio("cashier.wav.wav");
        boolean startGame = true;
        Scanner scanMe = new Scanner(System.in);

        while (startGame) {
            Game game = new Game();
            game.gameOn();
            System.out.println("Would you like to play again?");
            System.out.println("1) YES \n2) NO\n");

            String sc = scanMe.nextLine();
            while (!sc.equals("1") && !sc.equals("2")) {
                System.out.println("Please select either 1 or 2.");
                sc = scanMe.nextLine();

            }
            if (sc.equals("2")) {
                startGame = false;
            }
        }
    }

    public static void playAudio(String audioFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(ClassLoader.getSystemResourceAsStream(audioFile)))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
    }
}





