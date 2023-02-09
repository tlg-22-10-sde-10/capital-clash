package news;


import java.util.Map;

public class News {

    Map<Integer, String> news= Map.of(
            //case 1:x will be a random number between 1% and 2%
            1,"Cooler Pay Gains Add to Debate on When Fed Might Pause Rate Hikes!",
            //case 2:BA residual will be random between 3% and 5%
            2,"Boeing to set up a new 737 MAX assembly line in Everett!",
            //case 3:PFE residual will be random between -4% and -2%
            3,"Pfizer Bids Adieu to Covid Boom Years!",
            //case 4:Meta residual will be random between 3% and 6%
            4,"Facebook and Instagram’s parent said it is paring back spending plans and focusing on its social media roots!",
            //case 5:JPM residual will be random between -5% and -2%
            5,"JPMorgan says CEO Jamie Dimon is having emergency heart surgery!",
            //case 6:x will be a random number between -2% to -1%
            6,"Powell: Inflation Has Eased Somewhat but Remains Elevated!",
            //case 7:x will be a random number between -2% to - 3%
            7,"U.S. has intel that Russian commanders have orders to proceed with Finland invasion!",
            //case 8:UNH residual will be a random number between -4% to - 5%
            8,"Justice Dept. Sues to Block $13 Billion Deal by UnitedHealth Group!",
            //case 9:AAPL residual +3%  TSLA residual -3%
            9,"Apple may launch Apple Cars in 2024!",
            //case 10:COST residual will be random between 2% and 4%
            10,"Costco Might Raise Membership Fees in 2023!");


    public String getNewsContent(int num){
        return news.get(num);
    }

}
