package storage;

import stock.Stock;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StockInventory implements IStockInventory{
    StockFileReader reader = new StockFileReader();
    private List<Stock> stockCollections;

    public StockInventory() throws FileNotFoundException{
        stockCollections = reader.processStockInventory();
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockCollections;
    }

    @Override
    public Stock findBySymbol(String symbol) {
        Stock result = null;
        List<Stock> stockStream = stockCollections.stream()
                .filter(stock -> stock.getSymbol().equals(symbol))
                .collect(Collectors.toList());

        if (stockStream.size() == 1){
            result = stockStream.get(0);
        }
        return result;
    }

    @Override
    public Stock getRandomStock() {
        int randomNum = 0 + (int)(Math.random() * 10);
        return stockCollections.get(randomNum);
    }
}
