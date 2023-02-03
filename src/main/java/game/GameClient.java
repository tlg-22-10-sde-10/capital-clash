package game;

import stock.Stock;
import storage.StockFileReader;
import storage.StockInventory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameClient {

    public static void main(String[] args) throws FileNotFoundException {
        Game game=new Game();
        game.gameOn();
    }
}
