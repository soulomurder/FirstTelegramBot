package tg.bot.tgbot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tg.bot.tgbot.controller.TgController;
import tg.bot.tgbot.gui.MainGUI;
import tg.bot.tgbot.util.ConsoleOutput;

@SpringBootApplication
public class TgBotApplication {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TgController());
            ConsoleOutput.welcome();
            MainGUI.enableGUI();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
