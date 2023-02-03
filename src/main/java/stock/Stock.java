package stock;

import storage.StockType;

import java.util.ArrayList;

public class Stock {
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

    public void setCurrentPrice(double alpha, double beta,double residual) {
        // y = a+bx+u
        /*
        y is the performance of the stock or fund.
        a is alpha, which is the excess return of the stock or fund. (this will be randomized)
        b is beta, which is volatility relative to the benchmark. (Each stock has a different beta)
        x is the performance of the benchmark, which is often the S&P 500 index. (this will be randomized)
        u is the residual, which is the unexplained random portion of performance. (This will be randomized and adds more uncertainty to the game)
         */
        double x= this.currentPrice + (int)((Math.random() * this.currentPrice)+10);
        this.currentPrice = alpha+(beta*x)+residual;
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
        String result = String.format("%-10s %-20s %-15s %-18s %-10s %-10s %-18s %-11s","",
                getStockName(),getSymbol(),getCurrentPrice(),getBeta(),getAlpha(),getResidual(),getSector());
        return result;
    }
    // price calculator based on the day



}
