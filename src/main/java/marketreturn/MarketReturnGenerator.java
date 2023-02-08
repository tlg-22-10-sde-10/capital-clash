package marketreturn;

import java.util.Random;

public class MarketReturnGenerator {


    Random random = new Random();

    public double nextMarketReturn(int newsIndex){

        double marketReturn;
        if(newsIndex==1){
            //case 1:x will be a random number between 1% and 2%
            marketReturn=(random.nextDouble() * 1 + 1)/100.0;
        }else if(newsIndex==6){
            //case 6:x will be a random number between -2% to -1%
            marketReturn=(random.nextDouble() * 1 - 2)/100.0;
        }else if(newsIndex==7){
            //case 7:x will be a random number between -2% to - 3%
            marketReturn=(random.nextDouble() * 1 - 3)/100.0;
        }else{
            marketReturn=(random.nextDouble() * 6 - 3)/100.0;
        }
        return marketReturn;
    }
}
