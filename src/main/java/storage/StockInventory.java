package storage;

import stock.Stock;

import java.io.FileNotFoundException;
import java.util.List;

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
}
