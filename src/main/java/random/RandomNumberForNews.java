package random;

import java.util.Random;

public class RandomNumberForNews {
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
