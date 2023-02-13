package players;

import account.Account;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import storage.StockInventory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {
    private Map<String,Integer> playerStockMap;
    private StockInventory stockInventory = new StockInventory();
    Player player = new Player("TestPlayer",new Account("testingAccount"));
    ComputerTest() throws FileNotFoundException {
    }

    @Test
    public void testGetBalanceFromHolding() {
        playerStockMap = new HashMap<>(){{
            put("AAPL",1);
            put("NKE",1);
        }};

        player.setStocks(playerStockMap);

        double expectedBalance= 271.05;
        double actualBalance= player.getBalanceFromHolding(stockInventory);
        assertEquals(expectedBalance,actualBalance);

    }
}