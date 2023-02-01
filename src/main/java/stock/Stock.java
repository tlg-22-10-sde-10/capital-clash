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
    private ArrayList<Double> priceHistory;
    private StockType sector;

    // constructors
    public Stock(String stockName, String symbol, double currentPrice, double beta,
                 double alpha, double residual, ArrayList<Double> priceHistory, StockType sector) {
        this.stockName = stockName;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.beta = beta;
        this.alpha = alpha;
        this.residual = residual;
        this.priceHistory = priceHistory;
        this.sector = sector;
    }

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

    public ArrayList<Double> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(ArrayList<Double> priceHistory) {
        this.priceHistory = priceHistory;
    }


    public StockType getSector() {
        return sector;
    }

    public void setSector(StockType sector) {
        this.sector = sector;
    }
}
