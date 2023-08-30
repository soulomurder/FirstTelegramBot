package tg.bot.tgbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.bot.tgbot.config.BotConfig;
import tg.bot.tgbot.util.ConsoleOutput;

import java.util.List;

@Slf4j
@Component
public class TgController extends TelegramLongPollingBot{

    public TgController() {
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message msg = update.getMessage();
            msg.getFrom().getId();
            String chatID = msg.getChatId().toString();
            SendMessage sendMsg = new SendMessage();
            sendMsg.setChatId(chatID);
            MenuButton menuButton = new MenuButton() {
                @Override
                public String getType() {
                    return null;
                }
            };

            MessageEntity me = new MessageEntity();
            me.setType("bold");
            List<MessageEntity> listOfEntities = List.of(me);

            String text;
            if (msg.hasAnimation()
                    || msg.hasAudio()
                    || msg.hasContact()
                    || msg.hasDice()
                    || msg.hasDocument()
                    || msg.hasLocation()
                    || msg.hasPhoto()
                    || msg.hasPoll()
                    || msg.hasSticker()
                    || msg.hasVideo()
                    || msg.hasVoice()) {
                me.setOffset(0);
                me.setLength(70);
                sendMsg.setEntities(listOfEntities);
                text = "Прости, но я могу развернуть задом наперёд только текстовые сообщения.";
            }
            else if (msg.hasText()) {
                text = parseMessage(msg.getText());
            }
            else {
                me.setOffset(0);
                me.setLength(47);
                sendMsg.setEntities(listOfEntities);
                text = "Прости, я не нашёл текста в данном сообщении :(";
            }
            sendMsg.setText(text);

            String username = msg.getFrom().getUserName();
            try {
                execute(sendMsg);
                ConsoleOutput.success(username, chatID);
            } catch (TelegramApiException e) {
                ConsoleOutput.fail(username, chatID);
                System.out.println(e.getMessage());
            }
        }
    }

    private String parseMessage(String textMsg){
        return textMsg.equals("/start")
                ? "Привет! Пришли мне любое сообщение, и я напишу его тебе задом наперёд."
                : new StringBuilder(textMsg).reverse().toString();
    }

    @Override
    public String getBotUsername() {
        return BotConfig.botName;
    }

    @Override
    public String getBotToken() {
        return BotConfig.token;
    }
}
