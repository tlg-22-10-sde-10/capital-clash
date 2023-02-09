package random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberForNews {

    private static Set<Integer> shownNews= new HashSet<>();

    public static int getRandomNumber() {
        Random random = new Random();
        int number=random.nextInt(10) + 1;

        while(shownNews.contains(number)){
            number=random.nextInt(10) + 1;
        }
        //for testing to verify no news shown twice
        //System.out.println(shownNews);

        shownNews.add(number);
        return number;
    }
}
