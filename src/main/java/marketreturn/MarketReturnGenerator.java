package marketreturn;

import java.util.Random;

public class MarketReturnGenerator {
    private final Random random = new Random();

    public double nextMarketReturn(int newsIndex) {
        double lowerBound = 0.0;
        double upperBound = 0.0;

        switch (newsIndex) {
            case 1://case 1:x will be a random number between 1% and 2%
                lowerBound = 1.0;
                upperBound = 2.0;
                break;
            case 6: //case 6:x will be a random number between -2% to -1%
                lowerBound = -2.0;
                upperBound = -1.0;
                break;
            case 7://case 7:x will be a random number between -3% to - 2%
                lowerBound = -3.0;
                upperBound = -2.0;
                break;
            default:
                lowerBound = -3.0;
                upperBound = 3.0;
                break;
        }

        double marketReturn = lowerBound + (upperBound - lowerBound) * random.nextDouble();
        marketReturn /= 100.0;

        return marketReturn;
    }
}

