package game;
import random.RandomNumberForNews;
import marketreturn.MarketReturnGenerator;
import ui.GlobalMethodsAndAttributes;
import ui.TradingRoomMenuOne;
import ui.TradingRoomMenuTwo;
import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import static ui.GlobalMethodsAndAttributes.*;

public class Game {

    public void gameOn() throws FileNotFoundException {
        GlobalMethodsAndAttributes.initializeGlobalInstances();
        ui.displayASCII();
        boolean isStartMenuRunning = true;
        while (isStartMenuRunning) {
            ui.startMenu();
            String selection = ui.userInput();
            if (selection.equalsIgnoreCase(NUMBER_ONE)) {
                play();
                isStartMenuRunning = false;
            } else if (selection.equalsIgnoreCase(NUMBER_TWO)) {
                ui.thankYouMessage();
                isStartMenuRunning = false;
            } else {
                ui.invalidChoice();
            }
        }
    }

    private void play() throws IllegalArgumentException, InputMismatchException {
        try {
            ui.displayGameInfo();
            int day = 0;
            while (day < GAME_DAYS) {

                int newsIndexOfTheDay = RandomNumberForNews.getRandomNumber();
                String todayNews = news.getNewsContent(newsIndexOfTheDay);
                MarketReturnGenerator generator = new MarketReturnGenerator();
                double mktReturnOfTheDay = generator.nextMarketReturn(newsIndexOfTheDay);
                updateDashboard(day, newsIndexOfTheDay, mktReturnOfTheDay);

                ui.playerVsBrotherReports(day, player, brother, inventory);
                // for the main menu to keep running
                boolean isMainMenuRunning = true;
                while(isMainMenuRunning) {
                    ui.mainMenu();
                    String mainMenuSelections = ui.userInput();
                    if(mainMenuSelections.equalsIgnoreCase(NUMBER_ONE)) {
                        // for the trading room menu to keep running
                        boolean isTradingRoomMenuRunning = true;
                        while (isTradingRoomMenuRunning) {
                            // shows stock Inventory
                            showStockInventory(day);
                            ui.tradingRoomMenu();
                            String userInputForBuyAndSale = ui.userInput();
                            // BUY logic
                            if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_ONE)) {
                                // calls static method from tradingRoomOne class
                                TradingRoomMenuOne.menuOneBuy(day);
                                // SELL LOGIC
                            } else if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_TWO)) {
                                // calls static method from tradingRoomTwo class
                                TradingRoomMenuTwo.menuTwoSell();
                                // VIEW ACCOUNT
                            } else if (userInputForBuyAndSale.equalsIgnoreCase(NUMBER_THREE)) {
                                ui.playerVsBrotherReports(day, player, brother, inventory);
                                isTradingRoomMenuRunning = false;
                                // LEAVE TRADING ROOM
                            } else {
                                ui.invalidChoice();
                            }
                        }

                    } else if(mainMenuSelections.equalsIgnoreCase(NUMBER_TWO)){
                        ui.newsRoomOps(todayNews);
                        ui.playerVsBrotherReports(day, player, brother, inventory);

                    } else if(mainMenuSelections.equalsIgnoreCase(NUMBER_THREE)){
                        GlobalMethodsAndAttributes.nextDayOps(day);
                        isMainMenuRunning = false;
                    } else {
                        ui.invalidChoice();
                    }
                }
                day++;
            }
        } catch (InputMismatchException | NumberFormatException e) {
            ui.invalidChoice();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  }