package news;


import java.util.Map;

public class News {

    Map<Integer, String> news= Map.of(1,"Cooler Pay Gains Add to Debate on When Fed Might Pause Rate Hikes",
            2,"Boeing to set up a new 737 MAX assembly line in Everett",
            3,"Pfizer Bids Adieu to Covid Boom Years",
            4,"Facebook and Instagram’s parent said it is paring back spending plans and focusing on its social media roots",
            5,"JPMorgan says CEO Jamie Dimon is having emergency heart surgery",
            6,"Powell: Inflation Has Eased Somewhat but Remains Elevated",
            7,"U.S. has intel that Russian commanders have orders to proceed with Finland invasion",
            8,"Justice Dept. Sues to Block $13 Billion Deal by UnitedHealth Group",
            9,"Apple may launch Apple Cars in 2024",
            10,"Costco Might Raise Membership Fees in 2023.");


    public String getNewsContent(int num){
        return news.get(num);
    }

}
