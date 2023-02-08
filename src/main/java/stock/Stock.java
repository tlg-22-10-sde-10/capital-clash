package stock;

import storage.StockType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Stock {
    private static final DecimalFormat df=new DecimalFormat("0.00");
    private String stockName;
    private String symbol;
    private double currentPrice;
    private double beta;
    private double alpha;
    private double residual;
    private StockType sector;

    // constructors
    public Stock(String stockName, String symbol, double currentPrice, double beta,
                 double alpha, double residual, StockType sector) {
        this.stockName = stockName;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.beta = beta;
        this.alpha = alpha;
        this.residual = residual;
        this.sector = sector;
    }

    // getters and setters
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getResidual() {
        return residual;
    }

    public void setResidual(double residual) {
        this.residual = residual;
    }

    public StockType getSector() {
        return sector;
    }

    public void setSector(StockType sector) {
        this.sector = sector;
    }

    public String toString() {

        String result = String.format("%-10s %-20s %-15s %-18s  %-11s","",
                getStockName(),getSymbol(),getCurrentPrice(),getSector());

        return result;
    }

    // price calculator based on the day
    public double UpdateStockPriceForTheDay(double currentPrice, double mktReturn, int newsIndex) {

        double ans=999;
        switch (newsIndex) {
            //case 1, 6, 7 will  be handled in game.java because only the mktReturn is the variable.
            case 2:
                if(this.symbol.equalsIgnoreCase("BA")){
                    Random random = new Random();
                    double residualInMethod=(random.nextDouble() * (5 - 3) + 3) / 100.0;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }
                break;

            case 3:
                if(this.symbol.equalsIgnoreCase("PFE")){
                    Random random = new Random();
                    double residualInMethod=(random.nextDouble() * (2) + (-4)) / 100.0;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }

                break;

            case 4:

                if(this.symbol.equalsIgnoreCase("META")){
                    Random random = new Random();
                    double residualInMethod=(random.nextDouble() * (6 - 3) + 3) / 100.0;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }

                break;

            case 5:
                if(this.symbol.equalsIgnoreCase("JPM")){
                    Random random = new Random();
                    double residualInMethod=(random.nextDouble() * (3) - 5) / 100;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }

                break;

            case 8:
                if(this.symbol.equalsIgnoreCase("UNH")){
                    Random random = new Random();
                    double residualInMethod=(random.nextDouble() * (1) - 5) / 100;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }

                break;

            case 9:
                if(this.symbol.equalsIgnoreCase("AAPL")){
                    double residualInMethod=0.03;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else if(this.symbol.equalsIgnoreCase("TSLA")){
                    double residualInMethod=-0.03;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }
                else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }

                break;

            case 10:
                if(this.symbol.equalsIgnoreCase("COST")){
                    Random random = new Random();
                    double residualInMethod=(random.nextDouble() * (2) + 2) / 100;
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+
                            this.alpha+residualInMethod)));
                }else{
                    ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));
                }

                break;

            default:
                ans= Double.parseDouble(df.format(currentPrice*(1+mktReturn*this.beta+this.alpha)));

        }
        return ans;
    }

}
