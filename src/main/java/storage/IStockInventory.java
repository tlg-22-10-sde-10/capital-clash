package storage;

import stock.Stock;

import java.util.List;

public interface IStockInventory {

    List<Stock> getAllStocks();
    Stock findBySymbol(String symbol);
    Stock getRandomStock();
}
