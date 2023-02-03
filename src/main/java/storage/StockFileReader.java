package storage;

import stock.Stock;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StockFileReader {
    private Scanner scanner;
    private File itemFile;

    public List<Stock> processStockInventory() throws FileNotFoundException {

        itemFile = new File("StockFileSystem.csv");
        List<Stock> stocksList = new ArrayList<>();
        scanner = new Scanner(itemFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] fields = line.split("\\|");
            Stock stock = new Stock(fields[0], fields[1],Double.parseDouble(fields[2]),
                    Double.parseDouble(fields[3]), Double.parseDouble(fields[4]),Double.parseDouble(fields[5]),StockType.valueOf(fields[6]));
            stocksList.add(stock);
        }
        return stocksList;
    }
}
